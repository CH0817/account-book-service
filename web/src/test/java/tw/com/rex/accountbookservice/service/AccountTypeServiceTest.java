package tw.com.rex.accountbookservice.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tw.com.rex.accountbookservice.AccountBookServiceApplication;
import tw.com.rex.accountbookservice.model.dao.AccountTypeDAO;
import tw.com.rex.accountbookservice.model.vo.AccountTypeVO;
import tw.com.rex.accountbookservice.service.impl.AccountTypeServiceImpl;

import javax.transaction.Transactional;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AccountBookServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AccountTypeServiceTest {

    @Autowired
    private AccountTypeServiceImpl service;

    @Test
    // @Sql("/db/schema-mariadb.sql")
    @Transactional
    public void save() throws Exception {
        AccountTypeVO vo = service.save(new AccountTypeDAO("測試"));
        assertNotNull(vo.getId());
    }

}
