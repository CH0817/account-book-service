package tw.com.rex.accountbookservice.mapper;

import org.apache.ibatis.annotations.Mapper;
import tw.com.rex.accountbookservice.model.dao.AccountType;

import java.util.List;

@Mapper
public interface AccountTypeMapper {

    int deleteByPrimaryKey(String id);

    int insertSelective(AccountType record);

    AccountType selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AccountType record);

    List<AccountType> selectAll();
}