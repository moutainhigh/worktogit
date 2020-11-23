package com.lwxf.industry4.webapp.controller.admin.factory.customOrder;

import com.lwxf.industry4.webapp.common.enums.company.FactoryEmployeeRole;
import com.lwxf.industry4.webapp.common.utils.UserUtil;
import com.lwxf.industry4.webapp.domain.dto.customorder.CustomOrderDto;
import io.swagger.annotations.*;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import org.apache.commons.lang3.StringUtils;

import com.lwxf.commons.json.JsonMapper;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.order.OrderProductStatus;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.customorder.OrderProductDto;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.order.ProduceOrderFacade;
import com.lwxf.mybatis.utils.MapContext;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/4/8/008 15:45
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@RestController
@RequestMapping(value = "/api/f/produceorder",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
@Api(value = "ProduceOrderController",tags = {"生产订单管理接口"})
public class ProduceOrderController {

	@Resource(name = "produceOrderFacade")
	private ProduceOrderFacade productOrderFacade;

	/**
	 * 查询产品生产单列表
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@ApiOperation(value = "查询产品生产单列表",notes = "查询产品生产单列表",response = OrderProductDto.class)

	@GetMapping
	private String findProduceOrderList(@RequestParam(required = false,defaultValue = "1")Integer pageNum,
											   @RequestParam(required = false,defaultValue = "10")Integer pageSize,
	                                           @RequestParam(required = false)@ApiParam(value = "订单编号") String no,
										       @RequestParam(required = false)@ApiParam(value = "下单人") String placeOrder,
											   @RequestParam(required = false)@ApiParam(value = "经销商名称")String companyName,
											   @RequestParam(required = false)@ApiParam(value = "客户地址")String customerAddress,
										       @RequestParam(required = false)@ApiParam(value = "客户名称")String customerName,
										       @RequestParam(required = false)@ApiParam(value = "经销商地址")String dealerAddress,
											   @RequestParam(required = false)@ApiParam(value = "开始生产时间的开始时间")String startProduceTime,
											   @RequestParam(required = false)@ApiParam(value = "开始生产时间的结束时间")String endProduceTime,
											   @RequestParam(required = false)@ApiParam(value = "计划完成时间的开始时间")String beginPlanFinishTime,
											   @RequestParam(required = false)@ApiParam(value = "计划完成时间的结束时间")String endPlanFinishTime,
										       @RequestParam(required = false)@ApiParam(value = "计划交付时间的开始时间")String beginPlanOrderFinishTime,
										       @RequestParam(required = false)@ApiParam(value = "计划交付时间的结束时间")String endPlanOrderFinishTime,
											   @RequestParam(required = false)@ApiParam(value = "订单状态")String status,
											   @RequestParam(required = false)@ApiParam(value = "下单时间的开始时间")String beginPayTime,
											   @RequestParam(required = false)@ApiParam(value = "下单时间的结束时间")String endPayTime,
											   @RequestParam(required = false)@ApiParam(value = "顶部数据统计 4-待生产 1-生产中 2-生产即将延期 3-已延期订单")String topNum){
		MapContext mapContext=MapContext.newOne();
		mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID,WebUtils.getCurrBranchId());
		if(LwxfStringUtils.isNotBlank(no)){
             mapContext.put("no",no);
		}
		if(LwxfStringUtils.isNotBlank(placeOrder)){
			mapContext.put("placeOrder",placeOrder);
		}
		if(LwxfStringUtils.isNotBlank(companyName)){
			mapContext.put("companyName",companyName);
		}
		if(LwxfStringUtils.isNotBlank(customerAddress)){
			mapContext.put("customerAddress",customerAddress);
		}
		if(LwxfStringUtils.isNotBlank(customerName)){
			mapContext.put("customerName",customerName);
		}
		if(LwxfStringUtils.isNotBlank(dealerAddress)){
			mapContext.put("dealerAddress",dealerAddress);
		}
		if(LwxfStringUtils.isNotBlank(startProduceTime)){
			mapContext.put("startProduceTime",startProduceTime);
		}
		if(LwxfStringUtils.isNotBlank(endProduceTime)){
			mapContext.put("endProduceTime",endProduceTime);
		}
		if(LwxfStringUtils.isNotBlank(beginPlanFinishTime)){
			mapContext.put("beginPlanFinishTime",beginPlanFinishTime);
		}
		if(LwxfStringUtils.isNotBlank(endPlanFinishTime)){
			mapContext.put("endPlanFinishTime",endPlanFinishTime);
		}
		if(LwxfStringUtils.isNotBlank(beginPlanOrderFinishTime)){
			mapContext.put("beginPlanOrderFinishTime",beginPlanOrderFinishTime);
		}
		if(LwxfStringUtils.isNotBlank(endPlanOrderFinishTime)){
			mapContext.put("endPlanOrderFinishTime",endPlanOrderFinishTime);
		}
		if(LwxfStringUtils.isNotBlank(beginPayTime)){
			mapContext.put("beginPayTime",beginPayTime);
		}
		if(LwxfStringUtils.isNotBlank(endPayTime)){
			mapContext.put("endPayTime",endPayTime);
		}
		if(LwxfStringUtils.isNotBlank(status)){
			mapContext.put("status",status);
		}
		if(LwxfStringUtils.isNotBlank(topNum)){
			if(topNum.equals("4")){topNum="0";}//由于前台0无法传递过来，所以暂时用4来代替0
			mapContext.put("topNum",topNum);
		}
		JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
		RequestResult result=this.productOrderFacade.findProductList(pageNum,pageSize,mapContext);
		return jsonMapper.toJson(result);
	}
	/**
	 * 产品开始生产操作
	 */
	@ApiOperation(value = "产品开始生产操作",notes = "产品开始生产操作")
	@PutMapping("/{productIds}")
	private RequestResult updateOrderProduct(@PathVariable String productIds){
		String userId=WebUtils.getCurrUserId();
		if(userId==null){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
		}
		return this.productOrderFacade.updateOrderProduct(productIds);
	}
	/**
	 * 取消生产操作
	 */
	@ApiOperation(value = "取消生产操作",notes = "取消生产操作")
	@PutMapping("/{productId}/cancel")
	private RequestResult cancelOrderProduct(@PathVariable String productId){
		String userId=WebUtils.getCurrUserId();
		if(userId==null){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
		}
		return this.productOrderFacade.cancelOrderProduct(productId);
	}

	/**
	 * 生产管理顶部信息数据统计接口
	 */
	@ApiOperation(value = "生产管理顶部信息数据统计接口",notes = "生产管理顶部信息数据统计接口")
	@GetMapping("/topcount")
	private RequestResult countTopProduce(){
		return this.productOrderFacade.countTopProduct();
	}
	/**
	 * 外协信息统计
	 *
	 * @param
	 * @return
	 */
	@GetMapping("/count")
	@ApiOperation(value = "外协信息统计",notes = "外协信息统计")
	private RequestResult findCoordinationCount() {
		String branchId= WebUtils.getCurrBranchId();
		return this.productOrderFacade.findCoordinationCount(branchId);
	}

	@GetMapping("/overview")
	@ApiOperation(value = "生产管理信息统计",notes = "生产管理信息统计")
	private RequestResult findProduceOrderOverview(){
		return this.productOrderFacade.findProduceOrderOverview();
	}

    /**
     * 添加生产延期原因
     * @param id 订单产品id
     * @param mapContext
     * @return
     */
    @PutMapping("/delayReason/{id}")
    @ApiOperation(value = "添加生产延期原因",notes = "添加生产延期原因")
    private RequestResult addProduceDelayReason(@PathVariable(value = "id") String id, @RequestBody MapContext mapContext) {
        // 参数校验
        RequestResult result = this.validateMap(mapContext);
        if (result != null) {
            return result;
        }
        return this.productOrderFacade.addProduceDelayReason(id, mapContext);
    }

    /**
     * 生产完成操作
     * @param id 订单产品id
     * @param mapContext
     * @return
     */
    @PutMapping("/produceComplete/{id}")
    @ApiOperation(value = "生产完成",notes = "生产完成")
    private RequestResult produceComplete(@PathVariable(value = "id") String id, @RequestBody MapContext mapContext) {
        // 参数校验
        RequestResult result = this.validateMap(mapContext);
        if (result != null) {
            return result;
        }
        return this.productOrderFacade.produceComplete(id, mapContext);
    }

    /**
     * 校验参数
     * @param mapContext 参数map集合
     * @return
     */
    private RequestResult validateMap(MapContext mapContext) {
        Map<String, String> validResult = new HashMap<>();
        if (mapContext.containsKey("delayReasonRemark")
                && mapContext.getTypedValue("delayReasonRemark", String.class).length() > 200) {
                validResult.put("delayReasonRemark", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
        }
        if (mapContext.containsKey("delayReason")
                && mapContext.getTypedValue("delayReason", String.class).length() > 100) {
            validResult.put("delayReason", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
        }

        if (validResult.size() > 0) {
            return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, validResult);
        }

        return null;
    }

	/**
	 * 外协厂家查看的菜单列表
	 * @param pageNum
	 * @param pageSize
	 * @param
	 * @return
	 */
	@GetMapping("/coordination")
	@ApiOperation(value = "外协生产列表",notes = "外协生产列表")
		private String findCoordinationList(@RequestParam(required = false,defaultValue = "1")Integer pageNum,
											@RequestParam(required = false,defaultValue = "10")Integer pageSize,
											@RequestParam(required = false)@ApiParam(value = "产品编号") String productNo,
											@RequestParam(required = false)@ApiParam(value = "外协状态") String produceStatus

											){
		MapContext mapContext=MapContext.newOne();
		mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID,WebUtils.getCurrBranchId());
		if(LwxfStringUtils.isNotBlank(productNo)){
			mapContext.put("productNo",productNo);
		}
		if(LwxfStringUtils.isNotBlank(produceStatus)){
			mapContext.put("produceStatus",produceStatus);
		}
		JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
		RequestResult result=this.productOrderFacade.findCoordinationList(mapContext,pageNum,pageSize);
		return jsonMapper.toJson(result);
		}
	/**
	 * 外协单开始生产操作
	 */
	@ApiOperation(value = "外协单开始生产操作",notes = "外协单开始生产操作")
	@PutMapping("/coordination/startwork")
	private RequestResult updateCoordinationProduce(@RequestParam String produceIds){
		String userId=WebUtils.getCurrUserId();
		if(userId==null){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
		}
		return this.productOrderFacade.updateCoordinationProduce(produceIds);
	}
	/**
	 * 取消生产操作
	 */
	@ApiOperation(value = "外协单取消生产操作",notes = "外协单取消生产操作")
	@PutMapping("/coordination/cancel")
	private RequestResult cancelCoordinationProduce(@RequestParam String produceIds){
		String userId=WebUtils.getCurrUserId();
		if(userId==null){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
		}
		return this.productOrderFacade.cancelCoordinationProduce(produceIds);
	}
	/**
	 * 外协单生产完成
	 */
	@ApiOperation(value = "外协单生产完成",notes = "外协单生产完成")
	@PutMapping("/coordination/end")
	private RequestResult endCoordinationProduce(@RequestParam String produceIds){
		String userId=WebUtils.getCurrUserId();
		if(userId==null){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
		}
		return this.productOrderFacade.endCoordinationProduce(produceIds);
	}
}
