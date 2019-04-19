package tw.com.rex.accountbookservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.rex.accountbookservice.model.dao.CategoryDAO;
import tw.com.rex.accountbookservice.repository.CategoryRepository;
import tw.com.rex.accountbookservice.service.CategoryService;
import tw.com.rex.accountbookservice.service.impl.base.BaseServiceImpl;

@Service
public class CategoryServiceImpl extends BaseServiceImpl<CategoryRepository, CategoryDAO> implements CategoryService {

    @Autowired
    public CategoryServiceImpl(CategoryRepository repository) {
        super(repository);
    }
}
