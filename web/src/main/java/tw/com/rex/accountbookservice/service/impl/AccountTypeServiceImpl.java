package tw.com.rex.accountbookservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.rex.accountbookservice.annotation.NecessaryData;
import tw.com.rex.accountbookservice.dao.AccountTypeDAO;
import tw.com.rex.accountbookservice.exception.NotFoundDataException;
import tw.com.rex.accountbookservice.repository.AccountTypeRepository;
import tw.com.rex.accountbookservice.service.AccountTypeService;
import tw.com.rex.accountbookservice.util.CRUDUtils;

import javax.transaction.Transactional;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Supplier;

@Service
@Transactional
public class AccountTypeServiceImpl implements AccountTypeService {

    private AccountTypeRepository repository;

    @Autowired
    public AccountTypeServiceImpl(AccountTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public AccountTypeDAO save(AccountTypeDAO entity) {
        entity.setCreateDate(LocalDate.now());
        CRUDUtils.checkNecessaryData(entity, NecessaryData.DLL.SAVE);
        return repository.save(entity);
    }

    @Override
    public boolean deleteById(String id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public AccountTypeDAO findById(String id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundDataException("cannot found by id: " + id));
    }

    @Override
    public List<AccountTypeDAO> findAll() {
        return repository.findAll();
    }

    @Override
    public AccountTypeDAO update(AccountTypeDAO entity) {
        AccountTypeDAO dao = findById(entity.getId());
        copyProperties(entity, dao, "id", "createDate");
        dao.setUpdateDate(LocalDate.now());
        CRUDUtils.checkNecessaryData(dao, NecessaryData.DLL.UPDATE);
        return repository.save(entity);
    }

    private <E> void copyProperties(E source, E target, String... ignores) {
        List<PropertyDescriptor> sourcePropertyDescriptors = getPropertyDescriptorsFromClass(source.getClass(),
                                                                                             ignores);
        sourcePropertyDescriptors//
                .forEach(p -> {
                    try {
                        Method method = target.getClass().getMethod(p.getWriteMethod().getName(), p.getPropertyType());
                        Object value = p.getReadMethod().invoke(source);
                        if (Objects.nonNull(value)) {
                            method.invoke(target, value);
                        }
                    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                });
    }

    private List<PropertyDescriptor> getPropertyDescriptorsFromClass(Class<?> clz, String... ignores) {
        return getPropertyDescriptorsFromClass(clz, ArrayList::new, ignores);
    }

    private List<PropertyDescriptor> getPropertyDescriptorsFromClass(Class<?> clz,
                                                                     Supplier<List<PropertyDescriptor>> supplier,
                                                                     String... ignores) {
        List<String> ignoreList = (Objects.nonNull(ignores)) ? Arrays.asList(ignores) : Collections.emptyList();
        List<PropertyDescriptor> result = supplier.get();
        if (!clz.getSimpleName().equalsIgnoreCase("Object")) {
            for (Field field : clz.getDeclaredFields()) {
                try {
                    String fieldName = field.getName();
                    if (!ignoreList.contains(fieldName)) {
                        result.add(new PropertyDescriptor(fieldName, clz));
                    }
                } catch (IntrospectionException e) {
                    e.printStackTrace();
                }
            }
            getPropertyDescriptorsFromClass(clz.getSuperclass(), () -> result, ignores);
        }
        return result;
    }

}
