package tw.com.rex.accountbookservice.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import tw.com.rex.accountbookservice.define.ServerStatusCodeEnum;

import java.io.Serializable;

@SuppressWarnings("unused`")
@ApiModel(description = "API executed response")
public class ServerResponse<R> implements Serializable {

    @ApiModelProperty("status code from server executed")
    private ServerStatusCodeEnum code = ServerStatusCodeEnum.SUCCESS;
    @ApiModelProperty("response data from server")
    private R data;
    @ApiModelProperty("error message when response code are not 1")
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
