package com.lwxf.industry4.webapp.controller.wxapi.dealer.company;

import com.lwxf.industry4.webapp.bizservice.customer.CompanyCustomerService;
import com.lwxf.industry4.webapp.domain.entity.customer.CompanyCustomer;
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
import com.lwxf.industry4.webapp.facade.wxapi.dealer.company.BWxCustomerFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/6/20 0020 9:58
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Api(value="CompanyMessageFacade",tags={"B端微信小程序接口:客户管理"})
@RestController
@RequestMapping(value = "/wxapi/b/companyCustomers", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class BWxCompanyCustomerController {
	@Resource(name = "bWxCustomerFacade")
	private BWxCustomerFacade bWxCustomerFacade;
	@Resource(name = "companyCustomerService")
	private CompanyCustomerService companyCustomerService;

	@GetMapping
	@ApiOperation(value = "查询客户列表",notes = "查询客户列表接口",response = WxCustomerDto.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "pageNum",value = "页码",dataType = "integer",paramType = "query"),
			@ApiImplicitParam(name = "pageSize",value = "页数",dataType = "integer",paramType = "query"),
			@ApiImplicitParam(name = "condation",value = "客户姓名/电话",dataType = "string",paramType = "query"),
			@ApiImplicitParam(name = "startTime",value = "起始时间：例（2019-06-01） ",dataType = "string",paramType = "query"),
			@ApiImplicitParam(name = "endTime",value = "结束时间：例（2019-06-01）",dataType = "string",paramType = "query"),
			@ApiImplicitParam(name = "type0",value = "首页入口：客户类型 1-意向客户 2-成交客户 3-执行客户",dataType = "String",paramType = "query",required = true),
			@ApiImplicitParam(name = "type1",value = "列表内：客户类型 1-意向客户 2-成交客户 3-执行客户",dataType = "String",paramType = "query")
	})
	public String findWxBCustomers( @RequestParam(required = false) Integer pageNum,
									@RequestParam(required = false) Integer pageSize,
									@RequestParam(required = false) String condation,
									@RequestParam(required = false) String startTime,
									@RequestParam(required = false) String endTime,
									@RequestParam String type0,
									@RequestParam(required = false) String type1){
		JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
		String atoken= WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
		if(uid==null){
			return jsonMapper.toJson(ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
		}
		String cid=mapInfo.get("companyId")==null?null:mapInfo.get("companyId").toString();
		if(cid==null){
			return jsonMapper.toJson(ResultFactory.generateErrorResult(ErrorCodes.BIZ_IS_NOT_ORG_MEMBER_10004, AppBeanInjector.i18nUtil.getMessage("BIZ_IS_NOT_ORG_MEMBER_10004")));
		}
		MapContext mapContext=MapContext.newOne();
		mapContext.put("companyId",cid);
		if(LwxfStringUtils.isNotBlank(condation)){
			mapContext.put("condation",condation);
		}
		if(LwxfStringUtils.isNotBlank(startTime)){
			mapContext.put("startTime",startTime);
		}
		if(LwxfStringUtils.isNotBlank(endTime)){
			mapContext.put("endTime",endTime);
		}
		if(LwxfStringUtils.isNotBlank(type1)){
			mapContext.put("type",type1);
		}else {
			mapContext.put("type",type0);
		}
		RequestResult result=this.bWxCustomerFacade.findWxBCustomers(pageNum,pageSize,mapContext);
		return jsonMapper.toJson(result);
	}

	/**
	 * 查询客户详情
	 * @param customerId
	 * @return
	 */
	@GetMapping("/{customerId}")
	@ApiOperation(value = "查询客户详情",notes = "查询客户详情接口",response = WxCustomerInfoDto.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "customerId",value = "客户id",required = true,dataType = "string",paramType = "path")
	})
	public String findWxCustomerInfo(@PathVariable String customerId) {
		JsonMapper jsonMapper = JsonMapper.createAllToStringMapper();
		String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);

		String uid = mapInfo == null ? null : mapInfo.get("userId").toString();
		if (uid == null) {
			return jsonMapper.toJson(ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
		}

		String cid = mapInfo == null ? null : mapInfo.get("companyId").toString();
		if (cid == null) {
			return jsonMapper.toJson(ResultFactory.generateErrorResult(ErrorCodes.BIZ_IS_NOT_ORG_MEMBER_10004, AppBeanInjector.i18nUtil.getMessage("BIZ_IS_NOT_ORG_MEMBER_10004")));
		}
		return jsonMapper.toJson(this.bWxCustomerFacade.findWxCustomerInfo(customerId,cid));
	}
	/**
	 * 添加客户
	 * @param mapContext
	 * @return
	 */
	@PostMapping
	@ApiOperation(value = "添加客户",notes = "添加客户接口")
	public RequestResult addWxCustomers(@RequestBody MapContext mapContext){
		String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String uid = mapInfo.get("userId") == null ? null:mapInfo.get("userId").toString();
		if (uid == null) {
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
		}
		String cid = mapInfo.get("companyId") == null ? null:mapInfo.get("companyId").toString();
		if (cid == null) {
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_IS_NOT_ORG_MEMBER_10004, AppBeanInjector.i18nUtil.getMessage("BIZ_IS_NOT_ORG_MEMBER_10004"));
		}
		String branchid = mapInfo.get("branchId") == null ? null:mapInfo.get("branchId").toString();
		if (branchid == null) {
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_IS_NOT_ORG_MEMBER_10004, AppBeanInjector.i18nUtil.getMessage("BIZ_IS_NOT_ORG_MEMBER_10004"));
		}

		return this.bWxCustomerFacade.addWxCustomers(mapContext,cid,uid,branchid);

	}
	/**
	 * 添加客户
	 * @param mapContext
	 * @return
	 */
	@PostMapping("/addWxCustomers2")
	@ApiOperation(value = "添加客户",notes = "添加客户接口")
	public RequestResult addWxCustomers2(@RequestBody MapContext mapContext){
		String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String uid = mapInfo.get("userId") == null ? null:mapInfo.get("userId").toString();
		if (uid == null) {
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
		}
		String cid = mapInfo.get("companyId") == null ? null:mapInfo.get("companyId").toString();
		if (cid == null) {
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_IS_NOT_ORG_MEMBER_10004, AppBeanInjector.i18nUtil.getMessage("BIZ_IS_NOT_ORG_MEMBER_10004"));
		}
		String branchid = mapInfo.get("branchId") == null ? null:mapInfo.get("branchId").toString();
		if (branchid == null) {
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_IS_NOT_ORG_MEMBER_10004, AppBeanInjector.i18nUtil.getMessage("BIZ_IS_NOT_ORG_MEMBER_10004"));
		}
		String mobile = mapContext.getTypedValue("mobile", String.class);
		CompanyCustomer customer = this.companyCustomerService.findByPhoneAndBranchId(mobile, cid);
		if(customer!=null){
			return ResultFactory.generateRequestResult(customer);
		}
		return this.bWxCustomerFacade.addWxCustomers(mapContext,cid,uid,branchid);

	}
	/**
	 * 修改客户信息
	 */
	@PutMapping("/{customerId}")
	@ApiOperation(value = "修改客户信息",notes = "修改客户信息")
	public RequestResult updateCustomer(@PathVariable String customerId,
										@RequestBody MapContext mapContext){
		String atoken= WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String uid = mapInfo.get("userId") == null ? null:mapInfo.get("userId").toString();
		if (uid == null) {
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
		}
		String cid = mapInfo.get("companyId") == null ? null:mapInfo.get("companyId").toString();
		if (cid == null) {
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_IS_NOT_ORG_MEMBER_10004, AppBeanInjector.i18nUtil.getMessage("BIZ_IS_NOT_ORG_MEMBER_10004"));
		}
		mapContext.put("companyId", cid);
		String branchid = mapInfo.get("branchId") == null ? null:mapInfo.get("branchId").toString();
		if (branchid == null){
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_IS_NOT_ORG_MEMBER_10004, AppBeanInjector.i18nUtil.getMessage("BIZ_IS_NOT_ORG_MEMBER_10004"));
		}
		return this.bWxCustomerFacade.updateWxCustomers(mapContext,customerId);
	}

	@DeleteMapping("/{customerId}")
	@ApiOperation(value = "删除客户信息",notes = "删除客户信息")
	public RequestResult deleteCustomer(@PathVariable String customerId) {
		String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);

		if (mapInfo == null) {
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
		}

		String cid = mapInfo.get("companyId") == null ? null:mapInfo.get("companyId").toString();
		if (cid == null) {
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_IS_NOT_ORG_MEMBER_10004, AppBeanInjector.i18nUtil.getMessage("BIZ_IS_NOT_ORG_MEMBER_10004"));
		}

		return this.bWxCustomerFacade.deleteWxCustomer(customerId, cid);

	}
}
