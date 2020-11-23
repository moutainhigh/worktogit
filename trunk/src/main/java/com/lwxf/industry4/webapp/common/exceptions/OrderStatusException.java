package com.lwxf.industry4.webapp.common.exceptions;

import com.lwxf.commons.exception.LwxfException;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/4/1/001 13:53
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class OrderStatusException extends LwxfException {
	public OrderStatusException() {
		super("订单状态错误");
	}

	public OrderStatusException(String message) {
		super(message);
	}

	public OrderStatusException(String message, Throwable cause) {
		super(message, cause);
	}

	public OrderStatusException(Throwable cause) {
		super(cause);
	}

	protected OrderStatusException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	@Override
	public String getErrorCode() {
		return ErrorCodes.BIZ_ORDER_STATUS_ERROR_10077;
	}
}
