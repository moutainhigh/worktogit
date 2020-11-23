package com.lwxf.industry4.webapp.facade.admin.factory.statement;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

import java.util.Date;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/6/30 0030 9:25
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface DealerStatementFacade extends BaseFacade {
	RequestResult weekDealerStatements(String branchId, String countType);

	RequestResult dealerStatements(Date startTime, Date endTime, MapContext map, Integer dateType);

	RequestResult monthDealerStatements(String branchId, String countType);

	RequestResult quarterDealerStatements(String branchId, String countType);

	RequestResult yearDealerStatements(String branchId, String countType);

	RequestResult getSalemans(String branchId);

	RequestResult companyOrderRankingList(MapContext map);

	RequestResult companyOrderInfoCount(String dealerId, String yearTime);

	RequestResult findDealerReportSummary(String branchId);

	RequestResult findDealerRank(Integer pageNum, Integer pageSize, MapContext mapContext);
}
