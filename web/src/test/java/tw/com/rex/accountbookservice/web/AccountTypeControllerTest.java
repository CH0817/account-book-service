package tw.com.rex.accountbookservice.web;

import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tw.com.rex.accountbookservice.controller.AccountTypeController;
import tw.com.rex.accountbookservice.exception.RepositoryException;
import tw.com.rex.accountbookservice.model.dao.AccountTypeDAO;
import tw.com.rex.accountbookservice.service.AccountTypeService;
import tw.com.rex.accountbookservice.web.base.BaseControllerTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * only test web mvc
 */
@WebMvcTest(controllers = AccountTypeController.class)
public class AccountTypeControllerTest extends BaseControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private AccountTypeService service;

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

        mvc.perform(delete("/accountType/delete/{id}", 1L))//
                .andExpect(status().isOk())//
                .andDo(print())//
                .andExpect(jsonPath("$", is(true)));

        verify(service, atLeastOnce()).deleteById(anyLong());
    }

    @Test
    public void deleteByIdStatus500() throws Exception {
        when(service.deleteById(anyLong())).thenThrow(RepositoryException.class);

        mvc.perform(delete("/accountType/delete/{id}", 1L))//
                .andExpect(status().isInternalServerError())//
                .andDo(print())//
                .andExpect(jsonPath("$").doesNotExist());

        verify(service, atLeastOnce()).deleteById(anyLong());
    }

    @Test
    public void updateSuccess() throws Exception {
        AccountTypeDAO entity = new AccountTypeDAO();
        entity.setId(1L);
        entity.setName("test");

        AccountTypeDAO dao = new AccountTypeDAO();
        BeanUtils.copyProperties(entity, dao);
        dao.setName("test");
        dao.setCreateDate(LocalDate.now().minusDays(1L));
        dao.setUpdateDate(LocalDate.now());

        when(service.update(entity)).thenReturn(dao);

        mvc.perform(patch("/accountType/update")//
                            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)//
                            .content(mapper.writeValueAsString(entity)))//
                .andExpect(status().isOk())//
                .andDo(print())//
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))//
                .andExpect(jsonPath("$.id").exists())//
                .andExpect(jsonPath("$.name", is(dao.getName())))//
                .andExpect(jsonPath("$.createDate").exists())//
                .andExpect(jsonPath("$.updateDate").exists());

        verify(service, atLeastOnce()).update(entity);
    }

    @Test
    public void updateStatus500() throws Exception {
        AccountTypeDAO entity = new AccountTypeDAO();
        entity.setId(1L);
        entity.setName("銀行");

        when(service.update(any())).thenThrow(RepositoryException.class);

        mvc.perform(patch("/accountType/update")//
                            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)//
                            .content(mapper.writeValueAsString(entity)))//
                .andExpect(status().isInternalServerError())//
                .andDo(print());

        verify(service, atLeastOnce()).update(entity);
    }

    @Test
    public void findById() throws Exception {
        AccountTypeDAO dao = new AccountTypeDAO();
        dao.setId(1L);
        dao.setName("test");
        dao.setCreateDate(LocalDate.now());
        dao.setUpdateDate(LocalDate.now());

        when(service.findById(anyLong())).thenReturn(dao);

        mvc.perform(get("/accountType/find/{id}", 1L))//
                .andExpect(status().isOk())//
                .andDo(print());

        verify(service, atLeastOnce()).findById(anyLong());
    }

    @Test
    public void findByIdStatus500() throws Exception {
        when(service.findById(anyLong())).thenThrow(RepositoryException.class);

        mvc.perform(get("/accountType/find/{id}", 1L))//
                .andExpect(status().isInternalServerError())//
                .andDo(print());

        verify(service, atLeastOnce()).findById(anyLong());
    }

    @Test
    public void findAll() throws Exception {
        List<AccountTypeDAO> types = new ArrayList<>();
        types.add(new AccountTypeDAO("test1"));
        types.add(new AccountTypeDAO("test2"));
        types.add(new AccountTypeDAO("test3"));

        when(service.findAll()).thenReturn(types);

        mvc.perform(get("/accountType/find/all"))//
                .andExpect(status().isOk())//
                .andDo(print());

        verify(service, atLeastOnce()).findAll();
    }

}
