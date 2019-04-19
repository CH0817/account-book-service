package tw.com.rex.accountbookservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tw.com.rex.accountbookservice.exception.RepositoryException;
import tw.com.rex.accountbookservice.model.dao.CurrencyDAO;
import tw.com.rex.accountbookservice.model.dao.response.ServerResponse;
import tw.com.rex.accountbookservice.service.CurrencyService;

import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    private CurrencyService service;

    @Autowired
    public CurrencyController(CurrencyService service) {
        this.service = service;
    }

    @PostMapping(path = "/save", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ServerResponse<CurrencyDAO> save(@RequestBody CurrencyDAO dao) throws RepositoryException {
        return new ServerResponse<>(service.save(dao));
    }

    @DeleteMapping(path = "/delete/{id}")
    public ServerResponse<Boolean> deleteById(@PathVariable Long id) throws RepositoryException {
        return new ServerResponse<>(service.deleteById(id));
    }

    @PatchMapping(path = "/update", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ServerResponse<CurrencyDAO> update(@RequestBody CurrencyDAO dao) throws RepositoryException {
        return new ServerResponse<>(service.update(dao));
    }

    @GetMapping("/find/{id}")
    public ServerResponse<CurrencyDAO> findById(@PathVariable Long id) {
        return new ServerResponse<>(service.findById(id));
    }

    @GetMapping("/find/all")
    public ServerResponse<List<CurrencyDAO>> findAll() {
        return new ServerResponse<>(service.findAll());
    }

}
