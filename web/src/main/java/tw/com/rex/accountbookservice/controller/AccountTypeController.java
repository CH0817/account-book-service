package tw.com.rex.accountbookservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tw.com.rex.accountbookservice.exception.RepositoryException;
import tw.com.rex.accountbookservice.model.dao.AccountTypeDAO;
import tw.com.rex.accountbookservice.model.dao.response.ServerResponse;
import tw.com.rex.accountbookservice.service.AccountTypeService;

import java.util.List;

@RestController
@RequestMapping("/accountType")
public class AccountTypeController {

    private AccountTypeService service;

    @Autowired
    public AccountTypeController(AccountTypeService service) {
        this.service = service;
    }

    @PostMapping(path = "/save", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ServerResponse<AccountTypeDAO> save(@RequestBody AccountTypeDAO dao) throws RepositoryException {
        return new ServerResponse<>(service.save(dao));
    }

    @DeleteMapping(path = "/delete/{id}")
    public ServerResponse<Boolean> deleteById(@PathVariable Long id) throws RepositoryException {
        return new ServerResponse<>(service.deleteById(id));
    }

    @PatchMapping(path = "/update", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ServerResponse<AccountTypeDAO> update(@RequestBody AccountTypeDAO dao) throws RepositoryException {
        return new ServerResponse<>(service.update(dao));
    }

    @GetMapping("/find/{id}")
    public ServerResponse<AccountTypeDAO> findById(@PathVariable Long id) {
        return new ServerResponse<>(service.findById(id));
    }

    @GetMapping("/find/all")
    public ServerResponse<List<AccountTypeDAO>> findAll() {
        return new ServerResponse<>(service.findAll());
    }

}
