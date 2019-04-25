package tw.com.rex.accountbookservice.model.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import tw.com.rex.accountbookservice.model.dao.base.BaseDAO;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "account")
@DynamicInsert
@DynamicUpdate
public class AccountDAO extends BaseDAO {

    @Column(name = "name", unique = true, nullable = false, length = 10)
    private String name;
    @ManyToOne
    @JoinColumn(name = "account_type_id", nullable = false)
    private AccountTypeDAO accountType;
    @ManyToOne
    @JoinColumn(name = "currency_id", nullable = false)
    private CurrencyDAO currency;
    @Column(name = "init_money", nullable = false)
    private BigDecimal initMoney;
    @Column(name = "current_money", nullable = false)
    private BigDecimal currentMoney;
    @Column(name = "closing_date")
    private LocalDate closingDate;
    @Column(name = "payment_due_date")
    private LocalDate paymentDueDate;
    @Column(name = "note", length = 150)
    private String note;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private List<TradeDAO> transactions;

    public AccountDAO(Long id) {
        super(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AccountDAO)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        AccountDAO dao = (AccountDAO) o;
        return name.equals(dao.name) && accountType.equals(dao.accountType) && currency.equals(dao.currency)
               && Objects.equals(initMoney, dao.initMoney) && Objects.equals(currentMoney, dao.currentMoney)
               && Objects.equals(closingDate, dao.closingDate) && Objects.equals(paymentDueDate, dao.paymentDueDate)
               && Objects.equals(note, dao.note) && Objects.equals(transactions, dao.transactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, accountType, currency, initMoney, currentMoney, closingDate,
                            paymentDueDate, note, transactions);
    }
}
