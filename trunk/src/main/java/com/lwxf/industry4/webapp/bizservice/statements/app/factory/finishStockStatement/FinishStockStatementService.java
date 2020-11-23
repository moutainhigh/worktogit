package com.lwxf.industry4.webapp.bizservice.statements.app.factory.finishStockStatement;

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
 * @create：2019/7/3 0003 15:07
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface FinishStockStatementService extends BaseNoIdService {
	CountByWeekDto findFinishStockStatementByWeek(String branchId, String countType);

	MapContext findList(MapContext map);

	CountByMonthDto findFinishStockStatementByMonth(MapContext mapContext);

	CountByQuarterDto findFinishStockStatementByQuarter(MapContext mapContext);

	CountByYearDto findAFinishStockStatementByYear(String branchId, String countType);

	DateNum findFinishStockStatements(MapContext mapContext);
}
