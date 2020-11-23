package com.lwxf.industry4.webapp.domain.dao.statements.factory.dealerStatement.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.dealer.DealerReportRankDto;
import com.lwxf.industry4.webapp.domain.dto.dealer.DealerReportSummaryDto;
import com.lwxf.industry4.webapp.domain.dto.dealer.WxDealerAddressDto;
import org.springframework.stereotype.Repository;

import com.lwxf.industry4.webapp.domain.dao.base.BaseNoIdDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.statements.factory.dealerStatement.DealerStatementDao;
import com.lwxf.industry4.webapp.domain.dto.aftersale.DateNum;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByMonthDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByQuarterDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByWeekDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByYearDto;
import com.lwxf.mybatis.utils.MapContext;

import java.util.List;
import java.util.Map;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/6/28 0028 14:12
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("dealerStatementDao")
public class DealerStatementDaoImpl extends BaseNoIdDaoImpl implements DealerStatementDao {

    @Override
    public List queryLogisticsOrder() {
        String sqlId=this.getNamedSqlId("queryLogisticsOrder");
        return this.getSqlSession().selectList(sqlId);
    }

    @Override
    public List<Map> findDealerNumGroupByType(String branchId) {
        String sqlId=this.getNamedSqlId("findDealerNumGroupByType");
        return this.getSqlSession().selectList(sqlId,branchId);
    }

    @Override
    public List queryTotalOrderByTypeData() {
        String sqlId=this.getNamedSqlId("queryTotalOrderByTypeData");
        return this.getSqlSession().selectList(sqlId);
    }

    @Override
    public List queryNowYearData() {
        String sqlId=this.getNamedSqlId("queryNowYearData");
        return this.getSqlSession().selectList(sqlId);
    }

    @Override
    public List queryLastYearData() {
        String sqlId=this.getNamedSqlId("queryLastYearData");
        return this.getSqlSession().selectList(sqlId);
    }

    @Override
    public List queryDealerSellOrder() {
        String sqlId=this.getNamedSqlId("queryDealerSellOrder");
        return this.getSqlSession().selectList(sqlId);
    }

    @Override
    public List queryDeliverData(MapContext deliverStatus) {
        String sqlId=this.getNamedSqlId("queryDeliverData");
        return this.getSqlSession().selectList(sqlId,deliverStatus);
    }

    @Override
    public List queryRepertoryData() {
        String sqlId=this.getNamedSqlId("queryRepertoryData");
        return this.getSqlSession().selectList(sqlId);
    }

    @Override
    public List queryTotalOrderData() {
        String sqlId=this.getNamedSqlId("queryTotalOrderData");
        return this.getSqlSession().selectList(sqlId);
    }

    @Override
    public List queryOrderPieChartData() {
        String sqlId=this.getNamedSqlId("queryOrderPieChartData");
        return this.getSqlSession().selectList(sqlId);
    }

    @Override
    public List queryOwnLineChartData() {
        String sqlId=this.getNamedSqlId("queryOwnLineChartData");
        return this.getSqlSession().selectList(sqlId);
    }

    @Override
    public List queryOutOrderLineChartData() {
        String sqlId=this.getNamedSqlId("queryOutOrderLineChartData");
        return this.getSqlSession().selectList(sqlId);
    }

    @Override
    public List queryAfterSalOrderLineChartData() {
        String sqlId=this.getNamedSqlId("queryAfterSalOrderLineChartData");
        return this.getSqlSession().selectList(sqlId);
    }

    @Override
    public List queryNowMonthYearOrderData() {
        String sqlId=this.getNamedSqlId("queryNowMonthYearOrderData");
        return this.getSqlSession().selectList(sqlId);
    }

    @Override
    public List queryFinanceData() {
        String sqlId=this.getNamedSqlId("queryFinanceData");
        return this.getSqlSession().selectList(sqlId);
    }

    @Override
    public List queryFinancePieChartYearData() {
        String sqlId=this.getNamedSqlId("queryFinancePieChartYearData");
        return this.getSqlSession().selectList(sqlId);
    }

    @Override
    public List queryFinancePieChartMonthData() {
        String sqlId=this.getNamedSqlId("queryFinancePieChartMonthData");
        return this.getSqlSession().selectList(sqlId);
    }

    @Override
    public List queryFinanceTotalComparisonData() {
        String sqlId=this.getNamedSqlId("queryFinanceTotalComparisonData");
        return this.getSqlSession().selectList(sqlId);
    }

    @Override
    public List queryDeptMemberData() {
        String sqlId=this.getNamedSqlId("queryDeptMemberData");
        return this.getSqlSession().selectList(sqlId);
    }

    @Override
	public List queryProductData() {
		String sqlId=this.getNamedSqlId("queryProductData");
		return this.getSqlSession().selectList(sqlId);
	}

	@Override
	public List queryOwnerStateData() {
		String sqlId=this.getNamedSqlId("queryOwnerStateData");
		return this.getSqlSession().selectList(sqlId);
	}

	@Override
	public List queryOutStateData() {
		String sqlId=this.getNamedSqlId("queryOutStateData");
		return this.getSqlSession().selectList(sqlId);
	}

	@Override
	public List queryAftererSaleStateData() {
		String sqlId=this.getNamedSqlId("queryAftererSaleStateData");
        return this.getSqlSession().selectList(sqlId);
	}

	@Override
	public Map queryDealerData() {
		String sqlId=this.getNamedSqlId("queryDealerData");
		return this.getSqlSession().selectOne(sqlId);
	}

	@Override
	public CountByWeekDto findDealerStatementByWeek(String branchId, String countType) {
		String sqlId=this.getNamedSqlId("findDealerStatementByWeek");
		MapContext mapContext=MapContext.newOne();
		mapContext.put("branchId",branchId);
		mapContext.put("countType",countType);
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public CountByMonthDto findDealerStatementByMonth(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findDealerStatementByMonth");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public CountByQuarterDto findDealerStatementByQuarter(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findDealerStatementByQuarter");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public CountByYearDto findDealerStatementByYear(String branchId, String countType) {
		String sqlId=this.getNamedSqlId("findDealerStatementByYear");
		MapContext mapContext=MapContext.newOne();
		mapContext.put("branchId",branchId);
		mapContext.put("countType",countType);
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public Integer findDealerStatements(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findDealerStatements");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public DealerReportSummaryDto selectDealerReportSummaryDto(String branchId) {
		String sqlId=this.getNamedSqlId("selectDealerReportSummaryDto");
		MapContext mapContext=MapContext.newOne();
		mapContext.put("branchId",branchId);
		return this.getSqlSession().selectOne(sqlId, mapContext);
	}

	@Override
	public PaginatedList<DealerReportRankDto> selectDealerReportRankDtoByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectDealerReportRankDtoByFilter");
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination());
		PageList<DealerReportRankDto> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

}
