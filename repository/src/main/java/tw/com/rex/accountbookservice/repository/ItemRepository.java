package tw.com.rex.accountbookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.com.rex.accountbookservice.model.dao.CategoryDAO;
import tw.com.rex.accountbookservice.model.dao.ItemDAO;

@Repository
public interface ItemRepository extends JpaRepository<ItemDAO, Long> {

    ItemDAO findByNameAndCategory(String name, CategoryDAO category);
}
