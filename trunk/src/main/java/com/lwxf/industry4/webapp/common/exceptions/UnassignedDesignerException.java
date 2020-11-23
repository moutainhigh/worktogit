package com.lwxf.industry4.webapp.common.exceptions;

import com.lwxf.commons.exception.LwxfException;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/4/1/001 14:33
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class UnassignedDesignerException extends LwxfException {
	public UnassignedDesignerException() {
		super("未分配设计师");
	}

	public UnassignedDesignerException(String message) {
		super(message);
	}

	public UnassignedDesignerException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnassignedDesignerException(Throwable cause) {
		super(cause);
	}

	protected UnassignedDesignerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	@Override
	public String getErrorCode() {
		return ErrorCodes.BIZ_NO_DELETION_AFTER_SUBMISSION_10079;
	}
}
