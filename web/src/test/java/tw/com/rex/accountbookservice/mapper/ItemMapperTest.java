package tw.com.rex.accountbookservice.mapper;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import tw.com.rex.accountbookservice.mapper.base.BaseMapperTest;
import tw.com.rex.accountbookservice.model.dao.Item;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Sql({"/db/data/test/data-category.sql", "/db/data/test/data-item.sql"})
public class ItemMapperTest extends BaseMapperTest {

    @Autowired
    private ItemMapper mapper;
    private Item entity;

    @Before
    public void setUp() {
        entity = new Item();
        entity.setName("XD");
        entity.setCategoryId("a");
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
        assertEquals(5, mapper.selectAll().size());
    }

}
