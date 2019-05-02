package tw.com.rex.accountbookservice.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tw.com.rex.accountbookservice.repository.base.BaseRepositoryTest;

public class AccountRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private AccountRepository repository;

    @Test
    public void findByNameAndAccountType() {
        System.out.println(repository);
    }

    @Test
    public void findByNameAndAccountTypeAndIdNot() {

    }

}
