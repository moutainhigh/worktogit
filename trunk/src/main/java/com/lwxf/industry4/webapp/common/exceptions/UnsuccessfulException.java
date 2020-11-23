package com.lwxf.industry4.webapp.common.exceptions;

import com.lwxf.commons.exception.LwxfException;

/**
 * 功能：
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @create：2018/10/15 20:09
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class UnsuccessfulException extends LwxfException {
    public UnsuccessfulException() {
        super("申请失败");
    }

    public UnsuccessfulException(String message) {
        super(message);
    }

    public UnsuccessfulException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnsuccessfulException(Throwable cause) {
        super(cause);
    }

    protected UnsuccessfulException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String getErrorCode() {
        return ErrorCodes.BIZ_UNSUCCESSFUL_10068;
    }
}

