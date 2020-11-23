package com.lwxf.industry4.webapp.bizservice.statements.app.factory.aftersaleStatement.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lwxf.industry4.webapp.bizservice.base.BaseNoIdServiceImpl;
import com.lwxf.industry4.webapp.bizservice.statements.app.factory.aftersaleStatement.AftersaleStatementService;
import com.lwxf.industry4.webapp.domain.dao.base.BaseNoIdDao;
import com.lwxf.industry4.webapp.domain.dao.statements.factory.aftersaleStatement.AftersaleStatementDao;
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
@Service("aftersaleStatementService")
public class AftersaleStatementServiceImpl extends BaseNoIdServiceImpl implements AftersaleStatementService {
	@Resource
	private AftersaleStatementDao aftersaleStatementDao;
	@Override
	public void setDao(BaseNoIdDao dao) {
		this.dao=aftersaleStatementDao;
	}

	@Override
	public int add(Object entity) {
		return 0;
	}

	@Override
	public CountByWeekDto findAftersaleStatementByWeek(String branchId, String countType) {
		return aftersaleStatementDao.findAftersaleStatementByWeek(branchId,countType);
	}

	@Override
	public MapContext findAftersaleStatementTypeByWeek(MapContext mapContext) {
		return aftersaleStatementDao.findAftersaleStatementTypeByWeek(mapContext);
	}

	@Override
	public MapContext findfindAftersaleStatementTypeOneByWeek(MapContext map) {
		return aftersaleStatementDao.findfindAftersaleStatementTypeOneByWeek(map);
	}

	@Override
	public MapContext findfindAftersaleStatementTypeTwoByWeek(MapContext map) {
		return aftersaleStatementDao.findfindAftersaleStatementTypeTwoByWeek(map);
	}

	@Override
	public CountByMonthDto findAftersaleStatementByMonth(MapContext mapContext) {
		return aftersaleStatementDao.findAftersaleStatementByMonth(mapContext);
	}

	@Override
	public CountByQuarterDto findAftersaleStatementByQuarter(MapContext mapContext) {
		return aftersaleStatementDao.findAftersaleStatementByQuarter(mapContext);
	}

	@Override
	public CountByYearDto findAftersaleStatementByYear(String branchId, String countType) {
		return aftersaleStatementDao.findAftersaleStatementByYear(branchId,countType);
	}

	@Override
	public Integer findAftersaleStatements(MapContext mapContext) {
		return aftersaleStatementDao.findAftersaleStatements(mapContext);
	}
}
