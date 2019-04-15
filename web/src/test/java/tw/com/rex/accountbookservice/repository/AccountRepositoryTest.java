package tw.com.rex.accountbookservice.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.test.context.jdbc.Sql;
import tw.com.rex.accountbookservice.model.dao.AccountDAO;
import tw.com.rex.accountbookservice.model.dao.AccountTypeDAO;
import tw.com.rex.accountbookservice.model.dao.CurrencyDAO;
import tw.com.rex.accountbookservice.repository.base.BaseRepositoryTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class AccountRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private AccountRepository repository;

    @Test
    public void saveSuccess() {
        AccountDAO entity = getSaveEntity();
        AccountDAO dao = repository.save(entity);
        assertNotNull(dao.getId());
    }

    @Test(expected = RuntimeException.class)
    public void saveAccountTypeNotFound() {
        AccountDAO entity = getSaveEntity();
        entity.getAccountType().setId(999L);
        repository.save(entity);
    }

    @Test(expected = RuntimeException.class)
    public void saveCurrencyNotFound() {
        AccountDAO entity = getSaveEntity();
        entity.getCurrency().setId(999L);
        repository.save(entity);
    }

    public AccountDAO getSaveEntity() {
        AccountDAO entity = new AccountDAO();
        entity.setName("test");
        entity.setAccountType(getAccountTypeDAO());
        entity.setCurrency(getCurrencyDAO());
        entity.setCreateDate(LocalDate.now());
        return entity;
    }

    public AccountTypeDAO getAccountTypeDAO() {
        AccountTypeDAO accountTypeDAO = new AccountTypeDAO();
        accountTypeDAO.setId(1L);
        return accountTypeDAO;
    }

    public CurrencyDAO getCurrencyDAO() {
        CurrencyDAO currencyDAO = new CurrencyDAO();
        currencyDAO.setId(1L);
        return currencyDAO;
    }

    @Test
    @Sql({"/db/data/data-account.sql"})
    public void findById() {
        Optional<AccountDAO> dao = repository.findById(66L);
        assertTrue(dao.isPresent());
    }

    @Test
    public void findByIdNotFound() {
        Optional<AccountDAO> dao = repository.findById(999L);
        assertFalse(dao.isPresent());
    }

    @Test
    @Sql({"/db/data/data-account.sql"})
    public void findAll() {
        List<AccountDAO> all = repository.findAll();
        assertEquals(1, all.size());
    }

    @Test
    @Sql({"/db/data/data-account.sql"})
    public void countById() {
        AccountDAO entity = new AccountDAO();
        entity.setId(66L);
        long count = repository.count(Example.of(entity));
        assertEquals(1L, count);
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Test
    @Sql({"/db/data/data-account.sql"})
    public void updateSuccess() {
        AccountDAO dao = repository.findById(66L).get();
        dao.setName("test");
        dao.setUpdateDate(LocalDate.now());

        AccountDAO result = repository.saveAndFlush(dao);

        assertEquals("test", result.getName());
    }

}
