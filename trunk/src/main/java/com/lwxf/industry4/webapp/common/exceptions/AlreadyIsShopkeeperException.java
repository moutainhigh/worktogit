package com.lwxf.industry4.webapp.common.exceptions;

import com.lwxf.commons.exception.LwxfException;

/**
 * 功能：
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @create：2018/10/15 19:57
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class AlreadyIsShopkeeperException extends LwxfException {
    public AlreadyIsShopkeeperException() {
        super("已经是店主");
    }

    public AlreadyIsShopkeeperException(String message) {
        super(message);
    }

    public AlreadyIsShopkeeperException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyIsShopkeeperException(Throwable cause) {
        super(cause);
    }

    protected AlreadyIsShopkeeperException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String getErrorCode() {
        return ErrorCodes.BIZ_ALREADY_IS_SHOPKEEPER_10067;
    }
}

