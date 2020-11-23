package com.lwxf.industry4.webapp.domain.dao.statements.factory.produce;

import com.lwxf.industry4.webapp.domain.dao.base.BaseNoIdDao;
import com.lwxf.industry4.webapp.domain.dto.aftersale.DateNum;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByMonthDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByQuarterDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByWeekDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByYearDto;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.mybatis.utils.MapContext;

import java.util.List;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/7/3 0003 16:48
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface ProduceStatementDao extends BaseNoIdDao {
	CountByWeekDto findProduceStatementByWeek(MapContext mapContext);

	CountByMonthDto findProduceStatementByMonth(MapContext mapContext);

	CountByQuarterDto findProduceStatementByQuarter(MapContext mapContext);

	CountByYearDto findProduceStatementByYear(MapContext mapContext);

	DateNum findProduceStatements(MapContext mapContext);

	MapContext findOrderProduceCount(MapContext mapContext);

    List<MapContext> orderProduceTypeCountByCondition(MapContext mapContext);

    Integer orderProduceDelayByDay(MapContext mapContext);
}
