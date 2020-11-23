package com.lwxf.industry4.webapp.domain.dao.statements.factory.dispatchBillStatement;

import java.util.Date;
import java.util.List;

import com.lwxf.industry4.webapp.common.result.RequestResult;
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
 * @create：2019/6/30 0030 10:52
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface DispatchBillStatementDao extends BaseNoIdDao {
	CountByWeekDto findDispatchBillStatementByWeek(String branchId, String countType);

	MapContext findDispatchBillOneByWeek(MapContext mapContext);

	List<MapContext> findDispatchBillTwoByWeek(MapContext mapContext);

	CountByMonthDto findDispatchBillStatementByMonth(MapContext mapContext);

	CountByQuarterDto findDispatchBillStatementByQuarter(MapContext mapContext);

	CountByYearDto findDispatchBillStatementByYear(String branchId, String countType);

	DateNum findDispatchBillStatements(MapContext mapContext);

    Integer dispatchBillStatementsNow(String branchId);

    Integer dispatchBillStatementsTrend(String branchId, Date date,String dateType);

	List<MapContext> dispatchBillStatementsRank(MapContext mapContext);
}
