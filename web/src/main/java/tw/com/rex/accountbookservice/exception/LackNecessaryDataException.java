package tw.com.rex.accountbookservice.exception;

public class LackNecessaryDataException extends Exception {

    public LackNecessaryDataException(String message) {
        super(message);
    }

}
