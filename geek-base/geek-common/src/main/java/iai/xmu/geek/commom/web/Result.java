package iai.xmu.geek.commom.web;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 统一返回结构
 *
 * @author iai.xmu.edu.cn
 */
@Getter
@Setter
@ToString
@ApiModel("接口返回对象")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> implements Serializable {

    public interface ResultView {
    }

    private static final long serialVersionUID = -4696008537295855861L;

    @JsonView({ResultView.class})
    @ApiModelProperty(value = "响应数据")
    private T data;

    @JsonView({ResultView.class})
    @ApiModelProperty(value = "响应状态码")
    private String code;

    @JsonView({ResultView.class})
    @ApiModelProperty(value = "提示信息")
    private String msg;

    @JsonIgnore
    @JSONField(serialize = false)
    private boolean success;

    public Result() {
    }

    public Result(T data, String code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Result<T> success() {
        return successWith(null, ErrorCode.SUCCESS, null);
    }

    public static <T> Result<T> success(T data) {
        return successWith(data, ErrorCode.SUCCESS, null);
    }

    public static <T> Result<T> success(T data, String msg) {
        return successWith(data, ErrorCode.SUCCESS, msg);
    }

    public static <T> Result<T> successWith(T data, IErrorCode code, String msg) {
        return new Result<T>(data, code.code(), msg);
    }

    public static <T> Result<T> successWith(T data, String code, String msg) {
        return new Result<T>(data, code, msg);
    }

    public static <T> Result<T> failure(String msg) {
        return failureWith(null, ErrorCode.BUSINESS_ERROR, msg);
    }

    public static <T> Result<T> failure(IErrorCode code, String msg) {
        return failureWith(null, code, msg);
    }

    public static <T> Result<T> failure(T data, IErrorCode code, String msg) {
        return failureWith(data, code, msg);
    }

    public static <T> Result<T> failureWith(T data, IErrorCode code, String msg) {
        return new Result<T>(data, code.code(), msg);
    }

    public static <T> Result<T> failureWith(T data, String code, String msg) {
        return new Result<T>(data, code, msg);
    }

    public static String successWithJson(IErrorCode code, String msg) {
        return JSONObject.toJSONString(successWith(null, code, msg));
    }

    public static String failureWithJson(IErrorCode code, String msg) {
        return JSONObject.toJSONString(failureWith(null, code, msg));
    }

    public boolean isSuccess() {
        return ErrorCode.SUCCESS.code().equals(this.code);
    }

}
