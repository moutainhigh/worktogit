package com.lwxf.industry4.webapp.controller.admin.factory.warehouse;

import com.lwxf.commons.json.JsonMapper;
import com.lwxf.commons.utils.DateUtil;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.storage.FinishedStockStatus;
import com.lwxf.industry4.webapp.common.enums.storage.FinishedStockWay;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.common.utils.excel.impl.DispatchBillItemParam;
import com.lwxf.industry4.webapp.common.utils.excel.impl.DispatchBillPlanItemParam;
import com.lwxf.industry4.webapp.domain.dto.warehouse.DispatchBillPlanDto;
import com.lwxf.industry4.webapp.domain.dto.warehouse.FinishedStockDto;
import com.lwxf.industry4.webapp.domain.dto.warehouse.FinishedStockItemDto;
import com.lwxf.industry4.webapp.domain.entity.branch.Branch;
import com.lwxf.industry4.webapp.domain.entity.warehouse.FinishedStockItem;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.warehouse.FinishedStockFacade;
import com.lwxf.mybatis.utils.MapContext;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/24/024 13:41
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@RestController
@RequestMapping(value = "/api/f/storages/finisheds",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
@Api(value = "FinishedStockController",tags = "F端后台管理接口：成品管理")
public class FinishedStockController {
	@Resource(name = "finishedStockFacade")
	private FinishedStockFacade finishedStockFacade;

	/**
	 * 查询成品库包裹列表
	 * @param way
	 * @param orderNo
	 * @param status
	 * @return
	 */
	@ApiResponses({
			@ApiResponse(code = 200,message = "查询成功",response =FinishedStockItemDto.class)
	})
	@ApiOperation(value = "查询成品库包裹列表",notes = "查询成品库包裹列表")
	@ApiImplicitParams({
			@ApiImplicitParam(value = "入库方式：0 - 系统自动入库；1 - 人工手动入库",name = "way",paramType = "query",dataType = "int"),
			@ApiImplicitParam(value = "订单编号",name = "orderNo",paramType = "query",dataType = "string"),
			@ApiImplicitParam(value = "状态：0 - 未配送；1 - 部分配送；2 - 全部配送",name = "status",paramType = "query",dataType = "int"),
			@ApiImplicitParam(value = "是否已创建配送计划",name = "ship",paramType = "query",dataType = "int"),
			@ApiImplicitParam(value = "是否已入库",name = "in",paramType = "query",dataType = "int"),
			@ApiImplicitParam(value = "包装编号/条形码",name = "barcode",paramType = "query",dataType = "int"),
			@ApiImplicitParam(value = "是否发货",name = "delivery",dataType = "boolean", paramType = "query"),
			@ApiImplicitParam(value = "页数",name = "pageSize",dataType = "int", paramType = "query"),
			@ApiImplicitParam(value = "页码",name = "pageNum",dataType = "int",paramType = "query")
	})
	@GetMapping
	private String findFinishedDto(@RequestParam(required = false) Integer way,
								   @RequestParam(required = false) String orderNo,
								   @RequestParam(required = false) Integer status,
								   @RequestParam(required = false) Integer ship,
								   @RequestParam(required = false) Integer in,
								   @RequestParam(required = false) String barcode,
								   @RequestParam(required = false) List<Integer> type,
								   @RequestParam(required = false) Boolean delivery,
								   @RequestParam(required = false) Integer pageNum,
								   @RequestParam(required = false) Integer pageSize){
 		if(null == pageSize){
			pageSize = AppBeanInjector.configuration.getPageSizeLimit();
		}
		if(null == pageNum){
			pageNum = 1;
		}
		MapContext mapContext = this.createMapContent(way,orderNo,status,ship,in,barcode,type,delivery);
		JsonMapper jsonMapper = new JsonMapper();
		return jsonMapper.toJson(this.finishedStockFacade.findFinishedDto(mapContext,pageNum,pageSize));
	}

	/**
	 * 查询成品库包裹列表
	 * @return
	 */
	@ApiResponses({
			@ApiResponse(code = 200,message = "查询成功",response =FinishedStockItemDto.class)
	})
	@ApiOperation(value = "查询待入库产品列表",notes = "查询待入库产品列表")
	@ApiImplicitParams({
			@ApiImplicitParam(value = "订单编号",name = "orderNo",dataType = "string", paramType = "query"),
			@ApiImplicitParam(value = "经销商名称",name = "companyName",dataType = "string", paramType = "query"),
			@ApiImplicitParam(value = "客户地址",name = "customAddress",dataType = "string", paramType = "query"),
			@ApiImplicitParam(value = "经销商地址",name = "dealerAddress",dataType = "string", paramType = "query"),
			@ApiImplicitParam(value = "入库时间起",name = "beginStockInputDate",dataType = "string", paramType = "query"),
			@ApiImplicitParam(value = "入库时间止",name = "endStockInputDate",dataType = "string", paramType = "query"),
			@ApiImplicitParam(value = "下单时间起",name = "beginPayDate",dataType = "string", paramType = "query"),
			@ApiImplicitParam(value = "下单时间止",name = "endPayDate",dataType = "string", paramType = "query"),
			@ApiImplicitParam(value = "生产类型",name = "produceType",dataType = "string", paramType = "query"),
			@ApiImplicitParam(value = "页数",name = "pageSize",dataType = "int", paramType = "query"),
			@ApiImplicitParam(value = "页码",name = "pageNum",dataType = "int",paramType = "query")
	})
	@GetMapping("/findInputProducts")
	private String findProductDto(@RequestParam(required = false) String orderNo,
								  @RequestParam(required = false) String companyName,
								  @RequestParam(required = false) String customAddress,
								  @RequestParam(required = false) String dealerAddress,
								  @RequestParam(required = false) Date beginStockInputDate,
								  @RequestParam(required = false) Date endStockInputDate,
								  @RequestParam(required = false) Date beginPayDate,
								  @RequestParam(required = false) Date endPayDate,
								  @RequestParam(required = false) String produceType,
								  @RequestParam(required = false) Integer pageNum,
								  @RequestParam(required = false) Integer pageSize){
		if(null == pageSize){
			pageSize = AppBeanInjector.configuration.getPageSizeLimit();
		}
		if(null == pageNum){
			pageNum = 1;
		}
		MapContext mapContext = this.createMapContentDto(orderNo,companyName,customAddress,dealerAddress,beginStockInputDate,endStockInputDate,beginPayDate,endPayDate,produceType);
		//MapContext mapContext = MapContext.newOne();
		JsonMapper jsonMapper = new JsonMapper();
		return jsonMapper.toJson(this.finishedStockFacade.findProductsDto(mapContext,pageNum,pageSize));
	}

	/**
	 * 查询各种部件的待入库总数
	 * @return
	 */
	@ApiResponses({
			@ApiResponse(code = 200,message = "查询成功",response =MapContext.class)
	})
	@ApiOperation(value = "待入库部件总数",notes = "待入库部件总数")
	@GetMapping("/countInputPart")
	private String queryPartCount(){
		JsonMapper jsonMapper = new JsonMapper();
		return jsonMapper.toJson(this.finishedStockFacade.countInputPart(WebUtils.getCurrBranchId()));
	}



	/**
	 * 创建发货时，条件搜索成品库包裹
	 */
	@ApiOperation(value = "待审核产品总数",notes = "待审核产品总数")
	@GetMapping("/countVerifyProducts")
	private String countVerifyProducts(){
		JsonMapper jsonMapper = new JsonMapper();
		return jsonMapper.toJson(this.finishedStockFacade.countVerifyProducts());
	}


	/**
	 * 创建发货时，条件搜索成品库包裹
	 */
	@ApiOperation(value = "创建发货时，条件搜索成品库包裹",notes = "创建发货时，条件搜索成品库包裹")
	@ApiImplicitParams({
			@ApiImplicitParam(value = "收货人排序，值是（consigneeName），订单创建时间排序（orderCreated）",name = "order",paramType = "query",dataType = "string")
	})
	@GetMapping("/finishedStockNos")
	private String findFinishedStockNos(
								   @RequestParam(required = false) String order
								   ){

		JsonMapper jsonMapper = new JsonMapper();
		MapContext mapContext=MapContext.newOne();
//		mapContext.put("in",1);//已入库
//		mapContext.put("status",0);//未发货consigneeName
	//	mapContext.put("storageId",WebUtils.getFinishedStorageId());
		mapContext.put("branchId",WebUtils.getCurrBranchId());
		if(LwxfStringUtils.isNotBlank(order)){
			mapContext.put("order",order);
		}
		return jsonMapper.toJson(this.finishedStockFacade.findFinishedStockNos(order,mapContext));
	}

	/**
	 * 新增成品库单
	 * @param finishedStockDto
	 * @return
	 */
	@PostMapping
	private RequestResult addFinishedStock(@RequestBody FinishedStockDto finishedStockDto){
	//	finishedStockDto.setStorageId(WebUtils.getFinishedStorageId());
		finishedStockDto.setCreator(WebUtils.getCurrUserId());
		finishedStockDto.setCreated(DateUtil.getSystemDate());
		finishedStockDto.setStatus(FinishedStockStatus.UNSHIPPED.getValue());
		finishedStockDto.setWay(FinishedStockWay.MANUAL.getValue());
		RequestResult result = finishedStockDto.validateFields();
		if(result!=null){
			return result;
		}
		//判断条目数是否大于包数
		if(finishedStockDto.getPackages()<finishedStockDto.getFinishedStockItemDtos().size()){
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_RESOURCES_LIMIT_10076,AppBeanInjector.i18nUtil.getMessage("BIZ_RESOURCES_LIMIT_10076"));
		}
		for(FinishedStockItemDto finishedStockItemDto:finishedStockDto.getFinishedStockItemDtos()){
			finishedStockItemDto.setCreated(DateUtil.getSystemDate());
			finishedStockItemDto.setCreator(WebUtils.getCurrUserId());
			finishedStockItemDto.setShipped(false);
			finishedStockItemDto.setIn(false);
			RequestResult requestResult = finishedStockItemDto.validateFields();
			if(requestResult!=null){
				return requestResult;
			}
		}
		return this.finishedStockFacade.addFinishedStock(finishedStockDto);
	}

	/**
	 * 成品库单下新增条目(废弃)
	 * @param finishedStockItem
	 * @param id
	 * @return
	 */
	@PostMapping("/{id}")
	private RequestResult addFinishedItem(@PathVariable String storageId ,@RequestBody FinishedStockItem finishedStockItem,@PathVariable String id){
		finishedStockItem.setCreated(DateUtil.getSystemDate());
		finishedStockItem.setCreator(WebUtils.getCurrUserId());
		finishedStockItem.setShipped(false);
		finishedStockItem.setIn(false);
		finishedStockItem.setFinishedStockId(id);
		RequestResult result = finishedStockItem.validateFields();
		if(result!=null){
			return result;
		}
		return this.finishedStockFacade.addFinishedItem(finishedStockItem,id,storageId);
	}

	/**
	 * 更新打包数量
	 * @return
	 */
	@PutMapping("/{product_id}")
	@ApiOperation(value = "成品库入库",notes = "成品库入库")
	private RequestResult updateFinishedStock(@PathVariable String product_id,
											  @RequestBody List<MapContext> mapContexts,
											  @RequestParam(required = false)String status,
											  @RequestParam(required = false)String notes,
											  @RequestParam(required = false)String queryInstock){
		return this.finishedStockFacade.updateFinishedStock(product_id,mapContexts,status,notes,queryInstock);
	}

	/**
	 * 发货审核
	 * @return
	 */
	@PutMapping("/verifyDelivery/{productsIds}")
	@ApiOperation(value = "发货审核",notes = "发货审核")
	private RequestResult verifyDelivery(@PathVariable String productsIds){
		return this.finishedStockFacade.verifyDelivery(productsIds);
	}





	@ApiOperation(value = "配送计划列表")
	@GetMapping("/dispatchplans")
	private String finddispatchPlans(@RequestParam(required = false)@ApiParam(value = "日期")String planTime,
									 @RequestParam(required = false)@ApiParam(value = "发货计划名称")String name,
											@RequestParam(required = false)@ApiParam(value = "状态")String status,
									 @RequestParam(required = false)@ApiParam(value = "页码")Integer pageSize,
									 @RequestParam(required = false)@ApiParam(value = "页条数")Integer pageNum
									 ){
		if (null == pageSize) {
			pageSize = AppBeanInjector.configuration.getPageSizeLimit();
		}
		if (null == pageNum) {
			pageNum = 1;
		}
		String branchId=WebUtils.getCurrBranchId();
		MapContext mapContext=MapContext.newOne();
		mapContext.put("branchId",branchId);
		if(LwxfStringUtils.isNotBlank(planTime)){
			mapContext.put("planTime",planTime);
		}
		if(LwxfStringUtils.isNotBlank(name)){
			mapContext.put("name",name);
		}
		if(LwxfStringUtils.isNotBlank(status)){
			mapContext.put("status",status);
		}
		JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
		return jsonMapper.toJson(this.finishedStockFacade.finddispatchPlans(mapContext,pageSize,pageNum));
	}

	@ApiOperation(value = "配送计划单详情")
	@GetMapping("/dispatchplans/info")
	private String findDispatchPlanInfo(@RequestParam String planId,
											   @RequestParam(required = false)String productNo,
											   @RequestParam(required = false)String dealerName,
											   @RequestParam(required = false)String customerName){
		JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
		MapContext mapContext=MapContext.newOne();
		if(LwxfStringUtils.isNotBlank(productNo)) {
			mapContext.put("productNo",productNo);
		}
		if(LwxfStringUtils.isNotBlank(dealerName)) {
			mapContext.put("dealerName",dealerName);
		}
		if(LwxfStringUtils.isNotBlank(customerName)) {
			mapContext.put("customerName",customerName);
		}
		RequestResult dispatchPlanInfo = this.finishedStockFacade.findDispatchPlanInfo(planId, mapContext);
		return jsonMapper.toJson(dispatchPlanInfo);
	}

	/**
	 * 创建配送计划
	 * @param dispatchBillPlanDto
	 * @return
	 */
	@ApiOperation(value = "创建配送计划",notes = "创建配送计划",response = DispatchBillPlanDto.class)
	@ApiImplicitParams({
			@ApiImplicitParam(value = "配送计划",name = "dispatchBillPlanDto",dataTypeClass = DispatchBillPlanDto.class,paramType = "body")
	})
	@PostMapping("/dispatchplan")
	private RequestResult addDispatchPlan(@RequestBody DispatchBillPlanDto dispatchBillPlanDto){
		if(dispatchBillPlanDto.getDispatchBillPlanItems().size()==0){
			return ResultFactory.generateResNotFoundResult();
		}
		return this.finishedStockFacade.addDispatchPlan(dispatchBillPlanDto);
	}

	@ApiOperation(value = "添加发货计划单的产品")
	@PutMapping("/dispatchplan/add")
	private RequestResult addDispatchPlanProduct(@RequestBody DispatchBillPlanDto dispatchBillPlanDto){
		return this.finishedStockFacade.addDispatchPlanProduct(dispatchBillPlanDto);
	}

	@ApiOperation(value = "修改发货计划单")
	@PutMapping("/dispatchplan/edit")
	private RequestResult editDispatchPlan(@RequestBody DispatchBillPlanDto dispatchBillPlanDto){
		return this.finishedStockFacade.editDispatchPlan(dispatchBillPlanDto);
	}

	@ApiOperation(value = "修改发货计划单明细的生产单物流信息")
	@PutMapping("/dispatchplan/edit/logistics")
	private RequestResult editDispatchPlanlogistics(@RequestBody DispatchBillPlanDto dispatchBillPlanDto){
		return this.finishedStockFacade.editDispatchPlanlogistics(dispatchBillPlanDto);
	}

	@ApiOperation(value = "查询是否有未完成的发货单")
	@GetMapping("/dispatchplans/nodone")
	private RequestResult nodoneDispatchPlan(){
		return this.finishedStockFacade.nodoneDispatchPlan();
	}

	@ApiOperation(value = "查询当前未完成的发货单详情")
	@GetMapping("/dispatchplans/nowInfo")
	private RequestResult nodoneDispatchPlanNowinfo(){
		return this.finishedStockFacade.nodoneDispatchPlanNowinfo();
	}


	@ApiOperation(value = "创建配送计划通过订单")
	@PostMapping("/dispatchplan/orders")
	private RequestResult addDispatchPlanByOrder(@RequestBody DispatchBillPlanDto dispatchBillPlanDto){
		if(dispatchBillPlanDto.getOrderIds().size()==0){
			return ResultFactory.generateResNotFoundResult();
		}
		return this.finishedStockFacade.addDispatchPlanByOrder(dispatchBillPlanDto);
	}

	/**
	 * 财务审核用的配送计划列表
	 * @param planTime
	 * @param name
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */

	@ApiOperation(value = "财务审核用的配送计划列表")
	@GetMapping("/dispatchplans/audit")
	private String finddispatchPlansForAudit(@RequestParam(required = false)@ApiParam(value = "日期")String planTime,
									 @RequestParam(required = false)@ApiParam(value = "发货计划名称")String name,
									 @RequestParam(required = false)@ApiParam(value = "状态")String auditStatus,
									 @RequestParam(required = false)@ApiParam(value = "页码")Integer pageSize,
									 @RequestParam(required = false)@ApiParam(value = "页条数")Integer pageNum
	){
		if (null == pageSize) {
			pageSize = AppBeanInjector.configuration.getPageSizeLimit();
		}
		if (null == pageNum) {
			pageNum = 1;
		}
		String branchId=WebUtils.getCurrBranchId();
		MapContext mapContext=MapContext.newOne();
		mapContext.put("branchId",branchId);
		if(LwxfStringUtils.isNotBlank(planTime)){
			mapContext.put("planTime",planTime);
		}
		if(LwxfStringUtils.isNotBlank(name)){
			mapContext.put("name",name);
		}
		if(LwxfStringUtils.isNotBlank(auditStatus)){
			mapContext.put("auditStatus",auditStatus);
		}
		//查询企业审核发货计划的模式
		Branch byId = AppBeanInjector.branchService.findById(branchId);
		if(byId.getToExaminePlan()==1){
			if(byId.getExamineType()==1){//只查询有欠账的发货计划单
              mapContext.put("auditType",1);
			}
		}
		JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
		return jsonMapper.toJson(this.finishedStockFacade.finddispatchPlans(mapContext,pageSize,pageNum));
	}

	/**
	 * 发货计划审核
	 * @return
	 */
	@PutMapping("/auditplan")
	@ApiOperation(value = "发货计划审核",notes = "发货计划审核")
	private RequestResult auditplan(@RequestBody DispatchBillPlanDto dispatchBillPlanDto){
		return this.finishedStockFacade.auditplan(dispatchBillPlanDto);
	}

	/**
	 * 导出配送计划（现用）
	 * @param planId
	 * @return
	 */
	@ApiOperation(value = "导出配送计划单详情")
	@GetMapping("/dispatchplans/excel")
	private RequestResult execlDispatchPlanInfo(@RequestParam String planId){
		return this.finishedStockFacade.execlDispatchPlanInfo(planId,new DispatchBillPlanItemParam());
	}


	/**
	 * 导出配送计划（弃用）
	 * @param ids
	 * @return
	 */
	@GetMapping("/dispatchplan")
	@ApiOperation(value = "导出配送计划",notes = "导出配送计划")
	@ApiImplicitParams({
			@ApiImplicitParam(value = "入库方式：0 - 系统自动入库；1 - 人工手动入库",name = "way",paramType = "query",dataType = "int"),
			@ApiImplicitParam(value = "订单编号",name = "orderNo",paramType = "query",dataType = "string"),
			@ApiImplicitParam(value = "状态：0 - 未配送；1 - 部分配送；2 - 全部配送",name = "status",paramType = "query",dataType = "int"),
			@ApiImplicitParam(value = "是否已创建配送计划",name = "ship",paramType = "query",dataType = "int"),
			@ApiImplicitParam(value = "是否已入库",name = "in",paramType = "query",dataType = "int"),
			@ApiImplicitParam(value = "包装编号/条形码",name = "barcode",paramType = "query",dataType = "int"),
			@ApiImplicitParam(value = "是否发货",name = "delivery",dataType = "boolean", paramType = "query")
	})
	private RequestResult writeExcelPlan(@RequestParam@ApiParam(value = "包裹id集合") List<String> ids,
										 @RequestParam(required = false) Integer way,
										 @RequestParam(required = false) String orderNo,
										 @RequestParam(required = false) Integer status,
										 @RequestParam(required = false) Integer ship,
										 @RequestParam(required = false) Integer in,
										 @RequestParam(required = false) String barcode,
										 @RequestParam(required = false) List<Integer> type,
										 @RequestParam(required = false) Boolean delivery){
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		Pagination pagination = new Pagination();
		pagination.setPageNum(1);
		pagination.setPageSize(1000);
		paginatedFilter.setPagination(pagination);
		MapContext mapContext = this.createMapContent(way,orderNo,status,ship,in,barcode,type,delivery);
		mapContext.put("ids",ids);
		mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID,WebUtils.getCurrBranchId());
		paginatedFilter.setFilters(mapContext);
		return this.finishedStockFacade.writeExcel(paginatedFilter,new DispatchBillPlanItemParam());
	}


	/**
	 * 导出待发货包裹
	 * @return
	 */
	@GetMapping("/dispatch")
	@ApiOperation(value = "导出待发货包裹",notes = "导出待发货包裹")
	@ApiImplicitParams({
			@ApiImplicitParam(value = "入库方式：0 - 系统自动入库；1 - 人工手动入库",name = "way",paramType = "query",dataType = "int"),
			@ApiImplicitParam(value = "订单编号",name = "orderNo",paramType = "query",dataType = "string"),
			@ApiImplicitParam(value = "状态：0 - 未配送；1 - 部分配送；2 - 全部配送",name = "status",paramType = "query",dataType = "int"),
			@ApiImplicitParam(value = "是否已创建配送计划",name = "ship",paramType = "query",dataType = "int"),
			@ApiImplicitParam(value = "是否已入库",name = "in",paramType = "query",dataType = "int"),
			@ApiImplicitParam(value = "包装编号/条形码",name = "barcode",paramType = "query",dataType = "int"),
			@ApiImplicitParam(value = "是否发货",name = "delivery",dataType = "boolean", paramType = "query")
	})
	private RequestResult writeExcelDispatch(@RequestParam(required = false)@ApiParam(value = "包裹id集合") List<String> ids,
											 @RequestParam(required = false) Integer way,
											 @RequestParam(required = false) String orderNo,
											 @RequestParam(required = false) Integer status,
											 @RequestParam(required = false) Integer ship,
											 @RequestParam(required = false) Integer in,
											 @RequestParam(required = false) String barcode,
											 @RequestParam(required = false) List<Integer> type,
											 @RequestParam(required = false) Boolean delivery){
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		Pagination pagination = new Pagination();
		pagination.setPageNum(1);
		pagination.setPageSize(1000);
		paginatedFilter.setPagination(pagination);
		MapContext mapContext = this.createMapContent(way,orderNo,status,ship,in,barcode,type,delivery);
		mapContext.put("ids",ids);
		mapContext.put("ship",true);
		mapContext.put("delivery",false);
		mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID,WebUtils.getCurrBranchId());
		paginatedFilter.setFilters(mapContext);
		return this.finishedStockFacade.writeExcel(paginatedFilter,new DispatchBillItemParam());
	}
	/**
	 * 成品信息统计
	 *
	 * @param
	 * @return
	 */
	@GetMapping("/count")
	@ApiOperation(value = "成品信息统计",notes = "成品信息统计")
	private RequestResult findFinishedStockCount() {
		String branchId=WebUtils.getCurrBranchId();
		return this.finishedStockFacade.findFinishedStockCount(branchId);
	}

	private MapContext createMapContentDto(String orderNo, String companyName,String customAddress,String dealerAddress,Date beginStockInputDate,Date endStockInputDate,Date beginPayDate,Date endPayDate,String produceType) {
		MapContext mapContext = new MapContext();
		if(orderNo!=null&&!orderNo.trim().equals("")){
			mapContext.put("orderNo",orderNo);
		}
		if(customAddress!=null&&!customAddress.trim().equals("")){
			mapContext.put("customAddress",customAddress);
		}
		if(dealerAddress!=null&&!dealerAddress.trim().equals("")){
			mapContext.put("dealerAddress",dealerAddress);
		}
		if(companyName!=null){
			mapContext.put("companyName",companyName);
		}
		if(produceType!=null){
			mapContext.put("produceType",produceType);
		}
		if(beginStockInputDate!=null){
			mapContext.put("beginStockInputTime",beginStockInputDate);
		}
		if(endStockInputDate!=null){
			mapContext.put("endStockInputTime",endStockInputDate);
		}
		if(beginPayDate!=null){
			mapContext.put("beginPayTime",beginPayDate);
		}
		if(endPayDate!=null){
			mapContext.put("endPayTime",endPayDate);
		}
		return mapContext;
	}

	private MapContext createMapContent(Integer way, String orderNo, Integer status,Integer ship,Integer in,String barcode,List<Integer> type,Boolean delivery) {
		MapContext mapContext = new MapContext();
		if (way!=null){
			mapContext.put("way",way);
		}
		if(orderNo!=null&&!orderNo.trim().equals("")){
			mapContext.put("orderNo",orderNo);
		}
		if(status!=null){
			mapContext.put(WebConstant.KEY_ENTITY_STATUS,status);
		}
		if(ship!=null){
			mapContext.put("ship",ship);
		}
		if(in!=null){
			mapContext.put("ins",in);
		}
		if(barcode!=null){
			mapContext.put("barcode",barcode);
		}
		if(type!=null){
			mapContext.put("type",type);
		}
		if(delivery!=null){
			mapContext.put("delivery",delivery);
		}
		return mapContext;
	}

}
