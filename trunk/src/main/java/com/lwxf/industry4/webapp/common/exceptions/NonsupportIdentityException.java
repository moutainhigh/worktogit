package com.lwxf.industry4.webapp.common.exceptions;

import com.lwxf.commons.exception.LwxfException;

/**
 * 功能：
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @create：2018/10/15 20:29
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class NonsupportIdentityException extends LwxfException {

    public NonsupportIdentityException() {
        super("不支持的身份");
    }

    public NonsupportIdentityException(String message) {
        super(message);
    }

    public NonsupportIdentityException(String message, Throwable cause) {
        super(message, cause);
    }

    public NonsupportIdentityException(Throwable cause) {
        super(cause);
    }

    protected NonsupportIdentityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String getErrorCode() {
        return ErrorCodes.BIZ_NONSUPPORT_IDENTITY_10069;
    }
}

