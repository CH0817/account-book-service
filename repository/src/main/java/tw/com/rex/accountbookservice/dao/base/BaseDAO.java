package tw.com.rex.accountbookservice.dao.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseDAO implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", length = 36, nullable = false, unique = true)
    private String id;
    @Column(name = "create_date", nullable = false)
    private LocalDate createDate;
    @Column(name = "update_date")
    private LocalDate updateDate;

    public BaseDAO(String id) {
        this.id = id;
    }

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
        return id.equals(baseDAO.id) && Objects.equals(createDate, baseDAO.createDate) && Objects.equals(updateDate,
                                                                                                         baseDAO.updateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createDate, updateDate);
    }
}
