package tw.com.rex.accountbookservice.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import tw.com.rex.accountbookservice.mapper.AccountTypeMapper;
import tw.com.rex.accountbookservice.model.dao.AccountType;
import tw.com.rex.accountbookservice.service.base.BaseServiceTest;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@EnableAutoConfiguration(
        exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class,
                   HibernateJpaAutoConfiguration.class})
public class AccountTypeServiceTest extends BaseServiceTest {

    @Autowired
    private AccountTypeService service;
    @MockBean
    private AccountTypeMapper mapper;
    private AccountType entity;

    @Before
    public void setUp() {
        entity = new AccountType();
        entity.setName("測試");
    }

    @Test
    public void insert() {
        service.insert(entity);
        verify(mapper, atLeastOnce()).insertSelective(entity);
    }

    @Test
    public void deleteById() {
        service.deleteById("a");
        verify(mapper, atLeastOnce()).deleteByPrimaryKey("a");
    }

    @Test
    public void update() {
        service.updateById(entity);
        verify(mapper, atLeastOnce()).updateByPrimaryKeySelective(entity);
    }

    @Test
    public void selectById() {
        service.selectById("a");
        verify(mapper, atLeastOnce()).selectByPrimaryKey("a");
    }

    @Test
    public void selectAll() {
        service.selectAll();
        verify(mapper, atLeastOnce()).selectAll();
    }

}
