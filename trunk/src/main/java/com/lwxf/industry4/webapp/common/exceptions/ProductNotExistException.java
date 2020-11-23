package com.lwxf.industry4.webapp.common.exceptions;

import com.lwxf.commons.exception.LwxfException;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/4/29/029 10:53
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class ProductNotExistException extends LwxfException {

	public ProductNotExistException() {
		super("订单产品不存在");
	}

	public ProductNotExistException(String message) {
		super(message);
	}

	public ProductNotExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProductNotExistException(Throwable cause) {
		super(cause);
	}

	protected ProductNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	@Override
	public String getErrorCode() {
		return ErrorCodes.BIZ_PRODUCT_DOES_NOT_EXIST_10089;
	}
}
