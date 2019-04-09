package tw.com.rex.accountbookservice.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import tw.com.rex.accountbookservice.model.dao.TradeDAO;
import tw.com.rex.accountbookservice.model.vo.base.BaseVO;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TradeVO extends BaseVO {

    private AccountVO accountDTO;
    private ItemVO item;
    private String note;
    private LocalDate transactDate;
    private BigDecimal cost;

    public TradeVO(TradeDAO dao) {
        BeanUtils.copyProperties(dao, this);
    }
}
