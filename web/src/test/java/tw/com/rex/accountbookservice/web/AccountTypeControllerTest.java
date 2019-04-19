package tw.com.rex.accountbookservice.web;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import tw.com.rex.accountbookservice.model.dao.AccountTypeDAO;
import tw.com.rex.accountbookservice.web.base.BaseControllerTest;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * only test web mvc
 */
// FIXME 重寫測試
@Sql({"/db/data/test/data-account_type.sql"})
public class AccountTypeControllerTest extends BaseControllerTest {

    @Test
    public void saveWithSuccess() throws Exception {
        mvc.perform(post("/accountType/save")//
                            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)//
                            .content(mapper.writeValueAsString(new AccountTypeDAO("test_save"))))//
                .andExpect(status().isOk())//
                .andDo(print())//
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))//
                .andExpect(jsonPath("$.id").exists())//
                .andExpect(jsonPath("$.name", is("test_save")))//
                .andExpect(jsonPath("$.createDate").exists());
    }

    @Test
    public void saveWithDuplicateName() throws Exception {
        mvc.perform(post("/accountType/save")//
                            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)//
                            .content(mapper.writeValueAsString(new AccountTypeDAO("銀行"))))//
                .andExpect(status().isInternalServerError())//
                .andDo(print())//
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    public void deleteByIdWithSuccess() throws Exception {
        mvc.perform(delete("/accountType/delete/{id}", 66L))//
                .andExpect(status().isOk())//
                .andDo(print())//
                .andExpect(jsonPath("$", is(true)));
    }

    @Test
    public void deleteByIdWithNotFoundId() throws Exception {
        mvc.perform(delete("/accountType/delete/{id}", 1L))//
                .andExpect(status().isInternalServerError())//
                .andDo(print())//
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    public void updateWithSuccess() throws Exception {
        AccountTypeDAO entity = new AccountTypeDAO();
        entity.setId(66L);
        entity.setName("test");

        mvc.perform(patch("/accountType/update")//
                            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)//
                            .content(mapper.writeValueAsString(entity)))//
                .andExpect(status().isOk())//
                .andDo(print())//
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))//
                .andExpect(jsonPath("$.id", is(entity.getId().intValue())))//
                .andExpect(jsonPath("$.name", is(entity.getName())))//
                .andExpect(jsonPath("$.createDate").exists())//
                .andExpect(jsonPath("$.updateDate").exists());
    }

    @Test
    public void updateWithDuplicateName() throws Exception {
        AccountTypeDAO entity = new AccountTypeDAO();
        entity.setId(1L);
        entity.setName("銀行");

        mvc.perform(patch("/accountType/update")//
                            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)//
                            .content(mapper.writeValueAsString(entity)))//
                .andExpect(status().isInternalServerError())//
                .andDo(print());
    }

    @Test
    public void findById() throws Exception {
        mvc.perform(get("/accountType/find/{id}", 66L))//
                .andExpect(status().isOk())//
                .andDo(print())//
                .andExpect(jsonPath("$.id", is(66)))//
                .andExpect(jsonPath("$.name", is("銀行")))//
                .andExpect(jsonPath("$.createDate").exists());
    }

    @Test
    public void findByIdNotFound() throws Exception {
        mvc.perform(get("/accountType/find/{id}", 1L))//
                .andExpect(status().isInternalServerError())//
                .andDo(print());
    }

    @Test
    public void findAll() throws Exception {
        mvc.perform(get("/accountType/find/all"))//
                .andExpect(status().isOk())//
                .andDo(print())//
                .andExpect(jsonPath("$", hasSize(3)));
    }

}
