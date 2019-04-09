package tw.com.rex.accountbookservice.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import tw.com.rex.accountbookservice.model.dao.AccountTypeDAO;
import tw.com.rex.accountbookservice.model.vo.AccountTypeVO;
import tw.com.rex.accountbookservice.repository.AccountTypeRepository;
import tw.com.rex.accountbookservice.service.impl.AccountTypeServiceImpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountTypeServiceTest {

    @InjectMocks
    private AccountTypeServiceImpl service;
    @Mock
    private AccountTypeRepository repository;

    @Test
    public void save() throws Exception {
        // given
        AccountTypeDAO entity = new AccountTypeDAO();
        AccountTypeDAO dao = new AccountTypeDAO();
        dao.setId(1L);
        // when
        when(repository.save(entity)).thenReturn(dao);
        AccountTypeVO vo = service.save(entity);
        // then
        verify(repository, atLeastOnce()).save(entity);
        assertEquals(1L, vo.getId().longValue());
    }

}
