package com.lwxf.industry4.webapp.domain.dao.statements.paymentStatement;

import com.lwxf.industry4.webapp.domain.dao.base.BaseNoIdDao;
import com.lwxf.industry4.webapp.domain.dto.statement.*;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.mybatis.utils.MapContext;

import java.util.List;

@IBatisSqlTarget
public interface PaymentStatementDao extends BaseNoIdDao {

    Double selectByfilter(MapContext map);

    List<PaymentCountByFunds> selectFundsStatementByFilter(MapContext map);

    CountByWeekDto countPaymentByWeek(MapContext map);

    CountByMonthDto countPaymentByMonth(MapContext map);

    CountByQuarterDto countPaymentByQuarter(MapContext map);

    CountByYearDto countPaymentByYear(MapContext map);


    List<PaymentCountByFunds> countFundsStatementByWeek(MapContext map);

    List<PaymentCountByFunds> countFundsStatementByMonth(MapContext map);

    List<PaymentCountByFunds> countFundsStatementByQuarter(MapContext map);

    List<PaymentCountByFunds> countFundsStatementByYear(MapContext map);

    List<FinanceReportOrderDto> selectTwoDaysContrast(MapContext mapContext);

    List<FinanceReportOrderDto> selectTwoMonthContrast(MapContext mapContext);

}
