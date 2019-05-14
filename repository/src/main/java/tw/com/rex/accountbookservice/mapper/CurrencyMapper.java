package tw.com.rex.accountbookservice.mapper;

import org.apache.ibatis.annotations.Mapper;
import tw.com.rex.accountbookservice.model.dao.Currency;

import java.util.List;

@Mapper
public interface CurrencyMapper {

    int deleteByPrimaryKey(String id);

    int insertSelective(Currency record);

    Currency selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Currency record);

    List<Currency> selectAll();
}