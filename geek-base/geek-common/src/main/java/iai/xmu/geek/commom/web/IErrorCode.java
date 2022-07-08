package iai.xmu.geek.commom.web;

import java.io.Serializable;

/**
 * 异常码接口，所有的异常码都需要实现这个接口
 *
 * @Author: iai.xmu.edu.cn

 */
public interface IErrorCode extends Serializable {

    /**
     * 状态码
     *
     * @return
     */
    String code();

    /**
     * 状态码描述
     *
     * @return
     */
    String message();
}
