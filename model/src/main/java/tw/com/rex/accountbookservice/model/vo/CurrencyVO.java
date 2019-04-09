package tw.com.rex.accountbookservice.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import tw.com.rex.accountbookservice.model.dao.CurrencyDAO;
import tw.com.rex.accountbookservice.model.vo.base.BaseVO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyVO extends BaseVO {

    private Long id;
    private String name;

    public CurrencyVO(CurrencyDAO dao) {
        BeanUtils.copyProperties(dao, this);
    }
}
