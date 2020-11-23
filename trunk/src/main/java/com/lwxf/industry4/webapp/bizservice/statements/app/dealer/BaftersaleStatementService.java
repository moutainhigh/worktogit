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
 * @create：2019/6/30 0030 10:56
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface BaftersaleStatementService extends BaseNoIdService {
	CountByWeekDto findAftersaleStatementByWeek(String companyId, String countType);

	CountByMonthDto findAftersaleStatementByMonth(MapContext mapContext);

	CountByQuarterDto findAftersaleStatementByQuarter(MapContext mapContext);

	CountByYearDto findAftersaleStatementByYear(String companyId, String countType);

	DateNum findAftersaleStatements(MapContext mapContext);

	MapContext findAftersaleStatementType(MapContext map);
}
