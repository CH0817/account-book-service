package tw.com.rex.accountbookservice.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import tw.com.rex.accountbookservice.dao.base.BaseDAO;
import tw.com.rex.accountbookservice.model.define.CategoryTypeEnum;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@DynamicInsert
@Entity
@Table(name = "category")
public class CategoryDAO extends BaseDAO {

    @Column(name = "name", nullable = false, length = 10)
    private String name;
    @Column(name = "category_type", nullable = false)
    private Integer categoryType;
    @JsonIgnoreProperties("category")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private List<ItemDAO> items;

    public CategoryDAO(Long id) {
        super(id);
    }

    public CategoryDAO(String name, Integer categoryType) {
        this.name = name;
        this.categoryType = categoryType;
    }

    public CategoryDAO(String name, CategoryTypeEnum categoryType) {
        this.name = name;
        this.categoryType = categoryType.getCode();
    }

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
        return name.equals(that.name) && categoryType.equals(that.categoryType) && Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, categoryType, items);
    }
}
