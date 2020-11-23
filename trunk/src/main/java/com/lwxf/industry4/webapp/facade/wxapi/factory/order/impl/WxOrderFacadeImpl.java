package com.lwxf.industry4.webapp.facade.wxapi.factory.order.impl;

import javax.annotation.Resource;

import java.util.*;

import org.springframework.stereotype.Component;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.bizservice.company.CompanyService;
import com.lwxf.industry4.webapp.bizservice.customorder.CustomOrderDesignService;
import com.lwxf.industry4.webapp.bizservice.customorder.CustomOrderLogService;
import com.lwxf.industry4.webapp.bizservice.customorder.CustomOrderService;
import com.lwxf.industry4.webapp.bizservice.customorder.OrderProductService;
import com.lwxf.industry4.webapp.bizservice.dept.DeptMemberService;
import com.lwxf.industry4.webapp.bizservice.dept.DeptService;
import com.lwxf.industry4.webapp.bizservice.dispatch.DispatchBillItemService;
import com.lwxf.industry4.webapp.bizservice.dispatch.DispatchBillService;
import com.lwxf.industry4.webapp.bizservice.financing.PaymentService;
import com.lwxf.industry4.webapp.bizservice.warehouse.FinishedStockItemService;
import com.lwxf.industry4.webapp.bizservice.warehouse.FinishedStockService;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.company.CompanyStatus;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentFunds;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentWay;
import com.lwxf.industry4.webapp.common.enums.order.OrderProductStatus;
import com.lwxf.industry4.webapp.common.enums.order.OrderStatus;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dao.financing.PaymentDao;
import com.lwxf.industry4.webapp.domain.dto.customorder.*;
import com.lwxf.industry4.webapp.domain.dto.financing.PaymentDto;
import com.lwxf.industry4.webapp.domain.dto.warehouse.FinishedStockDto;
import com.lwxf.industry4.webapp.domain.dto.warehouse.FinishedStockItemDto;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderLog;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.wxapi.factory.order.WxOrderFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/6/15 0015 11:07
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component(value = "wxOrderFacade")
public class WxOrderFacadeImpl extends BaseFacadeImpl implements WxOrderFacade {
	@Resource(name = "customOrderService")
	private CustomOrderService customOrderService;
	@Resource(name = "orderProductService")
	private OrderProductService orderProductService;
	@Resource(name = "customOrderDesignService")
	private CustomOrderDesignService customOrderDesignService;
	@Resource(name = "paymentService")
	private PaymentService paymentService;
	@Resource(name = "dispatchBillItemService")
	private DispatchBillItemService dispatchBillItemService;
	@Resource(name = "finishedStockItemService")
	private FinishedStockItemService finishedStockItemService;
	@Resource(name = "dispatchBillService")
	private DispatchBillService dispatchBillService;
	@Resource(name = "finishedStockService")
	private FinishedStockService finishedStockService;
	@Resource(name = "deptService")
	private DeptService deptService;
	@Resource(name = "deptMemberService")
	private DeptMemberService deptMemberService;
	@Resource(name="companyService")
	private CompanyService companyService;
	@Resource(name = "customOrderLogService")
	private CustomOrderLogService customOrderLogService;




	@Override
	public RequestResult findWxOrderList(String branchId, Integer pageNum, Integer pageSize, MapContext mapContext) {
//		//今日统计
//		List count=new ArrayList();
//		//合计
//		MapContext param1=MapContext.newOne();
//		param1.put("branchId",branchId);
//		Integer total = this.customOrderService.findTodayOrderCount(param1);
//		MapContext mapContext1=MapContext.newOne();
//		mapContext1.put("name", "合计");
//		mapContext1.put("value",total);
//		count.add(mapContext1);
//		Integer status = OrderStatus.TO_PAID.getValue();
//		//无效（未付款订单）
//		MapContext param2=MapContext.newOne();
//		param2.put("branchId",branchId);
//		param2.put("status",status);
//		Integer invalid = this.customOrderService.findTodayInvalidOrder(param2);
//		MapContext mapContext2=MapContext.newOne();
//		mapContext2.put("name", "无效");
//		mapContext2.put("value",invalid);
//		count.add(mapContext2);
//		//有效（订单已支付货款）
//		Integer effective = this.customOrderService.findTodayEffectiveOrder(param2);
//		MapContext mapContext3=MapContext.newOne();
//		mapContext3.put("name", "有效");
//		mapContext3.put("value",effective);
//		count.add(mapContext3);

		//列表分页查询
		Pagination pagination = new Pagination();//设置分页信息
		pagination.setPageNum(pageNum);
		pagination.setPageSize(pageSize);
		PaginatedFilter paginatedFilter = new PaginatedFilter();//查询条件
		mapContext.put("branchId",branchId);
		paginatedFilter.setFilters(mapContext);
		paginatedFilter.setPagination(pagination);
		PaginatedList<WxCustomOrderDto> customOrderDtoPaginatedList = this.customOrderService.findWxOrderList(paginatedFilter);
		for(WxCustomOrderDto customOrderDto:customOrderDtoPaginatedList.getRows()){
			List<String> seriesNames=new ArrayList<>();
			String orderId=customOrderDto.getOrderId();
			List<OrderProductDto> listByOrderId = this.orderProductService.findListByOrderId(orderId);
			for(OrderProductDto productDto:listByOrderId){
				String seriesName=productDto.getSeriesName();
				seriesNames.add(seriesName);
			}
			if(customOrderDto.getOrderProductType()!=null) {
				if (customOrderDto.getOrderProductType().equals("0")) {
					customOrderDto.setOrderProductType("橱柜");
				} else if(customOrderDto.getOrderProductType().equals("1")){
					customOrderDto.setOrderProductType("衣柜");
				}
				else if(customOrderDto.getOrderProductType().equals("4")){
					customOrderDto.setOrderProductType("五金");
				}
				else if(customOrderDto.getOrderProductType().equals("5")){
					customOrderDto.setOrderProductType("样块");
				}
			}
			customOrderDto.setSeriesNames(seriesNames);
			customOrderDto.setProductNum(listByOrderId.size());
		}
		MapContext result = MapContext.newOne();
		result.put("CustomOrderlist", customOrderDtoPaginatedList.getRows());
		//result.put("count",count);
		return ResultFactory.generateRequestResult(result, customOrderDtoPaginatedList.getPagination());
	}

	/**
	 * 查询订单信息
	 *
	 * @param branchId
	 * @param orderId
	 * @return
	 */
	@Override
	public RequestResult findWxOrderInfo(String branchId, String orderId) {
		WxCustomerOrderInfoDto wxCustomOrderInfoDto = this.customOrderService.findWxOrderByorderId(orderId);
		if (wxCustomOrderInfoDto == null) {
			return ResultFactory.generateResNotFoundResult();
		}
		if(wxCustomOrderInfoDto.getType()==0){
			wxCustomOrderInfoDto.setOrderType("正常订单");
		}else {
			wxCustomOrderInfoDto.setOrderType("售后订单");
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
			wxCustomOrderInfoDto.setPayAmount(payment.getPayAmount());
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
			}else {
				wxCustomOrderInfoDto.setTimeRemainingStatus(1);
				wxCustomOrderInfoDto.setTimeRemaining("未付款不排期");
			}
		}
		//产品信息、入库信息、物流信息
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
			return ResultFactory.generateRequestResult(wxCustomOrderInfoDto);

	}

    @Override
    public RequestResult QueryOrdersTrace(String orderId) {
        WxCustomerOrderInfoDto wxCustomOrderInfoDto = this.customOrderService.findWxOrderByorderId(orderId);
        if (wxCustomOrderInfoDto == null) {//不存在订单就不查询
            return ResultFactory.generateResNotFoundResult();
        }


        LinkedHashMap<String, List<CustomOrderLogDto>> LinkedHashMap = new LinkedHashMap<>();
        MapContext params=MapContext.newOne();
        params.put("orderId",orderId);
        List<CustomOrderLogDto> customOrderLogDtos = this.customOrderLogService.findByOrderId(params);
        for (CustomOrderLogDto c : customOrderLogDtos) {
            String name = c.getName();//状态名称
            List<CustomOrderLogDto> customOrderLogDtos2 = LinkedHashMap.get(name);
            if (customOrderLogDtos2 != null) {
                customOrderLogDtos2.add(c);
            }else{
                ArrayList<CustomOrderLogDto> customOrderLogDtos1 = new ArrayList<>();
                customOrderLogDtos1.add(c);
                LinkedHashMap.put(name,customOrderLogDtos1);
            }
        }

        Collection<List<CustomOrderLogDto>> values = LinkedHashMap.values();

        for (List<CustomOrderLogDto> v : values) {
            for (CustomOrderLogDto v2 : v) {
                Integer status = v2.getStage();
                if (status == 4) {
                    //查询物流信息
                    List<OrderProductDto> listByOrderId1 = this.orderProductService.findListByOrderId(orderId);
                    List<MapContext> list = new ArrayList<>();
                    for (OrderProductDto orderProductDto : listByOrderId1) {
                        if (orderProductDto.getLogisticsCompanyName() != null && !orderProductDto.getLogisticsCompanyName().equals("")) {
                            MapContext mapContext = MapContext.newOne();
                            mapContext.put("logisticsName", orderProductDto.getLogisticsCompanyName());
                            mapContext.put("logisticsNo", orderProductDto.getLogisticsNo());
                            list.add(mapContext);
                        }
                    }
                    v2.setLogisticsInfo(list);
                }
            }
        }
        return ResultFactory.generateRequestResult(values);
    }

	@Override
	public RequestResult findDeptList(String id,String companyId) {
		//判断部门是否存在
		if(!this.deptService.isExist(id)){
			return ResultFactory.generateResNotFoundResult();
		}
		MapContext mapContext=MapContext.newOne();
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		mapContext.put(WebConstant.KEY_ENTITY_ID,id);
		mapContext.put(WebConstant.KEY_ENTITY_COMPANY_ID, WebUtils.getCurrCompanyId());
		paginatedFilter.setFilters(mapContext);
		return ResultFactory.generateRequestResult(this.deptMemberService.findListByDeptIdAndNameAndCompanyId(paginatedFilter));
	}

	@Override
	public RequestResult findDealersList(String condation,String branchId) {
		MapContext mapContext=MapContext.newOne();
		mapContext.put("condation",condation);
		mapContext.put("branchId",branchId);
		mapContext.put("status", CompanyStatus.NORMAL.getValue());
		List<MapContext> dealerList=this.companyService.findCompanyByNameAndTel(mapContext);
		return ResultFactory.generateRequestResult(dealerList);
	}
}
