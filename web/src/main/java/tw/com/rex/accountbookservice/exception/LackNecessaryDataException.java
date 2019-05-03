package tw.com.rex.accountbookservice.exception;

import tw.com.rex.accountbookservice.model.define.ServerStatusCodeEnum;

public class LackNecessaryDataException extends RuntimeException {

    private ServerStatusCodeEnum statusCode;

    public LackNecessaryDataException(String message, ServerStatusCodeEnum statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public ServerStatusCodeEnum getStatusCode() {
        return statusCode;
    }
}
