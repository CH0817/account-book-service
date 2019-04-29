package tw.com.rex.accountbookservice.web;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import tw.com.rex.accountbookservice.dao.CategoryDAO;
import tw.com.rex.accountbookservice.dao.ItemDAO;
import tw.com.rex.accountbookservice.web.base.BaseControllerTest;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@Sql({"/db/data/test/data-category.sql", "/db/data/test/data-item.sql"})
public class ItemControllerTest extends BaseControllerTest {

    @Autowired
    private MockMvc mvc;
    private CategoryDAO categoryDAO;

    @Before
    public void setUp() {
        categoryDAO = new CategoryDAO();
        categoryDAO.setId(66L);
    }

    @Test
    public void saveSuccess() throws Exception {
        ItemDAO dao = new ItemDAO("test", categoryDAO);
        expectOkJsonRequest(mvc.perform(postJsonRequest("/item/save", dao)))//
                .andExpect(jsonPath("$.data.id").exists())//
                .andExpect(jsonPath("$.data.name", is("test")))//
                .andExpect(jsonPath("$.data.createDate", is(today())));
    }

    @Test
    public void saveWithNoCategory() throws Exception {
        ItemDAO dao = new ItemDAO();
        dao.setName("test");
        expectDatabaseFailJsonRequest(mvc.perform(postJsonRequest("/item/save", dao)));
    }

    @Test
    public void saveWithDuplicate() throws Exception {
        ItemDAO dao = new ItemDAO("早餐", new CategoryDAO(77L));
        expectDuplicateFailJsonRequest(mvc.perform(postJsonRequest("/item/save", dao)));
    }

    @Test
    public void deleteByIdSuccess() throws Exception {
        expectOkJsonRequest(mvc.perform(delete("/item/delete/{id}", 66L)))//
                .andExpect(jsonPath("$.data", is(true)));
    }

    @Test
    public void deleteByIdWithNotFoundId() throws Exception {
        expectDatabaseFailJsonRequest(mvc.perform(delete("/item/delete/{id}", 1L)));
    }

    @Test
    public void updateSuccess() throws Exception {
        ItemDAO entity = new ItemDAO();
        entity.setId(66L);
        entity.setName("test");
        entity.setCategory(categoryDAO);

        expectOkJsonRequest(mvc.perform(patchJsonRequest("/item/update", entity)))//
                .andExpect(jsonPath("$.data.id").exists())//
                .andExpect(jsonPath("$.data.name", is(entity.getName())))//
                .andExpect(jsonPath("$.data.createDate").exists())//
                .andExpect(jsonPath("$.data.updateDate", is(today())));
    }

    @Test
    public void updateWithNoCategory() throws Exception {
        ItemDAO entity = new ItemDAO();
        entity.setId(66L);
        entity.setName("晚餐");
        expectDatabaseFailJsonRequest(mvc.perform(patchJsonRequest("/item/update", entity)));
    }

    @Test
    public void updateWithDuplicate() throws Exception {
        ItemDAO entity = new ItemDAO();
        entity.setId(66L);
        entity.setName("晚餐");
        entity.setCategory(new CategoryDAO(77L));

        expectDuplicateFailJsonRequest(mvc.perform(patchJsonRequest("/item/update", entity)));
    }

    @Test
    public void findById() throws Exception {
        expectOkJsonRequest(mvc.perform(get("/item/find/{id}", 66L)))//
                .andExpect(jsonPath("$.data.id", is(66)))//
                .andExpect(jsonPath("$.data.name", is("早餐")));
    }

    @Test
    public void findByIdWithNotFound() throws Exception {
        expectDatabaseFailJsonRequest(mvc.perform(get("/item/find/{id}", 1L)));
    }

    @Test
    public void findAll() throws Exception {
        expectOkJsonRequest(mvc.perform(get("/item/find/all")))//
                .andExpect(jsonPath("$.data", hasSize(5)));
    }

}
