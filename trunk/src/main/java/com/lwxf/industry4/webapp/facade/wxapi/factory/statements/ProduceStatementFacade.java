package com.lwxf.industry4.webapp.facade.wxapi.factory.statements;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/7/3 0003 17:12
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface ProduceStatementFacade extends BaseFacade {
	RequestResult weekProduceStatements(String branchId, String countType);

	RequestResult monthProduceStatements(String branchId, String countType);

	RequestResult quarterProduceStatements(String branchId, String countType);

	RequestResult yearProduceStatements(String branchId, String countType);

	RequestResult produceStatements(String branchId, String countType, MapContext mapContext);
}
