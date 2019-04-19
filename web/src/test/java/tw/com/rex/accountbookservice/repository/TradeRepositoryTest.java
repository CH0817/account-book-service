package tw.com.rex.accountbookservice.repository;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.test.context.jdbc.Sql;
import tw.com.rex.accountbookservice.model.dao.AccountDAO;
import tw.com.rex.accountbookservice.model.dao.ItemDAO;
import tw.com.rex.accountbookservice.model.dao.TradeDAO;
import tw.com.rex.accountbookservice.repository.base.BaseRepositoryTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@Ignore
@Sql({"/db/data/test/data-account_type.sql", "/db/data/test/data-currency.sql", "/db/data/test/data-category.sql",
      "/db/data/test/data-item.sql", "/db/data/test/data-account.sql", "/db/data/test/data-trade.sql"})
public class TradeRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private TradeRepository repository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void saveSuccess() {
        TradeDAO dao = repository.save(getTradeDAO());
        assertNotNull(dao.getId());
    }

    @Test(expected = RuntimeException.class)
    public void saveAccountNotFound() {
        TradeDAO entity = getTradeDAO();
        entity.getAccount().setId(999L);
        repository.save(entity);
    }

    @Test(expected = RuntimeException.class)
    public void saveItemNotFound() {
        TradeDAO entity = getTradeDAO();
        entity.getItem().setId(999L);
        repository.save(entity);
    }

    private TradeDAO getTradeDAO() {
        TradeDAO entity = new TradeDAO();
        entity.setAccount(getAccountDAO());
        entity.setItem(getItemDAO());
        entity.setTransactDate(LocalDate.now());
        entity.setCost(new BigDecimal("100"));
        entity.setCreateDate(LocalDate.now());
        return entity;
    }

    private AccountDAO getAccountDAO() {
        AccountDAO dao = new AccountDAO();
        dao.setId(66L);
        return dao;
    }

    private ItemDAO getItemDAO() {
        ItemDAO itemDAO = new ItemDAO();
        itemDAO.setId(66L);
        return itemDAO;
    }

    @Test(expected = RuntimeException.class)
    public void saveNotIntegrity() {
        repository.save(new TradeDAO());
    }

    @Test
    public void findById() {
        Optional<TradeDAO> dao = repository.findById(66L);
        assertTrue(dao.isPresent());
    }

    @Test
    public void findByIdNotFound() {
        Optional<TradeDAO> dao = repository.findById(999L);
        assertFalse(dao.isPresent());
    }

    @Test
    public void findAll() {
        List<TradeDAO> all = repository.findAll();
        assertEquals(1, all.size());
    }

    @Test
    public void countById() {
        TradeDAO entity = new TradeDAO();
        entity.setId(66L);
        assertEquals(1L, repository.count(Example.of(entity)));
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Test
    public void updateSuccess() {
        TradeDAO dao = repository.findById(66L).get();
        dao.setCost(new BigDecimal("36"));
        dao.setUpdateDate(LocalDate.now());

        TradeDAO result = repository.saveAndFlush(dao);

        assertEquals(new BigDecimal("36"), result.getCost());
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
    public void deleteFromAccount() {
        accountRepository.deleteById(getAccountDAO().getId());
        Optional<TradeDAO> dao = repository.findById(66L);
        assertFalse(dao.isPresent());
    }

    @Test
    public void deleteFromItem() {
        itemRepository.deleteById(getItemDAO().getId());
        Optional<TradeDAO> dao = repository.findById(66L);
        assertFalse(dao.isPresent());
    }

}
