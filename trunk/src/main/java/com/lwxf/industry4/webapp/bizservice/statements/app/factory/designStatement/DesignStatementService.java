package com.lwxf.industry4.webapp.bizservice.statements.app.factory.designStatement;

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
public interface DesignStatementService extends BaseNoIdService {
	CountByWeekDto findDesignStatementByWeek(MapContext map);

	CountByMonthDto findDesignStatementByMonth(MapContext mapContext);

	CountByQuarterDto findDesignStatementByQuarter(MapContext mapContext);


	CountByYearDto findDesignStatementByYear(MapContext mapContext);

	DateNum findDesignStatements(MapContext mapContext);
}
