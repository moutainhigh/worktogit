package com.lwxf.industry4.webapp.common.exceptions;

import com.lwxf.commons.exception.LwxfException;

public class DealerAccountBalanceLowException extends LwxfException {

    public DealerAccountBalanceLowException() {
        super("该经销商账户余额不足");
    }

    @Override
    public String getErrorCode() {
        return ErrorCodes.BIZ_DEALERACCOUNT_LOW_10095;
    }
}
