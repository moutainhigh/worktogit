package com.lwxf.industry4.webapp.common.exceptions;

import com.lwxf.commons.exception.LwxfException;

/**
 * 功能：
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @create：2018/10/15 20:30
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class IdentityExistException extends LwxfException {
    public IdentityExistException() {
        super("身份已存在");
    }

    public IdentityExistException(String message) {
        super(message);
    }

    public IdentityExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public IdentityExistException(Throwable cause) {
        super(cause);
    }

    protected IdentityExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String getErrorCode() {
        return ErrorCodes.BIZ_IDENTITY_EXIST_10070;
    }
}

