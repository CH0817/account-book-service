package tw.com.rex.accountbookservice.service.base;

import tw.com.rex.accountbookservice.model.dao.base.BaseDAO;

import java.util.List;

public interface BaseService<E extends BaseDAO> {

    E save(E entity);

    boolean deleteById(long id);

    E findById(long id);

    List<E> findAll();

    E update(E entity);

}
