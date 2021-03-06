package tw.com.rex.accountbookservice.define;

public enum ServerStatusCodeEnum {
    SUCCESS(1, "success"),//
    DUPLICATE(997, "data is duplicate"),//
    DATABASE_FAIL(998, "execute SQL fail"),//
    FAIL(999, "fail");
    private Integer code;
    private String message;

    ServerStatusCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
