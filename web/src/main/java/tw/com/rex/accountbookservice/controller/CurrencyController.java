package tw.com.rex.accountbookservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.com.rex.accountbookservice.controller.base.BaseController;
import tw.com.rex.accountbookservice.model.dao.CurrencyDAO;
import tw.com.rex.accountbookservice.service.CurrencyService;

@RestController
@RequestMapping("/currency")
public class CurrencyController extends BaseController<CurrencyService, CurrencyDAO> {

    @Autowired
    public CurrencyController(CurrencyService service) {
        super(service);
    }

}
