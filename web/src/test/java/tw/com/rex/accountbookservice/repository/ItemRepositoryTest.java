package tw.com.rex.accountbookservice.repository;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.test.context.jdbc.Sql;
import tw.com.rex.accountbookservice.model.dao.CategoryDAO;
import tw.com.rex.accountbookservice.model.dao.ItemDAO;
import tw.com.rex.accountbookservice.repository.base.BaseRepositoryTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@Ignore
@Sql({"/db/data/test/data-category.sql", "/db/data/test/data-item.sql"})
public class ItemRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private ItemRepository repository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void saveSuccess() {
        ItemDAO dao = repository.save(getItemDAO());
        assertNotNull(dao.getId());
    }

    @Test(expected = RuntimeException.class)
    public void saveCategoryNotFound() {
        ItemDAO entity = getItemDAO();
        entity.getCategory().setId(999L);
        repository.save(entity);
    }

    public ItemDAO getItemDAO() {
        ItemDAO entity = new ItemDAO();
        entity.setName("test");
        entity.setCategory(getCategoryDAO());
        entity.setCreateDate(LocalDate.now());
        return entity;
    }

    private CategoryDAO getCategoryDAO() {
        CategoryDAO categoryDAO = new CategoryDAO();
        categoryDAO.setId(66L);
        return categoryDAO;
    }

    @Test(expected = RuntimeException.class)
    public void saveNotIntegrity() {
        repository.save(new ItemDAO());
    }

    @Test
    public void findById() {
        Optional<ItemDAO> dao = repository.findById(66L);
        assertTrue(dao.isPresent());
    }

    @Test
    public void findByIdNotFound() {
        Optional<ItemDAO> dao = repository.findById(999L);
        assertFalse(dao.isPresent());
    }

    @Test
    public void findAll() {
        List<ItemDAO> all = repository.findAll();
        assertEquals(5, all.size());
    }

    @Test
    public void countById() {
        ItemDAO entity = new ItemDAO();
        entity.setId(44L);
        long count = repository.count(Example.of(entity));
        assertEquals(1L, count);
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Test
    public void updateSuccess() {
        ItemDAO dao = repository.findById(44L).get();
        dao.setName("test");
        dao.setUpdateDate(LocalDate.now());

        ItemDAO result = repository.saveAndFlush(dao);

        assertEquals("test", result.getName());
    }

    @Test
    public void deleteByIdSuccess() {
        repository.deleteById(66L);
    }

    @Test(expected = RuntimeException.class)
    public void deleteByIdWithIdNotFound() {
        repository.deleteById(111L);
    }

    @Test
    public void deleteFromCategory() {
        categoryRepository.deleteById(66L);
        Optional<ItemDAO> dao = repository.findById(44L);
        assertFalse(dao.isPresent());
    }

}
