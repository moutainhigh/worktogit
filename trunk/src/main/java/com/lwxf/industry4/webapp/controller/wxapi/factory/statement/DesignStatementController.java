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
import com.lwxf.industry4.webapp.facade.wxapi.factory.statements.DesignStatementFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：设计报表
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/7/3 0003 9:22
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Api(value="wxDesignStatementController",tags={"F端微信小程序接口:设计报表接口"})
@RestController("wxDesignStatementController")
@RequestMapping(value = "/wxapi/f/",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class DesignStatementController {
	@Resource(name = "wxDesignStatementFacade")
	private DesignStatementFacade designStatementFacade;

	/**
	 * 本周设计报表
	 * @param countType
	 * @param request
	 * @return
	 */
	@ApiOperation(value="本周设计报表",notes="本周设计报表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "countType",value = "要查看的类型，新增统计传“0”，完成统计传“1”",dataType = "string",paramType = "path",required = true)
	})
	@GetMapping("/designStatements/week/{countType}")
	private String weekDesignStatements(@PathVariable String countType,
										   HttpServletRequest request){
		JsonMapper jsonMapper=new JsonMapper();
		String atoken=request.getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
		String branchId =mapInfo.get("branchId")==null?null:mapInfo.get("branchId").toString();
		if(uid==null){
			return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
		}

		RequestResult result=this.designStatementFacade.weekDesignStatements(branchId,countType);
		return jsonMapper.toJson(result);
	}
	@ApiOperation(value="本月设计报表接口",notes="本月设计报表接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "countType",value = "要查看的类型，新增统计传“0”，完成统计传“1”",dataType = "string",paramType = "path",required = true)
	})
	@GetMapping("/designStatements/month/{countType}")
	private String monthDesignStatements(@PathVariable String countType,
										 HttpServletRequest request){
		JsonMapper jsonMapper=new JsonMapper();
		String atoken=request.getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
		String branchId =mapInfo.get("branchId")==null?null:mapInfo.get("branchId").toString();
		if(uid==null){
			return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
		}
		RequestResult result=this.designStatementFacade.monthDesignStatements(branchId,countType);
		return jsonMapper.toJson(result);
	}
	@ApiOperation(value="本季设计报表接口",notes="本季设计报表接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "countType",value = "要查看的类型，新增统计传“0”，完成统计传“1”",dataType = "string",paramType = "path",required = true)
	})
	@GetMapping("/designStatements/quarter/{countType}")
	private String quarterDesignStatements(@PathVariable String countType,
										   HttpServletRequest request){
		JsonMapper jsonMapper=new JsonMapper();
		String atoken=request.getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
		String branchId =mapInfo.get("branchId")==null?null:mapInfo.get("branchId").toString();
		if(uid==null){
			return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
		}
		RequestResult result=this.designStatementFacade.quarterDesignStatements(branchId,countType);
		return jsonMapper.toJson(result);
	}
	@ApiOperation(value="本年设计报表接口",notes="本年设计报表接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "countType",value = "要查看的类型，新增统计传“0”，完成统计传“1”",dataType = "string",paramType = "path",required = true)
	})
	@GetMapping("/designStatements/year/{countType}")
	private String yearDesignStatements(@PathVariable String countType,
										HttpServletRequest request){
		JsonMapper jsonMapper=new JsonMapper();
		String atoken=request.getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
		String branchId =mapInfo.get("branchId")==null?null:mapInfo.get("branchId").toString();
		if(uid==null){
			return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
		}
		RequestResult result=this.designStatementFacade.yearDesignStatements(branchId,countType);
		return jsonMapper.toJson(result);
	}
	@ApiOperation(value="条件搜索设计报表接口",notes="条件搜索设计报表接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "countType",value = "要查看的类型，新增统计传“0”，完成统计传“1”",dataType = "string",paramType = "path",required = true),
			@ApiImplicitParam(name = "dateType",value = "要查看的时间类型 0-按日 1-按月 2-按年",dataType = "String",paramType = "query"),
			@ApiImplicitParam(name = "startTime",value = "开始时间",dataType = "string",paramType = "query"),
			@ApiImplicitParam(name = "endTime",value = "结束时间",dataType = "string",paramType = "query")
	})
	@GetMapping("/designStatements/{countType}")
	private String designStatements(@PathVariable String countType,
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
		RequestResult result=this.designStatementFacade.designStatements(branchId,countType,mapContext);
		return jsonMapper.toJson(result);
	}
}
