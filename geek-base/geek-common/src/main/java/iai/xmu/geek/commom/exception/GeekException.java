package iai.xmu.geek.commom.exception;

import iai.xmu.geek.commom.web.IErrorCode;
import lombok.Getter;

/**
 * @Author: iai.xmu.edu.cn
 */
@Getter
public class GeekException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final IErrorCode code;

    public GeekException(IErrorCode code, String message) {
        super(message);
        this.code = code;
    }

    public GeekException(IErrorCode code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}
