package tw.com.rex.accountbookservice.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import tw.com.rex.accountbookservice.model.dao.CurrencyDAO;
import tw.com.rex.accountbookservice.web.base.BaseControllerTest;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@Sql({"/db/data/test/data-currency.sql"})
public class CurrencyControllerTest extends BaseControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void saveSuccess() throws Exception {
        expectOkJsonRequest(mvc.perform(postJsonRequest("/currency/save", new CurrencyDAO("test"))))//
                .andExpect(jsonPath("$.data.id").exists())//
                .andExpect(jsonPath("$.data.name", is("test")))//
                .andExpect(jsonPath("$.data.createDate", is(today())));

    }

    @Test
    public void saveWithDuplicate() throws Exception {
        expectBadJsonRequest(mvc.perform(postJsonRequest("/currency/save", new CurrencyDAO("新台幣"))));
    }

    @Test
    public void deleteByIdSuccess() throws Exception {
        expectOkJsonRequest(mvc.perform(delete("/currency/delete/{id}", 66L)))//
                .andExpect(jsonPath("$.data", is(true)));
    }

    @Test
    public void deleteByIdWithNotFoundId() throws Exception {
        expectBadJsonRequest(mvc.perform(delete("/currency/delete/{id}", 1L)));
    }

    @Test
    public void updateSuccess() throws Exception {
        CurrencyDAO entity = new CurrencyDAO();
        entity.setId(66L);
        entity.setName("test_update");

        expectOkJsonRequest(mvc.perform(patchJsonRequest("/currency/update", entity)))//
                .andExpect(jsonPath("$.data.id").exists())//
                .andExpect(jsonPath("$.data.name", is(entity.getName())))//
                .andExpect(jsonPath("$.data.createDate").exists())//
                .andExpect(jsonPath("$.data.updateDate", is(today())));
    }

    @Test
    public void updateWithDuplicateName() throws Exception {
        CurrencyDAO entity = new CurrencyDAO();
        entity.setId(66L);
        entity.setName("美元");

        expectBadJsonRequest(mvc.perform(patchJsonRequest("/currency/update", entity)));
    }

    @Test
    public void findById() throws Exception {
        expectOkJsonRequest(mvc.perform(get("/currency/find/{id}", 66L)))//
                .andExpect(jsonPath("$.data.id", is(66)))//
                .andExpect(jsonPath("$.data.name", is("新台幣")));
    }

    @Test
    public void findByIdWithNotFound() throws Exception {
        expectBadJsonRequest(mvc.perform(get("/currency/find/{id}", 1L)));
    }

    @Test
    public void findAll() throws Exception {
        expectOkJsonRequest(mvc.perform(get("/currency/find/all")))//
                .andExpect(jsonPath("$.data", hasSize(2)));
    }

}
