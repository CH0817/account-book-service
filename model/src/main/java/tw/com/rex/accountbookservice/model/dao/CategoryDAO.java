package tw.com.rex.accountbookservice.model.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import tw.com.rex.accountbookservice.model.dao.define.CategoryTypeEnum;
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
@Table(name = "category")
@DynamicInsert
@DynamicUpdate
public class CategoryDAO extends BaseDAO {

    @NonNull
    @Column(name = "name", nullable = false, length = 10)
    private String name;
    @NonNull
    @Column(name = "category_type", nullable = false)
    private Integer categoryType;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private List<ItemDAO> items;

    public void setCategoryType(Integer categoryType) {
        if (Objects.nonNull(categoryType) && CategoryTypeEnum.isCategory(categoryType)) {
            this.categoryType = categoryType;
        }
    }

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
        return name.equals(that.name) && categoryType.equals(that.categoryType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, categoryType);
    }
}
