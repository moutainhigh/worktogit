package com.lwxf.industry4.webapp.controller.smallProgram.smallProgramOrder;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.lwxf.commons.json.JsonMapper;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.order.OrderType;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.LoginUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.customorder.WxCustomerOrderInfoDto;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.smallProgram.smallProgramOrder.SpOrderFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/10/19 0019 9:56
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Api(value = "SpOrderController",tags ={"小程序F端接口: 订单数据管理"} )
@RestController(value = "SpOrderController")
@RequestMapping(value = "/spapi/f/orders", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class SpOrderController {
	@Resource(name = "spOrderFacade")
	private SpOrderFacade spOrderFacade;
	@ApiOperation(value = "订单列表",notes = "订单列表")
	@ApiImplicitParams({
			@ApiImplicitParam(value = "订单编号",name = "orderNo",dataType = "string",paramType = "query"),
			@ApiImplicitParam(value = "经销商名称",name = "dealerName",dataType = "string",paramType = "query"),
			@ApiImplicitParam(value = "订单状态",name = "orderStatus",dataType = "string",paramType = "query"),
			@ApiImplicitParam(value = "开始时间",name = "startTime",dataType = "string",paramType = "query"),
			@ApiImplicitParam(value = "结束时间",name = "endTime",dataType = "string",paramType = "query"),
			@ApiImplicitParam(value = "时间分类0-今天 1-本周 2-本月 3-本季 4-本年",name = "timeType",dataType = "string",paramType = "query"),
			@ApiImplicitParam(value = "页码",name = "pageNum",dataType = "Integer",paramType = "query"),
			@ApiImplicitParam(value = "条数",name = "pageSize",dataType = "Integer",paramType = "query"),
			@ApiImplicitParam(value = "业务员/下单员/跟单员/大区经理 名称",name = "saleName",dataType = "string",paramType = "query"),
			@ApiImplicitParam(value = "业务员/下单员/跟单员/大区经理 ID",name = "saleId",dataType = "string",paramType = "query"),
	})
	@GetMapping
	private String findSmallProgramOrderList(@RequestParam(required = false)String orderNo,
											 @RequestParam(required = false)String dealerName,
											 @RequestParam(required = false)String saleName,
											 @RequestParam(required = false)String saleId,
											 @RequestParam(required = false)String orderStatus,
											 @RequestParam(required = false)String startTime,
											 @RequestParam(required = false)String endTime,
											 @RequestParam(required = false)Integer pageNum,
											 @RequestParam(required = false)Integer pageSize
	){
		JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
		String atoken= WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String branchId =mapInfo.get("branchId")==null?null:mapInfo.get("branchId").toString();
		MapContext mapContext= MapContext.newOne();
		if(orderNo!=null&&!orderNo.equals("")){
			mapContext.put("orderNo",orderNo);
		}
		if(dealerName!=null&&!dealerName.equals("")){
			mapContext.put("dealerName",dealerName);
		}
		if(saleName!=null&&!saleName.equals("")){
			mapContext.put("saleName",saleName);
		}
		if(saleId!=null&&!saleId.equals("")){
			mapContext.put("saleId",saleId);
		}
		if(startTime!=null&&!startTime.equals("")){
			mapContext.put("startTime",startTime);
		}
		if(endTime!=null&&!endTime.equals("")){
			mapContext.put("endTime",endTime);
		}
		if(orderStatus!=null&&!orderStatus.equals("")){
			mapContext.put("status",orderStatus);
		}
		mapContext.put("branchId", branchId);
		mapContext.put("type", OrderType.NORMALORDER.getValue());
		return jsonMapper.toJson(this.spOrderFacade.findSmallProgramOrderList(pageNum,pageSize,mapContext));
	}
	@ApiOperation(value = "查询订单详情接口",notes = "查询订单详情接口",response = WxCustomerOrderInfoDto.class)
	@GetMapping("/{orderId}")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "orderId",value = "订单id",dataType = "string",paramType = "path",required = true)
	})
	public String findWxOrderInfo(@PathVariable String orderId){
		JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
		String atoken= WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
		if(uid==null){
			return jsonMapper.toJson(ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
		}
		String branchid =mapInfo.get("branchId")==null?null:mapInfo.get("branchId").toString();
		RequestResult result=this.spOrderFacade.findSmallProgramOrderInfo(branchid,orderId);
		return jsonMapper.toJson(result);
	}

	@ApiOperation(value = "订单首页统计数据展示",notes = "订单首页统计数据展示")
	@GetMapping("/index")
    public String findOrderIndexInfo(){
		JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
		String atoken= WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
		if(uid==null){
			return jsonMapper.toJson(ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
		}
		String branchid =mapInfo.get("branchId")==null?null:mapInfo.get("branchId").toString();
		MapContext mapContext=MapContext.newOne();
		mapContext.put("branchId", branchid);
		mapContext.put("type", OrderType.NORMALORDER.getValue());
		RequestResult result=this.spOrderFacade.findOrderIndexInfo(mapContext);
		return jsonMapper.toJson(result);
	}
}
