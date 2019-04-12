package tw.com.rex.accountbookservice.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
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
import tw.com.rex.accountbookservice.exception.RepositoryException;
import tw.com.rex.accountbookservice.model.dao.AccountTypeDAO;
import tw.com.rex.accountbookservice.service.AccountTypeService;

import java.time.LocalDate;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
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
    public void saveSuccess() throws Exception {
        AccountTypeDAO dao = new AccountTypeDAO("test");

        AccountTypeDAO result = new AccountTypeDAO();
        BeanUtils.copyProperties(dao, result);
        result.setId(1L);
        result.setCreateDate(LocalDate.now());

        when(service.save(dao)).thenReturn(result);

        mvc.perform(post("/accountType/save")//
                            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)//
                            .content(mapper.writeValueAsString(dao)))//
                .andExpect(status().isOk())//
                .andDo(print())//
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))//
                .andExpect(jsonPath("$.id").exists())//
                .andExpect(jsonPath("$.name", is(result.getName())))//
                .andExpect(jsonPath("$.createDate").exists())//
                .andExpect(jsonPath("$.updateDate").doesNotExist());

        verify(service, atLeastOnce()).save(dao);
    }

    @Test
    public void saveStatus500() throws Exception {
        AccountTypeDAO dao = new AccountTypeDAO("test");

        AccountTypeDAO result = new AccountTypeDAO();
        BeanUtils.copyProperties(dao, result);
        result.setId(1L);
        result.setCreateDate(LocalDate.now());

        when(service.save(dao)).thenThrow(RepositoryException.class);

        mvc.perform(post("/accountType/save")//
                            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)//
                            .content(mapper.writeValueAsString(dao)))//
                .andExpect(status().isInternalServerError())//
                .andDo(print())//
                .andExpect(jsonPath("$").doesNotExist());

        verify(service, atLeastOnce()).save(dao);
    }

    @Test
    public void deleteByIdSuccess() throws Exception {
        when(service.deleteById(anyLong())).thenReturn(true);

        mvc.perform(post("/accountType/delete/{id}", 1L))//
                .andExpect(status().isOk())//
                .andDo(print())//
                .andExpect(jsonPath("$", is(true)));

        verify(service, atLeastOnce()).deleteById(anyLong());
    }

    @Test
    public void deleteByIdStatus500() throws Exception {
        when(service.deleteById(anyLong())).thenThrow(RepositoryException.class);

        mvc.perform(post("/accountType/delete/{id}", 1L))//
                .andExpect(status().isInternalServerError())//
                .andDo(print())//
                .andExpect(jsonPath("$").doesNotExist());

        verify(service, atLeastOnce()).deleteById(anyLong());
    }

    private static ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new ParameterNamesModule())//
                .registerModule(new Jdk8Module())//
                .registerModule(new JavaTimeModule());
        return mapper;
    }

}
