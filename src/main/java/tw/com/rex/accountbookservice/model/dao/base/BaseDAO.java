package tw.com.rex.accountbookservice.model.dao.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
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
@MappedSuperclass
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseDAO implements Serializable {

    @ApiModelProperty("primary key, auto create when insert")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ApiModelProperty("data create date, generate when insert")
    @Column(name = "create_date", nullable = false)
    private LocalDate createDate;
    @ApiModelProperty("data update date, generate when update")
    @Column(name = "update_date")
    private LocalDate updateDate;

    public BaseDAO(Long id) {
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
