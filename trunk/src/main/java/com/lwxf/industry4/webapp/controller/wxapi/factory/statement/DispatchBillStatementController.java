package com.lwxf.industry4.webapp.controller.wxapi.factory.statement;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.*;

import com.lwxf.commons.json.JsonMapper;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.LoginUtil;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.wxapi.factory.statements.DispatchBillStatementFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/7/2 0002 9:44
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Api(value="wxDispatchBillStatementController",tags={"F端微信小程序接口:发货报表接口"})
@RestController("wxDispatchBillStatementController")
@RequestMapping(value = "/wxapi/f/",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class DispatchBillStatementController {
	@Resource(name = "dispatchBillStatementFacade")
	private DispatchBillStatementFacade dispatchBillStatementFacade;

	@ApiOperation(value="本周发货报表",notes="本周发货报表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "countType",value = "要查看的类型，包装统计传“0”，发货单统计传“1”",dataType = "string",paramType = "path",required = true)
	})
	@GetMapping("/dispatchBillStatements/week/{countType}")
	private String weekDealerStatements(@PathVariable String countType,
										HttpServletRequest request){
		JsonMapper jsonMapper=new JsonMapper();
		String atoken=request.getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
		String branchId =mapInfo.get("branchId")==null?null:mapInfo.get("branchId").toString();
		if(uid==null){
			return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
		}

		RequestResult result=this.dispatchBillStatementFacade.weekDispatchBillStatements(branchId,countType);
		return jsonMapper.toJson(result);
	}
	@ApiOperation(value="本月发货报表接口",notes="本月发货报表接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "countType",value = "要查看的类型，包装统计传“0”，发货单统计传“1”",dataType = "string",paramType = "path",required = true)
	})
	@GetMapping("/dispatchBillStatements/month/{countType}")
	private String monthDispatchBillStatements(@PathVariable String countType,
											HttpServletRequest request){
		JsonMapper jsonMapper=new JsonMapper();
		String atoken=request.getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
		String branchId =mapInfo.get("branchId")==null?null:mapInfo.get("branchId").toString();
		if(uid==null){
			return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
		}
		RequestResult result=this.dispatchBillStatementFacade.monthDispatchBilleStatements(branchId,countType);
		return jsonMapper.toJson(result);
	}
	@ApiOperation(value="本季发货报表接口",notes="本季发货报表接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "countType",value = "要查看的类型，包装统计传“0”，发货单统计传“1”",dataType = "string",paramType = "path",required = true)
	})
	@GetMapping("/dispatchBillStatements/quarter/{countType}")
	private String quarterDispatchBillStatements(@PathVariable String countType,
											  HttpServletRequest request){
		JsonMapper jsonMapper=new JsonMapper();
		String atoken=request.getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
		String branchId =mapInfo.get("branchId")==null?null:mapInfo.get("branchId").toString();
		if(uid==null){
			return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
		}
		RequestResult result=this.dispatchBillStatementFacade.quarterDispatchBillStatements(branchId,countType);
		return jsonMapper.toJson(result);
	}
	@ApiOperation(value="本年发货报表接口",notes="本年发货报表接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "countType",value = "要查看的类型，包装统计传“0”，发货单统计传“1”",dataType = "string",paramType = "path",required = true)
	})
	@GetMapping("/dispatchBillStatements/year/{countType}")
	private String yearDispatchBillStatements(@PathVariable String countType,
										   HttpServletRequest request){
		JsonMapper jsonMapper=new JsonMapper();
		String atoken=request.getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
		String branchId =mapInfo.get("branchId")==null?null:mapInfo.get("branchId").toString();
		if(uid==null){
			return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
		}
		RequestResult result=this.dispatchBillStatementFacade.yearDispatchBillStatements(branchId,countType);
		return jsonMapper.toJson(result);
	}
	@ApiOperation(value="条件搜索发货报表接口",notes="条件搜索发货报表接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "countType",value = "要查看的类型，包装统计传“0”，发货单统计传“1”",dataType = "string",paramType = "path",required = true),
			@ApiImplicitParam(name = "dateType",value = "要查看的时间类型 0-按日 1-按月 2-按年",dataType = "String",paramType = "query"),
			@ApiImplicitParam(name = "startTime",value = "开始时间",dataType = "string",paramType = "query"),
			@ApiImplicitParam(name = "endTime",value = "结束时间",dataType = "string",paramType = "query")
	})
	@GetMapping("/dispatchBillStatements/{countType}")
	private String dispatchBillStatements(@PathVariable String countType,
										  @RequestParam(required = false) String dateType,
									   @RequestParam(required = false) String startTime,
									   @RequestParam(required = false) String endTime,
									   HttpServletRequest request){
		JsonMapper jsonMapper=new JsonMapper();
		String atoken=request.getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
		String branchId =mapInfo.get("branchId")==null?null:mapInfo.get("branchId").toString();
		if(uid==null){
			return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
		}
		MapContext mapContext=MapContext.newOne();
		if(startTime!=null&&!startTime.equals("")){
			mapContext.put("startTime",startTime);
		}
		if(endTime!=null&&!endTime.equals("")){
			mapContext.put("endTime",endTime);
		}
		if(dateType!=null&&!dateType.equals("")){
			mapContext.put("dateType",dateType);
		}else {
			mapContext.put("dateType","0");
		}
		RequestResult result=this.dispatchBillStatementFacade.dispatchBillStatements(branchId,countType,mapContext);
		return jsonMapper.toJson(result);
	}
}

