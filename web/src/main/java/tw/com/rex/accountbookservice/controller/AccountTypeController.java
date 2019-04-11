package tw.com.rex.accountbookservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import tw.com.rex.accountbookservice.exception.RepositoryException;
import tw.com.rex.accountbookservice.model.dao.AccountTypeDAO;
import tw.com.rex.accountbookservice.service.AccountTypeService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/accountType")
public class AccountTypeController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private AccountTypeService service;

    @Autowired
    public AccountTypeController(AccountTypeService service) {
        this.service = service;
    }

    @PostMapping(path = "/save", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public AccountTypeDAO save(@RequestBody AccountTypeDAO dao, HttpServletResponse response) {
        try {
            return service.save(dao);
        } catch (RepositoryException e) {
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return null;
        }
    }

}
