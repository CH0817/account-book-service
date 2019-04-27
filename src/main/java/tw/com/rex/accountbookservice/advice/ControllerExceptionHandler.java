package tw.com.rex.accountbookservice.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tw.com.rex.accountbookservice.define.ServerStatusCodeEnum;
import tw.com.rex.accountbookservice.exception.DataDuplicateException;
import tw.com.rex.accountbookservice.exception.RepositoryException;
import tw.com.rex.accountbookservice.model.response.ServerResponse;

@ControllerAdvice
public class ControllerExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private ObjectMapper mapper = new ObjectMapper();

    @ExceptionHandler(value = {RepositoryException.class, DataDuplicateException.class})
    public ResponseEntity<String> repositoryExceptionHandler(RuntimeException exception) {
        showError(exception);
        return ResponseEntity.badRequest()//
                .contentType(MediaType.APPLICATION_JSON_UTF8)//
                .body(objToJson(createServerResponse(exception)));
    }

    private void showError(RuntimeException exception) {
        logger.error(exception.getMessage());
        exception.printStackTrace();
    }

    private ServerResponse createServerResponse(RuntimeException exception) {
        ServerStatusCodeEnum statusCodeEnum;
        if (exception instanceof DataDuplicateException) {
            statusCodeEnum = ServerStatusCodeEnum.DUPLICATE;
        } else {
            statusCodeEnum = ServerStatusCodeEnum.DATABASE_FAIL;
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
