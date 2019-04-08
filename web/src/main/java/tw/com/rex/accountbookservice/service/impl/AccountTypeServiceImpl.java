package tw.com.rex.accountbookservice.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.rex.accountbookservice.model.dao.AccountTypeDAO;
import tw.com.rex.accountbookservice.model.vo.AccountTypeVO;
import tw.com.rex.accountbookservice.repository.AccountTypeRepository;
import tw.com.rex.accountbookservice.service.AccountTypeService;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@Transactional
public class AccountTypeServiceImpl implements AccountTypeService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private AccountTypeRepository repository;

    @Autowired
    public AccountTypeServiceImpl(AccountTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public AccountTypeVO save(AccountTypeDAO entity) throws Exception {
        logger.info("entity: {}", entity);
        LocalDate now = LocalDate.now();
        entity.setCreateDate(now);
        entity.setUpdateDate(now);
        AccountTypeVO result = new AccountTypeVO();
        AccountTypeDAO dao = repository.save(entity);
        BeanUtils.copyProperties(dao, result);
        logger.info("id: {}", result.getId());
        return result;
    }

}
