package tw.com.rex.accountbookservice.exception;

public class DataDuplicateException extends RuntimeException {

    public DataDuplicateException() { }

    public DataDuplicateException(String message) {
        super(message);
    }

    public DataDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }
}
