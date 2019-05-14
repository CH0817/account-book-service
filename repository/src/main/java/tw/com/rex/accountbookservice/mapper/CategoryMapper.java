package tw.com.rex.accountbookservice.mapper;

import tw.com.rex.accountbookservice.model.dao.Category;

public interface CategoryMapper {

    int deleteByPrimaryKey(String id);

    int insertSelective(Category record);

    Category selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Category record);

}