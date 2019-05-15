package tw.com.rex.accountbookservice.model.dao;

import java.util.Date;

import tw.com.rex.accountbookservice.annotation.NecessaryData;
import tw.com.rex.accountbookservice.model.dao.base.BaseDao;

public class AccountType extends BaseDao {

    @NecessaryData(useIn = {NecessaryData.DLL.UPDATE, NecessaryData.DLL.DELETE})
    private String id;
    @NecessaryData(useIn = {NecessaryData.DLL.UPDATE, NecessaryData.DLL.INSERT})
    private String name;
    @NecessaryData(useIn = {NecessaryData.DLL.INSERT})
    private Date createDate;
    @NecessaryData(useIn = {NecessaryData.DLL.UPDATE,})
    private Date updateDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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