package com.lwxf.industry4.webapp.controller.admin.factory.finance;

import com.lwxf.commons.json.JsonMapper;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentFunds;
import com.lwxf.industry4.webapp.common.enums.order.ProduceOrderWay;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.FileMimeTypeUtil;
import com.lwxf.industry4.webapp.common.utils.excel.impl.PaymentExcelParam;
import com.lwxf.industry4.webapp.domain.dto.financing.PaymentDto;
import com.lwxf.industry4.webapp.domain.entity.financing.PaymentFiles;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.dealer.OrderFacade;
import com.lwxf.industry4.webapp.facade.admin.factory.finance.FinanceFacade;
import com.lwxf.mybatis.utils.MapContext;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.*;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2019/1/9/009 9:58
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@RestController
@RequestMapping(value = "/api/f/finances", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
@Api(value = "FinanceController", tags = "财务审核管理")
public class FinanceController {
	@Resource(name = "financeFacade")
	private FinanceFacade financeFacade;
	@Resource(name = "orderFacade")
	private OrderFacade orderFacade;

	/**
	 * 查询需要财务审核的订单与支付记录列表
	 *
	 * @param type      订单类型
	 * @param companyId 经销商公司ID
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@GetMapping("customorders")
	@ApiOperation(value = "查询需要财务审核的订单与支付记录列表", notes = "查询需要财务审核的订单与支付记录列表", response = PaymentDto.class)
	private String findOrderFinanceList(@RequestParam(required = false) @ApiParam(value = "类型") Integer type,
										@RequestParam(required = false) @ApiParam(value = "公司Id") String companyId,
										@RequestParam(required = false) @ApiParam(value = "页码") Integer pageNum,
										@RequestParam(required = false) @ApiParam(value = "状态") Integer status,
										@RequestParam(required = false) @ApiParam(value = "订单编号") String orderNo,
										@RequestParam(required = false) @ApiParam(value = "数据量") Integer pageSize,
										@RequestParam(required = false) @ApiParam(value = "客户手机号") String customerTel,
										@RequestParam(required = false) @ApiParam(value = "款项") Integer funds) {

		if (null == pageSize) {
			pageSize = AppBeanInjector.configuration.getPageSizeLimit();
		}
		if (null == pageNum) {
			pageNum = 1;
		}
		JsonMapper jsonMapper = JsonMapper.createAllToStringMapper();
		MapContext mapContext = this.createMapContext(type, companyId, null, null, null, null, null, orderNo, null, status, funds, customerTel, null, null,null,null);
		RequestResult result = this.financeFacade.findOrderFinanceList(mapContext, pageNum, pageSize);
		return jsonMapper.toJson(result);
	}

	@GetMapping("customorders/{paymentId}")
	@ApiOperation(value = "支付记录详情", notes = "支付记录详情", response = PaymentDto.class)
	private String findOrderFinanceInfo(@PathVariable String paymentId) {
		JsonMapper jsonMapper = JsonMapper.createAllToStringMapper();
		RequestResult result = this.financeFacade.findOrderFinanceInfo(paymentId);
		return jsonMapper.toJson(result);
	}

	/**
	 * 查询待审批的采购单列表
	 *
	 * @param name
	 * @param supplierId
	 * @param batch
	 * @param storageId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@GetMapping("purchases")
	private String findPurchaseList(@RequestParam(required = false) String name,
									@RequestParam(required = false) String supplierId,
									@RequestParam(required = false) String batch,
									@RequestParam(required = false) String storageId,
									@RequestParam(required = false) Integer pageNum,
									@RequestParam(required = false) Integer pageSize) {
		if (null == pageSize) {
			pageSize = AppBeanInjector.configuration.getPageSizeLimit();
		}
		if (null == pageNum) {
			pageNum = 1;
		}
		JsonMapper jsonMapper = JsonMapper.createAllToStringMapper();
		MapContext mapContext = this.createMapContext(null, null, name, supplierId, batch, storageId, null, null, null, null, null, null, null, null,null,null);
		RequestResult result = this.financeFacade.findPurchaseList(mapContext, pageNum, pageSize);
		return jsonMapper.toJson(result);
	}

	/**
	 * 查询待审核的经销商收支列表
	 *
	 * @param type
	 * @param leaderTel
	 * @param no
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@GetMapping("dealerpays")
	private RequestResult findDealerPayList(@RequestParam(required = false) Integer type,
											@RequestParam(required = false) String leaderTel,
											@RequestParam(required = false) String no,
											@RequestParam(required = false) Integer pageNum,
											@RequestParam(required = false) Integer pageSize) {

//        if (null == pageSize) {
//            pageSize = AppBeanInjector.configuration.getPageSizeLimit();
//        }
//        if (null == pageNum) {
//            pageNum = 1;
//        }
//        MapContext mapContext = this.createMapContext(null, null, null, null, null, null, leaderTel, no, null,null,null);
//        if (type != null && type != -1) {
//            mapContext.put("type", Arrays.asList(type));
//        } else {
//            mapContext.put("type", Arrays.asList(2, 3));
//        }
//        return this.financeFacade.findDealerPayList(mapContext, pageNum, pageSize);
		return ResultFactory.generateRequestResult(new PaginatedList());
	}

	/**
	 * 修改经销商支付记录状态
	 *
	 * @param id
	 * @param type
	 * @return
	 */
	@PutMapping("dealerpays/{id}")
	private RequestResult updateFinancePayStatus(@PathVariable String id, @RequestParam Integer type) {
		return this.financeFacade.updateFinancePayStatus(id, type);
	}

	/**
	 * 查询待审批供应商列表
	 *
	 * @param name
	 * @param leaderTel
	 * @param leaderName
	 * @param no
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@GetMapping("suppliers")
	private String findSupplierList(@RequestParam(required = false) String name,
									@RequestParam(required = false) String leaderTel,
									@RequestParam(required = false) String leaderName,
									@RequestParam(required = false) String no,
									@RequestParam(required = false) Integer pageNum,
									@RequestParam(required = false) Integer pageSize) {

		if (null == pageSize) {
			pageSize = AppBeanInjector.configuration.getPageSizeLimit();
		}
		if (null == pageNum) {
			pageNum = 1;
		}
		MapContext mapContext = this.createMapContext(null, null, name, null, null, null, leaderTel, no, leaderName, null, null, null, null, null,null,null);
		JsonMapper jsonMapper = JsonMapper.createAllToStringMapper();
		RequestResult result = this.financeFacade.findSupplierList(mapContext, pageNum, pageSize);
		return jsonMapper.toJson(result);
	}

	/**
	 * 审批供应商
	 *
	 * @param id
	 * @param status
	 * @return
	 */
	@PutMapping("suppliers/{id}/{status}")
	private RequestResult updateSupplier(@PathVariable String id, @PathVariable Integer status) {
		return this.financeFacade.updateSupplier(id, status);
	}

	/**
	 * 查询待审批的售后单列表
	 *
	 * @param type
	 * @param no
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@GetMapping("aftersale")
	private String findAftersaleList(@RequestParam(required = false) Integer type,
									 @RequestParam(required = false) String no,
									 @RequestParam(required = false) Integer pageNum,
									 @RequestParam(required = false) Integer pageSize) {

		if (null == pageSize) {
			pageSize = AppBeanInjector.configuration.getPageSizeLimit();
		}
		if (null == pageNum) {
			pageNum = 1;
		}
		MapContext mapContext = this.createMapContext(type, null, null, null, null, null, null, no, null, null, null, null, null, null,null,null);
		JsonMapper jsonMapper = JsonMapper.createAllToStringMapper();
		RequestResult result = this.financeFacade.findAftersaleList(mapContext, pageNum, pageSize);
		return jsonMapper.toJson(result);
	}

	/**
	 * 查询待审批的经销商加盟费支付记录
	 *
	 * @param name
	 * @param no
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@GetMapping("dealer")
	private String findDealerList(@RequestParam(required = false) String name,
								  @RequestParam(required = false) String no,
								  @RequestParam(required = false) Integer pageNum,
								  @RequestParam(required = false) Integer pageSize) {

		if (null == pageSize) {
			pageSize = AppBeanInjector.configuration.getPageSizeLimit();
		}
		if (null == pageNum) {
			pageNum = 1;
		}
		MapContext mapContext = this.createMapContext(null, null, name, null, null, null, null, no, null, null, null, null, null, null,null,null);
		JsonMapper jsonMapper = JsonMapper.createAllToStringMapper();
		RequestResult result = this.financeFacade.findDealerList(mapContext, pageNum, pageSize);
		return jsonMapper.toJson(result);
	}

	/**
	 * 加盟费确认收款
	 *
	 * @param id
	 * @return
	 */
	@PutMapping("/dealer/{id}")
	private RequestResult updateDealerPay(@PathVariable String id) {
		return this.financeFacade.updateDealerPay(id);
	}

	/**
	 * 查询需要财务审核的外协生产单
	 *
	 * @param pageNum
	 * @param orderNo
	 * @param pageSize
	 * @return
	 */
	@ApiOperation(value = "查询需要财务审核的外协生产单", notes = "查询需要财务审核的外协生产单")
	@ApiImplicitParams({
			@ApiImplicitParam(value = "页码", name = "pageNum", dataType = "int", paramType = "query"),
			@ApiImplicitParam(value = "订单编号", name = "orderNo", dataType = "string", paramType = "query"),
			@ApiImplicitParam(value = "页数", name = "pageSize", dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "no", value = "生产编号", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "produceState", value = "生产单状态 0 未开始 1 生产中 2 已完成", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "pay", value = "是否已付款  true 已付款 false 未付款", dataType = "string", paramType = "query")
	})
	@GetMapping("/coordination")
	private String findCoordinationList(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
										@RequestParam(required = false) String orderNo,
										@RequestParam(required = false) String no,
										@RequestParam(required = false) Integer produceState,
										@RequestParam(required = false) Boolean pay,
										@RequestParam(required = false, defaultValue = "10") Integer pageSize) {
		MapContext mapContext = this.createProduceMapContext(no, orderNo, ProduceOrderWay.COORDINATION.getValue(), null, produceState, pay);
		List<Map<String, String>> sorts = new ArrayList<Map<String, String>>();
		Map<String, String> paySorts = new HashMap<String, String>();
		paySorts.put("is_pay", "asc");
		sorts.add(paySorts);
		JsonMapper jsonMapper = JsonMapper.createAllToStringMapper();
		RequestResult result = this.orderFacade.findProducesList(mapContext, pageNum, pageSize, sorts);
		return jsonMapper.toJson(result);
	}

	/**
	 * 财务审核外协生产单
	 *
	 * @return
	 */
	@ApiOperation(value = "财务审核外协生产单", notes = "财务审核外协生产单")
	@ApiImplicitParams({
			@ApiImplicitParam(value = "外协单主键ID", name = "id", dataType = "int", paramType = "path")
	})
	@PutMapping("/coordination/{id}")
	private RequestResult updateCoordinationPay(@PathVariable String id, @RequestBody MapContext map) {
		return this.financeFacade.updateCoordinationPay(id, map);
	}

	/**
	 * 订单货款或者 设计费 支付记录 审核
	 *
	 * @param id
	 * @return
	 */
	@PutMapping("/customorders/{id}")
	@ApiOperation(value = "订单货款或者 设计费 支付记录 审核", notes = "订单货款或者 设计费 支付记录 审核")
	private RequestResult updateCustomOrdersPay(@PathVariable @ApiParam(value = "支付记录主键ID") String id, @RequestBody MapContext map) {
		return this.financeFacade.updateCustomOrdersPay(id, map);
	}
	/**
	 * 快速充值及审核
	 *
	 * @param id
	 * @return
	 */
	@PutMapping("/customorders/{id}/fastquit")
	@ApiOperation(value = "快速充值及审核", notes = "快速充值及审核")
	private RequestResult fastQuitCustomOrdersPay(@PathVariable @ApiParam(value = "支付记录主键ID") String id, @RequestBody MapContext map) {
		return this.financeFacade.fastQuitCustomOrdersPay(id, map);
	}

	/**
	 * 财务反审订单
	 */
	@PutMapping("/customorders/{id}/other")
	@ApiOperation(value = "财务反审订单", notes = "财务反审订单")
	private RequestResult counterTrialOrdersPay(@PathVariable @ApiParam(value = "支付记录主键ID") String id) {
		return this.financeFacade.counterTrialOrdersPay(id);
	}



	/**
	 * 查询支付记录
	 * @param pageNum
	 * @param pageSize
	 * @param status
	 * @param orderNo
	 * @param type
	 * @param companyId
	 * @param companyName
	 * @param startCreatedTime
	 * @param endCreatedTime
	 * @param startAuditTime
	 * @param endAuditTime
	 * @param funds
	 * @param financeTop
	 * @return
	 */

	@GetMapping
	@ApiOperation(value = "查询支付记录", notes = "查询支付记录")
	private String findPayemntList(@RequestParam(required = false, defaultValue = "1") Integer pageNum, @RequestParam(required = false, defaultValue = "10") Integer pageSize
			, @RequestParam(required = false) @ApiParam(value = "状态") String status
			, @RequestParam(required = false) @ApiParam(value = "订单编号") String orderNo
			, @RequestParam(required = false) @ApiParam(value = "类型") String type
			, @RequestParam(required = false) @ApiParam(value = "单据来源类型") String resourceType
			, @RequestParam(required = false) @ApiParam(value = "公司Id") String companyId
			, @RequestParam(required = false) @ApiParam(value = "公司名称") String companyName
			, @RequestParam(required = false) @ApiParam(value = "录入时间开始时间") String startCreatedTime
			, @RequestParam(required = false) @ApiParam(value = "录入时间结束始时间") String endCreatedTime
			, @RequestParam(required = false) @ApiParam(value = "支付时间(审核时间)开始") String startAuditTime
			, @RequestParam(required = false) @ApiParam(value = "支付时间(审核时间)结束") String endAuditTime
			, @RequestParam(required = false) @ApiParam(value = "款项") String funds
			, @RequestParam(required = false) @ApiParam(value = "顶部信息统计列表") String financeTop) {
		JsonMapper jsonMapper = JsonMapper.createAllToStringMapper();
		MapContext mapContext=MapContext.newOne();
		if (companyId != null && !companyId.trim().equals("")) {
			mapContext.put(WebConstant.KEY_ENTITY_CREATOR, companyId);
		}
		if (companyName != null && !companyName.trim().equals("")) {
			mapContext.put(WebConstant.KEY_ENTITY_NAME, companyName);
		}
		if (orderNo != null && !orderNo.trim().equals("")) {
			mapContext.put("orderNo", orderNo);
		}
		if (type != null && !type.trim().equals("")) {
			mapContext.put("type", type);
		}
		if (resourceType != null && !resourceType.trim().equals("")) {
			mapContext.put("resourceType", resourceType);
		}
		if (startCreatedTime != null && !startCreatedTime.trim().equals("")) {
			mapContext.put("startCreatedTime", startCreatedTime);
		}
		if (endCreatedTime != null && !endCreatedTime.trim().equals("")) {
			mapContext.put("endCreatedTime", endCreatedTime);
		}
		if (startAuditTime != null && !startAuditTime.trim().equals("")) {
			mapContext.put("startAuditTime", startAuditTime);
		}
		if (endAuditTime != null && !endAuditTime.trim().equals("")) {
			mapContext.put("endAuditTime", endAuditTime);
		}
		if (funds != null && !funds.trim().equals("")) {
			mapContext.put("funds", funds);
		}
		if (financeTop != null && !financeTop.trim().equals("")) {
			if(financeTop.equals("4")){financeTop="0";}//由于前台0无法传递过来，所以暂时用4来代替0
			mapContext.put("financeTop", financeTop);
		}
		if (status != null) {
			mapContext.put(WebConstant.KEY_ENTITY_STATUS, status);
		}
		RequestResult result = this.financeFacade.findOrderFinanceList(mapContext, pageNum, pageSize);
		return jsonMapper.toJson(result);
	}

	/**
	 * 支付记录 导出Excel
	 * @param pageNum
	 * @param pageSize
	 * @param status
	 * @param orderNo
	 * @param type
	 * @param companyId
	 * @param companyName
	 * @param startCreatedTime
	 * @param endCreatedTime
	 * @param startAuditTime
	 * @param endAuditTime
	 * @param funds
	 * @param financeTop
	 * @return
	 */

	@GetMapping("/writeExcel")
	@ApiOperation(value = "支付记录 导出Excel", notes = "支付记录 导出Excel")
	private String writePayemntList(@RequestParam(required = false, defaultValue = "1") Integer pageNum, @RequestParam(required = false, defaultValue = "10") Integer pageSize
			, @RequestParam(required = false) @ApiParam(value = "状态") String status
			, @RequestParam(required = false) @ApiParam(value = "订单编号") String orderNo
			, @RequestParam(required = false) @ApiParam(value = "类型") String type
			, @RequestParam(required = false) @ApiParam(value = "单据来源类型") String resourceType
			, @RequestParam(required = false) @ApiParam(value = "公司Id") String companyId
			, @RequestParam(required = false) @ApiParam(value = "公司名称") String companyName
			, @RequestParam(required = false) @ApiParam(value = "录入时间开始时间") String startCreatedTime
			, @RequestParam(required = false) @ApiParam(value = "录入时间结束始时间") String endCreatedTime
			, @RequestParam(required = false) @ApiParam(value = "支付时间(审核时间)开始") String startAuditTime
			, @RequestParam(required = false) @ApiParam(value = "支付时间(审核时间)结束") String endAuditTime
			, @RequestParam(required = false) @ApiParam(value = "款项") String funds
			, @RequestParam(required = false) @ApiParam(value = "顶部信息统计列表") String financeTop) {
		JsonMapper jsonMapper = JsonMapper.createAllToStringMapper();
		MapContext mapContext=MapContext.newOne();
		StringBuffer params=new StringBuffer();//查询参数，导出Excel时使用
		if (companyId != null && !companyId.trim().equals("")) {
			mapContext.put(WebConstant.KEY_ENTITY_CREATOR, companyId);
		}
		if (companyName != null && !companyName.trim().equals("")) {
			mapContext.put(WebConstant.KEY_ENTITY_NAME, companyName);
			params.append(companyName+" ");
		}
		if (orderNo != null && !orderNo.trim().equals("")) {
			mapContext.put("orderNo", orderNo);
			params.append(orderNo+" ");
		}
		if (type != null && !type.trim().equals("")) {
			mapContext.put("type", type);
		}
		if (resourceType != null && !resourceType.trim().equals("")) {
			mapContext.put("resourceType", resourceType);
		}
		if (startCreatedTime != null && !startCreatedTime.trim().equals("")) {
			mapContext.put("startCreatedTime", startCreatedTime);
		}
		if (endCreatedTime != null && !endCreatedTime.trim().equals("")) {
			mapContext.put("endCreatedTime", endCreatedTime);
		}
		if (startAuditTime != null && !startAuditTime.trim().equals("")) {
			mapContext.put("startAuditTime", startAuditTime);
			params.append(startAuditTime+"-");
		}
		if (endAuditTime != null && !endAuditTime.trim().equals("")) {
			mapContext.put("endAuditTime", endAuditTime);
			params.append(endAuditTime+" ");
		}
		if (funds != null && !funds.trim().equals("")) {
			mapContext.put("funds", funds);
			params.append(PaymentFunds.getByValue(Integer.valueOf(funds)));
		}
		if (financeTop != null && !financeTop.trim().equals("")) {
			if(financeTop.equals("4")){financeTop="0";}//由于前台0无法传递过来，所以暂时用4来代替0
			mapContext.put("financeTop", financeTop);
		}
		if (status != null) {
			mapContext.put(WebConstant.KEY_ENTITY_STATUS, status);
		}
		RequestResult result = this.financeFacade.writePayemntList(mapContext, pageNum, pageSize,new PaymentExcelParam(),params);
		return jsonMapper.toJson(result);
	}

	@GetMapping("/countinfo")
	@ApiOperation(value = "合计金额、待付款金额、已付款金额、挂账金额与订单数量", notes = "合计金额、待付款金额、已付款金额、挂账金额与订单数量")
	private String findPayemntCountinfo(
			 @RequestParam(required = false) @ApiParam(value = "状态") String status
			, @RequestParam(required = false) @ApiParam(value = "订单编号") String orderNo
			, @RequestParam(required = false) @ApiParam(value = "类型") String type
			, @RequestParam(required = false) @ApiParam(value = "单据来源类型") String resourceType
			, @RequestParam(required = false) @ApiParam(value = "公司Id") String companyId
			, @RequestParam(required = false) @ApiParam(value = "公司名称") String companyName
			, @RequestParam(required = false) @ApiParam(value = "录入时间开始时间") String startCreatedTime
			, @RequestParam(required = false) @ApiParam(value = "录入时间结束始时间") String endCreatedTime
			, @RequestParam(required = false) @ApiParam(value = "支付时间(审核时间)开始") String startAuditTime
			, @RequestParam(required = false) @ApiParam(value = "支付时间(审核时间)结束") String endAuditTime
			, @RequestParam(required = false) @ApiParam(value = "款项") String funds
			, @RequestParam(required = false) @ApiParam(value = "顶部信息统计列表") String financeTop) {
		JsonMapper jsonMapper = JsonMapper.createAllToStringMapper();
		MapContext mapContext=MapContext.newOne();
		if (companyId != null && !companyId.trim().equals("")) {
			mapContext.put(WebConstant.KEY_ENTITY_CREATOR, companyId);
		}
		if (companyName != null && !companyName.trim().equals("")) {
			mapContext.put(WebConstant.KEY_ENTITY_NAME, companyName);

		}
		if (orderNo != null && !orderNo.trim().equals("")) {
			mapContext.put("orderNo", orderNo);

		}
		if (type != null && !type.trim().equals("")) {
			mapContext.put("type", type);
		}
		if (resourceType != null && !resourceType.trim().equals("")) {
			mapContext.put("resourceType", resourceType);
		}
		if (startCreatedTime != null && !startCreatedTime.trim().equals("")) {
			mapContext.put("startCreatedTime", startCreatedTime);
		}
		if (endCreatedTime != null && !endCreatedTime.trim().equals("")) {
			mapContext.put("endCreatedTime", endCreatedTime);
		}
		if (startAuditTime != null && !startAuditTime.trim().equals("")) {
			mapContext.put("startAuditTime", startAuditTime);

		}
		if (endAuditTime != null && !endAuditTime.trim().equals("")) {
			mapContext.put("endAuditTime", endAuditTime);

		}
		if (funds != null && !funds.trim().equals("")) {
			mapContext.put("funds", funds);

		}
		if (financeTop != null && !financeTop.trim().equals("")) {
			if(financeTop.equals("4")){financeTop="0";}//由于前台0无法传递过来，所以暂时用4来代替0
			mapContext.put("financeTop", financeTop);
		}
		if (status != null) {
			mapContext.put(WebConstant.KEY_ENTITY_STATUS, status);
		}
		RequestResult result = this.financeFacade.findOrderFinanceCountinfo(mapContext);
		return jsonMapper.toJson(result);
	}

	@GetMapping("/overview")
	@ApiOperation(value = "查询财务审核统计", notes = "查询财务审核统计")
	private RequestResult findFinanceOverview() {
		return this.financeFacade.findFinanceOverview();
	}


	/**
	 * 上传支付审核记录附件
	 *
	 * @param id
	 * @param multipartFileList
	 * @return
	 */
	@PostMapping("/{id}/files")
	@ApiOperation(value = "上传支付审核记录附件", notes = "上传支付审核记录附件", response = PaymentFiles.class)
	private RequestResult uploadFiles(@PathVariable @ApiParam(value = "支付记录主键ID") String id, @RequestBody List<MultipartFile> multipartFileList) {
		Map<String, String> result = new HashMap<>();
		if (multipartFileList == null || multipartFileList.size() == 0) {
			result.put("file", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, result);
		}
		for (MultipartFile multipartFile : multipartFileList) {
			if (multipartFile == null) {
				result.put("file", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			} else if (!FileMimeTypeUtil.isLegalImageType(multipartFile)) {
				result.put("file", AppBeanInjector.i18nUtil.getMessage("VALIDATE_ILLEGAL_ARGUMENT"));
			} else if (multipartFile.getSize() > 1024 * 1024 * AppBeanInjector.configuration.getUploadBackgroundMaxsize()) {
				return ResultFactory.generateErrorResult(ErrorCodes.BIZ_FILE_SIZE_LIMIT_10031, LwxfStringUtils.format(AppBeanInjector.i18nUtil.getMessage("BIZ_FILE_SIZE_LIMIT_10031"), AppBeanInjector.configuration.getUploadBackgroundMaxsize()));
			}
			if (result.size() > 0) {
				return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, result);
			}
		}
		return this.financeFacade.uploadFiles(id, multipartFileList);
	}

	/**
	 * 删除支付记录资源文件
	 *
	 * @param fileId
	 * @return
	 */
	@DeleteMapping("/files/{fileId}")
	@ApiOperation(value = "删除支付记录资源文件", notes = "删除支付记录资源文件")
	private RequestResult deleteFile(@PathVariable @ApiParam(value = "资源文件ID") String fileId) {
		return this.financeFacade.deleteFile(fileId);
	}

	private MapContext createProduceMapContext(String no, String orderNo, Integer way, Integer type, Integer state, Boolean pay) {
		MapContext mapContext = new MapContext();
		if (no != null && !no.trim().equals("")) {
			mapContext.put(WebConstant.STRING_NO, no);
		}
		if (orderNo != null && !orderNo.trim().equals("")) {
			mapContext.put("orderNo", orderNo);
		}
		if (way != null) {
			mapContext.put("way", Arrays.asList(way));
		}
		if (type != null) {
			mapContext.put("type", Arrays.asList(type));
		}
		if (state != null) {
			mapContext.put(WebConstant.KEY_ENTITY_STATE, Arrays.asList(state));
		}
		if (pay != null) {
			mapContext.put("pay", pay);
		}
		return mapContext;
	}

	private MapContext createMapContext(Integer type, String companyId, String name, String supplierId, String batch, String storageId, String leaderTel, String no, String leaderName, Integer status, Integer funds, String customerTel, String orderNo, String financeTop,String createdTime,String auditTime) {
		MapContext mapContext = new MapContext();
		if (type != null && type != -1) {
			mapContext.put("type", type);
		}
		if (companyId != null && !companyId.trim().equals("")) {
			mapContext.put(WebConstant.KEY_ENTITY_CREATOR, companyId);
		}
		if (name != null && !name.trim().equals("")) {
			mapContext.put(WebConstant.KEY_ENTITY_NAME, name);
		}
		if (supplierId != null && !supplierId.trim().equals("")) {
			mapContext.put("supplierId", supplierId);
		}
		if (batch != null && !batch.trim().equals("")) {
			mapContext.put("batch", batch);
		}
		if (storageId != null && !storageId.trim().equals("")) {
			mapContext.put("storageId", storageId);
		}
		if (createdTime != null && !createdTime.trim().equals("")) {
			mapContext.put("created", createdTime);
		}
		if (auditTime != null && !auditTime.trim().equals("")) {
			mapContext.put("audited", auditTime);
		}
		if (leaderTel != null && !leaderTel.trim().equals("")) {
			mapContext.put("leaderTel", leaderTel);
		}
		if (no != null && !no.trim().equals("")) {
			mapContext.put("no", no);
		}
		if (leaderName != null && !leaderName.trim().equals("")) {
			mapContext.put("leaderName", leaderName);
		}
		if (status != null) {
			mapContext.put(WebConstant.KEY_ENTITY_STATUS, status);
		}
		if (funds != null) {
			mapContext.put("fundsList", Arrays.asList(funds));
		} else {
			mapContext.put("fundsList", Arrays.asList(PaymentFunds.DESIGN_FEE_CHARGE.getValue(), PaymentFunds.ORDER_FEE_CHARGE.getValue(), PaymentFunds.COORDINATION.getValue()));
		}
		if (customerTel != null) {
			mapContext.put("customerTel", customerTel);
		}
		if (orderNo != null) {
			mapContext.put("orderNo", orderNo);
		}
		if (financeTop != null) {
			if(financeTop.equals("4")){
				financeTop="0";
			}
				mapContext.put("financeTop", financeTop);
		}
		return mapContext;
	}
}
