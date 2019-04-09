package tw.com.rex.accountbookservice.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import tw.com.rex.accountbookservice.model.dao.CategoryDAO;
import tw.com.rex.accountbookservice.model.vo.base.BaseVO;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryVO extends BaseVO {

    private String name;
    private Integer categoryType;
    private List<ItemVO> items;

    public CategoryVO(CategoryDAO dao) {
        BeanUtils.copyProperties(dao, this);
    }
}
