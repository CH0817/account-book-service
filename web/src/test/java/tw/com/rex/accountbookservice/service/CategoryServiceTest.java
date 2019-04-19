package tw.com.rex.accountbookservice.service;

import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.util.CollectionUtils;
import tw.com.rex.accountbookservice.exception.RepositoryException;
import tw.com.rex.accountbookservice.model.dao.CategoryDAO;
import tw.com.rex.accountbookservice.repository.CategoryRepository;
import tw.com.rex.accountbookservice.service.base.BaseServiceTest;
import tw.com.rex.accountbookservice.service.impl.CategoryServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@Ignore
public class CategoryServiceTest extends BaseServiceTest {

    @InjectMocks
    private CategoryServiceImpl service;
    @Mock
    private CategoryRepository repository;

    @Test
    public void save() {
        // given
        CategoryDAO dao = getDAO();
        // when
        when(repository.save(any(CategoryDAO.class))).thenReturn(dao);
        CategoryDAO result = service.save(new CategoryDAO());
        // then
        verify(repository, atLeastOnce()).save(any(CategoryDAO.class));
        assertEquals(dao.getId().longValue(), result.getId().longValue());
    }

    @Test(expected = RepositoryException.class)
    public void saveThrowException() {
        // given when
        doThrow(RepositoryException.class).when(repository).save(any(CategoryDAO.class));
        service.save(new CategoryDAO());
        // then
        verify(repository, atLeastOnce()).save(any(CategoryDAO.class));
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
        CategoryDAO dao = getDAO();
        // when
        when(repository.findById(anyLong())).thenReturn(Optional.of(dao));
        CategoryDAO result = service.findById(1L);
        // then
        verify(repository, atLeastOnce()).findById(anyLong());
        assertEquals(dao.getId().longValue(), result.getId().longValue());
    }

    @Test
    public void findAll() {
        // given
        ArrayList<CategoryDAO> daoList = new ArrayList<>();
        daoList.add(getDAO());
        // when
        when(repository.findAll()).thenReturn(daoList);
        List<CategoryDAO> accountTypes = service.findAll();
        // then
        verify(repository, atLeastOnce()).findAll();
        assertFalse(CollectionUtils.isEmpty(accountTypes));
    }

    @Test
    public void update() {
        // given
        CategoryDAO dao = getDAO();
        // when
        when(repository.findById(anyLong())).thenReturn(Optional.of(dao));
        when(repository.save(any(CategoryDAO.class))).thenReturn(dao);
        CategoryDAO result = service.update(dao);
        // then
        verify(repository, atLeastOnce()).findById(anyLong());
        verify(repository, atLeastOnce()).save(any(CategoryDAO.class));
        assertEquals(dao.getId().longValue(), result.getId().longValue());
    }

    @Test(expected = RepositoryException.class)
    public void updateThrowException() {
        // given
        CategoryDAO dao = getDAO();
        // when
        doThrow(RepositoryException.class).when(repository).save(any(CategoryDAO.class));
        when(repository.findById(anyLong())).thenReturn(Optional.of(dao));
        service.update(dao);
        // then
        verify(repository, atLeastOnce()).findById(anyLong());
        verify(repository, atLeastOnce()).save(any(CategoryDAO.class));
    }

    public CategoryDAO getDAO() {
        CategoryDAO dao = new CategoryDAO();
        dao.setId(1L);
        dao.setName("test");
        return dao;
    }

}
