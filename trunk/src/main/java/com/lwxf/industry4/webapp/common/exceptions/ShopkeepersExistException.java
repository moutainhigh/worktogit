package com.lwxf.industry4.webapp.common.exceptions;

import com.lwxf.commons.exception.LwxfException;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/4/2/002 9:32
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class ShopkeepersExistException extends LwxfException {
	public ShopkeepersExistException() {
		super("店主已存在");
	}

	public ShopkeepersExistException(String message) {
		super(message);
	}

	public ShopkeepersExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public ShopkeepersExistException(Throwable cause) {
		super(cause);
	}

	protected ShopkeepersExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	@Override
	public String getErrorCode() {
		return ErrorCodes.BIZ_SHOPKEEPERS_ALREADY_EXIST_10080;
	}
}
