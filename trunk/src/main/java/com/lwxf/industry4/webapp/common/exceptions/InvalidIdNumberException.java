package com.lwxf.industry4.webapp.common.exceptions;

import com.lwxf.commons.exception.LwxfException;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/4/22/022 11:09
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class InvalidIdNumberException extends LwxfException {

	public InvalidIdNumberException() {
		super("无效的身份证号");
	}

	public InvalidIdNumberException(String message) {
		super(message);
	}

	public InvalidIdNumberException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidIdNumberException(Throwable cause) {
		super(cause);
	}

	protected InvalidIdNumberException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	@Override
	public String getErrorCode() {
		return ErrorCodes.VALIDATE_INVALID_ID_NUMBER_20037;
	}
}
