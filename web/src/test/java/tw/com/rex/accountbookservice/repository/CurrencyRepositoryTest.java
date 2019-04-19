package tw.com.rex.accountbookservice.repository;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.test.context.jdbc.Sql;
import tw.com.rex.accountbookservice.model.dao.CurrencyDAO;
import tw.com.rex.accountbookservice.repository.base.BaseRepositoryTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@Ignore
@Sql("/db/data/test/data-currency.sql")
public class CurrencyRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private CurrencyRepository repository;

    @Test
    public void saveSuccess() {
        CurrencyDAO entity = new CurrencyDAO();
        entity.setName("test");
        entity.setCreateDate(LocalDate.now());
        CurrencyDAO dao = repository.save(entity);
        assertNotNull(dao.getId());
    }

    @Test(expected = RuntimeException.class)
    public void saveDuplicate() {
        CurrencyDAO entity = new CurrencyDAO();
        entity.setName("新台幣");
        entity.setCreateDate(LocalDate.now());
        repository.save(entity);
    }

    @Test(expected = RuntimeException.class)
    public void saveNotIntegrity() {
        repository.save(new CurrencyDAO());
    }

    @Test
    public void findById() {
        Optional<CurrencyDAO> dao = repository.findById(66L);
        assertTrue(dao.isPresent());
    }

    @Test
    public void findByIdNotFound() {
        Optional<CurrencyDAO> dao = repository.findById(999L);
        assertFalse(dao.isPresent());
    }

    @Test
    public void findAll() {
        List<CurrencyDAO> all = repository.findAll();
        assertEquals(2, all.size());
    }

    @Test
    public void countById() {
        CurrencyDAO entity = new CurrencyDAO();
        entity.setId(66L);
        long count = repository.count(Example.of(entity));
        assertEquals(1L, count);
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Test
    public void updateSuccess() {
        CurrencyDAO dao = repository.findById(66L).get();
        dao.setName("test");
        dao.setUpdateDate(LocalDate.now());

        CurrencyDAO result = repository.saveAndFlush(dao);

        assertEquals("test", result.getName());
    }

}
