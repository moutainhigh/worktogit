package com.lwxf.industry4.webapp.common.exceptions;

import com.lwxf.commons.exception.LwxfException;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/7/18/018 9:37
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class NoRepetitionAllowedException extends LwxfException {

	public NoRepetitionAllowedException() {
		super("资源存在 不允许重复新增");
	}

	public NoRepetitionAllowedException(String message) {
		super(message);
	}

	public NoRepetitionAllowedException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoRepetitionAllowedException(Throwable cause) {
		super(cause);
	}

	protected NoRepetitionAllowedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	@Override
	public String getErrorCode() {
		return ErrorCodes.BIZ_NO_REPETITION_ALLOWED_10094;
	}
}
