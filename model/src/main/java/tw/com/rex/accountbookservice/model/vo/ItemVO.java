package tw.com.rex.accountbookservice.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import tw.com.rex.accountbookservice.model.dao.CategoryDAO;
import tw.com.rex.accountbookservice.model.dao.ItemDAO;
import tw.com.rex.accountbookservice.model.vo.base.BaseVO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemVO extends BaseVO {

    private String name;
    private CategoryVO category;

    public ItemVO(ItemDAO dao) {
        BeanUtils.copyProperties(dao, this);
    }
}
