package com.lwxf.industry4.webapp.common.exceptions;

import com.lwxf.commons.exception.LwxfException;

public class BankBalanceLowException  extends LwxfException {

    public BankBalanceLowException() {
        super("该银行余额不足");
    }

    @Override
    public String getErrorCode() {
        return ErrorCodes.BIZ_BANK_NOT_FOUND_10091;
    }
}
