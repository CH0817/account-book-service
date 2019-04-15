package tw.com.rex.accountbookservice.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import tw.com.rex.accountbookservice.model.dao.CategoryDAO;
import tw.com.rex.accountbookservice.repository.base.BaseRepositoryTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class CategoryRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private CategoryRepository repository;

    @Test
    public void saveSuccess() {
        CategoryDAO entity = new CategoryDAO();
        entity.setName("test");
        entity.setCreateDate(LocalDate.now());
        CategoryDAO dao = repository.save(entity);
        assertNotNull(dao.getId());
    }

    @Test(expected = RuntimeException.class)
    public void saveDuplicate() {
        CategoryDAO entity = new CategoryDAO();
        entity.setName("銀行");
        entity.setCreateDate(LocalDate.now());
        repository.save(entity);
    }

    @Test(expected = RuntimeException.class)
    public void saveNotIntegrity() {
        repository.save(new CategoryDAO());
    }

    @Test
    public void findById() {
        Optional<CategoryDAO> dao = repository.findById(1L);
        assertTrue(dao.isPresent());
    }

    @Test
    public void findByIdNotFound() {
        Optional<CategoryDAO> dao = repository.findById(999L);
        assertFalse(dao.isPresent());
    }

    @Test
    public void findAll() {
        List<CategoryDAO> all = repository.findAll();
        assertEquals(3, all.size());
    }

    @Test
    public void countById() {
        CategoryDAO entity = new CategoryDAO();
        entity.setId(1L);
        long count = repository.count(Example.of(entity));
        assertEquals(1L, count);
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Test
    public void updateSuccess() {
        CategoryDAO dao = repository.findById(1L).get();
        dao.setName("test");
        dao.setUpdateDate(LocalDate.now());

        CategoryDAO result = repository.saveAndFlush(dao);

        assertEquals("test", result.getName());
    }
    
}
