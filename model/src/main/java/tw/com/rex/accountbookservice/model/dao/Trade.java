package tw.com.rex.accountbookservice.model.dao;

import java.math.BigDecimal;
import java.util.Date;

import tw.com.rex.accountbookservice.model.dao.base.BaseDao;

public class Trade extends BaseDao {

    private String id;
    private String accountId;
    private String itemId;
    private Date transactDate;
    private BigDecimal cost;
    private String note;
    private Date createDate;
    private Date updateDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Date getTransactDate() {
        return transactDate;
    }

    public void setTransactDate(Date transactDate) {
        this.transactDate = transactDate;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}