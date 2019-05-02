package tw.com.rex.accountbookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.com.rex.accountbookservice.dao.TradeDAO;

@Repository
public interface TradeRepository extends JpaRepository<TradeDAO, String> {}
