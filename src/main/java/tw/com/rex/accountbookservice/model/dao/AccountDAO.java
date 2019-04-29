package tw.com.rex.accountbookservice.model.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import tw.com.rex.accountbookservice.model.dao.base.BaseDAO;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@DynamicInsert
@Entity
@Table(name = "account")
public class AccountDAO extends BaseDAO {

    @ApiModelProperty(value = "account name, max length 10", required = true)
    @Column(name = "name", unique = true, nullable = false, length = 10)
    private String name;
    @ApiModelProperty(value = "account type", required = true)
    @JsonIgnoreProperties("accounts")
    @ManyToOne
    @JoinColumn(name = "account_type_id", nullable = false)
    private AccountTypeDAO accountType;
    @ApiModelProperty(value = "account currency", required = true)
    @JsonIgnoreProperties("accounts")
    @ManyToOne
    @JoinColumn(name = "currency_id", nullable = false)
    private CurrencyDAO currency;
    @ApiModelProperty(value = "initialization money when the account created", required = true)
    @Column(name = "init_money", nullable = false)
    private BigDecimal initMoney;
    @ApiModelProperty(value = "current money", required = true)
    @Column(name = "current_money", nullable = false)
    private BigDecimal currentMoney;
    @ApiModelProperty("account closing date")
    @Column(name = "closing_date")
    private LocalDate closingDate;
    @ApiModelProperty("account payment due date")
    @Column(name = "payment_due_date")
    private LocalDate paymentDueDate;
    @ApiModelProperty("account's note")
    @Column(name = "note", length = 150)
    private String note;
    @ApiModelProperty("trades in the account")
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
