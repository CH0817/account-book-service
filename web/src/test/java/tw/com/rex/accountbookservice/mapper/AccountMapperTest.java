package tw.com.rex.accountbookservice.mapper;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import tw.com.rex.accountbookservice.mapper.base.BaseMapperTest;
import tw.com.rex.accountbookservice.model.dao.Account;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Sql({"/db/data/test/data-currency.sql", "/db/data/test/data-account_type.sql", "/db/data/test/data-account.sql"})
public class AccountMapperTest extends BaseMapperTest {

    @Autowired
    private AccountMapper mapper;
    private Account entity;

    @Before
    public void setUp() {
        entity = new Account();
        entity.setName("XD");
        entity.setAccountTypeId("c");
        entity.setCurrencyId("a");
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
        assertEquals(6, mapper.selectAll().size());
    }

}
