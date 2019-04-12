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
@Entity
@Table(name = "category")
@DynamicInsert
@DynamicUpdate
public class CategoryDAO extends BaseDAO {

    @Column(name = "name", unique = true, nullable = false, length = 10)
    private String name;
    @Column(name = "category_type", nullable = false)
    private Integer categoryType;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private List<ItemDAO> items;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CategoryDAO)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        CategoryDAO that = (CategoryDAO) o;
        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getName());
    }
}
