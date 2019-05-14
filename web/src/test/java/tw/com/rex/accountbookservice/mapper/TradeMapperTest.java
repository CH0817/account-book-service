package tw.com.rex.accountbookservice.mapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import tw.com.rex.accountbookservice.mapper.base.BaseMapperTest;
import tw.com.rex.accountbookservice.model.dao.Trade;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Sql({"/db/data/test/data-currency.sql", "/db/data/test/data-account_type.sql", "/db/data/test/data-account.sql",
      "/db/data/test/data-category.sql", "/db/data/test/data-item.sql", "/db/data/test/data-trade.sql"})
public class TradeMapperTest extends BaseMapperTest {

    @Autowired
    private TradeMapper mapper;
    private Trade entity;

    @Before
    public void setUp() {
        entity = new Trade();
        entity.setAccountId("a");
        entity.setItemId("a");
        entity.setTransactDate(new Date());
        entity.setCost(new BigDecimal("5.00"));
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
