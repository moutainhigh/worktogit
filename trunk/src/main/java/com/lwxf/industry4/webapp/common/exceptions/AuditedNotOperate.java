package com.lwxf.industry4.webapp.common.exceptions;

import com.lwxf.commons.exception.LwxfException;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/4/2/002 10:31
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class AuditedNotOperate extends LwxfException {
	public AuditedNotOperate() {
		super("审核后不允许操作");
	}

	public AuditedNotOperate(String message) {
		super(message);
	}

	public AuditedNotOperate(String message, Throwable cause) {
		super(message, cause);
	}

	public AuditedNotOperate(Throwable cause) {
		super(cause);
	}

	protected AuditedNotOperate(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	@Override
	public String getErrorCode() {
		return ErrorCodes.BIZ_AUDITED_NOT_OPERATE_10083;
	}
}
