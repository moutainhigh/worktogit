package com.lwxf.industry4.webapp.domain.dao.statements.factory.finishStock;

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
 * @create：2019/7/3 0003 15:00
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface FinishStockStatementDao extends BaseNoIdDao {
	CountByWeekDto findFinishStockStatementByWeek(String branchId, String countType);

	MapContext findList(MapContext map);

	CountByMonthDto findFinishStockStatementByMonth(MapContext mapContext);

	CountByQuarterDto findFinishStockStatementByQuarter(MapContext mapContext);

	CountByYearDto findAFinishStockStatementByYear(String branchId, String countType);

	DateNum findFinishStockStatements(MapContext mapContext);
}
