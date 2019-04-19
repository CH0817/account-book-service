package tw.com.rex.accountbookservice.repository;

import org.junit.Ignore;
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

@Ignore
@Sql({"/db/data/test/data-account_type.sql", "/db/data/test/data-currency.sql", "/db/data/test/data-account.sql"})
public class AccountRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private AccountRepository repository;
    @Autowired
    private AccountTypeRepository accountTypeRepository;
    @Autowired
    private CurrencyRepository currencyRepository;

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
        entity.setName("XD");
        entity.setAccountType(getAccountTypeDAO());
        entity.setCurrency(getCurrencyDAO());
        entity.setCreateDate(LocalDate.now());
        return entity;
    }

    public AccountTypeDAO getAccountTypeDAO() {
        AccountTypeDAO accountTypeDAO = new AccountTypeDAO();
        accountTypeDAO.setId(66L);
        return accountTypeDAO;
    }

    public CurrencyDAO getCurrencyDAO() {
        CurrencyDAO currencyDAO = new CurrencyDAO();
        currencyDAO.setId(66L);
        return currencyDAO;
    }

    @Test
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
    public void findAll() {
        List<AccountDAO> all = repository.findAll();
        assertEquals(1, all.size());
    }

    @Test
    public void countById() {
        AccountDAO entity = new AccountDAO();
        entity.setId(66L);
        long count = repository.count(Example.of(entity));
        assertEquals(1L, count);
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Test
    public void updateSuccess() {
        AccountDAO dao = repository.findById(66L).get();
        dao.setName("test");
        dao.setUpdateDate(LocalDate.now());

        AccountDAO result = repository.saveAndFlush(dao);

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
    public void deleteFromAccountType() {
        accountTypeRepository.deleteById(66L);
        Optional<AccountDAO> dao = repository.findById(66L);
        assertFalse(dao.isPresent());
    }

    @Test
    public void deleteFromCurrency() {
        currencyRepository.deleteById(66L);
        Optional<AccountDAO> dao = repository.findById(66L);
        assertFalse(dao.isPresent());
    }

}
