package tw.com.rex.accountbookservice.aop;

import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ServiceLogAOP {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(public * tw.com.rex.accountbookservice.service..*.*(..))")
    public void serviceLog() {}

    @Before("serviceLog()")
    public void beforeLog(JoinPoint joinPoint) {
        logger.info("execute {}.{}", joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName());
        if (ArrayUtils.isNotEmpty(joinPoint.getArgs())) {
            Arrays.stream(joinPoint.getArgs()).forEach(
                    arg -> logger.info("input arguments : {} ({})", arg, arg.getClass().getSimpleName()));
        }
    }

}
