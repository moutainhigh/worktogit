package com.lwxf.industry4.webapp.bizservice.statements.app.factory.dispatchBillStatement.impl;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import org.springframework.stereotype.Service;

import com.lwxf.industry4.webapp.bizservice.base.BaseNoIdServiceImpl;
import com.lwxf.industry4.webapp.bizservice.statements.app.factory.dispatchBillStatement.DispatchBillStatementService;
import com.lwxf.industry4.webapp.domain.dao.base.BaseNoIdDao;
import com.lwxf.industry4.webapp.domain.dao.statements.factory.dispatchBillStatement.DispatchBillStatementDao;
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
 * @create：2019/6/30 0030 10:56
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("dispatchBillStatementService")
public class DispatchBillStatementServiceImpl extends BaseNoIdServiceImpl implements DispatchBillStatementService {
	@Resource
	private DispatchBillStatementDao dispatchBillStatementDao;
	@Override
	public void setDao(BaseNoIdDao dao) {
		this.dao=dispatchBillStatementDao;
	}

	@Override
	public int add(Object entity) {
		return 0;
	}

	@Override
	public CountByWeekDto findDispatchBillStatementByWeek(String branchId, String countType) {
		return dispatchBillStatementDao.findDispatchBillStatementByWeek(branchId,countType);
	}

	@Override
	public MapContext findDispatchBillOneByWeek(MapContext mapContext) {
		return dispatchBillStatementDao.findDispatchBillOneByWeek(mapContext);
	}

	@Override
	public List<MapContext> findDispatchBillTwoByWeek(MapContext mapContext) {
		return dispatchBillStatementDao.findDispatchBillTwoByWeek(mapContext);
	}

	@Override
	public CountByMonthDto findDispatchBillStatementByMonth(MapContext mapContext) {
		return dispatchBillStatementDao.findDispatchBillStatementByMonth(mapContext);
	}

	@Override
	public CountByQuarterDto findDispatchBillStatementByQuarter(MapContext mapContext) {
		return dispatchBillStatementDao.findDispatchBillStatementByQuarter(mapContext);
	}

	@Override
	public CountByYearDto findDispatchBillStatementByYear(String branchId, String countType) {
		return dispatchBillStatementDao.findDispatchBillStatementByYear(branchId,countType);
	}

	@Override
	public DateNum findDispatchBillStatements(MapContext mapContext) {
		return dispatchBillStatementDao.findDispatchBillStatements(mapContext);
	}

	@Override
	public Integer dispatchBillStatementsNow(String branchId) {
		return dispatchBillStatementDao.dispatchBillStatementsNow(branchId);
	}

	@Override
	public Integer dispatchBillStatementsTrend(String branchId, Date date,String dateType) {
		return dispatchBillStatementDao.dispatchBillStatementsTrend(branchId,date,dateType);
	}

	@Override
	public List<MapContext> dispatchBillStatementsRank(MapContext mapContext) {
		return dispatchBillStatementDao.dispatchBillStatementsRank(mapContext);
	}
}
