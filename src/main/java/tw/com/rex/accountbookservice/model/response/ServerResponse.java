package tw.com.rex.accountbookservice.model.response;

import tw.com.rex.accountbookservice.define.ServerStatusCodeEnum;

import java.io.Serializable;

@SuppressWarnings("unused")
public class ServerResponse<R> implements Serializable {

    private ServerStatusCodeEnum code = ServerStatusCodeEnum.SUCCESS;
    private R data;
    private String errorMessage;

    public ServerResponse() { }

    public ServerResponse(ServerStatusCodeEnum code) {
        this.code = code;
    }

    public ServerResponse(R data) {
        this.data = data;
    }

    public ServerResponse(ServerStatusCodeEnum code, R data) {
        this.code = code;
        this.data = data;
    }

    public ServerResponse(ServerStatusCodeEnum code, String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public Integer getCode() {
        return code.getCode();
    }

    public void setCode(ServerStatusCodeEnum code) {
        this.code = code;
    }

    public R getData() {
        return data;
    }

    public final void setData(R data) {
        this.data = data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
