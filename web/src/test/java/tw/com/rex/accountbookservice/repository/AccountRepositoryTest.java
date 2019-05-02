package tw.com.rex.accountbookservice.repository;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import tw.com.rex.accountbookservice.dao.AccountDAO;
import tw.com.rex.accountbookservice.dao.AccountTypeDAO;
import tw.com.rex.accountbookservice.repository.base.BaseRepositoryTest;

import static org.junit.Assert.assertEquals;

@Sql({"/db/data/test/data-currency.sql", "/db/data/test/data-account_type.sql", "/db/data/test/data-account.sql"})
public class AccountRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private AccountRepository repository;
    private AccountTypeDAO accountTypeDAO;

    @Before
    public void setUp() {
        accountTypeDAO = new AccountTypeDAO();
        accountTypeDAO.setId("a");
    }

    @Test
    public void findByNameAndAccountType() {
        AccountDAO dao = repository.findByNameAndAccountType("test_1", accountTypeDAO);
        assertEquals("test_1", dao.getName());
        assertEquals("a", dao.getAccountType().getId());
    }

}
