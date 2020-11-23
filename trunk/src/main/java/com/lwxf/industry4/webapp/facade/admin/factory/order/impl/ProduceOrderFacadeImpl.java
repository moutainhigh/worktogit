package com.lwxf.industry4.webapp.facade.admin.factory.order.impl;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.bizservice.company.CompanyEmployeeService;
import com.lwxf.industry4.webapp.bizservice.corporatePartners.CorporatePartnersService;
import com.lwxf.industry4.webapp.bizservice.customorder.*;
import com.lwxf.industry4.webapp.bizservice.product.ProductPartsService;
import com.lwxf.industry4.webapp.bizservice.system.BasecodeService;
import com.lwxf.industry4.webapp.common.aop.syslog.OperationMoudule;
import com.lwxf.industry4.webapp.common.aop.syslog.OperationType;
import com.lwxf.industry4.webapp.common.aop.syslog.SysOperationLog;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.customorder.CustomOrderFilesType;
import com.lwxf.industry4.webapp.common.enums.customorder.ProduceOrderState;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentFunds;
import com.lwxf.industry4.webapp.common.enums.order.*;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.common.utils.WeiXin.WeiXinMsgPush;
import com.lwxf.industry4.webapp.domain.dto.company.CompanyDto;
import com.lwxf.industry4.webapp.domain.dto.customorder.CustomOrderDto;
import com.lwxf.industry4.webapp.domain.dto.customorder.OrderProductDto;
import com.lwxf.industry4.webapp.domain.dto.customorder.ProduceOrderDto;
import com.lwxf.industry4.webapp.domain.dto.printTable.CoordinationPrintTableDto;
import com.lwxf.industry4.webapp.domain.dto.product.ProductPartsDto;
import com.lwxf.industry4.webapp.domain.entity.company.CompanyEmployee;
import com.lwxf.industry4.webapp.domain.entity.corporatePartners.CorporatePartners;
import com.lwxf.industry4.webapp.domain.entity.customorder.*;
import com.lwxf.industry4.webapp.domain.entity.system.Basecode;
import com.lwxf.industry4.webapp.domain.entity.system.Role;
import com.lwxf.industry4.webapp.domain.entity.user.User;
import com.lwxf.industry4.webapp.domain.entity.user.UserThirdInfo;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.dealer.impl.OrderFacadeImpl;
import com.lwxf.industry4.webapp.facade.admin.factory.order.ProduceOrderFacade;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/4/8/008 15:57
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("produceOrderFacade")
public class ProduceOrderFacadeImpl extends BaseFacadeImpl implements ProduceOrderFacade {
	@Resource(name = "produceOrderService")
	private ProduceOrderService produceOrderService;
	@Resource(name = "orderProductService")
	private OrderProductService orderProductService;
	@Resource(name = "productPartsService")
	private ProductPartsService productPartsService;
	@Resource(name = "customOrderTimeService")
	private CustomOrderTimeService customOrderTimeService;
	@Resource(name = "customOrderService")
	private CustomOrderService customOrderService;
	@Resource(name = "customOrderLogService")
	private CustomOrderLogService customOrderLogService;
	@Resource(name = "basecodeService")
	private BasecodeService basecodeService;
	@Resource(name = "companyEmployeeService")
	private CompanyEmployeeService companyEmployeeService;
	@Resource(name = "corporatePartnersService")
	private CorporatePartnersService corporatePartnersService;
	@Resource(name = "customOrderFilesService")
	private CustomOrderFilesService customOrderFilesService;
	@Resource(name = "orderProductAttributeService")
	private OrderProductAttributeService orderProductAttributeService;
	@Autowired
	private WeiXinMsgPush weiXinMsgPush;

	@Override
	public RequestResult findCoordinationCount(String branchId) {
		MapContext mapContext = this.produceOrderService.findCoordinationCount(branchId);
		List result = new ArrayList();
		int a = 0;
		for (int i = 0; i < 3; i++) {
			Map map = new HashMap();
			switch (a) {
				case 0:
					map.put("name", "新增外协数量");
					map.put("value", mapContext.getTypedValue("newNum", Integer.class));
					a = a + 1;
					break;
				case 1:
					map.put("name", "待支付外协单数量");
					map.put("value", mapContext.getTypedValue("afterNum", Integer.class));
					a = a + 1;
					break;
				case 2:
					map.put("name", "外协完成数量");
					map.put("value", mapContext.getTypedValue("endNum", Integer.class));
					a = a + 1;
					break;
			}
			result.add(map);
		}
		return ResultFactory.generateRequestResult(result);

	}

	@Override
	public RequestResult findProduceOrderOverview() {
		MapContext result = new MapContext();

		PaginatedFilter paginatedFilter = new PaginatedFilter();
		Pagination pagination = new Pagination();
		pagination.setPageSize(99999999);
		pagination.setPageNum(1);
		paginatedFilter.setPagination(pagination);
		//今日生产数量
		MapContext filter1 = new MapContext();
		filter1.put("actualTimeNow", 1);
		filter1.put("funds", PaymentFunds.ORDER_FEE_CHARGE.getValue());
		paginatedFilter.setFilters(filter1);
		result.put("produceOrderAccount", this.produceOrderService.findListByFilter(paginatedFilter).getRows().size());
		//今日完成数量
		MapContext filter2 = new MapContext();
		filter2.put("completionTime", 1);
		filter2.put("funds", PaymentFunds.ORDER_FEE_CHARGE.getValue());
		paginatedFilter.setFilters(filter2);
		result.put("produceOrderCompleteAccount", this.produceOrderService.findListByFilter(paginatedFilter).getRows().size());
		//今日临产数量
		MapContext filter3 = new MapContext();
		filter3.put("plannedTimeNow", 1);
		filter3.put("funds", PaymentFunds.ORDER_FEE_CHARGE.getValue());
		filter3.put(WebConstant.KEY_ENTITY_STATE, Arrays.asList(ProduceOrderState.NOT_YET_BEGUN.getValue()));
		paginatedFilter.setFilters(filter3);
		result.put("produceOrderScheduleAccount", this.produceOrderService.findListByFilter(paginatedFilter).getRows().size());
		//今日超期数量
		MapContext filter4 = new MapContext();
		filter4.put("plannedTimeYes", 1);
		filter4.put("funds", PaymentFunds.ORDER_FEE_CHARGE.getValue());
		filter4.put(WebConstant.KEY_ENTITY_STATE, Arrays.asList(ProduceOrderState.NOT_YET_BEGUN.getValue()));
		paginatedFilter.setFilters(filter4);
		result.put("produceOrderOverdueAccount", this.produceOrderService.findListByFilter(paginatedFilter).getRows().size());
		return ResultFactory.generateRequestResult(result);
	}

	@Override
	public RequestResult findCoordinationPrintInfo(String id) {
		CoordinationPrintTableDto coordinationPrintTableDto = this.produceOrderService.findCoordinationPrintInfo(id);
		coordinationPrintTableDto.setOrderProduct(this.orderProductService.findOneById(coordinationPrintTableDto.getOrderProductId()));
		return ResultFactory.generateRequestResult(coordinationPrintTableDto);
	}

	@Override
	public RequestResult findProductList(Integer pageNum, Integer pageSize, MapContext mapContext) {
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		Pagination pagination = new Pagination();
		pagination.setPageNum(pageNum);
		pagination.setPageSize(pageSize);
		paginatedFilter.setPagination(pagination);
		String userId=WebUtils.getCurrUserId();
		//判断登录人是否为外协厂家人员
		if(userId==null){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED,AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
		}
		//查询登录人手机号
		User byUserId = AppBeanInjector.userService.findByUserId(userId);
		String tel=byUserId.getMobile();
		Role roleByCidAndUid = AppBeanInjector.roleService.findRoleByCidAndUid(userId,WebUtils.getCurrCompanyId());
		if(roleByCidAndUid!=null) {
			if (roleByCidAndUid.getKey().equals("outfacturer")) {
				CorporatePartners corporatePartners = this.corporatePartnersService.findByTel(tel);
				String outFactoryName = corporatePartners.getName();
				mapContext.put("outFactoryName", outFactoryName);
			}
		}
		paginatedFilter.setFilters(mapContext);
		PaginatedList<OrderProductDto> listByPaginateFilter = this.orderProductService.findListByPaginateFilter(paginatedFilter);
		if (listByPaginateFilter.getRows().size() > 0) {
		    // 查询所有延期的原因列表
            List<Basecode> basecodeList = this.basecodeService.findByType("produceDelayReason");
            Map<String, String> basecdoeMap = basecodeList.stream()
                    .collect(Collectors.toMap(Basecode::getCode, Basecode::getValue));

			MapContext param = MapContext.newOne();
			param.put("way", ProduceOrderWay.COORDINATION.getValue());
			for (OrderProductDto orderProductDto : listByPaginateFilter.getRows()) {
                String id = orderProductDto.getId();
                param.put("id", id);
                List<ProduceOrderDto> produceOrderDtos = this.produceOrderService.findProduceListByProductId(param);
                orderProductDto.setProduceOrderList(produceOrderDtos);

                // 匹配延期原因列表
                if (orderProductDto.getDelayReason() != null && basecodeList != null && basecodeList.size() > 0) {
                    List<String> delayReasonList = new ArrayList<>();
                    String[] reasonCodes = orderProductDto.getDelayReason().split(",");
                    for (String code : reasonCodes) {
                        if (basecdoeMap.containsKey(code)) {
                            delayReasonList.add(basecdoeMap.get(code));
                        }
                    }
                    orderProductDto.setDelayReasonList(delayReasonList);
                }
                //查询生产工期时长
				MapContext p=MapContext.newOne();
                p.put("productType",orderProductDto.getType());
                if(orderProductDto.getSeries()!=null&&!orderProductDto.getSeries().equals("")) {
					p.put("productSeries", orderProductDto.getSeries());
				}else if(orderProductDto.getBodyTec()!=null&&!orderProductDto.getBodyTec().equals("")){
					p.put("productSeries", orderProductDto.getBodyTec());
				}
                p.put("productId",id);
				CustomOrderTime	customOrderTime=this.customOrderTimeService.findFirstByProductId(p);
                //生产发货工期
				String produceAndDeliveryInfo="";
				Date payTime=orderProductDto.getPayTime();
                Date deliveryTime=orderProductDto.getDeliveryTime();
                if(deliveryTime==null||deliveryTime.equals("")){
                	deliveryTime=DateUtil.getSystemDate();
				}
                Integer dateNum=15;//默认生产时间
				if(customOrderTime!=null){
					dateNum=customOrderTime.getProduceTime();
				}
                if(payTime!=null&&!payTime.equals("")){
					produceAndDeliveryInfo= OrderFacadeImpl.getTimeInfo(payTime,deliveryTime,dateNum);
				}
				orderProductDto.setProduceAndDeliveryInfo(produceAndDeliveryInfo);
                //查询产品属性
				List<OrderProductAttribute> listByProductId = this.orderProductAttributeService.findListByProductId(orderProductDto.getId());
				orderProductDto.setProductAttributeValues(listByProductId);
			}

		}
		return ResultFactory.generateRequestResult(listByPaginateFilter);
	}

	/**
	 * 产品开始生产操作
	 */
	@Override
	@Transactional(value = "transactionManager")
	@SysOperationLog(detail = "开始生产", operationType = OperationType.UPDATE, operationMoudule = OperationMoudule.DESIGN)
	public RequestResult updateOrderProduct(String productIds) {
		String[] productIdList = productIds.split(",");
		for (String productId : productIdList) {
			OrderProductDto orderProductDto = this.orderProductService.findOneById(productId);
			if (orderProductDto == null) {
				return ResultFactory.generateResNotFoundResult();
			}
			Integer status = orderProductDto.getStatus();
			if (status == OrderProductStatus.PRODUCING.getValue()) {
				return ResultFactory.generateErrorResult(ErrorCodes.BIZ_NOT_ALLOW_OPERATION_10020, AppBeanInjector.i18nUtil.getMessage("BIZ_NOT_ALLOW_OPERATION_10020"));
			}
			//查询产品生产周期
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(DateUtil.getSystemDate());
			Integer productType = orderProductDto.getType();//产品品类
			Integer series = orderProductDto.getSeries();//系列
			MapContext mapContext = MapContext.newOne();
			Integer countProduce = 0;
			Integer allCost=0;
			Integer partsType = null;//部件
			//橱柜和衣柜
			if (productType == OrderProductType.CUPBOARD.getValue() || productType == OrderProductType.WARDROBE.getValue()) {
				List<ProductPartsDto> productParts = this.productPartsService.findByOrderProductId(productId);
				for (ProductPartsDto productPartsDto : productParts) {
					mapContext.put("productSeries", series);
					partsType = productPartsDto.getPartsType();
					//如果部件是五金,则默认系列为0
					if (partsType == ProduceOrderType.HARDWARE.getValue()) {
						allCost=1;
					} else {
						//如果系列和生产工艺都是null,并且部件不是五金
						if ((orderProductDto.getSeries() == null || orderProductDto.getSeries().equals(""))
								&& (orderProductDto.getBodyTec() == null || orderProductDto.getBodyTec().equals(""))) {
							if (productType == OrderProductType.CUPBOARD.getValue()) {//橱柜7天
								allCost = 7;
							} else if (productType == OrderProductType.WARDROBE.getValue()) {//衣柜12天
								allCost = 12;
							}
						} else {
							//橱柜柜体没有系列，如果为橱柜柜体，则默认系列是0
							if (productType == OrderProductType.CUPBOARD.getValue() && partsType == ProduceOrderType.CABINET_BODY.getValue()) {
								mapContext.put("productSeries", 0);
							}
							if (productType == OrderProductType.WARDROBE.getValue() && partsType == ProduceOrderType.CABINET_BODY.getValue()) {
								mapContext.put("productSeries", 0);
							}
							//如果是衣柜柜体，则门板系列为空，衣柜柜体工艺不能为空，衣柜柜体工艺也存在customorderTime表中的series字段
							//所以只需要把衣柜柜体工艺也存入series对customorderTime表进行查询即可
							if (orderProductDto.getSeries() == null || orderProductDto.getSeries().equals("")) {
								mapContext.put("productSeries", orderProductDto.getBodyTec());
							}
						mapContext.put("produceType", partsType);
						mapContext.put("productType", orderProductDto.getType());
						CustomOrderTime customOrderTime = this.customOrderTimeService.findByTypeAndSeries(mapContext);
						if (customOrderTime != null) {
							 allCost = customOrderTime.getProduceTime();

						}
						}
					}
					if (allCost > countProduce) {
						countProduce = allCost;
					}
				}

			}
			//五金和样块默认系列和生产方式都为0
			else if (productType == OrderProductType.HARDWARE.getValue() || productType == OrderProductType.SAMPLE_PIECE.getValue()) {
				mapContext.put("productSeries", 0);
				mapContext.put("produceType", 0);
				mapContext.put("productType", orderProductDto.getType());
				CustomOrderTime customOrderTime = this.customOrderTimeService.findByTypeAndSeries(mapContext);
				if (customOrderTime != null) {
					 allCost = customOrderTime.getProduceTime();
				}
				if (allCost > countProduce) {
					countProduce = allCost;
				}
			}
			calendar.add(calendar.DATE, countProduce);
			//修改产品状态和计划生产完成时间,开始生产时间
			MapContext map = MapContext.newOne();
			map.put("id", productId);
			map.put("planFinishTime", calendar.getTime());
			map.put("status", OrderProductStatus.PRODUCING.getValue());
			map.put("startProduceTime", DateUtil.getSystemDate());
			this.orderProductService.updateByMapContext(map);
			//修改订单状态
			if (orderProductDto.getCustomOrderId() != null) {//订单
				MapContext map2 = MapContext.newOne();
				map2.put("id", orderProductDto.getCustomOrderId());
				CustomOrder byId = this.customOrderService.findById(orderProductDto.getCustomOrderId());
				if(byId.getDocumentaryTime()==null||byId.getDocumentaryTime().equals("")){
					map2.put("documentaryTime",DateUtil.getSystemDate());
				}
				map2.put("status", OrderStatus.PRODUCTION.getValue());
				this.customOrderService.updateByMapContext(map2);
			}
			//更新该产品下生产单状态
			List<ProduceOrder> list = produceOrderService.findListByProductId(productId);
			if (list != null && list.size() > 0) {
				for (ProduceOrder p : list) {
					if (p.getWay() == ProduceOrderWay.SELF_PRODUCED.getValue()) {
						MapContext mp = MapContext.newOne();
						mp.put("id", p.getId());
						mp.put("state", ProduceOrderState.IN_PRODUCTION.getValue());
						produceOrderService.updateByMapContext(mp);
					}
				}
			}
			//生成订单操作日志
			CustomOrder order = customOrderService.findById(orderProductDto.getCustomOrderId());
			if (order != null) {
				CustomOrderLog log = new CustomOrderLog();
				log.setCreated(new Date());
				log.setCreator(WebUtils.getCurrUserId());
				log.setName("生产");
				log.setStage(OrderStage.PRODUCTION.getValue());
				log.setContent("产品：" + orderProductDto.getTypeName()+orderProductDto.getNo() + "已开始生产");
				log.setCustomOrderId(order.getId());
				customOrderLogService.add(log);
			}
			//公众号消息通知
			String companyId = order.getCompanyId();
			CompanyDto companyById = AppBeanInjector.companyService.findCompanyById(companyId);
			if(companyById!=null){
				String leader = companyById.getLeader();
				UserThirdInfo byUserId = AppBeanInjector.userThirdInfoService.findByUserId(leader);
				if(byUserId!=null){
					//查询负责人的公众号openId是否为空
					if(byUserId.getOfficialOpenId()!=null&&!byUserId.getOfficialOpenId().equals("")){
						MapContext msg=MapContext.newOne();
						msg.put("orderId",order.getId());
						msg.put("orderStatus",3);
						msg.put("first","您好，您的订单产品已开始生产，请耐心等候...");
						msg.put("keyword1",orderProductDto.getNo());
						msg.put("keyword2",DateUtil.dateTimeToString(calendar.getTime()));
						msg.put("remark","感谢您的支持");
						String openId=byUserId.getOfficialOpenId();
						weiXinMsgPush.SendWxMsg(openId,msg);
					}
				}
			}
		}
		return ResultFactory.generateSuccessResult();
	}

	/**
	 * 生产管理顶部信息数据统计接口
	 */
	@Override
	public RequestResult countTopProduct() {
		MapContext params = MapContext.newOne();
		params.put("branchId", WebUtils.getCurrBranchId());
		MapContext mapContext = this.produceOrderService.countTopProduct(params);
		List result = new ArrayList();
		int a = 0;
		for (int i = 0; i < 5; i++) {
			Map map = new HashMap();
			switch (a) {
				case 0:
					map.put("name", "待生产");
					map.put("value", mapContext.getTypedValue("waitProduce", Integer.class));
					a = a + 1;
					break;
				case 1:
					map.put("name", "生产中");
					map.put("value", mapContext.getTypedValue("atProduce", Integer.class));
					a = a + 1;
					break;
				case 2:
					map.put("name", "生产即将延期");
					map.put("value", mapContext.getTypedValue("matureProduce", Integer.class));
					a = a + 1;
					break;
				case 3:
					map.put("name", "已延期订单");
					map.put("value", mapContext.getTypedValue("lateProduce", Integer.class));
					a = a + 1;
					break;
				case 4:
					map.put("name", "已完成生产数");
					map.put("value", mapContext.getTypedValue("endProduce", Integer.class));
					a = a + 1;
					break;
			}
			result.add(map);
		}
		return ResultFactory.generateRequestResult(result);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult cancelOrderProduct(String productId) {
		List<ProduceOrder> listByProductId = this.produceOrderService.findListByProductId(productId);
		//如果有已经入库的部件，则不允许取消生产
		for (ProduceOrder produceOrder : listByProductId) {
			if (produceOrder.getCount() != null) {
				return ResultFactory.generateErrorResult(ErrorCodes.BIZ_NOT_ALLOW_OPERATION_10020, AppBeanInjector.i18nUtil.getMessage("BIZ_NOT_ALLOW_OPERATION_10020"));
			}
		}
		//修改产品状态为待生产
		MapContext mapContext = MapContext.newOne();
		mapContext.put("id", productId);
		mapContext.put("status", OrderProductStatus.TO_PRODUCE.getValue());
		mapContext.put("planFinishTime", null);
		mapContext.put("startProduceTime", null);
		int result = this.orderProductService.cancelOrderProduct(mapContext);
		//如果订单下所有产品都是待生产，则订单状态改为待生产
		OrderProductDto oneById = this.orderProductService.findOneById(productId);
		String orderId=oneById.getCustomOrderId();
		List<OrderProductDto> listByOrderId = this.orderProductService.findListByOrderId(orderId);
		MapContext map=MapContext.newOne();
		map.put("orderId",orderId);
		map.put("status",OrderProductStatus.TO_PRODUCE.getValue());
		List<OrderProductDto> productDtos=this.orderProductService.findListByOrderIdAndStatus(map);
		if(listByOrderId.size()==productDtos.size()){
			MapContext mapContext1=MapContext.newOne();
			mapContext1.put("id",orderId);
			mapContext1.put("status",OrderStatus.TO_PRODUCED.getValue());
			this.customOrderService.updateByMapContext(mapContext1);
		}
		return ResultFactory.generateSuccessResult();
	}

	@Override
    @Transactional(value = "transactionManager")
	public RequestResult addProduceDelayReason(String id, MapContext mapContext) {
		OrderProduct orderProduct = this.orderProductService.findById(id);
		if (null == orderProduct) {
			return ResultFactory.generateResNotFoundResult();
		}
		mapContext.put(WebConstant.KEY_ENTITY_ID, id);
		mapContext.put("updateTime", DateUtil.getSystemDate());
		mapContext.put("updateUser", WebUtils.getCurrUserId());
        this.orderProductService.updateByMapContext(mapContext);

		return ResultFactory.generateSuccessResult();
	}

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult produceComplete(String id, MapContext mapContext) {
        OrderProduct orderProduct = this.orderProductService.findById(id);
        if (null == orderProduct) {
            return ResultFactory.generateResNotFoundResult();
        }
        // 非生产状态的产品禁止进行生产完成操作
        if (!orderProduct.getStatus().equals(OrderProductStatus.PRODUCING.getValue())) {
            return ResultFactory.generateErrorResult(ErrorCodes.BIZ_NOT_ALLOW_OPERATION_10020, AppBeanInjector.i18nUtil.getMessage("BIZ_NOT_ALLOW_OPERATION_10020"));
        }
        mapContext.put(WebConstant.KEY_ENTITY_STATUS, OrderProductStatus.PRODUCE_COMPLETE.getValue());
        mapContext.put(WebConstant.KEY_ENTITY_ID, id);
        mapContext.put("updateTime", DateUtil.getSystemDate());
        mapContext.put("updateUser", WebUtils.getCurrUserId());
        this.orderProductService.updateByMapContext(mapContext);

        return ResultFactory.generateSuccessResult();
    }

	@Override
	public RequestResult findCoordinationList(MapContext mapContext, Integer pageNum, Integer pageSize) {
		String branchId=WebUtils.getCurrBranchId();
		String userId=WebUtils.getCurrUserId();
		mapContext.put("branchId",branchId);
		CompanyEmployee oneByCompanyIdAndUserId = this.companyEmployeeService.findOneByCompanyIdAndUserId(branchId, userId);
		if(oneByCompanyIdAndUserId==null){
			return ResultFactory.generateResNotFoundResult();
		}
		//外协厂家id
		String corporatePartnersId = oneByCompanyIdAndUserId.getCorporatePartnersId();
		//外协厂家名称
		CorporatePartners corporatePartners=this.corporatePartnersService.findById(corporatePartnersId);
		String corporatePartnersName=corporatePartners.getName();
		mapContext.put("corporatePartnersName",corporatePartnersName);
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		Pagination pagination = new Pagination();
		pagination.setPageNum(pageNum);
		pagination.setPageSize(pageSize);
		paginatedFilter.setPagination(pagination);
		paginatedFilter.setFilters(mapContext);
		PaginatedList<OrderProductDto> listByPaginateFilter = this.orderProductService.findListByPaginateFilter(paginatedFilter);
		MapContext param=MapContext.newOne();
		param.put("corporatePartnersName",corporatePartnersName);
		for(OrderProductDto productDto:listByPaginateFilter.getRows()){
			param.put("id",productDto.getId());
			List<ProduceOrderDto> listByProductId = this.produceOrderService.findProduceListByProductId(param);
			for(ProduceOrderDto produceOrderDto:listByProductId){
				List<CustomOrderFiles> customOrderFiles = this.customOrderFilesService.selectByOrderIdAndType(productDto.getCustomOrderId(), CustomOrderFilesType.PRODUCE_ORDER.getValue(), produceOrderDto.getId());
				produceOrderDto.setUploadFiles(customOrderFiles);
			}
			productDto.setProduceOrderList(listByProductId);
		}
		return ResultFactory.generateRequestResult(listByPaginateFilter);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updateCoordinationProduce(String produceIds) {
		Set<String> products=new HashSet();
		Set<String> orderIds=new HashSet();
		String[] produceIdList = produceIds.split(",");
		for(String produceId:produceIdList){
			ProduceOrderDto oneById = this.produceOrderService.findOneById(produceId);
			String productId=oneById.getOrderProductId();
			products.add(productId);
			String orderId=oneById.getCustomOrderId();
			orderIds.add(orderId);
			MapContext mapContext=MapContext.newOne();
			mapContext.put("id",produceId);
			mapContext.put("actualTime",DateUtil.getSystemDate());
			mapContext.put("state",ProduceOrderState.IN_PRODUCTION.getValue());
			this.produceOrderService.updateByMapContext(mapContext);
		}
        //查询产品下的生产单是否全部开始生产
		for(String productId:products) {
			List<ProduceOrder> listByProductId = this.produceOrderService.findListByProductId(productId);
			MapContext listByProductIdAndState = this.produceOrderService.findListByProductIdAndState(productId, ProduceOrderState.IN_PRODUCTION.getValue());
			Integer num=listByProductIdAndState.getTypedValue("countDeliver",Integer.class);
			//如果全部开始生产，则修改产品状态为生产中
			if(listByProductId.size()==num){
					MapContext map = MapContext.newOne();
					map.put("status", OrderProductStatus.PRODUCING.getValue());
					map.put("id", productId);
					this.orderProductService.updateByMapContext(map);
			}
		}
		//查询订单下的产品是否全部开始生产
		for(String orderId:orderIds) {
			List<OrderProductDto> listByOrderId = this.orderProductService.findListByOrderId(orderId);
			MapContext map = MapContext.newOne();
			map.put("orderId", orderId);
			map.put("status", OrderProductStatus.PRODUCING.getValue());
			List<OrderProductDto> productDtos = this.orderProductService.findListByOrderIdAndStatus(map);
			if (listByOrderId.size() == productDtos.size()) {
				MapContext mapContext1 = MapContext.newOne();
				mapContext1.put("id", orderId);
				mapContext1.put("status", OrderStatus.PRODUCTION.getValue());
				this.customOrderService.updateByMapContext(mapContext1);
			}
		}
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult cancelCoordinationProduce(String produceIds) {
		Set<String> products=new HashSet();
		Set<String> orderIds=new HashSet();
		String[] produceIdList = produceIds.split(",");
		for(String produceId:produceIdList){
			ProduceOrderDto oneById = this.produceOrderService.findOneById(produceId);
			Integer state = oneById.getState();
			if(state!=ProduceOrderState.IN_PRODUCTION.getValue()){
				return ResultFactory.generateErrorResult(ErrorCodes.BIZ_NOT_ALLOW_OPERATION_10020,AppBeanInjector.i18nUtil.getMessage("BIZ_NOT_ALLOW_OPERATION_10020"));
			}
			String productId=oneById.getOrderProductId();
			products.add(productId);
			String orderId=oneById.getCustomOrderId();
			orderIds.add(orderId);
			MapContext mapContext=MapContext.newOne();
			mapContext.put("id",produceId);
			mapContext.put("state",ProduceOrderState.NOT_YET_BEGUN.getValue());
			this.produceOrderService.updateByMapContext(mapContext);
		}
		//查询产品下的生产单是否全部未生产
		for(String productId:products) {
			OrderProductDto oneById = this.orderProductService.findOneById(productId);
			List<ProduceOrder> listByProductId = this.produceOrderService.findListByProductId(productId);
			MapContext listByProductIdAndState = this.produceOrderService.findListByProductIdAndState(productId, ProduceOrderState.NOT_YET_BEGUN.getValue());
			Integer num=listByProductIdAndState.getTypedValue("countDeliver",Integer.class);
			//如果全部未生产，且产品状态为生产中，则修改产品状态为待生产
			if(oneById.getStatus()==OrderProductStatus.PRODUCING.getValue()) {
				if (listByProductId.size() == num) {
					MapContext map = MapContext.newOne();
					map.put("status", OrderProductStatus.TO_PRODUCE.getValue());
					map.put("id", productId);
					this.orderProductService.updateByMapContext(map);
				}
			}
		}
		//查询订单下的产品是否全部待生产
		for(String orderId:orderIds) {
			CustomOrderDto byOrderId = this.customOrderService.findByOrderId(orderId);
			List<OrderProductDto> listByOrderId = this.orderProductService.findListByOrderId(orderId);
			MapContext map = MapContext.newOne();
			map.put("orderId", orderId);
			map.put("status", OrderProductStatus.TO_PRODUCE.getValue());
			List<OrderProductDto> productDtos = this.orderProductService.findListByOrderIdAndStatus(map);
			//如果全部未生产，且订单状态为生产中，则修改产品状态为待生产
			if(byOrderId.getStatus()==OrderStatus.PRODUCTION.getValue()) {
				if (listByOrderId.size() == productDtos.size()) {
					MapContext mapContext1 = MapContext.newOne();
					mapContext1.put("id", orderId);
					mapContext1.put("status", OrderStatus.TO_PRODUCED.getValue());
					this.customOrderService.updateByMapContext(mapContext1);
				}
			}
		}
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult endCoordinationProduce(String produceIds) {
		String[] produceIdList = produceIds.split(",");
		for(String produceId:produceIdList){
			ProduceOrderDto oneById = this.produceOrderService.findOneById(produceId);
			Integer state = oneById.getState();
			if(state!=ProduceOrderState.IN_PRODUCTION.getValue()){
				return ResultFactory.generateErrorResult(ErrorCodes.BIZ_NOT_ALLOW_OPERATION_10020,AppBeanInjector.i18nUtil.getMessage("BIZ_NOT_ALLOW_OPERATION_10020"));
			}
			MapContext mapContext=MapContext.newOne();
			mapContext.put("id",produceId);
			mapContext.put("state",ProduceOrderState.COMPLETE.getValue());
			this.produceOrderService.updateByMapContext(mapContext);
		}
		return ResultFactory.generateSuccessResult();
	}


}
