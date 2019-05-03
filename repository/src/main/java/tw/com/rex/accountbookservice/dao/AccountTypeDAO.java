package tw.com.rex.accountbookservice.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import tw.com.rex.accountbookservice.annotation.NecessaryData;
import tw.com.rex.accountbookservice.dao.base.BaseDAO;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@DynamicInsert
@Entity
@Table(name = "account_type")
@DynamicUpdate
public class AccountTypeDAO extends BaseDAO {

    @NecessaryData(useIn = {NecessaryData.DLL.SAVE, NecessaryData.DLL.UPDATE})
    @Column(name = "name", unique = true, nullable = false, length = 10)
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_type_id")
    private List<AccountDAO> accounts;

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
