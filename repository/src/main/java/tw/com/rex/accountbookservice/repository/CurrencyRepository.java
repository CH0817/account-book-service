package tw.com.rex.accountbookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.com.rex.accountbookservice.dao.CurrencyDAO;

@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyDAO, String> {

    CurrencyDAO findByName(String name);

}
