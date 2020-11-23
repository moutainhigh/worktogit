package com.lwxf.industry4.webapp.domain.dao.statements.factory.dealerStatement;

import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.base.BaseNoIdDao;
import com.lwxf.industry4.webapp.domain.dto.aftersale.DateNum;
import com.lwxf.industry4.webapp.domain.dto.dealer.DealerReportRankDto;
import com.lwxf.industry4.webapp.domain.dto.dealer.DealerReportSummaryDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByMonthDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByQuarterDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByWeekDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByYearDto;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.mybatis.annotation.Param;
import com.lwxf.mybatis.utils.MapContext;

import java.util.List;
import java.util.Map;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/6/28 0028 14:11
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface DealerStatementDao extends BaseNoIdDao {

	CountByWeekDto findDealerStatementByWeek(String branchId, String countType);

	CountByMonthDto findDealerStatementByMonth(MapContext mapContext);

	CountByQuarterDto findDealerStatementByQuarter(MapContext mapContext);

	CountByYearDto findDealerStatementByYear(String branchId, String countType);

	Integer findDealerStatements(MapContext mapContext);

    DealerReportSummaryDto selectDealerReportSummaryDto(String branchId);

    PaginatedList<DealerReportRankDto> selectDealerReportRankDtoByFilter(PaginatedFilter paginatedFilter);

    Map queryDealerData();
	List queryProductData();
	List queryOwnerStateData();
	List queryOutStateData();
	List queryAftererSaleStateData();
	List queryRepertoryData();
	List queryTotalOrderData();
	List queryOrderPieChartData();
	List queryOwnLineChartData();
	List queryOutOrderLineChartData();
	List queryAfterSalOrderLineChartData();
	List queryNowMonthYearOrderData();
	List queryFinanceData();
	List queryFinancePieChartYearData();
	List queryFinancePieChartMonthData();
	List queryFinanceTotalComparisonData();
	List queryDeptMemberData();
	List queryDeliverData(MapContext deliverStatus);
	List queryDealerSellOrder();
	List queryNowYearData();
	List queryLastYearData();
	List queryTotalOrderByTypeData();
	List queryLogisticsOrder();
	List<Map> findDealerNumGroupByType(String branchId);
}
