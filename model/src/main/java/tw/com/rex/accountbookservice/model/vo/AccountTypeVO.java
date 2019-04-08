package tw.com.rex.accountbookservice.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tw.com.rex.accountbookservice.model.vo.base.BaseVO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountTypeVO extends BaseVO {

    private Long id;
    private String name;

}
