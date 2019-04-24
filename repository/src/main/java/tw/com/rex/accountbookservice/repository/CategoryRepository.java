package tw.com.rex.accountbookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.com.rex.accountbookservice.model.dao.CategoryDAO;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryDAO, Long> {

    CategoryDAO findByNameAndCategoryType(String name, Integer categoryType);

    CategoryDAO findByName(String name);
}
