package tw.com.rex.accountbookservice.web.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import tw.com.rex.accountbookservice.AccountBookServiceApplication;
import tw.com.rex.accountbookservice.model.dao.define.ServerStatusCodeEnum;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AccountBookServiceApplication.class)
@AutoConfigureMockMvc
@Transactional
public class BaseControllerTest {

    protected static ObjectMapper mapper;
    @Autowired
    protected MockMvc mvc;

    @BeforeClass
    public static void init() {
        mapper = getObjectMapper();
    }

    private static ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new ParameterNamesModule())//
                .registerModule(new Jdk8Module())//
                .registerModule(new JavaTimeModule());
        return mapper;
    }

    protected MockHttpServletRequestBuilder getPostJsonRequestBuilder(String uri, Object content)
            throws JsonProcessingException {
        return post(uri)//
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)//
                .content(mapper.writeValueAsString(content));
    }

    protected ResultActions expectOkJsonRequest(ResultActions resultActions) throws Exception {
        return resultActions.andExpect(status().isOk())//
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))//
                .andExpect(jsonPath("$.code", is(ServerStatusCodeEnum.SUCCESS.getCode())));
    }

    protected ResultActions expectBadJsonRequest(ResultActions resultActions) throws Exception {
        return resultActions.andExpect(status().isBadRequest())//
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))//
                .andExpect(jsonPath("$.code", is(ServerStatusCodeEnum.DATABASE_FAIL.getCode())))//
                .andExpect(jsonPath("$.errorMessage").exists());
    }

}
