package tw.com.rex.accountbookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.com.rex.accountbookservice.model.dao.CurrencyDAO;

@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyDAO, Long> {

    CurrencyDAO findByName(String name);

}
