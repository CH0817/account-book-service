package tw.com.rex.accountbookservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.rex.accountbookservice.model.dao.AccountTypeDAO;
import tw.com.rex.accountbookservice.repoistory.AccountTypeRepository;
import tw.com.rex.accountbookservice.service.AccountTypeService;
import tw.com.rex.accountbookservice.service.impl.base.BaseServiceImpl;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
@Transactional
public class AccountTypeServiceImpl extends BaseServiceImpl<AccountTypeRepository, AccountTypeDAO>
        implements AccountTypeService {

    private AccountTypeRepository repository;

    @Autowired
    public AccountTypeServiceImpl(AccountTypeRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    protected Boolean isDuplicate(AccountTypeDAO entity) {
        return Objects.nonNull(repository.findByName(entity.getName()));
    }

}
