package tw.com.rex.accountbookservice.mapper;

import tw.com.rex.accountbookservice.model.dao.Item;

public interface ItemMapper {

    int deleteByPrimaryKey(String id);

    int insertSelective(Item record);

    Item selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Item record);

}