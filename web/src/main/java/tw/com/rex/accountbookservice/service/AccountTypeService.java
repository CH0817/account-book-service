package tw.com.rex.accountbookservice.service;

import tw.com.rex.accountbookservice.model.dao.AccountTypeDAO;
import tw.com.rex.accountbookservice.model.vo.AccountTypeVO;

public interface AccountTypeService {

    AccountTypeVO save(AccountTypeDAO entity) throws Exception;
}
