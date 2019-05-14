package tw.com.rex.accountbookservice.mapper;

import tw.com.rex.accountbookservice.model.dao.Currency;

public interface CurrencyMapper {

    int deleteByPrimaryKey(String id);

    int insertSelective(Currency record);

    Currency selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Currency record);

}