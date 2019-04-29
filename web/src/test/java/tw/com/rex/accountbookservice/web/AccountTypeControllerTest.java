package tw.com.rex.accountbookservice.web;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import tw.com.rex.accountbookservice.dao.AccountTypeDAO;
import tw.com.rex.accountbookservice.web.base.BaseControllerTest;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@Sql({"/db/data/test/data-account_type.sql"})
public class AccountTypeControllerTest extends BaseControllerTest {

    @Test
    public void saveWithSuccess() throws Exception {
        expectOkJsonRequest(
                mvc.perform(postJsonRequest("/accountType/save", new AccountTypeDAO("test_save"))))//
                .andExpect(jsonPath("$.code", is(1)))//
                .andExpect(jsonPath("$.data.id").exists())//
                .andExpect(jsonPath("$.data.name", is("test_save")))//
                .andExpect(jsonPath("$.data.createDate", is(today())));
    }

    @Test
    public void saveWithDuplicateName() throws Exception {
        expectDuplicateFailJsonRequest(mvc.perform(postJsonRequest("/accountType/save", new AccountTypeDAO("銀行"))));
    }

    @Test
    public void deleteByIdWithSuccess() throws Exception {
        expectOkJsonRequest(mvc.perform(delete("/accountType/delete/{id}", 66L)))//
                .andExpect(jsonPath("$.data", is(true)));
    }

    @Test
    public void deleteByIdWithNotFoundId() throws Exception {
        expectDatabaseFailJsonRequest(mvc.perform(delete("/accountType/delete/{id}", 1L)));
    }

    @Test
    public void updateWithSuccess() throws Exception {
        AccountTypeDAO entity = new AccountTypeDAO();
        entity.setId(66L);
        entity.setName("test");

        expectOkJsonRequest(mvc.perform(patch("/accountType/update")//
                                                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)//
                                                .content(mapper.writeValueAsString(entity))))//
                .andExpect(jsonPath("$.data.id", is(entity.getId().intValue())))//
                .andExpect(jsonPath("$.data.name", is(entity.getName())))//
                .andExpect(jsonPath("$.data.createDate").exists())//
                .andExpect(jsonPath("$.data.updateDate", is(today())));
    }

    @Test
    public void updateWithDuplicateName() throws Exception {
        AccountTypeDAO entity = new AccountTypeDAO();
        entity.setId(77L);
        entity.setName("銀行");
        expectDuplicateFailJsonRequest(mvc.perform(patch("/accountType/update")//
                                                 .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)//
                                                 .content(mapper.writeValueAsString(entity))));
    }

    @Test
    public void findById() throws Exception {
        expectOkJsonRequest(mvc.perform(get("/accountType/find/{id}", 66L)))//
                .andExpect(jsonPath("$.data.id", is(66)))//
                .andExpect(jsonPath("$.data.name", is("銀行")))//
                .andExpect(jsonPath("$.data.createDate").exists());
    }

    @Test
    public void findByIdNotFound() throws Exception {
        expectDatabaseFailJsonRequest(mvc.perform(get("/accountType/find/{id}", 1L))//
                                     .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)));
    }

    @Test
    public void findAll() throws Exception {
        expectOkJsonRequest(mvc.perform(get("/accountType/find/all")))//
                .andDo(print())//
                .andExpect(jsonPath("$.data", hasSize(3)));
    }

}
