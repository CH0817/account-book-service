package tw.com.rex.accountbookservice.model.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tw.com.rex.accountbookservice.model.dao.base.BaseDAO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "currency")
public class CurrencyDAO extends BaseDAO {

    @Column(name = "name", unique = true, nullable = false, length = 10)
    private String name;

}
