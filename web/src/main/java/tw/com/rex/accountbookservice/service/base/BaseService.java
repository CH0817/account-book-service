package tw.com.rex.accountbookservice.service.base;

import tw.com.rex.accountbookservice.exception.RepositoryException;
import tw.com.rex.accountbookservice.model.dao.base.BaseDAO;
import tw.com.rex.accountbookservice.model.vo.base.BaseVO;

import java.util.List;

public interface BaseService<E extends BaseDAO, R extends BaseVO> {

    R save(E entity) throws Exception;

    boolean deleteById(long id) throws RepositoryException;

    R findById(long id);

    List<R> findAll();

    R update(E entity);
    
}
