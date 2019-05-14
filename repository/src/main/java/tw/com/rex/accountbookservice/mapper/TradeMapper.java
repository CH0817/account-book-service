package tw.com.rex.accountbookservice.mapper;

import tw.com.rex.accountbookservice.model.dao.Trade;

public interface TradeMapper {

    int deleteByPrimaryKey(String id);

    int insertSelective(Trade record);

    Trade selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Trade record);

}