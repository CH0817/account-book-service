package tw.com.rex.accountbookservice.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.test.context.jdbc.Sql;
import tw.com.rex.accountbookservice.model.dao.define.CategoryTypeEnum;
import tw.com.rex.accountbookservice.model.dao.CategoryDAO;
import tw.com.rex.accountbookservice.repository.base.BaseRepositoryTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@Sql("/db/data/test/data-category.sql")
public class CategoryRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private CategoryRepository repository;

    @Test
    public void saveSuccess() {
        CategoryDAO entity = new CategoryDAO();
        entity.setName("test");
        entity.setCategoryType(CategoryTypeEnum.INCOME.getCode());
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
        Optional<CategoryDAO> dao = repository.findById(66L);
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
        assertEquals(2, all.size());
    }

    @Test
    public void countById() {
        CategoryDAO entity = new CategoryDAO();
        entity.setId(66L);
        long count = repository.count(Example.of(entity));
        assertEquals(1L, count);
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Test
    public void updateSuccess() {
        CategoryDAO dao = repository.findById(66L).get();
        dao.setName("test");
        dao.setUpdateDate(LocalDate.now());

        CategoryDAO result = repository.saveAndFlush(dao);

        assertEquals("test", result.getName());
    }

}
