package tw.com.rex.accountbookservice.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import tw.com.rex.accountbookservice.model.dao.AccountDAO;
import tw.com.rex.accountbookservice.model.dao.TradeDAO;
import tw.com.rex.accountbookservice.model.vo.base.BaseVO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountVO extends BaseVO {

    private String name;
    private AccountTypeVO accountType;
    private CurrencyVO currency;
    private BigDecimal initMoney;
    private BigDecimal currentMoney;
    private LocalDate closingDate;
    private LocalDate paymentDueDate;
    private String note;
    private List<TradeVO> transactions;

    public AccountVO(AccountDAO dao) {
        String[] ignores = {"accountType", "currency", "transactions"};
        BeanUtils.copyProperties(dao, this, ignores);
        this.setAccountType(new AccountTypeVO(dao.getAccountType()));
        this.setCurrency(new CurrencyVO(dao.getCurrency()));
        List<TradeDAO> daoList = dao.getTransactions();
        if (!CollectionUtils.isEmpty(daoList)) {
            List<TradeVO> voList = daoList.stream()//
                    .map(TradeVO::new)//
                    .collect(Collectors.toList());
            this.setTransactions(voList);
        }
    }
}
