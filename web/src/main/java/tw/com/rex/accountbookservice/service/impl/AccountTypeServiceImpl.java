package tw.com.rex.accountbookservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.rex.accountbookservice.model.dao.AccountTypeDAO;
import tw.com.rex.accountbookservice.repository.AccountTypeRepository;
import tw.com.rex.accountbookservice.service.AccountTypeService;
import tw.com.rex.accountbookservice.service.impl.base.BaseServiceImpl;

import javax.transaction.Transactional;

@Service
@Transactional
public class AccountTypeServiceImpl extends BaseServiceImpl<AccountTypeRepository, AccountTypeDAO>
        implements AccountTypeService {

    @Autowired
    public AccountTypeServiceImpl(AccountTypeRepository repository) {
        super(repository);
    }

}
