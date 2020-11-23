package com.lwxf.industry4.webapp.common.exceptions;

import com.lwxf.commons.exception.LwxfException;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2019/1/7/007 15:52
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class CreditLowException extends LwxfException {
	public CreditLowException() {
		super("商品未下架不可修改");
	}

	public CreditLowException(String message) {
		super(message);
	}

	public CreditLowException(String message, Throwable cause) {
		super(message, cause);
	}

	public CreditLowException(Throwable cause) {
		super(cause);
	}

	protected CreditLowException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	@Override
	public String getErrorCode() {
		return ErrorCodes.BIZ_CREDIT_IS_LOW_10075;
	}
}
