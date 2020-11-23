package com.lwxf.industry4.webapp.bizservice.statements.app.factory.aftersaleStatement;

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
public interface AftersaleStatementService extends BaseNoIdService {
	CountByWeekDto findAftersaleStatementByWeek(String branchId, String countType);

	MapContext findAftersaleStatementTypeByWeek(MapContext mapContext);

	MapContext findfindAftersaleStatementTypeOneByWeek(MapContext map);

	MapContext findfindAftersaleStatementTypeTwoByWeek(MapContext map);

	CountByMonthDto findAftersaleStatementByMonth(MapContext mapContext);

	CountByQuarterDto findAftersaleStatementByQuarter(MapContext mapContext);

	CountByYearDto findAftersaleStatementByYear(String branchId, String countType);

	Integer findAftersaleStatements(MapContext mapContext);
}
