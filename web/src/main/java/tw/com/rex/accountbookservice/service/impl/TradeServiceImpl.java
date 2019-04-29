package tw.com.rex.accountbookservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.rex.accountbookservice.dao.TradeDAO;
import tw.com.rex.accountbookservice.repository.TradeRepository;
import tw.com.rex.accountbookservice.service.TradeService;
import tw.com.rex.accountbookservice.service.impl.base.BaseServiceImpl;

@Service
public class TradeServiceImpl extends BaseServiceImpl<TradeRepository, TradeDAO> implements TradeService {

    @Autowired
    public TradeServiceImpl(TradeRepository repository) {
        super(repository);
    }

    @Override
    protected Boolean isDuplicate(TradeDAO entity) {
        // always false
        return Boolean.FALSE;
    }
}
