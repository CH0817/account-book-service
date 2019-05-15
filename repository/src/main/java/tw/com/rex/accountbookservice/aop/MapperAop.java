package tw.com.rex.accountbookservice.aop;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import tw.com.rex.accountbookservice.annotation.NecessaryData;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Aspect
@Component
public class MapperAop {

    @Around("execution(public * tw.com.rex.accountbookservice.mapper..*.insert*(..))")
    public Object insertCheckNecessaryData(ProceedingJoinPoint joinPoint) throws Throwable {
        return checkNecessaryData(joinPoint, NecessaryData.DLL.INSERT);
    }

    @Around("execution(public * tw.com.rex.accountbookservice.mapper..*.update*(..))")
    public Object updateCheckNecessaryData(ProceedingJoinPoint joinPoint) throws Throwable {
        return checkNecessaryData(joinPoint, NecessaryData.DLL.UPDATE);
    }

    private Object checkNecessaryData(ProceedingJoinPoint joinPoint, NecessaryData.DLL use) throws Throwable {
        Object entity = joinPoint.getArgs()[0];
        Set<String> nullFields = new HashSet<>();
        Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (Arrays.asList(field.getAnnotation(NecessaryData.class).useIn()).contains(use)) {
                String getter = "get" + Character.toUpperCase(field.getName().charAt(0)) + field.getName().substring(1);
                Method method = entity.getClass().getMethod(getter);
                Object value = method.invoke(entity);
                String typeName = field.getType().getSimpleName();
                if (Objects.isNull(value) || typeName.equalsIgnoreCase("String") && StringUtils.isBlank(
                        (String) value)) {
                    nullFields.add(field.getName());
                }
            }
        }
        if (CollectionUtils.isEmpty(nullFields)) {
            return joinPoint.proceed();
        } else {
            throw new DataIntegrityViolationException(nullFields.stream().collect(
                    Collectors.joining(", ", "field: ", "; value cannot be null or empty")));
        }
    }

}
