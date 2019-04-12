package tw.com.rex.accountbookservice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "repository error")
public class RepositoryException extends RuntimeException {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public RepositoryException() {
    }

    public RepositoryException(String message) {
        super(message);
        logger.error(message);
    }

    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
        logger.error(message);
        cause.printStackTrace();
    }
}
