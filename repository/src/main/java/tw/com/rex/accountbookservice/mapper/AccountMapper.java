package tw.com.rex.accountbookservice.mapper;

import tw.com.rex.accountbookservice.model.dao.Account;

public interface AccountMapper {

    int deleteByPrimaryKey(String id);

    int insertSelective(Account record);

    Account selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Account record);

}