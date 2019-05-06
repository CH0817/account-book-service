package tw.com.rex.accountbookservice.util;

import org.apache.commons.lang3.StringUtils;
import tw.com.rex.accountbookservice.annotation.NecessaryData;
import tw.com.rex.accountbookservice.dao.base.BaseDAO;
import tw.com.rex.accountbookservice.exception.LackNecessaryDataException;
import tw.com.rex.accountbookservice.model.define.ServerStatusCodeEnum;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Supplier;

public class CRUDUtils {

    public static <E extends BaseDAO> void checkNecessaryData(E entity, NecessaryData.DLL useIn) {
        checkNecessaryData(entity, entity.getClass(), useIn);
    }

    private static void checkNecessaryData(Object entity, Class<?> clz, NecessaryData.DLL useIn) {
        if (!clz.getSimpleName().equalsIgnoreCase("Object")) {
            Field[] fields = clz.getDeclaredFields();
            for (Field field : fields) {
                NecessaryData annotation = field.getAnnotation(NecessaryData.class);
                if (Objects.nonNull(annotation) && Arrays.asList(annotation.useIn()).contains(useIn)) {
                    String fieldName = field.getName();
                    String getter = "get" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
                    try {
                        Object value = clz.getMethod(getter).invoke(entity);
                        String typeName = field.getType().getSimpleName();
                        if (Objects.isNull(value) || typeName.equalsIgnoreCase("String") && StringUtils.isBlank(
                                (String) value)) {
                            throw new LackNecessaryDataException(fieldName + " must have value",
                                                                 ServerStatusCodeEnum.LACK_NECESSARY_DATA);
                        }
                    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
            checkNecessaryData(entity, clz.getSuperclass(), useIn);
        }
    }

    public static <E> void copyProperties(E source, E target, String... ignores) {
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

    private static List<PropertyDescriptor> getPropertyDescriptorsFromClass(Class<?> clz, String... ignores) {
        return getPropertyDescriptorsFromClass(clz, ArrayList::new, ignores);
    }

    private static List<PropertyDescriptor> getPropertyDescriptorsFromClass(Class<?> clz,
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
