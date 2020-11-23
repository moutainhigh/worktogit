package com.lwxf.industry4.webapp.common.exceptions;

import com.lwxf.commons.exception.LwxfException;

/**
 * 功能：
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @create：2018/10/15 20:31
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class IdentityOnlyOneCompanyException extends LwxfException {
    public IdentityOnlyOneCompanyException() {
        super("身份只能在同一家公司下");
    }

    public IdentityOnlyOneCompanyException(String message) {
        super(message);
    }

    public IdentityOnlyOneCompanyException(String message, Throwable cause) {
        super(message, cause);
    }

    public IdentityOnlyOneCompanyException(Throwable cause) {
        super(cause);
    }

    protected IdentityOnlyOneCompanyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String getErrorCode() {
        return ErrorCodes.BIZ_IDENTITY_ONLY_ONE_COMPANY_10071;
    }
}

