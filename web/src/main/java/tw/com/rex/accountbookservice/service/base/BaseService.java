package tw.com.rex.accountbookservice.service.base;

import tw.com.rex.accountbookservice.exception.RepositoryException;
import tw.com.rex.accountbookservice.model.dao.base.BaseDAO;

import java.util.List;
import java.util.Optional;

public interface BaseService<E extends BaseDAO> {

    E save(E entity) throws RepositoryException;

    boolean deleteById(long id) throws RepositoryException;

    E findById(long id);

    List<E> findAll();

    E update(E entity);
    
}
