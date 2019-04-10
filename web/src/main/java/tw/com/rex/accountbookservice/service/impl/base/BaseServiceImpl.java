package tw.com.rex.accountbookservice.service.impl.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.CollectionUtils;
import tw.com.rex.accountbookservice.exception.RepositoryException;
import tw.com.rex.accountbookservice.model.dao.base.BaseDAO;
import tw.com.rex.accountbookservice.service.base.BaseService;

import java.lang.reflect.ParameterizedType;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("all")
public abstract class BaseServiceImpl<B extends JpaRepository, E extends BaseDAO>
        implements BaseService<E> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private B repository;
    private Class<E> eClass;

    public BaseServiceImpl(B repository) {
        this.repository = repository;
        eClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    @Override
    public E save(E entity) throws RepositoryException {
        entity.setCreateDate(LocalDate.now());
        E result = newResultInstance().get();
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
    public E findById(long id) {
        E result = newResultInstance().get();
        Optional<E> daoOptional = repository.findById(id);
        if (daoOptional.isPresent()) {
            BeanUtils.copyProperties(daoOptional.get(), result);
        }
        return result;
    }

    @Override
    public List<E> findAll() {
        List<E> all = repository.findAll();
        if (!CollectionUtils.isEmpty(all)) {
            return all;
        }
        return Collections.emptyList();
    }

    @Override
    public E update(E entity) {
        E result = newResultInstance().get();
        Optional<E> daoOptional = repository.findById(entity.getId());
        if (daoOptional.isPresent()) {
            E dao = daoOptional.get();
            BeanUtils.copyProperties(entity, dao, "id", "createDate");
            dao.setUpdateDate(LocalDate.now());
            try {
                result = (E) repository.save(dao);
            } catch (Exception e) {
                throw new RepositoryException("update " + dao.getClass().getSimpleName() + " failure " + entity, e);
            }
        }
        return result;
    }

    private Optional<E> newResultInstance() {
        Optional<E> result = Optional.empty();
        try {
            result = Optional.of(eClass.newInstance());
        } catch (Exception e) {
            loggerNewReturnInstance(e);
        }
        return result;
    }

    private void loggerNewReturnInstance(Exception e) {
        e.printStackTrace();
        logger.error("new {} instance error, {}", eClass.getName(), e.getMessage());
    }
}
