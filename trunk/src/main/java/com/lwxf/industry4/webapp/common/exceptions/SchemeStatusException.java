package com.lwxf.industry4.webapp.common.exceptions;

import com.lwxf.commons.exception.LwxfException;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/4/2/002 9:57
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class SchemeStatusException extends LwxfException {
	public SchemeStatusException() {
		super("案例状态错误");
	}

	public SchemeStatusException(String message) {
		super(message);
	}

	public SchemeStatusException(String message, Throwable cause) {
		super(message, cause);
	}

	public SchemeStatusException(Throwable cause) {
		super(cause);
	}

	protected SchemeStatusException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	@Override
	public String getErrorCode() {
		return ErrorCodes.BIZ_SCHEME_STATUS_ERROR_10081;
	}
}
