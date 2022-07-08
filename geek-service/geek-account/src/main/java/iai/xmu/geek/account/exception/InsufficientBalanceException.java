package iai.xmu.geek.account.exception;

import iai.xmu.geek.commom.exception.GeekException;
import iai.xmu.geek.commom.web.ErrorCode;

/**
 * 余额不足
 *
 * @Author: iai.xmu.edu.cn

 */
public class InsufficientBalanceException extends GeekException {
    public InsufficientBalanceException(String message) {
        super(ErrorCode.BUSINESS_ERROR, message);
    }
}
