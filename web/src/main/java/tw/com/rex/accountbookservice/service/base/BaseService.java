package tw.com.rex.accountbookservice.service.base;

import tw.com.rex.accountbookservice.dao.base.BaseDAO;
import tw.com.rex.accountbookservice.exception.LackNecessaryDataException;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface BaseService<E extends BaseDAO> {

    E save(E entity);

    boolean deleteById(String id);

    E findById(String id);

    List<E> findAll();

    E update(E entity);

}
