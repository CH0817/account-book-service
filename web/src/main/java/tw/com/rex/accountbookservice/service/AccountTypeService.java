package tw.com.rex.accountbookservice.service;

import tw.com.rex.accountbookservice.model.dao.AccountType;

import java.util.List;

public interface AccountTypeService {

    String insert(AccountType entity);

    boolean deleteById(String id);

    boolean updateById(AccountType entity);

    AccountType selectById(String id);

    List<AccountType> selectAll();
}
