package tw.com.rex.accountbookservice.exception;

public class NotFoundDataException extends RuntimeException {

    public NotFoundDataException(String message) {
        super(message);
    }

}
