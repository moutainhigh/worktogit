package com.lwxf.industry4.webapp.domain.dao.statements.dealer.impl;

import org.springframework.stereotype.Repository;

import com.lwxf.industry4.webapp.domain.dao.base.BaseNoIdDaoImpl;
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
 * @create：2019/7/8 0008 10:08
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("baftersaleStatementDao")
public class BaftersaleStatementDaoImpl extends BaseNoIdDaoImpl implements BaftersaleStatementDao {
	@Override
	public CountByWeekDto findAftersaleStatementByWeek(String companyId, String countType) {
		String sqlId=this.getNamedSqlId("findAftersaleStatementByWeek");
		MapContext params=MapContext.newOne();
		params.put("companyId",companyId);
		params.put("countType",countType);
		return this.getSqlSession().selectOne(sqlId,params);
	}

	@Override
	public MapContext findAftersaleStatementType(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findAftersaleStatementType");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public CountByMonthDto findAftersaleStatementByMonth(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findAftersaleStatementByMonth");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public CountByQuarterDto findAftersaleStatementByQuarter(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findAftersaleStatementByQuarter");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public CountByYearDto findAftersaleStatementByYear(String companyId, String countType) {
		String sqlId=this.getNamedSqlId("findAftersaleStatementByYear");
		MapContext params=MapContext.newOne();
		params.put("companyId",companyId);
		params.put("countType",countType);
		return this.getSqlSession().selectOne(sqlId,params);
	}

	@Override
	public DateNum findAftersaleStatements(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findAftersaleStatements");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}
}
