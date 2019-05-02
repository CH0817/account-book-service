package tw.com.rex.accountbookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.com.rex.accountbookservice.dao.AccountDAO;
import tw.com.rex.accountbookservice.dao.AccountTypeDAO;

@Repository
public interface AccountRepository extends JpaRepository<AccountDAO, String> {

    AccountDAO findByNameAndAccountType(String name, AccountTypeDAO accountType);

}
