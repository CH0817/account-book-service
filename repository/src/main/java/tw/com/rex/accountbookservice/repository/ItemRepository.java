package tw.com.rex.accountbookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.com.rex.accountbookservice.dao.CategoryDAO;
import tw.com.rex.accountbookservice.dao.ItemDAO;

@Repository
public interface ItemRepository extends JpaRepository<ItemDAO, String> {

    ItemDAO findByNameAndCategory(String name, CategoryDAO category);
}
