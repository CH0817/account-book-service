package tw.com.rex.accountbookservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.com.rex.accountbookservice.controller.base.BaseController;
import tw.com.rex.accountbookservice.dao.ItemDAO;
import tw.com.rex.accountbookservice.service.ItemService;

@RestController
@RequestMapping("/item")
public class ItemController extends BaseController<ItemService, ItemDAO> {

    @Autowired
    public ItemController(ItemService service) {
        super(service);
    }

}
