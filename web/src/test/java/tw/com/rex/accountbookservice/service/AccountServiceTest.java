package tw.com.rex.accountbookservice.service;

import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.util.CollectionUtils;
import tw.com.rex.accountbookservice.exception.RepositoryException;
import tw.com.rex.accountbookservice.model.dao.AccountDAO;
import tw.com.rex.accountbookservice.repository.AccountRepository;
import tw.com.rex.accountbookservice.service.base.BaseServiceTest;
import tw.com.rex.accountbookservice.service.impl.AccountServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@Ignore
public class AccountServiceTest extends BaseServiceTest {

    @InjectMocks
    private AccountServiceImpl service;
    @Mock
    private AccountRepository repository;

    @Test
    public void save() {
        // given
        AccountDAO dao = getAccountDAO();
        // when
        when(repository.save(any(AccountDAO.class))).thenReturn(dao);
        AccountDAO result = service.save(new AccountDAO());
        // then
        verify(repository, atLeastOnce()).save(any(AccountDAO.class));
        assertEquals(dao.getId().longValue(), result.getId().longValue());
    }

    @Test(expected = RepositoryException.class)
    public void saveThrowException() {
        // given when
        doThrow(RepositoryException.class).when(repository).save(any(AccountDAO.class));
        service.save(new AccountDAO());
        // then
        verify(repository, atLeastOnce()).save(any(AccountDAO.class));
    }

    @Test
    public void deleteById() {
        // given when
        boolean result = service.deleteById(1L);
        // then
        verify(repository, atLeastOnce()).deleteById(1L);
        assertTrue("delete id " + 1L + " failure", result);
    }

    @Test(expected = RepositoryException.class)
    public void deleteByIdThrowException() {
        // given
        doThrow(RepositoryException.class).when(repository).deleteById(anyLong());
        // when
        boolean result = service.deleteById(1L);
        // then
        verify(repository, atLeastOnce()).deleteById(1L);
        assertFalse("delete id " + 1L + " failure", result);
    }

    @Test
    public void findById() {
        // given
        AccountDAO dao = getAccountDAO();
        // when
        when(repository.findById(anyLong())).thenReturn(Optional.of(dao));
        AccountDAO result = service.findById(1L);
        // then
        verify(repository, atLeastOnce()).findById(anyLong());
        assertEquals(dao.getId().longValue(), result.getId().longValue());
    }

    @Test
    public void findAll() {
        // given
        ArrayList<AccountDAO> daoList = new ArrayList<>();
        daoList.add(getAccountDAO());
        // when
        when(repository.findAll()).thenReturn(daoList);
        List<AccountDAO> accountTypes = service.findAll();
        // then
        verify(repository, atLeastOnce()).findAll();
        assertFalse(CollectionUtils.isEmpty(accountTypes));
    }

    @Test
    public void update() {
        // given
        AccountDAO dao = getAccountDAO();
        // when
        when(repository.findById(anyLong())).thenReturn(Optional.of(dao));
        when(repository.save(any(AccountDAO.class))).thenReturn(dao);
        AccountDAO result = service.update(dao);
        // then
        verify(repository, atLeastOnce()).findById(anyLong());
        verify(repository, atLeastOnce()).save(any(AccountDAO.class));
        assertEquals(dao.getId().longValue(), result.getId().longValue());
    }

    @Test(expected = RepositoryException.class)
    public void updateThrowException() {
        // given
        AccountDAO dao = getAccountDAO();
        // when
        doThrow(RepositoryException.class).when(repository).save(any(AccountDAO.class));
        when(repository.findById(anyLong())).thenReturn(Optional.of(dao));
        service.update(dao);
        // then
        verify(repository, atLeastOnce()).findById(anyLong());
        verify(repository, atLeastOnce()).save(any(AccountDAO.class));
    }

    public AccountDAO getAccountDAO() {
        AccountDAO dao = new AccountDAO();
        dao.setId(1L);
        dao.setName("test");
        return dao;
    }

}
