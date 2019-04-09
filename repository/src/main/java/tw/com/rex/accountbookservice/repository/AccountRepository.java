package tw.com.rex.accountbookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.com.rex.accountbookservice.model.dao.AccountDAO;

@Repository
public interface AccountRepository extends JpaRepository<AccountDAO, Long> {}