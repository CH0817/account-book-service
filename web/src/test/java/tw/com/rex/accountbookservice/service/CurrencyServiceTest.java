package tw.com.rex.accountbookservice.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import tw.com.rex.accountbookservice.dao.CurrencyDAO;
import tw.com.rex.accountbookservice.exception.LackNecessaryDataException;
import tw.com.rex.accountbookservice.repository.CurrencyRepository;
import tw.com.rex.accountbookservice.service.base.BaseServiceTest;
import tw.com.rex.accountbookservice.service.impl.CurrencyServiceImpl;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CurrencyServiceTest extends BaseServiceTest {

    @InjectMocks
    private CurrencyServiceImpl service;
    @Mock
    private CurrencyRepository repository;
    private CurrencyDAO entity;
    private CurrencyDAO dao;

    @Before
    public void setUp() {
        entity = new CurrencyDAO();
        entity.setName("test");
        dao = new CurrencyDAO();
        dao.setId("a");
        dao.setName("test");
        dao.setCreateDate(LocalDate.now());
        dao.setAccounts(new ArrayList<>());
    }

    @Test
    public void save() {
        when(repository.save(entity)).thenReturn(dao);
        CurrencyDAO result = service.save(entity);
        verify(repository, atLeastOnce()).save(any(CurrencyDAO.class));
        assertNotNull(result.getId());
    }

    @Test(expected = LackNecessaryDataException.class)
    public void saveThrowException() {
        service.save(new CurrencyDAO());
    }

    @Test
    public void deleteById() {
        boolean result = service.deleteById("a");
        verify(repository, atLeastOnce()).deleteById("a");
        assertTrue(result);
    }

    @Test
    public void findById() {
        when(repository.getOne(anyString())).thenReturn(dao);
        CurrencyDAO result = service.findById("a");
        verify(repository, atLeastOnce()).getOne(anyString());
        assertNotNull(result);
    }

    @Test(expected = EntityNotFoundException.class)
    public void findByIdThrowNotFoundDataException() {
        doThrow(EntityNotFoundException.class).when(repository).getOne(anyString());
        service.findById("a");
        verify(repository, atLeastOnce()).getOne(anyString());
    }

    @Test
    public void findAll() {
        List<CurrencyDAO> daoList = new ArrayList<>();
        daoList.add(dao);
        daoList.add(dao);
        daoList.add(dao);
        when(repository.findAll()).thenReturn(daoList);
        List<CurrencyDAO> result = service.findAll();
        verify(repository, atLeastOnce()).findAll();
        assertEquals(3, result.size());
    }

    @Test
    public void update() {
        when(repository.getOne("a")).thenReturn(dao);
        entity.setId("a");
        entity.setName("test_name");
        when(repository.save(any(CurrencyDAO.class))).thenReturn(entity);
        CurrencyDAO result = service.update(entity);
        verify(repository, atLeastOnce()).save(any(CurrencyDAO.class));
        assertNotNull(result);
    }

    @Test(expected = LackNecessaryDataException.class)
    public void updateThrowLackNecessaryDataException() {
        when(repository.getOne("a")).thenReturn(dao);
        entity.setId("a");
        entity.setName("");
        service.update(entity);
    }

}
