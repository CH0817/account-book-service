package tw.com.rex.accountbookservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.rex.accountbookservice.mapper.AccountTypeMapper;
import tw.com.rex.accountbookservice.model.dao.AccountType;
import tw.com.rex.accountbookservice.service.AccountTypeService;

import java.util.List;

@Service
public class AccountTypeServiceImpl implements AccountTypeService {

    private AccountTypeMapper accountTypeMapper;

    @Autowired
    public AccountTypeServiceImpl(AccountTypeMapper accountTypeMapper) {
        this.accountTypeMapper = accountTypeMapper;
    }

    @Override
    public String insert(AccountType entity) {
        accountTypeMapper.insertSelective(entity);
        return entity.getId();
    }

    @Override
    public boolean deleteById(String id) {
        return accountTypeMapper.deleteByPrimaryKey(id) == 1;
    }

    @Override
    public boolean updateById(AccountType entity) {
        return accountTypeMapper.updateByPrimaryKeySelective(entity) == 1;
    }

    @Override
    public AccountType selectById(String id) {
        return accountTypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<AccountType> selectAll() {
        return accountTypeMapper.selectAll();
    }
}
