package iai.xmu.geek.commom.handler;

import iai.xmu.geek.commom.exception.GeekException;
import iai.xmu.geek.commom.web.ErrorCode;
import iai.xmu.geek.commom.web.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常通用处理
 *
 * @Author: iai.xmu.edu.cn
 */
@Slf4j
@RestControllerAdvice
public class ExceptionHandlerAdvice {

    /**
     * 全局最后一级异常拦截
     *
     * @param e
     * @return
     */
    @ExceptionHandler({Throwable.class, Exception.class, RuntimeException.class})
    @ResponseBody
    public Result throwableHandler(Throwable e) {
        log.error(e.getMessage(), e);
        return Result.failure(ErrorCode.SYSTEM_ERROR, "服务器繁忙，请联系管理员");
    }

    /**
     * AIoTRuntimeException全局异常捕获，统一封装
     *
     * @param e
     * @return
     */
    @ExceptionHandler(GeekException.class)
    @ResponseBody
    public Result geekException(GeekException e) {
        log.error("业务异常:{}", e.getMessage());
        return Result.failure(e.getCode(), e.getMessage());
    }

    /**
     * 对实体类validation验证约束异常进行拦截
     *
     * @param e
     * @return
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.OK)
    public Result validException(MethodArgumentNotValidException e) {
        return bindingResult(e.getBindingResult());
    }

    @ExceptionHandler({BindException.class})
    @ResponseStatus(HttpStatus.OK)
    public Result bindException(BindException e) {
        return bindingResult(e.getBindingResult());
    }

    private Result bindingResult(BindingResult bindingResult) {
        StringBuilder builder = new StringBuilder();
        for (ObjectError error : bindingResult.getAllErrors()) {
            builder.append(error.getDefaultMessage()).append(" ");
        }
        log.warn("参数异常：{}", builder.toString());
        return Result.failure(ErrorCode.ILLEGAL_ARGUMENT, builder.toString());
    }

}
