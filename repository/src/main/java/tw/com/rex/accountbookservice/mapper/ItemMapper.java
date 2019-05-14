package tw.com.rex.accountbookservice.mapper;

import org.apache.ibatis.annotations.Mapper;
import tw.com.rex.accountbookservice.model.dao.Item;

import java.util.Collection;
import java.util.List;

@Mapper
public interface ItemMapper {

    int deleteByPrimaryKey(String id);

    int insertSelective(Item record);

    Item selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Item record);

    List<Item> selectAll();
}