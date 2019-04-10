package tw.com.rex.accountbookservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.rex.accountbookservice.model.dao.CurrencyDAO;
import tw.com.rex.accountbookservice.repository.CurrencyRepository;
import tw.com.rex.accountbookservice.service.CurrencyService;
import tw.com.rex.accountbookservice.service.impl.base.BaseServiceImpl;

import javax.transaction.Transactional;

@Service
@Transactional
public class CurrencyServiceImpl extends BaseServiceImpl<CurrencyRepository, CurrencyDAO>
        implements CurrencyService {

    @Autowired
    public CurrencyServiceImpl(CurrencyRepository repository) {
        super(repository);
    }

}
