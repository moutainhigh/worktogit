package com.lwxf.industry4.webapp.bizservice.statements.app.factory.finishStockStatement.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lwxf.industry4.webapp.bizservice.base.BaseNoIdServiceImpl;
import com.lwxf.industry4.webapp.bizservice.statements.app.factory.finishStockStatement.FinishStockStatementService;
import com.lwxf.industry4.webapp.domain.dao.base.BaseNoIdDao;
import com.lwxf.industry4.webapp.domain.dao.statements.factory.finishStock.FinishStockStatementDao;
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
 * @create：2019/7/3 0003 15:07
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("finishStockStatementService")
class FinishStockStatementServiceImpl extends BaseNoIdServiceImpl implements FinishStockStatementService {
	@Resource
	private FinishStockStatementDao finishStockStatementDao;
	@Override
	public void setDao(BaseNoIdDao dao) {
		this.dao=finishStockStatementDao;
	}

	@Override
	public int add(Object entity) {
		return 0;
	}

	@Override
	public CountByWeekDto findFinishStockStatementByWeek(String branchId, String countType) {
		return finishStockStatementDao.findFinishStockStatementByWeek(branchId,countType);
	}

	@Override
	public MapContext findList(MapContext map) {
		return finishStockStatementDao.findList(map);
	}

	@Override
	public CountByMonthDto findFinishStockStatementByMonth(MapContext mapContext) {
		return finishStockStatementDao.findFinishStockStatementByMonth(mapContext);
	}

	@Override
	public CountByQuarterDto findFinishStockStatementByQuarter(MapContext mapContext) {
		return finishStockStatementDao.findFinishStockStatementByQuarter(mapContext);
	}

	@Override
	public CountByYearDto findAFinishStockStatementByYear(String branchId, String countType) {
		return finishStockStatementDao.findAFinishStockStatementByYear(branchId,countType);
	}

	@Override
	public DateNum findFinishStockStatements(MapContext mapContext) {
		return finishStockStatementDao.findFinishStockStatements(mapContext);
	}
}
