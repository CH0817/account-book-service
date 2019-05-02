package tw.com.rex.accountbookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.com.rex.accountbookservice.dao.AccountTypeDAO;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountTypeDAO, String> {

    AccountTypeDAO findByName(String name);

}
