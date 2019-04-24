package tw.com.rex.accountbookservice.model.dao.base;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class BaseDAO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "create_date", nullable = false)
    private LocalDate createDate;
    @Column(name = "update_date")
    private LocalDate updateDate;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BaseDAO)) {
            return false;
        }
        BaseDAO baseDAO = (BaseDAO) o;
        return Objects.equals(id, baseDAO.id) && Objects.equals(createDate, baseDAO.createDate) && Objects.equals(
                updateDate, baseDAO.updateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createDate, updateDate);
    }
}
