package com.lwxf.industry4.webapp.common.exceptions;

import com.lwxf.commons.exception.LwxfException;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/30/030 10:10
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class ProductExistException extends LwxfException {

	public ProductExistException() {
		super("产品已存在");
	}

	public ProductExistException(String message) {
		super(message);
	}

	public ProductExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProductExistException(Throwable cause) {
		super(cause);
	}

	protected ProductExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	@Override
	public String getErrorCode() {
		return ErrorCodes.BIZ_PRODUCTS_ALREADY_EXIST_10074;
	}
}
