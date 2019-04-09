package tw.com.rex.accountbookservice.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import tw.com.rex.accountbookservice.model.dao.AccountTypeDAO;
import tw.com.rex.accountbookservice.model.vo.base.BaseVO;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountTypeVO extends BaseVO {

    private Long id;
    private String name;

    public AccountTypeVO(AccountTypeDAO dao) {
        if (Objects.nonNull(dao)) {
            BeanUtils.copyProperties(dao, this);
        }
    }
}
