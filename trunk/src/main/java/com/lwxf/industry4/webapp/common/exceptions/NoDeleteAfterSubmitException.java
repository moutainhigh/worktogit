package com.lwxf.industry4.webapp.common.exceptions;

import com.lwxf.commons.exception.LwxfException;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/4/1/001 14:50
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class NoDeleteAfterSubmitException extends LwxfException {
	public NoDeleteAfterSubmitException() {
		super("提交后不允许删除");
	}

	public NoDeleteAfterSubmitException(String message) {
		super(message);
	}

	public NoDeleteAfterSubmitException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoDeleteAfterSubmitException(Throwable cause) {
		super(cause);
	}

	protected NoDeleteAfterSubmitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	@Override
	public String getErrorCode() {
		return ErrorCodes.BIZ_NO_DELETION_AFTER_SUBMISSION_10079;
	}
}
