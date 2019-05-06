package tw.com.rex.accountbookservice.util;

import org.apache.commons.lang3.StringUtils;
import tw.com.rex.accountbookservice.annotation.NecessaryData;
import tw.com.rex.accountbookservice.dao.base.BaseDAO;
import tw.com.rex.accountbookservice.exception.LackNecessaryDataException;
import tw.com.rex.accountbookservice.model.define.ServerStatusCodeEnum;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Objects;

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

}
