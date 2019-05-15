package tw.com.rex.accountbookservice.mapper;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import tw.com.rex.accountbookservice.mapper.base.BaseMapperTest;
import tw.com.rex.accountbookservice.model.dao.Category;
import tw.com.rex.accountbookservice.model.define.CategoryTypeEnum;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Sql({"/db/data/test/data-category.sql"})
public class CategoryMapperTest extends BaseMapperTest {

    @Autowired
    private CategoryMapper mapper;
    private Category entity;

    @Before
    public void setUp() {
        entity = new Category();
        entity.setName("XD");
        entity.setCategoryType(CategoryTypeEnum.INCOME.getCode());
        entity.setCreateDate(new Date());
    }

    @Test
    public void insert() {
        mapper.insertSelective(entity);
        assertNotNull(entity.getId());
    }

    @Test
    public void deleteByKey() {
        assertEquals(1, mapper.deleteByPrimaryKey("a"));
    }

    @Test
    public void updateByKey() {
        entity.setId("a");
        entity.setUpdateDate(new Date());
        assertEquals(1, mapper.updateByPrimaryKeySelective(entity));
    }

    @Test
    public void selectByKey() {
        assertNotNull(mapper.selectByPrimaryKey("a"));
    }

    @Test
    public void selectAll() {
        assertEquals(2, mapper.selectAll().size());
    }

}