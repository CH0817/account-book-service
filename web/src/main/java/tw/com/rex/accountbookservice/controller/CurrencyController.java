package tw.com.rex.accountbookservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tw.com.rex.accountbookservice.exception.RepositoryException;
import tw.com.rex.accountbookservice.model.dao.AccountTypeDAO;
import tw.com.rex.accountbookservice.model.dao.CurrencyDAO;
import tw.com.rex.accountbookservice.service.AccountTypeService;
import tw.com.rex.accountbookservice.service.CurrencyService;

import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private CurrencyService service;

    @Autowired
    public CurrencyController(CurrencyService service) {
        this.service = service;
    }

    @PostMapping(path = "/save", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public CurrencyDAO save(@RequestBody CurrencyDAO dao) throws RepositoryException {
        return service.save(dao);
    }

    @DeleteMapping(path = "/delete/{id}")
    public boolean deleteById(@PathVariable Long id) throws RepositoryException {
        return service.deleteById(id);
    }

    @PatchMapping(path = "/update", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public CurrencyDAO update(@RequestBody CurrencyDAO dao) throws RepositoryException {
        return service.update(dao);
    }

    @GetMapping("/find/{id}")
    public CurrencyDAO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/find/all")
    public List<CurrencyDAO> findAll() {
        return service.findAll();
    }

}
