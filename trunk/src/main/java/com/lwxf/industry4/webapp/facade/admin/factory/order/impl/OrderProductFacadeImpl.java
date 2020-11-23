package com.lwxf.industry4.webapp.facade.admin.factory.order.impl;

import com.lwxf.industry4.webapp.bizservice.common.CityAreaService;
import com.lwxf.industry4.webapp.bizservice.customorder.*;
import com.lwxf.industry4.webapp.bizservice.dispatch.DispatchBillItemService;
import com.lwxf.industry4.webapp.bizservice.dispatch.DispatchBillPlanService;
import com.lwxf.industry4.webapp.bizservice.dispatch.DispatchBillService;
import com.lwxf.industry4.webapp.bizservice.financing.PaymentService;
import com.lwxf.industry4.webapp.bizservice.system.BasecodeService;
import com.lwxf.industry4.webapp.bizservice.system.LogisticsCompanyService;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.customorder.ProduceOrderState;
import com.lwxf.industry4.webapp.common.enums.order.OrderProductStatus;
import com.lwxf.industry4.webapp.common.enums.order.OrderStage;
import com.lwxf.industry4.webapp.common.enums.order.OrderStatus;
import com.lwxf.industry4.webapp.common.enums.order.ProduceOrderWay;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.common.utils.excel.BaseExportExcelUtil;
import com.lwxf.industry4.webapp.common.utils.excel.impl.OrderProductExcelParam;
import com.lwxf.industry4.webapp.domain.dto.customorder.OrderProductDto;
import com.lwxf.industry4.webapp.domain.dto.customorder.ProduceOrderDto;
import com.lwxf.industry4.webapp.domain.entity.common.CityArea;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderLog;
import com.lwxf.industry4.webapp.domain.entity.customorder.OrderProduct;
import com.lwxf.industry4.webapp.domain.entity.customorder.OrderProductAttribute;
import com.lwxf.industry4.webapp.domain.entity.customorder.ProduceOrder;
import com.lwxf.industry4.webapp.domain.entity.dispatch.DispatchBillPlan;
import com.lwxf.industry4.webapp.domain.entity.system.Basecode;
import com.lwxf.industry4.webapp.domain.entity.system.LogisticsCompany;
import com.lwxf.industry4.webapp.facade.admin.factory.order.OrderProductFacade;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.mybatis.utils.DateUtil;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Component("orderProductFacade")
public class OrderProductFacadeImpl extends BaseFacadeImpl implements OrderProductFacade {

	@Resource(name = "orderProductService")
	private OrderProductService orderProductService;
	@Resource(name = "customOrderService")
	private CustomOrderService customOrderService;
	@Resource(name = "produceOrderService")
	private ProduceOrderService produceOrderService;
	@Resource(name = "basecodeService")
	private BasecodeService basecodeService;
	@Resource(name = "customOrderLogService")
	private CustomOrderLogService customOrderLogService;
	@Resource(name = "logisticsCompanyService")
	private LogisticsCompanyService logisticsCompanyService;
	@Resource(name = "dispatchBillService")
	private DispatchBillService dispatchBillService;
	@Resource(name = "dispatchBillItemService")
	private DispatchBillItemService dispatchBillItemService;
	@Resource(name = "dispatchBillPlanService")
	private DispatchBillPlanService dispatchBillPlanService;
	@Resource(name = "paymentService")
	private PaymentService paymentService;
	@Resource(name = "cityAreaService")
	private CityAreaService cityAreaService;
	@Resource(name = "orderProductAttributeService")
	private OrderProductAttributeService orderProductAttributeService;

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updateOrderProduct(String id, MapContext mapContext) {
		mapContext.put("id", id);
		return ResultFactory.generateRequestResult(this.orderProductService.updateByMapContext(mapContext));
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updateDeliveryInfo(MapContext mapContext) {
			String ids = mapContext.getTypedValue("ids", String.class);//产品id
			String logisticsCompanyId = mapContext.getTypedValue("logisticsCompanyId", String.class);
			LogisticsCompany byLogisticId = this.logisticsCompanyService.findByLogisticId(logisticsCompanyId);
			String logisticsCompany = byLogisticId.getName();
			String logisticsNo = mapContext.getTypedValue("logisticsNo", String.class);
			String[] productIds = ids.split(",");
			for (int i = 0; i < productIds.length; i++) {
				String id = productIds[i];
				mapContext.put("id", id);
				mapContext.put("deliveryTime", new Date());
				mapContext.put("deliveryCreator", WebUtils.getCurrUserId());
				mapContext.put("status", OrderProductStatus.HAS_DELIVERY.getValue());
				this.orderProductService.updateByMapContext(mapContext);
				//如果该订单下的所有产品均发货，则更新订单状态为已发货
				OrderProduct prod = orderProductService.findById(id);
				List<OrderProductDto> listProd = orderProductService.findListByOrderId(prod.getCustomOrderId());
				int count = 0;
				for (OrderProductDto dto : listProd) {
					if (dto.getStatus() == OrderProductStatus.HAS_DELIVERY.getValue()) {
						count++;
					}
				}
				// 更新生产单状态为已发货
				List<ProduceOrder> listproduce = produceOrderService.findListByProductId(id);
				for (ProduceOrder produce : listproduce) {
					MapContext p = MapContext.newOne();
					p.put("id", produce.getId());
					p.put("state", ProduceOrderState.COMPLETE.getValue());
					p.put("logisticsCompanyId", logisticsCompanyId);
					p.put("logisticsNo", logisticsNo);
					p.put("delivered", DateUtil.now());
					p.put("deliverer", WebUtils.getCurrUserId());
					p.put("deliverStatus",1);
					produceOrderService.updateByMapContext(p);
				}
				if (count == listProd.size()) {
					MapContext map = MapContext.newOne();
					map.put("id", prod.getCustomOrderId());
					map.put("status", OrderStatus.SHIPPED.getValue());
					map.put("deliveryDate", new Date());
					customOrderService.updateByMapContext(map);
				}
				//生成订单日志
				OrderProduct op = orderProductService.findOneById(id);
				CustomOrderLog log = new CustomOrderLog();
				log.setCreated(new Date());
				log.setCreator(WebUtils.getCurrUserId());
				log.setName("发货");
				log.setStage(OrderStage.DELIVERY.getValue());
				log.setContent("订单产品:" + op.getNo() + "已发货" + "-" + logisticsCompany + logisticsNo);
				log.setCustomOrderId(op.getCustomOrderId());
				customOrderLogService.add(log);
			}
		return ResultFactory.generateSuccessResult();
	}

	@Override
	public RequestResult findOrderProductList(Integer pageNum, Integer pageSize, MapContext mapContext) {
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		Pagination pagination = new Pagination();
		pagination.setPageNum(pageNum);
		pagination.setPageSize(pageSize);
		paginatedFilter.setPagination(pagination);
		mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID, WebUtils.getCurrBranchId());
		paginatedFilter.setFilters(mapContext);
		List<Map<String, String>> sorts = new ArrayList<Map<String, String>>();
		paginatedFilter.setSorts(sorts);
		PaginatedList<OrderProductDto> list = this.orderProductService.selectDtoByFilter(paginatedFilter);
		if (list.getRows().size() > 0) {
			MapContext param = MapContext.newOne();
			param.put("way", ProduceOrderWay.COORDINATION.getValue());//此参数暂时无用
			//base64编码
			Base64.Encoder encoder = Base64.getEncoder();
			for (OrderProductDto orderProductDto : list.getRows()) {
				//查询产品属性
				List<OrderProductAttribute> listByProductId = this.orderProductAttributeService.findListByProductId(orderProductDto.getId());
				orderProductDto.setProductAttributeValues(listByProductId);
				//64编码订单编号，用来生成条形码
				String orderNo=orderProductDto.getOrderNo();
				try {
					byte[] textByte = orderNo.getBytes("UTF-8");
					String aesOrderNo=encoder.encodeToString(textByte).replaceAll("[=/+]","");
					orderProductDto.setAesOrderNo(aesOrderNo);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				//查询经销商所在城市
				String dealerCityId = orderProductDto.getDealerCityId();
				if(dealerCityId!=null&&!dealerCityId.equals("")){
					CityArea byId = cityAreaService.findById(dealerCityId);
					String mergerName = byId.getMergerName();
//					String[] split = mergerName.split(",");
//					String cityName="";
//					if(split.length>3){
//						 cityName= split[1] + split[3];
//					}else {
//						cityName=split[1] + split[2];
//					}
					String cityName = mergerName.substring(3).replaceAll(",", "");
					orderProductDto.setDealerCityAddress(cityName);
				}
				String productDtoId = orderProductDto.getId();
				param.put("id", productDtoId);
				//排除未入库的生产单信息
				if(mapContext.containsKey("resources")) {
					if (mapContext.getTypedValue("resources", String.class).equals("1")||mapContext.getTypedValue("resources", String.class).equals("3")) {
						param.put("resources", "1");
					}
				}
				//查询可发货数量
				List<ProduceOrderDto> produceOrderDtos=	 this.produceOrderService.findProduceListByProductId(param);
				for(ProduceOrderDto produceOrderDto:produceOrderDtos){
					Integer count = produceOrderDto.getCount();
					Integer hasDeliverCount = produceOrderDto.getHasDeliverCount();
					if(count!=null&&!count.equals("")){
						if(hasDeliverCount==null||hasDeliverCount.equals("")){
							hasDeliverCount=0;
						}
						Integer toBeDeliver=count-hasDeliverCount;
						produceOrderDto.setToBeDeliver(toBeDeliver.toString());
					}else {
						produceOrderDto.setToBeDeliver("");
					}
				}
				orderProductDto.setProduceOrderList(produceOrderDtos);
			}
		}
		String planInfo=mapContext.getTypedValue("planInfo",String.class);
		if(planInfo!=null&&planInfo.equals("1")){
			DispatchBillPlan byStatusAndBranchId = this.dispatchBillPlanService.findByStatusAndBranchId(0, WebUtils.getCurrBranchId());
			MapContext map = MapContext.newOne();
			if(byStatusAndBranchId!=null) {
				String name = byStatusAndBranchId.getName();
				map.put("name", name);
				map.put("list", list.getRows());
				return ResultFactory.generateRequestResult(map);
			}else {
				return ResultFactory.generateSuccessResult();
			}

		}
		return ResultFactory.generateRequestResult(list);
	}

	@Override
	public RequestResult countByProductStatus(Integer status) {
		MapContext map = MapContext.newOne();
		map.put("status", status);
		map.put("branchId", WebUtils.getCurrBranchId());
		return ResultFactory.generateRequestResult(orderProductService.countProductsByStatus(map));
	}

	@Override
	public RequestResult findProductSeries() {
		String type = "orderProductSeries";
		Integer delFlag = 0;
		return ResultFactory.generateRequestResult(this.basecodeService.findByTypeAndDelFlag(type, delFlag));
	}

	@Override
	public RequestResult countPartStock() {
		String branchId = WebUtils.getCurrBranchId();
		List<MapContext> result = this.orderProductService.countPartStock(branchId);
		return ResultFactory.generateRequestResult(result);
	}

	@Override
	public RequestResult writeExcel(Integer pageNum, Integer pageSize, MapContext mapContext, OrderProductExcelParam excelParam) {
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		Pagination pagination = new Pagination();
		pagination.setPageNum(pageNum);
		pagination.setPageSize(pageSize);
		paginatedFilter.setPagination(pagination);
		mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID, WebUtils.getCurrBranchId());
		paginatedFilter.setFilters(mapContext);
		List<Map<String, String>> sorts = new ArrayList<Map<String, String>>();
		Map<String,String> status=new HashMap<>();
		status.put("status","asc");
		sorts.add(status);
		Map<String, String> created = new HashMap<String, String>();
		created.put("created", "desc");
		sorts.add(created);
		Map<String, String> id = new HashMap<String, String>();
		id.put("id", "desc");
		sorts.add(id);
		paginatedFilter.setSorts(sorts);
		PaginatedList<OrderProductDto> list = this.orderProductService.selectDtoByFilter(paginatedFilter);
        List<Map<String, Object>> result = new ArrayList<>();
		if (list.getRows().size() > 0) {
			MapContext param = MapContext.newOne();
			param.put("way", ProduceOrderWay.COORDINATION.getValue());//此参数暂时无用
			for (OrderProductDto orderProductDto : list.getRows()) {
				String productDtoId = orderProductDto.getId();
				param.put("id", productDtoId);
				List<ProduceOrderDto> produceOrderDtos = this.produceOrderService.findProduceListByProductId(param);
				orderProductDto.setProduceOrderList(produceOrderDtos);
				Map map = new HashMap();
                map.put("no", orderProductDto.getNo()); // 产品编号
                // 计算拆单类型，包裹数量
                List<ProduceOrderDto> dtos = orderProductDto.getProduceOrderList();
                if (dtos != null && dtos.size() > 0) {
                    StringBuilder packageCount = new StringBuilder();
                    for (ProduceOrderDto dto : dtos) {
                        // 查询生产单类型
                        Basecode basecode = basecodeService.findByTypeAndCode("produceType", String.valueOf(dto.getType()));
                        if (basecode != null) {
                            packageCount.append(basecode.getValue());
                            packageCount.append("=");
                            packageCount.append(dto.getCount() == null ? 0 : dto.getCount());
                            packageCount.append("\r\n");
                        }

                    }
                    map.put("packageCount", packageCount.toString().trim());
                }
                map.put("orderNo", orderProductDto.getOrderNo()); //订单编号
                map.put("statusName",orderProductDto.getStatusName()); //产品状态
                map.put("typeName", orderProductDto.getTypeName()); // 产品类型
                map.put("payTime", com.lwxf.commons.utils.DateUtil.dateTimeToString(orderProductDto.getPayTime())); // 审核时间
                result.add(map);
			}
		}

        new BaseExportExcelUtil().download("第一页", result, excelParam);

		return ResultFactory.generateSuccessResult();
	}
}
