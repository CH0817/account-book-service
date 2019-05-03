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
import java.util.Optional;

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
        service.save(entity);
        verify(repository, atLeastOnce()).save(any(AccountTypeDAO.class));
    }

    @Test(expected = LackNecessaryDataException.class)
    public void saveThrowException() {
        service.save(new AccountTypeDAO());
    }

    @Test
    public void deleteById() {
        service.deleteById("a");
        verify(repository, atLeastOnce()).deleteById("a");
    }

    @Test
    public void findById() {
        when(repository.findById(anyString())).thenReturn(Optional.of(dao));
        service.findById("a");
        verify(repository, atLeastOnce()).findById(anyString());
    }

    @Test(expected = NotFoundDataException.class)
    public void findByIdThrowNotFoundDataException() {
        service.findById("a");
        verify(repository, atLeastOnce()).findById(anyString());
    }

    @Test
    public void findAll() {
        service.findAll();
        verify(repository, atLeastOnce()).findAll();
    }

    @Test
    public void update() {
        when(repository.findById("a")).thenReturn(Optional.of(dao));
        entity.setId("a");
        entity.setName("test_name");
        service.update(entity);
        verify(repository, atLeastOnce()).save(any(AccountTypeDAO.class));
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
