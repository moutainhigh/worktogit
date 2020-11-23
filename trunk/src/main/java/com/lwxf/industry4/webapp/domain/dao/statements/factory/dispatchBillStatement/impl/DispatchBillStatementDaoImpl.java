package com.lwxf.industry4.webapp.domain.dao.statements.factory.dispatchBillStatement.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lwxf.industry4.webapp.domain.dao.base.BaseNoIdDaoImpl;
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
 * @create：2019/6/30 0030 10:53
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("dispatchBillStatementDao")
public class DispatchBillStatementDaoImpl extends BaseNoIdDaoImpl implements DispatchBillStatementDao {
	@Override
	public CountByWeekDto findDispatchBillStatementByWeek(String branchId, String countType) {
		String sqlId=this.getNamedSqlId("findDispatchBillStatementByWeek");
		MapContext mapContext=MapContext.newOne();
		mapContext.put("branchId",branchId);
		mapContext.put("countType",countType);
		return this.sqlSession.selectOne(sqlId,mapContext);
	}

	@Override
	public MapContext findDispatchBillOneByWeek(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findDispatchBillOneByWeek");
		return this.sqlSession.selectOne(sqlId,mapContext);
	}

	@Override
	public List<MapContext> findDispatchBillTwoByWeek(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findDispatchBillTwoByWeek");
		return this.sqlSession.selectList(sqlId,mapContext);
	}

	@Override
	public CountByMonthDto findDispatchBillStatementByMonth(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findDispatchBillStatementByMonth");
		return this.sqlSession.selectOne(sqlId,mapContext);
	}

	@Override
	public CountByQuarterDto findDispatchBillStatementByQuarter(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findDispatchBillStatementByQuarter");
		return this.sqlSession.selectOne(sqlId,mapContext);
	}

	@Override
	public CountByYearDto findDispatchBillStatementByYear(String branchId, String countType) {
		String sqlId=this.getNamedSqlId("findDispatchBillStatementByYear");
		MapContext mapContext=MapContext.newOne();
		mapContext.put("branchId",branchId);
		mapContext.put("countType",countType);
		return this.sqlSession.selectOne(sqlId,mapContext);
	}

	@Override
	public DateNum findDispatchBillStatements(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findDispatchBillStatements");
		return this.sqlSession.selectOne(sqlId,mapContext);
	}

	@Override
	public Integer dispatchBillStatementsNow(String branchId) {
		String sqlId=this.getNamedSqlId("dispatchBillStatementsNow");
		return this.sqlSession.selectOne(sqlId,branchId);
	}

	@Override
	public Integer dispatchBillStatementsTrend(String branchId, Date date,String dateType) {
		String sqlId=this.getNamedSqlId("dispatchBillStatementsTrend");
		MapContext mapContext=MapContext.newOne();
		mapContext.put("branchId",branchId);
		mapContext.put("date",date);
		mapContext.put("dateType",dateType);
		return this.sqlSession.selectOne(sqlId,mapContext);
	}

	@Override
	public List<MapContext> dispatchBillStatementsRank(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("dispatchBillStatementsRank");
		return this.sqlSession.selectList(sqlId,mapContext);
	}
}
