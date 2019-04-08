package tw.com.rex.accountbookservice.model.dao;

import lombok.*;
import tw.com.rex.accountbookservice.model.dao.base.BaseDAO;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class AccountDAO extends BaseDAO {

    @Column(name = "name", unique = true, nullable = false, length = 10)
    private String name;
    @ManyToOne(targetEntity = AccountDAO.class)
    @JoinColumn(name = "account_type_id", nullable = false)
    private AccountTypeDAO accountType;
    @ManyToOne(targetEntity = CurrencyDAO.class)
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
        AccountDAO that = (AccountDAO) o;
        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getName());
    }
}
