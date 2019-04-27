package tw.com.rex.accountbookservice.base;

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
import tw.com.rex.accountbookservice.define.ServerStatusCodeEnum;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
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

    protected MockHttpServletRequestBuilder postJsonRequest(String uri, Object content) throws JsonProcessingException {
        return setJsonContent(setJsonContentType(post(uri)), content);
    }

    protected MockHttpServletRequestBuilder patchJsonRequest(String uri, Object content)
            throws JsonProcessingException {
        return setJsonContent(setJsonContentType(patch(uri)), content);
    }

    private MockHttpServletRequestBuilder setJsonContentType(MockHttpServletRequestBuilder builder) {
        return builder.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
    }

    private MockHttpServletRequestBuilder setJsonContent(MockHttpServletRequestBuilder builder, Object content)
            throws JsonProcessingException {
        return builder.content(mapper.writeValueAsString(content));
    }

    protected ResultActions expectOkJsonRequest(ResultActions resultActions) throws Exception {
        return resultActions.andExpect(status().isOk())//
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))//
                .andExpect(jsonPath("$.code", is(ServerStatusCodeEnum.SUCCESS.getCode())));
    }

    protected void expectDatabaseFailJsonRequest(ResultActions resultActions) throws Exception {
        expectBadJsonRequest(resultActions, ServerStatusCodeEnum.DATABASE_FAIL);
    }

    protected void expectDuplicateFailJsonRequest(ResultActions resultActions) throws Exception {
        expectBadJsonRequest(resultActions, ServerStatusCodeEnum.DUPLICATE);
    }

    private void expectBadJsonRequest(ResultActions resultActions, ServerStatusCodeEnum statusCodeEnum)
            throws Exception {
        resultActions.andExpect(status().isBadRequest())//
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))//
                .andExpect(jsonPath("$.code", is(statusCodeEnum.getCode())))//
                .andExpect(jsonPath("$.errorMessage").exists());
    }

    protected String today() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return formatter.format(LocalDate.now());
    }

}
