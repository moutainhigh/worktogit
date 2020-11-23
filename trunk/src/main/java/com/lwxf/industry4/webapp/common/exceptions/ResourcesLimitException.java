package com.lwxf.industry4.webapp.common.exceptions;

import com.lwxf.commons.exception.LwxfException;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2019/1/18/018 11:05
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class ResourcesLimitException extends LwxfException {
	public ResourcesLimitException() {
		super("资源超过最大限制");
	}

	public ResourcesLimitException(String message) {
		super(message);
	}

	public ResourcesLimitException(String message, Throwable cause) {
		super(message, cause);
	}

	public ResourcesLimitException(Throwable cause) {
		super(cause);
	}

	protected ResourcesLimitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	@Override
	public String getErrorCode() {
		return ErrorCodes.BIZ_RESOURCES_LIMIT_10076;
	}
}
