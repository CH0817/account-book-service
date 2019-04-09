package tw.com.rex.accountbookservice.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tw.com.rex.accountbookservice.exception.RepositoryException;
import tw.com.rex.accountbookservice.model.dao.AccountTypeDAO;
import tw.com.rex.accountbookservice.model.vo.AccountTypeVO;
import tw.com.rex.accountbookservice.repository.AccountTypeRepository;
import tw.com.rex.accountbookservice.service.AccountTypeService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AccountTypeServiceImpl implements AccountTypeService {

    private AccountTypeRepository repository;

    @Autowired
    public AccountTypeServiceImpl(AccountTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public AccountTypeVO save(AccountTypeDAO entity) throws RepositoryException {
        LocalDate now = LocalDate.now();
        entity.setCreateDate(now);
        entity.setUpdateDate(now);
        AccountTypeVO result = new AccountTypeVO();
        try {
            AccountTypeDAO dao = repository.save(entity);
            BeanUtils.copyProperties(dao, result);
        } catch (Exception e) {
            throw new RepositoryException("save account type failure " + entity, e);
        }
        return result;
    }

    @Override
    public boolean deleteById(long id) throws RepositoryException {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new RepositoryException("delete account type id " + id + " failure", e);
        }
        return true;
    }

    @Override
    public AccountTypeVO findById(long id) {
        AccountTypeVO result = null;
        Optional<AccountTypeDAO> daoOptional = repository.findById(id);
        if (daoOptional.isPresent()) {
            result = new AccountTypeVO();
            BeanUtils.copyProperties(daoOptional.get(), result);
        }
        return result;
    }

    @Override
    public List<AccountTypeVO> findAll() {
        List<AccountTypeDAO> all = repository.findAll();
        if (!CollectionUtils.isEmpty(all)) {
            return all.stream().map(AccountTypeVO::new).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public AccountTypeVO update(AccountTypeDAO entity) throws RepositoryException {
        AccountTypeVO result = null;
        Optional<AccountTypeDAO> daoOptional = repository.findById(entity.getId());
        if (daoOptional.isPresent()) {
            AccountTypeDAO dao = daoOptional.get();
            BeanUtils.copyProperties(entity, dao, "id", "createDate");
            dao.setUpdateDate(LocalDate.now());
            try {
                dao = repository.save(dao);
                result = new AccountTypeVO(dao);
            } catch (Exception e) {
                throw new RepositoryException("update account type failure " + entity, e);
            }
        }
        return result;
    }

}
