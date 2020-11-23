package com.lwxf.industry4.webapp.bizservice.customorder.impl;


import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.bizservice.customorder.CustomOrderService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.customorder.CustomOrderDao;
import com.lwxf.industry4.webapp.domain.dto.aftersale.DateNum;
import com.lwxf.industry4.webapp.domain.dto.customorder.*;
import com.lwxf.industry4.webapp.domain.dto.dealer.DealerOrderRankDto;
import com.lwxf.industry4.webapp.domain.dto.dealer.WxDealerInfoDto;
import com.lwxf.industry4.webapp.domain.dto.printTable.OfferPrintTableDto;
import com.lwxf.industry4.webapp.domain.dto.printTable.OrderPrintTableDto;
import com.lwxf.industry4.webapp.domain.dto.statement.WxFactoryStatementDto;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrder;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-04 17:48:28
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("customOrderService")
public class CustomOrderServiceImpl extends BaseServiceImpl<CustomOrder, String, CustomOrderDao> implements CustomOrderService {


	@Resource

	@Override	public void setDao( CustomOrderDao customOrderDao) {
		this.dao = customOrderDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<CustomOrder> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public PaginatedList<CustomOrderDto> selectByCondition(PaginatedFilter paginatedFilter) {
		return this.dao.selectByCondition(paginatedFilter);
	}

	@Override
	public CustomOrderDto findByOrderId(String orderId) {
		return this.dao.findByOrderId(orderId);
	}

	@Override
	public PaginatedList<CustomOrderDto> findFinishedOrderList(PaginatedFilter paginatedFilter) {
		return this.dao.findFinishedOrderList(paginatedFilter);
	}

	@Override
	public PaginatedList<OrderQuoteDto> findOrderQuoteMessage(PaginatedFilter paginatedFilter) {
		return this.dao.findOrderQuoteMessage(paginatedFilter);
	}

	@Override
	public PaginatedList<CustomOrderDto> findListByPaginateFilter(PaginatedFilter paginatedFilter) {
		return this.dao.findListByPaginateFilter(paginatedFilter);
	}
	@Override
	public PaginatedList<CustomOrderDto> findByCompanyIdAndStatus(PaginatedFilter paginatedFilter) {
		return this.dao.findByCompanyIdAndStatus(paginatedFilter);
	}

	@Override
	public Integer findOrderCountByStatus(MapContext mapContext) {
		return this.dao.findOrderCountByStatus(mapContext);
	}

	@Override
	public OrderCountDto findOrderNumByUidAndCid(MapContext mapContext) {
		return this.dao.findOrderNumByUidAndCid(mapContext);
	}

	@Override
	public List<CustomOrder> findByCidAndSalemanId(MapContext mapContext) {

		return this.dao.findByCidAndSalemanId(mapContext);
	}

	@Override
	public Integer findOrderMoneyCount(MapContext mapContext) {
		return this.dao.findOrderMoneyCount(mapContext);
	}

	@Override
	public BigDecimal findpayAmountByOrderId(String orderId) {
		return this.dao.findpayAmountByOrderId(orderId);
	}

	@Override
	public List<DateNum> findOrderNumByCreatedAndCid(MapContext mapContext) {
		return this.dao.findOrderNumByCreatedAndCid(mapContext);
	}
	@Override
	public List<CustomOrderDto> findByCompanyIdAndStatus(MapContext params) {
		return this.dao.findByCompanyIdAndStatus(params);
	}

	@Override
	public PaginatedList<Map> findSpecimenOrderList(PaginatedFilter paginatedFilter) {
		return this.dao.findSpecimenOrderList(paginatedFilter);
	}




	@Override
	public List<MapContext> findOrderListByCidAndUid(String dealerId, String userId) {
		return this.dao.findOrderListByCidAndUid(userId,dealerId);
	}


	@Override
	public Map findFAppBaseInfoByOrderId(MapContext params) {
		return this.dao.findFAppBaseInfoByOrderId(params);
	}

	@Override
	public List<CustomOrder> findByIds(Set orderIds) {
		return this.dao.findByIds(orderIds);
	}

	@Override
	public int updateOrderStatusByIds(List startOrderIds, Integer status) {
		return this.dao.updateOrderStatusByIds(startOrderIds,status);
	}

	@Override
	public PaginatedList<CustomOrderDto> findPacksOrderList(PaginatedFilter paginatedFilter) {
		return this.dao.findPacksOrderList(paginatedFilter);
	}

	@Override
	public List<CustomOrder> findByCustomerIdAndCid(String uId, String branchId) {
		return this.dao.findByCustomerIdAndCid(uId,branchId);
	}

	@Override
	public List<CustomOrder> findByCustomerIdAndCidAndStatus(String uId, String branchId, Integer status) {
		return this.dao.findByCustomerIdAndCidAndStatus(uId,branchId,status);
	}

	@Override
	public CustomOrder findByUidAndBranchId(MapContext mapContext) {
		return this.dao.findByUidAndBranchId(mapContext);
	}

	@Override
	public CustomOrder findByCidAndBranchId(String dealerId, String branchId) {
		return this.dao.findByCidAndBranchId(dealerId,branchId);
	}

	@Override
	public Integer findTodayOrderCount(MapContext param1) {
		return this.dao.findTodayOrderCount(param1);
	}

	@Override
	public Integer findTodayInvalidOrder(MapContext param2) {
		return this.dao.findTodayInvalidOrder(param2);
	}

	@Override
	public Integer findTodayEffectiveOrder(MapContext param2) {
		return this.dao.findTodayEffectiveOrder(param2);
	}

	@Override
	public PaginatedList<WxCustomOrderDto> findWxOrderList(PaginatedFilter paginatedFilter) {
		return this.dao.findWxOrderList(paginatedFilter);
	}

	@Override
	public WxCustomerOrderInfoDto findWxOrderByorderId(String orderId) {
		return this.dao.findWxOrderByorderId(orderId);
	}

	@Override
	public WxFactoryStatementDto statementWxFactory(String branchId) {
		return this.dao.statementWxFactory(branchId);
	}

	@Override
	public WxDealerInfoDto selectDealerInfo(String companyId) {
		return this.dao.selectDealerInfo(companyId);
	}

	@Override
	public OrderPrintTableDto findOrderPrintTable(MapContext mapContext) {
		return this.dao.findOrderPrintTable(mapContext);
	}

	@Override
	public OfferPrintTableDto findOfferPrintTableInfo(String id) {
		return this.dao.findOfferPrintTableInfo(id);
	}

	@Override
	public Integer findOverdueOrderCount(MapContext filter3) {
		return this.dao.findOverdueOrderCount(filter3);
	}

	@Override
	public List<CustomOrder> findByParentId(String parentId) {
		return this.dao.findByParentId(parentId);
	}

	@Override
	public PaginatedList<CustomOrderDto> findExtendIntoNextMonth(PaginatedFilter paginatedFilter) {
		return this.dao.findExtendIntoNextMonth(paginatedFilter);
	}

	@Override
	public Integer findOrderNumByNo(String no, String currBranchId) {
		return this.dao.findOrderNumByNo(no,currBranchId);
	}

	@Override
	public Integer findAllOrderCount(String currBranchId) {
		return this.dao.findAllOrderCount(currBranchId);
	}

	@Override
	public MapContext countLoginUserOrders(MapContext mapContext) {
		return this.dao.countLoginUserOrders(mapContext);
	}
	@Override
	public PaginatedList<CustomOrderDto> findSmallProgramOrderList(PaginatedFilter paginatedFilter) {
		return this.dao.findSmallProgramOrderList(paginatedFilter);
	}

	@Override
	public PaginatedList<CustomOrderDto> findOrderBySearchInfo(PaginatedFilter paginatedFilter) {
		return this.dao.findOrderBySearchInfo(paginatedFilter);
	}

	@Override
	public Integer countLoginToBeOffer(MapContext mapContext) {
		return this.dao.countLoginToBeOffer(mapContext);
	}

	@Override
	public MapContext countOrderSeries(MapContext mapContext) {
		return this.dao.countOrderSeries(mapContext);
	}
	@Override
	public MapContext selectOrderCount(MapContext map) {
		return this.dao.selectOrderCount(map);
	}

	@Override
	public MapContext selectProduceCount(MapContext map) {
		return this.dao.selectProduceCount(map);
	}

	@Override
	public MapContext selectAfterCount(MapContext map) {
		return this.dao.selectAfterCount(map);
	}

	@Override
	public MapContext findCountByBidAndType(String branchId, String monthTime) {
		return this.dao.findCountByBidAndType(branchId,monthTime);
	}

	@Override
	public MapContext selectOrderCountByDay(MapContext mapContext) {
		return this.dao.selectOrderCountByDay(mapContext);
	}

	@Override
	public List<MapContext> findDealerByTime(String branchId, String monthTime) {
		return this.dao.findDealerByTime(branchId,monthTime);
	}

	@Override
	public List<CustomOrder> findOrderCOuntByCidAndTime(String companyId, String monthTime) {
		return this.dao.findOrderCOuntByCidAndTime(companyId,monthTime);
	}

	@Override
	public List<MapContext> selectcoordinationMonthCount(String branchId, String monthTime) {
		return this.dao.selectcoordinationMonthCount(branchId,monthTime);
	}

	@Override
	public MapContext findCountAndAmountByDate(MapContext mapContext) {
		return this.dao.findCountAndAmountByDate(mapContext);
	}

	@Override
	public List<MapContext> coordinationOrderRanking(MapContext mapContext) {
		return this.dao.coordinationOrderRanking(mapContext);
	}

	@Override
	public MapContext selectcoordinationCount(MapContext mapContext) {
		return this.dao.selectcoordinationCount(mapContext);
	}

	@Override
	public MapContext selectCompanyOrderInfoCount(MapContext mapContext) {
		return this.dao.selectCompanyOrderInfoCount(mapContext);
	}

	@Override
	public List<WxBCustomOrderDto> selectRecentOrder(MapContext mapContext) {
		return this.dao.selectRecentOrder(mapContext);
	}

	@Override
	public Integer findOrderNumByemployeeUserId(MapContext mapContext) {
		return this.dao.findOrderNumByemployeeUserId(mapContext);
	}

	@Override
	public MapContext finddesignNumByemployeeUserId(MapContext mapContext) {
		return this.dao.finddesignNumByemployeeUserId(mapContext);
	}

	@Override
	public PaginatedList<MapContext> countUserAchievements(PaginatedFilter paginatedFilter) {
		return this.dao.countUserAchievements(paginatedFilter);
	}

	@Override
	public List<MapContext> findByBranchIdAndTypeAndTime(MapContext params) {
		return this.dao.findByBranchIdAndTypeAndTime(params);
	}

	@Override
	public List<CustomOrder> findByBranchIdAndStatus(MapContext mapContext) {
		return this.dao.findByBranchIdAndStatus(mapContext);
	}

	@Override
	public Integer findOrderCountByTime(MapContext mapContext) {
		return this.dao.findOrderCountByTime(mapContext);
	}

	@Override
	public Integer findDelayOrderCountByTime(MapContext mapContext) {
		return this.dao.findDelayOrderCountByTime(mapContext);
	}

    @Override
    public Integer findDelayWarningOrderCountByTime(MapContext mapContext) {
        return this.dao.findDelayWarningOrderCountByTime(mapContext);
    }

    @Override
    public Double findOrderMoneyByTime(MapContext mapContext) {
        return this.dao.findOrderMoneyByTime(mapContext);
    }

	@Override
	public Double findCoordinationMoneyByTime(MapContext mapContext) {
		return this.dao.findCoordinationMoneyByTime(mapContext);
	}

	@Override
	public Integer findCoordinationCountByTime(MapContext mapContext) {
		return this.dao.findCoordinationCountByTime(mapContext);
	}

	@Override
	public MapContext countOrderSeriesByDay(MapContext mapContext) {
		return this.dao.countOrderSeriesByDay(mapContext);
	}

	@Override
	public MapContext countNopayOrderSeriesByDay(MapContext mapContext) {
		return this.dao.countNopayOrderSeriesByDay(mapContext);
	}

	@Override
	public MapContext findOrderCountByProductType(MapContext mapContext) {
		return this.dao.findOrderCountByProductType(mapContext);
	}

	@Override
	public List<MapContext> findOrderCountByDoor(MapContext params) {
		return this.dao.findOrderCountByDoor(params);
	}

	@Override
	public List<MapContext> findOrderCountByDoorcolor(MapContext params) {
		return this.dao.findOrderCountByDoorcolor(params);
	}

	@Override
	public List<MapContext> findOrderCountBycolor(MapContext params) {
		return this.dao.findOrderCountBycolor(params);
	}

	@Override
	public MapContext findSaleBestProduct(MapContext params) {
		return this.dao.findSaleBestProduct(params);
	}

	@Override
	public MapContext findSaleBestSeries(MapContext params) {
		return this.dao.findSaleBestSeries(params);
	}

	@Override
	public Integer findAuditCountByTime(MapContext mapContext) {
		return this.dao.findAuditCountByTime(mapContext);
	}

    @Override
    public Double findAuditMoneyByTime(MapContext mapContext) {
        return this.dao.findAuditMoneyByTime(mapContext);
    }

	@Override
	public Integer findnoAuditCountByTime(MapContext mapContext) {
		return this.dao.findnoAuditCountByTime(mapContext);
	}

    @Override
    public Double findnoAuditMoneyByTime(MapContext mapContext) {
        return this.dao.findnoAuditMoneyByTime(mapContext);
    }

	@Override
	public MapContext findCupboardOrWardrobeCountByTime(MapContext mapCon) {
		return this.dao.findCupboardOrWardrobeCountByTime(mapCon);
	}

	@Override
	public MapContext findTheSaleBestDoorClolor(MapContext params) {
		return this.dao.findTheSaleBestDoorClolor(params);
	}

	@Override
	public MapContext findTheSaleBestDoor(MapContext params) {
		return this.dao.findTheSaleBestDoor(params);
	}

	@Override
	public Integer findAftersaleCountByTime(MapContext mapContext) {
		return this.dao.findAftersaleCountByTime(mapContext);
	}

	@Override
	public MapContext findPlaceOrderMost(MapContext mapContext) {
		return this.dao.findPlaceOrderMost(mapContext);
	}

	@Override
	public MapContext findPlaceOrderMoneyMost(MapContext mapContext) {
		return this.dao.findPlaceOrderMoneyMost(mapContext);
	}

	@Override
	public MapContext selectOrderProductTypeCount(String currBranchId,String productType) {
		return this.dao.selectOrderProductTypeCount(currBranchId,productType);
	}

	@Override
	public MapContext findOrderTrendByTime(MapContext params) {
		return this.dao.findOrderTrendByTime(params);
	}

	@Override
	public PaginatedList<CustomOrderDto> findDeliverGoodsList(PaginatedFilter filter) {
		return this.dao.findDeliverGoodsList(filter);
	}

	@Override
	public List<CustomOrder> findByParentIdAndType(MapContext params) {
		return this.dao.findByParentIdAndType(params);
	}

	@Override
	public Integer findAllDealerOrderCount(String dealerId) {
		return this.dao.findAllDealerOrderCount(dealerId);
	}

	@Override
	public MapContext findDealerOrderTrendByTime(MapContext params) {
		return this.dao.findDealerOrderTrendByTime(params);
	}

	@Override
	public CustomOrder findOneByNo(String no) {
		return this.dao.findOneByNo(no);
	}


}