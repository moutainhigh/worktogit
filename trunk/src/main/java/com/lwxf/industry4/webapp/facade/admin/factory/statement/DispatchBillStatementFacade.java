package com.lwxf.industry4.webapp.facade.admin.factory.statement;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

import java.util.Date;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/7/2 0002 9:46
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface DispatchBillStatementFacade extends BaseFacade {
	RequestResult weekDispatchBillStatements(String branchId, String countType);

	RequestResult monthDispatchBilleStatements(String branchId, String countType);

	RequestResult quarterDispatchBillStatements(String branchId, String countType);

	RequestResult yearDispatchBillStatements(String branchId, String countType);

	RequestResult dispatchBillStatements(String branchId, String countType, MapContext mapContext);

	RequestResult dispatchBillStatementsNow(String branchId);

	RequestResult dispatchBillStatementsTrend(String branchId, String startTime, String endTime,String dateType);

	RequestResult dispatchBillStatementsRank(String branchId, String startTime, String endTime);
}
