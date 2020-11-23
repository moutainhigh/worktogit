package com.lwxf.industry4.webapp.bizservice.statements.app.factory.paymentStatement.impl;

import com.lwxf.industry4.webapp.bizservice.base.BaseNoIdServiceImpl;
import com.lwxf.industry4.webapp.bizservice.statements.app.factory.paymentStatement.PaymentStatementService;
import com.lwxf.industry4.webapp.domain.dao.base.BaseNoIdDao;
import com.lwxf.industry4.webapp.domain.dao.statements.paymentStatement.PaymentStatementDao;
import com.lwxf.industry4.webapp.domain.dto.statement.*;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service("paymentStatementService")
public class PaymentStatementServiceImpl extends BaseNoIdServiceImpl implements PaymentStatementService {

    @Resource
    private PaymentStatementDao paymentStatementDao;
    @Override
    public void setDao(BaseNoIdDao dao) {
        this.dao=paymentStatementDao;
    }

    @Override
    public Double selectByfilter(MapContext map) {
        return paymentStatementDao.selectByfilter(map);
    }

    @Override
    public List<PaymentCountByFunds> selectFundsStatementByFilter(MapContext map) {
        return paymentStatementDao.selectFundsStatementByFilter(map);
    }

    @Override
    public CountByWeekDto countPaymentByWeek(MapContext map) {
        return paymentStatementDao.countPaymentByWeek(map);
    }

    @Override
    public CountByMonthDto countPaymentByMonth(MapContext map) {
        return paymentStatementDao.countPaymentByMonth(map);
    }

    @Override
    public CountByQuarterDto countPaymentByQuarter(MapContext map) {
        return paymentStatementDao.countPaymentByQuarter(map);
    }

    @Override
    public CountByYearDto countPaymentByYear(MapContext map) {
        return paymentStatementDao.countPaymentByYear(map);
    }

    @Override
    public List<PaymentCountByFunds> countFundsStatementByWeek(MapContext map) {
        return paymentStatementDao.countFundsStatementByWeek(map);
    }

    @Override
    public List<PaymentCountByFunds> countFundsStatementByMonth(MapContext map) {
        return paymentStatementDao.countFundsStatementByMonth(map);
    }

    @Override
    public List<PaymentCountByFunds> countFundsStatementByQuarter(MapContext map) {
        return paymentStatementDao.countFundsStatementByQuarter(map);
    }

    @Override
    public List<PaymentCountByFunds> countFundsStatementByYear(MapContext map) {
        return paymentStatementDao.countFundsStatementByYear(map);
    }

    @Override
    public List<FinanceReportOrderDto> selectTwoDaysContrast(MapContext mapContext) {
        return paymentStatementDao.selectTwoDaysContrast(mapContext);
    }

    @Override
    public List<FinanceReportOrderDto> selectTwoMonthContrast(MapContext mapContext) {
        return paymentStatementDao.selectTwoMonthContrast(mapContext);
    }

    @Override
    public int add(Object entity) {
        return 0;
    }
}
