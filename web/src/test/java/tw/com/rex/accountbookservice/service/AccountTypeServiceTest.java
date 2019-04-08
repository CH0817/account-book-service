package tw.com.rex.accountbookservice.service;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import tw.com.rex.accountbookservice.model.dao.AccountTypeDAO;
import tw.com.rex.accountbookservice.model.vo.AccountTypeVO;
import tw.com.rex.accountbookservice.repository.AccountTypeRepository;
import tw.com.rex.accountbookservice.service.impl.AccountTypeServiceImpl;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class AccountTypeServiceTest {

    @Spy
    @InjectMocks
    private AccountTypeServiceImpl service;
    @Mock
    private AccountTypeRepository repository;

    @Test
    public void save() throws Exception {
        AccountTypeDAO entity = new AccountTypeDAO("XD");
        AccountTypeDAO dao = new AccountTypeDAO("XD");
        dao.setId(1L);
        when(repository.save(entity)).thenReturn(dao);
        AccountTypeVO vo = service.save(entity);
        verify(repository, times(1)).save(entity);
        assertNotNull("return id is null", vo.getId());
    }

}
