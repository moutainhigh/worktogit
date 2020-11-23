package com.lwxf.industry4.webapp.bizservice.statements.app.dealer;

import com.lwxf.industry4.webapp.bizservice.base.BaseNoIdService;
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
 * @create：2019/7/4 0004 10:34
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface BOrderStatementService extends BaseNoIdService {
	CountByWeekDto findOrderStatementByWeek(String companyId, String countType);

	MapContext findOrderStatementType(MapContext map);

	CountByMonthDto findOrderStatementByMonth(MapContext mapContext);

	CountByQuarterDto findOrderStatementByQuarter(MapContext mapContext);

	CountByYearDto findOrderStatementByYear(String companyId, String countType);

	DateNum findOrderStatements(MapContext mapContext);
}
