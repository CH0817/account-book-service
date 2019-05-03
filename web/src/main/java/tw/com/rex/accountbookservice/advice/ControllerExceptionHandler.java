package tw.com.rex.accountbookservice.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tw.com.rex.accountbookservice.exception.DataDuplicateException;
import tw.com.rex.accountbookservice.exception.LackNecessaryDataException;
import tw.com.rex.accountbookservice.exception.NotFoundDataException;
import tw.com.rex.accountbookservice.exception.RepositoryException;
import tw.com.rex.accountbookservice.model.define.ServerStatusCodeEnum;
import tw.com.rex.accountbookservice.model.response.ServerResponse;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@ControllerAdvice
public class ControllerExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private ObjectMapper mapper = new ObjectMapper();

    @ExceptionHandler(value = {RepositoryException.class, DataDuplicateException.class, NotFoundDataException.class,
                               LackNecessaryDataException.class})
    public ResponseEntity<String> repositoryExceptionHandler(RuntimeException exception) {
        showError(exception);
        return ResponseEntity.badRequest()//
                .contentType(MediaType.APPLICATION_JSON_UTF8)//
                .body(objToJson(createServerResponse(exception)));
    }

    private void showError(Exception exception) {
        logger.error(exception.getMessage());
        exception.printStackTrace();
    }

    private ServerResponse createServerResponse(RuntimeException exception) {
        Class<? extends RuntimeException> clz = exception.getClass();
        ServerStatusCodeEnum statusCodeEnum = null;
        try {
            Method method = clz.getMethod("getStatusCode");
            statusCodeEnum = (ServerStatusCodeEnum) method.invoke(exception);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            showError(e);
        }
        return new ServerResponse(statusCodeEnum, exception.getMessage());
    }

    private String objToJson(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

}
