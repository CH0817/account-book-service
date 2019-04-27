package tw.com.rex.accountbookservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.rex.accountbookservice.exception.RepositoryException;
import tw.com.rex.accountbookservice.model.dao.ItemDAO;
import tw.com.rex.accountbookservice.repoistory.ItemRepository;
import tw.com.rex.accountbookservice.service.ItemService;
import tw.com.rex.accountbookservice.service.impl.base.BaseServiceImpl;

import java.util.Objects;

@Service
public class ItemServiceImpl extends BaseServiceImpl<ItemRepository, ItemDAO> implements ItemService {

    private ItemRepository repository;

    @Autowired
    public ItemServiceImpl(ItemRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    protected Boolean isDuplicate(ItemDAO entity) {
        if (Objects.isNull(entity.getCategory())) {
            throw new RepositoryException("not set category");
        }
        return Objects.nonNull(repository.findByNameAndCategory(entity.getName(), entity.getCategory()));
    }

}
