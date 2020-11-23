package com.lwxf.industry4.webapp.domain.dao.statements.factory.order.impl;

import com.lwxf.industry4.webapp.domain.dao.base.BaseNoIdDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.statements.factory.order.CustomOrderStatementDao;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByMonthDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByQuarterDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByWeekDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByYearDto;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrder;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository("orderStatementDao")
public class OrderStatementDaoImpl extends BaseNoIdDaoImpl implements CustomOrderStatementDao {

    @Override
    public CountByWeekDto countOrderByWeek(MapContext map) {
        String sqlId = this.getNamedSqlId("countOrderByWeek");
        return this.getSqlSession().selectOne(sqlId,map);
    }

    @Override
    public CountByMonthDto countOrderByMonth(MapContext map) {
        String sqlId = this.getNamedSqlId("countOrderByMonth");
        return this.getSqlSession().selectOne(sqlId,map);
    }

    @Override
    public CountByQuarterDto countOrderByQuarter(MapContext map) {
        String sqlId = this.getNamedSqlId("countOrderByQuarter");
        return this.getSqlSession().selectOne(sqlId,map);
    }

    @Override
    public CountByYearDto countOrderByYear(MapContext map) {
        String sqlId = this.getNamedSqlId("countOrderByYear");
        return this.getSqlSession().selectOne(sqlId,map);
    }



    @Override
    public List<MapContext> findCorporates(MapContext mapContext) {
        String sqlId = this.getNamedSqlId("findCorporates");
        return this.getSqlSession().selectList(sqlId,mapContext);
    }

    @Override
    public MapContext findByCorporateNameAndDate(MapContext mapContext) {
        String sqlId = this.getNamedSqlId("findByCorporateNameAndDate");
        return this.getSqlSession().selectOne(sqlId,mapContext);
    }

    @Override
    public MapContext countSalemanAchievements(MapContext mapContext) {
        String sqlId = this.getNamedSqlId("countSalemanAchievements");
        return this.getSqlSession().selectOne(sqlId,mapContext);
    }

    @Override
    public List<MapContext> findCorporatesByBranchId(String currBranchId) {
        String sqlId = this.getNamedSqlId("findCorporatesByBranchId");
        return this.getSqlSession().selectList(sqlId,currBranchId);
    }

    @Override
    public Integer findAllOrderCountByBidAndType(MapContext mapContext) {
        String sqlId = this.getNamedSqlId("findAllOrderCountByBidAndType");
        return this.getSqlSession().selectOne(sqlId,mapContext);
    }

    @Override
    public MapContext selectOrderCount(MapContext map) {
        String sqlId = this.getNamedSqlId("selectOrderCount");
        return this.getSqlSession().selectOne(sqlId,map);
    }

    @Override
    public MapContext selectProduceCount(MapContext map) {
        String sqlId = this.getNamedSqlId("selectProduceCount");
        return this.getSqlSession().selectOne(sqlId,map);
    }

    @Override
    public MapContext selectAfterCount(MapContext map) {
        String sqlId = this.getNamedSqlId("selectAfterCount");
        return this.getSqlSession().selectOne(sqlId,map);
    }

    @Override
    public Integer selectByfilter(MapContext map) {
        String sqlId = this.getNamedSqlId("selectByfilter");
        return this.getSqlSession().selectOne(sqlId,map);
    }
    @Override
    public MapContext findCountByBidAndType(String branchId, String monthTime) {
        MapContext map=MapContext.newOne();
        map.put("branchId",branchId);
        map.put("monthTime",monthTime);
        String sqlId=this.getNamedSqlId("findCountByBidAndType");
        return this.getSqlSession().selectOne(sqlId,map);
    }

    @Override
    public MapContext selectOrderCountByDay(MapContext mapContext) {
        String sqlId = this.getNamedSqlId("selectOrderCountByDay");
        return this.getSqlSession().selectOne(sqlId,mapContext);
    }

    @Override
    public List<MapContext> findDealerByTime(String branchId, String monthTime) {
        MapContext map=MapContext.newOne();
        map.put("branchId",branchId);
        map.put("monthTime",monthTime);
        String sqlId=this.getNamedSqlId("findDealerByTime");
        return this.getSqlSession().selectList(sqlId,map);
    }

    @Override
    public List<CustomOrder> findOrderCOuntByCidAndTime(String companyId, String monthTime) {
        MapContext map=MapContext.newOne();
        map.put("companyId",companyId);
        map.put("monthTime",monthTime);
        String sqlId=this.getNamedSqlId("findOrderCOuntByCidAndTime");
        return this.getSqlSession().selectList(sqlId,map);
    }

    @Override
    public Integer findProductNumByBidAndTime(MapContext mapContext) {
        String sqlId = this.getNamedSqlId("findProductNumByBidAndTime");
        return this.getSqlSession().selectOne(sqlId,mapContext);
    }

    @Override
    public MapContext homeOrderCount(MapContext mapContext) {
        String sqlId = this.getNamedSqlId("homeOrderCount");
        return this.getSqlSession().selectOne(sqlId,mapContext);
    }

    @Override
    public MapContext myOrderCount(MapContext mapContext) {
        String sqlId = this.getNamedSqlId("myOrderCount");
        return this.getSqlSession().selectOne(sqlId,mapContext);
    }

    @Override
    public MapContext placeOrderCount(MapContext mapContext) {
        String sqlId = this.getNamedSqlId("placeOrderCount");
        return this.getSqlSession().selectOne(sqlId,mapContext);
    }

    @Override
    public MapContext merchandiserOrderCount(MapContext mapContext) {
        String sqlId = this.getNamedSqlId("merchandiserOrderCount");
        return this.getSqlSession().selectOne(sqlId,mapContext);
    }

    @Override
    public MapContext findOrderProductTypeCount(MapContext mapContext) {
        String sqlId = this.getNamedSqlId("findOrderProductTypeCount");
        return this.getSqlSession().selectOne(sqlId,mapContext);
    }

    @Override
    public MapContext dispatchOrderCountByDay(MapContext mapContext) {
        String sqlId = this.getNamedSqlId("dispatchOrderCountByDay");
        return this.getSqlSession().selectOne(sqlId,mapContext);
    }

    @Override
    public MapContext findOrderProductStockCount(MapContext mapContext) {
        String sqlId = this.getNamedSqlId("findOrderProductStockCount");
        return this.getSqlSession().selectOne(sqlId,mapContext);
    }

    @Override
    public MapContext findAfterOrderCount(MapContext mapContext) {
        String sqlId = this.getNamedSqlId("findAfterOrderCount");
        return this.getSqlSession().selectOne(sqlId,mapContext);
    }

    @Override
    public Map findDealerOrderCountsByTime(MapContext mapContext1) {
        String sqlId = this.getNamedSqlId("findDealerOrderCountsByTime");
        return this.getSqlSession().selectOne(sqlId,mapContext1);
    }

    @Override
    public Map selectDealerOrderCountByDay(MapContext params) {
        String sqlId = this.getNamedSqlId("selectDealerOrderCountByDay");
        return this.getSqlSession().selectOne(sqlId,params);
    }
}
