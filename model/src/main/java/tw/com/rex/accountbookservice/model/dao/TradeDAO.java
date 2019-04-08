package tw.com.rex.accountbookservice.model.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tw.com.rex.accountbookservice.model.dao.base.BaseDAO;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trade")
public class TradeDAO extends BaseDAO {

    @ManyToOne(targetEntity = AccountDAO.class)
    @JoinColumn(name = "account_id", nullable = false)
    private AccountDAO accountDTO;
    @ManyToOne(targetEntity = ItemDAO.class)
    @JoinColumn(name = "item_id", nullable = false)
    private ItemDAO item;
    @Column(name = "note", length = 150)
    private String note;
    @Column(name = "transact_date", nullable = false)
    private LocalDate transactDate;
    @Column(name = "cost", precision = 10, scale = 2, nullable = false)
    private BigDecimal cost;

}
