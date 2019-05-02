package tw.com.rex.accountbookservice.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import tw.com.rex.accountbookservice.dao.CategoryDAO;
import tw.com.rex.accountbookservice.dao.ItemDAO;
import tw.com.rex.accountbookservice.repository.base.BaseRepositoryTest;

import static org.junit.Assert.assertEquals;

@Sql({"/db/data/test/data-category.sql", "/db/data/test/data-item.sql"})
public class ItemRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private ItemRepository repository;

    @Test
    public void findByNameAndCategory() {
        CategoryDAO categoryDAO = new CategoryDAO();
        categoryDAO.setId("a");
        ItemDAO dao = repository.findByNameAndCategory("薪資", categoryDAO);
        assertEquals("薪資", dao.getName());
        assertEquals("a", categoryDAO.getId());
    }

}
