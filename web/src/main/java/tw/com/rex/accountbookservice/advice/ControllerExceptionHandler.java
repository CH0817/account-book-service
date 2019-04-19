package tw.com.rex.accountbookservice.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tw.com.rex.accountbookservice.exception.RepositoryException;
import tw.com.rex.accountbookservice.model.dao.define.ServerStatusCodeEnum;
import tw.com.rex.accountbookservice.model.dao.response.ServerResponse;

@ControllerAdvice
public class ControllerExceptionHandler {

    private ObjectMapper mapper = new ObjectMapper();

    @ExceptionHandler(value = {RepositoryException.class})
    public ResponseEntity<String> repositoryExceptionHandler(RepositoryException exception) {
        return ResponseEntity.badRequest()//
                .contentType(MediaType.APPLICATION_JSON_UTF8)//
                .body(objToJson(new ServerResponse(ServerStatusCodeEnum.DATABASE_FAIL, exception.getMessage())));
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
