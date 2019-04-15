package tw.com.rex.accountbookservice.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;
import tw.com.rex.accountbookservice.model.dao.AccountTypeDAO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class AccountTypeRepositoryTest {

    @Autowired
    private AccountTypeRepository repository;

    @Test
    public void saveSuccess() {
        AccountTypeDAO entity = new AccountTypeDAO();
        entity.setName("test");
        entity.setCreateDate(LocalDate.now());
        AccountTypeDAO dao = repository.save(entity);
        assertNotNull(dao.getId());
    }

    @Test(expected = RuntimeException.class)
    public void saveDuplicate() {
        AccountTypeDAO entity = new AccountTypeDAO();
        entity.setName("銀行");
        entity.setCreateDate(LocalDate.now());
        repository.save(entity);
    }

    @Test(expected = RuntimeException.class)
    public void saveNotIntegrity() {
        repository.save(new AccountTypeDAO());
    }

    @Test
    public void findById() {
        Optional<AccountTypeDAO> dao = repository.findById(1L);
        assertTrue(dao.isPresent());
    }

    @Test
    public void findByIdNotFound() {
        Optional<AccountTypeDAO> dao = repository.findById(999L);
        assertFalse(dao.isPresent());
    }

    @Test
    public void findAll() {
        List<AccountTypeDAO> all = repository.findAll();
        assertEquals(3, all.size());
    }

    @Test
    public void countById() {
        AccountTypeDAO entity = new AccountTypeDAO();
        entity.setId(1L);
        long count = repository.count(Example.of(entity));
        assertEquals(1L, count);
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Test
    public void updateSuccess() {
        AccountTypeDAO dao = repository.findById(1L).get();
        dao.setName("test");
        dao.setUpdateDate(LocalDate.now());

        AccountTypeDAO result = repository.saveAndFlush(dao);

        assertEquals("test", result.getName());
    }

}