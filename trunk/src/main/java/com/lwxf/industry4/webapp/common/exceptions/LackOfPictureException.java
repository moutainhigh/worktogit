package com.lwxf.industry4.webapp.common.exceptions;

import com.lwxf.commons.exception.LwxfException;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/9/27/027 15:52
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class LackOfPictureException extends LwxfException {
	public LackOfPictureException() {
		super("商品图片缺失");
	}

	public LackOfPictureException(String message) {
		super(message);
	}

	public LackOfPictureException(String message, Throwable cause) {
		super(message, cause);
	}

	public LackOfPictureException(Throwable cause) {
		super(cause);
	}

	protected LackOfPictureException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	@Override
	public String getErrorCode() {
		return ErrorCodes.BIZ_LACK_OF_PICTURE_10065;
	}
}
