package com.lwxf.industry4.webapp.domain.dao.statements.factory.finishStock.impl;

import org.springframework.stereotype.Repository;

import com.lwxf.industry4.webapp.domain.dao.base.BaseNoIdDaoImpl;
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
 * @create：2019/7/3 0003 15:00
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("finishStockStatementDao")
public class FinishStockStatementDaoImpl extends BaseNoIdDaoImpl implements FinishStockStatementDao {
	@Override
	public CountByWeekDto findFinishStockStatementByWeek(String branchId, String countType) {
		String sqlId=this.getNamedSqlId("findFinishStockStatementByWeek");
		MapContext mapContext=MapContext.newOne();
		mapContext.put("branchId",branchId);
		mapContext.put("countType",countType);
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public MapContext findList(MapContext map) {
		String sqlId=this.getNamedSqlId("findList");
		return this.getSqlSession().selectOne(sqlId,map);
	}

	@Override
	public CountByMonthDto findFinishStockStatementByMonth(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findFinishStockStatementByMonth");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public CountByQuarterDto findFinishStockStatementByQuarter(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findFinishStockStatementByQuarter");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public CountByYearDto findAFinishStockStatementByYear(String branchId, String countType) {
		String sqlId=this.getNamedSqlId("findAFinishStockStatementByYear");
		MapContext mapContext=MapContext.newOne();
		mapContext.put("branchId",branchId);
		mapContext.put("countType",countType);
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public DateNum findFinishStockStatements(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findFinishStockStatements");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}
}
