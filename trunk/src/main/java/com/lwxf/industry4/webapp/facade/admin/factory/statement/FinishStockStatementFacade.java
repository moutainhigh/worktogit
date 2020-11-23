package com.lwxf.industry4.webapp.facade.admin.factory.statement;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/7/3 0003 15:12
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface FinishStockStatementFacade extends BaseFacade {
	RequestResult weekFinishStockBillStatements(String branchId, String countType);

	RequestResult monthFinisheStockStatements(String branchId, String countType);

	RequestResult quarterFinishStockStatements(String branchId, String countType);

	RequestResult yearFinishStockStatements(String branchId, String countType);

	RequestResult finishStockStatements(String branchId, String countType, MapContext mapContext);
}
