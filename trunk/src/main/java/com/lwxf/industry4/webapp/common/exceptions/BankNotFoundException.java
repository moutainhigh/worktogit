package com.lwxf.industry4.webapp.common.exceptions;

import com.lwxf.commons.exception.LwxfException;

public class BankNotFoundException  extends LwxfException {

    public BankNotFoundException() {
        super("该银行不存在");
    }

    @Override
    public String getErrorCode() {
        return ErrorCodes.BIZ_BANK_NOT_FOUND_10090;
    }
}
