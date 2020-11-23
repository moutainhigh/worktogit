package com.lwxf.industry4.webapp.common.exceptions;

import com.lwxf.commons.exception.LwxfException;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/5/28/028 15:37
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class TypeProduceOrderNotExistException extends LwxfException {
	public TypeProduceOrderNotExistException() {
		super("该类型生产单不存在");
	}

	public TypeProduceOrderNotExistException(String message) {
		super(message);
	}

	public TypeProduceOrderNotExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public TypeProduceOrderNotExistException(Throwable cause) {
		super(cause);
	}

	protected TypeProduceOrderNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	@Override
	public String getErrorCode() {
		return ErrorCodes.BIZ_THIS_TYPE_OF_PRODUCE_ORDER_NOT_EXIST_10092;
	}
}
