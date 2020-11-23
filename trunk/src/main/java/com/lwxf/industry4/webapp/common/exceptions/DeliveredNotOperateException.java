package com.lwxf.industry4.webapp.common.exceptions;

import com.lwxf.commons.exception.LwxfException;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/4/2/002 10:16
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class DeliveredNotOperateException extends LwxfException {
	public DeliveredNotOperateException() {
		super("已发货不允许操作");
	}

	public DeliveredNotOperateException(String message) {
		super(message);
	}

	public DeliveredNotOperateException(String message, Throwable cause) {
		super(message, cause);
	}

	public DeliveredNotOperateException(Throwable cause) {
		super(cause);
	}

	protected DeliveredNotOperateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	@Override
	public String getErrorCode() {
		return ErrorCodes.BIZ_DELIVERED_NOT_OPERATE_10082;
	}
}
