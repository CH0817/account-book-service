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
@Table(name = "item")
@DynamicInsert
@DynamicUpdate
public class ItemDAO extends BaseDAO {

    @NonNull
    @Column(name = "name", unique = true, nullable = false, length = 10)
    private String name;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryDAO category;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    private List<TradeDAO> trades;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ItemDAO)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        ItemDAO itemDAO = (ItemDAO) o;
        return name.equals(itemDAO.name) && category.equals(itemDAO.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, category);
    }
}
