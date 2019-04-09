package tw.com.rex.accountbookservice.service.impl.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.CollectionUtils;
import tw.com.rex.accountbookservice.exception.RepositoryException;
import tw.com.rex.accountbookservice.model.dao.base.BaseDAO;
import tw.com.rex.accountbookservice.model.vo.base.BaseVO;
import tw.com.rex.accountbookservice.service.base.BaseService;

import java.lang.reflect.ParameterizedType;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings("all")
public abstract class BaseServiceImpl<B extends JpaRepository, E extends BaseDAO, R extends BaseVO>
        implements BaseService<E, R> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private B repository;
    private Class<R> rClass;

    public BaseServiceImpl(B repository) {
        this.repository = repository;
        rClass = (Class<R>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[2];
    }

    @Override
    public R save(E entity) throws RepositoryException {
        entity.setCreateDate(LocalDate.now());
        entity.setUpdateDate(LocalDate.now());
        R result = newResultInstance().get();
        try {
            E dao = (E) repository.save(entity);
            BeanUtils.copyProperties(dao, result);
        } catch (RuntimeException e) {
            throw new RepositoryException("save " + entity.getClass().getSimpleName() + " failure " + entity, e);
        }
        return result;
    }

    @Override
    public boolean deleteById(long id) throws RepositoryException {
        try {
            repository.deleteById(id);
        } catch (RuntimeException e) {
            throw new RepositoryException("delete id " + id + " failure", e);
        }
        return true;
    }

    @Override
    public R findById(long id) {
        R result = newResultInstance().get();
        Optional<E> daoOptional = repository.findById(id);
        if (daoOptional.isPresent()) {
            BeanUtils.copyProperties(daoOptional.get(), result);
        }
        return result;
    }

    @Override
    public List<R> findAll() {
        List<E> all = repository.findAll();
        if (!CollectionUtils.isEmpty(all)) {
            return all.stream()//
                    .map(this::newResultInstance)//
                    .map(Optional::get)//
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public R update(E entity) {
        R result = newResultInstance().get();
        Optional<E> daoOptional = repository.findById(entity.getId());
        if (daoOptional.isPresent()) {
            E dao = daoOptional.get();
            BeanUtils.copyProperties(entity, dao, "id", "createDate");
            dao.setUpdateDate(LocalDate.now());
            try {
                dao = (E) repository.save(dao);
                result = newResultInstance(dao).get();
            } catch (Exception e) {
                throw new RepositoryException("update " + dao.getClass().getSimpleName() + " failure " + entity, e);
            }
        }
        return result;
    }

    private Optional<R> newResultInstance() {
        Optional<R> result = Optional.empty();
        try {
            result = Optional.of(rClass.newInstance());
        } catch (Exception e) {
            loggerNewReturnInstance(e);
        }
        return result;
    }

    private Optional<R> newResultInstance(E param) {
        Optional<R> result = Optional.empty();
        try {
            result = Optional.of(rClass.getDeclaredConstructor(param.getClass()).newInstance(param));
        } catch (Exception e) {
            loggerNewReturnInstance(e);
        }
        return result;
    }

    private void loggerNewReturnInstance(Exception e) {
        e.printStackTrace();
        logger.error("new {} instance error, {}", rClass.getName(), e.getMessage());
    }
}
