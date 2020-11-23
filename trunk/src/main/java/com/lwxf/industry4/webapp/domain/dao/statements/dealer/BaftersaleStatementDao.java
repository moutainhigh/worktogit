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
 * @create：2019/7/8 0008 10:08
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface BaftersaleStatementDao extends BaseNoIdDao {
	CountByWeekDto findAftersaleStatementByWeek(String companyId, String countType);

	MapContext findAftersaleStatementType(MapContext mapContext);

	CountByMonthDto findAftersaleStatementByMonth(MapContext mapContext);

	CountByQuarterDto findAftersaleStatementByQuarter(MapContext mapContext);

	CountByYearDto findAftersaleStatementByYear(String companyId, String countType);

	DateNum findAftersaleStatements(MapContext mapContext);
}
