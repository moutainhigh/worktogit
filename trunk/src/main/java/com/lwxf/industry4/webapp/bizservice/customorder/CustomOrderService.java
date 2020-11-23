package com.lwxf.industry4.webapp.bizservice.customorder;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.dto.aftersale.DateNum;
import com.lwxf.industry4.webapp.domain.dto.customorder.*;
import com.lwxf.industry4.webapp.domain.dto.dealer.DealerOrderRankDto;
import com.lwxf.industry4.webapp.domain.dto.dealer.WxDealerInfoDto;
import com.lwxf.industry4.webapp.domain.dto.printTable.OfferPrintTableDto;
import com.lwxf.industry4.webapp.domain.dto.printTable.OrderPrintTableDto;
import com.lwxf.industry4.webapp.domain.dto.statement.WxFactoryStatementDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrder;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-04 17:48:28
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface CustomOrderService extends BaseService <CustomOrder, String> {

	PaginatedList<CustomOrder> selectByFilter(PaginatedFilter paginatedFilter);
	PaginatedList<CustomOrderDto> selectByCondition(PaginatedFilter paginatedFilter);

	CustomOrderDto findByOrderId (String orderId);

	PaginatedList<CustomOrderDto> findFinishedOrderList(PaginatedFilter paginatedFilter);


	PaginatedList<OrderQuoteDto> findOrderQuoteMessage(PaginatedFilter paginatedFilter);

	List<CustomOrderDto>  findByCompanyIdAndStatus(MapContext params);

	PaginatedList<CustomOrderDto> findListByPaginateFilter(PaginatedFilter paginatedFilter);

	PaginatedList<CustomOrderDto> findByCompanyIdAndStatus(PaginatedFilter paginatedFilter);


	PaginatedList<Map>  findSpecimenOrderList(PaginatedFilter paginatedFilter);


	Integer findOrderCountByStatus(MapContext mapContext);

	OrderCountDto findOrderNumByUidAndCid(MapContext mapContext);

	List<CustomOrder> findByCidAndSalemanId(MapContext mapContext);

	Integer findOrderMoneyCount(MapContext mapContext);

	BigDecimal findpayAmountByOrderId(String orderId);

	List<DateNum> findOrderNumByCreatedAndCid(MapContext mapContext);

	List<MapContext> findOrderListByCidAndUid(String dealerId, String userId);

	Map findFAppBaseInfoByOrderId(MapContext params);

	List<CustomOrder> findByIds(Set orderIds);

	int updateOrderStatusByIds(List startOrderIds, Integer status);

	PaginatedList<CustomOrderDto> findPacksOrderList(PaginatedFilter paginatedFilter);

	List<CustomOrder> findByCustomerIdAndCid(String uId, String branchId);

	List<CustomOrder> findByCustomerIdAndCidAndStatus(String uId, String branchId, Integer status);

	CustomOrder findByUidAndBranchId(MapContext mapContext);

	CustomOrder findByCidAndBranchId(String dealerId, String branchId);

	Integer findTodayOrderCount(MapContext param1);

	Integer findTodayInvalidOrder(MapContext param2);

	Integer findTodayEffectiveOrder(MapContext param2);

	PaginatedList<WxCustomOrderDto> findWxOrderList(PaginatedFilter paginatedFilter);

	WxCustomerOrderInfoDto findWxOrderByorderId(String orderId);
	//微信工厂端报表
	WxFactoryStatementDto statementWxFactory(String branchId);

	//微信经销商端首页
	WxDealerInfoDto selectDealerInfo(String companyId);


	OrderPrintTableDto findOrderPrintTable(MapContext mapContext);

	OfferPrintTableDto findOfferPrintTableInfo(String id);

	Integer findOverdueOrderCount(MapContext filter3);


	List<CustomOrder> findByParentId(String parentId);

	PaginatedList<CustomOrderDto> findExtendIntoNextMonth(PaginatedFilter paginatedFilter);

	Integer findOrderNumByNo(String no, String currBranchId);

	Integer findAllOrderCount(String currBranchId);

	MapContext countLoginUserOrders(MapContext mapContext);

	PaginatedList<CustomOrderDto> findSmallProgramOrderList(PaginatedFilter paginatedFilter);

	PaginatedList<CustomOrderDto> findOrderBySearchInfo(PaginatedFilter paginatedFilter);

	Integer countLoginToBeOffer(MapContext mapContext);

	MapContext countOrderSeries(MapContext mapContext);
	MapContext selectOrderCount(MapContext map);
	MapContext selectProduceCount(MapContext map);
	MapContext selectAfterCount(MapContext map);
	MapContext findCountByBidAndType(String branchId, String monthTime);

	MapContext selectOrderCountByDay(MapContext mapContext);

	List<MapContext> findDealerByTime(String branchId, String monthTime);

	List<CustomOrder> findOrderCOuntByCidAndTime(String companyId, String monthTime);

	List<MapContext> selectcoordinationMonthCount(String branchId, String monthTime);

	MapContext findCountAndAmountByDate(MapContext mapContext);

	List<MapContext> coordinationOrderRanking(MapContext mapContext);

	MapContext selectcoordinationCount(MapContext mapContext);

	MapContext selectCompanyOrderInfoCount(MapContext mapContext);

	List<WxBCustomOrderDto> selectRecentOrder(MapContext mapContext);

	Integer findOrderNumByemployeeUserId(MapContext mapContext);

	MapContext finddesignNumByemployeeUserId(MapContext mapContext);


	PaginatedList<MapContext> countUserAchievements(PaginatedFilter paginatedFilter);

	List<MapContext> findByBranchIdAndTypeAndTime(MapContext params);

	List<CustomOrder> findByBranchIdAndStatus(MapContext mapContext);

	Integer findOrderCountByTime(MapContext mapContext);

	Integer findDelayOrderCountByTime(MapContext mapContext);

	Integer findDelayWarningOrderCountByTime(MapContext mapContext);

	Double findOrderMoneyByTime(MapContext mapContext);

	Double findCoordinationMoneyByTime(MapContext mapContext);

	Integer findCoordinationCountByTime(MapContext mapContext);

	MapContext countOrderSeriesByDay(MapContext mapContext);

	MapContext countNopayOrderSeriesByDay(MapContext mapContext);

	MapContext findOrderCountByProductType(MapContext mapContext);

	List<MapContext> findOrderCountByDoor(MapContext params);

	List<MapContext> findOrderCountByDoorcolor(MapContext params);

	List<MapContext> findOrderCountBycolor(MapContext params);

	MapContext findSaleBestProduct(MapContext params);

	MapContext findSaleBestSeries(MapContext params);

	Integer findAuditCountByTime(MapContext mapContext);

	Double findAuditMoneyByTime(MapContext mapContext);

	Integer findnoAuditCountByTime(MapContext mapContext);

	Double findnoAuditMoneyByTime(MapContext mapContext);

	MapContext findCupboardOrWardrobeCountByTime(MapContext mapCon);

	MapContext findTheSaleBestDoorClolor(MapContext params);

	MapContext findTheSaleBestDoor(MapContext params);

	Integer findAftersaleCountByTime(MapContext mapContext);

	MapContext findPlaceOrderMost(MapContext mapContext);

	MapContext findPlaceOrderMoneyMost(MapContext mapContext);

	MapContext selectOrderProductTypeCount(String currBranchId,String productType);

    MapContext findOrderTrendByTime(MapContext params);

    PaginatedList<CustomOrderDto> findDeliverGoodsList(PaginatedFilter filter);

	List<CustomOrder> findByParentIdAndType(MapContext params);

	Integer findAllDealerOrderCount(String dealerId);

    MapContext findDealerOrderTrendByTime(MapContext params);

    CustomOrder findOneByNo(String no);
}