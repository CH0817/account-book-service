package tw.com.rex.accountbookservice.mapper;

import org.apache.ibatis.annotations.Mapper;
import tw.com.rex.accountbookservice.model.dao.Account;

import java.util.Collection;
import java.util.List;

@Mapper
public interface AccountMapper {

    int deleteByPrimaryKey(String id);

    int insertSelective(Account record);

    Account selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Account record);

    List<Account> selectAll();
}