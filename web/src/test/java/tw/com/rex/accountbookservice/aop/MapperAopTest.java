package tw.com.rex.accountbookservice.aop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;
import tw.com.rex.accountbookservice.mapper.AccountTypeMapper;
import tw.com.rex.accountbookservice.model.dao.AccountType;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class MapperAopTest {

    @Autowired
    private AccountTypeMapper mapper;

    @Test(expected = DataIntegrityViolationException.class)
    public void insertNotEnoughData() {
        mapper.insertSelective(new AccountType());
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void updateNotEnoughData() {
        mapper.updateByPrimaryKeySelective(new AccountType());
    }

}
