package tw.com.rex.accountbookservice.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import tw.com.rex.accountbookservice.dao.CategoryDAO;
import tw.com.rex.accountbookservice.model.define.CategoryTypeEnum;
import tw.com.rex.accountbookservice.web.base.BaseControllerTest;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@Sql({"/db/data/test/data-category.sql"})
public class CategoryControllerTest extends BaseControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void saveIncomeTypeSuccess() throws Exception {
        CategoryDAO dao = new CategoryDAO("test", CategoryTypeEnum.INCOME.getCode());
        expectOkJsonRequest(mvc.perform(postJsonRequest("/category/save", dao)))//
                .andExpect(jsonPath("$.data.id").exists())//
                .andExpect(jsonPath("$.data.name", is("test")))//
                .andExpect(jsonPath("$.data.createDate", is(today())));

    }

    @Test
    public void saveWithDuplicate() throws Exception {
        CategoryDAO dao = new CategoryDAO("一般收入", CategoryTypeEnum.INCOME.getCode());
        expectDuplicateFailJsonRequest(mvc.perform(postJsonRequest("/category/save", dao)));
    }

    @Test
    public void deleteByIdSuccess() throws Exception {
        expectOkJsonRequest(mvc.perform(delete("/category/delete/{id}", 66L)))//
                .andExpect(jsonPath("$.data", is(true)));
    }

    @Test
    public void deleteByIdWithNotFoundId() throws Exception {
        expectDatabaseFailJsonRequest(mvc.perform(delete("/category/delete/{id}", 1L)));
    }

    @Test
    public void updateSuccess() throws Exception {
        CategoryDAO entity = new CategoryDAO();
        entity.setId(66L);
        entity.setName("test");
        entity.setCategoryType(CategoryTypeEnum.INCOME.getCode());

        expectOkJsonRequest(mvc.perform(patchJsonRequest("/category/update", entity)))//
                .andExpect(jsonPath("$.data.id").exists())//
                .andExpect(jsonPath("$.data.name", is(entity.getName())))//
                .andExpect(jsonPath("$.data.createDate").exists())//
                .andExpect(jsonPath("$.data.updateDate", is(today())));
    }

    @Test
    public void updateWithDuplicate() throws Exception {
        CategoryDAO entity = new CategoryDAO();
        entity.setId(66L);
        entity.setName("一般收入");
        entity.setCategoryType(CategoryTypeEnum.INCOME.getCode());

        expectDuplicateFailJsonRequest(mvc.perform(patchJsonRequest("/category/update", entity)));
    }

    @Test
    public void findById() throws Exception {
        expectOkJsonRequest(mvc.perform(get("/category/find/{id}", 66L)))//
                .andExpect(jsonPath("$.data.id", is(66)))//
                .andExpect(jsonPath("$.data.name", is("一般收入")));
    }

    @Test
    public void findByIdWithNotFound() throws Exception {
        expectDatabaseFailJsonRequest(mvc.perform(get("/category/find/{id}", 1L)));
    }

    @Test
    public void findAll() throws Exception {
        expectOkJsonRequest(mvc.perform(get("/category/find/all")))//
                .andExpect(jsonPath("$.data", hasSize(2)));
    }

}
