package tw.com.rex.accountbookservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.com.rex.accountbookservice.controller.base.BaseController;
import tw.com.rex.accountbookservice.dao.TradeDAO;
import tw.com.rex.accountbookservice.service.TradeService;

@RestController
@RequestMapping("/trade")
public class TradeController extends BaseController<TradeService, TradeDAO> {

    @Autowired
    public TradeController(TradeService service) {
        super(service);
    }
}
