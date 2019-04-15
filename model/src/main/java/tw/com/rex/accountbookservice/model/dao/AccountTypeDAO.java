package tw.com.rex.accountbookservice.model.dao;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import tw.com.rex.accountbookservice.model.dao.base.BaseDAO;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "account_type")
@DynamicInsert
@DynamicUpdate
public class AccountTypeDAO extends BaseDAO {

    @NonNull
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
        AccountTypeDAO that = (AccountTypeDAO) o;
        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getName());
    }
}
