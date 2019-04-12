package tw.com.rex.accountbookservice.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import tw.com.rex.accountbookservice.AccountBookServiceApplication;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AccountBookServiceApplication.class)
@Transactional
public class AccountTypeClientTest {

    @Test
    public void save(){
        RestTemplate template = new RestTemplate();
    }

}
