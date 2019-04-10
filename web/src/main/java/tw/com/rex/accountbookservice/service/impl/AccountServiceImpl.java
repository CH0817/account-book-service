package tw.com.rex.accountbookservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.rex.accountbookservice.model.dao.AccountDAO;
import tw.com.rex.accountbookservice.repository.AccountRepository;
import tw.com.rex.accountbookservice.service.AccountService;
import tw.com.rex.accountbookservice.service.impl.base.BaseServiceImpl;

import javax.transaction.Transactional;

@Service
@Transactional
public class AccountServiceImpl extends BaseServiceImpl<AccountRepository, AccountDAO>
        implements AccountService {

    @Autowired
    public AccountServiceImpl(AccountRepository repository) {
        super(repository);
    }
}
