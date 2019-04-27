package tw.com.rex.accountbookservice;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.jdbc.Sql;
import tw.com.rex.accountbookservice.base.BaseControllerTest;
import tw.com.rex.accountbookservice.define.ServerStatusCodeEnum;
import tw.com.rex.accountbookservice.model.dao.AccountDAO;
import tw.com.rex.accountbookservice.model.dao.ItemDAO;
import tw.com.rex.accountbookservice.model.dao.TradeDAO;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@Sql({"/db/data/test/data-account_type.sql", "/db/data/test/data-currency.sql", "/db/data/test/data-category.sql",
      "/db/data/test/data-item.sql", "/db/data/test/data-account.sql", "/db/data/test/data-trade.sql"})
public class TradeControllerTest extends BaseControllerTest {

    private TradeDAO entity;

    @Before
    public void setUp() {
        entity = new TradeDAO();
        entity.setAccount(new AccountDAO(66L));
        entity.setItem(new ItemDAO(66L));
        entity.setCost(new BigDecimal("5000"));
        entity.setTransactDate(LocalDate.now());
        entity.setNote("test");
    }

    @Test
    public void saveSuccess() throws Exception {
        expectOkJsonRequest(mvc.perform(postJsonRequest("/trade/save", entity)))//
                .andExpect(jsonPath("$.data.id").exists())//
                .andExpect(jsonPath("$.data.account.id", is(66)))//
                .andExpect(jsonPath("$.data.item.id", is(66)))//
                .andExpect(jsonPath("$.data.cost", is(5000)))//
                .andExpect(jsonPath("$.data.transactDate", is(today())))//
                .andExpect(jsonPath("$.data.note", is("test")))//
                .andExpect(jsonPath("$.data.createDate", is(today())));
    }

    @Test
    public void saveWithNoAccount() throws Exception {
        entity.setAccount(null);
        expectDatabaseFailJsonRequest(mvc.perform(postJsonRequest("/trade/save", entity)));
    }

    @Test
    public void saveWithNoItem() throws Exception {
        entity.setItem(null);
        expectDatabaseFailJsonRequest(mvc.perform(postJsonRequest("/trade/save", entity)));
    }

    @Test
    public void saveWithNoCost() throws Exception {
        entity.setCost(null);
        expectDatabaseFailJsonRequest(mvc.perform(postJsonRequest("/trade/save", entity)));
    }

    @Test
    public void saveWithNoTransactDate() throws Exception {
        entity.setTransactDate(null);
        expectDatabaseFailJsonRequest(mvc.perform(postJsonRequest("/trade/save", entity)));
    }

    @Test
    public void deleteById() throws Exception {
        expectOkJsonRequest(mvc.perform(delete("/trade/delete/{id}", 66L)))//
                .andExpect(jsonPath("$.code", is(ServerStatusCodeEnum.SUCCESS.getCode())))//
                .andExpect(jsonPath("$.data", is(Boolean.TRUE)));//
    }

    @Test
    public void deleteByIdWithNotFound() throws Exception {
        expectDatabaseFailJsonRequest(mvc.perform(delete("/trade/delete/{id}", 1L)));
    }

    @Test
    public void updateSuccess() throws Exception {
        entity.setId(66L);
        entity.setCost(new BigDecimal("1000"));
        entity.setAccount(new AccountDAO(77L));
        entity.setItem(new ItemDAO(77L));
        entity.setNote(null);
        expectOkJsonRequest(mvc.perform(patchJsonRequest("/trade/update", entity)))//
                .andExpect(jsonPath("$.data.id").exists())//
                .andExpect(jsonPath("$.data.account.id", is(77)))//
                .andExpect(jsonPath("$.data.item.id", is(77)))//
                .andExpect(jsonPath("$.data.cost", is(1000)))//
                .andExpect(jsonPath("$.data.transactDate", is(today())))//
                .andExpect(jsonPath("$.data.note").doesNotExist())//
                .andExpect(jsonPath("$.data.createDate").exists())//
                .andExpect(jsonPath("$.data.updateDate", is(today())));
    }

    @Test
    public void updateWithNoAccount() throws Exception {
        entity.setId(66L);
        entity.setAccount(null);
        expectDatabaseFailJsonRequest(mvc.perform(patchJsonRequest("/trade/update", entity)));
    }

    @Test
    public void updateWithNoItem() throws Exception {
        entity.setId(66L);
        entity.setItem(null);
        expectDatabaseFailJsonRequest(mvc.perform(patchJsonRequest("/trade/update", entity)));
    }

    @Test
    public void updateWithNoCost() throws Exception {
        entity.setId(66L);
        entity.setCost(null);
        expectDatabaseFailJsonRequest(mvc.perform(patchJsonRequest("/trade/update", entity)));
    }

    @Test
    public void updateWithNoTransactDate() throws Exception {
        entity.setId(66L);
        entity.setTransactDate(null);
        expectDatabaseFailJsonRequest(mvc.perform(patchJsonRequest("/trade/update", entity)));
    }

}
