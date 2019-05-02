package tw.com.rex.accountbookservice.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.util.CollectionUtils;
import tw.com.rex.accountbookservice.dao.AccountDAO;
import tw.com.rex.accountbookservice.exception.RepositoryException;
import tw.com.rex.accountbookservice.repository.AccountRepository;
import tw.com.rex.accountbookservice.service.base.BaseServiceTest;
import tw.com.rex.accountbookservice.service.impl.AccountServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AccountServiceTest extends BaseServiceTest {

    @InjectMocks
    private AccountServiceImpl service;
    @Mock
    private AccountRepository repository;
    private AccountDAO entity;
    private AccountDAO dao;

    @Before
    public void setUp(){
        entity = new AccountDAO();
        entity.setName("test");

        dao = new AccountDAO();
        dao.setId("a");
        dao.setName("test");
        dao.setCreateDate(LocalDate.now());
    }

    @Test
    public void save() {
        when(repository.save(any(AccountDAO.class))).thenReturn(dao);
        service.save(entity);
        // then
        verify(repository, atLeastOnce()).save(any(AccountDAO.class));
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
        boolean result = service.deleteById("a");
        // then
        verify(repository, atLeastOnce()).deleteById("a");
        assertTrue("delete id " + "a" + " failure", result);
    }

    @Test(expected = RepositoryException.class)
    public void deleteByIdThrowException() {
        // given
        doThrow(RepositoryException.class).when(repository).deleteById(anyString());
        // when
        boolean result = service.deleteById("a");
        // then
        verify(repository, atLeastOnce()).deleteById("a");
        assertFalse("delete id " + "a" + " failure", result);
    }

    @Test
    public void findById() {
        // given
        AccountDAO dao = getAccountDAO();
        // when
        when(repository.findById(anyString())).thenReturn(Optional.of(dao));
        AccountDAO result = service.findById("a");
        // then
        verify(repository, atLeastOnce()).findById(anyString());
        assertEquals(dao.getId(), result.getId());
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
        when(repository.findById(anyString())).thenReturn(Optional.of(dao));
        when(repository.save(any(AccountDAO.class))).thenReturn(dao);
        AccountDAO result = service.update(dao);
        // then
        verify(repository, atLeastOnce()).findById(anyString());
        verify(repository, atLeastOnce()).save(any(AccountDAO.class));
        assertEquals(dao.getId(), result.getId());
    }

    @Test(expected = RepositoryException.class)
    public void updateThrowException() {
        // given
        AccountDAO dao = getAccountDAO();
        // when
        doThrow(RepositoryException.class).when(repository).save(any(AccountDAO.class));
        when(repository.findById(anyString())).thenReturn(Optional.of(dao));
        service.update(dao);
        // then
        verify(repository, atLeastOnce()).findById(anyString());
        verify(repository, atLeastOnce()).save(any(AccountDAO.class));
    }

    public AccountDAO getAccountDAO() {
        AccountDAO dao = new AccountDAO();
        dao.setId("a");
        dao.setName("test");
        return dao;
    }

}
