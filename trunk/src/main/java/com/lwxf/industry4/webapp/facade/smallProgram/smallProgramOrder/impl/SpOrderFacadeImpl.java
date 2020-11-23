package com.lwxf.industry4.webapp.facade.smallProgram.smallProgramOrder.impl;

import javax.annotation.Resource;

import java.util.*;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.bizservice.customorder.*;
import com.lwxf.industry4.webapp.bizservice.dispatch.DispatchBillService;
import com.lwxf.industry4.webapp.bizservice.financing.PaymentService;
import com.lwxf.industry4.webapp.bizservice.statements.app.factory.orderStatements.CustomOrderStatementService;
import com.lwxf.industry4.webapp.bizservice.warehouse.FinishedStockService;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentFunds;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentWay;
import com.lwxf.industry4.webapp.common.enums.order.OrderStatus;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.domain.dto.customorder.*;
import com.lwxf.industry4.webapp.domain.dto.financing.PaymentDto;
import com.lwxf.industry4.webapp.domain.dto.warehouse.FinishedStockDto;
import com.lwxf.industry4.webapp.domain.dto.warehouse.FinishedStockItemDto;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.smallProgram.smallProgramOrder.SpOrderFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/10/19 0019 9:57
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("spOrderFacade")
public class SpOrderFacadeImpl extends BaseFacadeImpl implements SpOrderFacade {
	@Resource(name = "customOrderService")
	private CustomOrderService customOrderService;
	@Resource(name = "orderProductService")
	private OrderProductService orderProductService;
	@Resource(name = "paymentService")
	private PaymentService paymentService;
	@Resource(name = "dispatchBillService")
	private DispatchBillService dispatchBillService;
	@Resource(name = "finishedStockService")
	private FinishedStockService finishedStockService;
	@Resource(name = "customOrderLogService")
	private CustomOrderLogService customOrderLogService;
	@Resource(name = "customOrderDesignService")
	private CustomOrderDesignService customOrderDesignService;
	@Resource(name = "customOrderStatementService")
	private CustomOrderStatementService customOrderStatementService;
	@Resource(name = "produceOrderService")
	private ProduceOrderService produceOrderService;


	@Override
	public RequestResult findSmallProgramOrderList(Integer pageNum, Integer pageSize, MapContext mapContext) {
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		Pagination pagination = new Pagination();
		pagination.setPageNum(pageNum);
		pagination.setPageSize(pageSize);
		paginatedFilter.setPagination(pagination);
		paginatedFilter.setFilters(mapContext);
		List<Map<String, String>> sorts = new ArrayList<Map<String, String>>();
		Map<String, String> created = new HashMap<String, String>();
		created.put(WebConstant.KEY_ENTITY_CREATED, "desc");
		sorts.add(created);
		paginatedFilter.setSorts(sorts);
		PaginatedList<CustomOrderDto> customOrderDtoPaginatedList=this.customOrderService.findSmallProgramOrderList(paginatedFilter);
		for(CustomOrderDto customOrderDto:customOrderDtoPaginatedList.getRows()){
			String orderId=customOrderDto.getId();
			List<OrderProductDto> listByOrderId = this.orderProductService.findListByOrderId(orderId);
			customOrderDto.setOrderProductDtoList(listByOrderId);
			//计算剩余时间
			Date estimatedDeliveryDate = customOrderDto.getEstimatedDeliveryDate();//预计交货时间
			Date deliveryDate = customOrderDto.getDeliveryDate();//实际交货时间
			Date systemDate = DateUtil.getSystemDate();//系统当前时间
			if (customOrderDto.getDeliveryDate() != null) {//实际交货时间不为空，则订单生产已结束
				customOrderDto.setTimeRemaining("已完成");
			} else {
				if (estimatedDeliveryDate != null) {
					long nd = 1000 * 24 * 60 * 60;//一天的毫秒数
					long nh = 1000 * 60 * 60;//一小时的毫秒数
					long nm = 1000 * 60;//一分钟的毫秒数
					long ns = 1000;//一秒钟的毫秒数
					//预计交货时间毫秒
					long estimatedDeliveryDateValue = estimatedDeliveryDate.getTime();
					//系统时间
					long systemDateTime = systemDate.getTime();
					//预计交货时间毫秒-下车间的时间 = 剩余的时间
					long diff = (estimatedDeliveryDateValue - systemDateTime);
					if (diff > 0) {//如果剩余时间大于0，时间交货时间为空
						long day = diff / nd;//计算剩余多少天
						long hour = diff % nd / nh;//计算剩余多少小时
						long min = diff % nd % nh / nm;//计算剩余多少分钟
						String residueTime = day + "天" + hour + "小时" + min + "分钟";
						customOrderDto.setTimeRemaining(residueTime);
//						if (day > 1) {
//							customOrderDto.setTimeRemainingStatus(1);
//						} else if (day < 1) {
//							customOrderDto.setTimeRemainingStatus(2);
//						}
					} else {
//						wxCustomOrderInfoDto.setTimeRemainingStatus(3);
						long diffValue = systemDateTime - estimatedDeliveryDateValue;
						long day = diffValue / nd;//计算超期多少天
						long hour = diffValue % nd / nh;//计算超期多少小时
						long min = diffValue % nd % nh / nm;//计算超期多少分钟
						String residueTime = "-" + day + "天" + hour + "小时" + min + "分钟";
						customOrderDto.setTimeRemaining(residueTime);
					}
				}
			}
		}
		return ResultFactory.generateRequestResult(customOrderDtoPaginatedList);
	}

	@Override
	public RequestResult findSmallProgramOrderInfo(String branchid, String orderId) {
		WxCustomerOrderInfoDto wxCustomOrderInfoDto = this.customOrderService.findWxOrderByorderId(orderId);
		if (wxCustomOrderInfoDto == null) {
			return ResultFactory.generateResNotFoundResult();
		}
		//订单财务信息
		Integer value = PaymentFunds.ORDER_FEE_CHARGE.getValue();
		MapContext params = MapContext.newOne();
		params.put("orderId", orderId);
		params.put("funds", value);
		PaymentDto payment = this.paymentService.findByOrderIdAndFunds(params);
		if (payment != null) {
			if (payment.getStatus() == 1) {
				if (payment.getWay() != null) {
					if (payment.getWay() == PaymentWay.CASH.getValue()) {
						wxCustomOrderInfoDto.setAuditorName(payment.getAuditorName());
						wxCustomOrderInfoDto.setAudited(payment.getAudited());
					} else {
						wxCustomOrderInfoDto.setAudited(payment.getCreated());
						wxCustomOrderInfoDto.setAuditorName(payment.getHolder());
					}
				}
				wxCustomOrderInfoDto.setFinanceStatus("已到账");

			} else {
				wxCustomOrderInfoDto.setFinanceStatus("未到账");
			}
		}

		//剩余时间计算
		Date estimatedDeliveryDate = wxCustomOrderInfoDto.getEstimatedDeliveryDate();//预计交货时间
		Date deliveryDate = wxCustomOrderInfoDto.getDeliveryDate();//实际交货时间
		Date systemDate = DateUtil.getSystemDate();//系统当前时间
		if (deliveryDate != null) {//实际交货时间不为空，则订单生产已结束
			wxCustomOrderInfoDto.setTimeRemaining("00:00:00");
			wxCustomOrderInfoDto.setTimeRemainingStatus(0);
		} else {
			if (estimatedDeliveryDate != null) {
				long nd = 1000 * 24 * 60 * 60;//一天的毫秒数
				long nh = 1000 * 60 * 60;//一小时的毫秒数
				long nm = 1000 * 60;//一分钟的毫秒数
				long ns = 1000;//一秒钟的毫秒数
				//预计交货时间毫秒
				long estimatedDeliveryDateValue = estimatedDeliveryDate.getTime();
				//系统时间
				long systemDateTime = systemDate.getTime();
				//预计交货时间毫秒-下车间的时间 = 剩余的时间
				long diff = (estimatedDeliveryDateValue - systemDateTime);
				if (diff > 0) {//如果剩余时间大于0，时间交货时间为空
					long day = diff / nd;//计算剩余多少天
					long hour = diff % nd / nh;//计算剩余多少小时
					long min = diff % nd % nh / nm;//计算剩余多少分钟
					String residueTime = day + "天" + hour + "小时" + min + "分钟";
					wxCustomOrderInfoDto.setTimeRemaining(residueTime);
					if (day > 1) {
						wxCustomOrderInfoDto.setTimeRemainingStatus(1);
					} else if (day < 1) {
						wxCustomOrderInfoDto.setTimeRemainingStatus(2);
					}
				} else {
					wxCustomOrderInfoDto.setTimeRemainingStatus(3);
					long diffValue = systemDateTime - estimatedDeliveryDateValue;
					long day = diffValue / nd;//计算超期多少天
					long hour = diffValue % nd / nh;//计算超期多少小时
					long min = diffValue % nd % nh / nm;//计算超期多少分钟
					String residueTime = "-" + day + "天" + hour + "小时" + min + "分钟";
					wxCustomOrderInfoDto.setTimeRemaining(residueTime);
				}
			}
		}
		//产品信息
		List<OrderProductDto> orderProductDtos = this.orderProductService.findListByOrderId(orderId);
		wxCustomOrderInfoDto.setOrderProduct(orderProductDtos);
		//设计信息
		List<CustomOrderDesignDto> customOrderDesignDtos = this.customOrderDesignService.findListByOrderId(orderId);
		wxCustomOrderInfoDto.setOrderDesign(customOrderDesignDtos);
		//生产信息
		List list = new ArrayList();
		Map map = new HashMap();
		map.put("documentaryTime", wxCustomOrderInfoDto.getDocumentaryTime());
		map.put("merchandiserName", wxCustomOrderInfoDto.getMerchandiserName());
		map.put("documentaryNotes", wxCustomOrderInfoDto.getDocumentaryNotes());
		list.add(map);
		wxCustomOrderInfoDto.setOrderProduce(list);
		//入库信息
		List<FinishedStockDto> finishedStockDtos = this.finishedStockService.findWxFinishedList(orderId);
		if(finishedStockDtos!=null) {
			wxCustomOrderInfoDto.setOrderFinishStock(finishedStockDtos);
		}
		if(finishedStockDtos!=null) {
			wxCustomOrderInfoDto.setOrderFinishStock(finishedStockDtos);
		}
		//生产单信息
		MapContext mapProduce=MapContext.newOne();
		mapProduce.put("id",orderId);
		List<ProduceOrderDto> listByOrderId = this.produceOrderService.findListByOrderId(mapProduce);
		wxCustomOrderInfoDto.setProducesList(listByOrderId);
		//发货信息
		if (finishedStockDtos.size() > 0) {
			List itemids = new ArrayList();
			for (FinishedStockDto finishedStockDto : finishedStockDtos) {//获取所有的包裹id
				List<FinishedStockItemDto> list1 = finishedStockDto.getFinishedStockItemDtos();
				for (FinishedStockItemDto finishedStockItemDto : list1) {
					String itemid = finishedStockItemDto.getId();
					itemids.add(itemid);
				}
			}
			List<Map> mapList = this.dispatchBillService.findDispatchListByFinishedItemId(itemids);
			for (Map map1 : mapList) {
				String dispatchBillId = map1.get("dispatchBillId").toString();
				List<FinishedStockDto> list1 = this.dispatchBillService.findFinishedItemTypeByDispatchId(dispatchBillId, itemids);
				if(list1==null||list1.size()==0){
					mapList.remove(map1);
				}else {
					map1.put("typeNameList", list1);
				}
			}

			wxCustomOrderInfoDto.setOrderDispatchBill(mapList);
		}
		return ResultFactory.generateRequestResult(wxCustomOrderInfoDto);
	}

	@Override
	public RequestResult findOrderIndexInfo(MapContext mapContext) {
		//所有订单
		Integer allOrderCount = this.customOrderStatementService.findAllOrderCountByBidAndType(mapContext);
		//完成订单数
		mapContext.put("status", OrderStatus.SHIPPED.getValue());
		Integer endOrderCount = this.customOrderStatementService.findAllOrderCountByBidAndType(mapContext);
		//未完成订单数
		Integer noEndOrderCount = allOrderCount - endOrderCount;
		//建单数量
		mapContext.remove("status");
		List list = new ArrayList();
		for (int i = 0; i < 5; i++) {
			int timeType = i;
			mapContext.put("timeType",timeType);
			Integer creatCount = this.customOrderStatementService.findAllOrderCountByBidAndType(mapContext);
			MapContext mapContext2 = MapContext.newOne();
			switch (i) {
				case 0:
					mapContext2.put("timeType", "今天");
				case 1:
					mapContext2.put("timeType", "本周");
				case 2:
					mapContext2.put("timeType", "本月");
				case 3:
					mapContext2.put("timeType", "本季");
				case 4:
					mapContext2.put("timeType", "本年");
			}
			mapContext2.put("count", creatCount);
			list.add(mapContext2);
		}
		return null;
	}
}
