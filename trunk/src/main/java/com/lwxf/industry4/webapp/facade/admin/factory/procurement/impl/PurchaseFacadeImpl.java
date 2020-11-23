package com.lwxf.industry4.webapp.facade.admin.factory.procurement.impl;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.bizservice.company.CompanyEmployeeService;
import com.lwxf.industry4.webapp.bizservice.company.CompanyService;
import com.lwxf.industry4.webapp.bizservice.dept.DeptService;
import com.lwxf.industry4.webapp.bizservice.procurement.PurchaseAuditService;
import com.lwxf.industry4.webapp.bizservice.procurement.PurchaseProductService;
import com.lwxf.industry4.webapp.bizservice.procurement.PurchaseRequestService;
import com.lwxf.industry4.webapp.bizservice.procurement.PurchaseService;
import com.lwxf.industry4.webapp.bizservice.product.ProductService;
import com.lwxf.industry4.webapp.bizservice.supplier.SupplierProductService;
import com.lwxf.industry4.webapp.bizservice.supplier.SupplierService;
import com.lwxf.industry4.webapp.bizservice.system.BasecodeService;
import com.lwxf.industry4.webapp.bizservice.user.UserService;
import com.lwxf.industry4.webapp.bizservice.warehouse.StockService;
import com.lwxf.industry4.webapp.bizservice.warehouse.StorageOutputInItemService;
import com.lwxf.industry4.webapp.bizservice.warehouse.StorageOutputInService;
import com.lwxf.industry4.webapp.bizservice.warehouse.StorageService;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.procurment.PurchaseAuditType;
import com.lwxf.industry4.webapp.common.enums.procurment.PurchaseProductStatus;
import com.lwxf.industry4.webapp.common.enums.procurment.PurchaseRquestStatus;
import com.lwxf.industry4.webapp.common.enums.storage.StorageOutputInType;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.uniquecode.UniquneCodeGenerator;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.procurement.PurchaseProductDto;
import com.lwxf.industry4.webapp.domain.dto.procurement.PurchaseRequestDto;
import com.lwxf.industry4.webapp.domain.dto.supplier.SupplierDto;
import com.lwxf.industry4.webapp.domain.entity.org.Dept;
import com.lwxf.industry4.webapp.domain.entity.procurement.PurchaseAudit;
import com.lwxf.industry4.webapp.domain.entity.procurement.PurchaseRequest;
import com.lwxf.industry4.webapp.domain.entity.supplier.SupplierProduct;
import com.lwxf.industry4.webapp.domain.entity.system.Basecode;
import com.lwxf.industry4.webapp.domain.entity.warehouse.StorageOutputIn;
import com.lwxf.industry4.webapp.domain.entity.warehouse.StorageOutputInItem;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.procurement.PurchaseFacade;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/17/017 11:16
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("purchaseFacade")
public class PurchaseFacadeImpl extends BaseFacadeImpl implements PurchaseFacade {
	@Resource(name = "purchaseRequestService")
	private PurchaseRequestService purchaseRequestService;
	@Resource(name = "storageService")
	private StorageService storageService;
	@Resource(name = "productService")
	private ProductService productService;
	@Resource(name = "purchaseService")
	private PurchaseService purchaseService;
	@Resource(name = "userService")
	private UserService userService;
	@Resource(name = "companyService")
	private CompanyService companyService;
	@Resource(name = "purchaseProductService")
	private PurchaseProductService purchaseProductService;
	@Resource(name = "supplierProductService")
	private SupplierProductService supplierProductService;
	@Resource(name = "stockService")
	private StockService stockService;
	@Resource(name = "storageOutputInService")
	private StorageOutputInService storageOutputInService;
	@Resource(name = "storageOutputInItemService")
	private StorageOutputInItemService storageOutputInItemService;
	@Resource(name = "deptService")
	private DeptService deptService;
	@Resource(name = "purchaseAuditService")
	private PurchaseAuditService purchaseAuditService;
	@Resource(name = "supplierService")
	private SupplierService supplierService;
	@Resource(name = "basecodeService")
	private BasecodeService basecodeService;
	@Resource(name = "companyEmployeeService")
	private CompanyEmployeeService companyEmployeeService;

	@Override
	public RequestResult findRequestList(MapContext mapContext, Integer pageNum, Integer pageSize) {
		String userId=WebUtils.getCurrUserId();
		if(userId == null){
			 userId=WebUtils.getUId();
		}
		if(userId==null){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED,AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
		}
		//查询登录人角色
		String currCompanyId = WebUtils.getCurrCompanyId();
		if(currCompanyId==null){
			currCompanyId = WebUtils.getCid();
		}
		//CompanyEmployee companyEmployee=this.companyEmployeeService.findOneByCompanyIdAndUserId(currCompanyId,userId);
		//是否为财务人员
		Integer finance=null;
		//查询登录人所在部门
		List<Dept> listByUserId = this.deptService.findListByUserId(userId);
		List<String> deptIds=new ArrayList<>();
		for (Dept dept:listByUserId){//财务部可以看到所有待付款的采购申请单
			String deptId = dept.getId();
			deptIds.add(deptId);
		}
//		Basecode personnelParameters = this.basecodeService.findByTypeAndCode("personnelParameters", "3");//成品物流部id
//		Basecode parameters = this.basecodeService.findByTypeAndCode("personnelParameters", "4");//财务部id
		if(deptIds.contains("4v0wqqtlwlxc")){//成品物流部人员能看到所有状态为待入库的申请单
			mapContext.put("statusParam", PurchaseRquestStatus.TO_BE_FINISHED.getValue());
		}else {
			if (deptIds.contains("4v1nda7v0ah0")) {
				finance = 0;
			} else {
				mapContext.put("userId", userId);//其他部门只能看到和自己有关的采购单
				finance = 1;
			}
		}
		//企业id
		String branchId=WebUtils.getCurrBranchId();
		if(branchId == null){
			branchId = WebUtils.getBId();
		}
		mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID,branchId);
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		paginatedFilter.setFilters(mapContext);
		Pagination pagination = new Pagination();
		pagination.setPageSize(pageSize);
		pagination.setPageNum(pageNum);
		paginatedFilter.setPagination(pagination);
		Map<String,String> created = new HashMap<String, String>();
		created.put(WebConstant.KEY_ENTITY_CREATED,"desc");
		List sort = new ArrayList();
		sort.add(created);
		paginatedFilter.setSorts(sort);
		PaginatedList<PurchaseRequestDto> requestDtoPaginatedList = this.purchaseRequestService.selectRequestListByFilter(paginatedFilter);
		if(requestDtoPaginatedList.getRows().size()>0) {
			for (PurchaseRequestDto purchaseRequestDto : requestDtoPaginatedList.getRows()) {
				List<String> auditName = new ArrayList<>();
				String auditOpinion = null;
				List<String> copyPerson = new ArrayList<>();
				List<String> auditIds=new ArrayList<>();
				List<String> copyPerpsonIds=new ArrayList<>();
				//查询申请单下的产品
				List<PurchaseProductDto> productDtos = this.purchaseProductService.selectProductsByRequestId(purchaseRequestDto.getId());
				//查询申请单的审核人以及抄送人
				List<PurchaseAudit> purchaseAudits = this.purchaseAuditService.selectAuditByRequestId(purchaseRequestDto.getId());
				for (PurchaseAudit purchaseAudit : purchaseAudits) {
					String usId = purchaseAudit.getUserId();
					String name = AppBeanInjector.userService.findById(usId).getName();
					if (purchaseAudit.getType() == PurchaseAuditType.AUDIT.getValue()) {
						auditName.add(name);
						auditIds.add(purchaseAudit.getUserId());
					} else if(purchaseAudit.getType() == PurchaseAuditType.COPY.getValue()){
						copyPerson.add(name);
						copyPerpsonIds.add(purchaseAudit.getUserId());
					}
					auditOpinion = auditOpinion + purchaseAudit.getAuditOpinion();
				}
				//查询登录人在申请单中的角色
				List<Integer> types=this.purchaseAuditService.findTypeByRequestIdAndUid(purchaseRequestDto.getId(),userId);
				purchaseRequestDto.setLoginType(types);
				//审核人的审核状态
				PurchaseAudit purchaseAudit =this.purchaseAuditService.findByRequestIdAndLoginUserId(purchaseRequestDto.getId(),userId,PurchaseAuditType.AUDIT.getValue());
				if(purchaseAudit!=null) {
					purchaseRequestDto.setAuditStatus(purchaseAudit.getStatus());
				}else {
					purchaseRequestDto.setAuditStatus(1);
				}
				purchaseRequestDto.setAuditIds(auditIds);
				purchaseRequestDto.setCopyPersonIds(copyPerpsonIds);
				purchaseRequestDto.setFinance(finance);
				purchaseRequestDto.setAuditName(auditName);
				purchaseRequestDto.setAuditOpinion(auditOpinion);
				purchaseRequestDto.setCopyPerson(copyPerson);
				String statusName = PurchaseRquestStatus.getByValue(purchaseRequestDto.getStatus()).getName();
				purchaseRequestDto.setStatusName(statusName);
				purchaseRequestDto.setPurchaseProductDtoList(productDtos);
			}
		}
		return ResultFactory.generateRequestResult(requestDtoPaginatedList);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addPurchaseRequest(PurchaseRequestDto purchaseRequestDto) {
		String userId=WebUtils.getCurrUserId();
		if(userId==null){
			userId = WebUtils.getUId();
		}
		String currBranchId = WebUtils.getCurrBranchId();
		if(currBranchId==null){
			currBranchId = WebUtils.getBId();
		}
		if(userId==null){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED,AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
		}
		//添加申请单
		purchaseRequestDto.setBranchId(currBranchId);
		purchaseRequestDto.setCreator(userId);
		purchaseRequestDto.setCreated(DateUtil.getSystemDate());
		purchaseRequestDto.setNotes(purchaseRequestDto.getNotes());
		purchaseRequestDto.setCategoryId(purchaseRequestDto.getCategoryId());
		purchaseRequestDto.setSupplierId(purchaseRequestDto.getSupplierId());
		if(purchaseRequestDto.getStatus()==null||purchaseRequestDto.getStatus().equals("")){
			purchaseRequestDto.setStatus(PurchaseRquestStatus.STAY.getValue());
		}
		if(purchaseRequestDto.getApplyTime()==null||purchaseRequestDto.getApplyTime().equals("")){
			purchaseRequestDto.setApplyTime(DateUtil.getSystemDate());
		}
		if(purchaseRequestDto.getAmount()==null||purchaseRequestDto.getAmount().equals("")){
			purchaseRequestDto.setAmount(new BigDecimal("0"));
		}
		if(purchaseRequestDto.getQuantity()==null||purchaseRequestDto.getQuantity().equals("")){
			purchaseRequestDto.setQuantity(0);
		}
		if(purchaseRequestDto.getProposer()==null||purchaseRequestDto.getProposer().equals("")){
			purchaseRequestDto.setProposer(userId);
		}
		purchaseRequestDto.setNo(AppBeanInjector.uniquneCodeGenerator.getNextNo(UniquneCodeGenerator.UniqueResource.PURCHASE_REQUEST));
		this.purchaseRequestService.add(purchaseRequestDto);
		//添加审核/抄送人信息
		List<String> auditIds=purchaseRequestDto.getAuditIds();
		PurchaseAudit purchaseAudit = new PurchaseAudit();
		purchaseAudit.setStatus(0);
		purchaseAudit.setCreator(userId);
		purchaseAudit.setCreated(DateUtil.getSystemDate());
		purchaseAudit.setPurchaseRequestId(purchaseRequestDto.getId());
		if(auditIds.size()>0) {
			for (String id : auditIds) {
				if(id!=null&&!id.equals("")) {
					PurchaseAudit audit = this.purchaseAuditService.findByRequestIdAndLoginUserId(purchaseRequestDto.getId(), id, PurchaseAuditType.AUDIT.getValue());
					if (audit == null) {
						purchaseAudit.setType(PurchaseAuditType.AUDIT.getValue());
						purchaseAudit.setStatus(0);
						purchaseAudit.setUserId(id);
						this.purchaseAuditService.add(purchaseAudit);
					}
				}
			}
		}
		List<String> copyPerpsonIds=purchaseRequestDto.getCopyPersonIds();
		if(copyPerpsonIds.size()>0){
			for (String id : copyPerpsonIds) {
				if(id!=null&&!id.equals("")) {
					PurchaseAudit audit = this.purchaseAuditService.findByRequestIdAndLoginUserId(purchaseRequestDto.getId(), id, PurchaseAuditType.COPY.getValue());
					if (audit == null) {
						purchaseAudit.setType(PurchaseAuditType.COPY.getValue());
						purchaseAudit.setUserId(id);
						this.purchaseAuditService.add(purchaseAudit);
					}
				}
			}
		}
		//添加申请人到人员表
		String proposer=purchaseRequestDto.getProposer();
		if(proposer==null||proposer.equals("")){
			proposer=userId;
			purchaseRequestDto.setProposer(proposer);
		}
		purchaseAudit.setType(PurchaseAuditType.PROPOSER.getValue());
		purchaseAudit.setUserId(proposer);
		this.purchaseAuditService.add(purchaseAudit);
		//添加采购产品
		String requestId=purchaseRequestDto.getId();
		List<PurchaseProductDto> purchaseProductDtoList = purchaseRequestDto.getPurchaseProductDtoList();
		for(PurchaseProductDto purchaseProductDto:purchaseProductDtoList){
			purchaseProductDto.setPurchaseRequestId(requestId);
			purchaseProductDto.setStatus(PurchaseProductStatus.PENDING_QUALITY_INSPECTION.getValue());
			this.purchaseProductService.add(purchaseProductDto);
		}
		return ResultFactory.generateRequestResult(purchaseRequestDto);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updatePurchaseRequest(String purchaseRequestId, PurchaseRequestDto requestDto) {
		String userId=WebUtils.getCurrUserId();
		if(userId==null){
			userId = WebUtils.getUId();
		}
		String currBranchId = WebUtils.getCurrBranchId();
		if(currBranchId==null){
			currBranchId = WebUtils.getBId();
		}
		if(userId==null){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED,AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
		}
		PurchaseRequest byId = this.purchaseRequestService.findById(purchaseRequestId);
		if(byId==null){
			return ResultFactory.generateResNotFoundResult();
		}
		//申请单提交后，不允许再修改
		Integer status = byId.getStatus();
		if(status!=PurchaseRquestStatus.STAY.getValue()){
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_NOT_ALLOW_OPERATION_10020,AppBeanInjector.i18nUtil.getMessage("BIZ_NOT_ALLOW_OPERATION_10020"));
		}
		MapContext mapContext=MapContext.newOne();
		if(requestDto.getName()!=null||!requestDto.getName().equals("")){
			mapContext.put("name",requestDto.getName());
		}
		if(requestDto.getStatus()!=null||!requestDto.getStatus().equals("")){
			mapContext.put("status",requestDto.getStatus());
		}
		if(requestDto.getCategoryId()!=null||!requestDto.getCategoryId().equals("")){
			mapContext.put("categoryId",requestDto.getCategoryId());
		}
		if(requestDto.getSupplierId()!=null||!requestDto.getSupplierId().equals("")){
			mapContext.put("supplierId",requestDto.getSupplierId());
		}
		if(requestDto.getQuantity()!=null||!requestDto.getQuantity().equals("")){
			mapContext.put("quantity",requestDto.getQuantity());
		}
		if(requestDto.getNotes()!=null||!requestDto.getNotes().equals("")){
			mapContext.put("notes",requestDto.getNotes());
		}
		if(requestDto.getAmount()!=null||!requestDto.getAmount().equals("")){
			mapContext.put("amount",requestDto.getAmount());
		}
		if(mapContext.size()>0){
			mapContext.put("id",purchaseRequestId);
			this.purchaseRequestService.updateByMapContext(mapContext);
		}
		//
		//删除原有的采购申请单产品
		List<PurchaseProductDto> productDtos = this.purchaseProductService.selectProductsByRequestId(purchaseRequestId);
		for(PurchaseProductDto productDto:productDtos){
			String productDtoId = productDto.getId();
			this.purchaseProductService.deleteById(productDtoId);
		}
		//添加新的产品
		List<PurchaseProductDto> purchaseProductDtoList = requestDto.getPurchaseProductDtoList();
		for(PurchaseProductDto purchaseProductDto:purchaseProductDtoList){
			purchaseProductDto.setPurchaseRequestId(purchaseRequestId);
			purchaseProductDto.setStatus(PurchaseProductStatus.PENDING_QUALITY_INSPECTION.getValue());
			this.purchaseProductService.add(purchaseProductDto);
		}
		//删除原有的审核/抄送人等相关人员
		List<PurchaseAudit> purchaseAudits = this.purchaseAuditService.selectAuditByRequestId(purchaseRequestId);
		for (PurchaseAudit purchaseAudit:purchaseAudits){
			String auditId=purchaseAudit.getId();
			this.purchaseAuditService.deleteById(auditId);
		}
		//重新添加人员
		//添加审核/抄送人信息
		List<String> auditIds=requestDto.getAuditIds();
		PurchaseAudit purchaseAudit = new PurchaseAudit();
		purchaseAudit.setStatus(0);
		purchaseAudit.setCreator(userId);
		purchaseAudit.setCreated(DateUtil.getSystemDate());
		purchaseAudit.setPurchaseRequestId(purchaseRequestId);
		if(auditIds.size()>0) {
			for (String id : auditIds) {
				if(id!=null&&!id.equals("")) {
					purchaseAudit.setType(PurchaseAuditType.AUDIT.getValue());
					purchaseAudit.setUserId(id);
					this.purchaseAuditService.add(purchaseAudit);
				}
			}
		}
		List<String> copyPerpsonIds=requestDto.getCopyPersonIds();
		if(copyPerpsonIds.size()>0){
			for (String id : copyPerpsonIds) {
				if(id!=null&&!id.equals("")) {
					purchaseAudit.setType(PurchaseAuditType.COPY.getValue());
					purchaseAudit.setUserId(id);
					this.purchaseAuditService.add(purchaseAudit);
				}
			}
		}
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updatePurchaseAuditStatus(String purchaseRequestId,MapContext mapContext) {
		String userId=WebUtils.getCurrUserId();
		if(userId==null){
			userId = WebUtils.getUId();
		}
		String currBranchId = WebUtils.getCurrBranchId();
		if(currBranchId==null){
			currBranchId = WebUtils.getBId();
		}
		if(userId==null){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED,AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
		}
		PurchaseAudit purchaseAudit = this.purchaseAuditService.findByRequestIdAndLoginUserId(purchaseRequestId, userId,PurchaseAuditType.AUDIT.getValue());
		if(purchaseAudit==null){
			return ResultFactory.generateResNotFoundResult();
		}
		Integer result=mapContext.getTypedValue("result",Integer.class);
		String id=purchaseAudit.getId();
		MapContext map=MapContext.newOne();
		map.put("id",id);
		map.put("result",result);
		map.put("status",1);
		map.put("notes",mapContext.getTypedValue("notes",String.class));
		this.purchaseAuditService.updateByMapContext(map);
		if(result==0) {
			//根据是否添加审核人，来判断是否修改申请单状态
			List<String> auditIds = mapContext.getTypedValue("auditIds", List.class);
			if (auditIds == null || auditIds.size() == 0) {
				MapContext mapContext1 = MapContext.newOne();
				mapContext1.put("id", purchaseRequestId);
				mapContext1.put("status", PurchaseRquestStatus.TO_BE_PAY.getValue());
				this.purchaseRequestService.updateByMapContext(mapContext1);
			} else {
				if (auditIds.size() > 0) {
					PurchaseAudit audit = new PurchaseAudit();
					audit.setStatus(0);
					audit.setCreator(userId);
					audit.setCreated(DateUtil.getSystemDate());
					audit.setPurchaseRequestId(purchaseRequestId);
					for (String aid : auditIds) {
						if(aid!=null&&!aid.equals("")) {
							PurchaseAudit audit0 = this.purchaseAuditService.findByRequestIdAndLoginUserId(purchaseRequestId, aid, PurchaseAuditType.AUDIT.getValue());
							if (audit0 == null) {//如果不存在，则新增
								audit.setType(PurchaseAuditType.AUDIT.getValue());
								audit.setUserId(aid);
								this.purchaseAuditService.add(audit);
							}else {//如果存在，则人物修改为未审核状态
								MapContext mapContext1=MapContext.newOne();
								mapContext1.put("id",audit0.getId());
								mapContext1.put("status",0);
								this.purchaseAuditService.updateByMapContext(mapContext1);
							}
						}
					}
				}
			}
			List<String> copyPerpsonIds = mapContext.getTypedValue("copyPersonIds", List.class);
			if (copyPerpsonIds != null && !copyPerpsonIds.equals("")) {
				if (copyPerpsonIds.size() > 0) {
					PurchaseAudit audit = new PurchaseAudit();
					audit.setStatus(0);
					audit.setCreator(userId);
					audit.setCreated(DateUtil.getSystemDate());
					audit.setPurchaseRequestId(purchaseRequestId);
					for (String aid : copyPerpsonIds) {
						if(aid!=null&&!aid.equals("")) {
							PurchaseAudit audit0 = this.purchaseAuditService.findByRequestIdAndLoginUserId(purchaseRequestId, aid, PurchaseAuditType.COPY.getValue());
							if(audit0==null) {
								audit.setType(PurchaseAuditType.COPY.getValue());
								audit.setUserId(aid);
								this.purchaseAuditService.add(audit);
							}
						}
					}
				}
			}
		}else if(result==1){
			MapContext mapContext1 = MapContext.newOne();
			mapContext1.put("id", purchaseRequestId);
			mapContext1.put("status", PurchaseRquestStatus.STAY.getValue());
			this.purchaseRequestService.updateByMapContext(mapContext1);
			//删除原有的审核/抄送人等相关人员
			List<PurchaseAudit> purchaseAudits = this.purchaseAuditService.selectAuditByRequestId(purchaseRequestId);
			for (PurchaseAudit audiu:purchaseAudits){
				String auditId=audiu.getId();
				this.purchaseAuditService.deleteById(auditId);
			}
		}
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updatePurchaseRequestStatus(String purchaseRequestId) {
		String userId=WebUtils.getCurrUserId();
		if(userId==null){
			userId = WebUtils.getUId();
		}
		String currBranchId = WebUtils.getCurrBranchId();
		if(currBranchId==null){
			currBranchId = WebUtils.getBId();
		}
		if(userId==null){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED,AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
		}
		MapContext mapContext=MapContext.newOne();
		mapContext.put("id",purchaseRequestId);
		mapContext.put("status",PurchaseRquestStatus.PURCHASEING.getValue());
		mapContext.put("financialAudit",userId);
		mapContext.put("financialTime",DateUtil.getSystemDate());
		this.purchaseRequestService.updateByMapContext(mapContext);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updatePurchaseProduct(String purchaseRequestId, PurchaseRequestDto requestDto) {
		String userId=WebUtils.getCurrUserId();
		if(userId==null){
			userId = WebUtils.getUId();
		}
		PurchaseRequest byId = this.purchaseRequestService.findById(purchaseRequestId);
		if(byId==null){
			return ResultFactory.generateResNotFoundResult();
		}
		//修改采购单为待入库
		MapContext mapCon=MapContext.newOne();
		mapCon.put("id",purchaseRequestId);
		mapCon.put("status",PurchaseRquestStatus.TO_BE_FINISHED.getValue());
		mapCon.put("notes",requestDto.getNotes());
		this.purchaseRequestService.updateByMapContext(mapCon);
		//生成采购入库单
		StorageOutputIn storageOutputIn=new StorageOutputIn();
		storageOutputIn.setCreated(new Date());
		storageOutputIn.setCreator(WebUtils.getCurrUserId());
		storageOutputIn.setBranchId(WebUtils.getCurrBranchId());
		storageOutputIn.setNo(AppBeanInjector.uniquneCodeGenerator.getNextNo(UniquneCodeGenerator.UniqueResource.STORAGE_OUTPUTIN_NO));
		storageOutputIn.setType(StorageOutputInType.PURCHASE_WAREHOUSING.getValue());
		storageOutputIn.setFlow(0);
		storageOutputIn.setStatus(0);
		storageOutputIn.setStorageId(requestDto.getStorageId());
        storageOutputIn.setResId(purchaseRequestId);
		storageOutputIn.setNotes(requestDto.getNotes());
        this.storageOutputInService.add(storageOutputIn);
		List<PurchaseProductDto> productDtos = requestDto.getPurchaseProductDtoList();
		for(PurchaseProductDto productDto:productDtos){
			MapContext mapContext=MapContext.newOne();
			mapContext.put("id",productDto.getId());
			mapContext.put("intoStorage",productDto.getIntoStorage());
			mapContext.put("notes",productDto.getNotes());
			mapContext.put("operator",userId);
			mapContext.put("operated",DateUtil.getSystemDate());
			this.purchaseProductService.updateByMapContext(mapContext);
			String supplierProductId = productDto.getSupplierProductId();
			SupplierProduct byId1 = this.supplierProductService.findById(supplierProductId);
			String materialId=byId1.getMaterialId();
			//生成入库明细
			StorageOutputInItem storageOutputInItem=new StorageOutputInItem();
			storageOutputInItem.setMaterialId(materialId);
			storageOutputInItem.setOutputInId(storageOutputIn.getId());
			storageOutputInItem.setPrice(productDto.getPrice());
			storageOutputInItem.setQuantity(productDto.getQuantity());
			storageOutputInItem.setSupplierId(byId1.getSupplierId());
			this.storageOutputInItemService.add(storageOutputInItem);
		}

		return ResultFactory.generateSuccessResult();
	}

	@Override
	public RequestResult findAllSupplierList(MapContext mapContext) {
		String userId=WebUtils.getCurrUserId();
		if(userId==null){
			userId = WebUtils.getUId();
		}
		String currBranchId = WebUtils.getCurrBranchId();
		if(currBranchId==null){
			currBranchId = WebUtils.getBId();
		}
		if(userId==null){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED,AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
		}
		List<SupplierDto> suppliers=this.supplierService.findListByType(mapContext);
		if(suppliers==null||suppliers.size()==0){
			return ResultFactory.generateSuccessResult();
		}
		for(SupplierDto supplier:suppliers){
			String supplierId=supplier.getId();
			String categoryId = supplier.getCategoryId();
			Basecode basecode=this.basecodeService.findById(categoryId);
			String code = basecode.getCode();
			List<SupplierProduct> supplierProducts=this.supplierProductService.findListBySupplierIdAndMaterialType(supplierId,code);
			supplier.setProducts(supplierProducts);
		}
		return ResultFactory.generateRequestResult(suppliers);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult endPurchaseRequest(String purchaseRequestId, MapContext mapContext) {
		mapContext.put("id",purchaseRequestId);
		return ResultFactory.generateRequestResult(this.purchaseRequestService.updateByMapContext(mapContext));
	}


}
