package tw.com.rex.accountbookservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.com.rex.accountbookservice.controller.base.BaseController;
import tw.com.rex.accountbookservice.model.dao.AccountDAO;
import tw.com.rex.accountbookservice.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController extends BaseController<AccountService, AccountDAO> {

    @Autowired
    public AccountController(AccountService service) {
        super(service);
    }
}
