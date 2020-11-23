package com.lwxf.industry4.webapp.facade.admin.factory.statement;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

import java.util.Date;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/6/30 0030 10:47
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface AftersaleStatementFacade extends BaseFacade {
	RequestResult weekAftersaleStatements(String branchId, String countType);

	RequestResult monthAftersaleStatements(String branchId, String countType);

	RequestResult quarterAftersaleStatements(String branchId, String countType);

	RequestResult yearAftersaleStatements(String branchId, String countType);

	RequestResult aftersaleStatements(Date startTime, Date endTime, MapContext map, Integer dateType);
}
