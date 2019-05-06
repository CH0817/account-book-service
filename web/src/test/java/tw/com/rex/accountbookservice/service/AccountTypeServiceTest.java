package tw.com.rex.accountbookservice.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import tw.com.rex.accountbookservice.dao.AccountTypeDAO;
import tw.com.rex.accountbookservice.exception.LackNecessaryDataException;
import tw.com.rex.accountbookservice.exception.NotFoundDataException;
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
        dao.setAccounts(new ArrayList<>());
    }

    @Test
    public void save() {
        when(repository.save(entity)).thenReturn(dao);
        AccountTypeDAO result = service.save(entity);
        verify(repository, atLeastOnce()).save(any(AccountTypeDAO.class));
        assertNotNull(result.getId());
    }

    @Test(expected = LackNecessaryDataException.class)
    public void saveThrowException() {
        service.save(new AccountTypeDAO());
    }

    @Test
    public void deleteById() {
        boolean result = service.deleteById("a");
        verify(repository, atLeastOnce()).deleteById("a");
        assertTrue(result);
    }

    @Test
    public void findById() {
        when(repository.findById(anyString())).thenReturn(Optional.of(dao));
        AccountTypeDAO result = service.findById("a");
        verify(repository, atLeastOnce()).findById(anyString());
        assertNotNull(result);
    }

    @Test(expected = NotFoundDataException.class)
    public void findByIdThrowNotFoundDataException() {
        service.findById("a");
        verify(repository, atLeastOnce()).findById(anyString());
    }

    @Test
    public void findAll() {
        List<AccountTypeDAO> daoList = new ArrayList<>();
        daoList.add(dao);
        daoList.add(dao);
        daoList.add(dao);
        when(repository.findAll()).thenReturn(daoList);
        List<AccountTypeDAO> result = service.findAll();
        verify(repository, atLeastOnce()).findAll();
        assertEquals(3, result.size());
    }

    @Test
    public void update() {
        when(repository.findById("a")).thenReturn(Optional.of(dao));
        entity.setId("a");
        entity.setName("test_name");
        when(repository.save(any(AccountTypeDAO.class))).thenReturn(entity);
        AccountTypeDAO result = service.update(entity);
        verify(repository, atLeastOnce()).save(any(AccountTypeDAO.class));
        assertNotNull(result);
    }

    @Test(expected = LackNecessaryDataException.class)
    public void updateThrowLackNecessaryDataException() {
        when(repository.findById("a")).thenReturn(Optional.of(dao));
        entity.setId("a");
        entity.setName("");
        service.update(entity);
    }

    @Test(expected = NotFoundDataException.class)
    public void updateThrowNotFoundDataException() {
        entity.setId("a");
        service.update(entity);
    }

}
