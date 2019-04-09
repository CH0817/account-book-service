package tw.com.rex.accountbookservice.service;

import tw.com.rex.accountbookservice.exception.RepositoryException;
import tw.com.rex.accountbookservice.model.dao.AccountTypeDAO;
import tw.com.rex.accountbookservice.model.vo.AccountTypeVO;

import java.util.List;

public interface AccountTypeService {

    AccountTypeVO save(AccountTypeDAO entity) throws Exception;

    boolean deleteById(long id) throws RepositoryException;

    AccountTypeVO findById(long id);

    List<AccountTypeVO> findAll();

    AccountTypeVO update(AccountTypeDAO entity);
}
