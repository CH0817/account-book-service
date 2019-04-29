package tw.com.rex.accountbookservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.rex.accountbookservice.dao.CategoryDAO;
import tw.com.rex.accountbookservice.exception.RepositoryException;
import tw.com.rex.accountbookservice.repository.CategoryRepository;
import tw.com.rex.accountbookservice.service.CategoryService;
import tw.com.rex.accountbookservice.service.impl.base.BaseServiceImpl;

import java.util.Objects;

@Service
public class CategoryServiceImpl extends BaseServiceImpl<CategoryRepository, CategoryDAO> implements CategoryService {

    private CategoryRepository repository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public CategoryDAO update(CategoryDAO entity) {

        return super.update(entity);
    }

    @Override
    protected Boolean isDuplicate(CategoryDAO entity) {
        if (Objects.isNull(entity.getCategoryType())) {
            throw new RepositoryException("not set category type");
        }
        return Objects.nonNull(repository.findByNameAndCategoryType(entity.getName(), entity.getCategoryType()));
    }

}
