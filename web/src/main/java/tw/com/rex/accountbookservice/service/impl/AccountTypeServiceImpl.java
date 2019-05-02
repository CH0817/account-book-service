package tw.com.rex.accountbookservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.com.rex.accountbookservice.annotation.NecessaryData;
import tw.com.rex.accountbookservice.dao.AccountTypeDAO;
import tw.com.rex.accountbookservice.exception.LackNecessaryDataException;
import tw.com.rex.accountbookservice.repository.AccountTypeRepository;
import tw.com.rex.accountbookservice.service.AccountTypeService;

import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class AccountTypeServiceImpl implements AccountTypeService {

    private AccountTypeRepository repository;

    @Autowired
    public AccountTypeServiceImpl(AccountTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public AccountTypeDAO save(AccountTypeDAO entity) throws Exception {
        AccountTypeDAO dao = null;
        if (checkNecessaryData(entity, NecessaryData.DLL.SAVE)) {
            dao = repository.save(entity);
        }
        return dao;
    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }

    @Override
    public AccountTypeDAO findById(String id) {
        return null;
    }

    @Override
    public List<AccountTypeDAO> findAll() {
        return null;
    }

    @Override
    public AccountTypeDAO update(AccountTypeDAO entity) {
        return null;
    }

    private boolean checkNecessaryData(AccountTypeDAO entity, NecessaryData.DLL useIn)
            throws LackNecessaryDataException, NoSuchMethodException, InvocationTargetException,
                   IllegalAccessException {
        Class<?> clz = entity.getClass();
        Class<?> superclass = entity.getClass().getSuperclass();
        System.out.println(superclass);
        Field[] fields = clz.getDeclaredFields();
        for (Field field : fields) {
            NecessaryData annotation = field.getAnnotation(NecessaryData.class);
            if (Objects.nonNull(annotation)) {
                NecessaryData.DLL[] dlls = annotation.useIn();
                if (Arrays.asList(dlls).contains(useIn)) {
                    String name = field.getName();
                    String getter = "get" + Character.toUpperCase(name.charAt(0)) + name.substring(1);
                    Method method = clz.getMethod(getter);
                    Object invoke = method.invoke(entity);
                    if (Objects.isNull(invoke)) {
                        throw new LackNecessaryDataException(name + " must have value");
                    }
                }
            }
        }
        return true;
    }
}
