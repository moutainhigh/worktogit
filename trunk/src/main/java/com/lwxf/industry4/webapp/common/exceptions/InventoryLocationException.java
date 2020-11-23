package com.lwxf.industry4.webapp.common.exceptions;

import com.lwxf.commons.exception.LwxfException;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/4/2/002 10:40
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class InventoryLocationException extends LwxfException {
	public InventoryLocationException() {
		super("库存位置错误");
	}

	public InventoryLocationException(String message) {
		super(message);
	}

	public InventoryLocationException(String message, Throwable cause) {
		super(message, cause);
	}

	public InventoryLocationException(Throwable cause) {
		super(cause);
	}

	protected InventoryLocationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	@Override
	public String getErrorCode() {
		return ErrorCodes.BIZ_INVENTORY_LOCATION_ERROR_10084;
	}
}
