package iai.xmu.geek.commom.web;

/**
 * 全局异常码
 * 统一格式：ABBCCC
 * A：错误级别：1-系统错误；2-业务错误
 * B：项目或模块名称，从01开始编号
 * C：具体错误号，从001开始编号，自增即可
 *
 * @Author: iai.xmu.edu.cn

 */
public enum ErrorCode implements IErrorCode {

    /**
     * 成功
     */
    SUCCESS("000000", "Success."),
    /**
     * 系统错误
     */
    SYSTEM_ERROR("100000", "System Error."),
    /**
     * 工具类错误
     */
    TOOLKIT_ERROR("100001", "Toolkit Error."),
    /**
     * JSON转换错误
     */
    JSON_PARSE_ERROR("100002", "Json parse error."),
    /**
     * 业务异常
     */
    BUSINESS_ERROR("100003", "Business Error."),
    /**
     * License校验异常
     */
    LICENSE_VALID_ERROR("100004", "License Valid Error."),
    /**
     * 文件处理异常
     */
    FILE_ERROR("100005", "File Exception."),
    /**
     * 未认证异常
     */
    NO_LOGIN("100006", "Not Login Exception."),
    /**
     * 初始化异常
     */
    INIT_ERROR("100007", "Init Error."),
    /**
     * 连接异常
     */
    CONNECTION_ERROR("100008","Connection Error."),

    /**
     * 服务器运行时异常
     */
    RUNTIME_ERROR("100009","Server RunTime Error."),
    /**
     * 参数异常
     */
    ILLEGAL_ARGUMENT("200001", "Illegal Argument Error."),
    /**
     * 响应异常
     */
    RESPONSE_ERROR("200002", "Response Error.");

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private String code;
    private String message;

    @Override
    public String code() {
        return this.code;
    }

    @Override
    public String message() {
        return this.message;
    }


}
