package com.lwxf.industry4.webapp.domain.dao.statements.factory.aftersaleStatement.impl;

import org.springframework.stereotype.Repository;

import com.lwxf.industry4.webapp.domain.dao.base.BaseNoIdDaoImpl;
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
 * @create：2019/6/30 0030 10:53
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("aftersaleStatementDao")
public class AftersaleStatementDaoImpl extends BaseNoIdDaoImpl implements AftersaleStatementDao {
	@Override
	public CountByWeekDto findAftersaleStatementByWeek(String branchId, String countType) {
		String sqlId=this.getNamedSqlId("findAftersaleStatementByWeek");
		MapContext params=MapContext.newOne();
		params.put("branchId",branchId);
		params.put("countType",countType);
		return this.getSqlSession().selectOne(sqlId,params);
	}

	@Override
	public MapContext findAftersaleStatementTypeByWeek(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findAftersaleStatementTypeByWeek");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public MapContext findfindAftersaleStatementTypeOneByWeek(MapContext map) {
		String sqlId=this.getNamedSqlId("findfindAftersaleStatementTypeOneByWeek");
		return this.getSqlSession().selectOne(sqlId,map);
	}

	@Override
	public MapContext findfindAftersaleStatementTypeTwoByWeek(MapContext map) {
		String sqlId=this.getNamedSqlId("findfindAftersaleStatementTypeTwoByWeek");
		return this.getSqlSession().selectOne(sqlId,map);
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
	public CountByYearDto findAftersaleStatementByYear(String branchId, String countType) {
		String sqlId=this.getNamedSqlId("findAftersaleStatementByYear");
		MapContext params=MapContext.newOne();
		params.put("branchId",branchId);
		params.put("countType",countType);
		return this.getSqlSession().selectOne(sqlId,params);
	}

	@Override
	public Integer findAftersaleStatements(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findAftersaleStatements");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}
}
