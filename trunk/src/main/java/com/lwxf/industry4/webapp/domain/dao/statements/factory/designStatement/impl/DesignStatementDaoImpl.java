package com.lwxf.industry4.webapp.domain.dao.statements.factory.designStatement.impl;

import org.springframework.stereotype.Repository;

import com.lwxf.industry4.webapp.domain.dao.base.BaseNoIdDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.statements.factory.designStatement.DesignStatementDao;
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
@Repository("designStatementDao")
public class DesignStatementDaoImpl extends BaseNoIdDaoImpl implements DesignStatementDao {
	@Override
	public CountByWeekDto findDesignStatementByWeek(MapContext map) {
		String sqlId=this.getNamedSqlId("findDesignStatementByWeek");
		return this.getSqlSession().selectOne(sqlId,map);
	}

	@Override
	public CountByMonthDto findDesignStatementByMonth(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findDesignStatementByMonth");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public CountByQuarterDto findDesignStatementByQuarter(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findDesignStatementByQuarter");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public CountByYearDto findDesignStatementByYear(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findDesignStatementByYear");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public DateNum findDesignStatements(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findDesignStatements");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}
}
