package tw.com.rex.accountbookservice.service.impl.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.CollectionUtils;
import tw.com.rex.accountbookservice.exception.RepositoryException;
import tw.com.rex.accountbookservice.model.dao.base.BaseDAO;
import tw.com.rex.accountbookservice.service.base.BaseService;

import javax.transaction.Transactional;
import java.lang.reflect.ParameterizedType;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("all")
@Transactional
public abstract class BaseServiceImpl<B extends JpaRepository, E extends BaseDAO> implements BaseService<E> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private B repository;
    private Class<E> eClass;

    public BaseServiceImpl(B repository) {
        this.repository = repository;
        eClass = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    @Override
    public E save(E entity) throws RepositoryException {
        entity.setCreateDate(LocalDate.now());
        try {
            entity = (E) repository.save(entity);
        } catch (RuntimeException e) {
            throw new RepositoryException("save " + entity.getClass().getSimpleName() + " failure " + entity, e);
        }
        return entity;
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
        Optional<E> result = repository.findById(id);
        return (result.isPresent()) ? result.get() : result.orElseThrow(
                () -> new RepositoryException("cannot find " + eClass.getSimpleName() + " by id: " + id));
    }

    @Override
    public List<E> findAll() {
        List<E> all = repository.findAll();
        return (CollectionUtils.isEmpty(all)) ? Collections.emptyList() : all;
    }

    @Override
    public E update(E entity) {
        E dao = findById(entity.getId());

        BeanUtils.copyProperties(entity, dao, "id", "createDate");
        dao.setUpdateDate(LocalDate.now());

        try {
            dao = (E) repository.saveAndFlush(dao);
        } catch (Exception e) {
            throw new RepositoryException("update " + entity.getClass().getSimpleName() + " failure " + entity, e);
        }
        return dao;
    }

}
