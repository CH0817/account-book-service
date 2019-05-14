package tw.com.rex.accountbookservice.mapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import tw.com.rex.accountbookservice.model.dao.AccountType;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql({"/db/data/test/data-account_type.sql"})
public class AccountTypeMapperTest {

    @Autowired
    private AccountTypeMapper mapper;
    private AccountType accountType;

    @Before
    public void setUp() {
        accountType = new AccountType();
        accountType.setName("XD");
        accountType.setCreateDate(new Date());
    }

    @Test
    public void insert() {
        mapper.insertSelective(accountType);
        assertNotNull(accountType.getId());
    }

    @Test
    public void deleteByKey() {
        assertEquals(1, mapper.deleteByPrimaryKey("a"));
    }

    @Test
    public void updateByKey() {
        accountType.setId("a");
        accountType.setUpdateDate(new Date());
        assertEquals(1, mapper.updateByPrimaryKeySelective(accountType));
    }

    @Test
    public void selectByKey() {
        assertNotNull(mapper.selectByPrimaryKey("a"));
    }

    @Test
    public void selectAll() {
        assertEquals(3, mapper.selectAll().size());
    }

}
