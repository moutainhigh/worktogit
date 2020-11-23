package com.lwxf.industry4.webapp.bizservice.statements.app.factory.dealerStatements;

import com.lwxf.industry4.webapp.bizservice.base.BaseNoIdService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.aftersale.DateNum;
import com.lwxf.industry4.webapp.domain.dto.dealer.DealerReportRankDto;
import com.lwxf.industry4.webapp.domain.dto.dealer.DealerReportSummaryDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByMonthDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByQuarterDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByWeekDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByYearDto;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/6/28 0028 14:07
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface DealerStatementService extends BaseNoIdService {

	CountByWeekDto findDealerStatementByWeek(String branchId, String countType);

	CountByMonthDto findDealerStatementByMonth(MapContext mapContext);

	CountByQuarterDto findDealerStatementByQuarter(MapContext mapContext);

	CountByYearDto findDealerStatementByYear(String branchId, String countType);

	Integer findDealerStatements(MapContext mapContext);

    DealerReportSummaryDto selectDealerReportSummary(String branchId);

    PaginatedList<DealerReportRankDto> selectDealerRankByFilter(PaginatedFilter paginatedFilter);
}
