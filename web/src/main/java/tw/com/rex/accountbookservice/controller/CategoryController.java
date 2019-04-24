package tw.com.rex.accountbookservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.com.rex.accountbookservice.controller.base.BaseController;
import tw.com.rex.accountbookservice.model.dao.CategoryDAO;
import tw.com.rex.accountbookservice.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController extends BaseController<CategoryService, CategoryDAO> {

    @Autowired
    public CategoryController(CategoryService service) {
        super(service);
    }

}
