package tw.com.rex.accountbookservice.web;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.jdbc.Sql;
import tw.com.rex.accountbookservice.model.dao.AccountDAO;
import tw.com.rex.accountbookservice.model.dao.AccountTypeDAO;
import tw.com.rex.accountbookservice.model.dao.CurrencyDAO;
import tw.com.rex.accountbookservice.model.define.ServerStatusCodeEnum;
import tw.com.rex.accountbookservice.web.base.BaseControllerTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Sql({"/db/data/test/data-currency.sql", "/db/data/test/data-account_type.sql", "/db/data/test/data-account.sql"})
public class AccountControllerTest extends BaseControllerTest {

    private AccountDAO entity;

    @Before
    public void setUp() {
        entity = new AccountDAO();
        entity.setName("test");
        entity.setCurrency(new CurrencyDAO(66L));
        entity.setAccountType(new AccountTypeDAO(66L));
        entity.setCurrentMoney(new BigDecimal("100"));
        entity.setInitMoney(new BigDecimal("10"));
        entity.setClosingDate(LocalDate.now());
        entity.setPaymentDueDate(LocalDate.now());
    }

    @Test
    public void saveSuccess() throws Exception {
        expectOkJsonRequest(mvc.perform(postJsonRequest("/account/save", entity)))//
                .andExpect(jsonPath("$.data.id").exists())//
                .andExpect(jsonPath("$.data.name", is("test")))//
                .andExpect(jsonPath("$.data.currency.id", is(66)))//
                .andExpect(jsonPath("$.data.accountType.id", is(66)))//
                .andExpect(jsonPath("$.data.currentMoney", is(100)))//
                .andExpect(jsonPath("$.data.initMoney", is(10)))//
                .andExpect(jsonPath("$.data.closingDate", is(today())))//
                .andExpect(jsonPath("$.data.paymentDueDate", is(today())))//
                .andExpect(jsonPath("$.data.createDate", is(today())));
    }

    @Test
    public void saveWithDuplicate() throws Exception {
        entity.setName("test_1");
        expectDuplicateFailJsonRequest(mvc.perform(postJsonRequest("/account/save", entity)));
    }

    @Test
    public void saveWithNoName() throws Exception {
        entity.setName(null);
        expectDatabaseFailJsonRequest(mvc.perform(postJsonRequest("/account/save", entity)));
    }

    @Test
    public void saveWithNoAccountType() throws Exception {
        entity.setAccountType(null);
        expectDatabaseFailJsonRequest(mvc.perform(postJsonRequest("/account/save", entity)));
    }

    @Test
    public void saveWithNoCurrency() throws Exception {
        entity.setCurrency(null);
        expectDatabaseFailJsonRequest(mvc.perform(postJsonRequest("/account/save", entity)));
    }

    @Test
    public void findAll() throws Exception {
        expectOkJsonRequest(mvc.perform(get("/account/find/all")))//
                .andDo(print())//
                .andExpect(jsonPath("$.data", hasSize(3)));
    }

    @Test
    public void findById() throws Exception {
        expectOkJsonRequest(mvc.perform(get("/account/find/{id}", 66L)))//
                .andExpect(status().isOk())//
                .andDo(print())//
                .andExpect(jsonPath("$.code", is(ServerStatusCodeEnum.SUCCESS.getCode())))//
                .andExpect(jsonPath("$.data.id", is(66)))//
                .andExpect(jsonPath("$.data.name", is("test_1")))//
                .andExpect(jsonPath("$.data.accountType.id", is(66)))//
                .andExpect(jsonPath("$.data.currency.id", is(66)))//
                .andExpect(jsonPath("$.data.createDate", is(today())));
    }

    @Test
    public void findByIdWithNotFound() throws Exception {
        expectDatabaseFailJsonRequest(mvc.perform(get("/account/find/{id}", 1L)));
    }

    @Test
    public void updateSuccess() throws Exception {
        entity.setId(66L);
        entity.setAccountType(new AccountTypeDAO(77L));
        entity.setCurrency(new CurrencyDAO(77L));

        expectOkJsonRequest(mvc.perform(patchJsonRequest("/account/update", entity)))//
                .andExpect(jsonPath("$.data.id", is(66)))//
                .andExpect(jsonPath("$.data.name", is("test")))//
                .andExpect(jsonPath("$.data.currency.id", is(77)))//
                .andExpect(jsonPath("$.data.accountType.id", is(77)))//
                .andExpect(jsonPath("$.data.currentMoney", is(100)))//
                .andExpect(jsonPath("$.data.initMoney", is(10)))//
                .andExpect(jsonPath("$.data.closingDate", is(today())))//
                .andExpect(jsonPath("$.data.paymentDueDate", is(today())))//
                .andExpect(jsonPath("$.data.createDate", is(today())));
    }

    @Test
    public void updateWithDuplicate() throws Exception {
        entity.setId(66L);
        entity.setName("test_2");
        expectDuplicateFailJsonRequest(mvc.perform(patchJsonRequest("/account/update", entity)));
    }

    @Test
    public void updateWithNoName() throws Exception {
        entity.setId(66L);
        entity.setName(null);
        expectDatabaseFailJsonRequest(mvc.perform(patchJsonRequest("/account/update", entity)));
    }

    @Test
    public void updateWithNoAccountType() throws Exception {
        entity.setId(66L);
        entity.setAccountType(null);
        expectDatabaseFailJsonRequest(mvc.perform(patchJsonRequest("/account/update", entity)));
    }

    @Test
    public void updateWithNoCurrency() throws Exception {
        entity.setId(66L);
        entity.setCurrency(null);
        expectDatabaseFailJsonRequest(mvc.perform(patchJsonRequest("/account/update", entity)));
    }

    @Test
    public void deleteByIdSuccess() throws Exception {
        expectOkJsonRequest(mvc.perform(delete("/account/delete/{id}", 66L)))//
                .andExpect(jsonPath("$.data", is(true)));
    }

    @Test
    public void deleteByIdWithNotFoundId() throws Exception {
        expectDatabaseFailJsonRequest(mvc.perform(delete("/account/delete/{id}", 1L)));
    }

}
