package com.lwxf.industry4.webapp.common.exceptions;

import com.lwxf.commons.exception.LwxfException;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/9/28/028 15:06
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class RepeatAttentionException extends LwxfException {
	public RepeatAttentionException() {
		super("重复关注");
	}

	public RepeatAttentionException(String message) {
		super(message);
	}

	public RepeatAttentionException(String message, Throwable cause) {
		super(message, cause);
	}

	public RepeatAttentionException(Throwable cause) {
		super(cause);
	}

	protected RepeatAttentionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	@Override
	public String getErrorCode() {
		return ErrorCodes.BIZ_REPEAT_ATTENTION_10066;
	}
}
