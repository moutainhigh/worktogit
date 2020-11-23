package com.lwxf.industry4.webapp.bizservice.statements.app.factory.paymentStatement;

import com.lwxf.industry4.webapp.bizservice.base.BaseNoIdService;
import com.lwxf.industry4.webapp.domain.dto.statement.*;
import com.lwxf.mybatis.utils.MapContext;

import java.util.List;

public interface PaymentStatementService extends BaseNoIdService {

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
