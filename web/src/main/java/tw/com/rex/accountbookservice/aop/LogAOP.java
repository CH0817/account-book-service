package tw.com.rex.accountbookservice.aop;

import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Objects;

@Aspect
@Component
public class LogAOP {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Around("execution(public * tw.com.rex.accountbookservice.service..*.*(..))")
    public Object loggerServiceAround(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("service execute {}.{}", joinPoint.getTarget().getClass().getName(),
                    joinPoint.getSignature().getName());
        if (ArrayUtils.isNotEmpty(joinPoint.getArgs())) {
            Arrays.stream(joinPoint.getArgs()).forEach(
                    arg -> logger.info("service input arguments : {} ({})", arg, arg.getClass().getSimpleName()));
        }
        Object result = joinPoint.proceed();
        logger.info("service return: {}", result);
        return result;
    }

    @Around("execution(public * tw.com.rex.accountbookservice.controller..*.*(..))")
    public Object loggerControllerAround(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (Objects.nonNull(sra)) {
            HttpServletRequest request = sra.getRequest();
            String url = request.getRequestURL().toString();
            String method = request.getMethod();
            String uri = request.getRequestURI();
            String queryString = request.getQueryString();
            logger.info("controller execute, url: {}, method: {}, uri: {}, params: {}", url, method, uri, queryString);
        }
        Object result = joinPoint.proceed();
        logger.info("controller return: {}", result);
        return result;
    }

}
