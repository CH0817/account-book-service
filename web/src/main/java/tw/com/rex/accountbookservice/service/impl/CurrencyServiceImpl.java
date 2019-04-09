package tw.com.rex.accountbookservice.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tw.com.rex.accountbookservice.exception.RepositoryException;
import tw.com.rex.accountbookservice.model.dao.CurrencyDAO;
import tw.com.rex.accountbookservice.model.vo.CurrencyVO;
import tw.com.rex.accountbookservice.repository.CurrencyRepository;
import tw.com.rex.accountbookservice.service.CurrencyService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private CurrencyRepository repository;

    @Autowired
    public CurrencyServiceImpl(CurrencyRepository repository) {
        this.repository = repository;
    }

    @Override
    public CurrencyVO save(CurrencyDAO entity) throws RepositoryException {
        LocalDate now = LocalDate.now();
        entity.setCreateDate(now);
        entity.setUpdateDate(now);
        CurrencyVO result = new CurrencyVO();
        try {
            CurrencyDAO dao = repository.save(entity);
            BeanUtils.copyProperties(dao, result);
        } catch (Exception e) {
            throw new RepositoryException("save currency failure " + entity, e);
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
    public CurrencyVO findById(long id) {
        CurrencyVO result = null;
        Optional<CurrencyDAO> daoOptional = repository.findById(id);
        if (daoOptional.isPresent()) {
            result = new CurrencyVO();
            BeanUtils.copyProperties(daoOptional.get(), result);
        }
        return result;
    }

    @Override
    public List<CurrencyVO> findAll() {
        List<CurrencyDAO> all = repository.findAll();
        if (!CollectionUtils.isEmpty(all)) {
            return all.stream().map(CurrencyVO::new).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public CurrencyVO update(CurrencyDAO entity) throws RepositoryException {
        CurrencyVO result = null;
        Optional<CurrencyDAO> daoOptional = repository.findById(entity.getId());
        if (daoOptional.isPresent()) {
            CurrencyDAO dao = daoOptional.get();
            BeanUtils.copyProperties(entity, dao, "id", "createDate");
            dao.setUpdateDate(LocalDate.now());
            try {
                dao = repository.save(dao);
                result = new CurrencyVO(dao);
            } catch (Exception e) {
                throw new RepositoryException("update account type failure " + entity, e);
            }
        }
        return result;
    }

}
