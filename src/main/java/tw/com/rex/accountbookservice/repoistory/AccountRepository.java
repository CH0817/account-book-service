package tw.com.rex.accountbookservice.repoistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.com.rex.accountbookservice.model.dao.AccountDAO;
import tw.com.rex.accountbookservice.model.dao.AccountTypeDAO;

@Repository
public interface AccountRepository extends JpaRepository<AccountDAO, Long> {

    AccountDAO findByNameAndAccountType(String name, AccountTypeDAO accountType);

    AccountDAO findByNameAndAccountTypeAndIdNot(String name, AccountTypeDAO accountType, Long id);
}
