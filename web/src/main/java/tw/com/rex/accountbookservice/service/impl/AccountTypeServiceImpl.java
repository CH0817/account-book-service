package tw.com.rex.accountbookservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.rex.accountbookservice.annotation.NecessaryData;
import tw.com.rex.accountbookservice.dao.AccountTypeDAO;
import tw.com.rex.accountbookservice.exception.NotFoundDataException;
import tw.com.rex.accountbookservice.repository.AccountTypeRepository;
import tw.com.rex.accountbookservice.service.AccountTypeService;
import tw.com.rex.accountbookservice.util.CRUDUtils;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class AccountTypeServiceImpl implements AccountTypeService {

    private AccountTypeRepository repository;

    @Autowired
    public AccountTypeServiceImpl(AccountTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public AccountTypeDAO save(AccountTypeDAO entity) {
        entity.setCreateDate(LocalDate.now());
        CRUDUtils.checkNecessaryData(entity, NecessaryData.DLL.SAVE);
        return repository.save(entity);
    }

    @Override
    public boolean deleteById(String id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public AccountTypeDAO findById(String id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundDataException("cannot found by id: " + id));
    }

    @Override
    public List<AccountTypeDAO> findAll() {
        return repository.findAll();
    }

    @Override
    public AccountTypeDAO update(AccountTypeDAO entity) {
        AccountTypeDAO dao = findById(entity.getId());
        CRUDUtils.copyProperties(entity, dao, "id", "createDate");
        dao.setUpdateDate(LocalDate.now());
        CRUDUtils.checkNecessaryData(dao, NecessaryData.DLL.UPDATE);
        return repository.save(dao);
    }

}
