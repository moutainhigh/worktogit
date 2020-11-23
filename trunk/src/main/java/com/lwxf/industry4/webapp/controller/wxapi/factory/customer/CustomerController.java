package com.lwxf.industry4.webapp.controller.wxapi.factory.customer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.json.JsonMapper;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.LoginUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.customer.WxCustomerDto;
import com.lwxf.industry4.webapp.domain.dto.customer.WxCustomerInfoDto;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.wxapi.factory.customer.CustomerFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：微信小程序工厂端 终端客户信息查看
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/6/6 0006 15:41
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Api(value = "wxCustomerController",tags = {"F端微信小程序接口: 终端客户信息"})
@RestController(value = "wxCustomerController")
@RequestMapping(value = "/wxapi/f", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class CustomerController {
	@Resource(name = "wxCustomerFacade")
    private CustomerFacade customerFacade;

	/**
	 * 查询客户列表
	 * @param pageNum
	 * @param pageSize
	 * @param condation
	 * @param cityId
	 * @param dealerId
	 * @param type
	 * @return
	 */
	@ApiOperation(value = "查询客户列表",notes = "查询客户列表接口",response = WxCustomerDto.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageNum",value = "页码",dataType = "integer",paramType = "query"),
			@ApiImplicitParam(name = "pageSize",value = "页数",dataType = "integer",paramType = "query"),
			@ApiImplicitParam(name = "condation",value = "客户姓名/电话/订单编号",dataType = "string",paramType = "query"),
			@ApiImplicitParam(name = "cityId",value = "地区id ",dataType = "string",paramType = "query"),
			@ApiImplicitParam(name = "dealerId",value = "经销商id",dataType = "string",paramType = "query"),
			@ApiImplicitParam(name = "type",value = "客户类型 1-潜在客户 2-执行客户 3-正常客户",dataType = "String",paramType = "query")
	})
	@GetMapping("/branchs/customers")
	public String findWxCustomers(@RequestParam(required = false) Integer pageNum,
								  @RequestParam(required = false) Integer pageSize,
								  @RequestParam(required = false) String condation,
								  @RequestParam(required = false) String cityId,
								  @RequestParam(required = false) String dealerId,
								  @RequestParam(required = false) String type
								  ){
		JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
		String atoken= WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
		if(uid==null){
			return jsonMapper.toJson(ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
		}
		String branchid =mapInfo.get("branchId")==null?null:mapInfo.get("branchId").toString();
		MapContext mapContext=MapContext.newOne();
		if(LwxfStringUtils.isNotBlank(condation)){
			mapContext.put("condation",condation);
		}
		if(LwxfStringUtils.isNotBlank(cityId)){
			mapContext.put("cityId",cityId);
		}
		if(LwxfStringUtils.isNotBlank(dealerId)){
			mapContext.put("dealerId",dealerId);
		}
		if(LwxfStringUtils.isNotBlank(type)){
			mapContext.put("type",type);
		}
		mapContext.put("branchId",branchid);
		RequestResult result=this.customerFacade.findWxCustomers(pageNum,pageSize,mapContext);
		return jsonMapper.toJson(result);
	}

	/**
	 * 查询客户详情
	 * @param customerId
	 * @return
	 */
	@GetMapping("/branchs/customers/{customerId}")
	@ApiOperation(value = "查询客户详情",notes = "查询客户详情接口",response = WxCustomerInfoDto.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "customerId",value = "客户id",required = true,dataType = "string",paramType = "path")
	})
	public String findWxCustomerInfo(@PathVariable String customerId
									 ){
		JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
		String atoken= WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
		if(uid==null){
			return jsonMapper.toJson(ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
		}
		String branchid =mapInfo.get("branchId")==null?null:mapInfo.get("branchId").toString();

		return jsonMapper.toJson(this.customerFacade.findWxCustomerInfo(branchid,customerId));
	}

	/**
	 * 添加客户
	 * @param mapContext
	 * @return
	 */
	@PostMapping("/branchs/customers")
	@ApiOperation(value = "添加客户",notes = "添加客户接口")
	public RequestResult addWxCustomers(@RequestBody MapContext mapContext){
		String atoken= WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
		if(uid==null){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
		}
		String branchid =mapInfo.get("branchId")==null?null:mapInfo.get("branchId").toString();

		return this.customerFacade.addWxCustomers(mapContext,branchid,uid);

	}
	/**
	 * 查询企业下的
	 * 经销商列表
	 */
	@ApiOperation(value = "添加客户时选择的经销商接口",notes = "添加客户时选择的经销商接口")
	@GetMapping("/branchs/dealers/addCustomer")
      public RequestResult findWxDealersAddCustomer(@RequestParam(required = false) String cityId){
		String atoken= WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String branchid =mapInfo.get("branchId")==null?null:mapInfo.get("branchId").toString();
      	return this.customerFacade.findWxDealersAddCustomer(branchid,cityId);
	  }

	/**
	 * 经销商下的员工列表
	 */
	@GetMapping("/branchs/dealers/{dealerId}/employees")
	@ApiOperation(value = "添加客户时选择的员工接口",notes = "添加客户时选择的员工接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "dealerId",value = "经销商id",required = true,dataType = "string",paramType = "path")
	})
	public RequestResult findCompanyEmployeesAddCustomer(@PathVariable String dealerId
											  ){
		String atoken= WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String branchid =mapInfo.get("branchId")==null?null:mapInfo.get("branchId").toString();
		RequestResult result=this.customerFacade.findWxCompanyEmployees(branchid,dealerId);
		return  result;
	}
	/**
	 * 客户详情页，更多订单查询接口
	 */
	@GetMapping("/branchs/customers/{customerId}/orders")
	@ApiOperation(value = "客户详情页，更多订单查询接口",notes = "客户详情页，更多订单查询接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "customerId",value = "客户id",required = true,dataType = "string",paramType = "path")
	})
	public RequestResult findCustomerOrders(@PathVariable String customerId,
											@RequestParam(required = false) Integer pageNum,
											@RequestParam(required = false) Integer pageSize
	){
		String atoken= WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String branchid =mapInfo.get("branchId")==null?null:mapInfo.get("branchId").toString();
		RequestResult result=this.customerFacade.findCustomerOrders(pageSize,pageNum,customerId);
		return  result;
	}
}
