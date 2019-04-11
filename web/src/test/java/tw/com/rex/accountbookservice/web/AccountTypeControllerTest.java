package tw.com.rex.accountbookservice.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import tw.com.rex.accountbookservice.AccountBookServiceApplication;
import tw.com.rex.accountbookservice.exception.RepositoryException;
import tw.com.rex.accountbookservice.model.dao.AccountTypeDAO;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AccountBookServiceApplication.class)
@AutoConfigureMockMvc
public class AccountTypeControllerTest {

    @Autowired
    private MockMvc mvc;
    private static ObjectMapper mapper;

    @BeforeClass
    public static void init() {
        mapper = getObjectMapper();
    }

    @Test
    public void saveSuccess() throws Exception {
        AccountTypeDAO dao = new AccountTypeDAO("test");

        String daoJson = mapper.writeValueAsString(dao);

        mvc.perform(post("/accountType/save")//
                            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)//
                            .content(daoJson))//
                .andExpect(status().isOk())//
                .andDo(print())//
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))//
                .andExpect(jsonPath("$.id").exists())//
                .andExpect(jsonPath("$.name", Matchers.is(dao.getName())))//
                .andExpect(jsonPath("$.createDate").exists())//
                .andExpect(jsonPath("$.updateDate").doesNotExist());
    }

    @Test
    public void saveStatus500() throws Exception {
        AccountTypeDAO dao = new AccountTypeDAO("銀行");

        String daoJson = mapper.writeValueAsString(dao);

        mvc.perform(post("/accountType/save")//
                            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)//
                            .content(daoJson))//
                .andExpect(status().isInternalServerError())//
                .andDo(print())//
                .andExpect(jsonPath("$").doesNotExist());
    }

    private static ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new ParameterNamesModule())//
                .registerModule(new Jdk8Module())//
                .registerModule(new JavaTimeModule());
        return mapper;
    }

}
