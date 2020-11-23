package com.lwxf.industry4.webapp.domain.dao.statements.factory.produce.impl;

import org.springframework.stereotype.Repository;

import com.lwxf.industry4.webapp.domain.dao.base.BaseNoIdDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.statements.factory.produce.ProduceStatementDao;
import com.lwxf.industry4.webapp.domain.dto.aftersale.DateNum;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByMonthDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByQuarterDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByWeekDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByYearDto;
import com.lwxf.mybatis.utils.MapContext;

import java.util.List;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/7/3 0003 16:49
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("produceStatementDao")
public class ProduceStatementDaoImpl extends BaseNoIdDaoImpl implements ProduceStatementDao {
	@Override
	public CountByWeekDto findProduceStatementByWeek(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findProduceStatementByWeek");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public CountByMonthDto findProduceStatementByMonth(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findProduceStatementByMonth");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public CountByQuarterDto findProduceStatementByQuarter(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findProduceStatementByQuarter");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public CountByYearDto findProduceStatementByYear(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findProduceStatementByYear");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public DateNum findProduceStatements(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findProduceStatements");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public MapContext findOrderProduceCount(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findOrderProduceCount");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public List<MapContext> orderProduceTypeCountByCondition(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("orderProduceTypeCountByCondition");
		return this.getSqlSession().selectList(sqlId,mapContext);
	}

	@Override
	public Integer orderProduceDelayByDay(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("orderProduceDelayByDay");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}


}
