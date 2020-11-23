package com.lwxf.industry4.webapp.facade.admin.factory.dealer.impl;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.bizservice.aftersale.AftersaleApplyFilesService;
import com.lwxf.industry4.webapp.bizservice.aftersale.AftersaleApplyService;
import com.lwxf.industry4.webapp.bizservice.branch.BranchService;
import com.lwxf.industry4.webapp.bizservice.common.CityAreaService;
import com.lwxf.industry4.webapp.bizservice.common.UploadFilesService;
import com.lwxf.industry4.webapp.bizservice.company.CompanyEmployeeService;
import com.lwxf.industry4.webapp.bizservice.company.CompanyMemberOrderService;
import com.lwxf.industry4.webapp.bizservice.company.CompanyService;
import com.lwxf.industry4.webapp.bizservice.company.CompanyShareMemberService;
import com.lwxf.industry4.webapp.bizservice.corporatePartners.CorporatePartnersService;
import com.lwxf.industry4.webapp.bizservice.customer.CompanyCustomerService;
import com.lwxf.industry4.webapp.bizservice.customorder.*;
import com.lwxf.industry4.webapp.bizservice.dealer.DealerAccountLogService;
import com.lwxf.industry4.webapp.bizservice.dealer.DealerAccountService;
import com.lwxf.industry4.webapp.bizservice.dealer.DealerLogisticsService;
import com.lwxf.industry4.webapp.bizservice.dept.DeptService;
import com.lwxf.industry4.webapp.bizservice.dispatch.DispatchBillItemService;
import com.lwxf.industry4.webapp.bizservice.dispatch.DispatchBillPlanItemService;
import com.lwxf.industry4.webapp.bizservice.dispatch.DispatchBillService;
import com.lwxf.industry4.webapp.bizservice.financing.PaymentService;
import com.lwxf.industry4.webapp.bizservice.product.ProductPartsService;
import com.lwxf.industry4.webapp.bizservice.productattribute.ProductAttributeKeyService;
import com.lwxf.industry4.webapp.bizservice.productattribute.ProductAttributeValueService;
import com.lwxf.industry4.webapp.bizservice.supplier.MaterialService;
import com.lwxf.industry4.webapp.bizservice.supplier.SupplierService;
import com.lwxf.industry4.webapp.bizservice.system.BasecodeService;
import com.lwxf.industry4.webapp.bizservice.system.RoleService;
import com.lwxf.industry4.webapp.bizservice.user.UserService;
import com.lwxf.industry4.webapp.bizservice.warehouse.FinishedStockItemService;
import com.lwxf.industry4.webapp.bizservice.warehouse.FinishedStockService;
import com.lwxf.industry4.webapp.bizservice.warehouse.StorageService;
import com.lwxf.industry4.webapp.common.aop.syslog.OperationMoudule;
import com.lwxf.industry4.webapp.common.aop.syslog.OperationType;
import com.lwxf.industry4.webapp.common.aop.syslog.SysOperationLog;
import com.lwxf.industry4.webapp.common.component.BaseFileUploadComponent;
import com.lwxf.industry4.webapp.common.component.UploadInfo;
import com.lwxf.industry4.webapp.common.component.UploadType;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.UploadResourceType;
import com.lwxf.industry4.webapp.common.enums.aftersale.AftersaleStatus;
import com.lwxf.industry4.webapp.common.enums.aftersale.AftersaleType;
import com.lwxf.industry4.webapp.common.enums.company.*;
import com.lwxf.industry4.webapp.common.enums.customorder.*;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentFunds;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentStatus;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentTypeNew;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentWay;
import com.lwxf.industry4.webapp.common.enums.order.*;
import com.lwxf.industry4.webapp.common.enums.payment.PaymentResourceType;
import com.lwxf.industry4.webapp.common.enums.storage.FinishedStockStatus;
import com.lwxf.industry4.webapp.common.enums.storage.FinishedStockWay;
import com.lwxf.industry4.webapp.common.enums.user.UserState;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.uniquecode.UniquneCodeGenerator;
import com.lwxf.industry4.webapp.common.utils.AddressUtils;
import com.lwxf.industry4.webapp.common.utils.UserUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.common.utils.excel.BaseExportExcelUtil;
import com.lwxf.industry4.webapp.common.utils.excel.ExcelParam;
import com.lwxf.industry4.webapp.common.utils.excel.POIUtil;
import com.lwxf.industry4.webapp.config.LoggerBuilder;
import com.lwxf.industry4.webapp.domain.dto.aftersale.AftersaleDto;
import com.lwxf.industry4.webapp.domain.dto.company.CompanyDto;
import com.lwxf.industry4.webapp.domain.dto.customorder.*;
import com.lwxf.industry4.webapp.domain.dto.dealer.WxDealerLogisticsDto;
import com.lwxf.industry4.webapp.domain.dto.financing.PaymentDto;
import com.lwxf.industry4.webapp.domain.dto.financing.PaymentProduceOrderDto;
import com.lwxf.industry4.webapp.domain.dto.printTable.OrderPrintTableDto;
import com.lwxf.industry4.webapp.domain.dto.product.ProductPartsDto;
import com.lwxf.industry4.webapp.domain.dto.warehouse.FinishedStockItemDto;
import com.lwxf.industry4.webapp.domain.entity.aftersale.AftersaleApply;
import com.lwxf.industry4.webapp.domain.entity.aftersale.AftersaleApplyFiles;
import com.lwxf.industry4.webapp.domain.entity.branch.Branch;
import com.lwxf.industry4.webapp.domain.entity.common.UploadFiles;
import com.lwxf.industry4.webapp.domain.entity.company.Company;
import com.lwxf.industry4.webapp.domain.entity.company.CompanyEmployee;
import com.lwxf.industry4.webapp.domain.entity.company.CompanyMemberOrder;
import com.lwxf.industry4.webapp.domain.entity.company.CompanyShareMember;
import com.lwxf.industry4.webapp.domain.entity.corporatePartners.CorporatePartners;
import com.lwxf.industry4.webapp.domain.entity.customer.CompanyCustomer;
import com.lwxf.industry4.webapp.domain.entity.customorder.*;
import com.lwxf.industry4.webapp.domain.entity.dealer.DealerAccount;
import com.lwxf.industry4.webapp.domain.entity.dealer.DealerAccountLog;
import com.lwxf.industry4.webapp.domain.entity.financing.Payment;
import com.lwxf.industry4.webapp.domain.entity.org.Dept;
import com.lwxf.industry4.webapp.domain.entity.product.ProductParts;
import com.lwxf.industry4.webapp.domain.entity.productattribute.ProductAttributeValue;
import com.lwxf.industry4.webapp.domain.entity.supplier.Material;
import com.lwxf.industry4.webapp.domain.entity.supplier.Supplier;
import com.lwxf.industry4.webapp.domain.entity.system.Basecode;
import com.lwxf.industry4.webapp.domain.entity.system.Role;
import com.lwxf.industry4.webapp.domain.entity.user.User;
import com.lwxf.industry4.webapp.domain.entity.warehouse.FinishedStock;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.dealer.OrderFacade;
import com.lwxf.industry4.webapp.facade.admin.factory.order.CustomOrderRemindFacade;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.mybatis.utils.MapContext;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 功能：
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @create：2018/12/6 16:34
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("orderFacade")
public class OrderFacadeImpl extends BaseFacadeImpl implements OrderFacade {
	LoggerBuilder loggerBuilder =new LoggerBuilder();
	Logger logger = loggerBuilder.getLogger("paymentsimple");
	@Resource(name = "customOrderService")
	private CustomOrderService customOrderService;
	@Resource(name = "customOrderDemandService")
	private CustomOrderDemandService customOrderDemandService;
	@Resource(name = "customOrderDesignService")
	private CustomOrderDesignService customOrderDesignService;//设计
	@Resource(name = "customOrderFilesService")
	private CustomOrderFilesService customOrderFilesService;
	@Resource(name = "baseFileUploadComponent")
	private BaseFileUploadComponent baseFileUploadComponent;
	@Resource(name = "userService")
	private UserService userService;
	@Resource(name = "companyEmployeeService")
	private CompanyEmployeeService companyEmployeeService;
	@Resource(name = "roleService")
	private RoleService roleService;
	@Resource(name = "dealerAccountService")
	private DealerAccountService dealerAccountService;
	@Resource(name = "dealerAccountLogService")
	private DealerAccountLogService dealerAccountLogService;
	@Resource(name = "companyService")
	private CompanyService companyService;
	@Resource(name = "aftersaleApplyService")
	private AftersaleApplyService aftersaleApplyService;
	@Resource(name = "paymentService")
	private PaymentService paymentService;
	@Resource(name = "orderAccountLogService")
	private OrderAccountLogService orderAccountLogService;
	@Resource(name = "produceOrderService")
	private ProduceOrderService produceOrderService;
	@Resource(name = "orderProductService")
	private OrderProductService orderProductService;
	@Resource(name = "produceFlowService")
	private ProduceFlowService produceFlowService;
	@Resource(name = "storageService")
	private StorageService storageService;
	@Resource(name = "finishedStockService")
	private FinishedStockService finishedStockService;
	@Resource(name = "finishedStockItemService")
	private FinishedStockItemService finishedStockItemService;
	@Resource(name = "uploadFilesService")
	private UploadFilesService uploadFilesService;
	@Resource(name = "dispatchBillService")
	private DispatchBillService dispatchBillService;
	@Resource(name = "dispatchBillItemService")
	private DispatchBillItemService dispatchBillItemService;
	@Resource(name = "dispatchBillPlanItemService")
	private DispatchBillPlanItemService dispatchBillPlanItemService;
	@Resource(name = "aftersaleApplyFilesService")
	private AftersaleApplyFilesService aftersaleApplyFilesService;
	@Resource(name = "orderOfferService")
	private OrderOfferService orderOfferService;
	@Resource(name = "offerItemService")
	private OfferItemService offerItemService;
	@Resource(name = "basecodeService")
	private BasecodeService basecodeService;
	@Resource(name = "companyCustomerService")
	private CompanyCustomerService companyCustomerService;
	@Resource(name = "customOrderLogService")
	private CustomOrderLogService customOrderLogService;
	@Resource(name = "orderProductPartsService")
	private OrderProductPartsService orderProductPartsService;
	@Resource(name = "productPartsService")
	private ProductPartsService productPartsService;
	@Resource(name = "cityAreaService")
	private CityAreaService cityAreaService;
	@Resource(name = "branchService")
	private BranchService branchService;
	@Resource(name = "customOrderRemindFacade")
	private CustomOrderRemindFacade customOrderRemindFacade;
	@Resource(name = "corporatePartnersService")
	private CorporatePartnersService corporatePartnersService;
	@Resource(name = "dealerLogisticsService")
	private DealerLogisticsService dealerLogisticsService;
	@Resource(name = "companyShareMemberService")
	private CompanyShareMemberService companyShareMemberService;
	@Resource(name = "companyMemberOrderService")
	private CompanyMemberOrderService companyMemberOrderService;
	@Resource(name = "customOrderTimeService")
	private CustomOrderTimeService customOrderTimeService;
	@Resource(name = "customOrderRemindService")
	private CustomOrderRemindService customOrderRemindService;
	@Resource(name = "deptService")
	private DeptService deptService;
	@Resource(name = "productAttributeValueService")
	private ProductAttributeValueService productAttributeValueService;
	@Resource(name = "productAttributeKeyService")
	private ProductAttributeKeyService productAttributeKeyService;
	@Resource(name = "orderProductAttributeService")
	private OrderProductAttributeService orderProductAttributeService;
	@Resource(name = "materialService")
	private MaterialService materialService;
	@Resource(name = "supplierService")
	private SupplierService supplierService;

	/**
	 * 订单列表
	 *
	 * @param pageNum
	 * @param pageSize
	 * @param params
	 * @return
	 */
	@Override
	public RequestResult selectByCondition(Integer pageNum, Integer pageSize, MapContext params, String uid, String cid) {
		CompanyEmployee companyEmployee = this.companyEmployeeService.findOneByCompanyIdAndUserId(cid, uid);
		if (null == companyEmployee) {
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_NOT_ALLOW_OPERATION_10020, AppBeanInjector.i18nUtil.getMessage("BIZ_NOT_ALLOW_OPERATION_10020"));
		}
		PaginatedList<CustomOrderDto> customOrderPaginatedList;
		PaginatedFilter filter = PaginatedFilter.newOne();
		Pagination pagination = Pagination.newOne();
		pagination.setPageSize(pageSize);
		pagination.setPageNum(pageNum);
		filter.setPagination(pagination);
		filter.setFilters(params);
		//判断是不是店主或者经理，true-查询店铺下的所有订单，fales-查询店铺下业务员的订单
		String roleId = companyEmployee.getRoleId();
		//经理
		Role manager = this.roleService.selectByKey(DealerEmployeeRole.MANAGER.getValue(), WebUtils.getCurrBranchId());
		//店主
		Role shopkeeper = this.roleService.selectByKey(DealerEmployeeRole.SHOPKEEPER.getValue(), WebUtils.getCurrBranchId());
		if (roleId.equals(manager.getId()) || roleId.equals(shopkeeper.getId())) {
			customOrderPaginatedList = this.customOrderService.selectByCondition(filter);
		} else {
			params.put("salesman", uid);
			filter.setFilters(params);
			customOrderPaginatedList = this.customOrderService.selectByCondition(filter);
		}
		for (int i = 0; i < customOrderPaginatedList.getRows().size(); i++) {
			String city = customOrderPaginatedList.getRows().get(i).getCityName();//地区地址
			String address1 = customOrderPaginatedList.getRows().get(i).getAddress();//详细地址
			String address = AddressUtils.getAddress(city, address1);
			customOrderPaginatedList.getRows().get(i).setAddress(address);
			customOrderPaginatedList.getRows().get(i).setCityName(null);
			customOrderPaginatedList.getRows().get(i).setCityAreaId(null);
		}
		return ResultFactory.generateRequestResult(customOrderPaginatedList);
	}

	/**
	 * 查询外协财务付款列表
	 */
	@Override
	public RequestResult queryOrderOutsourcePayList(PaginatedFilter paginatedFilter) {
		//查询payment 待付款列表
		PaginatedList<PaymentProduceOrderDto> produceOrderPaginatedList = this.produceOrderService.queryOrderOutsourcePayList(paginatedFilter);
		return ResultFactory.generateRequestResult(produceOrderPaginatedList);
	}

	/**
	 * 订单详情
	 *
	 * @param id
	 * @return
	 */
	@Override
	public RequestResult selectByOrderId(String id) {
		//订单信息
		CustomOrderDto customOrderDto = this.customOrderService.findByOrderId(id);
		if (null == customOrderDto) {
			return ResultFactory.generateRequestResult(customOrderDto);
		}
		String city = customOrderDto.getCityName();
		String address1 = customOrderDto.getAddress();//详细地址
		String address = AddressUtils.getAddress(city, address1);
		customOrderDto.setAddress(address);
		customOrderDto.setCityName(null);
		List<CustomOrderFiles> filesList = this.customOrderFilesService.selectByOrderIdAndType(id, 2, null);
		List<String> filesPath = new ArrayList<>();
		for (CustomOrderFiles customOrderFile : filesList) {
			String path = customOrderFile.getPath();
			filesPath.add(path);
		}
		MapContext data = MapContext.newOne();
		data.put("customOrder", customOrderDto);
		data.put("filesPath", filesPath);
		return ResultFactory.generateRequestResult(data);
	}

	/**
	 * 添加订单
	 *
	 * @param customOrder
	 * @param
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager")
	@SysOperationLog(detail = "新建订单", operationType = OperationType.INSERT, operationMoudule = OperationMoudule.CUSTOM_ORDER)
	public RequestResult addOrder(CustomOrder customOrder, String uid, String cid) {
		Integer type = customOrder.getType();
		boolean contains = OrderType.contains(type);
		if (!contains) {
			MapContext result = new MapContext();
			result.put("type", ErrorCodes.VALIDATE_ILLEGAL_ARGUMENT);
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, result);
		}
		customOrder.setNo(AppBeanInjector.uniquneCodeGenerator.getNextNo(UniquneCodeGenerator.UniqueResource.ORDER_NO));
		customOrder.setCompanyId(cid);
		customOrder.setCreated(DateUtil.getSystemDate());
		customOrder.setCreator(uid);
		customOrder.setStatus(0);
		customOrder.setAmount(new BigDecimal(0));
		customOrder.setImprest(new BigDecimal(0));
		customOrder.setRetainage(new BigDecimal(0));
		customOrder.setEarnest(0);
		customOrder.setFactoryPrice(new BigDecimal(0));
		customOrder.setMarketPrice(new BigDecimal(0));
		customOrder.setDiscounts(new BigDecimal(0));
		customOrder.setFactoryDiscounts(new BigDecimal(0));
		customOrder.setFactoryFinalPrice(new BigDecimal(0));
		customOrder.setConfirmFprice(false);
		customOrder.setConfirmCprice(false);
		customOrder.setFlag(0);
		RequestResult result = customOrder.validateFields();
		if (null != result) {
			return result;
		}
		this.customOrderService.add(customOrder);
//        CustomOrderLog customOrderLog = new CustomOrderLog();
//        customOrderLog.setContent("顾客XXX,添加订单了,订单编号为："+customOrder.getNo());
//        customOrderLog.setCreated(DateUtil.getSystemDate());
//        customOrderLog.setCreator(uid);
//        customOrderLog.setCustomOrderId(customOrder.getId());
//        customOrderLog.setName("创建订单");
//        //customOrderLog.setNotes();
//        customOrderLog.setStage(CustomOrderLogStage.Marketing.getValue());
//        //customOrderLog.setPath();
//        this.customOrderLogService.add(customOrderLog);
		MapContext map = MapContext.newOne();
		map.put("id", customOrder.getId());
		return ResultFactory.generateRequestResult(map);

	}

	/**
	 * 修改订单
	 *
	 * @param updateMap
	 * @param request
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager")
	@SysOperationLog(detail = "修改订单信息", operationType = OperationType.UPDATE, operationMoudule = OperationMoudule.CUSTOM_ORDER)
	public RequestResult updateOrder(MapContext updateMap, HttpServletRequest request) {
		this.customOrderService.updateByMapContext(updateMap);
		return ResultFactory.generateSuccessResult();
	}

	/**
	 * 删除订单
	 *
	 * @param orderId
	 * @param request
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager")
	@SysOperationLog(detail = "删除订单", operationType = OperationType.DELETE, operationMoudule = OperationMoudule.CUSTOM_ORDER)
	public RequestResult deleteByOrderId(String orderId, HttpServletRequest request) {

		List<CustomOrderFiles> filesList = this.customOrderFilesService.selectByOrderId(orderId);
		Map<String, UploadResourceType> files = new HashedMap();
		for (CustomOrderFiles file : filesList) {
			files.put(file.getPath(), UploadResourceType.CUSTOM_ORDER);
		}
		this.customOrderFilesService.deleteByOrderId(orderId);
		baseFileUploadComponent.deleteFiles(files, 0);
		this.customOrderDesignService.deleteByOrderId(orderId);
		this.customOrderDemandService.deleteByOrderId(orderId);
		this.customOrderService.deleteById(orderId);

		return ResultFactory.generateSuccessResult();
	}


	/**
	 * 修改订单的状态,将订单的状态改为1
	 * 提交需求。修改订单的状态。
	 *
	 * @param params
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager")
	public RequestResult commitOrderDemand(MapContext params) {
		String id = params.getTypedValue("id", String.class);

		CustomOrder customOrder = this.customOrderService.findById(id);
		if (null == customOrder) {
			return ResultFactory.generateResNotFoundResult();
		}

		Integer status = customOrder.getStatus();
		switch (status) {
			case 0:
				status = 1;
				break;
		}

		params.put("status", status);
		this.customOrderService.updateByMapContext(params);
		MapContext data = MapContext.newOne();
		return ResultFactory.generateRequestResult(data);
	}

	@Override
	public RequestResult findFinishedOrderList(List<Integer> statusList, Integer pageNum, Integer pageSize, boolean finishedOrder) {
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		Pagination pagination = new Pagination();
		pagination.setPageNum(pageNum);
		pagination.setPageSize(pageSize);
		paginatedFilter.setPagination(pagination);
		MapContext mapContext = MapContext.newOne();
		mapContext.put(WebConstant.KEY_ENTITY_STATUS, statusList);
		mapContext.put("finishedOrder", finishedOrder);
		mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID, WebUtils.getCurrBranchId());
		paginatedFilter.setFilters(mapContext);
		List<Map<String, String>> sorts = new ArrayList<Map<String, String>>();
		Map<String, String> created = new HashMap<String, String>();
		created.put(WebConstant.KEY_ENTITY_CREATED, "desc");
		sorts.add(created);
		paginatedFilter.setSorts(sorts);
		return ResultFactory.generateRequestResult(this.customOrderService.findFinishedOrderList(paginatedFilter));
	}

	@Override
	public RequestResult findOrderList(MapContext mapContext, Integer pageNum, Integer pageSize) {
		//查询是否开启催款功能
		Branch branch = AppBeanInjector.branchService.findById(WebUtils.getCurrBranchId());
		Integer enableRemind = branch.getEnableRemind();
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		Pagination pagination = new Pagination();
		pagination.setPageNum(pageNum);
		pagination.setPageSize(pageSize);
		paginatedFilter.setPagination(pagination);
		mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID, WebUtils.getCurrBranchId());
		mapContext.put("funds", PaymentFunds.ORDER_FEE_CHARGE.getValue());
		paginatedFilter.setFilters(mapContext);
		Map<String, String> created = new HashMap<String, String>();
		created.put(WebConstant.KEY_ENTITY_CREATED, "desc");
		List sort = new ArrayList();
		sort.add(created);
		paginatedFilter.setSorts(sort);
		PaginatedList<CustomOrderDto> listByPaginateFilter = this.customOrderService.findListByPaginateFilter(paginatedFilter);
		if (listByPaginateFilter.getRows().size() > 0) {
			for (CustomOrderDto customOrderDto : listByPaginateFilter.getRows()) {
				String orderId = customOrderDto.getId();
				List<OrderProductDto> productDtos = this.orderProductService.findListByOrderId(orderId);
				customOrderDto.setOrderProductDtoList(productDtos);
				CustomOrderTime customOrderTime=this.customOrderTimeService.findMaxByOrderId(orderId);//查询总工期最长的数据
				MapContext map=MapContext.newOne();
				//判断是工厂下单还是经销商传单
				Integer orderSource = customOrderDto.getOrderSource();
				Date receiveTimeStart=new Date();//接单起始时间
				if(orderSource==null||orderSource==0){
					receiveTimeStart=customOrderDto.getCreated();
				}else {
					receiveTimeStart=customOrderDto.getLeafletTime();
				}
				//接单工期
				Date receiptTime = customOrderDto.getReceiptTime();
				Integer receiveTimeDate=1;
				if(customOrderTime!=null){
					receiveTimeDate = customOrderTime.getReceiveTime();
				}
				String receiveInfo="";
				if(receiptTime==null||receiptTime.equals("")){
					receiptTime=DateUtil.getSystemDate();
				}
				if(receiveTimeStart!=null&&!receiveTimeStart.equals("")) {
					receiveInfo = OrderFacadeImpl.getTimeInfo(receiveTimeStart, receiptTime, receiveTimeDate);
				}
				//查询是否有催款环节
				CustomOrderRemind customOrderRemind=null;
				if(enableRemind==1){
					customOrderRemind = this.customOrderRemindService.selectByOrderId(orderId);
				}
				//下单工期
				Integer orderTime=1;
				if(customOrderTime!=null){
					orderTime=customOrderTime.getOrderTime();
				}
				String placeTimeInfo="";
				receiptTime = customOrderDto.getReceiptTime();
				Date commitTime = customOrderDto.getCommitTime();
				if(commitTime==null||commitTime.equals("")){
					commitTime=DateUtil.getSystemDate();
				}
				if(receiptTime!=null&&!receiptTime.equals("")) {
					placeTimeInfo = OrderFacadeImpl.getTimeInfo(receiptTime, commitTime, orderTime);
				}
//				//催款工期
//				String pressForMoneyTime="";
//				if(customOrderRemind!=null) {
//
//					if (customOrderRemind.getCommitTime() != null && !customOrderRemind.getCommitTime().equals("")) {
//						pressForMoneyTime = OrderFacadeImpl.getTimeInfo(customOrderRemind.getCreated(), customOrderRemind.getCommitTime(), customOrderTime.getOrderRemind());
//					}else{
//						pressForMoneyTime = OrderFacadeImpl.getTimeInfo(customOrderRemind.getCreated(), DateUtil.getSystemDate(), customOrderTime.getOrderRemind());
//					}
//				}
//				//审核工期
//				String auditInfo="";
//				Date commitTimeT = customOrderDto.getCommitTime();
//				Date payTime=customOrderDto.getPayTime();
//				if(customOrderRemind!=null){
//					commitTimeT=customOrderRemind.getCommitTime();
//				}
//				if(customOrderDto.getPayTime()==null||customOrderDto.getPayTime().equals("")){
//					payTime=DateUtil.getSystemDate();
//				}
//				if(commitTimeT!=null&&!commitTimeT.equals("")){
//					auditInfo=OrderFacadeImpl.getTimeInfo(commitTimeT,payTime,customOrderTime.getAuditTime());
//				}
//
//
//				//生产发货工期
//				String produceAndDeliveryInfo="";
//				Date documentaryTime = customOrderDto.getDocumentaryTime();//下车间时间
//				Date deliveryDate = customOrderDto.getDeliveryDate();//发货时间
//				if(deliveryDate==null||deliveryDate.equals("")){
//					deliveryDate=DateUtil.getSystemDate();
//				}
//				if(documentaryTime!=null&&!documentaryTime.equals("")){
//					produceAndDeliveryInfo=OrderFacadeImpl.getTimeInfo(documentaryTime,deliveryDate,customOrderTime.getProduceTime());
//				}
				customOrderDto.setReceiveInfo(receiveInfo);
				customOrderDto.setPlaceTimeInfo(placeTimeInfo);
//				customOrderDto.setPressForMoneyTime(pressForMoneyTime);
//				customOrderDto.setAuditInfo(auditInfo);
//				customOrderDto.setProduceAndDeliveryInfo(produceAndDeliveryInfo);
			}
		}
		return ResultFactory.generateRequestResult(listByPaginateFilter);
	}

	@Override
	@Transactional(value = "transactionManager")
	@SysOperationLog(detail = "修改设计方案", operationType = OperationType.UPDATE, operationMoudule = OperationMoudule.DESIGN)
	public RequestResult updateOrderDesigner(String userId, String id) {
		//该用户是否存在并且是否是厂家的员工以及该角色是否是设计师
		if (!UserUtil.hasRoleByKey(userId, FactoryEmployeeRole.DESIGNER.getValue())) {
			return ResultFactory.generateResNotFoundResult();
		}
		MapContext mapContext = new MapContext();
		mapContext.put(WebConstant.KEY_ENTITY_ID, id);
		mapContext.put("designer", userId);
		this.customOrderService.updateByMapContext(mapContext);
		return ResultFactory.generateRequestResult(this.customOrderService.findByOrderId(id));
	}

	@Override
	public RequestResult findOrderInfo(String id) {
		//判断订单是否存在
		CustomOrderDto customOrderDto = this.customOrderService.findByOrderId(id);
		if (customOrderDto == null) {
			return ResultFactory.generateResNotFoundResult();
		}
		//如果是售后，查询是否有售后责任人及部门信息
		if(customOrderDto.getType()==OrderType.SUPPLEMENTORDER.getValue()) {
			if (customOrderDto.getAfterPerson() != null && customOrderDto.getAfterPerson().equals("")) {
				User byUserId = this.userService.findByUserId(customOrderDto.getAfterPerson());
				if (byUserId != null) {
					customOrderDto.setAfterPersonName(byUserId.getName());
				}
			}

		if(customOrderDto.getAfterDept()!=null&&customOrderDto.getAfterDept().equals("")){
			Dept byId = this.deptService.findById(customOrderDto.getAfterDept());
			if(byId!=null){
				customOrderDto.setAfterDeptName(byId.getName());
			}
		}
		}
		//判断源数据保存的是省or市or区？
		String provinceId = customOrderDto.getProvinceId();
		String cityId = customOrderDto.getCityId();
		String cityAreaId = customOrderDto.getCityAreaId();
		if (cityId == null) {
			provinceId = customOrderDto.getCityAreaId();
			cityAreaId = null;
		} else if (provinceId == null) {
			provinceId = customOrderDto.getCityId();
			cityId = customOrderDto.getCityAreaId();
			cityAreaId = null;
		}
		customOrderDto.setProvinceId(provinceId);
		customOrderDto.setCityId(cityId);
		customOrderDto.setCityAreaId(cityAreaId);
		CustomOrderInfoDto customOrderInfoDto = new CustomOrderInfoDto();
		customOrderInfoDto.setCustomOrder(customOrderDto);
		customOrderInfoDto.setOrderContractFiles(this.customOrderFilesService.selectByOrderIdAndType(id, CustomOrderFilesType.CONTRACT.getValue(), null));
		customOrderInfoDto.setOrderDemand(this.customOrderDemandService.findListByOrderId(id));
		customOrderInfoDto.setOrderDesign(this.customOrderDesignService.findListByOrderId(id));
		//customOrderInfoDto.setOrderContractFiles(this.customOrderFilesService.selectByOrderIdAndType(id, CustomOrderFilesType.CONTRACT.getValue(), null));
		customOrderInfoDto.setOrderAccountLogList(this.orderAccountLogService.findByOrderId(id));
		List<OrderProductDto> listByOrderId = this.orderProductService.findListByOrderId(id);
		if (listByOrderId.size() > 0) {
			for (OrderProductDto orderProductDto : listByOrderId) {
				List<ProductPartsDto> productPartsDtos = this.productPartsService.findByOrderProductId(orderProductDto.getId());
				List<OrderProductAttribute> listByProductId = this.orderProductAttributeService.findListByProductId(orderProductDto.getId());
				orderProductDto.setProductAttributeValues(listByProductId);
				orderProductDto.setProductPartsDtos(productPartsDtos);
			}
		}
		//财务信息
		MapContext params = MapContext.newOne();
		params.put("orderId", id);
		params.put("funds", PaymentFunds.ORDER_FEE_CHARGE.getValue());
		PaymentDto paymentDto = this.paymentService.findByOrderIdAndFunds(params);
		customOrderInfoDto.setPayment(paymentDto);
		customOrderInfoDto.setOrderProducts(listByOrderId);
		MapContext mapContext = MapContext.newOne();
		mapContext.put("id", id);
		customOrderInfoDto.setProduceOrderDtos(this.produceOrderService.findListByOrderId(mapContext));
		customOrderInfoDto.setOrderOffer(this.orderOfferService.findByOrerId(id));
		if (customOrderInfoDto.getOrderOffer() != null) {
			customOrderInfoDto.setOfferItems(this.offerItemService.findByOfferId(customOrderInfoDto.getOrderOffer().getId()));
		}

		// 查询催款信息
        CustomOrderRemindDto customOrderRemindDto = this.customOrderRemindFacade.selectDtoByOrderId(id);
		if (customOrderRemindDto != null) {
            customOrderInfoDto.setRemindUserId(customOrderRemindDto.getReceiver());
            customOrderInfoDto.setRemindUserName(customOrderRemindDto.getReceiverName());
        }

		return ResultFactory.generateRequestResult(customOrderInfoDto);
	}

	@Override
	public RequestResult findOrderInfoNew(String id) {
		//判断订单是否存在
		CustomOrderDto customOrderDto = this.customOrderService.findByOrderId(id);
		if (customOrderDto == null) {
			return ResultFactory.generateResNotFoundResult();
		}
		CustomOrderInfoDto customOrderInfoDto = new CustomOrderInfoDto();
		customOrderInfoDto.setCustomOrder(customOrderDto);
		customOrderInfoDto.setOrderDemand(this.customOrderDemandService.findListByOrderId(id));
		customOrderInfoDto.setOrderDesign(this.customOrderDesignService.findListByOrderId(id));
		customOrderInfoDto.setOrderContractFiles(this.customOrderFilesService.selectByOrderIdAndType(id, CustomOrderFilesType.CONTRACT.getValue(), null));
		customOrderInfoDto.setOrderAccountLogList(this.orderAccountLogService.findByOrderId(id));
		List<OrderProductDto> productsList = this.orderProductService.findListByOrderId(id);
		MapContext map=MapContext.newOne();
		map.put("orderId",id);
		map.put("funds",PaymentFunds.ORDER_FEE_CHARGE.getValue());
		PaymentDto byOrderIdAndFunds = this.paymentService.findByOrderIdAndFunds(map);
		customOrderInfoDto.setPayment(byOrderIdAndFunds);
		if (productsList.size() > 0) {
			for (OrderProductDto orderProductDto : productsList) {
				List<ProductPartsDto> productPartsDtos = this.productPartsService.findByOrderProductId(orderProductDto.getId());
				List<OrderProductAttribute> listByProductId = this.orderProductAttributeService.findListByProductId(orderProductDto.getId());
				orderProductDto.setProductAttributeValues(listByProductId);
				orderProductDto.setProductPartsDtos(productPartsDtos);
			}
		}
		//物流信息
		customOrderInfoDto.setDispatchBillMap(this.dispatchBillService.findDispatchsInfoByOrderId(id));
		customOrderInfoDto.setOrderProducts(productsList);
		MapContext mapContext = MapContext.newOne();
		mapContext.put("id", id);
		mapContext.put("way", ProduceOrderWay.COORDINATION.getValue());
		customOrderInfoDto.setProduceOrderDtos(this.produceOrderService.findListByOrderId(mapContext));
		customOrderInfoDto.setOrderOffer(this.orderOfferService.findByOrerId(id));
		if (customOrderInfoDto.getOrderOffer() != null) {
			customOrderInfoDto.setOfferItems(this.offerItemService.findByOfferId(customOrderInfoDto.getOrderOffer().getId()));
		}
		//追加单信息
        List<CustomOrderDto> appendOrderList=new ArrayList<>();
        if(customOrderDto.getHaveAppendOrder()!=null&&customOrderDto.getHaveAppendOrder()==1){
        	String orderId=customOrderDto.getId();
        	MapContext params=MapContext.newOne();
        	params.put("parentId",orderId);
        	params.put("type",OrderType.APPENDORDER.getValue());
			List<CustomOrder> byParentIdAndType = this.customOrderService.findByParentIdAndType(params);
			for(CustomOrder customOrder:byParentIdAndType){
				String appendOrderId=customOrder.getId();
				CustomOrderDto byOrderId = this.customOrderService.findByOrderId(appendOrderId);
				appendOrderList.add(byOrderId);
			}
		}
        customOrderInfoDto.setAppendCustomOrder(appendOrderList);
		return ResultFactory.generateRequestResult(customOrderInfoDto);
	}

	@Override
	public RequestResult findOrderDemand(String id, String demandId) {
		CustomOrderDemandDto customOrderDemandDto = this.customOrderDemandService.selectByDemandId(demandId);
		if (customOrderDemandDto == null || !customOrderDemandDto.getCustomOrderId().equals(id)) {
			return ResultFactory.generateResNotFoundResult();
		}
		MapContext mapContext = MapContext.newOne();
		mapContext.put("customOrderDemand", customOrderDemandDto);
		mapContext.put("demandFile", this.customOrderFilesService.selectByOrderIdAndType(id, 0, demandId));
		return ResultFactory.generateRequestResult(mapContext);
	}

	@Override
	public RequestResult findOrderDesignId(String id, String designId) {
		CustomOrderDesignDto orderDesignDto = this.customOrderDesignService.findOneByDesignId(designId);
		if (orderDesignDto == null || !orderDesignDto.getCustomOrderId().equals(id)) {
			return ResultFactory.generateResNotFoundResult();
		}
		MapContext mapContext = MapContext.newOne();
		mapContext.put("orderDesignDto", orderDesignDto);
		mapContext.put("designFile", this.customOrderFilesService.selectByOrderIdAndType(id, null, designId));
		return ResultFactory.generateRequestResult(mapContext);
	}

	@Override
	@Transactional(value = "transactionManager")
	@SysOperationLog(detail = "新增设计方案", operationType = OperationType.INSERT, operationMoudule = OperationMoudule.DESIGN)
	public RequestResult addOrderDesign(String id, CustomOrderDesign customOrderDesign) {
		//判断订单是否存在
		CustomOrder customOrder = this.customOrderService.findById(id);
		if (customOrder == null) {
			return ResultFactory.generateResNotFoundResult();
		}
		//判断产品是否存在
		OrderProduct orderProduct = this.orderProductService.findById(customOrderDesign.getOrderProductId());
		if (orderProduct == null) {
			return ResultFactory.generateResNotFoundResult();
		}
		this.customOrderDesignService.add(customOrderDesign);
		return ResultFactory.generateRequestResult(this.customOrderDesignService.findOneByDesignId(customOrderDesign.getId()));
	}

	@Override
	@Transactional(value = "transactionManager")
	@SysOperationLog(detail = "修改设计方案", operationType = OperationType.UPDATE, operationMoudule = OperationMoudule.DESIGN)
	public RequestResult updateDesign(String id, String designId, MapContext mapContext) {
		//判断设计记录是否存在
		CustomOrderDesign customOrderDesign = this.customOrderDesignService.findById(designId);
		if (customOrderDesign == null || !customOrderDesign.getCustomOrderId().equals(id)) {
			return ResultFactory.generateResNotFoundResult();
		}
		//todo:判断修改操作是否是修改状态 如果存在 待确认或者已确认的设计记录则不允许操作
		mapContext.put(WebConstant.KEY_ENTITY_ID, designId);
		this.customOrderDesignService.updateByMapContext(mapContext);
		return ResultFactory.generateRequestResult(this.customOrderDesignService.findOneByDesignId(designId));
	}

	@Override
	@Transactional(value = "transactionManager")
	@SysOperationLog(detail = "删除设计方案", operationType = OperationType.DELETE, operationMoudule = OperationMoudule.DESIGN)
	public RequestResult deleteDesign(String id, String designId) {
		//判断设计记录是否存在
		CustomOrderDesign customOrderDesign = this.customOrderDesignService.findById(designId);
		if (customOrderDesign == null || !customOrderDesign.getCustomOrderId().equals(id)) {
			return ResultFactory.generateSuccessResult();
		}
		this.customOrderDesignService.deleteById(designId);
		//删除相关资源
		this.customOrderFilesService.deleteByBelongId(designId, id);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult uploadFile(String id, String designId, List<MultipartFile> multipartFileList, Integer category) {
		//判断设计记录是否存在
		CustomOrderDesign customOrderDesign = this.customOrderDesignService.findById(designId);
		if (customOrderDesign == null || !customOrderDesign.getCustomOrderId().equals(id)) {
			return ResultFactory.generateResNotFoundResult();
		}
		CustomOrderFiles customOrderFiles = new CustomOrderFiles();
		customOrderFiles.setBelongId(designId);
		customOrderFiles.setCustomOrderId(id);
		customOrderFiles.setCategory(category);
		customOrderFiles.setStatus(CustomOrderFilesStatus.FORMAL.getValue());
		customOrderFiles.setType(CustomOrderFilesType.DESIGN.getValue());
		customOrderFiles.setCreated(DateUtil.getSystemDate());
		customOrderFiles.setCreator(WebUtils.getCurrUserId());
		List imgList = new ArrayList();
		for (MultipartFile multipartFile : multipartFileList) {
			UploadInfo uploadInfo = AppBeanInjector.baseFileUploadComponent.doUploadByModule(UploadType.FORMAL, multipartFile, UploadResourceType.CUSTOM_ORDER, id, designId);
			customOrderFiles.setPath(uploadInfo.getRelativePath());
			customOrderFiles.setFullPath(AppBeanInjector.configuration.getDomainUrl() + uploadInfo.getRelativePath());
			customOrderFiles.setName(uploadInfo.getFileName());
			customOrderFiles.setMime(uploadInfo.getFileMimeType().getRealType());
			customOrderFiles.setOriginalMime(uploadInfo.getFileMimeType().getOriginalType());
			this.customOrderFilesService.add(customOrderFiles);
			imgList.add(customOrderFiles);
		}
		return ResultFactory.generateRequestResult(imgList);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult deleteFile(String id, String designId, String fileId) {
		//判断图片是否存在
		CustomOrderFiles customOrderFiles = this.customOrderFilesService.findById(fileId);
		if (customOrderFiles == null || !customOrderFiles.getBelongId().equals(designId) || !customOrderFiles.getCustomOrderId().equals(id)) {
			return ResultFactory.generateSuccessResult();
		}
		//删除资源
		AppBeanInjector.baseFileUploadComponent.deleteFileByDir(AppBeanInjector.configuration.getUploadFileRootDir().concat(customOrderFiles.getPath()));
		this.customOrderFilesService.deleteById(fileId);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	@SysOperationLog(detail = "新增订单", operationType = OperationType.INSERT, operationMoudule = OperationMoudule.CUSTOM_ORDER)
	public RequestResult factoryAddOrder(CustomOrderInfoDto customOrderInfoDto, String aftersaleId, Payment payment, String noValue) {
		CustomOrder customOrder = customOrderInfoDto.getCustomOrder();
		//如果存在父级订单 则判断是否存在
		if (customOrder.getParentId() != null) {
			if (this.customOrderService.findById(customOrder.getParentId()) == null) {
				return ResultFactory.generateResNotFoundResult();
			}
		}
        Integer type = customOrder.getType();
		if(7==type){
            MapContext param = MapContext.newOne();
            param.put("id",customOrder.getParentId());
            param.put("haveAppendOrder", "1");
            this.customOrderService.updateByMapContext(param);
        }
        Company company = this.companyService.findById(customOrder.getCompanyId());
		//判断经销商是否存在 状态是否是正常
		if (company == null || !company.getStatus().equals(CompanyStatus.NORMAL.getValue())) {
			return ResultFactory.generateResNotFoundResult();
		}


		//判断跟单员(厂家) 和接单员(经销商) 是否真实存在
		if(customOrderInfoDto.getCustomOrder().getMerchandiser()!=null) {
			User merchandiser = this.userService.findById(customOrderInfoDto.getCustomOrder().getMerchandiser());
			if (merchandiser == null || !merchandiser.getState().equals(UserState.ENABLED.getValue())) {
				return ResultFactory.generateResNotFoundResult();
			}
		}
//		User saleman = this.userService.findById(customOrderInfoDto.getCustomOrder().getSalesman());
//		if(saleman==null||!saleman.getState().equals(UserState.ENABLED.getValue())){
//			return ResultFactory.generateResNotFoundResult();
//		}
		//判断客户是否存在
		MapContext filter = new MapContext();
		filter.put(WebConstant.KEY_ENTITY_NAME, customOrderInfoDto.getCompanyCustomer().getName());
		filter.put(WebConstant.KEY_ENTITY_COMPANY_ID, company.getId());
		List<CompanyCustomer> customerByMap = this.companyCustomerService.findCustomerByMap(filter);
		if (customerByMap == null || customerByMap.size() == 0) {
			CompanyCustomer companyCustomer = new CompanyCustomer();
			companyCustomer.setName(customOrderInfoDto.getCompanyCustomer().getName());
			companyCustomer.setCompanyId(company.getId());
			companyCustomer.setCreated(DateUtil.getSystemDate());
			companyCustomer.setCreator(WebUtils.getCurrUserId());
			companyCustomer.setStatus(CompanyCustomerStatus.ORDER.getValue());
			this.companyCustomerService.add(companyCustomer);
			customOrder.setCustomerId(companyCustomer.getId());
		} else {
			customOrder.setCustomerId(customerByMap.get(0).getId());
		}
		customOrder.setFlag(0);
		this.customOrderService.add(customOrder);
		//如果支付记录不为空 则生成支付记录
		if (payment != null) {
			payment.setCustomOrderId(customOrder.getId());
			this.paymentService.add(payment);
		}
		//判断是否有定金,有定金 需要添加经销商收入流水·
		Integer earnest = customOrder.getEarnest();
		if(earnest>0){
			//生成充值记录
			Payment payment1 = new Payment();
			payment1.setWay(PaymentWay.CASH.getValue());
			payment1.setFunds(PaymentFunds.EARNEST_MONEY.getValue());
			payment1.setType(PaymentTypeNew.INCOME.getValue());
			payment1.setCreated(DateUtil.getSystemDate());
			payment1.setCreator(WebUtils.getCurrUserId());
			payment1.setStatus(1);
			payment1.setCustomOrderId(customOrder.getId());
			payment1.setCompanyId(customOrder.getCompanyId());
			payment1.setAmount(new BigDecimal(earnest));
			payment1.setBranchId(WebUtils.getCurrBranchId());
			payment1.setHolder(AppBeanInjector.userService.findByUserId(WebUtils.getCurrUserId()).getName());
			payment1.setAudited(DateUtil.getSystemDate());
			payment1.setAuditor(WebUtils.getCurrUserId());
			payment1.setNo(AppBeanInjector.uniquneCodeGenerator.getNextNo(UniquneCodeGenerator.UniqueResource.PAYMENT_NO));
			payment1.setFlag(0);
			payment1.setPayAmount(new BigDecimal(earnest));
			paymentService.add(payment1);
			}
		//订单产品
		List<OrderProductDto> orderProducts = customOrderInfoDto.getOrderProducts();
		//查询订单编号前缀
		Basecode byTypeAndCode = AppBeanInjector.basecodeService.findByTypeAndCode("orderProductType", customOrder.getOrderProductType().toString());
		noValue =  byTypeAndCode.getRemark() + noValue;
		saveOrderProducts3(orderProducts, noValue, customOrder.getId());
        // 判断是否启用催款功能
        Branch branch = this.branchService.findById(WebUtils.getCurrBranchId());
        boolean remindFlag = false;
        if (branch != null && branch.getEnableRemind() != null && branch.getEnableRemind().equals(WebConstant.ENABLE)) {
            remindFlag = true;
        }

		//支付记录信息生成,如果是提交，则生成财务信息
		if (customOrder.getStatus() == OrderStatus.TO_PAID.getValue()) {
			//判断是否存在订单货款支付记录,如果存在，则删除记录
			MapContext params = MapContext.newOne();
			params.put("orderId", customOrder.getId());
			params.put("funds", PaymentFunds.ORDER_FEE_CHARGE.getValue());
			PaymentDto paymentDto = this.paymentService.findByOrderIdAndFunds(params);
			if(paymentDto!=null){
				this.paymentService.deleteById(paymentDto.getId());
			}
			if (remindFlag == false) {
				if(customOrder.getPaymentWithholding()==1){//代扣款
					//新建经销商订单货款信息记录，金额为0
					payment = new Payment();
					String userId = WebUtils.getCurrUserId();
					String userName = AppBeanInjector.userService.findByUserId(userId).getName();
					payment.setHolder(userName);
					payment.setNo(AppBeanInjector.uniquneCodeGenerator.getNextNo(UniquneCodeGenerator.UniqueResource.PAYMENT_NO));
					payment.setAmount(new BigDecimal("0"));
					payment.setCompanyId(customOrder.getCompanyId());
					payment.setStatus(PaymentStatus.PENDING_PAYMENT.getValue());
					payment.setCreated(DateUtil.getSystemDate());
					payment.setCreator(WebUtils.getCurrUserId());
					payment.setFunds(PaymentFunds.ORDER_FEE_CHARGE.getValue());
					payment.setWay(PaymentWay.DEALER_ACCOUNT.getValue());
					payment.setType(PaymentTypeNew.CHARGEBACK.getValue());
					payment.setFlag(0);
					if(customOrder.getEarnest()!=null&&!customOrder.getEarnest().equals("")){
						payment.setPayAmount(new BigDecimal(customOrder.getEarnest()));
					}
					//payment.setPayee("4j1u3r1efshq");
					payment.setCustomOrderId(customOrder.getId());
					payment.setBranchId(WebUtils.getCurrUser().getBranchId());
					payment.setResourceType(PaymentResourceType.ORDER.getValue());
					this.paymentService.add(payment);

					//新建代扣款经销商货款信息记录
					payment = new Payment();
					payment.setHolder(userName);
					payment.setNo(AppBeanInjector.uniquneCodeGenerator.getNextNo(UniquneCodeGenerator.UniqueResource.PAYMENT_NO));
					payment.setAmount(customOrder.getFactoryFinalPrice());
					payment.setCompanyId(customOrder.getWithholdingCompanyId());
					payment.setStatus(PaymentStatus.PENDING_PAYMENT.getValue());
					payment.setCreated(DateUtil.getSystemDate());
					payment.setCreator(WebUtils.getCurrUserId());
					payment.setFunds(PaymentFunds.PAYMENT_WITHHOLDING_CHARGE.getValue());
					payment.setWay(PaymentWay.DEALER_ACCOUNT.getValue());
					payment.setType(PaymentTypeNew.CHARGEBACK.getValue());
					payment.setFlag(0);
					if(customOrder.getEarnest()!=null&&!customOrder.getEarnest().equals("")){
						payment.setPayAmount(new BigDecimal(customOrder.getEarnest()));
					}
					//payment.setPayee("4j1u3r1efshq");
					payment.setCustomOrderId(customOrder.getId());
					payment.setBranchId(WebUtils.getCurrUser().getBranchId());
					payment.setResourceType(PaymentResourceType.ORDER.getValue());
					this.paymentService.add(payment);

				}else {
					//新建订单货款信息记录
					payment = new Payment();
					String userId = WebUtils.getCurrUserId();
					String userName = AppBeanInjector.userService.findByUserId(userId).getName();
					payment.setHolder(userName);
					payment.setNo(AppBeanInjector.uniquneCodeGenerator.getNextNo(UniquneCodeGenerator.UniqueResource.PAYMENT_NO));
					payment.setAmount(customOrder.getFactoryFinalPrice());
					payment.setCompanyId(customOrder.getCompanyId());
					payment.setStatus(PaymentStatus.PENDING_PAYMENT.getValue());
					payment.setCreated(DateUtil.getSystemDate());
					payment.setCreator(WebUtils.getCurrUserId());
					payment.setFunds(PaymentFunds.ORDER_FEE_CHARGE.getValue());
					payment.setWay(PaymentWay.DEALER_ACCOUNT.getValue());
					payment.setType(PaymentTypeNew.CHARGEBACK.getValue());
					payment.setFlag(0);
					if(customOrder.getEarnest()!=null&&!customOrder.getEarnest().equals("")){
						payment.setPayAmount(new BigDecimal(customOrder.getEarnest()));
					}
					//payment.setPayee("4j1u3r1efshq");
					payment.setCustomOrderId(customOrder.getId());
					payment.setBranchId(WebUtils.getCurrUser().getBranchId());
					payment.setResourceType(PaymentResourceType.ORDER.getValue());
					this.paymentService.add(payment);
				}
            } else {
                this.customOrderRemindFacade.createRemindOrder(customOrder); // 生成催款单
            }

			//记录操作日志
			CustomOrderLog log = new CustomOrderLog();
			log.setCreated(new Date());
			log.setCreator(WebUtils.getCurrUserId());
			log.setName("下单");
			log.setStage(OrderStage.PLACE_AN_ORDER.getValue());
			log.setContent("订单已下单，订单号：" + customOrder.getNo());
			log.setCustomOrderId(customOrder.getId());
			customOrderLogService.add(log);
		} else {
			//记录操作日志
			CustomOrderLog log = new CustomOrderLog();
			log.setCreated(new Date());
			log.setCreator(WebUtils.getCurrUserId());
			log.setName("接单");
			log.setStage(OrderStage.RECEIPT.getValue());
			log.setContent("订单已接单，订单号：" + customOrder.getNo());
			log.setCustomOrderId(customOrder.getId());
			customOrderLogService.add(log);
		}

		//添加附件
		String orderFiles = customOrderInfoDto.getOrderFiles();
		String[] ids = orderFiles.split(",");
		for (String id : ids) {
			MapContext map = MapContext.newOne();
			map.put("id", id);
			map.put("customOrderId", customOrder.getId());
			map.put("status", 1);
			this.customOrderFilesService.updateByMapContext(map);
		}
		return ResultFactory.generateRequestResult(this.customOrderService.findByOrderId(customOrder.getId()));
	}

	private void saveOrderProducts(List<OrderProductDto> orderProducts, String noVule, String orderId) {
		int prodCount = 1;
		if (orderProducts != null && orderProducts.size() > 0) {
			boolean updateOrderNo = true;
			for (OrderProductDto orderProductDto : orderProducts) {
				orderProductDto.setPartStock(0);
				orderProductDto.setCustomOrderId(orderId);
				orderProductDto.setChange(0);
				orderProductDto.setCreated(DateUtil.getSystemDate());
				orderProductDto.setCreator(WebUtils.getCurrUserId());
				orderProductDto.setNo(noVule);
				orderProductDto.setFlag(0);
				orderProductDto.setAftersaleNum(0);
//				if (ishaveId) {//有源订单
//					orderProductDto.setNo(noVule);
//				} else {//无源订单
//					if (orderProducts.size() == 1) {
//						if (orderProductDto.getType().equals(OrderProductType.CUPBOARD.getValue())) {
//							orderProductDto.setNo(noVule);
//						} else {
//							orderProductDto.setNo(noVule + "-" + String.format("%02d", prodCount));
//						}
//					} else {
//						orderProductDto.setNo(noVule + "-" + String.format("%02d", prodCount));
//					}
//				}
//				prodCount++;
				this.orderProductService.add(orderProductDto);
				if (orderProductDto.getFileIds() != null && orderProductDto.getFileIds().size() != 0) {
					MapContext updateFile = new MapContext();
					updateFile.put(WebConstant.KEY_ENTITY_ID, orderProductDto.getFileIds());
					updateFile.put("belongId", orderProductDto.getId());
					updateFile.put(WebConstant.KEY_ENTITY_STATUS, CustomOrderFilesStatus.FORMAL.getValue());
					updateFile.put("customOrderId", orderId);
					this.customOrderFilesService.updateByIds(updateFile);
				}
				//添加产品属性
				List<OrderProductAttribute> productAttributeValues = orderProductDto.getProductAttributeValues();
				if(productAttributeValues!=null&&productAttributeValues.size()>0){
					for(OrderProductAttribute attributeValue:productAttributeValues) {
						//查询属性值是否存在
						String keyId=attributeValue.getProductAttributeKeyId();
						String value=attributeValue.getValueName();
						MapContext map0=MapContext.newOne();
						map0.put("productAttributeKeyId",keyId);
						map0.put("attributeValue",value);
						map0.put("delFlag",0);
						ProductAttributeValue newone=this.productAttributeValueService.findByKeyIdAndValue(map0);
						if(newone==null){//不存在，则新建
							ProductAttributeValue productAttributeValue=new ProductAttributeValue();
							productAttributeValue.setProductAttributeKeyId(keyId);
							productAttributeValue.setStatus(1);
							productAttributeValue.setDelFlag(0);
							productAttributeValue.setAttributeValue(attributeValue.getValueName());
							productAttributeValue.setCreator(WebUtils.getCurrUserId());
							productAttributeValue.setCreated(new Date());
							productAttributeValue.setKeyName(attributeValue.getKeyName());
							this.productAttributeValueService.add(productAttributeValue);

							attributeValue.setProductAttributeValueId(productAttributeValue.getId());
						}else {
							attributeValue.setProductAttributeValueId(newone.getId());
						}
						attributeValue.setOrderProductId(orderProductDto.getId());
						this.orderProductAttributeService.add(attributeValue);
					}
				}
				//添加产品部件信息
				String productParts = orderProductDto.getProductParts();
				if (productParts != null && !productParts.equals("")) {
					String[] partsIds = productParts.split(",");
					Boolean isCoordination = false;
					String type = "produceOrderType";
//					String productNo = orderProductDto.getNo();
					for (String id : partsIds) {
						OrderProductParts parts = new OrderProductParts();
						parts.setOrderProductId(orderProductDto.getId());
						parts.setProductPartsId(id);
						this.orderProductPartsService.add(parts);
						ProductParts parts1 = this.productPartsService.findById(id);
						if (parts1 != null) {
							if (parts1.getProduceType() == ProduceOrderWay.COORDINATION.getValue()) {
								isCoordination = true;
							}
						}
						String code = parts1.getPartsType().toString();
//						productNo = productNo + this.basecodeService.findByTypeAndCode(type, code).getRemark();

					}
					if (isCoordination) {
						MapContext updateOrder = new MapContext();
						updateOrder.put(WebConstant.KEY_ENTITY_ID, orderId);
						//updateOrder.put("no",productNo);
						updateOrder.put("coordination", CustomOrderCoordination.NEED_COORDINATION.getValue());
						this.customOrderService.updateByMapContext(updateOrder);
					}
//					MapContext mapContext = MapContext.newOne();
//					mapContext.put("id", orderProductDto.getId());
//					mapContext.put("no", productNo);
//					this.orderProductService.updateByMapContext(mapContext);
				}
			}
		}
	}

	private void saveOrderProducts2(List<OrderProductDto> orderProducts, String noVule, String orderId, Boolean ishaveId) {
		int prodCount = 1;
		if (orderProducts != null && orderProducts.size() > 0) {
			boolean updateOrderNo = true;
			for (OrderProductDto orderProductDto : orderProducts) {
				orderProductDto.setPartStock(0);
				orderProductDto.setCustomOrderId(orderId);
				orderProductDto.setChange(0);
				orderProductDto.setCreated(DateUtil.getSystemDate());
				orderProductDto.setCreator(WebUtils.getCurrUserId());
				orderProductDto.setAftersaleNum(0);
				orderProductDto.setFlag(0);
				if (ishaveId) {//有源订单
					orderProductDto.setNo(noVule);
				} else {//无源订单
					if (orderProducts.size() == 1) {
						if (orderProductDto.getType().equals(OrderProductType.CUPBOARD.getValue())) {
							orderProductDto.setNo(noVule);
						} else {
							orderProductDto.setNo(noVule + "-" + String.format("%02d", prodCount));
						}
					} else {
						orderProductDto.setNo(noVule + "-" + String.format("%02d", prodCount));
					}
				}
				prodCount++;
				this.orderProductService.add(orderProductDto);
				if (orderProductDto.getFileIds() != null && orderProductDto.getFileIds().size() != 0) {
					MapContext updateFile = new MapContext();
					updateFile.put(WebConstant.KEY_ENTITY_ID, orderProductDto.getFileIds());
					updateFile.put("belongId", orderProductDto.getId());
					updateFile.put(WebConstant.KEY_ENTITY_STATUS, CustomOrderFilesStatus.FORMAL.getValue());
					updateFile.put("customOrderId", orderId);
					this.customOrderFilesService.updateByIds(updateFile);
				}
//添加产品属性
				List<OrderProductAttribute> productAttributeValues = orderProductDto.getProductAttributeValues();
				if(productAttributeValues!=null&&productAttributeValues.size()>0){
					for(OrderProductAttribute attributeValue:productAttributeValues) {
						//查询属性值是否存在
						String keyId=attributeValue.getProductAttributeKeyId();
						String value=attributeValue.getValueName();
						MapContext map0=MapContext.newOne();
						map0.put("productAttributeKeyId",keyId);
						map0.put("attributeValue",value);
						map0.put("delFlag",0);
						ProductAttributeValue newone=this.productAttributeValueService.findByKeyIdAndValue(map0);
						if(newone==null){//不存在，则新建
							ProductAttributeValue productAttributeValue=new ProductAttributeValue();
							productAttributeValue.setProductAttributeKeyId(keyId);
							productAttributeValue.setStatus(1);
							productAttributeValue.setDelFlag(0);
							productAttributeValue.setAttributeValue(attributeValue.getValueName());
							productAttributeValue.setCreator(WebUtils.getCurrUserId());
							productAttributeValue.setCreated(new Date());
							productAttributeValue.setKeyName(attributeValue.getKeyName());
							this.productAttributeValueService.add(productAttributeValue);

							attributeValue.setProductAttributeValueId(productAttributeValue.getId());
						}else {
							attributeValue.setProductAttributeValueId(newone.getId());
						}
						attributeValue.setOrderProductId(orderProductDto.getId());
						this.orderProductAttributeService.add(attributeValue);
					}
				}
				//添加产品部件信息
				String productParts = orderProductDto.getProductParts();
				if (productParts != null && !productParts.equals("")) {
					String[] partsIds = productParts.split(",");
					Boolean isCoordination = false;
					String type = "produceOrderType";
					String productNo = orderProductDto.getNo();
					for (String id : partsIds) {
						OrderProductParts parts = new OrderProductParts();
						parts.setOrderProductId(orderProductDto.getId());
						parts.setProductPartsId(id);
						this.orderProductPartsService.add(parts);
						ProductParts parts1 = this.productPartsService.findById(id);
						if (parts1 != null) {
							if (parts1.getProduceType() == ProduceOrderWay.COORDINATION.getValue()) {
								isCoordination = true;
							}
						}
						String code = parts1.getPartsType().toString();
						productNo = productNo + this.basecodeService.findByTypeAndCode(type, code).getRemark();

					}
					if (isCoordination) {
						MapContext updateOrder = new MapContext();
						updateOrder.put(WebConstant.KEY_ENTITY_ID, orderId);
						updateOrder.put("coordination", CustomOrderCoordination.NEED_COORDINATION.getValue());
						this.customOrderService.updateByMapContext(updateOrder);
					}
					MapContext mapContext = MapContext.newOne();
					mapContext.put("id", orderProductDto.getId());
					mapContext.put("no", productNo);
					this.orderProductService.updateByMapContext(mapContext);
				}
			}
		}
	}

	private void saveOrderProducts3(List<OrderProductDto> orderProducts, String noVule, String orderId) {
		int prodCount = 1;
		if (orderProducts != null && orderProducts.size() > 0) {
			boolean updateOrderNo = true;
			for (OrderProductDto orderProductDto : orderProducts) {
				orderProductDto.setPartStock(0);
				orderProductDto.setCustomOrderId(orderId);
				orderProductDto.setChange(0);
				orderProductDto.setCreated(DateUtil.getSystemDate());
				orderProductDto.setCreator(WebUtils.getCurrUserId());
				orderProductDto.setAftersaleNum(0);
				orderProductDto.setFlag(0);
				if(orderProductDto.getBodyTec()==null ||"".equals(orderProductDto.getBodyTec())){
					orderProductDto.setBodyTec(null);
				}
				if (orderProducts.size() == 1) {
					if (orderProductDto.getType().equals(OrderProductType.CUPBOARD.getValue())) {
						orderProductDto.setNo(noVule);
					} else {
						orderProductDto.setNo(noVule + "-" + String.format("%02d", prodCount));
					}
				} else {
					orderProductDto.setNo(noVule + "-" + String.format("%02d", prodCount));
				}
				//orderProductDto.setNo(noVule);
				prodCount++;
				this.orderProductService.add(orderProductDto);
				if (orderProductDto.getFileIds() != null && orderProductDto.getFileIds().size() != 0) {
					MapContext updateFile = new MapContext();
					updateFile.put(WebConstant.KEY_ENTITY_ID, orderProductDto.getFileIds());
					updateFile.put("belongId", orderProductDto.getId());
					updateFile.put(WebConstant.KEY_ENTITY_STATUS, CustomOrderFilesStatus.FORMAL.getValue());
					updateFile.put("customOrderId", orderId);
					this.customOrderFilesService.updateByIds(updateFile);
				}
				//添加产品属性
				List<OrderProductAttribute> productAttributeValues = orderProductDto.getProductAttributeValues();
				if(productAttributeValues!=null&&productAttributeValues.size()>0){
					for(OrderProductAttribute attributeValue:productAttributeValues) {
						//查询属性值是否存在
						String keyId=attributeValue.getProductAttributeKeyId();
						String value=attributeValue.getValueName();
						MapContext map0=MapContext.newOne();
						map0.put("productAttributeKeyId",keyId);
						map0.put("attributeValue",value);
						map0.put("delFlag",0);
						ProductAttributeValue newone=this.productAttributeValueService.findByKeyIdAndValue(map0);
						if(newone==null){//不存在，则新建
							 ProductAttributeValue productAttributeValue=new ProductAttributeValue();
                           productAttributeValue.setProductAttributeKeyId(keyId);
                           productAttributeValue.setStatus(1);
                           productAttributeValue.setDelFlag(0);
                           productAttributeValue.setAttributeValue(attributeValue.getValueName());
                           productAttributeValue.setCreator(WebUtils.getCurrUserId());
                           productAttributeValue.setCreated(new Date());
                           productAttributeValue.setKeyName(attributeValue.getKeyName());
                           this.productAttributeValueService.add(productAttributeValue);

							attributeValue.setProductAttributeValueId(productAttributeValue.getId());
						}else {
							attributeValue.setProductAttributeValueId(newone.getId());
						}
						attributeValue.setOrderProductId(orderProductDto.getId());
						this.orderProductAttributeService.add(attributeValue);
					}
				}

				//添加产品部件信息
				String productParts = orderProductDto.getProductParts();
				if (productParts != null && !productParts.equals("")) {
					String[] partsIds = productParts.split(",");
					Boolean isCoordination = false;
					String type = "produceOrderType";
					String productNo = orderProductDto.getNo();
					for (String id : partsIds) {
						OrderProductParts parts = new OrderProductParts();
						parts.setOrderProductId(orderProductDto.getId());
						parts.setProductPartsId(id);
						this.orderProductPartsService.add(parts);
						ProductParts parts1 = this.productPartsService.findById(id);
						if (parts1 != null) {
							if (parts1.getProduceType() == ProduceOrderWay.COORDINATION.getValue()) {
								isCoordination = true;
							}
						}
						String code = parts1.getPartsType().toString();
						productNo = productNo + this.basecodeService.findByTypeAndCode(type, code).getRemark();

					}
					if (isCoordination) {
						MapContext updateOrder = new MapContext();
						updateOrder.put(WebConstant.KEY_ENTITY_ID, orderId);
						updateOrder.put("coordination", CustomOrderCoordination.NEED_COORDINATION.getValue());
						this.customOrderService.updateByMapContext(updateOrder);
					}
					MapContext mapContext = MapContext.newOne();
					mapContext.put("id", orderProductDto.getId());
					mapContext.put("no", productNo);
					this.orderProductService.updateByMapContext(mapContext);
				}
			}
		}
	}

	@Override
	@Transactional(value = "transactionManager")
	@SysOperationLog(detail = "修改订单", operationType = OperationType.UPDATE, operationMoudule = OperationMoudule.CUSTOM_ORDER)
	public RequestResult factoryUpdateOrder(String id, MapContext mapContext) {
        //判断订单是否存在
        CustomOrder customOrder = this.customOrderService.findByOrderId(id);
        if (customOrder == null) {
            return ResultFactory.generateResNotFoundResult();
        }
		int[] statusList={OrderStatus.TO_RECEIVE.getValue(),OrderStatus.TO_QUOTED.getValue(),OrderStatus.REMINDING.getValue(),OrderStatus.TO_PAID.getValue()};
		if(!ArrayUtils.contains(statusList,customOrder.getStatus())){
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_NOT_ALLOW_OPERATION_10020,"订单已审核，不允许编辑");
		}
	    // 判断是否启用催款功能
        Branch branch = this.branchService.findById(WebUtils.getCurrBranchId());
        boolean remindFlag = false;
        if (branch != null && branch.getEnableRemind() != null && branch.getEnableRemind().equals(WebConstant.ENABLE)) {
            remindFlag = true;
        }
        String companyId = customOrder.getCompanyId();
        Integer status = customOrder.getStatus();
        String productType = mapContext.getTypedValue("orderProductType", String.class);
        if (!productType.equals(customOrder.getOrderProductType().toString()) || (status == OrderStatus.TO_RECEIVE.getValue() && customOrder.getOrderSource() == 1&&(customOrder.getReceiver()==null||customOrder.getReceiver().equals("")))) {
            String no = null;
			//查询订单编号前缀
			Basecode byTypeAndCode = AppBeanInjector.basecodeService.findByTypeAndCode("orderProductType", productType);
			no =byTypeAndCode.getRemark() +  AppBeanInjector.uniquneCodeGenerator.getNoByTime(DateUtil.getSystemDate(), byTypeAndCode.getRemark());
            mapContext.put("no", no);
        }
        //判断客户是否存在
        MapContext filter1 = new MapContext();
        filter1.put(WebConstant.KEY_ENTITY_NAME, mapContext.getTypedValue("customerName", String.class));
        filter1.put(WebConstant.KEY_ENTITY_COMPANY_ID, mapContext.getTypedValue("companyId", String.class));
        List<CompanyCustomer> customerByMap = this.companyCustomerService.findCustomerByMap(filter1);
        String customerId = null;
        if (customerByMap == null || customerByMap.size() == 0) {
            CompanyCustomer companyCustomer = new CompanyCustomer();
            companyCustomer.setName(mapContext.getTypedValue("customerName", String.class));
            companyCustomer.setCompanyId(mapContext.getTypedValue("companyId", String.class));
            companyCustomer.setCreated(DateUtil.getSystemDate());
            companyCustomer.setCreator(WebUtils.getCurrUserId());
            companyCustomer.setStatus(CompanyCustomerStatus.ORDER.getValue());
            this.companyCustomerService.add(companyCustomer);
            customerId = companyCustomer.getId();
        } else {
            customerId = customerByMap.get(0).getId();
        }
        mapContext.put(WebConstant.KEY_ENTITY_ID, id);
        mapContext.put("customerId", customerId);
//		if(status==OrderStatus.TO_RECEIVE.getValue()){
//			mapContext.put("status",OrderStatus.TO_QUOTED.getValue());
//		}
		String statusValue=mapContext.getTypedValue("status", String.class);
        if (statusValue!=null&&statusValue.equals(OrderStatus.TO_QUOTED.getValue().toString())) {//接单
            mapContext.put("receiptTime", DateUtil.getSystemDate());
        }
        if (statusValue!=null&&statusValue.equals(OrderStatus.TO_PAID.getValue().toString())) {//下单
            if (remindFlag == false) {
                mapContext.put("commitTime", DateUtil.getSystemDate());
            }
        }
        if(status==OrderStatus.TO_RECEIVE.getValue()){
            if(customOrder.getReceiver()==null||customOrder.getReceiver().equals("")){
                mapContext.put("receiver",WebUtils.getCurrUserId());
            }
        }
        if(status==OrderStatus.TO_QUOTED.getValue()){
            if(customOrder.getPlaceOrder()==null||customOrder.getPlaceOrder().equals("")){
                mapContext.put("placeOrder",WebUtils.getCurrUserId());
            }
        }
		BigDecimal newEarnest=new BigDecimal(customOrder.getEarnest());
		//查询是否修改定金
        if(mapContext.containsKey("earnest")){
			Integer earnest = mapContext.getTypedValue("earnest", Integer.class);
			if(earnest!=customOrder.getEarnest()&&earnest>0){
				MapContext con=MapContext.newOne();
				con.put("orderId",id);
				con.put("funds",PaymentFunds.EARNEST_MONEY.getValue());
				PaymentDto byOrderIdAndFunds = this.paymentService.findOneByOrderIdAndFunds(con);
				if(byOrderIdAndFunds!=null){
					MapContext text=MapContext.newOne();
					text.put("id",byOrderIdAndFunds.getId());
					text.put("amount",earnest);
					if(mapContext.getTypedValue("paymentWithholding",Integer.class)==1){
						text.put("companyId",mapContext.getTypedValue("withholdingCompanyId", String.class));
					}else {
						text.put("companyId",mapContext.getTypedValue("companyId", String.class));
					}
					this.paymentService.updateByMapContext(text);
				}else {
					//生成充值记录
					Payment payment1 = new Payment();
					payment1.setWay(PaymentWay.CASH.getValue());
					payment1.setFunds(PaymentFunds.EARNEST_MONEY.getValue());
					payment1.setType(PaymentTypeNew.INCOME.getValue());
					payment1.setCreated(DateUtil.getSystemDate());
					payment1.setCreator(WebUtils.getCurrUserId());
					payment1.setStatus(1);
					payment1.setCustomOrderId(customOrder.getId());
					if(mapContext.getTypedValue("paymentWithholding",Integer.class)==1){
						payment1.setCompanyId(mapContext.getTypedValue("withholdingCompanyId", String.class));
					}else {
						payment1.setCompanyId(mapContext.getTypedValue("companyId", String.class));
					}
					payment1.setAmount(new BigDecimal(earnest));
					payment1.setBranchId(WebUtils.getCurrBranchId());
					payment1.setHolder(AppBeanInjector.userService.findByUserId(WebUtils.getCurrUserId()).getName());
					payment1.setAudited(DateUtil.getSystemDate());
					payment1.setAuditor(WebUtils.getCurrUserId());
					payment1.setNo(AppBeanInjector.uniquneCodeGenerator.getNextNo(UniquneCodeGenerator.UniqueResource.PAYMENT_NO));
					payment1.setFlag(0);
					payment1.setPayAmount(new BigDecimal(earnest));
					paymentService.add(payment1);
				}
			}
			newEarnest=new BigDecimal(earnest);
		}
        this.customOrderService.updateByMapContext(mapContext);
        if (customOrder.getStatus() == OrderStatus.TO_QUOTED.getValue() || status == OrderStatus.TO_RECEIVE.getValue()) {
            if (statusValue!=null&&statusValue.equals(OrderStatus.TO_PAID.getValue().toString())) {
                if (remindFlag == false) {
                    Payment payment = new Payment();
                    String userId = WebUtils.getCurrUserId();
                    String userName = AppBeanInjector.userService.findByUserId(userId).getName();
					if(mapContext.getTypedValue("paymentWithholding",Integer.class)==1){//代扣款
						//新建经销商订单货款信息记录，金额为0
						payment = new Payment();
						payment.setHolder(userName);
						payment.setNo(AppBeanInjector.uniquneCodeGenerator.getNextNo(UniquneCodeGenerator.UniqueResource.PAYMENT_NO));
						payment.setAmount(new BigDecimal("0"));
						payment.setCompanyId(mapContext.getTypedValue("companyId", String.class));
						payment.setStatus(PaymentStatus.PENDING_PAYMENT.getValue());
						payment.setCreated(DateUtil.getSystemDate());
						payment.setCreator(WebUtils.getCurrUserId());
						payment.setFunds(PaymentFunds.ORDER_FEE_CHARGE.getValue());
						payment.setWay(PaymentWay.DEALER_ACCOUNT.getValue());
						payment.setType(PaymentTypeNew.CHARGEBACK.getValue());
						payment.setFlag(0);
						payment.setCustomOrderId(customOrder.getId());
						payment.setBranchId(WebUtils.getCurrUser().getBranchId());
						payment.setResourceType(PaymentResourceType.ORDER.getValue());
						if(customOrder.getEarnest()!=null&&!customOrder.getEarnest().equals("")){
							payment.setPayAmount(newEarnest);
						}
						this.paymentService.add(payment);

						//新建代扣款经销商货款信息记录
						payment = new Payment();
						payment.setHolder(userName);
						payment.setNo(AppBeanInjector.uniquneCodeGenerator.getNextNo(UniquneCodeGenerator.UniqueResource.PAYMENT_NO));
						payment.setAmount(mapContext.getTypedValue("factoryFinalPrice", BigDecimal.class));
						payment.setCompanyId(mapContext.getTypedValue("withholdingCompanyId",String.class));
						payment.setStatus(PaymentStatus.PENDING_PAYMENT.getValue());
						payment.setCreated(DateUtil.getSystemDate());
						payment.setCreator(WebUtils.getCurrUserId());
						payment.setFunds(PaymentFunds.PAYMENT_WITHHOLDING_CHARGE.getValue());
						payment.setWay(PaymentWay.DEALER_ACCOUNT.getValue());
						payment.setType(PaymentTypeNew.CHARGEBACK.getValue());
						payment.setFlag(0);
						payment.setCustomOrderId(customOrder.getId());
						payment.setBranchId(WebUtils.getCurrUser().getBranchId());
						payment.setResourceType(PaymentResourceType.ORDER.getValue());
						if(customOrder.getEarnest()!=null&&!customOrder.getEarnest().equals("")){
							payment.setPayAmount(newEarnest);
						}
						this.paymentService.add(payment);

					}else {
						customOrder=this.customOrderService.findById(id);
						//新建订单货款信息记录
						payment = new Payment();
						payment.setHolder(userName);
						payment.setNo(AppBeanInjector.uniquneCodeGenerator.getNextNo(UniquneCodeGenerator.UniqueResource.PAYMENT_NO));
						payment.setAmount(mapContext.getTypedValue("factoryFinalPrice", BigDecimal.class));
						payment.setCompanyId(mapContext.getTypedValue("companyId", String.class));
						payment.setStatus(PaymentStatus.PENDING_PAYMENT.getValue());
						payment.setCreated(DateUtil.getSystemDate());
						payment.setCreator(WebUtils.getCurrUserId());
						payment.setFunds(PaymentFunds.ORDER_FEE_CHARGE.getValue());
						payment.setWay(PaymentWay.DEALER_ACCOUNT.getValue());
						payment.setType(PaymentTypeNew.CHARGEBACK.getValue());
						payment.setFlag(0);
						payment.setCustomOrderId(customOrder.getId());
						payment.setBranchId(WebUtils.getCurrUser().getBranchId());
						payment.setResourceType(PaymentResourceType.ORDER.getValue());
						if(customOrder.getEarnest()!=null&&!customOrder.getEarnest().equals("")){
							payment.setPayAmount(newEarnest);
						}
						this.paymentService.add(payment);
					}
                } else {
                    this.customOrderRemindFacade.createRemindOrder(customOrder); // 生成催款单
                }
                //记录操作日志
                customOrder = this.customOrderService.findById(id);
                CustomOrderLog log = new CustomOrderLog();
                log.setCreated(new Date());
                log.setCreator(WebUtils.getCurrUserId());
                log.setName("下单");
                log.setStage(OrderStage.PLACE_AN_ORDER.getValue());
                log.setContent("订单已下单，订单号：" + customOrder.getNo());
                log.setCustomOrderId(customOrder.getId());
                customOrderLogService.add(log);

            } else if (statusValue!=null&&statusValue.equals(OrderStatus.TO_QUOTED.getValue().toString())) {
                //记录操作日志
                customOrder = this.customOrderService.findById(id);
                CustomOrderLog log = new CustomOrderLog();
                log.setCreated(new Date());
                log.setCreator(WebUtils.getCurrUserId());
                log.setName("接单");
                log.setStage(OrderStage.RECEIPT.getValue());
                log.setContent("订单已接单，订单号：" + customOrder.getNo());
                log.setCustomOrderId(customOrder.getId());
                customOrderLogService.add(log);
            }
        } else if (customOrder.getStatus() == OrderStatus.TO_PAID.getValue()) {
			Integer paymentWithholding = customOrder.getPaymentWithholding();
			Integer paymentWithholdingValue= customOrder.getPaymentWithholding();
			if(mapContext.containsKey("paymentWithholding")) {
				 paymentWithholdingValue = mapContext.getTypedValue("paymentWithholding", Integer.class);
			}
			MapContext updatePayment=MapContext.newOne();
			if (!companyId.equals(mapContext.getTypedValue("companyId", String.class))) {
				updatePayment.put("companyId", mapContext.getTypedValue("companyId", String.class));
			}
			MapContext filter = new MapContext();
			filter.put("orderId", id);
			filter.put("funds", PaymentFunds.ORDER_FEE_CHARGE.getValue());
			PaymentDto paymentDto = this.paymentService.findByOrderIdAndFunds(filter);
			if(paymentWithholding!=paymentWithholdingValue){//如果修改是否代扣值
				if(paymentWithholdingValue==1){//原订单为非代扣订单
					String payCompanyId=mapContext.getTypedValue("withholdingCompanyId",String.class);
                    //修改原扣款记录的应付金额为0
					updatePayment.put("amount",new BigDecimal("0"));
					updatePayment.put("payAmount",newEarnest);
					updatePayment.put("id",paymentDto.getId());
					this.paymentService.updateByMapContext(updatePayment);
                    //新建代扣款经销商货款信息记录
					Payment payment = new Payment();
					payment.setHolder(paymentDto.getHolder());
					payment.setNo(AppBeanInjector.uniquneCodeGenerator.getNextNo(UniquneCodeGenerator.UniqueResource.PAYMENT_NO));
					payment.setAmount(mapContext.getTypedValue("factoryFinalPrice", BigDecimal.class));
					payment.setCompanyId(payCompanyId);
					payment.setStatus(PaymentStatus.PENDING_PAYMENT.getValue());
					payment.setCreated(DateUtil.getSystemDate());
					payment.setCreator(WebUtils.getCurrUserId());
					payment.setFunds(PaymentFunds.PAYMENT_WITHHOLDING_CHARGE.getValue());
					payment.setWay(PaymentWay.DEALER_ACCOUNT.getValue());
					payment.setType(PaymentTypeNew.CHARGEBACK.getValue());
					payment.setFlag(0);
					payment.setCustomOrderId(customOrder.getId());
					payment.setBranchId(WebUtils.getCurrUser().getBranchId());
					payment.setResourceType(PaymentResourceType.ORDER.getValue());
					if(customOrder.getEarnest()!=null&&!customOrder.getEarnest().equals("")){
						payment.setPayAmount(newEarnest);
					}
					this.paymentService.add(payment);
				}else {//原订单为代扣订单
					//删除代扣记录
					MapContext paramss=MapContext.newOne();
					paramss.put("orderId",id);
					paramss.put("funds",PaymentFunds.PAYMENT_WITHHOLDING_CHARGE.getValue());
					PaymentDto byOrderIdAndFunds = this.paymentService.findByOrderIdAndFunds(paramss);
					this.paymentService.deleteById(byOrderIdAndFunds.getId());
					//修改扣款记录的金额为实际订单金额
					updatePayment.put("amount",mapContext.getTypedValue("factoryFinalPrice", BigDecimal.class));
					updatePayment.put("id",paymentDto.getId());
					updatePayment.put("payAmount",newEarnest);
					this.paymentService.updateByMapContext(updatePayment);
				}
			}else {
				if(paymentWithholding!=null&&paymentWithholding==1){
					//查询代扣款记录信息
					MapContext paramss=MapContext.newOne();
					paramss.put("orderId",id);
					paramss.put("funds",PaymentFunds.PAYMENT_WITHHOLDING_CHARGE.getValue());
					PaymentDto byOrderIdAndFunds = this.paymentService.findByOrderIdAndFunds(paramss);
					this.paymentService.deleteById(byOrderIdAndFunds.getId());
					updatePayment=MapContext.newOne();
					if ((mapContext.getTypedValue("withholdingCompanyId", String.class))!=null) {
						updatePayment.put("withholdingCompanyId", mapContext.getTypedValue("withholdingCompanyId", String.class));
					}
					updatePayment.put(WebConstant.KEY_ENTITY_ID, byOrderIdAndFunds.getId());
					updatePayment.put("amount", mapContext.getTypedValue("factoryFinalPrice", BigDecimal.class));
					updatePayment.put("payAmount",newEarnest);
					this.paymentService.updateByMapContext(updatePayment);
					if (!companyId.equals(mapContext.getTypedValue("companyId", String.class))) {
						updatePayment=MapContext.newOne();
						updatePayment.put("companyId", mapContext.getTypedValue("companyId", String.class));
						updatePayment.put(WebConstant.KEY_ENTITY_ID, paymentDto.getId());
						this.paymentService.updateByMapContext(updatePayment);
					}
				}else {
					updatePayment.put(WebConstant.KEY_ENTITY_ID, paymentDto.getId());
					updatePayment.put("amount", mapContext.getTypedValue("factoryFinalPrice", BigDecimal.class));
					updatePayment.put("payAmount",newEarnest);
					this.paymentService.updateByMapContext(updatePayment);
				}
			}
        }
		//添加附件
		String addFileIds = mapContext.getTypedValue("addFileIds", String.class);
		if (addFileIds != null && !addFileIds.equals("")) {
			String[] ids = addFileIds.split(",");
			for (String fileid : ids) {
				MapContext mapContext1 = MapContext.newOne();
				mapContext1.put("id", fileid);
				mapContext1.put("customOrderId", id);
				mapContext1.put("status", 1);
				this.customOrderFilesService.updateByMapContext(mapContext1);
			}
		}
		//附件删除
		String deleteFileIds = mapContext.getTypedValue("deleteFileIds", String.class);
		if (deleteFileIds != null && !deleteFileIds.equals("")) {
			String[] ids = deleteFileIds.split(",");
			for (String fileid : ids) {
				this.customOrderFilesService.deleteById(fileid);
			}
		}
		return ResultFactory.generateRequestResult(this.customOrderService.findByOrderId(id));
	}


	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addProduceOrder(String id, ProduceOrderDto produceOrder, List<String> fileIds) {
		CustomOrder customOrder = this.customOrderService.findById(id);
		if (customOrder == null) {
			return ResultFactory.generateResNotFoundResult();
		}
		produceOrder.setCustomOrderNo(customOrder.getNo());
		produceOrder.setCustomOrderId(customOrder.getId());
		String orderNo = customOrder.getNo();
		Basecode b = basecodeService.findByTypeAndCode("produceType", produceOrder.getType().toString());
		OrderProductDto prod = orderProductService.findOneById(produceOrder.getOrderProductId());
		orderNo = prod.getNo() + "-" + b.getValue();
		produceOrder.setNo(orderNo);
		produceOrder.setCreated(DateUtil.getSystemDate());
		produceOrder.setCreator(WebUtils.getCurrUserId());
		produceOrder.setBranchId(WebUtils.getCurrBranchId());
		produceOrder.setResourceType(PaymentResourceType.ORDER.getValue());
		MapContext orderMapContext = new MapContext();
		//如果是五金
		if (produceOrder.getType().equals(ProduceOrderType.HARDWARE.getValue())) {
			produceOrder.setState(ProduceOrderState.COMPLETE.getValue());
			produceOrder.setWay(ProduceOrderWay.SELF_PRODUCED.getValue());
			//判断订单状态是否已付款
			if (customOrder.getStatus() > OrderStatus.TO_PAID.getValue()) {
				produceOrder.setPay(ProduceOrderPay.PAY.getValue());
			} else {
				produceOrder.setPay(ProduceOrderPay.NOT_PAY.getValue());
			}
			//判断是否存在自产生产单 存在则以自产生产单为主
			List<ProduceOrder> ways = this.produceOrderService.findListByOrderIdAndTypesAndWays(id, null, Arrays.asList(ProduceOrderWay.SELF_PRODUCED.getValue()));
			if (ways == null || ways.size() == 0) {
				if (customOrder.getStatus() == OrderStatus.PRODUCTION.getValue() || customOrder.getStatus().equals(OrderStatus.TO_PRODUCED.getValue())) {
					orderMapContext.put(WebConstant.KEY_ENTITY_STATUS, OrderStatus.TO_PACKAGED.getValue());
				}
			}
		} else if (produceOrder.getType().equals(ProduceOrderType.CABINET_BODY.getValue())) {//如果是柜体
			produceOrder.setState(ProduceOrderState.NOT_YET_BEGUN.getValue());
			produceOrder.setWay(ProduceOrderWay.SELF_PRODUCED.getValue());
			//判断订单状态是否已付款
			if (customOrder.getStatus() > OrderStatus.TO_PAID.getValue()) {
				produceOrder.setPay(ProduceOrderPay.PAY.getValue());
			} else {
				produceOrder.setPay(ProduceOrderPay.NOT_PAY.getValue());
			}
		} else {//门板
			if (produceOrder.getWay().equals(ProduceOrderWay.SELF_PRODUCED.getValue())) {//如果是自产
				produceOrder.setState(ProduceOrderState.NOT_YET_BEGUN.getValue());
				//判断订单状态是否已付款
				if (customOrder.getStatus() > OrderStatus.TO_PAID.getValue()) {
					produceOrder.setPay(ProduceOrderPay.PAY.getValue());
				} else {
					produceOrder.setPay(ProduceOrderPay.NOT_PAY.getValue());
				}
				produceOrder.setPermit(ProduceOrderPermit.NOT_ALLOW.getValue());
			} else if (produceOrder.getWay().equals(ProduceOrderWay.SPECIAL.getValue())) {//如果是特供实木
				produceOrder.setState(ProduceOrderState.COMPLETE.getValue());
				//判断订单状态是否已付款
				if (customOrder.getStatus() > OrderStatus.TO_PAID.getValue()) {
					produceOrder.setPay(ProduceOrderPay.PAY.getValue());
				} else {
					produceOrder.setPay(ProduceOrderPay.NOT_PAY.getValue());
				}
				//判断是否存在自产生产单 存在则以自产生产单为主
				List<ProduceOrder> ways = this.produceOrderService.findListByOrderIdAndTypesAndWays(id, null, Arrays.asList(ProduceOrderWay.SELF_PRODUCED.getValue()));
				if (ways == null || ways.size() == 0) {
					if (customOrder.getStatus() == OrderStatus.PRODUCTION.getValue() || customOrder.getStatus().equals(OrderStatus.TO_PRODUCED.getValue())) {
						orderMapContext.put(WebConstant.KEY_ENTITY_STATUS, OrderStatus.TO_PACKAGED.getValue());
					}
				}
			}
		}
		RequestResult result = produceOrder.validateFields();
		if (result != null) {
			return result;
		}
		this.produceOrderService.add(produceOrder);
		if (orderMapContext.size() > 0) {
			orderMapContext.put(WebConstant.KEY_ENTITY_ID, id);
			this.customOrderService.updateByMapContext(orderMapContext);
		}
		//修改图片资源
		if (fileIds.size() != 0) {
			MapContext mapContext = new MapContext();
			mapContext.put(WebConstant.KEY_ENTITY_ID, fileIds);
			mapContext.put("belongId", produceOrder.getId());
			mapContext.put(WebConstant.KEY_ENTITY_STATUS, CustomOrderFilesStatus.FORMAL.getValue());
			this.customOrderFilesService.updateByIds(mapContext);
		}
		//记录操作日志
		CustomOrderLog log = new CustomOrderLog();
		log.setCreated(new Date());
		log.setCreator(WebUtils.getCurrUserId());
		log.setName("生产");
		log.setStage(OrderStage.PRODUCTION.getValue());
		log.setContent("订单号：" + customOrder.getNo() + " 中的产品:" + OrderProductType.getByValue(produceOrder.getType()).getName() + "开始生产");
		log.setCustomOrderId(customOrder.getId());
		customOrderLogService.add(log);
		return ResultFactory.generateRequestResult(this.produceOrderService.findOneById(produceOrder.getId()));
	}

	/**
	 * 创建外协单
	 *
	 * @param id
	 * @param produceOrder
	 * @param fileIds
	 * @return
	 */
	@Override
	@SysOperationLog(detail = "新建外协生产单", operationType = OperationType.INSERT, operationMoudule = OperationMoudule.CORPORATE_ORDER)
	@Transactional(value = "transactionManager")
	public RequestResult addCorporateProduceOrder(String id, ProduceOrderDto produceOrder, List<String> fileIds) {
		CustomOrder customOrder = this.customOrderService.findById(id);
		if (customOrder == null) {
			return ResultFactory.generateResNotFoundResult();
		}
		String orderNo = customOrder.getNo();
		Basecode b = basecodeService.findByTypeAndCode("produceType", produceOrder.getType().toString());
		OrderProductDto prod = orderProductService.findOneById(produceOrder.getOrderProductId());
		orderNo = prod.getNo() + "-" + b.getValue();
		produceOrder.setCustomOrderNo(customOrder.getNo());
		produceOrder.setNo(orderNo);
		produceOrder.setCustomOrderId(customOrder.getId());
		produceOrder.setCreated(DateUtil.getSystemDate());
		produceOrder.setCreator(WebUtils.getCurrUserId());
		produceOrder.setBranchId(WebUtils.getCurrBranchId());
		produceOrder.setResourceType(PaymentResourceType.ORDER.getValue());
		MapContext orderMapContext = new MapContext();
		produceOrder.setState(ProduceOrderState.NOT_YET_BEGUN.getValue());
		produceOrder.setPay(ProduceOrderPay.NOT_PAY.getValue());
		RequestResult result = produceOrder.validateFields();
		if (result != null) {
			return result;
		}
		this.produceOrderService.add(produceOrder);
		orderMapContext.put("coordination", CustomOrderCoordination.NEED_COORDINATION.getValue());
		if (orderMapContext.size() > 0) {
			orderMapContext.put(WebConstant.KEY_ENTITY_ID, id);
			this.customOrderService.updateByMapContext(orderMapContext);
		}
		//修改图片资源
		if (fileIds.size() != 0) {
			MapContext mapContext = new MapContext();
			mapContext.put(WebConstant.KEY_ENTITY_ID, fileIds);
			mapContext.put("belongId", produceOrder.getId());
			mapContext.put(WebConstant.KEY_ENTITY_STATUS, CustomOrderFilesStatus.FORMAL.getValue());
			this.customOrderFilesService.updateByIds(mapContext);
		}
		//记录操作日志
		CustomOrderLog log = new CustomOrderLog();
		log.setCreated(new Date());
		log.setCreator(WebUtils.getCurrUserId());
		log.setName("生产");
		log.setStage(OrderStage.PRODUCTION.getValue());
		log.setContent("订单号：" + customOrder.getNo() + " 中的外协产品:" + OrderProductType.getByValue(produceOrder.getType()).getName() + "已下单");
		log.setCustomOrderId(customOrder.getId());
		customOrderLogService.add(log);
		return ResultFactory.generateRequestResult(this.produceOrderService.findOneById(produceOrder.getId()));
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addOrderProduct(String id, OrderProductDto orderProduct) {
		//判断订单是否存在
		CustomOrder customOrder = this.customOrderService.findById(id);
		if (customOrder == null) {
			return ResultFactory.generateResNotFoundResult();
		}
		//获取订单编号头一位 判断是否是数字
		int num = customOrder.getNo().charAt(0);
		if (num > 47 && num < 58) {//是的话表示当前订单使用的编号为临时编号需要修改
			MapContext updateOrder = new MapContext();
			updateOrder.put(WebConstant.KEY_ENTITY_ID, id);
			if (orderProduct.getType().equals(OrderProductType.WARDROBE.getValue())) {
				updateOrder.put(WebConstant.STRING_NO, "B" + customOrder.getNo());
			} else if (orderProduct.getType().equals(OrderProductType.SAMPLE_PIECE.getValue())) {
				updateOrder.put(WebConstant.STRING_NO, "Y" + customOrder.getNo());
			} else {
				updateOrder.put(WebConstant.STRING_NO, "J" + customOrder.getNo());
			}
			this.customOrderService.updateByMapContext(updateOrder);
		}
		orderProduct.setAftersaleNum(0);
		orderProduct.setFlag(0);
		this.orderProductService.add(orderProduct);
		if (orderProduct.getFileIds().size() != 0) {
			MapContext updateFile = new MapContext();
			updateFile.put(WebConstant.KEY_ENTITY_ID, orderProduct.getFileIds());
			updateFile.put("belongId", orderProduct.getId());
			updateFile.put(WebConstant.KEY_ENTITY_STATUS, CustomOrderFilesStatus.FORMAL.getValue());
			this.customOrderFilesService.updateByIds(updateFile);
		}
		//判断是否存在订单报价信息
		OrderOffer byOrerId = this.orderOfferService.findByOrerId(id);
		if (byOrerId == null) {
			//获取订单产品总金额
			BigDecimal countPrice = this.orderProductService.findCountPriceByOrderId(id);
			//修改订单金额
			MapContext updateOrder = new MapContext();
			updateOrder.put(WebConstant.KEY_ENTITY_ID, id);
			updateOrder.put("factoryFinalPrice", countPrice);
			this.customOrderService.updateByMapContext(updateOrder);
			//判断订单是未付款
			if (customOrder.getStatus() < OrderStatus.TO_PRODUCED.getValue()) {
				MapContext filter = new MapContext();
				filter.put("orderId", id);
				filter.put("funds", PaymentFunds.ORDER_FEE_CHARGE.getValue());
				PaymentDto paymentDto = this.paymentService.findByOrderIdAndFunds(filter);
				MapContext updatePayment = new MapContext();
				updatePayment.put(WebConstant.KEY_ENTITY_ID, paymentDto.getId());
				updatePayment.put("amount", countPrice);
				this.paymentService.updateByMapContext(updatePayment);
			}
		}
		//判断产品的 门板颜色 和 门型 和 柜体颜色 是否存在 不存在 则新增
		Basecode doorColor = this.basecodeService.findByTypeAndValue("orderProductDoorColor", orderProduct.getDoorColor());
		if (doorColor == null) {
			List<Basecode> basecodeList = this.basecodeService.findByType("orderProductDoorColor");
			Basecode basecode = new Basecode();
			basecode.setValue(orderProduct.getDoorColor());
			basecode.setType("orderProductDoorColor");
			basecode.setOrderNum(basecodeList.size());
			basecode.setName("订单产品门板颜色");
			basecode.setDelFlag(0);
			basecode.setCode(basecodeList.size() + "");
			this.basecodeService.add(basecode);
		}
		Basecode door = this.basecodeService.findByTypeAndValue("orderProductDoor", orderProduct.getDoor());
		if (door == null) {
			List<Basecode> basecodeList = this.basecodeService.findByType("orderProductDoor");
			Basecode basecode = new Basecode();
			basecode.setValue(orderProduct.getDoor());
			basecode.setType("orderProductDoor");
			basecode.setOrderNum(basecodeList.size());
			basecode.setName("订单产品门型");
			basecode.setDelFlag(0);
			basecode.setCode(basecodeList.size() + "");
			this.basecodeService.add(basecode);
		}
		Basecode bodyColor = this.basecodeService.findByTypeAndValue("orderProductBodyColor", orderProduct.getBodyColor());
		if (bodyColor == null) {
			List<Basecode> basecodeList = this.basecodeService.findByType("orderProductBodyColor");
			Basecode basecode = new Basecode();
			basecode.setValue(orderProduct.getBodyColor());
			basecode.setType("orderProductBodyColor");
			basecode.setOrderNum(basecodeList.size());
			basecode.setName("订单产品柜体颜色");
			basecode.setDelFlag(0);
			basecode.setCode(basecodeList.size() + "");
			this.basecodeService.add(basecode);
		}
		return ResultFactory.generateRequestResult(this.orderProductService.findOneById(orderProduct.getId()));
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updateOrderProduct(String id, List<MapContext> mapContexts) {
		int num = 0;//用来判断是否修改产品价格的标识
		for (MapContext mapContext : mapContexts) {
			//判断订单产品 以及订单是否存下
			String pId = mapContext.getTypedValue("productId", String.class);
			OrderProduct orderProduct = this.orderProductService.findById(pId);
			if (orderProduct == null || !orderProduct.getCustomOrderId().equals(id)) {
				return ResultFactory.generateResNotFoundResult();
			}
			mapContext.put(WebConstant.KEY_ENTITY_ID, pId);
			this.orderProductService.updateByMapContext(mapContext);
			BigDecimal amount = mapContext.getTypedValue("amount", BigDecimal.class);
			if (amount != null) {
				num++;
			}
		}
		if (num > 0) {//产品价格变动，如果订单报价信息为空，则修改订单价格，如果订单已报价，则不允许再修改订单价格
			//判断是否存在订单报价信息
			OrderOffer byOrerId = this.orderOfferService.findByOrerId(id);
			if (byOrerId == null) {
				//获取订单产品总金额
				BigDecimal countPrice = this.orderProductService.findCountPriceByOrderId(id);
				//修改订单金额
				MapContext updateOrder = new MapContext();
				updateOrder.put(WebConstant.KEY_ENTITY_ID, id);
				updateOrder.put("factoryFinalPrice", countPrice);
				this.customOrderService.updateByMapContext(updateOrder);
				//判断订单是未付款
				CustomOrder customOrder = this.customOrderService.findById(id);
				if (customOrder.getStatus() < OrderStatus.TO_PRODUCED.getValue()) {
					MapContext filter = new MapContext();
					filter.put("orderId", id);
					filter.put("funds", PaymentFunds.ORDER_FEE_CHARGE.getValue());
					PaymentDto paymentDto = this.paymentService.findByOrderIdAndFunds(filter);
					MapContext updatePayment = new MapContext();
					updatePayment.put(WebConstant.KEY_ENTITY_ID, paymentDto.getId());
					updatePayment.put("amount", countPrice);
					this.paymentService.updateByMapContext(updatePayment);
				}
			}
		}
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult deleteOrderProduct(String id, String pId) {
		//判断订单产品 以及订单是否存
		OrderProduct orderProduct = this.orderProductService.findById(pId);
		if (orderProduct == null || !orderProduct.getCustomOrderId().equals(id)) {
			return ResultFactory.generateSuccessResult();
		}
		CustomOrder customOrderServiceById = this.customOrderService.findById(id);
		//如果订单已付款 则不允许删除
		if (customOrderServiceById.getStatus() > OrderStatus.TO_PAID.getValue()) {
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_NOT_ALLOW_OPERATION_10020, AppBeanInjector.i18nUtil.getMessage("BIZ_NOT_ALLOW_OPERATION_10020"));
		}
		//删除产品下的生产单
		this.produceOrderService.deleteByProductId(pId);
		this.orderProductService.deleteById(pId);
		//判断是否存在订单报价信息
		OrderOffer byOrerId = this.orderOfferService.findByOrerId(id);
		if (byOrerId == null) {
			//获取订单产品总金额
			BigDecimal countPrice = this.orderProductService.findCountPriceByOrderId(id);
			//修改订单金额
			MapContext updateOrder = new MapContext();
			updateOrder.put(WebConstant.KEY_ENTITY_ID, id);
			updateOrder.put("factoryFinalPrice", countPrice);
			this.customOrderService.updateByMapContext(updateOrder);
			//判断订单是未付款
			CustomOrder customOrder = this.customOrderService.findById(id);
			if (customOrder.getStatus() < OrderStatus.TO_PRODUCED.getValue()) {
				MapContext filter = new MapContext();
				filter.put("orderId", id);
				filter.put("funds", PaymentFunds.ORDER_FEE_CHARGE.getValue());
				PaymentDto paymentDto = this.paymentService.findByOrderIdAndFunds(filter);
				MapContext updatePayment = new MapContext();
				updatePayment.put(WebConstant.KEY_ENTITY_ID, paymentDto.getId());
				updatePayment.put("amount", countPrice);
				this.paymentService.updateByMapContext(updatePayment);
			}
		}
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	@SysOperationLog(detail = "修改生产单", operationType = OperationType.UPDATE, operationMoudule = OperationMoudule.PRODUCE)
	public RequestResult updateProduceOrder(String id, String pId, MapContext mapContext) {
		//判断生产拆单是否存在 订单是否存在
		ProduceOrder produceOrder = this.produceOrderService.findById(pId);
		if (produceOrder == null || !produceOrder.getCustomOrderId().equals(id)) {
			return ResultFactory.generateResNotFoundResult();
		}
		//如果是外协单  则判断是否修改报价
		if (produceOrder.getWay().equals(ProduceOrderWay.COORDINATION.getValue())) {
			String coordinationName=mapContext.getTypedValue("coordinationName",String.class);
			if(coordinationName!=null&&!coordinationName.equals("")){
				mapContext.put(WebConstant.KEY_ENTITY_STATE, ProduceOrderState.TO_BE_QUOTED.getValue());
			}
			BigDecimal amount = mapContext.getTypedValue("amount", BigDecimal.class);
			if (amount != null && amount.doubleValue() >= 0) {
				mapContext.put(WebConstant.KEY_ENTITY_STATE, ProduceOrderState.NOT_YET_BEGUN.getValue());
				mapContext.put("actualTime", DateUtil.getSystemDate());
				mapContext.put("pay", 1);
			}
		}

		//如果是外协且为报价审核通过则生成一条财务待审核记录 6
		Integer state = Integer.valueOf((Integer) mapContext.get("state"));
		if ( ProduceOrderState.TO_BE_PAY.getValue() == state) {
			//生成财务单记录
			Payment payment = new Payment();
			String userId = WebUtils.getCurrUserId();
			String userName = AppBeanInjector.userService.findByUserId(userId).getName();
			payment.setHolder(userName);
			payment.setNo(AppBeanInjector.uniquneCodeGenerator.getNextNo(UniquneCodeGenerator.UniqueResource.PAYMENT_NO));
			payment.setAmount(produceOrder.getAmount());
			payment.setCompanyId(produceOrder.getBranchId());
			payment.setStatus(PaymentStatus.PENDING_PAYMENT.getValue());
			payment.setCreated(DateUtil.getSystemDate());
			payment.setCreator(WebUtils.getCurrUserId());
			payment.setFunds(PaymentFunds.COORDINATION.getValue());
			payment.setWay(PaymentWay.BANK.getValue());
			payment.setType(PaymentTypeNew.COOPERATE_PAY.getValue());
			payment.setCustomOrderId(produceOrder.getId());
			payment.setBranchId(WebUtils.getCurrUser().getBranchId());
			payment.setResourceType(PaymentResourceType.OUTSOURCE.getValue());
			payment.setFlag(0);
			this.paymentService.add(payment);
		}






		mapContext.put(WebConstant.KEY_ENTITY_ID, pId);
		this.produceOrderService.updateByMapContext(mapContext);
		return ResultFactory.generateRequestResult(this.produceOrderService.findOneById(pId));
	}


	@Override
	@Transactional(value = "transactionManager")
	@SysOperationLog(detail = "外协报价", operationType = OperationType.UPDATE, operationMoudule = OperationMoudule.PRODUCE)
	public RequestResult updateProduceOrderOutsource(String pId, MapContext mapContext) {
		//判断生产拆单是否存在 订单是否存在
		ProduceOrder produceOrder = this.produceOrderService.findById(pId);
		//如果是外协单  则判断是否修改报价
		if (produceOrder.getWay().equals(ProduceOrderWay.COORDINATION.getValue())) {
			BigDecimal amount = mapContext.getTypedValue("amount", BigDecimal.class);
			if (amount != null && amount.doubleValue() > 0) {
				mapContext.put(WebConstant.KEY_ENTITY_STATE, ProduceOrderState.TO_BE_PENDING_PRICE.getValue());//外协报价修改状态为工厂待审价
			}
		}
		mapContext.put(WebConstant.KEY_ENTITY_ID, pId);

		this.produceOrderService.updateByMapContext(mapContext);
		return ResultFactory.generateRequestResult(this.produceOrderService.findOneById(pId));
	}

	@Override
	@Transactional(value = "transactionManager")
	@SysOperationLog(detail = "删除生产单", operationType = OperationType.DELETE, operationMoudule = OperationMoudule.PRODUCE)
	public RequestResult deleteProduceOrder(String id, String pId) {
		//判断生产拆单是否存在 订单是否存在
		ProduceOrder produceOrder = this.produceOrderService.findById(pId);
		if (produceOrder == null || !produceOrder.getCustomOrderId().equals(id)) {
			return ResultFactory.generateSuccessResult();
		}
//		//删除生产单下的生产流程
//		this.produceFlowService.deleteByOrderId(id);
		//删除 生产单
		this.produceOrderService.deleteById(pId);
		//删除产品部件关联
		String productId = produceOrder.getOrderProductId();
		OrderProductDto oneById = this.orderProductService.findOneById(productId);
		Integer productType = oneById.getType();
		ProductParts parts = this.productPartsService.findByType(productType, produceOrder.getType(), produceOrder.getWay());
		if (parts != null) {
			OrderProductParts productParts = this.orderProductPartsService.findByProductIdAndPartsId(productId, parts.getId());
			if (productParts != null) {
				this.orderProductPartsService.deleteById(productParts.getId());
			}
		}
		return ResultFactory.generateSuccessResult();
	}

	@Override
	public RequestResult findProducesList(MapContext mapContext, Integer pageNum, Integer pageSize, List<Map<String, String>> sorts) {
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		mapContext.put("funds", PaymentFunds.ORDER_FEE_CHARGE.getValue());
		mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID, WebUtils.getCurrBranchId());
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
		Pagination pagination = new Pagination();
		pagination.setPageNum(pageNum);
		pagination.setPageSize(pageSize);
		paginatedFilter.setPagination(pagination);
		Map<String, String> created = new HashMap<String, String>();
		created.put(WebConstant.KEY_ENTITY_CREATED, "desc");
		sorts.add(created);
		Map<String, String> id = new HashMap<String, String>();
		id.put(WebConstant.KEY_ENTITY_ID, "desc");
		sorts.add(id);
		paginatedFilter.setSorts(sorts);
		return ResultFactory.generateRequestResult(this.produceOrderService.findListByFilter(paginatedFilter));
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addProduceFlow(String id, ProduceFlow produceFlow) {
		//判断生产单状态是否存在 是否是生产中
		ProduceOrder produceOrder = this.produceOrderService.findById(id);
		if (produceOrder == null || !produceOrder.getState().equals(ProduceOrderState.IN_PRODUCTION.getValue())) {
			return ResultFactory.generateResNotFoundResult();
		}
		//判断该节点是否已存在
		ProduceFlow existFlow = this.produceFlowService.findOneByProduceIdAndNode(id, produceFlow.getNode());
		if (existFlow != null) {
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_NOT_ALLOW_OPERATION_10020, AppBeanInjector.i18nUtil.getMessage("BIZ_NOT_ALLOW_OPERATION_10020"));
		}
		this.produceFlowService.add(produceFlow);
		return ResultFactory.generateRequestResult(this.produceFlowService.findOneById(produceFlow.getId()));
	}

	@Override
	@Transactional(value = "transactionManager")
	@SysOperationLog(detail = "新建包裹", operationType = OperationType.INSERT, operationMoudule = OperationMoudule.FINISHEDSTOCKITEM)
	public RequestResult addOrderPack(String id, List<FinishedStockItemDto> finishedStockItemDtoList, int type) {
		//判断资源是否存在
		CustomOrder order = null;
		AftersaleApply aftersaleApply = null;
		if (type == PaymentResourceType.ORDER.getValue()) {
			order = this.customOrderService.findById(id);
			if (order == null) {
				return ResultFactory.generateResNotFoundResult();
			}
		} else if (type == PaymentResourceType.AFTERSALEAPPLY.getValue()) {
			aftersaleApply = this.aftersaleApplyService.findById(id);
			if (aftersaleApply == null) {
				return ResultFactory.generateResNotFoundResult();
			}
		} else {
			return ResultFactory.generateResNotFoundResult();
		}
		//判断订单产品是否存在
		OrderProduct byId = this.orderProductService.findById(finishedStockItemDtoList.get(0).getOrderProductId());
		if (byId == null) {
			return ResultFactory.generateResNotFoundResult();
		}
		//判断是否存在与包裹类型相符的生产单
//		Integer itemType = finishedStockItemDtoList.get(0).getType();
//		if(itemType.equals(FinishedStockItemType.CABINET.getValue())){
//			if(this.produceOrderService.findListByOrderIdAndTypes(id,Arrays.asList(ProduceOrderType.CABINET_BODY.getValue())).size()==0){
//				return ResultFactory.generateErrorResult(ErrorCodes.BIZ_THIS_TYPE_OF_PRODUCE_ORDER_NOT_EXIST_10092,AppBeanInjector.i18nUtil.getMessage("BIZ_THIS_TYPE_OF_PRODUCE_ORDER_NOT_EXIST_10092"));
//			}
//		}else if(itemType.equals(FinishedStockItemType.DOOR_HOMEGROWN.getValue())||finishedStockItemDtoList.get(0).getType().equals(FinishedStockItemType.DOOR_OUTSOURCING.getValue())||finishedStockItemDtoList.get(0).getType().equals(FinishedStockItemType.SPECIAL_SUPPLY.getValue())){
//			if(this.produceOrderService.findListByOrderIdAndTypes(id,Arrays.asList(ProduceOrderType.DOOR_PLANK.getValue())).size()==0){
//				return ResultFactory.generateErrorResult(ErrorCodes.BIZ_THIS_TYPE_OF_PRODUCE_ORDER_NOT_EXIST_10092,AppBeanInjector.i18nUtil.getMessage("BIZ_THIS_TYPE_OF_PRODUCE_ORDER_NOT_EXIST_10092"));
//			}
//		}else if(itemType.equals(FinishedStockItemType.HARDWARE.getValue())){
//			if(this.produceOrderService.findListByOrderIdAndTypes(id,Arrays.asList(ProduceOrderType.HARDWARE.getValue())).size()==0){
//				return ResultFactory.generateErrorResult(ErrorCodes.BIZ_THIS_TYPE_OF_PRODUCE_ORDER_NOT_EXIST_10092,AppBeanInjector.i18nUtil.getMessage("BIZ_THIS_TYPE_OF_PRODUCE_ORDER_NOT_EXIST_10092"));
//			}
//		}else{
//			return ResultFactory.generateResNotFoundResult();
//		}
		FinishedStock oldFinishedStock = this.finishedStockService.findByOrderId(id);
		String finishedStockId;
		//判断成品库单是否存在
		if (oldFinishedStock == null) {
			FinishedStock finishedStock = new FinishedStock();
			finishedStock.setCreated(DateUtil.getSystemDate());
			finishedStock.setCreator(WebUtils.getCurrUserId());
			finishedStock.setOrderId(id);
			finishedStock.setPackages(finishedStockItemDtoList.size() + 1);
			if (type == PaymentResourceType.ORDER.getValue()) {
				finishedStock.setOrderNo(order.getNo());
			} else {
				finishedStock.setOrderNo(aftersaleApply.getNo());
			}
			finishedStock.setStatus(FinishedStockStatus.UNSHIPPED.getValue());
			//查询成品仓
			//Storage storage = this.storageService.findOneByProductCategoryKey(ProductCategoryKey.finished.getId(), WebUtils.getCurrBranchId());
			//finishedStock.setStorageId(storage.getId());
			finishedStock.setWay(FinishedStockWay.MANUAL.getValue());
			finishedStock.setBranchId(WebUtils.getCurrBranchId());
			finishedStock.setResourceType(type);
			RequestResult result = finishedStock.validateFields();
			if (result != null) {
				return result;
			}
			this.finishedStockService.add(finishedStock);
			finishedStockId = finishedStock.getId();
		} else {
			finishedStockId = oldFinishedStock.getId();
			MapContext mapContext = new MapContext();
			mapContext.put(WebConstant.KEY_ENTITY_ID, finishedStockId);
			//查询已经存在包裹数
			List<FinishedStockItemDto> listByFinishedStockId = this.finishedStockItemService.findListByFinishedStockId(finishedStockId);
			//如果已存在的包裹数 等于 成品库中 的包装数 则 代表着 已全部入库 再次入库时 存入真实包数
			if (listByFinishedStockId.size() == oldFinishedStock.getPackages()) {
				mapContext.put("packages", listByFinishedStockId.size() + finishedStockItemDtoList.size());
			} else {
				mapContext.put("packages", listByFinishedStockId.size() + finishedStockItemDtoList.size() + 1);
			}
			this.finishedStockService.updateByMapContext(mapContext);
		}
		for (FinishedStockItemDto finishedStockItem : finishedStockItemDtoList) {
			//判断包裹编号是否重复
			if (this.finishedStockItemService.findListByBarcodes(new HashSet(Arrays.asList(finishedStockItem.getBarcode()))).size() != 0) {
				Map res = new HashMap<String, String>();
				res.put("barcodes", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOT_ALLOWED_REPEAT"));
				return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, res);
			}
			finishedStockItem.setFinishedStockId(finishedStockId);
			this.finishedStockItemService.add(finishedStockItem);
			//修改图片资源
			if (finishedStockItem.getFileIds().size() != 0) {
				MapContext fileUpdate = new MapContext();
				fileUpdate.put(WebConstant.KEY_ENTITY_ID, finishedStockItem.getFileIds());
				fileUpdate.put("belongId", finishedStockItem.getId());
				fileUpdate.put(WebConstant.KEY_ENTITY_STATUS, CustomOrderFilesStatus.FORMAL.getValue());
				this.customOrderFilesService.updateByIds(fileUpdate);
			}
		}
		//修改订单状态 如果订单存在
		if (order != null && order.getStatus().equals(OrderStatus.TO_PACKAGED.getValue())) {
			MapContext orderUpdate = MapContext.newOne();
			orderUpdate.put(WebConstant.KEY_ENTITY_ID, order.getId());
			orderUpdate.put(WebConstant.KEY_ENTITY_STATUS, OrderStatus.TO_SHIPPED.getValue());
			this.customOrderService.updateByMapContext(orderUpdate);
		}
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult orderPacked(String id) {
		//判断订单是否存在
		CustomOrder order = this.customOrderService.findById(id);
		if (order == null) {
			return ResultFactory.generateResNotFoundResult();
		}
//		//判断是否存在未完成生产单
//		List<ProduceOrder> incompleteListByOrderId = this.produceOrderService.findIncompleteListByOrderId(id,Arrays.asList(ProduceOrderWay.SELF_PRODUCED.getValue(),ProduceOrderWay.COORDINATION.getValue()));
//		if (incompleteListByOrderId.size()!=0){
//			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_NOT_ALLOW_OPERATION_10020,AppBeanInjector.i18nUtil.getMessage("BIZ_NOT_ALLOW_OPERATION_10020"));
//		}
		//判断 成品库单 是否存在
		FinishedStock finishedStock = this.finishedStockService.findByOrderId(id);
		if (finishedStock == null) {
			return ResultFactory.generateResNotFoundResult();
		}
		//判断是否已确认打包完成
		List<FinishedStockItemDto> listByFinishedStockId = this.finishedStockItemService.findListByFinishedStockId(finishedStock.getId());
		if (finishedStock.getPackages() == listByFinishedStockId.size()) {
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_NOT_ALLOW_OPERATION_10020, AppBeanInjector.i18nUtil.getMessage("BIZ_NOT_ALLOW_OPERATION_10020"));
		}
		MapContext mapContext = new MapContext();
		mapContext.put(WebConstant.KEY_ENTITY_ID, finishedStock.getId());
		mapContext.put("packages", listByFinishedStockId.size());
		this.finishedStockService.updateByMapContext(mapContext);
		return ResultFactory.generateSuccessResult();
	}


	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updateProducesOrderPlanTime(List ids, Date planTime) {
		//安排生产时间即开始生产 进入生产中
		this.produceOrderService.updatePlanTimeByIds(planTime, ids);
		//订单进入生产中
		List<String> orderId = this.produceOrderService.findListOrderIdByPId(ids);
		for (String id : orderId) {
			CustomOrder customOrderServiceById = this.customOrderService.findById(id);
			//订单存在 则代表 为订单 生产单 不存在则代表为 售后单生产单
			if (customOrderServiceById != null) {
				if (customOrderServiceById.getStatus().equals(OrderStatus.TO_PRODUCED.getValue())) {
					MapContext updateOrder = new MapContext();
					updateOrder.put(WebConstant.KEY_ENTITY_ID, id);
					updateOrder.put(WebConstant.KEY_ENTITY_STATUS, OrderStatus.PRODUCTION.getValue());
					this.customOrderService.updateByMapContext(updateOrder);
				}
			}
		}
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult endCoordination(String id) {
		//判断生产单是否存在
		ProduceOrder produceOrder = this.produceOrderService.findById(id);
		// 生产单 是否存在 是否是生产中
		if (produceOrder == null || !produceOrder.getState().equals(ProduceOrderState.IN_PRODUCTION.getValue())) {
			return ResultFactory.generateResNotFoundResult();
		}
		//修改生产单状态为 已完成
		MapContext mapContext = new MapContext();
		mapContext.put(WebConstant.KEY_ENTITY_ID, id);
		mapContext.put(WebConstant.KEY_ENTITY_STATE, ProduceOrderState.COMPLETE.getValue());
		mapContext.put("completionTime", DateUtil.getSystemDate());
		this.produceOrderService.updateByMapContext(mapContext);
		//判断是否存在自产生产单 存在则以自产生产单为主
		List<ProduceOrder> ways = this.produceOrderService.findListByOrderIdAndTypesAndWays(produceOrder.getCustomOrderId(), null, Arrays.asList(ProduceOrderWay.SELF_PRODUCED.getValue()));
		if (ways == null || ways.size() == 0) {
			//如果订单状态为 生产中 则修改为待打包
			CustomOrder customOrder = this.customOrderService.findById(produceOrder.getCustomOrderId());
			if (customOrder.getStatus().equals(OrderStatus.PRODUCTION.getValue())) {
				MapContext updateOrder = new MapContext();
				updateOrder.put(WebConstant.KEY_ENTITY_ID, customOrder.getId());
				updateOrder.put(WebConstant.KEY_ENTITY_STATUS, OrderStatus.TO_PACKAGED.getValue());
				this.customOrderService.updateByMapContext(updateOrder);
			}
		}
		return ResultFactory.generateSuccessResult();
	}

	@Override
	public RequestResult findAllDesign(MapContext mapContext, Integer pageNum, Integer pageSize) {
		mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID, WebUtils.getCurrBranchId());
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		paginatedFilter.setFilters(mapContext);
		Pagination pagination = new Pagination();
		pagination.setPageSize(pageSize);
		pagination.setPageNum(pageNum);
		paginatedFilter.setPagination(pagination);
		return ResultFactory.generateRequestResult(this.customOrderDesignService.findListByFilter(paginatedFilter));
	}

	@Override
	public RequestResult findOrderPackagesNo(String id, String type, Integer count) {
		//判断订单是否存在
		String no;
		OrderProduct prod = orderProductService.findById(id);
		no = prod.getNo();
		List noOrder = new ArrayList();
		String typeString = "finishedStockItemType";
		Basecode basecode = this.basecodeService.findByTypeAndCode(typeString, type);
		type = basecode.getValue() + count.toString();
		for (int i = 1; i <= count; i++) {//包裹编号以订单编号+部件类型编码+包裹总数+第几包（例：J20190506-03-A5-01）
			noOrder.add(no + "-" + type + "-" + i);
		}
		return ResultFactory.generateRequestResult(noOrder);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult uploadOrderFiles(String id, Integer type, String resId, List<MultipartFile> multipartFileList) {
		int s; //判断资源赋值类型
		//判断订单以及资源是否存在
		switch (CustomOrderFilesType.getByValue(type)) {
			case DEMAND:
				CustomOrderDemand customOrderDemand = this.customOrderDemandService.findById(resId);
				if (customOrderDemand == null || !customOrderDemand.getCustomOrderId().equals(id)) {
					return ResultFactory.generateResNotFoundResult();
				}
				s = 0;
				break;
			case ORDER_PRODUCT:
				if (!resId.equals("productId")) {
					OrderProduct byIdOrderProduct = this.orderProductService.findById(resId);
					if (byIdOrderProduct == null || !byIdOrderProduct.getCustomOrderId().equals(id)) {
						return ResultFactory.generateResNotFoundResult();
					}
					s = 1;
				} else {
					s = 2;
				}
				break;
			case PRODUCE_ORDER:
				if (!resId.equals("produceId")) {
					ProduceOrder orderServiceById = this.produceOrderService.findById(resId);
					if (orderServiceById == null || !orderServiceById.getCustomOrderId().equals(id)) {
						return ResultFactory.generateResNotFoundResult();
					}
					s = 1;
				} else {
					s = 2;
				}
				break;
			default:
				return ResultFactory.generateResNotFoundResult();
		}
		List imgList = new ArrayList();
		for (MultipartFile multipartFile : multipartFileList) {
			CustomOrderFiles customOrderFiles = new CustomOrderFiles();
			if (s == 0) {
				customOrderFiles.setBelongId(resId);
				customOrderFiles.setStatus(CustomOrderFilesStatus.FORMAL.getValue());
			} else if (s == 1) {
				customOrderFiles.setBelongId(resId);
				customOrderFiles.setStatus(CustomOrderFilesStatus.FORMAL.getValue());
			} else {
				customOrderFiles.setBelongId(null);
				customOrderFiles.setStatus(CustomOrderFilesStatus.TEMP.getValue());
			}
			if (id.equals("null")) {
				customOrderFiles.setCustomOrderId(null);
			} else {
				customOrderFiles.setCustomOrderId(id);
			}
			customOrderFiles.setCategory(CustomOrderFilesCategory.ACCESSORY.getValue());
			customOrderFiles.setType(type);
			customOrderFiles.setCreated(DateUtil.getSystemDate());
			customOrderFiles.setCreator(WebUtils.getCurrUserId());
			UploadInfo uploadInfo = AppBeanInjector.baseFileUploadComponent.doUploadByModule(UploadType.FORMAL, multipartFile, UploadResourceType.CUSTOM_ORDER, id, resId);
			customOrderFiles.setPath(uploadInfo.getRelativePath());
			customOrderFiles.setFullPath(AppBeanInjector.configuration.getDomainUrl() + uploadInfo.getRelativePath());
			customOrderFiles.setName(uploadInfo.getFileName());
			customOrderFiles.setMime(uploadInfo.getFileMimeType().getRealType());
			customOrderFiles.setOriginalMime(uploadInfo.getFileMimeType().getOriginalType());
			this.customOrderFilesService.add(customOrderFiles);
			imgList.add(customOrderFiles);
		}
		return ResultFactory.generateRequestResult(imgList);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult deleteCustomOrderFile(String id, String fileId) {
		//判断资源文件 以及订单是否存在
		CustomOrderFiles byId = this.customOrderFilesService.findById(fileId);
		if (byId == null || !byId.getCustomOrderId().equals(id)) {
			return ResultFactory.generateSuccessResult();
		}
		//清除本地文件
		AppBeanInjector.baseFileUploadComponent.deleteFileByDir(AppBeanInjector.configuration.getUploadFileRootDir().concat(byId.getPath()));
		//清除数据库文件
		this.customOrderFilesService.deleteById(fileId);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	@SysOperationLog(detail = "删除订单", operationType = OperationType.DELETE, operationMoudule = OperationMoudule.CUSTOM_ORDER)
	public RequestResult deleteOrderById(String orderId) {
		return this.deleteOrder(orderId);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult producePermit(String orderId, String produceId) {
		ProduceOrder byId = this.produceOrderService.findById(produceId);
		if (byId == null || !byId.getCustomOrderId().equals(orderId) || byId.getPermit() == ProduceOrderPermit.ALLOW.getValue()) {
			return ResultFactory.generateResNotFoundResult();
		}
		MapContext mapContext = new MapContext();
		mapContext.put(WebConstant.KEY_ENTITY_ID, produceId);
		mapContext.put("permit", ProduceOrderPermit.ALLOW.getValue());
		this.produceOrderService.updateByMapContext(mapContext);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult produceComplete(String orderId, String produceId) {
		ProduceOrder byId = this.produceOrderService.findById(produceId);
		if (byId == null || !byId.getCustomOrderId().equals(orderId) || byId.getState() != ProduceOrderState.IN_PRODUCTION.getValue()) {
			return ResultFactory.generateResNotFoundResult();
		}
		MapContext mapContext = new MapContext();
		mapContext.put(WebConstant.KEY_ENTITY_ID, produceId);
		if (byId.getWay().equals(ProduceOrderWay.COORDINATION.getValue())) {
			mapContext.put(WebConstant.KEY_ENTITY_STATE, CoordinationState.COMPLETE.getValue());
		} else {
			mapContext.put(WebConstant.KEY_ENTITY_STATE, ProduceOrderState.COMPLETE.getValue());
		}
		this.produceOrderService.updateByMapContext(mapContext);
		//判断是否存在未完成的自产生产单
		List<ProduceOrder> produceOrders = this.produceOrderService.findIncompleteListByOrderId(orderId, Arrays.asList(ProduceOrderWay.SELF_PRODUCED.getValue()));
		if (produceOrders.size() == 0) {
			MapContext updateOrder = new MapContext();
			updateOrder.put(WebConstant.KEY_ENTITY_ID, orderId);
			updateOrder.put(WebConstant.KEY_ENTITY_STATUS, OrderStatus.TO_PACKAGED.getValue());
			this.customOrderService.updateByMapContext(updateOrder);
		}
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult submitDesignfee(String orderId, MapContext mapContext) {
		//判断订单是否存在
		CustomOrder customOrderServiceById = this.customOrderService.findById(orderId);
		if (customOrderServiceById == null) {
			return ResultFactory.generateResNotFoundResult();
		}
		//修改设计费
		mapContext.put(WebConstant.KEY_ENTITY_ID, orderId);
		this.customOrderService.updateByMapContext(mapContext);
		//如果订单不存在设计费的支付记录 则新建 存在则 修改支付记录
		MapContext filter = new MapContext();
		filter.put("orderId", orderId);
		filter.put("funds", PaymentFunds.DESIGN_FEE_CHARGE.getValue());
		PaymentDto paymentDto = this.paymentService.findByOrderIdAndFunds(filter);
		if (paymentDto == null) {
			//生成待支付的设计费支付记录
			Payment pricePayment = new Payment();
			pricePayment.setCompanyId(customOrderServiceById.getCompanyId());
			pricePayment.setStatus(PaymentStatus.PENDING_PAYMENT.getValue());
			pricePayment.setBranchId(WebUtils.getCurrBranchId());
			pricePayment.setNo(AppBeanInjector.uniquneCodeGenerator.getNextNo(UniquneCodeGenerator.UniqueResource.PAYMENT_NO));
			pricePayment.setCreator(WebUtils.getCurrUserId());
			pricePayment.setCreated(DateUtil.getSystemDate());
			pricePayment.setCustomOrderId(orderId);
			pricePayment.setWay(PaymentWay.BANK.getValue());
			pricePayment.setType(PaymentTypeNew.CHARGEBACK.getValue());
			pricePayment.setFunds(PaymentFunds.DESIGN_FEE_CHARGE.getValue());
			pricePayment.setAmount(mapContext.getTypedValue("designFee", BigDecimal.class));
			pricePayment.setHolder("红田集团");
			pricePayment.setPayee("4j1u3r1efshq");
			pricePayment.setResourceType(PaymentResourceType.ORDER.getValue());
			pricePayment.setFlag(0);
			RequestResult result = pricePayment.validateFields();
			if (result != null) {
				return result;
			}
			this.paymentService.add(pricePayment);
		} else {
			//如果支付记录未付款 则修改支付记录金额字段
			if (paymentDto.getStatus().equals(PaymentStatus.PENDING_PAYMENT.getValue())) {
				MapContext updatePayment = new MapContext();
				updatePayment.put("amount", mapContext.getTypedValue("designFee", BigDecimal.class));
				updatePayment.put(WebConstant.KEY_ENTITY_ID, paymentDto.getId());
				this.paymentService.updateByMapContext(updatePayment);
			}
		}
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addCustomOrderDemand(String orderId, CustomOrderDemand customOrderDemand) {
		//判断订单是否存在
		CustomOrder customOrderServiceById = this.customOrderService.findById(orderId);
		if (customOrderServiceById == null) {
			return ResultFactory.generateResNotFoundResult();
		}
		//判断产品是否存在
		OrderProduct orderProduct = this.orderProductService.findById(customOrderDemand.getOrderProductId());
		if (orderProduct == null || !orderProduct.getCustomOrderId().equals(orderId)) {
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_NO_REPETITION_ALLOWED_10094, AppBeanInjector.i18nUtil.getMessage("BIZ_NO_REPETITION_ALLOWED_10094"));
		}
		//判断该产品是否已经存在需求单
		CustomOrderDemand oldOrderDemand = this.customOrderDemandService.findByProductId(orderProduct.getId());
		if (oldOrderDemand != null) {
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_NOT_ALLOW_OPERATION_10020, AppBeanInjector.i18nUtil.getMessage("BIZ_NOT_ALLOW_OPERATION_10020"));
		}
		this.customOrderDemandService.add(customOrderDemand);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updateCustomOrderDemand(String orderId, String demandId, MapContext mapContext) {
		CustomOrderDemand byId = this.customOrderDemandService.findById(demandId);
		if (byId == null || !byId.getCustomOrderId().equals(orderId)) {
			return ResultFactory.generateResNotFoundResult();
		}
		mapContext.put(WebConstant.KEY_ENTITY_ID, demandId);
		this.customOrderDemandService.updateByMapContext(mapContext);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult deleteCustomOrderDemand(String orderId, String demandId) {
		CustomOrderDemand byId = this.customOrderDemandService.findById(demandId);
		if (byId == null || !byId.getCustomOrderId().equals(orderId)) {
			return ResultFactory.generateSuccessResult();
		}
		this.customOrderDemandService.deleteById(demandId);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult findProduceOrderById(String id, String produceId) {
		ProduceOrderDto oneById = this.produceOrderService.findOneById(produceId);
		if (oneById == null || !oneById.getCustomOrderId().equals(id)) {
			return ResultFactory.generateResNotFoundResult();
		}
		oneById.setProduceFlowDtos(this.produceFlowService.findListByProduceOrderId(produceId));
		oneById.setOrderProductDto(this.orderProductService.findOneById(oneById.getOrderProductId()));
		return ResultFactory.generateRequestResult(oneById);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult findProductInfo(String id, String pId) {
		OrderProductDto oneById = this.orderProductService.findOneById(id);
		if (oneById == null || !oneById.getCustomOrderId().equals(id)) {
			return ResultFactory.generateResNotFoundResult();
		}
		return ResultFactory.generateRequestResult(oneById);
	}

	/**
	 * 订单打印信息查询
	 *
	 * @param orderId
	 * @return
	 */
	@Override
	public RequestResult findOrderPrintTable(String orderId) {
		String branchId = WebUtils.getCurrBranchId();
		MapContext mapContext = MapContext.newOne();
		mapContext.put("orderId", orderId);
		mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID, branchId);
		OrderPrintTableDto orderPrintTableDto = this.customOrderService.findOrderPrintTable(mapContext);
		List<OrderProductDto> orderProductDtos = this.orderProductService.findListByOrderId(orderId);
		if (orderProductDtos.size() > 1) {
			orderPrintTableDto.setOrderProductTypeName("全屋");
		} else {
			orderPrintTableDto.setOrderProductTypeName("橱柜");
		}
		List<PaymentDto> paymentDtos = this.paymentService.findByOrderId(orderId);
		orderPrintTableDto.setOrderProductDtos(orderProductDtos);
		orderPrintTableDto.setPaymentDtos(paymentDtos);
		return ResultFactory.generateRequestResult(orderPrintTableDto);
	}

	/**
	 * 订单产品打印信息
	 *
	 * @param orderProductId
	 * @return
	 */
	@Override
	public RequestResult findProductPrintTable(String orderProductId) {
		//产品信息
		OrderProductDto orderProductDto = this.orderProductService.findOneById(orderProductId);
		if (orderProductDto == null) {
			return ResultFactory.generateResNotFoundResult();
		}
		//设计单信息
		String orderId = orderProductDto.getCustomOrderId();
		CustomOrderDesignDto customOrderDesignDto = this.customOrderDesignService.findOneByProductId(orderProductId);
		//订单信息
		MapContext mapContext = MapContext.newOne();
		mapContext.put("orderId", orderId);
		mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID, WebUtils.getCurrBranchId());
		OrderPrintTableDto orderPrintTableDto = this.customOrderService.findOrderPrintTable(mapContext);

		List<OrderProductDto> orderProductDtos = new ArrayList<>();
		orderProductDtos.add(orderProductDto);
		orderPrintTableDto.setOrderProductDtos(orderProductDtos);
		orderPrintTableDto.setCustomOrderDesignDto(customOrderDesignDto);
		if (orderProductDto.getType() == 0) {
			orderPrintTableDto.setOrderProductTypeName("橱柜");
		} else {
			orderPrintTableDto.setOrderProductTypeName("全屋");
		}
		return ResultFactory.generateRequestResult(orderPrintTableDto);
	}


	/**
	 * 产品需求打印信息
	 *
	 * @param demandId
	 * @return
	 */
	@Override
	public RequestResult findDemandPrintTable(String demandId) {
		//需求信息
		CustomOrderDemandDto customOrderDemandDto = this.customOrderDemandService.selectByDemandId(demandId);
		//订单信息
		String orderId = customOrderDemandDto.getCustomOrderId();
		MapContext mapContext = MapContext.newOne();
		mapContext.put("orderId", orderId);
		mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID, WebUtils.getCurrBranchId());
		OrderPrintTableDto orderPrintTableDto = this.customOrderService.findOrderPrintTable(mapContext);
		//产品信息
		OrderProductDto orderProductDto = this.orderProductService.findOneById(customOrderDemandDto.getOrderProductId());
		if (orderProductDto.getType() == 0) {
			orderPrintTableDto.setOrderProductTypeName("橱柜");
		} else {
			orderPrintTableDto.setOrderProductTypeName("全屋");
		}
		List<OrderProductDto> orderProductDtos = new ArrayList<>();
		orderProductDtos.add(orderProductDto);
		orderPrintTableDto.setOrderProductDtos(orderProductDtos);
		orderPrintTableDto.setCustomOrderDemandDto(customOrderDemandDto);
		return ResultFactory.generateRequestResult(orderPrintTableDto);
	}

	/**
	 * 设计信息打印
	 *
	 * @param designId
	 * @return
	 */
	@Override
	public RequestResult findDesignPrintTable(String designId) {
		//设计信息
		CustomOrderDesignDto oneByDesignId = this.customOrderDesignService.findOneByDesignId(designId);
		if (oneByDesignId == null) {
			return ResultFactory.generateResNotFoundResult();
		}
		//产品信息
		OrderProductDto orderProductDto = this.orderProductService.findOneById(oneByDesignId.getOrderProductId());
		//订单信息
		String orderId = oneByDesignId.getCustomOrderId();
		MapContext mapContext = MapContext.newOne();
		mapContext.put("orderId", orderId);
		mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID, WebUtils.getCurrBranchId());
		OrderPrintTableDto orderPrintTableDto = this.customOrderService.findOrderPrintTable(mapContext);
		if (orderProductDto.getType() == 0) {
			orderPrintTableDto.setOrderProductTypeName("橱柜");
		} else {
			orderPrintTableDto.setOrderProductTypeName("全屋");
		}
		orderPrintTableDto.setCustomOrderDesignDto(oneByDesignId);
		return ResultFactory.generateRequestResult(orderPrintTableDto);
	}

	/**
	 * 生产单打印信息
	 *
	 * @return
	 */
	@Override
	public RequestResult findProducePrintTable(String produceId) {

		//生产单信息
		ProduceOrderDto produceOrderDto = this.produceOrderService.findOneById(produceId);
		if (produceOrderDto == null) {
			return ResultFactory.generateResNotFoundResult();
		}
		String orderId = produceOrderDto.getCustomOrderId();
		//源订单信息
		MapContext mapContext = MapContext.newOne();
		mapContext.put("orderId", orderId);
		mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID, WebUtils.getCurrBranchId());
		OrderPrintTableDto orderPrintTableDto = this.customOrderService.findOrderPrintTable(mapContext);
		//产品信息
		String productId = produceOrderDto.getOrderProductId();
		OrderProductDto orderProductDto = this.orderProductService.findOneById(productId);
		if (orderProductDto.getType() == 0) {
			orderPrintTableDto.setOrderProductTypeName("橱柜");
		} else {
			orderPrintTableDto.setOrderProductTypeName("全屋");
		}
		//生产流程信息
		List<ProduceFlowDto> produceFlowDtos = this.produceFlowService.findListByProduceOrderId(produceId);
		List<OrderProductDto> orderProductDtos = new ArrayList<>();
		orderProductDtos.add(orderProductDto);
		orderPrintTableDto.setOrderProductDtos(orderProductDtos);
		orderPrintTableDto.setProduceOrderDto(produceOrderDto);
		orderPrintTableDto.setProduceFlowDtos(produceFlowDtos);
		return ResultFactory.generateRequestResult(orderPrintTableDto);
	}

	@Override
	public RequestResult findDesignOverview() {
		MapContext result = new MapContext();
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		Pagination pagination = new Pagination();
		pagination.setPageSize(999999999);
		pagination.setPageNum(1);
		paginatedFilter.setPagination(pagination);
		//今日新增设计任务数
		MapContext filter1 = new MapContext();
		filter1.put("createdNow", 1);
		paginatedFilter.setFilters(filter1);
		PaginatedList<CustomOrderDesign> designPaginatedList = this.customOrderDesignService.selectByFilter(paginatedFilter);
		result.put("addDesignCount", designPaginatedList.getRows().size());
		//今日延期设计任务数
		result.put("delayDesignCount", 0);
		//今日完成设计任务数
		MapContext filter3 = new MapContext();
		filter3.put("endTimeNow", 1);
		paginatedFilter.setFilters(filter3);
		PaginatedList<CustomOrderDesign> designPaginatedList2 = this.customOrderDesignService.selectByFilter(paginatedFilter);
		result.put("completeDesignCount", designPaginatedList2.getRows().size());
		return ResultFactory.generateRequestResult(result);
	}

	@Override
	public RequestResult findCustomOrderOverview() {
		MapContext result = new MapContext();
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		Pagination pagination = new Pagination();
		pagination.setPageNum(1);
		pagination.setPageSize(999999);
		paginatedFilter.setPagination(pagination);
		//待生产订单数
		MapContext filter1 = new MapContext();
		filter1.put("status", OrderStatus.TO_PRODUCED.getValue());
		filter1.put("state", 1);
		filter1.put(WebConstant.KEY_ENTITY_BRANCH_ID, WebUtils.getCurrBranchId());
		paginatedFilter.setFilters(filter1);
		PaginatedList<CustomOrder> customOrderPaginatedList = this.customOrderService.selectByFilter(paginatedFilter);
		result.put("addOrderCount", customOrderPaginatedList.getRows().size());
		//待付款订单数
		MapContext filter2 = new MapContext();
		filter2.put(WebConstant.KEY_ENTITY_STATUS, OrderStatus.TO_PAID.getValue());
		filter2.put(WebConstant.KEY_ENTITY_BRANCH_ID, WebUtils.getCurrBranchId());
		filter2.put("state", 1);
		paginatedFilter.setFilters(filter2);
		PaginatedList<CustomOrder> customOrderPaginatedList1 = this.customOrderService.selectByFilter(paginatedFilter);
		result.put("toPaidCount", customOrderPaginatedList1.getRows().size());
		//延期订单数
		MapContext filter3 = MapContext.newOne();
		filter3.put("branchId", WebUtils.getCurrBranchId());
		Integer delayOrderCount = this.customOrderService.findOverdueOrderCount(filter3);
		result.put("delayOrderCount", delayOrderCount);
		//待报价订单数
		MapContext m = MapContext.newOne();
		m.put("orderStatus", OrderStatus.TO_QUOTED.getValue());
		m.put("branchId", WebUtils.getCurrBranchId());
		Integer endOrderCount = this.customOrderService.findOrderCountByStatus(m);
		result.put("completeOrderCount", endOrderCount);
		//待接单订单数
		MapContext m2 = MapContext.newOne();
		m.put("orderStatus", OrderStatus.TO_RECEIVE.getValue());
		m.put("branchId", WebUtils.getCurrBranchId());
		Integer toreceiveOrderCount = this.customOrderService.findOrderCountByStatus(m2);
		result.put("toreceiveOrderCount", toreceiveOrderCount);
		//订单总数
		Integer allOrderCount = this.customOrderService.findAllOrderCount(WebUtils.getCurrBranchId());
		result.put("allOrderCount", allOrderCount);
		return ResultFactory.generateRequestResult(result);
	}

	/**
	 * 售后单概览
	 *
	 * @return
	 */
	@Override
	public RequestResult findAftersaleOrderOverview() {
		MapContext result = new MapContext();
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		Pagination pagination = new Pagination();
		pagination.setPageNum(1);
		pagination.setPageSize(999999);
		paginatedFilter.setPagination(pagination);
		//待生产售后单数
		MapContext filter1 = new MapContext();
		filter1.put("status", OrderStatus.TO_PRODUCED.getValue());
		filter1.put("type", OrderType.SUPPLEMENTORDER.getValue());
		filter1.put(WebConstant.KEY_ENTITY_BRANCH_ID, WebUtils.getCurrBranchId());
		paginatedFilter.setFilters(filter1);
		PaginatedList<CustomOrder> customOrderPaginatedList = this.customOrderService.selectByFilter(paginatedFilter);
		result.put("addOrderCount", customOrderPaginatedList.getRows().size());
		//待付款售后单数
		MapContext filter2 = new MapContext();
		filter2.put(WebConstant.KEY_ENTITY_STATUS, OrderStatus.TO_PAID.getValue());
		filter2.put(WebConstant.KEY_ENTITY_BRANCH_ID, WebUtils.getCurrBranchId());
		filter2.put("type", OrderType.SUPPLEMENTORDER.getValue());
		paginatedFilter.setFilters(filter2);
		PaginatedList<CustomOrder> customOrderPaginatedList1 = this.customOrderService.selectByFilter(paginatedFilter);
		result.put("toPaidCount", customOrderPaginatedList1.getRows().size());
		//延期售后单数
		MapContext filter3 = MapContext.newOne();
		filter3.put("branchId", WebUtils.getCurrBranchId());
		filter3.put("type", OrderType.SUPPLEMENTORDER.getValue());
		Integer delayOrderCount = this.customOrderService.findOverdueOrderCount(filter3);
		result.put("delayOrderCount", delayOrderCount);
		return ResultFactory.generateRequestResult(result);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updateCustomOrderFlag(String orderId, String flag) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		User logUser = AppBeanInjector.userService.findByUserId(WebUtils.getCurrUserId());
		String loginName = logUser.getName();
		CustomOrderDto byOrderId = this.customOrderService.findByOrderId(orderId);
		if (byOrderId == null) {
			return ResultFactory.generateResNotFoundResult();
		}
		DealerAccount dealerAccount = this.dealerAccountService.findByCompanIdAndNatureAndType(byOrderId.getCompanyId(), DealerAccountNature.PUBLIC.getValue(), DealerAccountType.FREE_ACCOUNT.getValue());
		if (flag.equals("0")) {//恢复
			//处理订单
			MapContext mapContext = MapContext.newOne();
			mapContext.put("id", orderId);
			mapContext.put("flag", flag);
			mapContext.put("status", OrderStatus.TO_PAID.getValue());
			this.customOrderService.updateByMapContext(mapContext);
			//处理产品
			List<OrderProductDto> listByOrderId = this.orderProductService.findListByOrderId(orderId);
			for (OrderProductDto productDto : listByOrderId) {
				MapContext mapContext1 = MapContext.newOne();
				mapContext1.put("id", productDto.getId());
				mapContext1.put("flag", flag);
				mapContext1.put("status", OrderProductStatus.TO_PAYMENT.getValue());
				this.orderProductService.updateByMapContext(mapContext1);
			}
			//处理支付单
			MapContext params = MapContext.newOne();
			params.put("orderId", orderId);
			params.put("funds", PaymentFunds.ORDER_FEE_CHARGE.getValue());
			PaymentDto byOrderIdAndFunds = this.paymentService.findByOrderIdAndFunds(params);
			if(byOrderIdAndFunds!=null) {
				MapContext mapContext1 = MapContext.newOne();
				mapContext1.put("id", byOrderIdAndFunds.getId());
				mapContext1.put("flag", flag);
				mapContext1.put("status", 0);
				this.paymentService.updateByMapContext(mapContext1);
			}else {
				mapContext.put("status", OrderStatus.TO_RECEIVE.getValue());
				this.customOrderService.updateByMapContext(mapContext);
			}
		} else {//废除
			if (byOrderId.getStatus() != OrderStatus.TO_RECEIVE.getValue()&&byOrderId.getStatus() != OrderStatus.TO_QUOTED.getValue() && byOrderId.getStatus() != OrderStatus.TO_PAID.getValue()) {
				return ResultFactory.generateErrorResult(ErrorCodes.BIZ_NOT_ALLOW_OPERATION_10020, AppBeanInjector.i18nUtil.getMessage("BIZ_NOT_ALLOW_OPERATION_10020"));
			}
			//处理订单
			MapContext mapContext = MapContext.newOne();
			mapContext.put("id", orderId);
			mapContext.put("flag", flag);
			mapContext.put("status", OrderStatus.DELETE.getValue());
			this.customOrderService.updateByMapContext(mapContext);
			//处理产品
			List<OrderProductDto> listByOrderId = this.orderProductService.findListByOrderId(orderId);
			for (OrderProductDto productDto : listByOrderId) {
				MapContext mapContext1 = MapContext.newOne();
				mapContext1.put("id", productDto.getId());
				mapContext1.put("flag", flag);
				mapContext1.put("status", OrderProductStatus.DELETE.getValue());
				this.orderProductService.updateByMapContext(mapContext1);
			}
			//处理支付单
			MapContext params = MapContext.newOne();
			params.put("orderId", orderId);
			params.put("funds", PaymentFunds.ORDER_FEE_CHARGE.getValue());
			PaymentDto byOrderIdAndFunds = this.paymentService.findByOrderIdAndFunds(params);
			if (byOrderIdAndFunds != null) {
				MapContext mapContext1 = MapContext.newOne();
				mapContext1.put("id", byOrderIdAndFunds.getId());
				mapContext1.put("flag", flag);
				mapContext1.put("status", 2);
				this.paymentService.updateByMapContext(mapContext1);
			//返还经销商账户金额
			MapContext updateDealerAccount = new MapContext();
			updateDealerAccount.put(WebConstant.KEY_ENTITY_ID, dealerAccount.getId());
			updateDealerAccount.put("balance", dealerAccount.getBalance().add((byOrderIdAndFunds.getPayAmount())));
			this.dealerAccountService.updateByMapContext(updateDealerAccount);
			//记录账户变动日志
			DealerAccountLog dealerAccountLog = new DealerAccountLog();
			dealerAccountLog.setAmount(byOrderIdAndFunds.getPayAmount());
			dealerAccountLog.setDealerAccountId(dealerAccount.getId());
			dealerAccountLog.setContent(dateFormat.format(DateUtil.getSystemDate()) + loginName + "废除订单" + byOrderId.getNo() + "," + "经销商自由资金返还" + byOrderIdAndFunds.getPayAmount());
			dealerAccountLog.setType(DealerAccountLogType.TO_CHANGE_INTO.getValue());
			dealerAccountLog.setCreator(WebUtils.getCurrUserId());
			dealerAccountLog.setCreated(DateUtil.getSystemDate());
			dealerAccountLog.setDisburse(false);
			dealerAccountLog.setResourceId(orderId);
			dealerAccountLog.setCompanyId(WebUtils.getCurrCompanyId());
			this.dealerAccountLogService.add(dealerAccountLog);
			//经销商积分扣除
			CompanyDto companyDto = this.companyService.findCompanyById(byOrderId.getCompanyId());
			if (companyDto != null) {
				MapContext companyMap = MapContext.newOne();
				companyMap.put("id", companyDto.getId());
				BigDecimal bigDecimal = new BigDecimal(companyDto.getIntegral().toString());
				BigDecimal bigDecimal1 = byOrderIdAndFunds.getPayAmount();
				companyMap.put("integral", bigDecimal.subtract(bigDecimal1));
				this.companyService.updateByMapContext(companyMap);
			}
			}
		}
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	@SysOperationLog(detail = "修改订单", operationType = OperationType.UPDATE, operationMoudule = OperationMoudule.PRODUCE)
	public RequestResult leaderupdateorder(String id, MapContext mapContext) {
		//订单是否存在
		CustomOrderDto byOrderId = this.customOrderService.findByOrderId(id);
		if (byOrderId == null) {
			return ResultFactory.generateResNotFoundResult();
		}
		//修改订单
		mapContext.put("id", id);
		this.customOrderService.updateByMapContext(mapContext);
		//是否修改产品
		List<Map> list = mapContext.getTypedValue("orderProducts", List.class);
		if (list.size() > 0) {//如果修改的有产品信息
			for (Map product : list) {
				//查询产品下所有的生产部件，和修改后的进行对比
				String productId = product.get("productId").toString();
				//查询产品
				OrderProductDto oneOrderProductById = this.orderProductService.findOneById(productId);
				//产品编号后缀字母清空
				String productNo = oneOrderProductById.getNo();
				List<Basecode> produceOrderType = this.basecodeService.findByTypeAndDelFlag("produceOrderType", 0);
				Basecode orderProductType = this.basecodeService.findByTypeAndCode("orderProductType", oneOrderProductById.getType().toString());
				for(Basecode basecode:produceOrderType) {
					String remark=basecode.getRemark();
					productNo = productNo.replace(remark, "");
					if(remark.equals(orderProductType.getRemark())){
						productNo = orderProductType.getRemark() + productNo;
					}
				}
				List<OrderProductParts> orderProductParts = this.orderProductPartsService.findByProductId(productId);
				List<String> newParts = new ArrayList<>();
				List<String> oldParts = new ArrayList<>();
				//新部件的id字符串
				String parts = product.get("productParts").toString();
				String[] newPart = parts.split(",");
				//新部件
				newParts = new ArrayList<>(Arrays.asList(newPart));
				//循环老部件，看是否存在新部件中
				for (OrderProductParts productParts : orderProductParts) {
					String partId = productParts.getProductPartsId();
					oldParts.add(partId);
					if (!newParts.contains(partId)) {//如果不存在，则删除老部件和生产单
						ProductParts byId = this.productPartsService.findById(partId);
						Integer type = byId.getPartsType();
						Integer way = byId.getProduceType();
						//根据产品，部件类型，生产方式查询生产单
						MapContext param = MapContext.newOne();
						param.put("type", type);
						param.put("way", way);
						param.put("orderProductId", productId);
						ProduceOrder produceOrder = this.produceOrderService.findOneByTypeAndWayAndProductId(param);
						if(produceOrder!=null) {
							//删除生产单
							this.produceOrderService.deleteById(produceOrder.getId());
						}
						this.orderProductPartsService.deleteById(productParts.getId());
					}
				}
				//循环新部件
				String houzhui = "";
				for (String newPartId : newParts) {
					//查询部件信息
					ProductParts partbyId = this.productPartsService.findById(newPartId);
					houzhui = houzhui + partbyId.getOrderPartsIdentify();
					if (!oldParts.contains(newPartId)) {
						//添加产品部件
						OrderProductParts productParts1 = new OrderProductParts();
						productParts1.setOrderProductId(productId);
						productParts1.setProductPartsId(newPartId);
						this.orderProductPartsService.add(productParts1);
						//生成生产单
						ProduceOrder produceOrder = new ProduceOrder();
						produceOrder.setCustomOrderId(id);
						produceOrder.setOrderProductId(productId);
						produceOrder.setCustomOrderNo(byOrderId.getNo());
						produceOrder.setCreated(DateUtil.getSystemDate());
						produceOrder.setCreator(WebUtils.getCurrUserId());
						produceOrder.setBranchId(WebUtils.getCurrBranchId());
						produceOrder.setResourceType(byOrderId.getType());
						produceOrder.setFlag(0);
						if (partbyId.getOrderPartsIdentify() != null) {
							produceOrder.setNo(productNo + partbyId.getOrderPartsIdentify());
						} else {
							produceOrder.setNo(productNo);
						}
						if (partbyId.getProduceType() != ProduceOrderWay.COORDINATION.getValue()) {
							produceOrder.setPay(ProduceOrderPay.PAY.getValue());
						} else {
							produceOrder.setPay(ProduceOrderPay.NOT_PAY.getValue());
							produceOrder.setAmount(new BigDecimal("0.00"));
						}
						produceOrder.setType(partbyId.getPartsType());
						produceOrder.setWay(partbyId.getProduceType());
						produceOrder.setPermit(ProduceOrderPermit.NOT_ALLOW.getValue());
						if (partbyId.getPartsType() == ProduceOrderType.HARDWARE.getValue()) {//如果是五金
							produceOrder.setState(ProduceOrderState.COMPLETE.getValue());
						} else if (partbyId.getPartsType().equals(ProduceOrderType.CABINET_BODY.getValue())) {//如果是柜体
							produceOrder.setState(ProduceOrderState.NOT_YET_BEGUN.getValue());
						} else if (partbyId.getPartsType().equals(ProduceOrderType.DOOR_PLANK.getValue())) {//如果是门板
							if (partbyId.getProduceType().equals(ProduceOrderWay.SELF_PRODUCED.getValue())) {//如果是自产
								produceOrder.setState(ProduceOrderState.NOT_YET_BEGUN.getValue());
								produceOrder.setPermit(ProduceOrderPermit.NOT_ALLOW.getValue());
							} else {//如果是外协
								produceOrder.setState(ProduceOrderState.NOT_YET_BEGUN.getValue());
							}
						} else {
							produceOrder.setState(ProduceOrderState.NOT_YET_BEGUN.getValue());
						}
						this.produceOrderService.add(produceOrder);
					}
				}
				//删除原属性值
				List<OrderProductAttribute> listByProductId = orderProductAttributeService.findListByProductId(productId);
				if(listByProductId!=null&&listByProductId.size()>0){//删除原属性值
					for(OrderProductAttribute productAttribute:listByProductId){
						String id1 = productAttribute.getId();
						this.orderProductAttributeService.deleteById(id1);
					}
				}
				//添加新产品属性
				List<Map> productAttributeValues = (List<Map>) product.get("productAttributeValues");
				if(productAttributeValues!=null&&productAttributeValues.size()>0){
					for(Map mapCon:productAttributeValues) {
						//查询属性值是否存在
						String keyId=mapCon.get("productAttributeKeyId").toString();
						String value=mapCon.get("valueName").toString();
						String keyName=mapCon.get("keyName").toString();
						OrderProductAttribute attributeValue=new OrderProductAttribute();
						attributeValue.setOrderProductId(productId);
						attributeValue.setProductAttributeKeyId(keyId);
						attributeValue.setKeyName(keyName);
						attributeValue.setValueName(value);
						MapContext map0=MapContext.newOne();
						map0.put("productAttributeKeyId",keyId);
						map0.put("attributeValue",value);
						map0.put("delFlag",0);
						ProductAttributeValue newone=this.productAttributeValueService.findByKeyIdAndValue(map0);
						if(newone==null){//不存在，则新建
							ProductAttributeValue productAttributeValue=new ProductAttributeValue();
							productAttributeValue.setProductAttributeKeyId(keyId);
							productAttributeValue.setStatus(1);
							productAttributeValue.setDelFlag(0);
							productAttributeValue.setAttributeValue(value);
							productAttributeValue.setCreator(WebUtils.getCurrUserId());
							productAttributeValue.setCreated(new Date());
							productAttributeValue.setKeyName(keyName);
							this.productAttributeValueService.add(productAttributeValue);

							attributeValue.setProductAttributeValueId(productAttributeValue.getId());
						}else {
							attributeValue.setProductAttributeValueId(newone.getId());
						}
						this.orderProductAttributeService.add(attributeValue);
					}
				}
				//修改产品编号
				product.put("id", productId);
				product.put("no", productNo + houzhui);
				Integer a=this.orderProductService.updateByMap(product);
			}

		}
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addOrderFiles(List<MultipartFile> multipartFileList) {
		//添加图片到本地
		List<MapContext> listUrls = new ArrayList<>();
		if (multipartFileList != null && multipartFileList.size() > 0) {
			for (MultipartFile multipartFile : multipartFileList) {
				UploadInfo uploadInfo = AppBeanInjector.baseFileUploadComponent.doUploadByModule(UploadType.FORMAL, multipartFile, UploadResourceType.CUSTOM_ORDER,"order");
				//添加图片到数据库
				CustomOrderFiles customOrderFiles = new CustomOrderFiles();
				customOrderFiles.setCreated(DateUtil.getSystemDate());
				customOrderFiles.setType(CustomOrderFilesType.CONTRACT.getValue());
				customOrderFiles.setFullPath(WebUtils.getDomainUrl() + uploadInfo.getRelativePath());
				customOrderFiles.setMime(uploadInfo.getFileMimeType().getRealType());
				customOrderFiles.setName(uploadInfo.getFileName());
				customOrderFiles.setOriginalMime(uploadInfo.getFileMimeType().getOriginalType());
				customOrderFiles.setPath(uploadInfo.getRelativePath());
				customOrderFiles.setStatus(0);
				customOrderFiles.setCategory(CustomOrderFilesCategory.ACCESSORY.getValue());
				customOrderFiles.setCreator(WebUtils.getCurrUserId());
				//customOrderFiles.setCustomOrderId(id);
				this.customOrderFilesService.add(customOrderFiles);
				MapContext map = MapContext.newOne();
				map.put("fullPath", customOrderFiles.getFullPath());
				map.put("imgRelPath", uploadInfo.getRelativePath());
				map.put("id", customOrderFiles.getId());
				listUrls.add(map);
			}
		}
		return ResultFactory.generateRequestResult(listUrls);
	}

	@Override
	public RequestResult writeOrderExcel(MapContext mapContext, Integer pageNum, Integer pageSize, ExcelParam excelParam) {
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		Pagination pagination = new Pagination();
		pagination.setPageNum(pageNum);
		pagination.setPageSize(pageSize);
		paginatedFilter.setPagination(pagination);
		mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID, WebUtils.getCurrBranchId());
		mapContext.put("funds", PaymentFunds.ORDER_FEE_CHARGE.getValue());
		paginatedFilter.setFilters(mapContext);
		Map<String, String> created = new HashMap<String, String>();
		created.put(WebConstant.KEY_ENTITY_CREATED, "desc");
		List sort = new ArrayList();
		sort.add(created);
		paginatedFilter.setSorts(sort);
		PaginatedList<CustomOrderDto> listByPaginateFilter = this.customOrderService.findListByPaginateFilter(paginatedFilter);
		List<Map<String, Object>> result = new ArrayList<>();
		if (listByPaginateFilter.getRows().size() > 0) {
			for (CustomOrderDto customOrderDto : listByPaginateFilter.getRows()) {
				String orderId = customOrderDto.getId();
				List<OrderProductDto> productDtos = this.orderProductService.findListByOrderId(orderId);
				for (OrderProductDto productDto : productDtos) {
					Map map = new HashMap() {
						{
							put("no", productDto.getNo());
							put("product", productDto.getTypeName());
							put("seriesName", productDto.getSeriesName());
							put("productDoor", productDto.getDoor());
							put("doorColor", productDto.getDoorColor());
							put("bodyTec", productDto.getBodyTecName());
							put("bodyColor", productDto.getBodyColor());
							put("price", productDto.getPrice());
							put("install", productDto.getInstall());
							put("productStatus", productDto.getStatusName());
							put("companyName", customOrderDto.getDealerName());
							put("dealerTel", customOrderDto.getDealerTel());
							put("companyAddress", customOrderDto.getDealerAddress());
							put("customer", customOrderDto.getCustomerName());
							put("reciverName", customOrderDto.getReceiverName());
							put("placeOrderName", customOrderDto.getPlaceOrderName());

						}
					};
					result.add(map);
				}
			}
		}
		new BaseExportExcelUtil().download("第" + pageNum + "页", result, excelParam);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addFactoryAftersaleOrderTwo(CustomOrderDto customOrderDto) {
		CustomOrder afterOrder = new CustomOrder();
		String afterProductId = customOrderDto.getAftersaleProductId();
		OrderProductDto oneById = new OrderProductDto();
		if (afterProductId != null) {
			oneById = this.orderProductService.findOneById(afterProductId);
			MapContext map=MapContext.newOne();
			Integer aftersaleNum=1;
			if(oneById.getAftersaleNum()!=null){
				aftersaleNum=oneById.getAftersaleNum()+1;
			}
			map.put("aftersaleNum",aftersaleNum);
			map.put("id",afterProductId);
         this.orderProductService.updateByMapContext(map);
		}
//		String productNo = null;
		//判断父级ID（源订单ID）是否存在
		if (customOrderDto.getParentId() == null) {
			afterOrder.setNo(customOrderDto.getNo());
			afterOrder.setOldNo(customOrderDto.getNo());
			afterOrder.setCityAreaId(customOrderDto.getCityAreaId());
			afterOrder.setAddress(customOrderDto.getAddress());
			afterOrder.setMerchandiser(customOrderDto.getMerchandiser());
			afterOrder.setParentId(customOrderDto.getParentId());
			afterOrder.setBusinessManager(customOrderDto.getBusinessManager());
			afterOrder.setOrderProductType(customOrderDto.getOrderProductType());
			Company company = this.companyService.findById(customOrderDto.getCompanyId());
			//判断经销商是否存在 状态是否是正常
			if (company == null || !company.getStatus().equals(CompanyStatus.NORMAL.getValue())) {
				return ResultFactory.generateResNotFoundResult();
			}
			//判断客户是否存在
			MapContext filter = new MapContext();
			filter.put(WebConstant.KEY_ENTITY_NAME, customOrderDto.getCustomerName());
			filter.put(WebConstant.KEY_ENTITY_COMPANY_ID, company.getId());
			List<CompanyCustomer> customerByMap = this.companyCustomerService.findCustomerByMap(filter);
			if (customerByMap == null || customerByMap.size() == 0) {
				CompanyCustomer companyCustomer = new CompanyCustomer();
				companyCustomer.setName(customOrderDto.getCustomerName());
				companyCustomer.setCompanyId(company.getId());
				companyCustomer.setCreated(DateUtil.getSystemDate());
				companyCustomer.setCreator(WebUtils.getCurrUserId());
				companyCustomer.setStatus(CompanyCustomerStatus.ORDER.getValue());
				this.companyCustomerService.add(companyCustomer);
				afterOrder.setCustomerId(companyCustomer.getId());
			} else {
				afterOrder.setCustomerId(customerByMap.get(0).getId());
			}
			afterOrder.setCompanyId(customOrderDto.getCompanyId());
		} else {
			//查询源订单信息
			CustomOrder customOrder = this.customOrderService.findById(customOrderDto.getParentId());
			if (customOrder == null) {
				return ResultFactory.generateResNotFoundResult();
			}
			afterOrder.setNo(customOrderDto.getNo());
			afterOrder.setOldNo(customOrder.getNo());
			afterOrder.setCityAreaId(customOrder.getCityAreaId());
			afterOrder.setAddress(customOrder.getAddress());
			afterOrder.setMerchandiser(customOrder.getMerchandiser());
			afterOrder.setCustomerId(customOrder.getCustomerId());
			afterOrder.setCompanyId(customOrder.getCompanyId());
			afterOrder.setParentId(customOrderDto.getParentId());
			afterOrder.setBusinessManager(customOrder.getBusinessManager());
			afterOrder.setOrderProductType(customOrder.getOrderProductType());
		}
		afterOrder.setReceiver(WebUtils.getCurrUserId());
		afterOrder.setPlaceOrder(WebUtils.getCurrUserId());
		afterOrder.setImprest(new BigDecimal(0));
		afterOrder.setRetainage(new BigDecimal(0));
		afterOrder.setEarnest(0);
		if (customOrderDto.getDesignFee() == null || customOrderDto.getDesignFee().equals("")) {
			afterOrder.setDesignFee(new BigDecimal(0));
		} else {
			afterOrder.setDesignFee(customOrderDto.getDesignFee());
		}
		afterOrder.setMarketPrice(new BigDecimal(0));
		afterOrder.setDiscounts(new BigDecimal(0));
		if (customOrderDto.getFactoryDiscounts() == null || customOrderDto.getFactoryDiscounts().equals("")) {
			afterOrder.setFactoryDiscounts(new BigDecimal(0));
		} else {
			afterOrder.setFactoryDiscounts(customOrderDto.getFactoryDiscounts());
		}
		if (customOrderDto.getFactoryFinalPrice() == null || customOrderDto.getFactoryFinalPrice().equals("")) {
			afterOrder.setFactoryFinalPrice(new BigDecimal(0.00));
		} else {
			afterOrder.setFactoryFinalPrice(customOrderDto.getFactoryFinalPrice());
		}
		//出厂价必填
		if (customOrderDto.getFactoryPrice() == null) {
			afterOrder.setFactoryPrice(new BigDecimal(0.00));
		} else {
			afterOrder.setFactoryPrice(customOrderDto.getFactoryPrice());
		}
		//是否代扣
		if(customOrderDto.getPaymentWithholding()==null||customOrderDto.getPaymentWithholding().equals("")){
			afterOrder.setPaymentWithholding(0);
		}
		afterOrder.setBranchId(WebUtils.getCurrBranchId());
		if(customOrderDto.getCharge()==null||customOrderDto.getCharge().equals("")){
			afterOrder.setCharge("1");
		}else {
			afterOrder.setCharge(customOrderDto.getCharge());
		}

		if (customOrderDto.getFreeAmount() == null||customOrderDto.getFreeAmount().equals("")) {
			afterOrder.setFreeAmount(new BigDecimal(0.00));
		} else {
			afterOrder.setFreeAmount(customOrderDto.getFreeAmount());
		}
		afterOrder.setFreeNotes(customOrderDto.getFreeNotes());
		afterOrder.setAftersaleNotes(customOrderDto.getAftersaleNotes());
		afterOrder.setAmount(new BigDecimal(0));
		afterOrder.setType(OrderType.SUPPLEMENTORDER.getValue());
		afterOrder.setConfirmFprice(false);
		afterOrder.setConfirmCprice(false);
		afterOrder.setEstimatedDeliveryDate(null);
		afterOrder.setCoordination(CustomOrderCoordination.UNWANTED_COORDINATION.getValue());
		afterOrder.setStatus(OrderStatus.TO_PAID.getValue());
		afterOrder.setCommitTime(DateUtil.getSystemDate());
		afterOrder.setCreated(DateUtil.getSystemDate());
		afterOrder.setCreator(WebUtils.getCurrUserId());
		afterOrder.setState(1);
		afterOrder.setCommitTime(DateUtil.getSystemDate());
		afterOrder.setConsigneeName(customOrderDto.getConsigneeName());
		afterOrder.setConsigneeTel(customOrderDto.getConsigneeTel());
		afterOrder.setFlag(0);
		afterOrder.setAfterPerson(customOrderDto.getAfterPerson());
		afterOrder.setAfterDept(customOrderDto.getAfterDept());
		this.customOrderService.add(afterOrder);
		//售后订单产品
		List<OrderProductDto> orderProducts = customOrderDto.getOrderProductDtoList();
		this.saveOrderProducts(orderProducts,customOrderDto.getNo() , afterOrder.getId());
		//支付记录信息生成
		Payment payment = new Payment();
		String userId = WebUtils.getCurrUserId();
		String userName = AppBeanInjector.userService.findByUserId(userId).getName();
		payment.setHolder(userName);
		payment.setNo(AppBeanInjector.uniquneCodeGenerator.getNextNo(UniquneCodeGenerator.UniqueResource.PAYMENT_NO));
		payment.setAmount(afterOrder.getFactoryFinalPrice());
		payment.setCompanyId(afterOrder.getCompanyId());
		payment.setStatus(PaymentStatus.PENDING_PAYMENT.getValue());
		payment.setCreated(DateUtil.getSystemDate());
		payment.setCreator(WebUtils.getCurrUserId());
		payment.setFunds(PaymentFunds.ORDER_FEE_CHARGE.getValue());
		payment.setWay(PaymentWay.DEALER_ACCOUNT.getValue());
		payment.setType(PaymentTypeNew.CHARGEBACK.getValue());
		payment.setCustomOrderId(afterOrder.getId());
		payment.setBranchId(WebUtils.getCurrBranchId());
		payment.setResourceType(PaymentResourceType.AFTERSALEAPPLY.getValue());
		payment.setFlag(0);
		this.paymentService.add(payment);
		//记录操作日志
		CustomOrderLog log = new CustomOrderLog();
		log.setCreated(new Date());
		log.setCreator(WebUtils.getCurrUserId());
		log.setName("接单");
		log.setStage(OrderStage.RECEIPT.getValue());
		log.setContent("售后订单创建，订单号：" + afterOrder.getNo());
		log.setCustomOrderId(afterOrder.getId());
		customOrderLogService.add(log);
		return ResultFactory.generateRequestResult(this.customOrderService.findByOrderId(afterOrder.getId()));
	}

	@Override
	public RequestResult findDeliverGoodsList(String orderNo, String dealerName, String deliverStatus,String logisticsNo,  Integer pageSize, Integer pageNum) {
		MapContext params=MapContext.newOne();
		//企业ID
		String branchId=WebUtils.getCurrBranchId();
		if(branchId==null){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED,AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
		}
		params.put("branchId",branchId);
		if(orderNo!=null&&!orderNo.equals("")){
			params.put("orderNo",orderNo);
		}
		if(dealerName!=null&&!dealerName.equals("")){
			params.put("dealerName",dealerName);
		}
		if(deliverStatus!=null&&!deliverStatus.equals("")){
			params.put("deliverStatus",deliverStatus);
		}
		if(logisticsNo!=null&&!logisticsNo.equals("")){
			params.put("logisticsNo",logisticsNo);
		}
		PaginatedFilter filter = PaginatedFilter.newOne();
		Pagination pagination = Pagination.newOne();
		pagination.setPageSize(pageSize);
		pagination.setPageNum(pageNum);
		filter.setPagination(pagination);
		filter.setFilters(params);
		PaginatedList<CustomOrderDto> orderDtoPaginatedList=this.customOrderService.findDeliverGoodsList(filter);
		MapContext map=MapContext.newOne();
		for(CustomOrderDto customOrderDto:orderDtoPaginatedList.getRows()){
			String orderId=customOrderDto.getId();
			//产品数量
			List<OrderProductDto> listByOrderId = this.orderProductService.findListByOrderId(orderId);
			map.put("orderId",orderId);
			//部件数量
			List<Map> byOrderId = this.produceOrderService.findByOrderId(orderId);
			//已入库包裹数量
			//map.put("hasInput",1);
			Integer hasInputProduceNum=this.produceOrderService.findAllProduceNum(map);
			//物流单号
			List<String> findLogisticsNos=this.produceOrderService.findLogisticsNobyOrderId(orderId);
			if(listByOrderId!=null) {
				customOrderDto.setOrderProductNum(listByOrderId.size());
			}else {
				customOrderDto.setOrderProductNum(0);
			}
			customOrderDto.setAllProduceNum(byOrderId.size());
			customOrderDto.setHasInputProduceNum(hasInputProduceNum);
			customOrderDto.setLogisticsNos(findLogisticsNos);
		}
		return ResultFactory.generateRequestResult(orderDtoPaginatedList);
	}

	@Override
	public RequestResult findDeliveMessage(String orderIds) {
		List result=new ArrayList();
		String[] orderIdList=orderIds.split(",");
		if(orderIdList==null||orderIdList.length==0){
			return ResultFactory.generateResNotFoundResult();
		}
		//如果选择的订单中包含追加单，并且有两个或以上的追加单，属于同一个订单，则需要利用去重，筛选出不重复的源订单
		HashSet<String> orderIdSetList=new HashSet();
		for(String orderId:orderIdList){
			CustomOrderDto byOrderId = this.customOrderService.findByOrderId(orderId);
			if(byOrderId.getType()==OrderType.APPENDORDER.getValue()){
				orderId=byOrderId.getParentId();
			}
			orderIdSetList.add(orderId);
		}
		//此时orderIdSetList内全部为源订单id
		for(String orderId:orderIdSetList){
			List<String> appendOrderNos=new ArrayList<>();
			CustomOrderDto byOrderId = this.customOrderService.findByOrderId(orderId);
			Boolean coordination = byOrderId.getCoordination();
			if(coordination){
			    byOrderId.setCoordinationValue("1");
			}else {
				byOrderId.setCoordinationValue("0");
			}
			    //经销商默认物流信息
			    String companyId=byOrderId.getCompanyId();
			WxDealerLogisticsDto defaultDto = this.dealerLogisticsService.findDefaultDto(companyId);
			String logisticsName="";
			String logisticsCompanyId="";
			if(defaultDto!=null){
				logisticsName = defaultDto.getLogisticsName();
				logisticsCompanyId = defaultDto.getLogisticsCompanyId();
			}
			byOrderId.setDealerLogistics(logisticsName);
			byOrderId.setDealerLogisticsId(logisticsCompanyId);
			//产品数量
				List<OrderProductDto> listByOrderId = this.orderProductService.findListByOrderId(orderId);
				//订单产品信息
				List<OrderProductDto> orderProductDtos = this.orderProductService.findListByOrderId(orderId);
				byOrderId.setOrderProductDtoList(orderProductDtos);
				//追加单信息
				MapContext params=MapContext.newOne();
				params.put("parentId",orderId);
				params.put("type",OrderType.APPENDORDER.getValue());
				List<CustomOrder> byParentIdAndType = this.customOrderService.findByParentIdAndType(params);
				List<OrderProductDto> appendProductDtos=new ArrayList<>();
				Integer appendProductNum=0;
				for(CustomOrder order:byParentIdAndType){
					String no=order.getNo();
					appendOrderNos.add(no);
					String appendOrderId=order.getId();
					//追加单产品信息
					List<OrderProductDto> appendProducts = this.orderProductService.findListByOrderId(appendOrderId);
                    appendProductNum=appendProductNum+appendProducts.size();
					appendProductDtos.addAll(appendProducts);
				}
				if(byParentIdAndType!=null&&byParentIdAndType.size()>0){
					byOrderId.setOrderProductNum(listByOrderId.size()+appendProductNum);
				}else {
					byOrderId.setOrderProductNum(listByOrderId.size());
				}
				byOrderId.setAppendProductDtoList(appendProductDtos);
				byOrderId.setAppendOrderNos(appendOrderNos);//追加单编号
				result.add(byOrderId);
			}

		return ResultFactory.generateRequestResult(result);
	}




	/**
	 * 成品单打印信息
	 *
	 * @param id
	 * @return
	 */
	@Override
	public RequestResult findOrderProductPrintTable(String id) {
		//源订单信息
		MapContext mapContext = MapContext.newOne();
		mapContext.put("orderId", id);
		mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID, WebUtils.getCurrBranchId());
		OrderPrintTableDto orderPrintTableDto = this.customOrderService.findOrderPrintTable(mapContext);
		if (orderPrintTableDto == null) {
			orderPrintTableDto = this.aftersaleApplyService.findOrderPrintTable(mapContext);
		}
		//产品信息
		List<OrderProductDto> orderProductDtos = this.orderProductService.findProductsByOrderId(id);
		orderPrintTableDto.setOrderProductDtos(orderProductDtos);

		return ResultFactory.generateRequestResult(orderPrintTableDto);
	}

	/**
	 * 添加售后订单
	 *
	 * @param customOrderDto
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addFactoryAftersaleOrder(CustomOrderDto customOrderDto) {
		CustomOrder afterOrder = new CustomOrder();
		String afterProductId = customOrderDto.getAftersaleProductId();
		OrderProductDto oneById = new OrderProductDto();
        Integer aftersaleNum=1;
		if (afterProductId != null) {
			oneById = this.orderProductService.findOneById(afterProductId);
            MapContext map=MapContext.newOne();
            if(oneById.getAftersaleNum()!=null){
                aftersaleNum=oneById.getAftersaleNum()+1;
            }
            map.put("aftersaleNum",aftersaleNum);
            map.put("id",afterProductId);
            this.orderProductService.updateByMapContext(map);
		}

		String productNo = null;
		//判断父级ID（源订单ID）是否存在
		if (customOrderDto.getParentId() == null) {
			String noValue = "";
			String orderNo = "";
			//查询订单编号前缀
			Basecode byTypeAndCode = AppBeanInjector.basecodeService.findByTypeAndCode("orderProductType", customOrderDto.getOrderProductType().toString());
			noValue = AppBeanInjector.uniquneCodeGenerator.getNoByTime(DateUtil.getSystemDate(), byTypeAndCode.getRemark());
			orderNo = byTypeAndCode.getRemark() + noValue;
			afterOrder.setNo(orderNo);
			afterOrder.setOldNo(customOrderDto.getNo());
			afterOrder.setCityAreaId(customOrderDto.getCityAreaId());
			afterOrder.setAddress(customOrderDto.getAddress());
			afterOrder.setMerchandiser(customOrderDto.getMerchandiser());
			afterOrder.setParentId(customOrderDto.getParentId());
			afterOrder.setBusinessManager(customOrderDto.getBusinessManager());
			afterOrder.setOrderProductType(customOrderDto.getOrderProductType());
			afterOrder.setReceiptTime(DateUtil.getSystemDate());
			Company company = this.companyService.findById(customOrderDto.getCompanyId());
			//判断经销商是否存在 状态是否是正常
			if (company == null || !company.getStatus().equals(CompanyStatus.NORMAL.getValue())) {
				return ResultFactory.generateResNotFoundResult();
			}
			//判断客户是否存在
			MapContext filter = new MapContext();
			filter.put(WebConstant.KEY_ENTITY_NAME, customOrderDto.getCustomerName());
			filter.put(WebConstant.KEY_ENTITY_COMPANY_ID, company.getId());
			List<CompanyCustomer> customerByMap = this.companyCustomerService.findCustomerByMap(filter);
			if (customerByMap == null || customerByMap.size() == 0) {
				CompanyCustomer companyCustomer = new CompanyCustomer();
				companyCustomer.setName(customOrderDto.getCustomerName());
				companyCustomer.setCompanyId(company.getId());
				companyCustomer.setCreated(DateUtil.getSystemDate());
				companyCustomer.setCreator(WebUtils.getCurrUserId());
				companyCustomer.setStatus(CompanyCustomerStatus.ORDER.getValue());
				this.companyCustomerService.add(companyCustomer);
				afterOrder.setCustomerId(companyCustomer.getId());
			} else {
				afterOrder.setCustomerId(customerByMap.get(0).getId());
			}
			afterOrder.setCompanyId(customOrderDto.getCompanyId());
			afterOrder.setOldNo(customOrderDto.getNo());
		} else {
			//查询源订单信息
			CustomOrder customOrder = this.customOrderService.findById(customOrderDto.getParentId());
			if (customOrder == null) {
				return ResultFactory.generateResNotFoundResult();
			}
			//查询订单产品的售后次数
			List<CustomOrder> list = this.customOrderService.findByParentId(customOrderDto.getParentId());
			productNo = oneById.getNo();
			List<Basecode> produceOrderType = this.basecodeService.findByTypeAndDelFlag("produceOrderType", 0);
			Basecode orderProductType = this.basecodeService.findByTypeAndCode("orderProductType", oneById.getType().toString());
			for(Basecode basecode:produceOrderType) {
					String remark=basecode.getRemark();
					productNo = productNo.replace(remark, "");
					if(remark.equals(orderProductType.getRemark())){
						productNo = orderProductType.getRemark() + productNo;
					}
				}
//			productNo = productNo.replace("A", "");
//			productNo = productNo.replace("B", "");
//			productNo = productNo.replace("C", "");
			String afterOrderNo = null;
			if (list == null || list.size() == 0) {
				afterOrderNo = "补(1)" + customOrder.getNo();
			} else {
				afterOrderNo = "补(" + (list.size() +1)+ ")" + customOrder.getNo();
			}
			afterOrder.setNo(afterOrderNo);
			afterOrder.setOldNo(customOrder.getNo());
			afterOrder.setCityAreaId(customOrder.getCityAreaId());
			afterOrder.setAddress(customOrder.getAddress());
			afterOrder.setMerchandiser(customOrder.getMerchandiser());
			afterOrder.setCustomerId(customOrder.getCustomerId());
			afterOrder.setCompanyId(customOrder.getCompanyId());
			afterOrder.setParentId(customOrderDto.getParentId());
			afterOrder.setBusinessManager(customOrder.getBusinessManager());
			afterOrder.setOrderProductType(customOrder.getOrderProductType());
		}
		afterOrder.setReceiver(WebUtils.getCurrUserId());
		afterOrder.setPlaceOrder(WebUtils.getCurrUserId());
		afterOrder.setImprest(new BigDecimal(0));
		afterOrder.setRetainage(new BigDecimal(0));
		afterOrder.setEarnest(0);
		if (customOrderDto.getDesignFee() == null || customOrderDto.getDesignFee().equals("")) {
			afterOrder.setDesignFee(new BigDecimal(0));
		} else {
			afterOrder.setDesignFee(customOrderDto.getDesignFee());
		}
		afterOrder.setMarketPrice(new BigDecimal(0));
		afterOrder.setDiscounts(new BigDecimal(0));
		if (customOrderDto.getFactoryDiscounts() == null || customOrderDto.getFactoryDiscounts().equals("")) {
			afterOrder.setFactoryDiscounts(new BigDecimal(0));
		} else {
			afterOrder.setFactoryDiscounts(customOrderDto.getFactoryDiscounts());
		}
		if (customOrderDto.getFactoryFinalPrice() == null || customOrderDto.getFactoryFinalPrice().equals("")) {
			afterOrder.setFactoryFinalPrice(new BigDecimal(0.00));
		} else {
			afterOrder.setFactoryFinalPrice(customOrderDto.getFactoryFinalPrice());
		}
		//出厂价必填
		if (customOrderDto.getFactoryPrice() == null) {
			afterOrder.setFactoryPrice(new BigDecimal(0.00));
		} else {
			afterOrder.setFactoryPrice(customOrderDto.getFactoryPrice());
		}
		afterOrder.setBranchId(WebUtils.getCurrBranchId());
		afterOrder.setCharge(customOrderDto.getCharge());
		afterOrder.setAftersaleNotes(customOrderDto.getAftersaleNotes());
		afterOrder.setAmount(new BigDecimal(0));
		afterOrder.setType(OrderType.SUPPLEMENTORDER.getValue());
		afterOrder.setConfirmFprice(false);
		afterOrder.setConfirmCprice(false);
		afterOrder.setEstimatedDeliveryDate(null);
		afterOrder.setCoordination(CustomOrderCoordination.UNWANTED_COORDINATION.getValue());
		afterOrder.setStatus(OrderStatus.TO_PAID.getValue());
		afterOrder.setCommitTime(DateUtil.getSystemDate());
		afterOrder.setCreated(DateUtil.getSystemDate());
		afterOrder.setCreator(WebUtils.getCurrUserId());
		afterOrder.setState(1);
		afterOrder.setCommitTime(DateUtil.getSystemDate());
		afterOrder.setConsigneeName(customOrderDto.getConsigneeName());
		afterOrder.setConsigneeTel(customOrderDto.getConsigneeTel());
		afterOrder.setFlag(0);
		afterOrder.setAfterPerson(customOrderDto.getAfterPerson());
		afterOrder.setAfterDept(customOrderDto.getAfterDept());
		this.customOrderService.add(afterOrder);
		//售后订单产品
		List<OrderProductDto> orderProducts = customOrderDto.getOrderProductDtoList();
		Boolean ishaveId = true;
		String noValue = null;
		if (customOrderDto.getParentId() != null) {
			noValue = "补(" + aftersaleNum + ")" + productNo;
		} else {
			noValue = afterOrder.getNo();
			ishaveId = false;
		}
		this.saveOrderProducts2(orderProducts, noValue, afterOrder.getId(), ishaveId);
		//支付记录信息生成
		Payment payment = new Payment();
		String userId = WebUtils.getCurrUserId();
		String userName = AppBeanInjector.userService.findByUserId(userId).getName();
		payment.setHolder(userName);
		payment.setNo(AppBeanInjector.uniquneCodeGenerator.getNextNo(UniquneCodeGenerator.UniqueResource.PAYMENT_NO));
		payment.setAmount(afterOrder.getFactoryFinalPrice());
		payment.setCompanyId(afterOrder.getCompanyId());
		payment.setStatus(PaymentStatus.PENDING_PAYMENT.getValue());
		payment.setCreated(DateUtil.getSystemDate());
		payment.setCreator(WebUtils.getCurrUserId());
		payment.setFunds(PaymentFunds.ORDER_FEE_CHARGE.getValue());
		payment.setWay(PaymentWay.DEALER_ACCOUNT.getValue());
		payment.setType(PaymentTypeNew.CHARGEBACK.getValue());
		payment.setCustomOrderId(afterOrder.getId());
		payment.setBranchId(WebUtils.getCurrBranchId());
		payment.setResourceType(PaymentResourceType.AFTERSALEAPPLY.getValue());
		payment.setFlag(0);
		this.paymentService.add(payment);
		//记录操作日志
		CustomOrderLog log = new CustomOrderLog();
		log.setCreated(new Date());
		log.setCreator(WebUtils.getCurrUserId());
		log.setName("接单");
		log.setStage(OrderStage.RECEIPT.getValue());
		log.setContent("售后订单创建，订单号：" + afterOrder.getNo());
		log.setCustomOrderId(afterOrder.getId());
		customOrderLogService.add(log);
		return ResultFactory.generateRequestResult(this.customOrderService.findByOrderId(afterOrder.getId()));
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updateProducts(String id, List<MapContext> mapContexts) {
		//判断订单是否存在
		CustomOrder order = this.customOrderService.findByOrderId(id);
		//
		if (order == null) {
			return ResultFactory.generateResNotFoundResult();
		}
		List<OrderProductDto> list = this.orderProductService.findListByOrderId(id);
		//删除原有的产品,产品属性和产品部件
		for (OrderProductDto orderProductDto : list) {
			String productId = orderProductDto.getId();
			//删除属性值
			List<OrderProductAttribute> productAttributes=this.orderProductAttributeService.findListByProductId(productId);
			if(productAttributes!=null&&productAttributes.size()>0){
				for(OrderProductAttribute attribute:productAttributes) {
					this.orderProductAttributeService.deleteById(attribute.getId());
				}
			}
			//删除部件
			List<OrderProductParts> partsList = this.orderProductPartsService.findByProductId(productId);
			for (OrderProductParts orderProductParts : partsList) {
				this.orderProductPartsService.deleteById(orderProductParts.getId());
			}
			//删除产品
			this.orderProductService.deleteById(productId);
		}
		//添加新的产品和部件
		CustomOrder customOrder = this.customOrderService.findByOrderId(id);
		int prodCount = 1;
		for (MapContext map : mapContexts) {
			//添加产品
			OrderProduct orderProduct = new OrderProduct();
			orderProduct.setCreator(customOrder.getCreator());
			orderProduct.setCreated(customOrder.getCreated());
			orderProduct.setCustomOrderId(id);
			orderProduct.setAftersaleNum(0);
			String prodNo = String.format("%02d", prodCount);
			String productNo = null;
			if (mapContexts.size() == 1) {
				if (map.getTypedValue("type", Integer.class).equals(OrderProductType.CUPBOARD.getValue())) {
					productNo = customOrder.getNo();
				} else {
					productNo = customOrder.getNo() + "-" + prodNo;
				}
			} else {
				productNo = customOrder.getNo() + "-" + prodNo;
			}
			orderProduct.setNo(productNo);
			prodCount++;
			if (map.getTypedValue("series", String.class).equals("")) {
				orderProduct.setSeries(null);
			} else {
				orderProduct.setSeries(Integer.valueOf(map.getTypedValue("series", String.class)));
			}
//			if(map.containsKey("bodyTec")) {
//				if (map.getTypedValue("bodyTec", String.class).equals("")) {
//					orderProduct.setBodyTec(null);
//				}
//			}else {
//				orderProduct.setBodyTec(map.getTypedValue("bodyTec", String.class));
//			}
			orderProduct.setNotes(map.getTypedValue("notes", String.class));
			orderProduct.setUpdateTime(DateUtil.getSystemDate());
			orderProduct.setUpdateUser(WebUtils.getCurrUserId());
			orderProduct.setDoor(map.getTypedValue("door", String.class));
			orderProduct.setDoorColor(map.getTypedValue("doorColor", String.class));
			orderProduct.setType(map.getTypedValue("type", Integer.class));
			orderProduct.setPrice(map.getTypedValue("price", BigDecimal.class));
			orderProduct.setBodyColor(map.getTypedValue("bodyColor", String.class));
			orderProduct.setInstall(map.getTypedValue("install", String.class));
			orderProduct.setStatus(OrderProductStatus.TO_PAYMENT.getValue());
			orderProduct.setPartStock(0);
			orderProduct.setAftersaleNum(0);
			orderProduct.setFlag(0);
			this.orderProductService.add(orderProduct);
			//添加产品属性
			List<Map> productAttributeValues = map.getTypedValue("productAttributeValues",List.class);
			if(productAttributeValues!=null&&productAttributeValues.size()>0){
				for(Map mapContext:productAttributeValues) {
					//查询属性值是否存在
					String keyId=mapContext.get("productAttributeKeyId").toString();
					String value=mapContext.get("valueName").toString();
					String keyName=mapContext.get("keyName").toString();
					OrderProductAttribute attributeValue=new OrderProductAttribute();
					attributeValue.setOrderProductId(orderProduct.getId());
					attributeValue.setProductAttributeKeyId(keyId);
					attributeValue.setKeyName(keyName);
					attributeValue.setValueName(value);
					MapContext map0=MapContext.newOne();
					map0.put("productAttributeKeyId",keyId);
					map0.put("attributeValue",value);
					map0.put("delFlag",0);
					ProductAttributeValue newone=this.productAttributeValueService.findByKeyIdAndValue(map0);
					if(newone==null){//不存在，则新建
						ProductAttributeValue productAttributeValue=new ProductAttributeValue();
						productAttributeValue.setProductAttributeKeyId(keyId);
						productAttributeValue.setStatus(1);
						productAttributeValue.setDelFlag(0);
						productAttributeValue.setAttributeValue(value);
						productAttributeValue.setCreator(WebUtils.getCurrUserId());
						productAttributeValue.setCreated(new Date());
						productAttributeValue.setKeyName(keyName);
						this.productAttributeValueService.add(productAttributeValue);

						attributeValue.setProductAttributeValueId(productAttributeValue.getId());
					}else {
						attributeValue.setProductAttributeValueId(newone.getId());
					}
					this.orderProductAttributeService.add(attributeValue);
				}
			}
			//添加部件
			String productParts = map.getTypedValue("productParts", String.class);
			if (productParts != null && !productParts.equals("")) {
				String[] partsIds = productParts.split(",");
				Boolean isCoordination = false;
				String type = "produceOrderType";
				for (String pId : partsIds) {
					OrderProductParts parts = new OrderProductParts();
					parts.setOrderProductId(orderProduct.getId());
					parts.setProductPartsId(pId);
					this.orderProductPartsService.add(parts);
					ProductParts parts1 = this.productPartsService.findById(pId);
					if (parts1 != null) {
						if (parts1.getProduceType() == ProduceOrderWay.COORDINATION.getValue()) {
							isCoordination = true;
						}
					}
					String code = parts1.getPartsType().toString();
					productNo = productNo + this.basecodeService.findByTypeAndCode(type, code).getRemark();

				}
				if (isCoordination) {
					MapContext updateOrder = new MapContext();
					updateOrder.put(WebConstant.KEY_ENTITY_ID, id);
					updateOrder.put("coordination", CustomOrderCoordination.NEED_COORDINATION.getValue());
					this.customOrderService.updateByMapContext(updateOrder);
				}
			}
			MapContext mapContext = MapContext.newOne();
			mapContext.put("id", orderProduct.getId());
			mapContext.put("no", productNo);
			this.orderProductService.updateByMapContext(mapContext);
			//添加部件
//			String partsIds = map.getTypedValue("productParts", String.class);
//			String[] ids = partsIds.split(",");
//			for (int i = 0; i < ids.length; i++) {
//				String partsId = ids[i];
//				OrderProductParts orderProductParts = new OrderProductParts();
//				orderProductParts.setOrderProductId(orderProduct.getId());
//				orderProductParts.setProductPartsId(partsId);
//				this.orderProductPartsService.add(orderProductParts);
//			}

		}
		return ResultFactory.generateSuccessResult();
	}

	@Override
	public RequestResult findExtendIntoNextMonth(Integer pageNum, Integer pageSize, MapContext mapContext) {
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		Pagination pagination = new Pagination();
		pagination.setPageNum(pageNum);
		pagination.setPageSize(pageSize);
		paginatedFilter.setPagination(pagination);
		mapContext.put("branchId", WebUtils.getCurrBranchId());
		mapContext.put("funds", PaymentFunds.ORDER_FEE_CHARGE.getValue());
		paginatedFilter.setFilters(mapContext);
		PaginatedList<CustomOrderDto> listByPaginateFilter = this.customOrderService.findExtendIntoNextMonth(paginatedFilter);
		if (listByPaginateFilter.getRows().size() > 0) {
			for (CustomOrderDto customOrderDto : listByPaginateFilter.getRows()) {
				String orderId = customOrderDto.getId();
				List<OrderProductDto> productDtos = this.orderProductService.findListByOrderId(orderId);
				customOrderDto.setOrderProductDtoList(productDtos);
			}
		}
		return ResultFactory.generateRequestResult(listByPaginateFilter);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult editExtendIntoNextMonth(String orderId) {
		CustomOrder customOrder = this.customOrderService.findByOrderId(orderId);
		if (customOrder == null) {
			return ResultFactory.generateResNotFoundResult();
		}
		Integer value = PaymentFunds.ORDER_FEE_CHARGE.getValue();
		MapContext params = MapContext.newOne();
		params.put("orderId", orderId);
		params.put("funds", value);
		PaymentDto payment = this.paymentService.findByOrderIdAndFunds(params);
		if (payment == null) {
			return ResultFactory.generateResNotFoundResult();
		}
		String orderType = null;
		if (customOrder.getOrderProductType() == 0) {
			orderType = "J";
		}
		if (customOrder.getOrderProductType() == 4) {
			orderType = "J";
		}
		if (customOrder.getOrderProductType() == 1) {
			orderType = "B";
		}
		if (customOrder.getOrderProductType() == 5) {
			orderType = "Y";
		}
		List<OrderProductDto> listByOrderId = this.orderProductService.findListByOrderId(orderId);
		Integer size = listByOrderId.size();
		Date created = customOrder.getCreated();
		Date audited = payment.getAudited();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		String createdValue = format.format(created);
		String auditedValue = format.format(audited);
		String newOrderNo = null;
		if (!createdValue.equals(auditedValue)) {
			if (auditedValue.equals(DateUtil.dateTimeToString(DateUtil.getSystemDate(), "yyyy-MM"))) {//如果处理时间和审核时间在同一个月
				newOrderNo = orderType + AppBeanInjector.uniquneCodeGenerator.getNoByTime(DateUtil.getSystemDate(), orderType);
			} else {//如果处理时间和审核时间不在一个月
				String no = orderType + DateUtil.dateTimeToString(audited, "yyMM");
				Integer num = this.customOrderService.findOrderNumByNo(no, WebUtils.getCurrBranchId());
				newOrderNo = orderType + DateUtil.dateTimeToString(audited, "yyMM") + "-" + String.format("%03d", num + 1);
			}
			if (listByOrderId.size() > 1) {
				newOrderNo = newOrderNo + "-" + size;
			}
			MapContext mapContext = MapContext.newOne();
			mapContext.put("no", newOrderNo);
			mapContext.put("oldNo", customOrder.getNo());
			mapContext.put("change", 1);
			mapContext.put("id", orderId);
			this.customOrderService.updateByMapContext(mapContext);
			//处理产品编号
			if (listByOrderId != null && listByOrderId.size() > 0) {
				int prodCount = 1;
				MapContext map = MapContext.newOne();
				for (OrderProductDto orderProductDto : listByOrderId) {
					//查询产品下的生产单已经部件标识
					List<ProduceOrder> listByProductId = this.produceOrderService.findListByProductId(orderProductDto.getId());
					String produceTypeValue = "";
					String type = "produceOrderType";
					for (ProduceOrder produceOrder : listByProductId) {
						String code = produceOrder.getType().toString();
						produceTypeValue = produceTypeValue + this.basecodeService.findByTypeAndCode(type, code).getRemark();
					}
					//修改单号
					map.put("id", orderProductDto.getId());
					map.put("oldNo", orderProductDto.getNo());
					map.put("change", 1);
					String prodNo = String.format("%02d", prodCount);
					if (produceTypeValue != null) {
						if (size == 1) {
							if (orderProductDto.getType() == OrderProductType.CUPBOARD.getValue()) {
								map.put("no", newOrderNo + produceTypeValue);
							} else {
								map.put("no", newOrderNo + "-" + prodNo + produceTypeValue);
							}
						} else if (size > 1) {
							map.put("no", newOrderNo + "-" + prodNo + produceTypeValue);
						}
					} else {
						if (size == 1) {
							if (orderProductDto.getType() == OrderProductType.CUPBOARD.getValue()) {
								map.put("no", newOrderNo);
							} else {
								map.put("no", newOrderNo + "-" + prodNo);
							}
						} else if (size > 1) {
							map.put("no", newOrderNo + "-" + prodNo);
						}
					}
					this.orderProductService.updateByMapContext(map);
					prodCount++;
					//修改生产单的单号
					Integer type2 = orderProductDto.getType();//产品类型
					for (ProduceOrder produceOrder : listByProductId) {
						Integer type1 = produceOrder.getType();//生产单类型
						Integer way = produceOrder.getWay();//生产单生产方式
						ProductParts parts = this.productPartsService.findByType(type2, type1, way);
						MapContext mapContext1 = MapContext.newOne();
						mapContext1.put("id", produceOrder.getId());
						mapContext1.put("no", orderProductDto.getNo() + parts.getOrderPartsIdentify());
						this.produceOrderService.updateByMapContext(mapContext1);

					}
				}
			}
		}
		return ResultFactory.generateSuccessResult();
	}

	@Override
	public RequestResult countLoginUserOrders(MapContext mapContext) {
		//订单总数和金额
		MapContext result = this.customOrderService.countLoginUserOrders(mapContext);
		//待报价订单数
		mapContext.remove("startTime");
		mapContext.remove("endTime");
		mapContext.put("status", OrderStatus.TO_QUOTED.getValue());
		Integer toBeOffer = this.customOrderService.countLoginToBeOffer(mapContext);
		//待生产订单数
		mapContext.put("status", OrderStatus.TO_PRODUCED.getValue());
		Integer toBeProduce = this.customOrderService.countLoginToBeOffer(mapContext);
		result.put("toBeOffer", toBeOffer);
		result.put("toBeProduce", toBeProduce);
		return ResultFactory.generateRequestResult(result);
	}

	private RequestResult deleteOrder(String orderId) {
		//判断订单是否存在
		CustomOrder customOrder = this.customOrderService.findById(orderId);
		if (customOrder == null) {
			return ResultFactory.generateSuccessResult();
		}
		//删除订单下的相关资源文件
		List<CustomOrderFiles> customOrderFiles = this.customOrderFilesService.selectByOrderId(orderId);
		this.customOrderFilesService.deleteByOrderId(orderId);
		List<UploadFiles> uploadFilesList = this.uploadFilesService.findByResourceId(orderId);
		this.uploadFilesService.deleteByResourceId(orderId);
		//删除订单下的设计记录
		this.customOrderDesignService.deleteByOrderId(orderId);
		//删除订单下的需求
		this.customOrderDemandService.deleteByOrderId(orderId);
		//删除订单下的订单产品
		this.orderProductService.deleteByOrderId(orderId);
		//删除订单下的生产流程明细
		this.produceFlowService.deleteByOrderId(orderId);
		//删除订单下的生产拆单
		this.produceOrderService.deleteByOrderId(orderId);
		//删除订单下的 发货条目信息
		this.dispatchBillItemService.deleteByOrderId(orderId);
		//删除订单下的 发货单 (发货单 和订单无关联 暂时不做 后果为 发货单下 包裹数量为 0)
		//删除订单下的包裹的配送计划
		this.dispatchBillPlanItemService.deleteByOrderId(orderId);
		//删除订单下包裹
		this.finishedStockItemService.deleteByOrderId(orderId);
		//删除订单下的成品库单
		this.finishedStockService.deleteByOrderId(orderId);
		//删除售后单资源文件
		List<AftersaleApplyFiles> aftersaleApplyFiles = this.aftersaleApplyFilesService.findListByOrderId(orderId);
		this.aftersaleApplyFilesService.deleteByOrderId(orderId);
		//删除售后单
		List<AftersaleApply> aftersaleApplies = this.aftersaleApplyService.findAftersaleListByOrderId(orderId);
		for (AftersaleApply aftersaleApply : aftersaleApplies) {
			if (!LwxfStringUtils.isBlank(aftersaleApply.getResultOrderId())) {
				this.deleteOrder(aftersaleApply.getResultOrderId());
			}
			this.aftersaleApplyService.deleteById(aftersaleApply.getId());
		}
		//删除订单报价修改记录
		this.orderAccountLogService.deleteByOrderId(orderId);
		//删除订单支付记录
		this.paymentService.deleteByOrderId(orderId);
		//删除订单关联的售后订单资源
		List<AftersaleApplyFiles> aftersaleApplyFilesList = this.aftersaleApplyFilesService.findListByResultOrderId(orderId);
		this.aftersaleApplyFilesService.deleteByResultOrderId(orderId);
		//删除售后单
		this.aftersaleApplyService.deleteByResultOrderId(orderId);
		//删除订单
		this.customOrderService.deleteById(orderId);
		for (CustomOrderFiles customOrderFile : customOrderFiles) {
			//清除本地文件
			AppBeanInjector.baseFileUploadComponent.deleteFileByDir(AppBeanInjector.configuration.getUploadFileRootDir().concat(customOrderFile.getPath()));
		}
		for (UploadFiles uploadFiles : uploadFilesList) {
			//清除本地文件
			AppBeanInjector.baseFileUploadComponent.deleteFileByDir(AppBeanInjector.configuration.getUploadFileRootDir().concat(uploadFiles.getPath()));
		}
		for (AftersaleApplyFiles aftersaleApplyFile : aftersaleApplyFiles) {
			//清除本地文件
			AppBeanInjector.baseFileUploadComponent.deleteFileByDir(AppBeanInjector.configuration.getUploadFileRootDir().concat(aftersaleApplyFile.getPath()));
		}
		for (AftersaleApplyFiles aftersaleApplyFile : aftersaleApplyFilesList) {
			//清除本地文件
			AppBeanInjector.baseFileUploadComponent.deleteFileByDir(AppBeanInjector.configuration.getUploadFileRootDir().concat(aftersaleApplyFile.getPath()));
		}
		return ResultFactory.generateSuccessResult();
	}

	public Integer getMoneyCount(MapContext mapContext, Integer low, Integer high) {
		mapContext.put("low", low);
		mapContext.put("high", high);
		Integer result = this.customOrderService.findOrderMoneyCount(mapContext);
		return result;
	}

	/**
	 * PC端经销商订单列表
	 * @param dealerId
	 * @param pageNum
	 * @param pageSize
	 * @param mapContext
	 * @return
	 */
	@Override
	public RequestResult findDealerOrderList(String dealerId, Integer pageNum, Integer pageSize, MapContext mapContext) {
		String currUserId = WebUtils.getCurrUserId();
		CompanyEmployee companyEmployee = this.companyEmployeeService.findOneByCompanyIdAndUserId(dealerId, currUserId);
		if (null == companyEmployee) {
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_NOT_ALLOW_OPERATION_10020, AppBeanInjector.i18nUtil.getMessage("BIZ_NOT_ALLOW_OPERATION_10020"));
		}
		PaginatedList<CustomOrderDto> customOrderPaginatedList;
		PaginatedFilter filter = PaginatedFilter.newOne();
		Pagination pagination = Pagination.newOne();
		pagination.setPageSize(pageSize);
		pagination.setPageNum(pageNum);
		mapContext.put("funds", PaymentFunds.ORDER_FEE_CHARGE.getValue());
		Map<String, String> created = new HashMap<String, String>();
		created.put(WebConstant.KEY_ENTITY_CREATED, "desc");
		List sort = new ArrayList();
		sort.add(created);
		filter.setSorts(sort);
		filter.setPagination(pagination);
		filter.setFilters(mapContext);
		//判断是不是店主或者经理，true-查询店铺下的所有订单，fales-查询店铺下业务员的订单
		String roleId = companyEmployee.getRoleId();
		//经理
		Role manager = this.roleService.selectByKey(DealerEmployeeRole.MANAGER.getValue(), WebUtils.getCurrBranchId());
		//店主
		Role shopkeeper = this.roleService.selectByKey(DealerEmployeeRole.SHOPKEEPER.getValue(), WebUtils.getCurrBranchId());
		if (roleId.equals(manager.getId()) || roleId.equals(shopkeeper.getId())) {
			customOrderPaginatedList = this.customOrderService.findListByPaginateFilter(filter);
		} else {
			mapContext.put("salesman", WebUtils.getCurrUserId());
			filter.setFilters(mapContext);
			customOrderPaginatedList = this.customOrderService.findListByPaginateFilter(filter);
		}
		//查询家居顾问，设计师，安装工信息
		for (CustomOrderDto customOrderDto:customOrderPaginatedList.getRows()) {
			List<CompanyShareMember> shareMembers = this.companyShareMemberService.findByOrderId(customOrderDto.getId());
			if (shareMembers != null && shareMembers.size() > 0) {
				for (CompanyShareMember shareMember : shareMembers) {
					if (shareMember.getIdentity() == 0) {
						customOrderDto.setHouseholdConsultant(shareMember.getName());
					} else if (shareMember.getIdentity() == 1) {
						customOrderDto.setDealerDesigner(shareMember.getName());
					} else if (shareMember.getIdentity() == 2) {
						customOrderDto.setDealerErector(shareMember.getName());
					}
				}
			}
			//产品数量
			String orderId = customOrderDto.getId();
			List<OrderProductDto> productDtos = this.orderProductService.findListByOrderId(orderId);
			customOrderDto.setOrderProductDtoList(productDtos);
			if(productDtos!=null){
				customOrderDto.setOrderProductNum(productDtos.size());
			}else {
				customOrderDto.setOrderProductNum(0);
			}

			//剩余交付时间
			Date estimatedDeliveryDate = customOrderDto.getEstimatedDeliveryDate();//预计交货时间
			Date deliveryDate = customOrderDto.getDeliveryDate();//实际交货时间
			Date systemDate = DateUtil.getSystemDate();//系统当前时间
			if (deliveryDate != null) {//实际交货时间不为空，则订单生产已结束
				customOrderDto.setTimeRemaining("00:00:00");
				customOrderDto.setTimeRemainingStatus(0);
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
						if (day > 1) {
							customOrderDto.setTimeRemainingStatus(1);
						} else if (day < 1) {
							customOrderDto.setTimeRemainingStatus(2);
						}
					} else {
						customOrderDto.setTimeRemainingStatus(3);
						long diffValue = systemDateTime - estimatedDeliveryDateValue;
						long day = diffValue / nd;//计算超期多少天
						long hour = diffValue % nd / nh;//计算超期多少小时
						long min = diffValue % nd % nh / nm;//计算超期多少分钟
						String residueTime = "-" + day + "天" + hour + "小时" + min + "分钟";
						customOrderDto.setTimeRemaining(residueTime);
					}
				} else {
					customOrderDto.setTimeRemainingStatus(1);
					customOrderDto.setTimeRemaining("未付款不排期");
				}
			}
		}
		return ResultFactory.generateRequestResult(customOrderPaginatedList);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult dealerAddOrder(CustomOrderInfoDto customOrderInfoDto) {
		String companyId=WebUtils.getCurrCompanyId();
		Company company = this.companyService.findById(companyId);
		CustomOrderDto customOrder = customOrderInfoDto.getCustomOrder();
		customOrder.setCreated(DateUtil.getSystemDate());
		customOrder.setCreator(WebUtils.getCurrUserId());
		customOrder.setOrderSource(1);//订单来源：经销商
		customOrder.setCompanyId(companyId);
		String orderNo = null;
		String noValue = null;
//		//查询订单编号前缀
//		Basecode byTypeAndCode = AppBeanInjector.basecodeService.findByTypeAndCode("orderProductType", customOrder.getOrderProductType().toString());
//		noValue = AppBeanInjector.uniquneCodeGenerator.getNoByTime(DateUtil.stringToDate(customOrder.getOrderTime()), byTypeAndCode.getRemark());
//		orderNo = byTypeAndCode.getRemark() + noValue;
		customOrder.setNo(orderNo);
		customOrder.setImprest(new BigDecimal(0));
		customOrder.setRetainage(new BigDecimal(0));
		customOrder.setEarnest(0);
		if (customOrder.getMarketPrice() == null || customOrder.getMarketPrice().equals("")) {
			customOrder.setMarketPrice(new BigDecimal(0));
		}
		customOrder.setDiscounts(new BigDecimal(0));
		if (customOrder.getFactoryDiscounts() == null || customOrder.getFactoryDiscounts().equals("")) {
			customOrder.setFactoryDiscounts(new BigDecimal(0));
		}
		if (customOrder.getFactoryFinalPrice() == null || customOrder.getFactoryFinalPrice().equals("")) {
			customOrder.setFactoryFinalPrice(new BigDecimal(0));
		}
		if (customOrder.getDesignFee() == null || customOrder.getDesignFee().equals("")) {
			customOrder.setDesignFee(new BigDecimal(0));
		}
//		if (customOrder.getReceiver() == null || customOrder.getReceiver().equals("")) {
//			customOrder.setReceiver(WebUtils.getCurrUserId());
//		}
		if(customOrder.getState()!=null&&!customOrder.getState().equals("")){
			if(customOrder.getState()==1){
				customOrder.setLeafletTime(DateUtil.getSystemDate());
			}
		}
		customOrder.setFactoryPrice(customOrder.getMarketPrice());
		customOrder.setAmount(new BigDecimal(0));
		customOrder.setConfirmFprice(false);
		customOrder.setConfirmCprice(false);
		customOrder.setEstimatedDeliveryDate(null);
		customOrder.setCoordination(CustomOrderCoordination.UNWANTED_COORDINATION.getValue());
		customOrder.setChange(0);
		customOrder.setOrderProductType(customOrder.getOrderProductType());
		customOrder.setBranchId(WebUtils.getCurrBranchId());
		customOrder.setFlag(0);
		if (customOrder.getDesigner().equals("")) {
			customOrder.setDesigner(null);
		}

		customOrder.setBusinessManager(company.getBusinessManager());
		customOrder.setStatus(OrderStatus.TO_RECEIVE.getValue());
		//判断客户是否存在
		MapContext filter = new MapContext();
		filter.put(WebConstant.KEY_ENTITY_NAME, customOrderInfoDto.getCompanyCustomer().getName());
		filter.put(WebConstant.KEY_ENTITY_COMPANY_ID, companyId);
		List<CompanyCustomer> customerByMap = this.companyCustomerService.findCustomerByMap(filter);
		if (customerByMap == null || customerByMap.size() == 0) {
			CompanyCustomer companyCustomer = new CompanyCustomer();
			companyCustomer.setName(customOrderInfoDto.getCompanyCustomer().getName());
			companyCustomer.setCompanyId(companyId);
			companyCustomer.setCreated(DateUtil.getSystemDate());
			companyCustomer.setCreator(WebUtils.getCurrUserId());
			companyCustomer.setStatus(CompanyCustomerStatus.ORDER.getValue());
			this.companyCustomerService.add(companyCustomer);
			customOrder.setCustomerId(companyCustomer.getId());
		} else {
			customOrder.setCustomerId(customerByMap.get(0).getId());
		}
		this.customOrderService.add(customOrder);
		//查询家居顾问，设计师，安装工是否存在
		if(customOrder.getHouseholdConsultant()!=null&&!customOrder.getHouseholdConsultant().equals("")){//家居顾问
			String name=customOrder.getHouseholdConsultant();
			CompanyShareMember companyShareMember=this.companyShareMemberService.findByCidAndNameAndType(companyId,name,CompanyShareMemberIdentity.FURNITURECONSULTANT.getValue());
			if(companyShareMember==null){
				companyShareMember=new CompanyShareMember();
				companyShareMember.setCompanyId(companyId);
				companyShareMember.setCreated(DateUtil.getSystemDate());
				companyShareMember.setCreator(WebUtils.getCurrUserId());
				companyShareMember.setIdentity(CompanyShareMemberIdentity.FURNITURECONSULTANT.getValue());
				companyShareMember.setStatus(2);
				companyShareMember.setName(name);
				this.companyShareMemberService.add(companyShareMember);
			}
				CompanyMemberOrder companyMemberOrder=new CompanyMemberOrder();
				companyMemberOrder.setCustomeOrderId(customOrder.getId());
				companyMemberOrder.setCompanyShareMemberId(companyShareMember.getId());
			    companyMemberOrder.setIdentity(CompanyShareMemberIdentity.FURNITURECONSULTANT.getValue());
				this.companyMemberOrderService.add(companyMemberOrder);

		}
		if(customOrder.getDealerDesigner()!=null&&!customOrder.getDealerDesigner().equals("")){//设计师
			String name=customOrder.getDealerDesigner();
			CompanyShareMember companyShareMember=this.companyShareMemberService.findByCidAndNameAndType(companyId,name,CompanyShareMemberIdentity.DESIGNER.getValue());
			if(companyShareMember==null){
				companyShareMember=new CompanyShareMember();
				companyShareMember.setCompanyId(companyId);
				companyShareMember.setCreated(DateUtil.getSystemDate());
				companyShareMember.setCreator(WebUtils.getCurrUserId());
				companyShareMember.setIdentity(CompanyShareMemberIdentity.DESIGNER.getValue());
				companyShareMember.setStatus(2);
				companyShareMember.setName(name);
				this.companyShareMemberService.add(companyShareMember);
			}
			CompanyMemberOrder companyMemberOrder=new CompanyMemberOrder();
			companyMemberOrder.setCustomeOrderId(customOrder.getId());
			companyMemberOrder.setCompanyShareMemberId(companyShareMember.getId());
			companyMemberOrder.setIdentity(CompanyShareMemberIdentity.DESIGNER.getValue());
			this.companyMemberOrderService.add(companyMemberOrder);

		}
		if(customOrder.getDealerErector()!=null&&!customOrder.getDealerErector().equals("")){//安装工
			String name=customOrder.getDealerErector();
			CompanyShareMember companyShareMember=this.companyShareMemberService.findByCidAndNameAndType(companyId,name,CompanyShareMemberIdentity.ERECTOR.getValue());
			if(companyShareMember==null){
				companyShareMember=new CompanyShareMember();
				companyShareMember.setCompanyId(companyId);
				companyShareMember.setCreated(DateUtil.getSystemDate());
				companyShareMember.setCreator(WebUtils.getCurrUserId());
				companyShareMember.setIdentity(CompanyShareMemberIdentity.ERECTOR.getValue());
				companyShareMember.setStatus(2);
				companyShareMember.setName(name);
				this.companyShareMemberService.add(companyShareMember);
			}
			CompanyMemberOrder companyMemberOrder=new CompanyMemberOrder();
			companyMemberOrder.setCustomeOrderId(customOrder.getId());
			companyMemberOrder.setCompanyShareMemberId(companyShareMember.getId());
			companyMemberOrder.setIdentity(CompanyShareMemberIdentity.ERECTOR.getValue());
			this.companyMemberOrderService.add(companyMemberOrder);
		}
		//添加附件
		String orderFiles = customOrderInfoDto.getOrderFiles();
		String[] ids = orderFiles.split(",");
		for (String id : ids) {
			MapContext map = MapContext.newOne();
			map.put("id", id);
			map.put("customOrderId", customOrder.getId());
			map.put("status", 1);
			this.customOrderFilesService.updateByMapContext(map);
		}
		if(customOrder.getState()==1){
			//记录操作日志
			CustomOrderLog log = new CustomOrderLog();
			log.setCreated(new Date());
			log.setCreator(WebUtils.getCurrUserId());
			log.setName("传单");
			log.setStage(OrderStage.LEAFLET.getValue());
			log.setContent("订单传单成功");
			log.setCustomOrderId(customOrder.getId());
			customOrderLogService.add(log);
		}
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult dealerUpdateOrder(String id, MapContext mapContext) {
		//判断订单是否存在
		CustomOrderDto byOrderId = this.customOrderService.findByOrderId(id);
		if(byOrderId==null){
			return ResultFactory.generateResNotFoundResult();
		}
		//判断是否为确认收货操作
		if(mapContext.getTypedValue("status",String.class)==null||mapContext.getTypedValue("status",String.class).equals("")) {//非确认收货操作
			//判断订单状态，如果订单已提交，则无法修改
			if (byOrderId.getState() == 1) {
				return ResultFactory.generateErrorResult(ErrorCodes.SYS_EXECUTE_FAIL_00001, AppBeanInjector.i18nUtil.getMessage("SYS_EXECUTE_FAIL_00001"));
			}
		}
		if(mapContext.containsKey("state")){
			if(mapContext.getTypedValue("state",Integer.class)==1){
				mapContext.put("leafletTime",DateUtil.getSystemDate());
			}
		}
		//判断客户是否存在
		MapContext filter = new MapContext();
		filter.put(WebConstant.KEY_ENTITY_NAME, mapContext.getTypedValue("customeName",String.class));
		filter.put(WebConstant.KEY_ENTITY_COMPANY_ID, byOrderId.getCompanyId());
		List<CompanyCustomer> customerByMap = this.companyCustomerService.findCustomerByMap(filter);
		if (customerByMap == null || customerByMap.size() == 0) {
			CompanyCustomer companyCustomer = new CompanyCustomer();
			companyCustomer.setName(mapContext.getTypedValue("customeName",String.class));
			companyCustomer.setCompanyId(byOrderId.getCompanyId());
			companyCustomer.setCreated(DateUtil.getSystemDate());
			companyCustomer.setCreator(WebUtils.getCurrUserId());
			companyCustomer.setStatus(CompanyCustomerStatus.ORDER.getValue());
			this.companyCustomerService.add(companyCustomer);
			mapContext.put("customerId",companyCustomer.getId());
		} else {
			mapContext.put("customerId",customerByMap.get(0).getId());
		}
		mapContext.put("id",id);
		this.customOrderService.updateByMapContext(mapContext);
		//判断家居顾问，设计师，安装工是否修改
		if(mapContext.getTypedValue("householdConsultant",String.class)!=null&&!mapContext.getTypedValue("householdConsultant",String.class).equals("")) {
			String name = mapContext.getTypedValue("householdConsultant", String.class);
			//判断新名称是否和原名称一致
			CompanyMemberOrder memberOrder = this.companyMemberOrderService.findByOrderIdAndType(id, CompanyShareMemberIdentity.FURNITURECONSULTANT.getValue());
			String memberId = memberOrder.getCompanyShareMemberId();
			CompanyShareMember byId = this.companyShareMemberService.findById(memberId);
			String oldname = byId.getName();
			if (!oldname.equals(name)) {
				this.companyMemberOrderService.deleteById(memberOrder.getId());
				CompanyShareMember companyShareMember = this.companyShareMemberService.findByCidAndNameAndType(WebUtils.getCurrCompanyId(), name, CompanyShareMemberIdentity.FURNITURECONSULTANT.getValue());
				if (companyShareMember == null) {
					companyShareMember=new CompanyShareMember();
					companyShareMember.setCompanyId(WebUtils.getCurrCompanyId());
					companyShareMember.setCreated(DateUtil.getSystemDate());
					companyShareMember.setCreator(WebUtils.getCurrUserId());
					companyShareMember.setIdentity(CompanyShareMemberIdentity.FURNITURECONSULTANT.getValue());
					companyShareMember.setStatus(2);
					companyShareMember.setName(name);
					this.companyShareMemberService.add(companyShareMember);
				}
				CompanyMemberOrder companyMemberOrder = new CompanyMemberOrder();
				companyMemberOrder.setCustomeOrderId(id);
				companyMemberOrder.setCompanyShareMemberId(companyShareMember.getId());
				companyMemberOrder.setIdentity(CompanyShareMemberIdentity.FURNITURECONSULTANT.getValue());
				this.companyMemberOrderService.add(companyMemberOrder);
			}
		}
		if(mapContext.getTypedValue("dealerDesigner",String.class)!=null&&!mapContext.getTypedValue("dealerDesigner",String.class).equals("")) {
			String name = mapContext.getTypedValue("dealerDesigner", String.class);
			//判断新名称是否和原名称一致
			CompanyMemberOrder memberOrder = this.companyMemberOrderService.findByOrderIdAndType(id, CompanyShareMemberIdentity.DESIGNER.getValue());
			String memberId = memberOrder.getCompanyShareMemberId();
			CompanyShareMember byId = this.companyShareMemberService.findById(memberId);
			String oldname = byId.getName();
			if (!oldname.equals(name)) {
				this.companyMemberOrderService.deleteById(memberOrder.getId());
				CompanyShareMember companyShareMember = this.companyShareMemberService.findByCidAndNameAndType(WebUtils.getCurrCompanyId(), name, CompanyShareMemberIdentity.DESIGNER.getValue());
				if (companyShareMember == null) {
					companyShareMember=new CompanyShareMember();
					companyShareMember.setCompanyId(WebUtils.getCurrCompanyId());
					companyShareMember.setCreated(DateUtil.getSystemDate());
					companyShareMember.setCreator(WebUtils.getCurrUserId());
					companyShareMember.setIdentity(CompanyShareMemberIdentity.DESIGNER.getValue());
					companyShareMember.setStatus(2);
					companyShareMember.setName(name);
					this.companyShareMemberService.add(companyShareMember);
				}
				CompanyMemberOrder companyMemberOrder = new CompanyMemberOrder();
				companyMemberOrder.setCustomeOrderId(id);
				companyMemberOrder.setCompanyShareMemberId(companyShareMember.getId());
				companyMemberOrder.setIdentity(CompanyShareMemberIdentity.DESIGNER.getValue());
				this.companyMemberOrderService.add(companyMemberOrder);
			}
		}
		if(mapContext.getTypedValue("dealerErector",String.class)!=null&&!mapContext.getTypedValue("dealerErector",String.class).equals("")) {
			String name = mapContext.getTypedValue("dealerErector", String.class);
			//判断新名称是否和原名称一致
			CompanyMemberOrder memberOrder = this.companyMemberOrderService.findByOrderIdAndType(id, CompanyShareMemberIdentity.ERECTOR.getValue());
			String memberId = memberOrder.getCompanyShareMemberId();
			CompanyShareMember byId = this.companyShareMemberService.findById(memberId);
			String oldname = byId.getName();
			if (!oldname.equals(name)) {
				this.companyMemberOrderService.deleteById(memberOrder.getId());
				CompanyShareMember companyShareMember = this.companyShareMemberService.findByCidAndNameAndType(WebUtils.getCurrCompanyId(), name, CompanyShareMemberIdentity.ERECTOR.getValue());
				if (companyShareMember == null) {
					companyShareMember=new CompanyShareMember();
					companyShareMember.setCompanyId(WebUtils.getCurrCompanyId());
					companyShareMember.setCreated(DateUtil.getSystemDate());
					companyShareMember.setCreator(WebUtils.getCurrUserId());
					companyShareMember.setIdentity(CompanyShareMemberIdentity.ERECTOR.getValue());
					companyShareMember.setStatus(2);
					companyShareMember.setName(name);
					this.companyShareMemberService.add(companyShareMember);
				}
				CompanyMemberOrder companyMemberOrder = new CompanyMemberOrder();
				companyMemberOrder.setCustomeOrderId(id);
				companyMemberOrder.setCompanyShareMemberId(companyShareMember.getId());
				companyMemberOrder.setIdentity(CompanyShareMemberIdentity.ERECTOR.getValue());
				this.companyMemberOrderService.add(companyMemberOrder);
			}
		}
		//添加附件
		String addFileIds = mapContext.getTypedValue("addFileIds", String.class);
		if (addFileIds != null && !addFileIds.equals("")) {
			String[] ids = addFileIds.split(",");
			for (String fileid : ids) {
				MapContext mapContext1 = MapContext.newOne();
				mapContext1.put("id", fileid);
				mapContext1.put("customOrderId", id);
				mapContext1.put("status", 1);
				this.customOrderFilesService.updateByMapContext(mapContext1);
			}
		}
		//附件删除
		String deleteFileIds = mapContext.getTypedValue("deleteFileIds", String.class);
		if (deleteFileIds != null && !deleteFileIds.equals("")) {
			String[] ids = deleteFileIds.split(",");
			for (String fileid : ids) {
				this.customOrderFilesService.deleteById(fileid);
			}
		}
		return ResultFactory.generateSuccessResult();
	}

	@Override
	public RequestResult dealerFindOrderInfo(String id) {
		//订单信息
		CustomOrderDto byOrderId = this.customOrderService.findByOrderId(id);
		if(byOrderId==null){
			return ResultFactory.generateResNotFoundResult();
		}
		//累计用时
		long audit=0;
		if(byOrderId.getOrderSource()==0){//工厂创建，用接单时间
			Date receiptTime = byOrderId.getReceiptTime();
			if(receiptTime!=null&&!receiptTime.equals("")) {
				audit = receiptTime.getTime();
			}
		}else {//经销商创建，用传单时间
			Date leafletTime = byOrderId.getLeafletTime();
			if(leafletTime!=null&&!leafletTime.equals("")) {
				audit = leafletTime.getTime();
			}
		}
			//查询接单/传单时间，计算订单总用时
			if(audit!=0) {
				long nowTime = DateUtil.getSystemDate().getTime();
				if(byOrderId.getStatus()==OrderStatus.END.getValue()){
					nowTime=byOrderId.getDeliveryDate().getTime();
				}
				long diff = (nowTime - audit);
				long nd = 1000 * 24 * 60 * 60;//一天的毫秒数
				long nh = 1000 * 60 * 60;//一小时的毫秒数
				long nm = 1000 * 60;//一分钟的毫秒数
				long day = diff / nd;//计算用时多少天
				long hour = diff % nd / nh;//计算用时多少小时
				long min = diff % nd % nh / nm;//计算用时多少分钟
				String timeConsuming = day + "天" + hour + "小时" + min + "分钟";
				byOrderId.setTimeConsuming(timeConsuming);
			}else {
				byOrderId.setTimeConsuming("--");
		}
		//家居顾问，设计师，安装工
		CompanyMemberOrder furnitureconsultant = this.companyMemberOrderService.findByOrderIdAndType(id, CompanyShareMemberIdentity.FURNITURECONSULTANT.getValue());
		if(furnitureconsultant!=null){
			String memberId=furnitureconsultant.getCompanyShareMemberId();
			CompanyShareMember byId = this.companyShareMemberService.findById(memberId);
			byOrderId.setHouseholdConsultant(byId.getName());
		}
		CompanyMemberOrder designer = this.companyMemberOrderService.findByOrderIdAndType(id, CompanyShareMemberIdentity.DESIGNER.getValue());
		if(designer!=null){
			String memberId=designer.getCompanyShareMemberId();
			CompanyShareMember byId = this.companyShareMemberService.findById(memberId);
			byOrderId.setDealerDesigner(byId.getName());
		}
		CompanyMemberOrder erector = this.companyMemberOrderService.findByOrderIdAndType(id, CompanyShareMemberIdentity.ERECTOR.getValue());
		if(erector!=null){
			String memberId=erector.getCompanyShareMemberId();
			CompanyShareMember byId = this.companyShareMemberService.findById(memberId);
			byOrderId.setDealerErector(byId.getName());
		}
		//产品信息(内含生产单信息，物流信息)
		List<OrderProductDto> listByOrderId = this.orderProductService.findListByOrderId(id);
		byOrderId.setOrderProductDtoList(listByOrderId);
        //附件信息
		byOrderId.setOrderContractFiles(this.customOrderFilesService.selectByOrderIdAndType(id, CustomOrderFilesType.CONTRACT.getValue(), null));
		return ResultFactory.generateRequestResult(byOrderId);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addAfterOrder(AftersaleDto aftersaleDto) {
		aftersaleDto.setCreator(WebUtils.getCurrUserId());
		aftersaleDto.setCreated(DateUtil.getSystemDate());
		aftersaleDto.setStatus(AftersaleStatus.WAIT.getValue());
		aftersaleDto.setNo(AppBeanInjector.uniquneCodeGenerator.getNextNo(UniquneCodeGenerator.UniqueResource.AFTERSALE_APPLY_NO));
		aftersaleDto.setCharge(false);
		aftersaleDto.setChargeAmount(new BigDecimal("0.00"));
		aftersaleDto.setType(AftersaleType.BULIAO.getValue());
		aftersaleDto.setFromType(0);
		this.aftersaleApplyService.add(aftersaleDto);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult deleteDealerOrderInfo(String id) {
		CustomOrderDto byOrderId = this.customOrderService.findByOrderId(id);
		if(byOrderId==null){
			return ResultFactory.generateResNotFoundResult();
		}
		//已提交，不允许删除
		if(byOrderId.getState()==1){
			return ResultFactory.generateErrorResult(ErrorCodes.SYS_EXECUTE_FAIL_00001,AppBeanInjector.i18nUtil.getMessage("SYS_EXECUTE_FAIL_00001"));
		}
		//删除附件
		List<CustomOrderFiles> customOrderFiles = this.customOrderFilesService.selectByOrderId(id);
		for(CustomOrderFiles files:customOrderFiles){
			String fileid=files.getId();
			this.customOrderFilesService.deleteById(fileid);
		}

		//删除相关人员信息
		CompanyMemberOrder furnitureconsultant = this.companyMemberOrderService.findByOrderIdAndType(id, CompanyShareMemberIdentity.FURNITURECONSULTANT.getValue());
		if(furnitureconsultant!=null){
			String memberOrderId=furnitureconsultant.getId();
			this.companyMemberOrderService.deleteById(memberOrderId);
		}
		CompanyMemberOrder designer = this.companyMemberOrderService.findByOrderIdAndType(id, CompanyShareMemberIdentity.DESIGNER.getValue());
		if(designer!=null){
			String memberOrderId=designer.getId();
			this.companyMemberOrderService.deleteById(memberOrderId);
		}
		CompanyMemberOrder erector = this.companyMemberOrderService.findByOrderIdAndType(id, CompanyShareMemberIdentity.ERECTOR.getValue());
		if(erector!=null){
			String memberOrderId=erector.getId();
			this.companyMemberOrderService.deleteById(memberOrderId);
		}
		//删除订单
		this.customOrderService.deleteById(id);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult factoryUpdateOrderBack(String id) {
		CustomOrder byId = this.customOrderService.findById(id);
		if(byId==null){
			return ResultFactory.generateResNotFoundResult();
		}
		MapContext mapContext=MapContext.newOne();
		mapContext.put("status",OrderStatus.TO_RECEIVE.getValue());
		mapContext.put("id",id);
		this.customOrderService.updateByMapContext(mapContext);
		//删除下单日志
		List<CustomOrderLogDto> byOrderIdAndState = this.customOrderLogService.findByOrderIdAndState(id, OrderStage.PLACE_AN_ORDER.getValue());
		if(byOrderIdAndState.size()>0) {
			for (CustomOrderLogDto dto : byOrderIdAndState) {
				this.customOrderLogService.deleteById(dto.getId());
			}
		}
		return ResultFactory.generateSuccessResult();
	}

	@Override
	public boolean noDuplicateCheck(String no) {
		CustomOrder order=this.customOrderService.findOneByNo(no);
		if(order!=null){
			return true;
		}
		return false;
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult inputexcel(MultipartFile file) {

		try {
			List<String[]> strings = POIUtil.readExcel(file);
			if(strings.size()>0){
				for(String[] s:strings){
					if(s.length>0){
						CustomOrder customOrder=new CustomOrder();
						customOrder.setNo(s[1]);
						String dealerName=s[2];
						Company company=this.companyService.findCompanyByName(dealerName);
						if(company!=null) {
							customOrder.setCompanyId(company.getId());
						}
						customOrder.setCustomerId("62uvbqe8qc5c");
						customOrder.setConsigneeName(s[4]);
						customOrder.setConsigneeTel(s[5]);
						customOrder.setCityAreaId("130229");
						customOrder.setAddress("县城");
                        customOrder.setOrderProductType(7);
                        customOrder.setType(0);
                        customOrder.setFactoryFinalPrice(new BigDecimal(s[9]));
                        customOrder.setStatus(9);
                        customOrder.setCreator("53gy35d4wm4g");
                        customOrder.setCreated(new Date());
                        customOrder.setEarnest(0);
                        customOrder.setImprest(new BigDecimal("0"));
                        customOrder.setRetainage(new BigDecimal("0"));
                        customOrder.setAmount(new BigDecimal("0"));
						customOrder.setDesignFee(new BigDecimal("0"));
						customOrder.setFactoryPrice(new BigDecimal("0"));
						customOrder.setMarketPrice(new BigDecimal("0"));
						customOrder.setDiscounts(new BigDecimal("0"));
						customOrder.setFactoryDiscounts(new BigDecimal("0"));
						customOrder.setConfirmCprice(false);
						customOrder.setConfirmFprice(false);
						customOrder.setBranchId("40ord3va6adp");
						customOrder.setFlag(0);
						customOrder.setState(1);
						customOrder.setOrderSource(0);
						this.customOrderService.add(customOrder);

					}
				}
			}

			System.out.println(strings);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ResultFactory.generateRequestResult("导入成功");
	}

	@Override
	@Transactional(value = "transactionManager")
	public void inputpayment() {
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		MapContext mapContext=MapContext.newOne();
		Pagination pagination = new Pagination();
		pagination.setPageNum(1);
		pagination.setPageSize(2000);
		paginatedFilter.setPagination(pagination);
		mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID, WebUtils.getCurrBranchId());
		mapContext.put("funds", PaymentFunds.ORDER_FEE_CHARGE.getValue());
		paginatedFilter.setFilters(mapContext);
		PaginatedList<CustomOrderDto> listByPaginateFilter = this.customOrderService.findListByPaginateFilter(paginatedFilter);
		for(CustomOrderDto orderDto:listByPaginateFilter.getRows()){
			Payment payment = new Payment();
			payment.setWay(2);
			payment.setFunds(11);
			payment.setType(1);
			payment.setCreated(DateUtil.getSystemDate());
			payment.setCreator(WebUtils.getCurrUserId());
			payment.setPayee("4j1u3r1efshq");//bug 需要替换为常量
			payment.setStatus(1);
			payment.setCompanyId(orderDto.getCompanyId());
			payment.setHolder("");
			payment.setAmount(orderDto.getFactoryFinalPrice());
			payment.setBranchId(WebUtils.getCurrBranchId());
			payment.setAudited(DateUtil.getSystemDate());
			payment.setAuditor(WebUtils.getCurrUserId());
			payment.setNo(AppBeanInjector.uniquneCodeGenerator.getNextNo(UniquneCodeGenerator.UniqueResource.PAYMENT_NO));
			payment.setFlag(0);
			paymentService.add(payment);

			Payment payment2 = new Payment();
			payment2.setWay(2);
			payment2.setFunds(31);
			payment2.setType(3);
			payment2.setCreated(DateUtil.getSystemDate());
			payment2.setCreator(WebUtils.getCurrUserId());
			payment2.setPayee("4j1u3r1efshq");//bug 需要替换为常量
			payment2.setStatus(1);
			payment2.setCompanyId(orderDto.getCompanyId());
			payment2.setHolder("");
			payment2.setAmount(orderDto.getFactoryFinalPrice());
			payment2.setBranchId(WebUtils.getCurrBranchId());
			payment2.setAudited(DateUtil.getSystemDate());
			payment2.setAuditor(WebUtils.getCurrUserId());
			payment2.setNo(AppBeanInjector.uniquneCodeGenerator.getNextNo(UniquneCodeGenerator.UniqueResource.PAYMENT_NO));
			payment2.setFlag(0);
			payment2.setCustomOrderId(orderDto.getId());
			payment2.setPayAmount(new BigDecimal("0"));
			payment2.setResourceType(0);
			paymentService.add(payment2);


		}
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addOrderFilesByType(List<MultipartFile> multipartFileList, String fileType) {
		//添加图片到本地
		List<MapContext> listUrls = new ArrayList<>();
		if (multipartFileList != null && multipartFileList.size() > 0) {
			for (MultipartFile multipartFile : multipartFileList) {
				UploadInfo uploadInfo = AppBeanInjector.baseFileUploadComponent.doUploadByModule(UploadType.FORMAL, multipartFile, UploadResourceType.CUSTOM_ORDER,"order");
				//添加图片到数据库
				CustomOrderFiles customOrderFiles = new CustomOrderFiles();
				customOrderFiles.setCreated(DateUtil.getSystemDate());
				if(fileType.equals("1")){
					customOrderFiles.setType(CustomOrderFilesType.DESIGN.getValue());
				}else if(fileType.equals("2")){
					customOrderFiles.setType(CustomOrderFilesType.DESIGN_SKETCH.getValue());
				}else if(fileType.equals("3")){
					customOrderFiles.setType(CustomOrderFilesType.INSTALLATION_DIAGRAM.getValue());
				}
				customOrderFiles.setFullPath(WebUtils.getDomainUrl() + uploadInfo.getRelativePath());
				customOrderFiles.setMime(uploadInfo.getFileMimeType().getRealType());
				customOrderFiles.setName(uploadInfo.getFileName());
				customOrderFiles.setOriginalMime(uploadInfo.getFileMimeType().getOriginalType());
				customOrderFiles.setPath(uploadInfo.getRelativePath());
				customOrderFiles.setStatus(0);
				customOrderFiles.setCategory(CustomOrderFilesCategory.ACCESSORY.getValue());
				customOrderFiles.setCreator(WebUtils.getCurrUserId());
				//customOrderFiles.setCustomOrderId(id);
				this.customOrderFilesService.add(customOrderFiles);
				MapContext map = MapContext.newOne();
				map.put("fullPath", customOrderFiles.getFullPath());
				map.put("imgRelPath", uploadInfo.getRelativePath());
				map.put("id", customOrderFiles.getId());
				listUrls.add(map);
			}
		}
		return ResultFactory.generateRequestResult(listUrls);
	}

	@Override
	@Transactional(value = "transactionManager")
	public void inputmaterial(MultipartFile file) {
		try {
			List<String[]> strings = POIUtil.readExcel(file);
			if(strings.size()>0){
				for(String[] s:strings){
					if(s.length>0){
						Material material=new Material();
						material.setBranchId(WebUtils.getCurrBranchId());
						material.setMaterialLevel(1);
						material.setName(s[1]);
						material.setCreated(new Date());
						material.setMaterialStatus(0);
						String typeValue = s[2];//查询原材料类型id
						Basecode supply_type = this.basecodeService.findByTypeAndValue("supplyType", typeValue);
						material.setCategoryId(supply_type.getId());
						String unit = s[3];
						Basecode unitType = this.basecodeService.findByTypeAndValue("unitType", unit);
						material.setUnit(Integer.valueOf( unitType.getCode()));
						this.materialService.add(material);

					}
				}
			}

			System.out.println(strings);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	@Transactional(value = "transactionManager")
	public void inputsupply(MultipartFile file) {
		try {
			List<String[]> strings = POIUtil.readExcel(file);
			if(strings.size()>0){
				for(String[] s:strings){
					if(s.length>0){
						Supplier supplier=new Supplier();
                        supplier.setSupplierName(s[1]);
                        if(s[1]!=null&&!s[1].equals("")) {
							String typeValue = s[2].trim();//查询类型id
							Basecode supply_type = this.basecodeService.findByTypeAndValue("supplyType", typeValue);
							supplier.setCategoryId(supply_type.getId());
							supplier.setCreated(new Date());
							supplier.setContacts(s[3]);
							if (s.length >= 5) {
								supplier.setSupplierPhone(s[4]);
							}
							if (s.length >= 6) {
								supplier.setAddress(s[5]);
							}
							supplier.setStatus(1);
							supplier.setSupplierStage(1);
							this.supplierService.add(supplier);
						}
					}
				}
			}

			System.out.println(strings);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getTimeInfo(Date startTime, Date endTime, Integer date) {
		long startTimeValue = startTime.getTime();
		long endTimeValue=endTime.getTime();
		long diff = (endTimeValue - startTimeValue);
		long nd = 1000 * 24 * 60 * 60;//一天的毫秒数
		long nh = 1000 * 60 * 60;//一小时的毫秒数
		long nm = 1000 * 60;//一分钟的毫秒数
		long day = diff / nd;//计算用时多少天
		long hour = diff % nd / nh;//计算用时多少小时
		long min = diff % nd % nh / nm;//计算用时多少分钟
		String timeConsuming="";
		if((int)day<date) {
			timeConsuming = "累计用时"+day + "天" + hour + "小时" + min + "分钟";
		}else {
			timeConsuming = "累计用时"+day + "天" + hour + "小时" + min + "分钟"+"("+"延期"+((int)day-date) + "天" + hour + "小时" + min + "分钟"+")";
		}
		return timeConsuming;
	}
}

