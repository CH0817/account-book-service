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
@Table(name = "item")
public class ItemDAO extends BaseDAO {

    @ApiModelProperty(value = "name of the item, max length 10", required = true)
    @Column(name = "name", nullable = false, length = 10)
    private String name;
    @ApiModelProperty(value = "category of the item", required = true)
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryDAO category;
    @ApiModelProperty("trades of the item")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    private List<TradeDAO> trades;

    public ItemDAO(Long id) {
        super(id);
    }

    public ItemDAO(String name, CategoryDAO category) {
        this.name = name;
        this.category = category;
    }

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
        return name.equals(itemDAO.name) && category.equals(itemDAO.category) && Objects.equals(trades, itemDAO.trades);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, category, trades);
    }
}
