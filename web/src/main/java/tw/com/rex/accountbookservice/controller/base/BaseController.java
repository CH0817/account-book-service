package tw.com.rex.accountbookservice.controller.base;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tw.com.rex.accountbookservice.exception.RepositoryException;
import tw.com.rex.accountbookservice.model.dao.base.BaseDAO;
import tw.com.rex.accountbookservice.model.dao.response.ServerResponse;
import tw.com.rex.accountbookservice.service.base.BaseService;

import java.util.List;

public abstract class BaseController<S extends BaseService<E>, E extends BaseDAO> {

    private S service;

    public BaseController(S service) {
        this.service = service;
    }

    @PostMapping(path = "/save", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ServerResponse<E> save(@RequestBody E dao) {
        return new ServerResponse<>(service.save(dao));
    }

    @DeleteMapping(path = "/delete/{id}")
    public ServerResponse<Boolean> deleteById(@PathVariable Long id) {
        return new ServerResponse<>(service.deleteById(id));
    }

    @PatchMapping(path = "/update", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ServerResponse<E> update(@RequestBody E dao) {
        return new ServerResponse<>(service.update(dao));
    }

    @GetMapping("/find/{id}")
    public ServerResponse<E> findById(@PathVariable Long id) {
        return new ServerResponse<>(service.findById(id));
    }

    @GetMapping("/find/all")
    public ServerResponse<List<E>> findAll() {
        return new ServerResponse<>(service.findAll());
    }
}
