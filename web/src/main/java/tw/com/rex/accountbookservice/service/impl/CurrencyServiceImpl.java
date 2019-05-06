package tw.com.rex.accountbookservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.rex.accountbookservice.annotation.NecessaryData;
import tw.com.rex.accountbookservice.dao.CurrencyDAO;
import tw.com.rex.accountbookservice.repository.CurrencyRepository;
import tw.com.rex.accountbookservice.util.CRUDUtils;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class CurrencyServiceImpl {

    private CurrencyRepository repository;

    @Autowired
    public CurrencyServiceImpl(CurrencyRepository repository) {
        this.repository = repository;
    }

    public CurrencyDAO save(CurrencyDAO entity) {
        entity.setCreateDate(LocalDate.now());
        CRUDUtils.checkNecessaryData(entity, NecessaryData.DLL.SAVE);
        return repository.save(entity);
    }

    public boolean deleteById(String id) {
        repository.deleteById(id);
        return true;
    }

    public CurrencyDAO findById(String id) {
        return repository.getOne(id);
    }

    public List<CurrencyDAO> findAll() {
        return repository.findAll();
    }

    public CurrencyDAO update(CurrencyDAO entity) {
        CurrencyDAO dao = repository.getOne(entity.getId());
        CRUDUtils.copyProperties(entity, dao, "id", "createDate");
        dao.setUpdateDate(LocalDate.now());
        CRUDUtils.checkNecessaryData(dao, NecessaryData.DLL.UPDATE);
        return repository.save(dao);
    }
}
