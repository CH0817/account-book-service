package tw.com.rex.accountbookservice.mapper;

import org.apache.ibatis.annotations.Mapper;
import tw.com.rex.accountbookservice.model.dao.Trade;

import java.util.List;

@Mapper
public interface TradeMapper {

    int deleteByPrimaryKey(String id);

    int insertSelective(Trade record);

    Trade selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Trade record);

    List<Trade> selectAll();
}