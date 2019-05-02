package tw.com.rex.accountbookservice.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import tw.com.rex.accountbookservice.dao.base.BaseDAO;

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

    @Column(name = "name", unique = true, nullable = false, length = 10)
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "currency_id")
    private List<AccountDAO> accounts;

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
