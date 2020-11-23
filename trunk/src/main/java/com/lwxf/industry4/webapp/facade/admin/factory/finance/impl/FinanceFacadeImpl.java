package com.lwxf.industry4.webapp.facade.admin.factory.finance.impl;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.bizservice.aftersale.AftersaleApplyService;
import com.lwxf.industry4.webapp.bizservice.company.CompanyService;
import com.lwxf.industry4.webapp.bizservice.customorder.*;
import com.lwxf.industry4.webapp.bizservice.dealer.DealerAccountLogService;
import com.lwxf.industry4.webapp.bizservice.dealer.DealerAccountService;
import com.lwxf.industry4.webapp.bizservice.financing.BankAccountService;
import com.lwxf.industry4.webapp.bizservice.financing.PaymentFilesService;
import com.lwxf.industry4.webapp.bizservice.financing.PaymentService;
import com.lwxf.industry4.webapp.bizservice.procurement.PurchaseService;
import com.lwxf.industry4.webapp.bizservice.product.ProductPartsService;
import com.lwxf.industry4.webapp.bizservice.system.BasecodeService;
import com.lwxf.industry4.webapp.common.aop.syslog.OperationMoudule;
import com.lwxf.industry4.webapp.common.aop.syslog.OperationType;
import com.lwxf.industry4.webapp.common.aop.syslog.SysOperationLog;
import com.lwxf.industry4.webapp.common.component.UploadInfo;
import com.lwxf.industry4.webapp.common.component.UploadType;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.UploadResourceType;
import com.lwxf.industry4.webapp.common.enums.company.*;
import com.lwxf.industry4.webapp.common.enums.customorder.ProduceOrderPermit;
import com.lwxf.industry4.webapp.common.enums.customorder.ProduceOrderState;
import com.lwxf.industry4.webapp.common.enums.financing.*;
import com.lwxf.industry4.webapp.common.enums.order.*;
import com.lwxf.industry4.webapp.common.exceptions.BankNotFoundException;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.uniquecode.UniquneCodeGenerator;
import com.lwxf.industry4.webapp.common.utils.DateUtilsExt;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.common.utils.WeiXin.WeiXinMsgPush;
import com.lwxf.industry4.webapp.common.utils.excel.BaseExportExcelUtil;
import com.lwxf.industry4.webapp.common.utils.excel.impl.PaymentExcelParam;
import com.lwxf.industry4.webapp.config.LoggerBuilder;
import com.lwxf.industry4.webapp.domain.dto.company.CompanyDto;
import com.lwxf.industry4.webapp.domain.dto.customorder.CustomOrderDto;
import com.lwxf.industry4.webapp.domain.dto.customorder.CustomOrderLogDto;
import com.lwxf.industry4.webapp.domain.dto.customorder.OrderProductDto;
import com.lwxf.industry4.webapp.domain.dto.financing.PaymentDto;
import com.lwxf.industry4.webapp.domain.dto.printTable.PaymentPrintTableDto;
import com.lwxf.industry4.webapp.domain.dto.product.ProductPartsDto;
import com.lwxf.industry4.webapp.domain.entity.branch.Branch;
import com.lwxf.industry4.webapp.domain.entity.company.Company;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrder;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderLog;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderTime;
import com.lwxf.industry4.webapp.domain.entity.customorder.ProduceOrder;
import com.lwxf.industry4.webapp.domain.entity.dealer.DealerAccount;
import com.lwxf.industry4.webapp.domain.entity.dealer.DealerAccountLog;
import com.lwxf.industry4.webapp.domain.entity.financing.BankAccount;
import com.lwxf.industry4.webapp.domain.entity.financing.Payment;
import com.lwxf.industry4.webapp.domain.entity.financing.PaymentFiles;
import com.lwxf.industry4.webapp.domain.entity.system.Basecode;
import com.lwxf.industry4.webapp.domain.entity.user.User;
import com.lwxf.industry4.webapp.domain.entity.user.UserThirdInfo;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.dealer.impl.OrderFacadeImpl;
import com.lwxf.industry4.webapp.facade.admin.factory.finance.FinanceFacade;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.mybatis.utils.MapContext;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.lwxf.industry4.webapp.facade.AppBeanInjector.i18nUtil;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2019/1/9/009 10:03
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("financeFacade")
public class FinanceFacadeImpl extends BaseFacadeImpl implements FinanceFacade {
	LoggerBuilder loggerBuilder =new LoggerBuilder();
	Logger logger = loggerBuilder.getLogger("paymentsimple");
	@Resource(name = "customOrderService")
	private CustomOrderService customOrderService;
	@Resource(name = "purchaseService")
	private PurchaseService purchaseService;
	@Resource(name = "companyService")
	private CompanyService companyService;
	@Resource(name = "paymentService")
	private PaymentService paymentService;
	@Resource(name = "dealerAccountService")
	private DealerAccountService dealerAccountService;
	@Resource(name = "aftersaleApplyService")
	private AftersaleApplyService aftersaleApplyService;
	@Resource(name = "produceOrderService")
	private ProduceOrderService produceOrderService;
	@Resource(name = "paymentFilesService")
	private PaymentFilesService paymentFilesService;
	@Resource(name = "bankAccountService")
	private BankAccountService bankAccountService;
	@Resource(name = "customOrderLogService")
	private CustomOrderLogService customOrderLogService;
	@Resource(name = "orderProductService")
	private OrderProductService orderProductService;
	@Resource(name = "productPartsService")
	private ProductPartsService productPartsService;
	@Resource(name = "orderProductPartsService")
	private OrderProductPartsService orderProductPartsService;
	@Resource(name = "basecodeService")
	private BasecodeService basecodeService;
	@Resource(name = "customOrderTimeService")
	private CustomOrderTimeService customOrderTimeService;
	@Resource(name = "dealerAccountLogService")
	private DealerAccountLogService dealerAccountLogService;
	@Resource(name = "customOrderRemindService")
	private CustomOrderRemindService customOrderRemindService;
	@Autowired
	private WeiXinMsgPush weiXinMsgPush;
	@Override
	public RequestResult findOrderFinanceList(MapContext mapContext, Integer pageNum, Integer pageSize) {
		//查询是否开启催款功能
		Branch branch = AppBeanInjector.branchService.findById(WebUtils.getCurrBranchId());
		Integer enableRemind = branch.getEnableRemind();
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		Pagination pagination = new Pagination();
		pagination.setPageNum(pageNum);
		pagination.setPageSize(pageSize);
		paginatedFilter.setPagination(pagination);
		mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID, WebUtils.getCurrBranchId());
		paginatedFilter.setFilters(mapContext);
		List<Map<String, String>> sort = new ArrayList<Map<String, String>>();
		Map<String, String> status = new HashMap<String, String>();
		status.put(WebConstant.KEY_ENTITY_STATUS, "asc");
		sort.add(status);
		Map<String, String> created = new HashMap<String, String>();
		created.put(WebConstant.KEY_ENTITY_CREATED, "desc");
		sort.add(created);
		paginatedFilter.setSorts(sort);
		PaginatedList<PaymentDto> listByPaginateFilter = this.paymentService.findListByPaginateFilter(paginatedFilter);
		if(listByPaginateFilter.getRows().size()>0) {
			for (PaymentDto paymentDto : listByPaginateFilter.getRows()) {
				String orderId = paymentDto.getCustomOrderId();
				CustomOrderTime customOrderTime=this.customOrderTimeService.findMaxByOrderId(orderId);//查询总工期最长的数据
				//审核工期
				Date commitTime = paymentDto.getCreated();
				Date audited=DateUtil.getSystemDate();
				String auditInfo="";
				if(paymentDto.getAudited()!=null&&!paymentDto.getAudited().equals("")){
					audited=paymentDto.getAudited();
				}
				Integer auditTime=1;
				if(customOrderTime!=null) {
					auditTime=customOrderTime.getAuditTime();
				}
				auditInfo= OrderFacadeImpl.getTimeInfo(commitTime,audited,auditTime);
				paymentDto.setAuditInfo(auditInfo);
			}
		}
		return ResultFactory.generateRequestResult(listByPaginateFilter);
	}

	@Override
	public RequestResult findPurchaseList(MapContext mapContext, Integer pageNum, Integer pageSize) {
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		Pagination pagination = new Pagination();
		pagination.setPageNum(pageNum);
		pagination.setPageSize(pageSize);
		paginatedFilter.setPagination(pagination);
		mapContext.put(WebConstant.KEY_ENTITY_STATUS, 0);
		paginatedFilter.setFilters(mapContext);
		List<Map<String, String>> sort = new ArrayList<Map<String, String>>();
		Map<String, String> created = new HashMap<String, String>();
		created.put(WebConstant.KEY_ENTITY_CREATED, "desc");
		sort.add(created);
		paginatedFilter.setSorts(sort);
		return ResultFactory.generateRequestResult(this.purchaseService.selectByFilter(paginatedFilter));
	}

	@Override
	public RequestResult findDealerPayList(MapContext mapContext, Integer pageNum, Integer pageSize) {
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		Pagination pagination = new Pagination();
		pagination.setPageNum(pageNum);
		pagination.setPageSize(pageSize);
		paginatedFilter.setPagination(pagination);
		mapContext.put(WebConstant.KEY_ENTITY_STATUS, 0);
		paginatedFilter.setFilters(mapContext);
		List<Map<String, String>> sort = new ArrayList<Map<String, String>>();
		Map<String, String> created = new HashMap<String, String>();
		created.put(WebConstant.KEY_ENTITY_CREATED, "desc");
		sort.add(created);
		paginatedFilter.setSorts(sort);
		return ResultFactory.generateRequestResult(this.paymentService.selectByFilter(paginatedFilter));
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updateFinancePayStatus(String id, Integer type) {
		//判断支付记录是否存在
		Payment payment = this.paymentService.findById(id);
		if (payment == null && payment.getStatus().equals(PaymentStatus.PAID.getValue())) {
			return ResultFactory.generateResNotFoundResult();
		}
		MapContext mapContext = MapContext.newOne();
		mapContext.put(WebConstant.KEY_ENTITY_ID, id);
		mapContext.put(WebConstant.KEY_ENTITY_STATUS, 1);
		this.paymentService.updateByMapContext(mapContext);
		MapContext updateDealerAccount = new MapContext();
		//根据type不同资金如不同的库
		DealerAccount dealerAccount = this.dealerAccountService.findByCompanIdAndNatureAndType(payment.getCompanyId(), DealerAccountNature.PUBLIC.getValue(), type);
		updateDealerAccount.put(WebConstant.KEY_ENTITY_ID, dealerAccount.getId());
		if (payment.getType().equals(PaymentType.B_TO_F.getValue())) {//是否充值操作
			if (dealerAccount == null) {
				return ResultFactory.generateResNotFoundResult();
			}
			updateDealerAccount.put("balance", dealerAccount.getBalance().add(payment.getAmount()));
		} else if (payment.getType().equals(PaymentType.F_TO_B.getValue())) {//是否是体现操作
			if (dealerAccount.getBalance().compareTo(payment.getAmount()) != -1) {//判断余额是否充足
				updateDealerAccount.put("balance", dealerAccount.getBalance().subtract(payment.getAmount()));
			} else {
				return ResultFactory.generateErrorResult(ErrorCodes.BIZ_CREDIT_IS_LOW_10075, AppBeanInjector.i18nUtil.getMessage("BIZ_CREDIT_IS_LOW_10075"));
			}
		} else {
			return ResultFactory.generateResNotFoundResult();
		}
		this.dealerAccountService.updateByMapContext(updateDealerAccount);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	public RequestResult findSupplierList(MapContext mapContext, Integer pageNum, Integer pageSize) {
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		Pagination pagination = new Pagination();
		pagination.setPageNum(pageNum);
		pagination.setPageSize(pageSize);
		paginatedFilter.setPagination(pagination);
		mapContext.put(WebConstant.KEY_ENTITY_STATUS, 0);
		mapContext.put("type", Arrays.asList(CompanyType.SUPPLIER.getValue()));
		paginatedFilter.setFilters(mapContext);
		List<Map<String, String>> sort = new ArrayList<Map<String, String>>();
		Map<String, String> created = new HashMap<String, String>();
		created.put(WebConstant.KEY_ENTITY_CREATED, "desc");
		sort.add(created);
		paginatedFilter.setSorts(sort);
		return ResultFactory.generateRequestResult(this.companyService.selectByFilter(paginatedFilter));
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updateSupplier(String id, Integer status) {
		//判断供应商是否存在
		Company company = this.companyService.findById(id);
		if (company == null || !company.getType().equals(CompanyType.SUPPLIER.getValue())) {
			return ResultFactory.generateResNotFoundResult();
		}
		MapContext update = new MapContext();
		update.put(WebConstant.KEY_ENTITY_ID, id);
		update.put(WebConstant.KEY_ENTITY_STATUS, status);
		this.companyService.updateByMapContext(update);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	public RequestResult findAftersaleList(MapContext mapContext, Integer pageNum, Integer pageSize) {
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		Pagination pagination = new Pagination();
		pagination.setPageNum(pageNum);
		pagination.setPageSize(pageSize);
		paginatedFilter.setPagination(pagination);
		mapContext.put(WebConstant.KEY_ENTITY_STATUS, 1);
		paginatedFilter.setFilters(mapContext);
		List<Map<String, String>> sort = new ArrayList<Map<String, String>>();
		Map<String, String> created = new HashMap<String, String>();
		created.put(WebConstant.KEY_ENTITY_CREATED, "desc");
		sort.add(created);
		paginatedFilter.setSorts(sort);
		return ResultFactory.generateRequestResult(this.aftersaleApplyService.selectByFilter(paginatedFilter));
	}

	@Override
	public RequestResult findDealerList(MapContext mapContext, Integer pageNum, Integer pageSize) {
//		PaginatedFilter paginatedFilter = new PaginatedFilter();
//		Pagination pagination = new Pagination();
//		pagination.setPageNum(pageNum);
//		pagination.setPageSize(pageSize);
//		paginatedFilter.setPagination(pagination);
//		mapContext.put(WebConstant.KEY_ENTITY_STATUS, 0);
//		mapContext.put("type",Arrays.asList(CompanyType.MANUFACTURERS.getValue(),CompanyType.DIRECT_SHOP.getValue(),CompanyType.SHOP_IN_STORE.getValue(),CompanyType.EXCLUSIVE_SHOP.getValue(),CompanyType.FRANCHISED_STORE.getValue(),CompanyType.LOOSE_ORDER.getValue()));
//		paginatedFilter.setFilters(mapContext);
//		List<Map<String,String>> sort = new ArrayList<Map<String, String>>();
//		Map<String,String> created = new HashMap<String, String>();
//		created.put(WebConstant.KEY_ENTITY_CREATED,"desc");
//		sort.add(created);
//		paginatedFilter.setSorts(sort);
//		return ResultFactory.generateRequestResult(this.companyService.selectByFilter(paginatedFilter));
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		Pagination pagination = new Pagination();
		pagination.setPageNum(pageNum);
		pagination.setPageSize(pageSize);
		paginatedFilter.setPagination(pagination);
		mapContext.put("fundsList", Arrays.asList(PaymentFunds.FRANCHISE_FEE.getValue()));
		mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID, WebUtils.getCurrBranchId());
		paginatedFilter.setFilters(mapContext);
		List<Map<String, String>> sort = new ArrayList<Map<String, String>>();
		Map<String, String> created = new HashMap<String, String>();
		created.put(WebConstant.KEY_ENTITY_CREATED, "desc");
		sort.add(created);
		paginatedFilter.setSorts(sort);
		return ResultFactory.generateRequestResult(this.paymentService.findListByPaginateFilter(paginatedFilter));
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updateDealerPay(String id) {
		Payment payment = this.paymentService.findById(id);
		if (payment == null || !payment.getFunds().equals(PaymentFunds.FRANCHISE_FEE.getValue())) {
			return ResultFactory.generateResNotFoundResult();
		}
		//修改支付记录状态为 已收款
		MapContext mapContext = new MapContext();
		mapContext.put(WebConstant.KEY_ENTITY_ID, id);
		mapContext.put(WebConstant.KEY_ENTITY_STATUS, PaymentStatus.PAID.getValue());
		mapContext.put("auditor", WebUtils.getCurrUserId());
		mapContext.put("audited", DateUtil.getSystemDate());
		mapContext.put("payTime", DateUtil.getSystemDate());
		this.paymentService.updateByMapContext(mapContext);
		//修改公司状态为待激活
		MapContext updateDealer = new MapContext();
		updateDealer.put(WebConstant.KEY_ENTITY_ID, payment.getCompanyId());
		updateDealer.put(WebConstant.KEY_ENTITY_STATUS, CompanyStatus.TO_BE_ENABLED.getValue());
		this.companyService.updateByMapContext(updateDealer);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	@SysOperationLog(detail = "外协单审核", operationType = OperationType.VERIFY, operationMoudule = OperationMoudule.FINANCE)
	public RequestResult updateCoordinationPay(String id, MapContext map) {
		//判断外协单是否存在 是否已付款
		ProduceOrder produceOrder = this.produceOrderService.findById(id);
		if (produceOrder == null || produceOrder.getPay().equals(ProduceOrderPay.PAY.getValue())) {
			return ResultFactory.generateResNotFoundResult();
		}
		String bankId = map.getTypedValue("bank", String.class);
		BigDecimal amount = map.getTypedValue("amount", BigDecimal.class);
		//判断余额是否充足
		BankAccount bankAccount = this.bankAccountService.findById(bankId);
		if (bankAccount == null) {
			throw new BankNotFoundException();
		} else {
			if (bankAccount.getAmount().compareTo(amount) == -1) {
				return ResultFactory.generateErrorResult(ErrorCodes.BIZ_BANK_NOT_FOUND_10091, AppBeanInjector.i18nUtil.getMessage("BIZ_BANK_NOT_FOUND_10091"));
			}
			MapContext updateBank = MapContext.newOne();
			updateBank.put("id", bankId);
			updateBank.put("amount", bankAccount.getAmount().subtract(amount));
			bankAccountService.updateByMapContext(updateBank);
		}

		//修改外协单为已付款 且生产中
		MapContext mapContext = new MapContext();
		mapContext.put(WebConstant.KEY_ENTITY_ID, id);
		mapContext.put("pay", ProduceOrderPay.PAY.getValue());
		mapContext.put(WebConstant.KEY_ENTITY_STATE, ProduceOrderState.IN_PRODUCTION.getValue());
		this.produceOrderService.updateByMapContext(mapContext);

		Payment payment = new Payment();
		payment.setHolder(produceOrder.getCoordinationName() == null ? "" : produceOrder.getCoordinationName());
		payment.setHolderAccount(produceOrder.getCoordinationAccount());
		payment.setNo(AppBeanInjector.uniquneCodeGenerator.getNextNo(UniquneCodeGenerator.UniqueResource.PAYMENT_NO));
		payment.setAmount(produceOrder.getAmount());
		payment.setCompanyId(WebUtils.getCurrCompanyId());
		payment.setStatus(PaymentStatus.PAID.getValue());
		payment.setCreated(DateUtil.getSystemDate());
		payment.setCreator(WebUtils.getCurrUserId());
		payment.setFunds(PaymentFunds.COORDINATION.getValue());
		payment.setWay(map.getTypedValue("way", Integer.class));
		payment.setType(PaymentType.F_TO_B.getValue());
		payment.setPayee("4j1u3r1efshq");
		payment.setCustomOrderId(produceOrder.getCustomOrderId());
		payment.setNotes("外协单编号:" + produceOrder.getNo());
		payment.setAudited(map.getTypedValue("payTime", Date.class));
		payment.setAuditor(map.getTypedValue("auditor", String.class));
		payment.setPayTime(map.getTypedValue("payTime", Date.class));
		payment.setPayAmount(amount);
		this.paymentService.add(payment);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	@SysOperationLog(detail = "订单审核", operationType = OperationType.VERIFY, operationMoudule = OperationMoudule.FINANCE)
	public RequestResult updateCustomOrdersPay(String id, MapContext map) {
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String userId = WebUtils.getCurrUserId();
		if (userId == null) {
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
		}
		User logUser=AppBeanInjector.userService.findByUserId(userId);
		String loginName=logUser.getName();
		Payment payment = this.paymentService.findById(id);
		CustomOrder customOrder = this.customOrderService.findByOrderId(payment.getCustomOrderId());
		if (map == null) {
			return ResultFactory.generateResNotFoundResult();
		} else {
			if (map.get("amountDeduction") == null) {
				return ResultFactory.generateErrorResult(ErrorCodes.BIZ_PAYMENT_PAY_AMOUNT_NOT_NULL_10093, i18nUtil.getMessage("BIZ_AUDITED_PAY_AMOUNT_NOTNULL_10085"));
			}
		}
		if (payment == null || payment.getStatus().equals(PaymentStatus.PAID.getValue())) {
			return ResultFactory.generateResNotFoundResult();
		}
		DealerAccount dealerAccount;
		//判断订单是否为代扣款
		String payCompanyId=null;
		if(customOrder.getPaymentWithholding()!=null&&customOrder.getPaymentWithholding()==1){
			payCompanyId=customOrder.getWithholdingCompanyId();
		}else {
			payCompanyId=payment.getCompanyId();
		}
		//查询经销商账户余额
		if (payment.getFunds().equals(PaymentFunds.ORDER_FEE_CHARGE.getValue())) {
			dealerAccount = this.dealerAccountService.findByCompanIdAndNatureAndType(payCompanyId, DealerAccountNature.PUBLIC.getValue(), DealerAccountType.FREE_ACCOUNT.getValue());
		} else if (payment.getFunds().equals(PaymentFunds.DESIGN_FEE_CHARGE.getValue())) {
			dealerAccount = this.dealerAccountService.findByCompanIdAndNatureAndType(payCompanyId, DealerAccountNature.PUBLIC.getValue(), DealerAccountType.FREEZE_ACCOUNT.getValue());
		} else {
			return ResultFactory.generateResNotFoundResult();
		}
		//审核类型
		String auditType=map.getTypedValue("auditType", String.class);//0-正常审核 1-挂账审核
		//抵扣金额
		BigDecimal amountDeduction = map.getTypedValue("amountDeduction", BigDecimal.class);
		Integer paymentStatus=payment.getStatus();
		Payment withholdingPayment=new Payment();
		if(paymentStatus==PaymentStatus.ON_CREDIT.getValue()){//挂账状态
			if(customOrder.getPaymentWithholding()!=null&&customOrder.getPaymentWithholding()==1){//如果为代扣款
				//查询代扣的支付信息记录
				MapContext paramss=MapContext.newOne();
				paramss.put("orderId",customOrder.getId());
				paramss.put("funds",PaymentFunds.PAYMENT_WITHHOLDING_CHARGE.getValue());
				withholdingPayment=this.paymentService.findByOrderIdAndFunds(paramss);
			BigDecimal countAmount = new BigDecimal(0);
			if(withholdingPayment.getPayAmount()==null||withholdingPayment.getPayAmount().equals("")){
				countAmount=amountDeduction;
			}else {
				countAmount=withholdingPayment.getPayAmount().add(amountDeduction);
			}
          MapContext mapContext=MapContext.newOne();
          mapContext.put("id",withholdingPayment.getId());
          mapContext.put("payAmount",countAmount);
          MapContext param1=MapContext.newOne();
		  param1.put("payAmount",countAmount);
		  param1.put("id",id);
          if(countAmount.compareTo(withholdingPayment.getAmount())==0){
          	mapContext.put("status",PaymentStatus.PAID.getValue());
          	param1.put("status",PaymentStatus.PAID.getValue());
		  }
          this.paymentService.updateByMapContext(param1);
          this.paymentService.updateByMapContext(mapContext);
          	  //代扣款经销商信息
			  Company byWithholdingId = this.companyService.findById(payCompanyId);
			  String withholdingDealerName = byWithholdingId.getName();
			  //订单实际经销商信息
			  Company byId = this.companyService.findById(payment.getCompanyId());
			  String dealerName = byId.getName();
			  //扣除代扣经销商账户金额（订单实际金额）
			  MapContext withholdingDealerAccount = new MapContext();
			  withholdingDealerAccount.put(WebConstant.KEY_ENTITY_ID, dealerAccount.getId());
			  withholdingDealerAccount.put("balance", dealerAccount.getBalance().subtract((amountDeduction)));
			  this.dealerAccountService.updateByMapContext(withholdingDealerAccount);
			  //记录账户变动日志
			  DealerAccountLog withholdingdealerAccountLog = new DealerAccountLog();
			  withholdingdealerAccountLog.setAmount(amountDeduction);
			  withholdingdealerAccountLog.setDealerAccountId(dealerAccount.getId());
			  withholdingdealerAccountLog.setContent(dateFormat.format(DateUtil.getSystemDate()) + loginName + "审核经销商"+dealerName+"订单" + customOrder.getNo() + "," + "此订单为货款代扣订单，货款由代扣经销商" + withholdingDealerName + "自由资金账户扣除，代扣金额为：" + amountDeduction + ",之前账户余额为：" + dealerAccount.getBalance() + ",剩余金额为：" + dealerAccount.getBalance().subtract((amountDeduction)));
			  withholdingdealerAccountLog.setType(DealerAccountLogType.ORDER_PAYMENT_WITHHOLDING.getValue());
			  withholdingdealerAccountLog.setCreator(WebUtils.getCurrUserId());
			  withholdingdealerAccountLog.setCreated(DateUtil.getSystemDate());
			  withholdingdealerAccountLog.setDisburse(false);
			  withholdingdealerAccountLog.setResourceId(customOrder.getId());
			  withholdingdealerAccountLog.setCompanyId(WebUtils.getCurrCompanyId());
			  this.dealerAccountLogService.add(withholdingdealerAccountLog);
			  logger.info(dateFormat.format(DateUtil.getSystemDate()) + loginName + "审核经销商"+dealerName+"订单" + customOrder.getNo() + "," + "此订单为货款代扣订单，货款由代扣经销商" + withholdingDealerName + "自由资金账户扣除，代扣金额为：" + amountDeduction + ",之前账户余额为：" + dealerAccount.getBalance() + ",剩余金额为：" + dealerAccount.getBalance().subtract((amountDeduction)));

		  }else {//非代扣款
				BigDecimal countAmount = new BigDecimal(0);
				if(payment.getPayAmount()==null||payment.getPayAmount().equals("")){
					countAmount=amountDeduction;
				}else {
					countAmount=payment.getPayAmount().add(amountDeduction);
				}
				MapContext mapContext=MapContext.newOne();
				mapContext.put("id",id);
				mapContext.put("payAmount",countAmount);
				if(countAmount.compareTo(payment.getAmount())==0){
					mapContext.put("status",PaymentStatus.PAID.getValue());
				}
				this.paymentService.updateByMapContext(mapContext);
			  //扣除经销商账户金额
			  MapContext updateDealerAccount = new MapContext();
			  updateDealerAccount.put(WebConstant.KEY_ENTITY_ID, dealerAccount.getId());
			  updateDealerAccount.put("balance", dealerAccount.getBalance().subtract((amountDeduction)));
			  this.dealerAccountService.updateByMapContext(updateDealerAccount);
			  //记录账户变动日志
			  DealerAccountLog dealerAccountLog = new DealerAccountLog();
			  Company byId = this.companyService.findById(payCompanyId);
			  String dealerName = byId.getName();
			  dealerAccountLog.setAmount(amountDeduction);
			  dealerAccountLog.setDealerAccountId(dealerAccount.getId());
			  dealerAccountLog.setContent(dateFormat.format(DateUtil.getSystemDate()) + loginName + "审核订单" + customOrder.getNo() + "," + "经销商" + dealerName + "自由资金账户扣除" + amountDeduction + ",之前账户余额为：" + dealerAccount.getBalance() + ",剩余金额为：" + dealerAccount.getBalance().subtract((amountDeduction)));
			  dealerAccountLog.setType(DealerAccountLogType.ORDER_DEDUCTION.getValue());
			  dealerAccountLog.setCreator(WebUtils.getCurrUserId());
			  dealerAccountLog.setCreated(DateUtil.getSystemDate());
			  dealerAccountLog.setDisburse(false);
			  dealerAccountLog.setResourceId(customOrder.getId());
			  dealerAccountLog.setCompanyId(WebUtils.getCurrCompanyId());
			  this.dealerAccountLogService.add(dealerAccountLog);
			  logger.info(dateFormat.format(DateUtil.getSystemDate()) + loginName + "审核订单" + customOrder.getNo() + "," + "经销商" + dealerName + "自由资金账户扣除" + amountDeduction + ",之前账户余额为：" + dealerAccount.getBalance() + ",剩余金额为：" + dealerAccount.getBalance().subtract((amountDeduction)));
		  }
			//经销商添加积分
			CompanyDto companyDto=this.companyService.findCompanyById(customOrder.getCompanyId());
			MapContext companyMap=MapContext.newOne();
			companyMap.put("id",customOrder.getCompanyId());
			companyMap.put("integral",companyDto.getIntegral().add(amountDeduction));
			this.companyService.updateByMapContext(companyMap);
		}else {
		String customOrderId = payment.getCustomOrderId();
		//如果是货款扣款 则修改订单状态
		MapContext updateOrder = new MapContext();
		updateOrder.put(WebConstant.KEY_ENTITY_ID, customOrderId);
		//判断支付记录是 订单 还是 售后单 根据资源不同 修改不同数据
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(DateUtil.getSystemDate()); //需要将date数据转移到Calender对象中操作
		List<OrderProductDto> productDtos = this.orderProductService.findListByOrderId(customOrderId);//产品品类，系列，部件不同，订单周期也不同
		MapContext mapContext = MapContext.newOne();
		Integer allDay = 0;
		Integer allCost = 0;
		CustomOrderDto customOrderDto = this.customOrderService.findByOrderId(customOrderId);
//		for (OrderProductDto orderProductDto : productDtos) {
//			//查询订单下的产品，以及相关的系列，部件
//			List<ProductPartsDto> productParts = this.productPartsService.findByOrderProductId(orderProductDto.getId());
//			Integer productType = orderProductDto.getType();//产品品类
//			Integer series = orderProductDto.getSeries();//系列
//			Integer partsType = null;//部件
//			if (customOrderDto.getType() == OrderType.NORMALORDER.getValue()) {
//				//橱柜和衣柜
//				if (productType == OrderProductType.CUPBOARD.getValue() || productType == OrderProductType.WARDROBE.getValue()) {
//					for (ProductPartsDto productPartsDto : productParts) {
//						mapContext.put("productSeries", series);
//						partsType = productPartsDto.getPartsType();
//						//如果部件是五金,则默认系列为0
//						if (partsType == ProduceOrderType.HARDWARE.getValue()) {
//							allCost = 3;
//						} else {
//							//如果系列和生产工艺都是null,并且部件不是五金
//							if ((orderProductDto.getSeries() == null || orderProductDto.getSeries().equals(""))
//									&& (orderProductDto.getBodyTec() == null || orderProductDto.getBodyTec().equals(""))) {
//								if (productType == OrderProductType.CUPBOARD.getValue()) {//橱柜7天
//									allCost = 7;
//								} else if (productType == OrderProductType.WARDROBE.getValue()) {//衣柜12天
//									allCost = 12;
//								}
//							} else {
//								//橱柜柜体没有系列，如果为橱柜柜体，则默认系列是0
//								if (productType == OrderProductType.CUPBOARD.getValue() && partsType == ProduceOrderType.CABINET_BODY.getValue()) {
//									mapContext.put("productSeries", 0);
//								}
//								//如果是衣柜柜体，系列默认为0
//								if (productType == OrderProductType.WARDROBE.getValue() && partsType == ProduceOrderType.CABINET_BODY.getValue()) {
//									mapContext.put("productSeries", 0);
//								}
//								//如果是衣柜柜体，则门板系列为空，衣柜柜体工艺不能为空，衣柜柜体工艺也存在customorderTime表中的series字段
//								//所以只需要把衣柜柜体工艺也存入series对customorderTime表进行查询即可
//								if (orderProductDto.getSeries() == null || orderProductDto.getSeries().equals("")) {
//									mapContext.put("productSeries", orderProductDto.getBodyTec());
//								}
//								mapContext.put("produceType", partsType);
//								mapContext.put("productType", orderProductDto.getType());
//								CustomOrderTime customOrderTime = this.customOrderTimeService.findByTypeAndSeries(mapContext);
//								if (customOrderTime != null) {
//									allCost = customOrderTime.getAllCost();
//								}
//							}
//						}
//						if (allCost > allDay) {
//							allDay = allCost;
//						}
//					}
//
//				}
//				//五金和样块默认系列和生产方式都为0
//				else if (productType == OrderProductType.HARDWARE.getValue() || productType == OrderProductType.SAMPLE_PIECE.getValue()) {
//					mapContext.put("productSeries", 0);
//					mapContext.put("produceType", 0);
//					mapContext.put("productType", orderProductDto.getType());
//					CustomOrderTime customOrderTime = this.customOrderTimeService.findByTypeAndSeries(mapContext);
//					if (customOrderTime != null) {
//						allCost = customOrderTime.getAllCost();
//					}
//					if (allCost > allDay) {
//						allDay = allCost;
//					}
//				}else {
//					Basecode base=this.basecodeService.findByTypeAndCode("defaultAllDuration","0");
//					if(base!=null) {
//						allDay = Integer.valueOf(base.getRemark());
//					}else {
//						allDay=15;
//					}
//				}
//			} else {
//				if (series != null && !series.equals("")) {
//					if (series == 5) {
//						allDay = 7;
//					} else {
//						allDay = 3;
//					}
//				} else {
//					allDay = 3;
//				}
//			}
//		}
		Basecode base=this.basecodeService.findByTypeAndCode("defaultAllDuration","0");
			if(base!=null) {
						allDay = Integer.valueOf(base.getRemark());
					}else {
						allDay=15;
					}
		calendar.add(calendar.DATE, allDay);//把日期往后增加最长的工期天数.正数往后推,负数往前移动
		updateOrder.put("estimatedDeliveryDate", calendar.getTime());
//		//判断产品不包含需要生产的产品，则直接状态更新为待发货
//		List<OrderProductDto> listProd = orderProductService.findListByOrderId(customOrderId);
//		int prodCount = 0;
//		//如果是柜体或者衣柜则订单状态改为待生产，否则为待发货
//		if (listProd != null && listProd.size() > 0) {
//			for (OrderProduct prod : listProd) {
//				if (prod.getType() == OrderProductType.CUPBOARD.getValue()) {
//					prodCount++;
//				}
//				if (prod.getType() == OrderProductType.WARDROBE.getValue()) {
//					prodCount++;
//				}
//			}
//		}
		if (customOrder.getOrderProductType()==OrderProductType.HARDWARE.getValue()||customOrder.getOrderProductType()==OrderProductType.SAMPLE_PIECE.getValue()) {
			updateOrder.put(WebConstant.KEY_ENTITY_STATUS, OrderStatus.PRODUCTION.getValue());
		} else {
			updateOrder.put(WebConstant.KEY_ENTITY_STATUS, OrderStatus.TO_PRODUCED.getValue());
		}
		this.customOrderService.updateByMapContext(updateOrder);
		//生成生产单
		List<OrderProductDto> orderProductDtos = new ArrayList<>();
		String no = null;//获取订单、售后单编号
		Integer status = null;
		no = customOrder.getNo();
		status = customOrder.getStatus();
		orderProductDtos = this.orderProductService.findListByOrderId(payment.getCustomOrderId());
		MapContext orderMapContext = new MapContext();
		//根据产品创建生产单
		CustomOrder customOrder1 = this.customOrderService.findByOrderId(customOrderId);
		if (orderProductDtos.size() > 0) {
			for (OrderProductDto productDto : orderProductDtos) {
				String orderProductId = productDto.getId();
				//修改订单产品的财务审核时间以及订单计划交付时间
				if (customOrder1 != null) {
					MapContext map1 = MapContext.newOne();
					map1.put("payTime", com.lwxf.mybatis.utils.DateUtil.now());
					map1.put("planOrderFinishTime", customOrder1.getEstimatedDeliveryDate());
					if (customOrder1.getOrderProductType() == 4 || customOrder1.getOrderProductType() == 5) {
						map1.put("status", OrderProductStatus.PRODUCING.getValue());
					} else {
						map1.put("status", OrderProductStatus.TO_PRODUCE.getValue());
					}
					map1.put("id", orderProductId);
					this.orderProductService.updateByMapContext(map1);
				}
				//查询产品部件，生成生产单
				List<ProductPartsDto> productParts = this.productPartsService.findByOrderProductId(orderProductId);
				if (productParts != null && productParts.size() > 0) {
					for (ProductPartsDto parts : productParts) {
						ProduceOrder produceOrder = new ProduceOrder();
						produceOrder.setCustomOrderId(payment.getCustomOrderId());
						produceOrder.setOrderProductId(orderProductId);
						produceOrder.setCustomOrderNo(no);
						produceOrder.setCreated(DateUtil.getSystemDate());
						produceOrder.setCreator(WebUtils.getCurrUserId());
						produceOrder.setBranchId(WebUtils.getCurrBranchId());
						produceOrder.setResourceType(payment.getResourceType());
						produceOrder.setFlag(0);
						produceOrder.setDeliverStatus(0);
						produceOrder.setHasDeliverCount(0);
						String productNo = productDto.getNo();
				List<Basecode> produceOrderType = this.basecodeService.findByTypeAndDelFlag("produceOrderType", 0);
				Basecode orderProductType = this.basecodeService.findByTypeAndCode("orderProductType", productDto.getType().toString());
				for(Basecode basecode:produceOrderType) {
					String remark=basecode.getRemark();
					productNo = productNo.replace(remark, "");
					if(remark.equals(orderProductType.getRemark())){
						productNo = orderProductType.getRemark() + productNo;
					}
				}
						if (parts.getOrderPartsIdentify() != null) {
							produceOrder.setNo(productNo + parts.getOrderPartsIdentify());
						} else {
							produceOrder.setNo(productNo);
						}
						if (parts.getProduceType() != ProduceOrderWay.COORDINATION.getValue()) {
							produceOrder.setPay(ProduceOrderPay.PAY.getValue());
						} else {
							produceOrder.setPay(ProduceOrderPay.NOT_PAY.getValue());
							produceOrder.setAmount(new BigDecimal("0.00"));
						}
						produceOrder.setType(parts.getPartsType());
						produceOrder.setWay(parts.getProduceType());
						produceOrder.setPermit(ProduceOrderPermit.NOT_ALLOW.getValue());
						if (parts.getPartsType() == ProduceOrderType.HARDWARE.getValue()) {//如果是五金
							if (parts.getProduceType().equals(ProduceOrderWay.COORDINATION.getValue())) {//如果是外协
								produceOrder.setState(ProduceOrderState.NOT_YET_BEGUN.getValue());
								produceOrder.setPermit(ProduceOrderPermit.NOT_ALLOW.getValue());
							} else {
								produceOrder.setState(ProduceOrderState.COMPLETE.getValue());
							}
						} else if (parts.getPartsType().equals(ProduceOrderType.CABINET_BODY.getValue())) {//如果是柜体
							produceOrder.setState(ProduceOrderState.NOT_YET_BEGUN.getValue());
						} else if (parts.getPartsType().equals(ProduceOrderType.DOOR_PLANK.getValue())) {//如果是门板
							if (parts.getProduceType().equals(ProduceOrderWay.SELF_PRODUCED.getValue())) {//如果是自产
								produceOrder.setState(ProduceOrderState.NOT_YET_BEGUN.getValue());
								produceOrder.setPermit(ProduceOrderPermit.NOT_ALLOW.getValue());
								//produceOrder.setNo(productDto.getNo() + "" + b.getValue()+"Z");
							} else {//如果是外协
								produceOrder.setState(ProduceOrderState.NOT_YET_BEGUN.getValue());
							}
						} else {
							produceOrder.setState(ProduceOrderState.NOT_YET_BEGUN.getValue());
						}
						this.produceOrderService.add(produceOrder);
					}
				}
			}
		}
		if (orderMapContext.size() > 0) {
			orderMapContext.put("id", payment.getCustomOrderId());
			this.customOrderService.updateByMapContext(orderMapContext);
		}


		//修改支付记录状态  已支付
			if(customOrder.getPaymentWithholding()!=null&&customOrder.getPaymentWithholding()==1){
				//查询代扣的支付信息记录
				MapContext paramss=MapContext.newOne();
				paramss.put("orderId",customOrder.getId());
				paramss.put("funds",PaymentFunds.PAYMENT_WITHHOLDING_CHARGE.getValue());
				withholdingPayment=this.paymentService.findByOrderIdAndFunds(paramss);
				//修改订单货款扣款记录表信息
				MapContext updatePayment = new MapContext();
				updatePayment.put(WebConstant.KEY_ENTITY_ID, id);
				if (auditType.equals("0")) {
					updatePayment.put(WebConstant.KEY_ENTITY_STATUS, PaymentStatus.PAID.getValue());
				} else if (auditType.equals("1")) {
					updatePayment.put(WebConstant.KEY_ENTITY_STATUS, PaymentStatus.ON_CREDIT.getValue());
				}
				updatePayment.put("auditor", map.get("auditor"));
				updatePayment.put("audited", DateUtil.getSystemDate());
				if (map.get("notes") != null && !map.get("notes").equals("")) {
					updatePayment.put("notes", map.get("notes"));
				}
				if (map.get("payTime") != null && !map.get("payTime").equals("")) {
					updatePayment.put("payTime", map.get("payTime"));
				}
				if (map.get("holder") != null && !map.get("holder").equals("")) {
					updatePayment.put("holder", map.get("holder"));
				}
				//现付金额
				//	BigDecimal cashDeduction = map.getTypedValue("cashDeduction", BigDecimal.class);
				if (auditType.equals("0")) {
					if(withholdingPayment.getPayAmount()!=null&&!withholdingPayment.getPayAmount().equals("")){
						updatePayment.put("payAmount", (new BigDecimal(map.get("amountDeduction").toString()).add(new BigDecimal(withholdingPayment.getPayAmount().toString()))));
					}else {
						updatePayment.put("payAmount", map.get("amountDeduction"));
					}
				}
				updatePayment.put("way", PaymentWay.DEALER_ACCOUNT.getValue());
				this.paymentService.updateByMapContext(updatePayment);

				//修改订单代扣款记录表信息
				updatePayment.remove("id");
				updatePayment.put(WebConstant.KEY_ENTITY_ID, withholdingPayment.getId());
				this.paymentService.updateByMapContext(updatePayment);
			}else {
				MapContext paramss=MapContext.newOne();
				paramss.put("orderId",customOrder.getId());
				paramss.put("funds",PaymentFunds.ORDER_FEE_CHARGE.getValue());
				withholdingPayment=this.paymentService.findByOrderIdAndFunds(paramss);
				MapContext updatePayment = new MapContext();
				updatePayment.put(WebConstant.KEY_ENTITY_ID, id);
				if (auditType.equals("0")) {
					updatePayment.put(WebConstant.KEY_ENTITY_STATUS, PaymentStatus.PAID.getValue());
				} else if (auditType.equals("1")) {
					updatePayment.put(WebConstant.KEY_ENTITY_STATUS, PaymentStatus.ON_CREDIT.getValue());
				}
				updatePayment.put("auditor", map.get("auditor"));
				updatePayment.put("audited", DateUtil.getSystemDate());
				if (map.get("notes") != null && !map.get("notes").equals("")) {
					updatePayment.put("notes", map.get("notes"));
				}
				if (map.get("payTime") != null && !map.get("payTime").equals("")) {
					updatePayment.put("payTime", map.get("payTime"));
//			cashPayment.setPayTime(map.getTypedValue("payTime", Date.class));
				}
				if (map.get("holder") != null && !map.get("holder").equals("")) {
					updatePayment.put("holder", map.get("holder"));
//			cashPayment.setHolder(map.getTypedValue("holder", String.class));
				}
				//现付金额
				//	BigDecimal cashDeduction = map.getTypedValue("cashDeduction", BigDecimal.class);
				if (auditType.equals("0")) {
					if(withholdingPayment.getPayAmount()!=null&&!withholdingPayment.getPayAmount().equals("")){
						updatePayment.put("payAmount", (new BigDecimal(map.get("amountDeduction").toString()).add(new BigDecimal(withholdingPayment.getPayAmount().toString()))));
					}else {
						updatePayment.put("payAmount", map.get("amountDeduction"));
					}
				}
				updatePayment.put("way", PaymentWay.DEALER_ACCOUNT.getValue());
				this.paymentService.updateByMapContext(updatePayment);
			}
		if(auditType.equals("0")) {//非挂账，扣除金额
			if(customOrder.getPaymentWithholding()!=null&&customOrder.getPaymentWithholding()==1){//如果为代扣款
				//代扣款经销商信息
				Company byWithholdingId = this.companyService.findById(payCompanyId);
				String withholdingDealerName = byWithholdingId.getName();
				//订单实际经销商信息
				Company byId = this.companyService.findById(payment.getCompanyId());
				String dealerName = byId.getName();
				//扣除代扣经销商账户金额（订单实际金额）
				MapContext withholdingDealerAccount = new MapContext();
				withholdingDealerAccount.put(WebConstant.KEY_ENTITY_ID, dealerAccount.getId());
//				//如果有定金，则扣除定金以外的金额
				BigDecimal earnest=new BigDecimal("0");
				if(customOrder.getEarnest()!=null&&!customOrder.getEarnest().equals("")){
					earnest= new BigDecimal(customOrder.getEarnest().toString());
				}
				BigDecimal subtract = customOrder.getFactoryFinalPrice().subtract(earnest);
				withholdingDealerAccount.put("balance", dealerAccount.getBalance().subtract((subtract)));
				this.dealerAccountService.updateByMapContext(withholdingDealerAccount);
				//记录账户变动日志
				DealerAccountLog withholdingdealerAccountLog = new DealerAccountLog();
				withholdingdealerAccountLog.setAmount(amountDeduction);
				withholdingdealerAccountLog.setDealerAccountId(dealerAccount.getId());
				withholdingdealerAccountLog.setContent(dateFormat.format(DateUtil.getSystemDate()) + loginName + "审核经销商"+dealerName+"订单" + customOrder.getNo() + "," + "此订单为货款代扣订单，货款由代扣经销商" + withholdingDealerName + "自由资金账户扣除，代扣金额为：" + subtract + ",之前账户余额为：" + dealerAccount.getBalance() + ",剩余金额为：" + dealerAccount.getBalance().subtract((subtract)));
				withholdingdealerAccountLog.setType(DealerAccountLogType.ORDER_PAYMENT_WITHHOLDING.getValue());
				withholdingdealerAccountLog.setCreator(WebUtils.getCurrUserId());
				withholdingdealerAccountLog.setCreated(DateUtil.getSystemDate());
				withholdingdealerAccountLog.setDisburse(false);
				withholdingdealerAccountLog.setResourceId(customOrder.getId());
				withholdingdealerAccountLog.setCompanyId(WebUtils.getCurrCompanyId());
				this.dealerAccountLogService.add(withholdingdealerAccountLog);
				logger.info(dateFormat.format(DateUtil.getSystemDate()) + loginName + "审核经销商"+dealerName+"订单" + customOrder.getNo() + "," + "此订单为货款代扣订单，货款由代扣经销商" + withholdingDealerName + "自由资金账户扣除，代扣金额为：" + subtract + ",之前账户余额为：" + dealerAccount.getBalance() + ",剩余金额为：" + dealerAccount.getBalance().subtract((subtract)));

			}else {
//				//如果有定金，则扣除定金以外的金额
				BigDecimal earnest=new BigDecimal("0");
				if(customOrder.getEarnest()!=null&&!customOrder.getEarnest().equals("")){
					earnest= new BigDecimal(customOrder.getEarnest().toString());
				}
				BigDecimal subtract = customOrder.getFactoryFinalPrice().subtract(earnest);
				//扣除经销商账户金额
				MapContext updateDealerAccount = new MapContext();
				updateDealerAccount.put(WebConstant.KEY_ENTITY_ID, dealerAccount.getId());
				updateDealerAccount.put("balance", dealerAccount.getBalance().subtract((subtract)));
				this.dealerAccountService.updateByMapContext(updateDealerAccount);
				//记录账户变动日志
				DealerAccountLog dealerAccountLog = new DealerAccountLog();
				Company byId = this.companyService.findById(payCompanyId);
				String dealerName = byId.getName();
				dealerAccountLog.setAmount(amountDeduction);
				dealerAccountLog.setDealerAccountId(dealerAccount.getId());
				dealerAccountLog.setContent(dateFormat.format(DateUtil.getSystemDate()) + loginName + "审核订单" + customOrder.getNo() + "," + "经销商" + dealerName + "自由资金账户扣除" + customOrder.getFactoryFinalPrice() + ",之前账户余额为：" + dealerAccount.getBalance() + ",剩余金额为：" + dealerAccount.getBalance().subtract((customOrder.getFactoryFinalPrice())));
				dealerAccountLog.setType(DealerAccountLogType.ORDER_DEDUCTION.getValue());
				dealerAccountLog.setCreator(WebUtils.getCurrUserId());
				dealerAccountLog.setCreated(DateUtil.getSystemDate());
				dealerAccountLog.setDisburse(false);
				dealerAccountLog.setResourceId(customOrder.getId());
				dealerAccountLog.setCompanyId(WebUtils.getCurrCompanyId());
				this.dealerAccountLogService.add(dealerAccountLog);
				logger.info(dateFormat.format(DateUtil.getSystemDate()) + loginName + "审核订单" + customOrder.getNo() + "," + "经销商" + dealerName + "自由资金账户扣除" + customOrder.getFactoryFinalPrice() + ",之前账户余额为：" + dealerAccount.getBalance() + ",剩余金额为：" + dealerAccount.getBalance().subtract((customOrder.getFactoryFinalPrice())));
			}
			//经销商添加积分
			CompanyDto companyDto=this.companyService.findCompanyById(customOrder.getCompanyId());
			MapContext companyMap=MapContext.newOne();
			companyMap.put("id",customOrder.getCompanyId());
			companyMap.put("integral",companyDto.getIntegral().add(customOrder.getFactoryFinalPrice()));
			this.companyService.updateByMapContext(companyMap);
		}
		//记录操作日志
		//正常订单操作日志，售后单不保存操作日志
//		if(payment.getResourceType()==0) {
		CustomOrder order = customOrderService.findByOrderId(customOrderId);
		if (order != null) {
			CustomOrderLog log = new CustomOrderLog();
			log.setCreated(new Date());
			log.setCreator(WebUtils.getCurrUserId());
			log.setName("审核");
			log.setStage(OrderStage.TO_EXAMINE.getValue());
			log.setContent("订单号：" + order.getNo() + "已由" + WebUtils.getCurrUser().getName() + "审核扣款");
			log.setCustomOrderId(order.getId());
			customOrderLogService.add(log);
		}
			//公众号消息通知
			String companyId = customOrder.getCompanyId();
			CompanyDto companyById = companyService.findCompanyById(companyId);
			if(companyById!=null){
				String leader = companyById.getLeader();
				UserThirdInfo byUserId = AppBeanInjector.userThirdInfoService.findByUserId(leader);
				if(byUserId!=null){
					//查询负责人的公众号openId是否为空
					if(byUserId.getOfficialOpenId()!=null&&!byUserId.getOfficialOpenId().equals("")){
						MapContext msg=MapContext.newOne();
						msg.put("orderId",payment.getCustomOrderId());
						msg.put("orderStatus",2);
						msg.put("first","您好，您的订单已确认，即将为您安排生产...");
						msg.put("keyword1",customOrder.getNo());
						msg.put("keyword2",amountDeduction);
						msg.put("keyword3",customOrder.getConsigneeName());
						msg.put("keyword4",customOrder.getConsigneeTel());
						msg.put("keyword5",customOrder.getAddress());
						msg.put("remark","感谢您的支持");
						String openId=byUserId.getOfficialOpenId();
						weiXinMsgPush.SendWxMsg(openId,msg);
					}
				}
			}
		}

		return ResultFactory.generateSuccessResult();
	}

	@Override
	public RequestResult findOrderFinanceInfo(String paymentId) {
		PaymentDto paymentDto = this.paymentService.findOrderFinanceInfo(paymentId);
		paymentDto.setPaymentFilesList(this.paymentFilesService.findByPaymentId(paymentId));
		return ResultFactory.generateRequestResult(paymentDto);
	}

	@Override
	public RequestResult findPaymentInfo(String id) {
		//判断支付记录是否存在
		PaymentPrintTableDto byPaymentId = this.paymentService.findPrintTableById(id);
		return ResultFactory.generateRequestResult(byPaymentId);
	}

	@Override
	public RequestResult findFinanceOverview() {
		MapContext mapContext = new MapContext();
		String branchId = WebUtils.getCurrBranchId();
		MapContext param = MapContext.newOne();
		param.put("status", PaymentStatus.PENDING_PAYMENT.getValue());
		param.put("branchId", branchId);
		//待审核
		Integer toBeAudited = this.paymentService.findCountByStatus(param);
		mapContext.put("toBeAudited", toBeAudited);
		//近三天待审核
		param.put("dateType", 0);
		Integer threeBeAudited = this.paymentService.findCountByStatus(param);
		//4-7天待审核
		param.put("dateType", 1);
		Integer sevenBeAudited = this.paymentService.findCountByStatus(param);
		//8-30天待审核
		param.put("dateType", 2);
		Integer thirtyBeAudited = this.paymentService.findCountByStatus(param);
		//8-30天待审核
		param.put("dateType", 3);
		Integer afterThirtyBeAudited = this.paymentService.findCountByStatus(param);
		// 挂账订单总额
		MapContext mapParam = MapContext.newOne();
        mapParam.put("branchId", branchId);
        mapParam.put("status", PaymentStatus.ON_CREDIT.getValue());
        MapContext re = this.paymentService.findAmountByStatus(mapParam);

		MapContext result = MapContext.newOne();
		result.put("toBeAudited", toBeAudited);
		result.put("threeBeAudited", threeBeAudited);
		result.put("sevenBeAudited", sevenBeAudited);
		result.put("thirtyBeAudited", thirtyBeAudited);
		result.put("afterThirtyBeAudited", afterThirtyBeAudited);
		if(re!=null){
			result.put("noCredit", String.format("%,.2f", re.getTypedValue("noCredit", Double.class)));
		}else {
			result.put("noCredit", 0.00);
		}



		//今日已审核
		//mapContext.put("expenditureAmount", this.paymentService.findTodayAmountByType(PaymentTypeNew.COOPERATE_PAY.getValue()));
//		Integer beAudited = this.paymentService.findTodayCountByStatus(PaymentStatus.PAID.getValue(), branchId);
//		mapContext.put("beAudited", beAudited);
//		//审核总数
//		Integer allBeAudited = this.paymentService.findAllCountByStatus(PaymentStatus.PAID.getValue(), branchId);
//		mapContext.put("allBeAudited", allBeAudited);
//
//
//		PaginatedFilter paginatedFilter = new PaginatedFilter();
//		Pagination pagination = new Pagination();
//		pagination.setPageNum(1);
//		pagination.setPageSize(99999);
//		paginatedFilter.setPagination(pagination);
//		//今日收入笔数
//		MapContext filter1 = new MapContext();
//		filter1.put("branchId", branchId);
//		filter1.put("type", PaymentTypeNew.CHARGEBACK.getValue());
//		filter1.put("payTimeNow", 1);
//		paginatedFilter.setFilters(filter1);
//		mapContext.put("incomeCount", this.paymentService.findListByPaginateFilter(paginatedFilter).getRows().size());
//		//今日支出笔数
//		MapContext filter2 = new MapContext();
//		filter2.put("branchId", branchId);
//		filter2.put("type", PaymentTypeNew.COOPERATE_PAY.getValue());
//		filter2.put("payTimeNow", 1);
//		paginatedFilter.setFilters(filter2);
//		mapContext.put("expenditureCount", this.paymentService.findListByPaginateFilter(paginatedFilter).getRows().size());
		return ResultFactory.generateRequestResult(result);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult uploadFiles(String id, List<MultipartFile> multipartFileList) {
		//添加图片到本地
		List<MapContext> listUrls = new ArrayList<>();
		if (multipartFileList != null && multipartFileList.size() > 0) {
			for (MultipartFile multipartFile : multipartFileList) {
				UploadInfo uploadInfo = AppBeanInjector.baseFileUploadComponent.doUploadByModule(UploadType.FORMAL, multipartFile, UploadResourceType.PAYMENT, id);
				//添加图片到数据库
				PaymentFiles paymentFiles = new PaymentFiles();
				paymentFiles.setCreated(DateUtil.getSystemDate());
				paymentFiles.setFullPath(WebUtils.getDomainUrl() + uploadInfo.getRelativePath());
				paymentFiles.setMime(uploadInfo.getFileMimeType().getRealType());
				paymentFiles.setName(uploadInfo.getFileName());
				paymentFiles.setOriginalMime(uploadInfo.getFileMimeType().getOriginalType());
				paymentFiles.setPath(uploadInfo.getRelativePath());
				paymentFiles.setStatus(1);
				paymentFiles.setCreator(WebUtils.getCurrUserId());
				paymentFiles.setPayment(id);
				this.paymentFilesService.add(paymentFiles);
				MapContext map = MapContext.newOne();
				map.put("fullPath", paymentFiles.getFullPath());
				map.put("imgRelPath", uploadInfo.getRelativePath());
				map.put("id", paymentFiles.getId());
				listUrls.add(map);
			}
		}
		return ResultFactory.generateRequestResult(listUrls);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult deleteFile(String fileId) {
		//删除图片资源
		PaymentFiles paymentFiles = this.paymentFilesService.findById(fileId);
		if (paymentFiles == null) {
			return ResultFactory.generateSuccessResult();
		}
		//删除数据库图片资源
		this.paymentFilesService.deleteByPaymentId(fileId);
		//清楚本地文件
		AppBeanInjector.baseFileUploadComponent.deleteFileByDir(paymentFiles.getFullPath());
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult counterTrialOrdersPay(String id) {
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		User logUser=AppBeanInjector.userService.findByUserId(WebUtils.getCurrUserId());
		String loginName=logUser.getName();
		//支付单是否存在
		PaymentDto byPaymentId = this.paymentService.findByPaymentId(id);
		//查询订单id和产品单
		String orderId = byPaymentId.getCustomOrderId();
		CustomOrderDto byOrderId = this.customOrderService.findByOrderId(orderId);
		List<OrderProductDto> listByOrderId = this.orderProductService.findListByOrderId(orderId);
		//订单已审核且没有开始生产，允许操作，否则不允许
		if (byOrderId.getStatus() != OrderStatus.TO_PRODUCED.getValue()) {
			Integer a = 0;
			for (OrderProductDto productDto : listByOrderId) {
				if (productDto.getStatus() == OrderProductStatus.PRODUCING.getValue()) {
					a = a + 1;
				}
			}
			if (a > 0) {
				return ResultFactory.generateErrorResult(ErrorCodes.BIZ_NOT_ALLOW_OPERATION_10020, AppBeanInjector.i18nUtil.getMessage("BIZ_NOT_ALLOW_OPERATION_10020"));
			}
		}
		Integer paymentWithholding = byOrderId.getPaymentWithholding();
		//修改订单状态
		Branch branch = AppBeanInjector.branchService.findById(WebUtils.getCurrBranchId());
		if(branch.getEnableRemind()==1) {//如果启用催款
			MapContext map1 = MapContext.newOne();
			map1.put("id", orderId);
			map1.put("status", OrderStatus.TO_PAID.getValue());
			this.customOrderService.updateByMapContext(map1);
			//修改支付单
			MapContext map2 = MapContext.newOne();
			map2.put("id", id);
			map2.put("status", PaymentStatus.PENDING_PAYMENT.getValue());
			if(byOrderId.getEarnest()!=null&&!byOrderId.getEarnest().equals("")){
				map2.put("payAmount", new BigDecimal(byOrderId.getEarnest().toString()));
			}else {
				map2.put("payAmount", new BigDecimal(0));
			}
			this.paymentService.updateByMapContext(map2);
			//如果代扣款，则修改代扣款记录
			if(paymentWithholding!=null&&paymentWithholding==1){
				MapContext mapcon=MapContext.newOne();
				mapcon.put("orderId",orderId);
				mapcon.put("funds",PaymentFunds.PAYMENT_WITHHOLDING_CHARGE.getValue());
				PaymentDto byOrderIdAndFunds = this.paymentService.findByOrderIdAndFunds(mapcon);
				if(byOrderIdAndFunds!=null){
					MapContext map3 = MapContext.newOne();
					map3.put("id", byOrderIdAndFunds.getId());
					map3.put("status", PaymentStatus.PENDING_PAYMENT.getValue());
					if(byOrderId.getEarnest()!=null&&!byOrderId.getEarnest().equals("")){
						map3.put("payAmount", new BigDecimal(byOrderId.getEarnest().toString()));
					}else {
						map3.put("payAmount", new BigDecimal(0));
					}
					this.paymentService.updateByMapContext(map3);
				}
			}
		}else {
			MapContext map1 = MapContext.newOne();
			map1.put("id", orderId);
			map1.put("status", OrderStatus.TO_QUOTED.getValue());
			this.customOrderService.updateByMapContext(map1);
			//删除财务记录
			this.paymentService.deleteById(id);
			//如果代扣款，则修改代扣款记录
			if(paymentWithholding!=null&&paymentWithholding==1){
				MapContext mapcon=MapContext.newOne();
				mapcon.put("orderId",orderId);
				mapcon.put("funds",PaymentFunds.PAYMENT_WITHHOLDING_CHARGE.getValue());
				PaymentDto byOrderIdAndFunds = this.paymentService.findByOrderIdAndFunds(mapcon);
				if(byOrderIdAndFunds!=null){
					this.paymentService.deleteById(byOrderIdAndFunds.getId());
				}
			}
		}

		//修改产品状态
		for (OrderProductDto productDto : listByOrderId) {
			MapContext mapContext = MapContext.newOne();
			mapContext.put("id", productDto.getId());
			mapContext.put("status", OrderProductStatus.TO_PAYMENT.getValue());
			this.orderProductService.updateByMapContext(mapContext);
			//删除生产单
			this.produceOrderService.deleteByProductId(productDto.getId());
		}
		//	经销商账户恢复金额
		if(byPaymentId.getPayAmount()!=null&&!byPaymentId.getPayAmount().equals("")) {
			DealerAccount dealerAccount = this.dealerAccountService.findByCompanIdAndNatureAndType(byPaymentId.getCompanyId(), DealerAccountNature.PUBLIC.getValue(), DealerAccountType.FREE_ACCOUNT.getValue());
			MapContext updateDealerAccount = new MapContext();
			updateDealerAccount.put(WebConstant.KEY_ENTITY_ID, dealerAccount.getId());
			BigDecimal bigDecimal2 = new BigDecimal("0");
			if(byOrderId.getEarnest()!=null&&!byOrderId.getEarnest().equals("")){
				bigDecimal2=new BigDecimal(byOrderId.getEarnest().toString());
			}
			BigDecimal subtract = byPaymentId.getPayAmount().subtract(bigDecimal2);
			updateDealerAccount.put("balance", dealerAccount.getBalance().add(subtract));
			this.dealerAccountService.updateByMapContext(updateDealerAccount);
			//记录经销商自由资金账户变动日志
			DealerAccountLog dealerAccountLog = new DealerAccountLog();
			Company byId = this.companyService.findById(dealerAccount.getCompanyId());
			String dealerName = byId.getName();
			dealerAccountLog.setAmount(byPaymentId.getPayAmount());
			dealerAccountLog.setDealerAccountId(dealerAccount.getId());
			dealerAccountLog.setContent(dateFormat.format(DateUtil.getSystemDate()) + loginName + "反审订单" + byOrderId.getNo() + "," + dealerName + "经销商自由资金账户返还" + subtract + ",之前账户余额为：" + dealerAccount.getBalance() + "，返还后账户余额为：" + dealerAccount.getBalance().add((subtract)));
			dealerAccountLog.setType(DealerAccountLogType.TO_CHANGE_INTO.getValue());
			dealerAccountLog.setCreator(WebUtils.getCurrUserId());
			dealerAccountLog.setCreated(DateUtil.getSystemDate());
			dealerAccountLog.setDisburse(false);
			dealerAccountLog.setResourceId(orderId);
			dealerAccountLog.setCompanyId(WebUtils.getCurrCompanyId());
			this.dealerAccountLogService.add(dealerAccountLog);
			logger.info(dateFormat.format(DateUtil.getSystemDate()) + loginName + "反审订单" + byOrderId.getNo() + "," + dealerName + "经销商自由资金账户返还" + subtract + ",之前账户余额为：" + dealerAccount.getBalance() + "，返还后账户余额为：" + dealerAccount.getBalance().add((subtract)));
			//经销商积分扣除
			CompanyDto companyDto = this.companyService.findCompanyById(byOrderId.getCompanyId());
			if (companyDto != null) {
				MapContext companyMap = MapContext.newOne();
				companyMap.put("id", companyDto.getId());
				BigDecimal bigDecimal = new BigDecimal(companyDto.getIntegral().toString());
				BigDecimal bigDecimal1 = subtract;
				companyMap.put("integral", bigDecimal.subtract(bigDecimal1));
				this.companyService.updateByMapContext(companyMap);
			}
		}
		//删除审核日志
		List<CustomOrderLogDto> byOrderIdAndState = this.customOrderLogService.findByOrderIdAndState(byPaymentId.getCustomOrderId(), OrderStage.TO_EXAMINE.getValue());
		if(byOrderIdAndState.size()>0) {
			for (CustomOrderLogDto dto : byOrderIdAndState) {
				this.customOrderLogService.deleteById(dto.getId());
			}
		}
		return ResultFactory.generateSuccessResult();
	}

	@Override
	public RequestResult findDealerPaymentCountInfo(String timeType, String startTime, String endTime) {
		//订单总数和订单总金额
		String dealerId=WebUtils.getCurrCompanyId();
		MapContext map=this.paymentService.findDealerPaymentCount(dealerId);
		//本月产品金额统计
		List products=new ArrayList();
		List<Basecode> basecodes=this.basecodeService.findByTypeAndDelFlag("orderProductType",0);
		for(Basecode basecode:basecodes){
			MapContext productMoney=MapContext.newOne();
			String productType = basecode.getCode();
			double count=this.orderProductService.findProductMoneyByType(dealerId,productType);
			productMoney.put("name",basecode.getValue());
			productMoney.put("price",String.format("%.2f", count));
			products.add(productMoney);
		}
        //产品金额走势图
		MapContext params = MapContext.newOne();
		Date startDate = new Date();
		Date endDate = new Date();
		if(startTime==null||startTime.equals("")||endTime==null||endTime.equals("")) {
			if (timeType == null || timeType.equals("")) {
				timeType = "1";
			}
		}
		if (timeType != null && !timeType.equals("")) {
			if (timeType.equals("1")) {
				startDate = DateUtilsExt.getFirstDayOfMonth(com.lwxf.mybatis.utils.DateUtil.now());
				endDate = com.lwxf.mybatis.utils.DateUtil.now();
			} else {
				startDate = DateUtilsExt.getFirstDayOfYear(com.lwxf.mybatis.utils.DateUtil.now());
				endDate = com.lwxf.mybatis.utils.DateUtil.now();
			}
		}
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			if (startTime != null && !startTime.equals("")) {
				startDate = format.parse(startTime);
			}
			if (endTime != null && !endTime.equals("")) {
				endDate = format.parse(endTime);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		params.put("companyId", dealerId);
		params.put("timeType", timeType);
		List<Date> dates = new ArrayList<>();
		if (timeType != null && timeType.equals("2")) {
			try {
				dates = DateUtilsExt.getMonthBetween(startDate, endDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			dates = DateUtilsExt.findDates(startDate, endDate);
		}
		List<MapContext> productsTrends = new ArrayList();
		for (Date date : dates) {
			params.put("date", date);
			List list=new ArrayList();
			for (Basecode basecode : basecodes) {
				MapContext productMoney=MapContext.newOne();
				String productType = basecode.getCode();
				params.put("type",productType);
				double count = this.orderProductService.findProductPriceTrendByTime(params);
				productMoney.put("name",basecode.getValue());
				productMoney.put("price",String.format("%.2f", count));
				list.add(productMoney);
			}
			MapContext productPriceTrend=MapContext.newOne();
			productPriceTrend.put("list",list);
				if (timeType != null && timeType.equals("2")) {
					SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
					productPriceTrend.put("dateTime", dateFormat.format(date));
				} else {
					SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd");
					productPriceTrend.put("dateTime", dateFormat.format(date));
				}
				productsTrends.add(productPriceTrend);
		}
		//返回结果
		MapContext result=MapContext.newOne();
		result.put("orderNumAndPrice",map);
		result.put("products",products);
		result.put("productsTrends",productsTrends);
		return ResultFactory.generateRequestResult(result);
	}

	@Override
	public RequestResult findOrderFinanceCountinfo(MapContext mapContext) {
		mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID, WebUtils.getCurrBranchId());
		List<MapContext> orderFinanceCountinfo = this.paymentService.findOrderFinanceCountinfo(mapContext);
		Map<String,Object> result=new HashMap<>();
		Integer ordernum=0;
		BigDecimal totalAmountValue=new BigDecimal("0.00").setScale(2,RoundingMode.HALF_UP);
		BigDecimal paidAmountValue=new BigDecimal("0.00").setScale(2,RoundingMode.HALF_UP);
		BigDecimal unpaidValue=new BigDecimal("0.00").setScale(2,RoundingMode.HALF_UP);
		BigDecimal arrearsValue=new BigDecimal("0.00").setScale(2,RoundingMode.HALF_UP);
		for(MapContext map:orderFinanceCountinfo){
			Integer orderNum = map.getTypedValue("orderNum", Integer.class);
			BigDecimal totalAmount = map.getTypedValue("totalAmount", BigDecimal.class).setScale(2,RoundingMode.HALF_UP);
			BigDecimal paidAmount = map.getTypedValue("paidAmount", BigDecimal.class).setScale(2,RoundingMode.HALF_UP);
			ordernum=ordernum+orderNum;
			totalAmountValue=totalAmountValue.add(totalAmount);
			paidAmountValue=paidAmountValue.add(paidAmount);
			Integer status = map.getTypedValue("status", Integer.class);
			if(status==0){
				unpaidValue=unpaidValue.add(totalAmount).setScale(2,RoundingMode.HALF_UP);
			}
			if(status==3){
				arrearsValue=arrearsValue.add(totalAmount.subtract(paidAmount)).setScale(2,RoundingMode.HALF_UP);
			}
		}
		String total=String.format("%.2f", totalAmountValue);
		String paid=String.format("%.2f", paidAmountValue);
		String unpaid=String.format("%.2f", unpaidValue);
		String arrears=String.format("%.2f", arrearsValue);
		result.put("ordernum",ordernum);
		result.put("total",total);
		result.put("paid",paid);
		result.put("unpaid",unpaid);
		result.put("arrears",arrears);
		return ResultFactory.generateRequestResult(result);
	}

	@Override
	public RequestResult writePayemntList(MapContext mapContext, Integer pageNum, Integer pageSize, PaymentExcelParam paymentExcelParam, StringBuffer params) {
		//查询是否开启催款功能
		Branch branch = AppBeanInjector.branchService.findById(WebUtils.getCurrBranchId());
		Integer enableRemind = branch.getEnableRemind();
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		Pagination pagination = new Pagination();
		pagination.setPageNum(pageNum);
		pagination.setPageSize(pageSize);
		paginatedFilter.setPagination(pagination);
		mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID, WebUtils.getCurrBranchId());
		paginatedFilter.setFilters(mapContext);
		List<Map<String, String>> sort = new ArrayList<Map<String, String>>();
		Map<String, String> status = new HashMap<String, String>();
		status.put(WebConstant.KEY_ENTITY_STATUS, "asc");
		sort.add(status);
		Map<String, String> created = new HashMap<String, String>();
		created.put(WebConstant.KEY_ENTITY_CREATED, "desc");
		sort.add(created);
		paginatedFilter.setSorts(sort);
		PaginatedList<PaymentDto> listByPaginateFilter = this.paymentService.findListByPaginateFilter(paginatedFilter);
		List<Map<String, Object>> result = new ArrayList<>();
		BigDecimal amount=new BigDecimal("0.00");
		if(listByPaginateFilter.getRows().size()>0) {
			for (PaymentDto paymentDto : listByPaginateFilter.getRows()) {
				Map map=new HashMap();
				//excel表头
				map.put("params",params);//查询参数
				map.put("orderNum",listByPaginateFilter.getPagination().getTotalCount());//导出订单数量
				amount =amount.add( paymentDto.getAmount());//合计金额
				map.put("amountAll",amount);

				//excel标题值
				map.put("orderNo",paymentDto.getOrderNo());
				map.put("companyName",paymentDto.getCompanyName());
				map.put("amount",paymentDto.getAmount());
				map.put("payAmount",paymentDto.getPayAmount());
				map.put("fundsName",paymentDto.getFundsName());
				map.put("customName",paymentDto.getCustomName());
				map.put("audited",paymentDto.getAudited());
				result.add(map);
			}
		}
		new BaseExportExcelUtil().download("财务审核明细",result,paymentExcelParam);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult fastQuitCustomOrdersPay(String id, MapContext map) {
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//查询登陆人名称
		String loginName="";
		if(WebUtils.getCurrUserId()!=null) {
			loginName= AppBeanInjector.userService.findByUserId(WebUtils.getCurrUserId()).getName();
		}else {
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED,AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
		}
		PaymentDto byPaymentId = this.paymentService.findByPaymentId(id);
		if(byPaymentId==null){
			return ResultFactory.generateResNotFoundResult();
		}
		//查询相关订单
		String customOrderId = byPaymentId.getCustomOrderId();
		CustomOrder customOrder = this.customOrderService.findById(customOrderId);
		if(customOrder==null){
			return ResultFactory.generateResNotFoundResult();
		}
		//充值的经销商
		String companyId1 = map.getTypedValue("companyId", String.class);
		BigDecimal amount = map.getTypedValue("amount", BigDecimal.class);
		//给经销商自由资金账户充值
		DealerAccount freeDealerAccount =this.dealerAccountService.findByCompanIdAndNatureAndType(companyId1, DealerAccountNature.PUBLIC.getValue(), DealerAccountType.FREE_ACCOUNT.getValue());
		MapContext mapContext=MapContext.newOne();
		mapContext.put("id",freeDealerAccount.getId());
		mapContext.put("balance",freeDealerAccount.getBalance().add(amount));
		this.dealerAccountService.updateByMapContext(mapContext);
		//记录账户变动日志
		DealerAccountLog dealerAccountLog=new DealerAccountLog();
		Company byId = this.companyService.findById(companyId1);
		String dealerName=byId.getName();
		dealerAccountLog.setAmount(amount);
		dealerAccountLog.setDealerAccountId(freeDealerAccount.getId());
		dealerAccountLog.setContent(dateFormat.format(DateUtil.getSystemDate())+loginName+"给经销商【"+dealerName+"】自由资金账户充值"+amount+",之前账户余额为："+freeDealerAccount.getBalance()+",剩余金额为："+freeDealerAccount.getBalance().add(amount));
		dealerAccountLog.setType(DealerAccountLogType.TO_CHANGE_INTO.getValue());
		dealerAccountLog.setCreator(WebUtils.getCurrUserId());
		dealerAccountLog.setCreated(DateUtil.getSystemDate());
		dealerAccountLog.setDisburse(false);
		dealerAccountLog.setResourceId(byPaymentId.getCustomOrderId());
		dealerAccountLog.setCompanyId(WebUtils.getCurrCompanyId());
		this.dealerAccountLogService.add(dealerAccountLog);
		logger.info(dateFormat.format(DateUtil.getSystemDate())+loginName+"给经销商【"+dealerName+"】自由资金账户充值"+amount+",之前账户余额为："+freeDealerAccount.getBalance()+",剩余金额为："+freeDealerAccount.getBalance().add(amount));
		//生成充值记录
		Payment payment = new Payment();
		payment.setWay(map.getTypedValue("way",Integer.class));
		payment.setFunds(PaymentFunds.FREE_FUNDS.getValue());
		payment.setType(PaymentTypeNew.INCOME.getValue());
		payment.setCreated(DateUtil.getSystemDate());
		payment.setCreator(WebUtils.getCurrUserId());
		payment.setStatus(1);
		payment.setCompanyId(companyId1);
		payment.setHolder(map.getTypedValue("holder",String.class));
		payment.setAmount(map.getTypedValue("amount",BigDecimal.class));
		payment.setBranchId(WebUtils.getCurrBranchId());
		payment.setBank(map.getTypedValue("bank",String.class));
		payment.setAudited(DateUtil.getSystemDate());
		payment.setAuditor(WebUtils.getCurrUserId());
		payment.setNo(AppBeanInjector.uniquneCodeGenerator.getNextNo(UniquneCodeGenerator.UniqueResource.PAYMENT_NO));
		payment.setFlag(0);
		paymentService.add(payment);

		//财务审核扣款
		BigDecimal countAmount = new BigDecimal(0);
		if(byPaymentId.getPayAmount()==null||byPaymentId.getPayAmount().equals("")){
			countAmount=amount;
		}else {
			countAmount=byPaymentId.getPayAmount().add(amount);
		}
		MapContext mapCon=MapContext.newOne();
		mapCon.put("id",id);
		BigDecimal needSubtract=new BigDecimal("0");
		BigDecimal allHasPay=new BigDecimal("0");
		BigDecimal payAmount = byPaymentId.getPayAmount();
		if(payAmount==null||payAmount.equals("")){
			payAmount=new BigDecimal("0");
		}
		if(countAmount.compareTo(byPaymentId.getAmount())==0||countAmount.compareTo(byPaymentId.getAmount())==1){
			needSubtract=byPaymentId.getAmount().subtract(payAmount);
			allHasPay=byPaymentId.getAmount();
			mapCon.put("payAmount",byPaymentId.getAmount());
			mapCon.put("status",PaymentStatus.PAID.getValue());
		}else {
			needSubtract=amount;
			allHasPay=countAmount;
			mapCon.put("payAmount",countAmount);
			mapCon.put("status",PaymentStatus.ON_CREDIT.getValue());
		}
		mapCon.put("audited",new Date());
		mapCon.put("auditor",WebUtils.getCurrUserId());
		//扣除经销商账户金额(如果充值的钱加上已付的钱大于等于订单需要支付的钱，则只需要扣除订单所需要的钱)
		freeDealerAccount =this.dealerAccountService.findByCompanIdAndNatureAndType(companyId1, DealerAccountNature.PUBLIC.getValue(), DealerAccountType.FREE_ACCOUNT.getValue());
		MapContext updateDealerAccount = new MapContext();
		updateDealerAccount.put(WebConstant.KEY_ENTITY_ID, freeDealerAccount.getId());
		updateDealerAccount.put("balance", freeDealerAccount.getBalance().subtract(needSubtract));
		this.dealerAccountService.updateByMapContext(updateDealerAccount);
		if(customOrder.getPaymentWithholding()==0) {//非代扣
			this.paymentService.updateByMapContext(mapCon);
			//记录账户变动日志
			DealerAccountLog dealerAccountLogTwo = new DealerAccountLog();
			dealerAccountLogTwo.setAmount(amount);
			dealerAccountLogTwo.setDealerAccountId(freeDealerAccount.getId());
			dealerAccountLogTwo.setContent(dateFormat.format(DateUtil.getSystemDate()) + loginName + "审核订单" + customOrder.getNo() + "," + "经销商" + dealerName + "自由资金账户扣除" + needSubtract + ",之前账户余额为：" + freeDealerAccount.getBalance() + ",剩余金额为：" + freeDealerAccount.getBalance().subtract((needSubtract)));
			dealerAccountLogTwo.setType(DealerAccountLogType.ORDER_DEDUCTION.getValue());
			dealerAccountLogTwo.setCreator(WebUtils.getCurrUserId());
			dealerAccountLogTwo.setCreated(DateUtil.getSystemDate());
			dealerAccountLogTwo.setDisburse(false);
			dealerAccountLogTwo.setResourceId(byPaymentId.getCustomOrderId());
			dealerAccountLogTwo.setCompanyId(WebUtils.getCurrCompanyId());
			this.dealerAccountLogService.add(dealerAccountLogTwo);
			logger.info(dateFormat.format(DateUtil.getSystemDate()) + loginName + "审核订单" + customOrder.getNo() + "," + "经销商" + dealerName + "自由资金账户扣除" + needSubtract + ",之前账户余额为：" + freeDealerAccount.getBalance() + ",剩余金额为：" + freeDealerAccount.getBalance().subtract((needSubtract)));
		}else {
			//查询代扣的支付信息记录
			MapContext paramss=MapContext.newOne();
			paramss.put("orderId",customOrder.getId());
			paramss.put("funds",PaymentFunds.PAYMENT_WITHHOLDING_CHARGE.getValue());
			Payment withholdingPayment=this.paymentService.findByOrderIdAndFunds(paramss);
			MapContext mapContextTwo=MapContext.newOne();
			mapContextTwo.put("id",withholdingPayment.getId());
			mapContextTwo.put("payAmount",allHasPay);
			MapContext param1=MapContext.newOne();
			param1.put("payAmount",allHasPay);
			param1.put("id",id);
			if(countAmount.compareTo(withholdingPayment.getAmount())==0||countAmount.compareTo(withholdingPayment.getAmount())==1){
				mapContextTwo.put("status",PaymentStatus.PAID.getValue());
				param1.put("status",PaymentStatus.PAID.getValue());
			}
			mapContextTwo.put("audited",new Date());
			mapContextTwo.put("auditor",WebUtils.getCurrUserId());
			param1.put("audited",new Date());
			param1.put("auditor",WebUtils.getCurrUserId());
			this.paymentService.updateByMapContext(param1);
			this.paymentService.updateByMapContext(mapContextTwo);
			//记录代扣账户变动日志
			String companyId = customOrder.getCompanyId();
			CompanyDto companyById = this.companyService.findCompanyById(companyId);//原经销商信息
			DealerAccountLog withholdingdealerAccountLog = new DealerAccountLog();
			withholdingdealerAccountLog.setAmount(needSubtract);
			withholdingdealerAccountLog.setDealerAccountId(freeDealerAccount.getId());
			withholdingdealerAccountLog.setContent(dateFormat.format(DateUtil.getSystemDate()) + loginName + "审核经销商"+companyById.getName()+"订单" + customOrder.getNo() + "," + "此订单为货款代扣订单，货款由代扣经销商" + dealerName + "自由资金账户扣除，代扣金额为：" + needSubtract + ",之前账户余额为：" + freeDealerAccount.getBalance() + ",剩余金额为：" + freeDealerAccount.getBalance().subtract((needSubtract)));
			withholdingdealerAccountLog.setType(DealerAccountLogType.ORDER_PAYMENT_WITHHOLDING.getValue());
			withholdingdealerAccountLog.setCreator(WebUtils.getCurrUserId());
			withholdingdealerAccountLog.setCreated(DateUtil.getSystemDate());
			withholdingdealerAccountLog.setDisburse(false);
			withholdingdealerAccountLog.setResourceId(customOrder.getId());
			withholdingdealerAccountLog.setCompanyId(WebUtils.getCurrCompanyId());
			this.dealerAccountLogService.add(withholdingdealerAccountLog);
			logger.info(dateFormat.format(DateUtil.getSystemDate()) + loginName + "审核经销商"+companyById.getName()+"订单" + customOrder.getNo() + "," + "此订单为货款代扣订单，货款由代扣经销商" + dealerName + "自由资金账户扣除，代扣金额为：" + needSubtract + ",之前账户余额为：" + freeDealerAccount.getBalance() + ",剩余金额为：" + freeDealerAccount.getBalance().subtract((needSubtract)));
		}
	   //经销商添加积分
	   CompanyDto companyDto=this.companyService.findCompanyById(customOrder.getCompanyId());
	   MapContext companyMap=MapContext.newOne();
	   companyMap.put("id",customOrder.getCompanyId());
	   companyMap.put("integral",companyDto.getIntegral().add(needSubtract));
	   this.companyService.updateByMapContext(companyMap);

		//如果原支付记录为非挂账状态，则需要生成生产单 //修改订单状态
		if(byPaymentId.getStatus()!=PaymentStatus.ON_CREDIT.getValue()){
			//生成生产单
			List<OrderProductDto> orderProductDtos = this.orderProductService.findListByOrderId(customOrderId);
			MapContext orderMapContext = new MapContext();
			//根据产品创建生产单
			CustomOrder customOrder1 = this.customOrderService.findByOrderId(customOrderId);
			if (orderProductDtos.size() > 0) {
				for (OrderProductDto productDto : orderProductDtos) {
					String orderProductId = productDto.getId();
					//修改订单产品的财务审核时间以及订单计划交付时间
					if (customOrder1 != null) {
						MapContext map1 = MapContext.newOne();
						map1.put("payTime", com.lwxf.mybatis.utils.DateUtil.now());
						map1.put("planOrderFinishTime", customOrder1.getEstimatedDeliveryDate());
						if (customOrder1.getOrderProductType() == 4 || customOrder1.getOrderProductType() == 5) {
							map1.put("status", OrderProductStatus.PRODUCING.getValue());
						} else {
							map1.put("status", OrderProductStatus.TO_PRODUCE.getValue());
						}
						map1.put("id", orderProductId);
						this.orderProductService.updateByMapContext(map1);
					}
					//查询产品部件，生成生产单
					List<ProductPartsDto> productParts = this.productPartsService.findByOrderProductId(orderProductId);
					if (productParts != null && productParts.size() > 0) {
						for (ProductPartsDto parts : productParts) {
							ProduceOrder produceOrder = new ProduceOrder();
							produceOrder.setCustomOrderId(byPaymentId.getCustomOrderId());
							produceOrder.setOrderProductId(orderProductId);
							produceOrder.setCustomOrderNo(customOrder.getNo());
							produceOrder.setCreated(DateUtil.getSystemDate());
							produceOrder.setCreator(WebUtils.getCurrUserId());
							produceOrder.setBranchId(WebUtils.getCurrBranchId());
							produceOrder.setResourceType(byPaymentId.getResourceType());
							produceOrder.setFlag(0);
							produceOrder.setDeliverStatus(0);
							produceOrder.setHasDeliverCount(0);
							String productNo = productDto.getNo();
							List<Basecode> produceOrderType = this.basecodeService.findByTypeAndDelFlag("produceOrderType", 0);
							Basecode orderProductType = this.basecodeService.findByTypeAndCode("orderProductType", productDto.getType().toString());
							for(Basecode basecode:produceOrderType) {
								String remark=basecode.getRemark();
								productNo = productNo.replace(remark, "");
								if(remark.equals(orderProductType.getRemark())){
									productNo = orderProductType.getRemark() + productNo;
								}
							}
							if (parts.getOrderPartsIdentify() != null) {
								produceOrder.setNo(productNo + parts.getOrderPartsIdentify());
							} else {
								produceOrder.setNo(productNo);
							}
							if (parts.getProduceType() != ProduceOrderWay.COORDINATION.getValue()) {
								produceOrder.setPay(ProduceOrderPay.PAY.getValue());
							} else {
								produceOrder.setPay(ProduceOrderPay.NOT_PAY.getValue());
								produceOrder.setAmount(new BigDecimal("0.00"));
							}
							produceOrder.setType(parts.getPartsType());
							produceOrder.setWay(parts.getProduceType());
							produceOrder.setPermit(ProduceOrderPermit.NOT_ALLOW.getValue());
							if (parts.getPartsType() == ProduceOrderType.HARDWARE.getValue()) {//如果是五金
								if (parts.getProduceType().equals(ProduceOrderWay.COORDINATION.getValue())) {//如果是外协
									produceOrder.setState(ProduceOrderState.NOT_YET_BEGUN.getValue());
									produceOrder.setPermit(ProduceOrderPermit.NOT_ALLOW.getValue());
								} else {
									produceOrder.setState(ProduceOrderState.COMPLETE.getValue());
								}
							} else if (parts.getPartsType().equals(ProduceOrderType.CABINET_BODY.getValue())) {//如果是柜体
								produceOrder.setState(ProduceOrderState.NOT_YET_BEGUN.getValue());
							} else if (parts.getPartsType().equals(ProduceOrderType.DOOR_PLANK.getValue())) {//如果是门板
								if (parts.getProduceType().equals(ProduceOrderWay.SELF_PRODUCED.getValue())) {//如果是自产
									produceOrder.setState(ProduceOrderState.NOT_YET_BEGUN.getValue());
									produceOrder.setPermit(ProduceOrderPermit.NOT_ALLOW.getValue());
									//produceOrder.setNo(productDto.getNo() + "" + b.getValue()+"Z");
								} else {//如果是外协
									produceOrder.setState(ProduceOrderState.NOT_YET_BEGUN.getValue());
								}
							} else {
								produceOrder.setState(ProduceOrderState.NOT_YET_BEGUN.getValue());
							}
							this.produceOrderService.add(produceOrder);
						}
					}
				}
			}
			if (orderMapContext.size() > 0) {
				orderMapContext.put("id", byPaymentId.getCustomOrderId());
				this.customOrderService.updateByMapContext(orderMapContext);
			}
			//修改订单状态及交货时间
			MapContext updateOrder = new MapContext();
			updateOrder.put(WebConstant.KEY_ENTITY_ID, customOrderId);
			Calendar calendar = Calendar.getInstance();
			Integer allDay = 0;
			calendar.setTime(DateUtil.getSystemDate()); //
			Basecode base=this.basecodeService.findByTypeAndCode("defaultAllDuration","0");
			if(base!=null) {
				allDay = Integer.valueOf(base.getRemark());
			}else {
				allDay=15;
			}
			calendar.add(calendar.DATE, allDay);
			updateOrder.put("estimatedDeliveryDate", calendar.getTime());
		//判断产品不包含需要生产的产品，则直接状态更新为待发货
			if (customOrder.getOrderProductType()==OrderProductType.HARDWARE.getValue()||customOrder.getOrderProductType()==OrderProductType.SAMPLE_PIECE.getValue()) {
				updateOrder.put(WebConstant.KEY_ENTITY_STATUS, OrderStatus.PRODUCTION.getValue());
			} else {
				updateOrder.put(WebConstant.KEY_ENTITY_STATUS, OrderStatus.TO_PRODUCED.getValue());
			}
			this.customOrderService.updateByMapContext(updateOrder);
			//公众号消息通知
			String companyId = customOrder.getCompanyId();
			CompanyDto companyById = companyService.findCompanyById(companyId);
			if(companyById!=null){
				String leader = companyById.getLeader();
				UserThirdInfo byUserId = AppBeanInjector.userThirdInfoService.findByUserId(leader);
				if(byUserId!=null){
					//查询负责人的公众号openId是否为空
					if(byUserId.getOfficialOpenId()!=null&&!byUserId.getOfficialOpenId().equals("")){
						MapContext msg=MapContext.newOne();
						msg.put("orderId",byPaymentId.getCustomOrderId());
						msg.put("orderStatus",2);
						msg.put("first","您好，您的订单已确认，即将为您安排生产...");
						msg.put("keyword1",customOrder.getNo());
						msg.put("keyword2",byPaymentId.getAmount());
						msg.put("keyword3",customOrder.getConsigneeName());
						msg.put("keyword4",customOrder.getConsigneeTel());
						msg.put("keyword5",customOrder.getAddress());
						msg.put("remark","感谢您的支持");
						String openId=byUserId.getOfficialOpenId();
						weiXinMsgPush.SendWxMsg(openId,msg);
					}
				}
			}
		}


		return ResultFactory.generateSuccessResult();
	}
}
