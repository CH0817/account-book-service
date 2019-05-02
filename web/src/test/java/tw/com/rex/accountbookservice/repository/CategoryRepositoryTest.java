package tw.com.rex.accountbookservice.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import tw.com.rex.accountbookservice.dao.CategoryDAO;
import tw.com.rex.accountbookservice.model.define.CategoryTypeEnum;
import tw.com.rex.accountbookservice.repository.base.BaseRepositoryTest;

import static org.junit.Assert.assertEquals;

@Sql({"/db/data/test/data-category.sql"})
public class CategoryRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private CategoryRepository repository;

    @Test
    public void findByNameAndCategoryType() {
        CategoryDAO dao = repository.findByNameAndCategoryType("一般收入", CategoryTypeEnum.INCOME.getCode());
        assertEquals("一般收入", dao.getName());
        assertEquals(CategoryTypeEnum.INCOME.getCode(), dao.getCategoryType());
    }

}
