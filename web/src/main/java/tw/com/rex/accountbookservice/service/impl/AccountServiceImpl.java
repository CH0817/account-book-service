package tw.com.rex.accountbookservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.rex.accountbookservice.dao.AccountDAO;
import tw.com.rex.accountbookservice.repository.AccountRepository;
import tw.com.rex.accountbookservice.service.AccountService;
import tw.com.rex.accountbookservice.service.impl.base.BaseServiceImpl;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
@Transactional
public class AccountServiceImpl extends BaseServiceImpl<AccountRepository, AccountDAO> implements AccountService {

    private AccountRepository repository;

    @Autowired
    public AccountServiceImpl(AccountRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public Boolean isDuplicate(AccountDAO entity) {
        return Objects.isNull(entity.getId()) ? isDuplicateForSave(entity) : isDuplicateForUpdate(entity);
    }

    private Boolean isDuplicateForSave(AccountDAO entity) {
        return Objects.nonNull(repository.findByNameAndAccountType(entity.getName(), entity.getAccountType()));
    }

    private Boolean isDuplicateForUpdate(AccountDAO entity) {
        // return Objects.nonNull(
        //         repository.findByNameAndAccountTypeAndIdNot(entity.getName(), entity.getAccountType(), entity.getId()));
        // FIXME
        return true;
    }

}
