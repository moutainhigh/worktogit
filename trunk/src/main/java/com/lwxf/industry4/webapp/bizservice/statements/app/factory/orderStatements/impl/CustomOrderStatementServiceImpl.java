package com.lwxf.industry4.webapp.bizservice.statements.app.factory.orderStatements.impl;

import com.lwxf.industry4.webapp.bizservice.base.BaseNoIdServiceImpl;
import com.lwxf.industry4.webapp.bizservice.statements.app.factory.orderStatements.CustomOrderStatementService;
import com.lwxf.industry4.webapp.domain.dao.base.BaseNoIdDao;
import com.lwxf.industry4.webapp.domain.dao.statements.factory.order.CustomOrderStatementDao;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByMonthDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByQuarterDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByWeekDto;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByYearDto;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrder;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("customOrderStatementService")
public class CustomOrderStatementServiceImpl extends BaseNoIdServiceImpl implements CustomOrderStatementService {

    @Resource
    private CustomOrderStatementDao customOrderStatementDao;
    @Override
    public void setDao(BaseNoIdDao dao) {
        this.dao = customOrderStatementDao;
    }

    @Override
    public int add(Object entity) {
        return 0;
    }


    @Override
    public CountByWeekDto countOrderByWeek(MapContext map) {
        return customOrderStatementDao.countOrderByWeek(map);
    }

    @Override
    public CountByMonthDto countOrderByMonth(MapContext map) {
        return customOrderStatementDao.countOrderByMonth(map);
    }

    @Override
    public CountByQuarterDto countOrderByQuarter(MapContext map) {
        return customOrderStatementDao.countOrderByQuarter(map);
    }

    @Override
    public CountByYearDto countOrderByYear(MapContext map) {
        return customOrderStatementDao.countOrderByYear(map);
    }



	@Override
	public List<MapContext> findCorporates(MapContext mapContext) {
		return customOrderStatementDao.findCorporates(mapContext);
	}

    @Override
    public MapContext findByCorporateNameAndDate(MapContext mapContext) {
        return customOrderStatementDao.findByCorporateNameAndDate(mapContext);
    }

    @Override
    public MapContext countSalemanAchievements(MapContext mapContext) {
        return customOrderStatementDao.countSalemanAchievements(mapContext);
    }

    @Override
    public List<MapContext> findCorporatesByBranchId(String currBranchId) {
        return customOrderStatementDao.findCorporatesByBranchId(currBranchId);
    }

    @Override
    public Integer findAllOrderCountByBidAndType(MapContext mapContext) {
        return customOrderStatementDao.findAllOrderCountByBidAndType(mapContext);
    }

    @Override
    public MapContext selectOrderCount(MapContext map) {
        return customOrderStatementDao.selectOrderCount(map);
    }

    @Override
    public MapContext selectProduceCount(MapContext map) {
        return customOrderStatementDao.selectProduceCount(map);
    }

    @Override
    public MapContext selectAfterCount(MapContext map) {
        return customOrderStatementDao.selectAfterCount(map);
    }

    @Override
    public MapContext findCountByBidAndType(String branchId, String monthTime) {
        return customOrderStatementDao.findCountByBidAndType(branchId,monthTime);
    }

    @Override
    public MapContext selectOrderCountByDay(MapContext mapContext) {
        return customOrderStatementDao.selectOrderCountByDay(mapContext);
    }

    @Override
    public List<MapContext> findDealerByTime(String branchId, String monthTime) {
        return customOrderStatementDao.findDealerByTime(branchId,monthTime);
    }

    @Override
    public List<CustomOrder> findOrderCOuntByCidAndTime(String companyId, String monthTime) {
        return customOrderStatementDao.findOrderCOuntByCidAndTime(companyId,monthTime);
    }

    @Override
    public Integer findProductNumByBidAndTime(MapContext mapContext) {
        return customOrderStatementDao.findProductNumByBidAndTime(mapContext);
    }

    @Override
    public MapContext homeOrderCount(MapContext mapContext) {
        return customOrderStatementDao.homeOrderCount(mapContext);
    }

    @Override
    public MapContext myOrderCount(MapContext mapContext) {
        return customOrderStatementDao.myOrderCount(mapContext);
    }

    @Override
    public MapContext placeOrderCount(MapContext mapContext) {
        return customOrderStatementDao.placeOrderCount(mapContext);
    }

    @Override
    public MapContext merchandiserOrderCount(MapContext mapContext) {
        return customOrderStatementDao.merchandiserOrderCount(mapContext);
    }


    @Override
    public MapContext findOrderProductTypeCount(MapContext mapContext) {
        return customOrderStatementDao.findOrderProductTypeCount(mapContext);
    }

    @Override
    public MapContext dispatchOrderCountByDay(MapContext mapContext) {
        return customOrderStatementDao.dispatchOrderCountByDay(mapContext);
    }

    @Override
    public MapContext findOrderProductStockCount(MapContext mapContext) {
        return customOrderStatementDao.findOrderProductStockCount(mapContext);
    }

    @Override
    public MapContext findAfterOrderCount(MapContext mapContext) {
        return customOrderStatementDao.findAfterOrderCount(mapContext);
    }

    @Override
    public Map findDealerOrderCountsByTime(MapContext mapContext1) {
        return customOrderStatementDao.findDealerOrderCountsByTime(mapContext1);
    }

    @Override
    public Map selectDealerOrderCountByDay(MapContext params) {
        return this.customOrderStatementDao.selectDealerOrderCountByDay(params);
    }

    @Override
    public Integer selectByfilter(MapContext map) {
        return customOrderStatementDao.selectByfilter(map);
    }
}
