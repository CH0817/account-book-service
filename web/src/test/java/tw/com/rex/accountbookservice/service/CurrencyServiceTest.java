package tw.com.rex.accountbookservice.service;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.util.CollectionUtils;
import tw.com.rex.accountbookservice.exception.RepositoryException;
import tw.com.rex.accountbookservice.model.dao.CurrencyDAO;
import tw.com.rex.accountbookservice.model.vo.CurrencyVO;
import tw.com.rex.accountbookservice.repository.CurrencyRepository;
import tw.com.rex.accountbookservice.service.base.BaseServiceTest;
import tw.com.rex.accountbookservice.service.impl.CurrencyServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CurrencyServiceTest extends BaseServiceTest {

    @InjectMocks
    private CurrencyServiceImpl service;
    @Mock
    private CurrencyRepository repository;

    @Test
    public void save() {
        // given
        CurrencyDAO dao = getCurrencyDAO();
        // when
        when(repository.save(any(CurrencyDAO.class))).thenReturn(dao);
        CurrencyVO vo = service.save(new CurrencyDAO());
        // then
        verify(repository, atLeastOnce()).save(any(CurrencyDAO.class));
        assertEquals(1L, vo.getId().longValue());
    }

    @Test(expected = RepositoryException.class)
    public void saveThrowException() {
        // given when
        doThrow(RepositoryException.class).when(repository).save(any(CurrencyDAO.class));
        service.save(new CurrencyDAO());
        // then
        verify(repository, atLeastOnce()).save(any(CurrencyDAO.class));
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
        CurrencyDAO dao = getCurrencyDAO();
        // when
        when(repository.findById(anyLong())).thenReturn(Optional.of(dao));
        CurrencyVO vo = service.findById(1L);
        // then
        verify(repository, atLeastOnce()).findById(anyLong());
        assertEquals(dao.getId().longValue(), vo.getId().longValue());
    }

    @Test
    public void findAll() {
        // given
        ArrayList<CurrencyDAO> daoList = new ArrayList<>();
        daoList.add(getCurrencyDAO());
        // when
        when(repository.findAll()).thenReturn(daoList);
        List<CurrencyVO> accountTypes = service.findAll();
        // then
        verify(repository, atLeastOnce()).findAll();
        assertFalse(CollectionUtils.isEmpty(accountTypes));
    }

    @Test
    public void update() {
        // given
        CurrencyDAO dao = getCurrencyDAO();
        // when
        when(repository.findById(anyLong())).thenReturn(Optional.of(dao));
        when(repository.save(any(CurrencyDAO.class))).thenReturn(dao);
        CurrencyVO vo = service.update(dao);
        // then
        verify(repository, atLeastOnce()).findById(anyLong());
        verify(repository, atLeastOnce()).save(any(CurrencyDAO.class));
        assertEquals(dao.getId().longValue(), vo.getId().longValue());
    }

    @Test(expected = RepositoryException.class)
    public void updateThrowException() {
        // given
        CurrencyDAO dao = getCurrencyDAO();
        // when
        doThrow(RepositoryException.class).when(repository).save(any(CurrencyDAO.class));
        when(repository.findById(anyLong())).thenReturn(Optional.of(dao));
        service.update(dao);
        // then
        verify(repository, atLeastOnce()).findById(anyLong());
        verify(repository, atLeastOnce()).save(any(CurrencyDAO.class));
    }

    public CurrencyDAO getCurrencyDAO() {
        CurrencyDAO dao = new CurrencyDAO();
        dao.setId(1L);
        dao.setName("test");
        return dao;
    }

}
