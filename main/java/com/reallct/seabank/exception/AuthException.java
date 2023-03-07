package com.reallct.seabank.exception;

/**
 * 身份权限异常
 *
 * @author ahuang
 * @version V1.0
 * @Title: AuthException
 * @Program: bookCornerServer
 * @Package com.reallct.seabank.exception
 * @create 2018-07-07 22:10
 */
public class AuthException extends BaseException{
    public AuthException(String code, String msg) {
        super(code, msg);
    }
}
