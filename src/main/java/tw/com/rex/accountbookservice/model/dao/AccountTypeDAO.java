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
@Table(name = "account_type")
public class AccountTypeDAO extends BaseDAO {

    @ApiModelProperty(value = "account type name, max length 10", required = true)
    @Column(name = "name", unique = true, nullable = false, length = 10)
    private String name;
    @ApiModelProperty("accounts in the account type")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_type_id")
    private List<AccountDAO> accounts;

    public AccountTypeDAO(Long id) {
        super(id);
    }

    public AccountTypeDAO(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AccountTypeDAO)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        AccountTypeDAO dao = (AccountTypeDAO) o;
        return name.equals(dao.name) && Objects.equals(accounts, dao.accounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, accounts);
    }
}
