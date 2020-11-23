package com.lwxf.industry4.webapp.domain.dao.statements.dealer.impl;

import org.springframework.stereotype.Repository;

import com.lwxf.industry4.webapp.domain.dao.base.BaseNoIdDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.statements.dealer.BOrderStatementDao;
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
 * @create：2019/7/4 0004 10:31
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("bOrderStatementDao")
public class BOrderStatementDaoImpl extends BaseNoIdDaoImpl implements BOrderStatementDao {
	@Override
	public CountByWeekDto findOrderStatementByWeek(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findOrderStatementByWeek");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public MapContext findOrderStatementType(MapContext map) {
		String sqlId=this.getNamedSqlId("findOrderStatementType");
		return this.getSqlSession().selectOne(sqlId,map);
	}

	@Override
	public CountByMonthDto findOrderStatementByMonth(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findOrderStatementByMonth");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public CountByQuarterDto findOrderStatementByQuarter(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findOrderStatementByQuarter");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public CountByYearDto findOrderStatementByYear(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findOrderStatementByYear");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public DateNum findOrderStatements(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findOrderStatements");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}
}
