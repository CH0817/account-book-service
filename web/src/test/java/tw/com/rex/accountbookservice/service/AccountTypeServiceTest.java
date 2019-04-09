package tw.com.rex.accountbookservice.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.util.CollectionUtils;
import tw.com.rex.accountbookservice.exception.RepositoryException;
import tw.com.rex.accountbookservice.model.dao.AccountTypeDAO;
import tw.com.rex.accountbookservice.model.vo.AccountTypeVO;
import tw.com.rex.accountbookservice.repository.AccountTypeRepository;
import tw.com.rex.accountbookservice.service.impl.AccountTypeServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountTypeServiceTest {

    @InjectMocks
    private AccountTypeServiceImpl service;
    @Mock
    private AccountTypeRepository repository;

    @Test
    public void save() {
        // given
        AccountTypeDAO dao = getAccountTypeDAO();
        // when
        when(repository.save(any(AccountTypeDAO.class))).thenReturn(dao);
        AccountTypeVO vo = service.save(new AccountTypeDAO());
        // then
        verify(repository, atLeastOnce()).save(any(AccountTypeDAO.class));
        assertEquals(1L, vo.getId().longValue());
    }

    @Test(expected = RepositoryException.class)
    public void saveThrowException() {
        // given when
        doThrow(RepositoryException.class).when(repository).save(any(AccountTypeDAO.class));
        service.save(new AccountTypeDAO());
        // then
        verify(repository, atLeastOnce()).save(any(AccountTypeDAO.class));
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
        AccountTypeDAO dao = getAccountTypeDAO();
        // when
        when(repository.findById(anyLong())).thenReturn(Optional.of(dao));
        AccountTypeVO vo = service.findById(1L);
        // then
        verify(repository, atLeastOnce()).findById(anyLong());
        assertEquals(dao.getId().longValue(), vo.getId().longValue());
    }

    @Test
    public void findAll() {
        // given
        ArrayList<AccountTypeDAO> daoList = new ArrayList<>();
        daoList.add(getAccountTypeDAO());
        // when
        when(repository.findAll()).thenReturn(daoList);
        List<AccountTypeVO> accountTypes = service.findAll();
        // then
        verify(repository, atLeastOnce()).findAll();
        assertFalse(CollectionUtils.isEmpty(accountTypes));
    }

    @Test
    public void update() {
        // given
        AccountTypeDAO dao = getAccountTypeDAO();
        // when
        when(repository.findById(anyLong())).thenReturn(Optional.of(dao));
        when(repository.save(any(AccountTypeDAO.class))).thenReturn(dao);
        AccountTypeVO vo = service.update(dao);
        // then
        verify(repository, atLeastOnce()).findById(anyLong());
        verify(repository, atLeastOnce()).save(any(AccountTypeDAO.class));
        assertEquals(dao.getId().longValue(), vo.getId().longValue());
    }

    @Test(expected = RepositoryException.class)
    public void updateThrowException() {
        // given
        AccountTypeDAO dao = getAccountTypeDAO();
        // when
        doThrow(RepositoryException.class).when(repository).save(any(AccountTypeDAO.class));
        when(repository.findById(anyLong())).thenReturn(Optional.of(dao));
        service.update(dao);
        // then
        verify(repository, atLeastOnce()).findById(anyLong());
        verify(repository, atLeastOnce()).save(any(AccountTypeDAO.class));
    }

    public AccountTypeDAO getAccountTypeDAO() {
        AccountTypeDAO dao = new AccountTypeDAO();
        dao.setId(1L);
        dao.setName("test");
        return dao;
    }

}
