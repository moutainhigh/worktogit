package com.lwxf.industry4.webapp.domain.dao.customorder.impl;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.lwxf.industry4.webapp.domain.dto.dealer.WxDealerInfoDto;
import com.lwxf.industry4.webapp.domain.dto.printTable.OfferPrintTableDto;
import com.lwxf.industry4.webapp.domain.dto.printTable.OrderPrintTableDto;
import com.lwxf.industry4.webapp.domain.dto.statement.WxFactoryStatementDto;
import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;


import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.customorder.CustomOrderDao;
import com.lwxf.industry4.webapp.domain.dto.aftersale.DateNum;
import com.lwxf.industry4.webapp.domain.dto.customorder.*;
import com.lwxf.industry4.webapp.domain.dto.dealer.DealerOrderRankDto;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrder;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Repository;

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
@Repository("customOrderDao")
public class CustomOrderDaoImpl extends BaseDaoImpl<CustomOrder, String> implements CustomOrderDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<CustomOrder> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<CustomOrder> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}
	@Override
	public PaginatedList<CustomOrderDto> selectByCondition(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByCondition");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<CustomOrderDto> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public CustomOrderDto findByOrderId(String orderId) {
		String sqlId = this.getNamedSqlId("findByOrderId");
		return this.getSqlSession().selectOne(sqlId,orderId);
	}

	@Override
	public PaginatedList<CustomOrderDto> findFinishedOrderList(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findFinishedOrderList");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<CustomOrderDto> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public PaginatedList<OrderQuoteDto> findOrderQuoteMessage(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findOrderQuoteMessage");
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<OrderQuoteDto> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);

	}

	@Override
	public PaginatedList<CustomOrderDto> findListByPaginateFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findListByPaginateFilter");
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<CustomOrderDto> pageList =  (PageList)this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}
	@Override
	public PaginatedList<CustomOrderDto> findByCompanyIdAndStatus(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findByCompanyIdAndStatus");
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<CustomOrderDto> pageList =  (PageList)this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}


	@Override
	public List<CustomOrderDto> findByCompanyIdAndStatus(MapContext params) {
		String sqlId = this.getNamedSqlId("findByCompanyIdAndStatus");
		return this.getSqlSession().selectList(sqlId,params);
	}


	@Override
	public PaginatedList<Map> findSpecimenOrderList(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findSpecimenOrderList");
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<Map> pageList =  (PageList)this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}


	@Override
	public Integer findOrderCountByStatus(MapContext mapContext) {
		String sqlId = this.getNamedSqlId("findOrderCountByStatus");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public OrderCountDto findOrderNumByUidAndCid(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findOrderNumByUidAndCid");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public List<CustomOrder> findByCidAndSalemanId(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findByCidAndSalemanId");
		return this.getSqlSession().selectList(sqlId,mapContext);
	}

	@Override
	public Integer findOrderMoneyCount(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findOrderMoneyCount");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public BigDecimal findpayAmountByOrderId(String orderId) {
		String sqlId=this.getNamedSqlId("findpayAmountByOrderId");
		return this.getSqlSession().selectOne(sqlId,orderId);
	}

	@Override
	public List<DateNum> findOrderNumByCreatedAndCid(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findOrderNumByCreatedAndCid");
		return this.getSqlSession().selectList(sqlId,mapContext);
	}
	@Override
	public List<MapContext> findOrderListByCidAndUid(String userId, String dealerId) {
		String sqlId=this.getNamedSqlId("findOrderListByCidAndUid");
		MapContext mapContext=MapContext.newOne();
		mapContext.put("userId",userId);
		mapContext.put("companyId",dealerId);
		return this.getSqlSession().selectList(sqlId,mapContext);
	}


	@Override
	public Map findFAppBaseInfoByOrderId(MapContext params) {
		String sqlId = this.getNamedSqlId("findFAppBaseInfoByOrderId");
		return this.getSqlSession().selectOne(sqlId,params);
	}

	@Override
	public List<CustomOrder> findByIds(Set orderIds) {
		String sqlId = this.getNamedSqlId("findByIds");
		return this.getSqlSession().selectList(sqlId,orderIds);
	}

	@Override
	public int updateOrderStatusByIds(List startOrderIds, Integer status) {
		String sqlId = this.getNamedSqlId("updateOrderStatusByIds");
		MapContext mapContext = new MapContext();
		mapContext.put("ids",startOrderIds);
		mapContext.put(WebConstant.KEY_ENTITY_STATUS,status);
		mapContext.put("documentaryTime",DateUtil.getSystemDate());
		return this.getSqlSession().update(sqlId,mapContext);
	}

	@Override
	public PaginatedList<CustomOrderDto> findPacksOrderList(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findPacksOrderList");
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<CustomOrderDto> pageList =  (PageList)this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public List<CustomOrder> findByCustomerIdAndCid(String uId, String branchId) {
		MapContext mapContext=MapContext.newOne();
		mapContext.put("uId",uId);
		mapContext.put("branchId",branchId);
		String sqlId=this.getNamedSqlId("findByCustomerIdAndCid");
		return this.getSqlSession().selectList(sqlId,mapContext);
	}

	@Override
	public List<CustomOrder> findByCustomerIdAndCidAndStatus(String uId, String branchId, Integer status) {
		MapContext mapContext=MapContext.newOne();
		mapContext.put("uId",uId);
		mapContext.put("branchId",branchId);
		mapContext.put("status",status);
		String sqlId=this.getNamedSqlId("findByCustomerIdAndCidAndStatus");
		return this.getSqlSession().selectList(sqlId,mapContext);
	}

	@Override
	public CustomOrder findByUidAndBranchId(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findByUidAndBranchId");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public CustomOrder findByCidAndBranchId(String dealerId, String branchId) {
		MapContext mapContext=MapContext.newOne();
		mapContext.put("cId",dealerId);
		mapContext.put("branchId",branchId);
		String sqlId=this.getNamedSqlId("findByCidAndBranchId");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public Integer findTodayOrderCount(MapContext param1) {

		String sqlId=this.getNamedSqlId("findTodayOrderCount");
		return this.getSqlSession().selectOne(sqlId,param1);
	}

	@Override
	public Integer findTodayInvalidOrder(MapContext param2) {
		String sqlId=this.getNamedSqlId("findTodayInvalidOrder");
		return this.getSqlSession().selectOne(sqlId,param2);
	}

	@Override
	public Integer findTodayEffectiveOrder(MapContext param2) {
		String sqlId=this.getNamedSqlId("findTodayEffectiveOrder");
		return this.getSqlSession().selectOne(sqlId,param2);
	}

	@Override
	public PaginatedList<WxCustomOrderDto> findWxOrderList(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findWxOrderList");
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<WxCustomOrderDto> pageList =  (PageList)this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public WxCustomerOrderInfoDto findWxOrderByorderId(String orderId) {
		String sqlId=this.getNamedSqlId("findWxOrderByorderId");
		return this.getSqlSession().selectOne(sqlId,orderId);
	}

	@Override
	public WxFactoryStatementDto statementWxFactory(String branchId) {
		String sqlId=this.getNamedSqlId("statementWxFactory");
		return this.getSqlSession().selectOne(sqlId,branchId);
	}

	@Override
	public WxDealerInfoDto selectDealerInfo(String companyId) {
		String sqlId=this.getNamedSqlId("selectDealerInfo");
		return this.getSqlSession().selectOne(sqlId,companyId);
	}

	@Override
	public OrderPrintTableDto findOrderPrintTable(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findOrderPrintTable");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public OfferPrintTableDto findOfferPrintTableInfo(String id) {
		String sqlId = this.getNamedSqlId("findOfferPrintTableInfo");
		return this.getSqlSession().selectOne(sqlId,id);
	}

	@Override
	public Integer findOverdueOrderCount(MapContext filter3) {
		String sqlId = this.getNamedSqlId("findOverdueOrderCount");
		return this.getSqlSession().selectOne(sqlId,filter3);
	}

	@Override
	public List<CustomOrder> findByParentId(String parentId) {
		String sqlId = this.getNamedSqlId("findByParentId");
		return this.getSqlSession().selectList(sqlId,parentId);
	}

	@Override
	public PaginatedList<CustomOrderDto> findExtendIntoNextMonth(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findExtendIntoNextMonth");
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<CustomOrderDto> pageList =  (PageList)this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public Integer findOrderNumByNo(String no, String currBranchId) {
		MapContext mapContext=MapContext.newOne();
		mapContext.put("no",no);
		mapContext.put("branchId",currBranchId);
		String sqlId=this.getNamedSqlId("findOrderNumByNo");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public Integer findAllOrderCount(String currBranchId) {
		String sqlId=this.getNamedSqlId("findAllOrderCount");
		return this.getSqlSession().selectOne(sqlId,currBranchId);
	}

	@Override
	public MapContext countLoginUserOrders(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("countLoginUserOrders");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}
	@Override
	public PaginatedList<CustomOrderDto> findSmallProgramOrderList(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findSmallProgramOrderList");
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<CustomOrderDto> pageList =  (PageList)this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public PaginatedList<CustomOrderDto> findOrderBySearchInfo(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findOrderBySearchInfo");
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<CustomOrderDto> pageList =  (PageList)this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public Integer countLoginToBeOffer(MapContext mapContext) {
		String sqlId = this.getNamedSqlId("countLoginToBeOffer");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public MapContext countOrderSeries(MapContext mapContext) {
		String sqlId = this.getNamedSqlId("countOrderSeries");
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
	public List<MapContext> selectcoordinationMonthCount(String branchId, String monthTime) {
		MapContext map=MapContext.newOne();
		map.put("branchId",branchId);
		map.put("monthTime",monthTime);
		String sqlId=this.getNamedSqlId("selectcoordinationMonthCount");
		return this.getSqlSession().selectList(sqlId,map);
	}

	@Override
	public MapContext findCountAndAmountByDate(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findCountAndAmountByDate");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public List<MapContext> coordinationOrderRanking(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("coordinationOrderRanking");
		return this.getSqlSession().selectList(sqlId,mapContext);
	}

	@Override
	public MapContext selectcoordinationCount(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("selectcoordinationCount");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public MapContext selectCompanyOrderInfoCount(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("selectCompanyOrderInfoCount");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public Integer findOrderNumByemployeeUserId(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findOrderNumByemployeeUserId");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public MapContext finddesignNumByemployeeUserId(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("finddesignNumByemployeeUserId");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public PaginatedList<MapContext> countUserAchievements(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("countUserAchievements");
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<MapContext> pageList =  (PageList)this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public List<MapContext> findByBranchIdAndTypeAndTime(MapContext params) {
		String sqlId = this.getNamedSqlId("findByBranchIdAndTypeAndTime");
		return this.getSqlSession().selectList(sqlId, params);
	}

	@Override
	public List<CustomOrder> findByBranchIdAndStatus(MapContext mapContext) {
		String sqlId = this.getNamedSqlId("findByBranchIdAndStatus");
		return this.getSqlSession().selectList(sqlId, mapContext);
	}

	@Override
	public Integer findOrderCountByTime(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findOrderCountByTime");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public Integer findDelayOrderCountByTime(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findDelayOrderCountByTime");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public Integer findDelayWarningOrderCountByTime(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findDelayWarningOrderCountByTime");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public Double findOrderMoneyByTime(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findOrderMoneyByTime");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public Double findCoordinationMoneyByTime(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findCoordinationMoneyByTime");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public Integer findCoordinationCountByTime(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findCoordinationCountByTime");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public MapContext countOrderSeriesByDay(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("countOrderSeriesByDay");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public MapContext countNopayOrderSeriesByDay(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("countNopayOrderSeriesByDay");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public MapContext findOrderCountByProductType(MapContext mapContext) {
		String sqlId = this.getNamedSqlId("findOrderCountByProductType");
		return this.getSqlSession().selectOne(sqlId, mapContext);
	}

	@Override
	public List<MapContext> findOrderCountByDoor(MapContext params) {
		String sqlId = this.getNamedSqlId("findOrderCountByDoor");
		return this.getSqlSession().selectList(sqlId, params);
	}

	@Override
	public List<MapContext> findOrderCountByDoorcolor(MapContext params) {
		String sqlId = this.getNamedSqlId("findOrderCountByDoorcolor");
		return this.getSqlSession().selectList(sqlId, params);
	}

	@Override
	public List<MapContext> findOrderCountBycolor(MapContext params) {
		String sqlId = this.getNamedSqlId("findOrderCountBycolor");
		return this.getSqlSession().selectList(sqlId, params);
	}

	@Override
	public MapContext findSaleBestProduct(MapContext params) {
		String sqlId=this.getNamedSqlId("findSaleBestProduct");
		return this.getSqlSession().selectOne(sqlId,params);
	}

	@Override
	public MapContext findSaleBestSeries(MapContext params) {
		String sqlId=this.getNamedSqlId("findSaleBestSeries");
		return this.getSqlSession().selectOne(sqlId,params);
	}

	@Override
	public Integer findAuditCountByTime(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findAuditCountByTime");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public Double findAuditMoneyByTime(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findAuditMoneyByTime");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public Integer findnoAuditCountByTime(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findnoAuditCountByTime");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

    @Override
    public Double findnoAuditMoneyByTime(MapContext mapContext) {
        String sqlId=this.getNamedSqlId("findnoAuditMoneyByTime");
        return this.getSqlSession().selectOne(sqlId,mapContext);
    }

	@Override
	public MapContext findCupboardOrWardrobeCountByTime(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findCupboardOrWardrobeCountByTime");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public MapContext findTheSaleBestDoor(MapContext params) {
		String sqlId=this.getNamedSqlId("findTheSaleBestDoor");
		return this.getSqlSession().selectOne(sqlId,params);
	}

	@Override
	public MapContext findTheSaleBestDoorClolor(MapContext params) {
		String sqlId=this.getNamedSqlId("findTheSaleBestDoorClolor");
		return this.getSqlSession().selectOne(sqlId,params);
	}

	@Override
	public Integer findAftersaleCountByTime(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findAftersaleCountByTime");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public MapContext findPlaceOrderMost(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findPlaceOrderMost");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public MapContext findPlaceOrderMoneyMost(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findPlaceOrderMoneyMost");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public MapContext selectOrderProductTypeCount(String currBranchId,String productType) {
		MapContext mapContext=MapContext.newOne();
		mapContext.put("branchId",currBranchId);
		mapContext.put("productType",productType);
		String sqlId = this.getNamedSqlId("selectOrderProductTypeCount");
		return this.getSqlSession().selectOne(sqlId, mapContext);
	}

	@Override
	public MapContext findOrderTrendByTime(MapContext params) {
		String sqlId = this.getNamedSqlId("findOrderTrendByTime");
		return this.getSqlSession().selectOne(sqlId, params);
	}

	@Override
	public PaginatedList<CustomOrderDto> findDeliverGoodsList(PaginatedFilter filter) {
		String sqlId = this.getNamedSqlId("findDeliverGoodsList");
		PageBounds pageBounds = this.toPageBounds(filter.getPagination(), filter.getSorts());
		PageList<CustomOrderDto> pageList = (PageList) this.getSqlSession().selectList(sqlId, filter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public List<CustomOrder> findByParentIdAndType(MapContext params) {
		String sqlId = this.getNamedSqlId("findByParentIdAndType");
		return this.getSqlSession().selectList(sqlId, params);
	}

	@Override
	public Integer findAllDealerOrderCount(String dealerId) {
		String sqlId = this.getNamedSqlId("findAllDealerOrderCount");
		return this.getSqlSession().selectOne(sqlId, dealerId);
	}

	@Override
	public MapContext findDealerOrderTrendByTime(MapContext params) {
		String sqlId = this.getNamedSqlId("findDealerOrderTrendByTime");
		return this.getSqlSession().selectOne(sqlId, params);
	}

	@Override
	public CustomOrder findOneByNo(String no) {
		String sqlId = this.getNamedSqlId("findOneByNo");
		return this.getSqlSession().selectOne(sqlId, no);
	}

	@Override
	public List<WxBCustomOrderDto> selectRecentOrder(MapContext mapContext) {
		String sqlId = this.getNamedSqlId("selectRecentOrder");
		return this.getSqlSession().selectList(sqlId, mapContext);
	}

}