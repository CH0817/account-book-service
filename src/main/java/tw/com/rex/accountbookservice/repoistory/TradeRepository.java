package tw.com.rex.accountbookservice.repoistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.com.rex.accountbookservice.model.dao.TradeDAO;

@Repository
public interface TradeRepository extends JpaRepository<TradeDAO, Long> {}
