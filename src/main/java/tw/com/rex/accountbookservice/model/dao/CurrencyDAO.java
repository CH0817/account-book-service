package tw.com.rex.accountbookservice.model.dao;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import tw.com.rex.accountbookservice.model.dao.base.BaseDAO;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@DynamicInsert
@Entity
@Table(name = "currency")
public class CurrencyDAO extends BaseDAO {

    @ApiModelProperty(value = "currency name, max length 10", required = true)
    @Column(name = "name", unique = true, nullable = false, length = 10)
    private String name;
    @ApiModelProperty("accounts in the currency")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "currency_id")
    private List<AccountDAO> accounts;

    public CurrencyDAO(Long id) {
        super(id);
    }

    public CurrencyDAO(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CurrencyDAO)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        CurrencyDAO that = (CurrencyDAO) o;
        return name.equals(that.name) && Objects.equals(accounts, that.accounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, accounts);
    }
}
