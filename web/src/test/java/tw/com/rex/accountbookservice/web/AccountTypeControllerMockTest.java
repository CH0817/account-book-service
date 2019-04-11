package tw.com.rex.accountbookservice.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import tw.com.rex.accountbookservice.controller.AccountTypeController;
import tw.com.rex.accountbookservice.model.dao.AccountTypeDAO;
import tw.com.rex.accountbookservice.service.AccountTypeService;

import java.time.LocalDate;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * only test web mvc
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = AccountTypeController.class)
public class AccountTypeControllerMockTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private AccountTypeService service;
    private static ObjectMapper mapper;

    @BeforeClass
    public static void init() {
        mapper = getObjectMapper();
    }

    @Test
    public void save() throws Exception {
        AccountTypeDAO dao = new AccountTypeDAO();
        dao.setName("test");
        dao.setCreateDate(LocalDate.now());

        AccountTypeDAO result = new AccountTypeDAO();
        BeanUtils.copyProperties(dao, result);
        result.setId(1L);
        result.setCreateDate(LocalDate.now());

        when(service.save(dao)).thenReturn(result);

        String daoJson = mapper.writeValueAsString(dao);
        System.out.println(daoJson);

        mvc.perform(post("/accountType/save")//
                            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)//
                            .content(daoJson))//
                .andExpect(status().isOk())//
                .andDo(print())//
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))//
                .andExpect(jsonPath("$.id").exists())//
                .andExpect(jsonPath("$.name", Matchers.is(result.getName())))//
                .andExpect(jsonPath("$.createDate").exists())//
                .andExpect(jsonPath("$.updateDate").doesNotExist());
    }

    private static ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new ParameterNamesModule())//
                .registerModule(new Jdk8Module())//
                .registerModule(new JavaTimeModule());
        return mapper;
    }

}
