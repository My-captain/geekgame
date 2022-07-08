package iai.xmu.geek.account.exception;

import iai.xmu.geek.commom.exception.GeekException;
import iai.xmu.geek.commom.web.ErrorCode;

/**
 * 账户非法
 *
 * @Author: iai.xmu.edu.cn

 */
public class IllegalAccountException extends GeekException {
    public IllegalAccountException(String message) {
        super(ErrorCode.BUSINESS_ERROR, message);
    }
}
