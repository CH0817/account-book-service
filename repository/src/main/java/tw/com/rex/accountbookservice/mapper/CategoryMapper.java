package tw.com.rex.accountbookservice.mapper;

import org.apache.ibatis.annotations.Mapper;
import tw.com.rex.accountbookservice.model.dao.Category;

import java.util.List;

@Mapper
public interface CategoryMapper {

    int deleteByPrimaryKey(String id);

    int insertSelective(Category record);

    Category selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Category record);

    List<Category> selectAll();
}