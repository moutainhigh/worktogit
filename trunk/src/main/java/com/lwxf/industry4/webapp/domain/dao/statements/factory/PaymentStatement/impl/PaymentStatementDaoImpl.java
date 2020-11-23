package com.lwxf.industry4.webapp.domain.dao.statements.paymentStatement.impl;

import com.lwxf.industry4.webapp.domain.dao.base.BaseNoIdDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.statements.paymentStatement.PaymentStatementDao;
import com.lwxf.industry4.webapp.domain.dto.statement.*;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("paymentStatementDao")
public class PaymentStatementDaoImpl extends BaseNoIdDaoImpl implements PaymentStatementDao {

    @Override
    public Double selectByfilter(MapContext map) {
        String sqlId = this.getNamedSqlId("selectByfilter");
        return this.getSqlSession().selectOne(sqlId,map);
    }

    @Override
    public List<PaymentCountByFunds> selectFundsStatementByFilter(MapContext map) {
        String sqlId = this.getNamedSqlId("selectFundsStatementByFilter");
        return this.getSqlSession().selectList(sqlId,map);
    }

    @Override
    public CountByWeekDto countPaymentByWeek(MapContext map) {
        String sqlId = this.getNamedSqlId("countPaymentByWeek");
        return this.getSqlSession().selectOne(sqlId,map);
    }

    @Override
    public CountByMonthDto countPaymentByMonth(MapContext map) {
        String sqlId = this.getNamedSqlId("countPaymentByMonth");
        return this.getSqlSession().selectOne(sqlId,map);
    }

    @Override
    public CountByQuarterDto countPaymentByQuarter(MapContext map) {
        String sqlId = this.getNamedSqlId("countPaymentByQuarter");
        return this.getSqlSession().selectOne(sqlId,map);
    }

    @Override
    public CountByYearDto countPaymentByYear(MapContext map) {
        String sqlId = this.getNamedSqlId("countPaymentByYear");
        return this.getSqlSession().selectOne(sqlId,map);
    }

    @Override
    public List<PaymentCountByFunds> countFundsStatementByWeek(MapContext map) {
        String sqlId = this.getNamedSqlId("countFundsStatementByWeek");
        return this.getSqlSession().selectList(sqlId,map);
    }

    @Override
    public List<PaymentCountByFunds> countFundsStatementByMonth(MapContext map) {
        String sqlId = this.getNamedSqlId("countFundsStatementByMonth");
        return this.getSqlSession().selectList(sqlId,map);
    }

    @Override
    public List<PaymentCountByFunds> countFundsStatementByQuarter(MapContext map) {
        String sqlId = this.getNamedSqlId("countFundsStatementByQuarter");
        return this.getSqlSession().selectList(sqlId,map);
    }

    @Override
    public List<PaymentCountByFunds> countFundsStatementByYear(MapContext map) {
        String sqlId = this.getNamedSqlId("countFundsStatementByYear");
        return this.getSqlSession().selectList(sqlId,map);
    }

    @Override
    public List<FinanceReportOrderDto> selectTwoDaysContrast(MapContext mapContext) {
        String sqlId = this.getNamedSqlId("selectTwoDaysContrast");
        return this.getSqlSession().selectList(sqlId, mapContext);
    }

    @Override
    public List<FinanceReportOrderDto> selectTwoMonthContrast(MapContext mapContext) {
        String sqlId = this.getNamedSqlId("selectTwoMonthContrast");
        return this.getSqlSession().selectList(sqlId, mapContext);
    }

}
