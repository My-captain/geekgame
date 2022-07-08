package iai.xmu.geek.account.exception;

import iai.xmu.geek.commom.exception.GeekException;
import iai.xmu.geek.commom.web.ErrorCode;

/**
 * 账户不存在
 *
 * @Author: iai.xmu.edu.cn

 */
public class AccountNotExistException extends GeekException {
    public AccountNotExistException(String message) {
        super(ErrorCode.BUSINESS_ERROR, message);
    }
}
