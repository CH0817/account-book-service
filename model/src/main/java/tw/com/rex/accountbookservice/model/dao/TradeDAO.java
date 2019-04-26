package tw.com.rex.accountbookservice.model.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import tw.com.rex.accountbookservice.model.dao.base.BaseDAO;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@DynamicInsert
@Entity
@Table(name = "trade")
public class TradeDAO extends BaseDAO {

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private AccountDAO account;
    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private ItemDAO item;
    @Column(name = "note", length = 150)
    private String note;
    @Column(name = "transact_date", nullable = false)
    private LocalDate transactDate;
    @Column(name = "cost", precision = 10, scale = 2, nullable = false)
    private BigDecimal cost;

    public TradeDAO(Long id) {
        super(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TradeDAO)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        TradeDAO tradeDAO = (TradeDAO) o;
        return account.equals(tradeDAO.account) && item.equals(tradeDAO.item) && Objects.equals(note, tradeDAO.note)
               && transactDate.equals(tradeDAO.transactDate) && cost.equals(tradeDAO.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), account, item, note, transactDate, cost);
    }
}
