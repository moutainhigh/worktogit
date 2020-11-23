package com.lwxf.industry4.webapp.domain.dao.statements.dealer;

import com.lwxf.industry4.webapp.domain.dao.base.BaseNoIdDao;
import com.lwxf.industry4.webapp.domain.dto.aftersale.DateNum;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByMonthDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByQuarterDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByWeekDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByYearDto;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/7/4 0004 10:30
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface BOrderStatementDao extends BaseNoIdDao {
	CountByWeekDto findOrderStatementByWeek(MapContext mapContext);

	MapContext findOrderStatementType(MapContext map);

	CountByMonthDto findOrderStatementByMonth(MapContext mapContext);

	CountByQuarterDto findOrderStatementByQuarter(MapContext mapContext);

	CountByYearDto findOrderStatementByYear(MapContext mapContext);

	DateNum findOrderStatements(MapContext mapContext);
}
