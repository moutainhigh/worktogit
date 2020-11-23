package com.lwxf.industry4.webapp.bizservice.statements.app.factory.produce;

import com.lwxf.industry4.webapp.bizservice.base.BaseNoIdService;
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
 * @create：2019/7/3 0003 16:53
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface ProduceStatementService extends BaseNoIdService {
	CountByWeekDto findProduceStatementByWeek(String branchId, String countType);

	CountByMonthDto findProduceStatementByMonth(MapContext mapContext);

	CountByQuarterDto findProduceStatementByQuarter(MapContext mapContext);

	CountByYearDto findProduceStatementByYear(String branchId, String countType);

	DateNum findProduceStatements(MapContext mapContext);

	MapContext orderProduceCount(MapContext mapContext);

	List<MapContext> orderProduceTypeCountByCondition(MapContext mapContext);

	Integer orderProduceDelayByDay(MapContext mapContext);
}
