package com.lwxf.industry4.webapp.bizservice.statements.app.dealer.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lwxf.industry4.webapp.bizservice.base.BaseNoIdServiceImpl;
import com.lwxf.industry4.webapp.bizservice.statements.app.dealer.BaftersaleStatementService;
import com.lwxf.industry4.webapp.domain.dao.base.BaseNoIdDao;
import com.lwxf.industry4.webapp.domain.dao.statements.dealer.BaftersaleStatementDao;
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
@Service("BaftersaleStatementService")
public class BaftersaleStatementServiceImpl extends BaseNoIdServiceImpl implements BaftersaleStatementService {
	@Resource
	private BaftersaleStatementDao baftersaleStatementDao;
	@Override
	public void setDao(BaseNoIdDao dao) {
		this.dao=baftersaleStatementDao;
	}

	@Override
	public int add(Object entity) {
		return 0;
	}
	@Override
	public CountByWeekDto findAftersaleStatementByWeek(String companyId, String countType) {
		return baftersaleStatementDao.findAftersaleStatementByWeek(companyId,countType);
	}

	@Override
	public MapContext findAftersaleStatementType(MapContext mapContext) {
		return baftersaleStatementDao.findAftersaleStatementType(mapContext);
	}

	@Override
	public CountByMonthDto findAftersaleStatementByMonth(MapContext mapContext) {
		return baftersaleStatementDao.findAftersaleStatementByMonth(mapContext);
	}

	@Override
	public CountByQuarterDto findAftersaleStatementByQuarter(MapContext mapContext) {
		return baftersaleStatementDao.findAftersaleStatementByQuarter(mapContext);
	}

	@Override
	public CountByYearDto findAftersaleStatementByYear(String companyId, String countType) {
		return baftersaleStatementDao.findAftersaleStatementByYear(companyId,countType);
	}

	@Override
	public DateNum findAftersaleStatements(MapContext mapContext) {
		return baftersaleStatementDao.findAftersaleStatements(mapContext);
	}
}
