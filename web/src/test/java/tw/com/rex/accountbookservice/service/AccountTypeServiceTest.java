package tw.com.rex.accountbookservice.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.util.CollectionUtils;
import tw.com.rex.accountbookservice.dao.AccountTypeDAO;
import tw.com.rex.accountbookservice.exception.RepositoryException;
import tw.com.rex.accountbookservice.repository.AccountTypeRepository;
import tw.com.rex.accountbookservice.service.base.BaseServiceTest;
import tw.com.rex.accountbookservice.service.impl.AccountTypeServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AccountTypeServiceTest extends BaseServiceTest {

    @InjectMocks
    private AccountTypeServiceImpl service;
    @Mock
    private AccountTypeRepository repository;
    private AccountTypeDAO entity;
    private AccountTypeDAO dao;

    @Before
    public void setUp() {
        entity = new AccountTypeDAO();
        entity.setName("test");
        dao = new AccountTypeDAO();
        dao.setId("a");
        dao.setName("test");
        dao.setCreateDate(LocalDate.now());
    }

    @Test
    public void save() throws Exception {
        // FIXME 這兒
        when(repository.save(any(AccountTypeDAO.class))).thenReturn(dao);
        service.save(entity);
        verify(repository, atLeastOnce()).save(any(AccountTypeDAO.class));
    }

    @Test(expected = RepositoryException.class)
    public void saveThrowException() throws Exception {
        doThrow(RepositoryException.class).when(repository).save(any(AccountTypeDAO.class));
        service.save(new AccountTypeDAO());
        verify(repository, atLeastOnce()).save(any(AccountTypeDAO.class));
    }

    @Test
    public void deleteById() {
        // given when
        boolean result = service.deleteById("a");
        // then
        verify(repository, atLeastOnce()).deleteById("a");
        assertTrue("delete id " + 1L + " failure", result);
    }

    @Test(expected = RepositoryException.class)
    public void deleteByIdThrowException() {
        // given
        doThrow(RepositoryException.class).when(repository).deleteById(anyString());
        // when
        boolean result = service.deleteById("a");
        // then
        verify(repository, atLeastOnce()).deleteById("a");
        assertFalse("delete id " + 1L + " failure", result);
    }

    @Test
    public void findById() {
        // given
        // when
        when(repository.findById(anyString())).thenReturn(Optional.of(dao));
        service.findById("a");
        // then
        verify(repository, atLeastOnce()).findById(anyString());
    }

    @Test
    public void findAll() {
        // given
        ArrayList<AccountTypeDAO> daoList = new ArrayList<>();
        daoList.add(dao);
        // when
        when(repository.findAll()).thenReturn(daoList);
        List<AccountTypeDAO> accountTypes = service.findAll();
        // then
        verify(repository, atLeastOnce()).findAll();
        assertFalse(CollectionUtils.isEmpty(accountTypes));
    }

    @Test
    public void update() {
        // given
        // when
        when(repository.findById(anyString())).thenReturn(Optional.of(dao));
        when(repository.save(any(AccountTypeDAO.class))).thenReturn(dao);
        service.update(dao);
        // then
        verify(repository, atLeastOnce()).findById(anyString());
        verify(repository, atLeastOnce()).save(any(AccountTypeDAO.class));
    }

    @Test(expected = RepositoryException.class)
    public void updateThrowException() {
        // given
        // when
        doThrow(RepositoryException.class).when(repository).save(any(AccountTypeDAO.class));
        when(repository.findById(anyString())).thenReturn(Optional.of(dao));
        service.update(dao);
        // then
        verify(repository, atLeastOnce()).findById(anyString());
        verify(repository, atLeastOnce()).save(any(AccountTypeDAO.class));
    }

}
