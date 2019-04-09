package tw.com.rex.accountbookservice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        cause.printStackTrace();
        logger.error(message);
    }
}
