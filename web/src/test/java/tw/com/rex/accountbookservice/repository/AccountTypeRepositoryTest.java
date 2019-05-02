package tw.com.rex.accountbookservice.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import tw.com.rex.accountbookservice.dao.AccountTypeDAO;
import tw.com.rex.accountbookservice.repository.base.BaseRepositoryTest;

import static org.junit.Assert.assertNotNull;

@Sql({"/db/data/test/data-account_type.sql"})
public class AccountTypeRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private AccountTypeRepository repository;

    @Test
    public void findByName() {
        AccountTypeDAO dao = repository.findByName("銀行");
        assertNotNull(dao);
    }

}
