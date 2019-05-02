package tw.com.rex.accountbookservice.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import tw.com.rex.accountbookservice.dao.CurrencyDAO;
import tw.com.rex.accountbookservice.repository.base.BaseRepositoryTest;

import static org.junit.Assert.assertEquals;

@Sql({"/db/data/test/data-currency.sql"})
public class CurrencyRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private CurrencyRepository repository;

    @Test
    public void findByName() {
        CurrencyDAO dao = repository.findByName("新台幣");
        assertEquals("新台幣", dao.getName());
    }

}
