package tw.com.rex.accountbookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.com.rex.accountbookservice.model.dao.AccountTypeDAO;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountTypeDAO, Long> {

    AccountTypeDAO findByName(String name);

}
