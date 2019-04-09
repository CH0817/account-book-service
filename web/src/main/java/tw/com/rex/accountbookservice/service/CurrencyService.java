package tw.com.rex.accountbookservice.service;

import tw.com.rex.accountbookservice.exception.RepositoryException;
import tw.com.rex.accountbookservice.model.dao.CurrencyDAO;
import tw.com.rex.accountbookservice.model.vo.CurrencyVO;

import java.util.List;

public interface CurrencyService {

    CurrencyVO save(CurrencyDAO entity) throws RepositoryException;

    boolean deleteById(long id) throws RepositoryException;

    CurrencyVO findById(long id);

    List<CurrencyVO> findAll();

    CurrencyVO update(CurrencyDAO entity) throws RepositoryException;
}
