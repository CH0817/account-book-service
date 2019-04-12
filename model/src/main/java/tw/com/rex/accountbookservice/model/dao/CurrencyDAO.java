package tw.com.rex.accountbookservice.model.dao;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import tw.com.rex.accountbookservice.model.dao.base.BaseDAO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "currency")
@DynamicInsert
@DynamicUpdate
public class CurrencyDAO extends BaseDAO {

    @Column(name = "name", unique = true, nullable = false, length = 10)
    private String name;

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
        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getName());
    }
}
