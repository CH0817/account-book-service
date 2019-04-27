package tw.com.rex.accountbookservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.com.rex.accountbookservice.controller.base.BaseController;
import tw.com.rex.accountbookservice.model.dao.AccountTypeDAO;
import tw.com.rex.accountbookservice.service.AccountTypeService;

@RestController
@RequestMapping("/accountType")
public class AccountTypeController extends BaseController<AccountTypeService, AccountTypeDAO> {

    @Autowired
    public AccountTypeController(AccountTypeService service) {
        super(service);
    }

}
