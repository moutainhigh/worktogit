package com.lwxf.industry4.webapp.bizservice.statements.app.factory.dealerStatements.impl;

import javax.annotation.Resource;

import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.dealer.DealerReportRankDto;
import com.lwxf.industry4.webapp.domain.dto.dealer.DealerReportSummaryDto;
import org.springframework.stereotype.Service;

import com.lwxf.industry4.webapp.bizservice.base.BaseNoIdServiceImpl;
import com.lwxf.industry4.webapp.bizservice.statements.app.factory.dealerStatements.DealerStatementService;
import com.lwxf.industry4.webapp.domain.dao.base.BaseNoIdDao;
import com.lwxf.industry4.webapp.domain.dao.statements.factory.dealerStatement.DealerStatementDao;
import com.lwxf.industry4.webapp.domain.dto.aftersale.DateNum;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByMonthDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByQuarterDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByWeekDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByYearDto;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/6/28 0028 14:08
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("dealerStatementService")
public class DealerStatementServiceImpl extends BaseNoIdServiceImpl implements DealerStatementService {
	@Resource
	private DealerStatementDao dealerStatementDao;


	@Override
	public void setDao(BaseNoIdDao dao) {
		this.dao=dealerStatementDao;
	}

	@Override
	public int add(Object entity) {
		return 0;
	}

	@Override
	public CountByWeekDto findDealerStatementByWeek(String branchId, String countType) {
		return dealerStatementDao.findDealerStatementByWeek(branchId,countType);

	}

	@Override
	public CountByMonthDto findDealerStatementByMonth(MapContext mapContext) {
		return dealerStatementDao.findDealerStatementByMonth(mapContext);
	}

	@Override
	public CountByQuarterDto findDealerStatementByQuarter(MapContext mapContext) {
		return dealerStatementDao.findDealerStatementByQuarter(mapContext);
	}

	@Override
	public CountByYearDto findDealerStatementByYear(String branchId, String countType) {
		return dealerStatementDao.findDealerStatementByYear(branchId,countType);
	}

	@Override
	public Integer findDealerStatements(MapContext mapContext) {
		return dealerStatementDao.findDealerStatements(mapContext);
	}

	@Override
	public DealerReportSummaryDto selectDealerReportSummary(String branchId) {
		return dealerStatementDao.selectDealerReportSummaryDto(branchId);
	}

	@Override
	public PaginatedList<DealerReportRankDto> selectDealerRankByFilter(PaginatedFilter paginatedFilter) {
		return dealerStatementDao.selectDealerReportRankDtoByFilter(paginatedFilter);
	}
}
