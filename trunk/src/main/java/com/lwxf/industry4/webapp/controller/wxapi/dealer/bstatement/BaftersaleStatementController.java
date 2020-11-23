package com.lwxf.industry4.webapp.controller.wxapi.dealer.bstatement;

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
import com.lwxf.industry4.webapp.facade.wxapi.dealer.bstatement.BaftersaleStatementFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/7/8 0008 10:13
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Api(value="BaftersaleStatementController",tags={"B端微信小程序接口:售后报表接口"})
@RestController("BaftersaleStatementController")
@RequestMapping(value = "/wxapi/b/",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class BaftersaleStatementController {

	@Resource(name = "baftersaleStatementFacade")
	private BaftersaleStatementFacade baftersaleStatementFacade;

	@ApiOperation(value="条件搜索售后报表接口",notes="条件搜索售后报表接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "countType",value = "要查看的类型，新增统计传“0”，完成统计传“1”",dataType = "string",paramType = "path",required = true),
			@ApiImplicitParam(name = "startTime",value = "开始时间",dataType = "string",paramType = "query"),
			@ApiImplicitParam(name = "endTime",value = "结束时间",dataType = "string",paramType = "query")
	})
	@GetMapping("/aftersaleStatements/{countType}")
	private String AftersaleStatements(@PathVariable String countType,
									   @RequestParam(required = false) String startTime,
									   @RequestParam(required = false) String endTime,
									   HttpServletRequest request){
		JsonMapper jsonMapper=new JsonMapper();
		String atoken=request.getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
		String companyId =mapInfo.get("companyId")==null?null:mapInfo.get("companyId").toString();
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
		RequestResult result=this.baftersaleStatementFacade.aftersaleStatements(companyId,countType,mapContext);
		return jsonMapper.toJson(result);
	}

	/**
	 * 本周售后报表
	 * @param countType
	 * @param request
	 * @return
	 */
	@ApiOperation(value="本周售后报表",notes="本周售后报表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "countType",value = "要查看的类型，新增统计传“0”，完成统计传“1”",dataType = "string",paramType = "path",required = true)
	})
	@GetMapping("/aftersaleStatements/week/{countType}")
	private String weekAftersaleStatements(@PathVariable String countType,
										   HttpServletRequest request){
		JsonMapper jsonMapper=new JsonMapper();
		String atoken=request.getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
		String companyId =mapInfo.get("companyId")==null?null:mapInfo.get("companyId").toString();
		if(uid==null){
			return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
		}

		RequestResult result=this.baftersaleStatementFacade.weekAftersaleStatements(companyId,countType);
		return jsonMapper.toJson(result);
	}
	@ApiOperation(value="本月售后报表接口",notes="本月售后报表接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "countType",value = "要查看的类型，新增统计传“0”，完成统计传“1”",dataType = "string",paramType = "path",required = true)
	})
	@GetMapping("/aftersaleStatements/month/{countType}")
	private String monthAftersaleStatements(@PathVariable String countType,
											HttpServletRequest request){
		JsonMapper jsonMapper=new JsonMapper();
		String atoken=request.getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
		String companyId =mapInfo.get("companyId")==null?null:mapInfo.get("companyId").toString();
		if(uid==null){
			return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
		}
		RequestResult result=this.baftersaleStatementFacade.monthAftersaleStatements(companyId,countType);
		return jsonMapper.toJson(result);
	}
	@ApiOperation(value="本季售后报表接口",notes="本季售后报表接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "countType",value = "要查看的类型，新增统计传“0”，完成统计传“1”",dataType = "string",paramType = "path",required = true)
	})
	@GetMapping("/aftersaleStatements/quarter/{countType}")
	private String quarterAftersaleStatements(@PathVariable String countType,
											  HttpServletRequest request){
		JsonMapper jsonMapper=new JsonMapper();
		String atoken=request.getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
		String companyId =mapInfo.get("companyId")==null?null:mapInfo.get("companyId").toString();
		if(uid==null){
			return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
		}
		RequestResult result=this.baftersaleStatementFacade.quarterAftersaleStatements(companyId,countType);
		return jsonMapper.toJson(result);
	}
	@ApiOperation(value="本年售后报表接口",notes="本年售后报表接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "countType",value = "要查看的类型，新增统计传“0”，完成统计传“1”",dataType = "string",paramType = "path",required = true)
	})
	@GetMapping("/aftersaleStatements/year/{countType}")
	private String yearAftersaleStatements(@PathVariable String countType,
										   HttpServletRequest request){
		JsonMapper jsonMapper=new JsonMapper();
		String atoken=request.getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
		String companyId =mapInfo.get("companyId")==null?null:mapInfo.get("companyId").toString();
		if(uid==null){
			return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
		}
		RequestResult result=this.baftersaleStatementFacade.yearAftersaleStatements(companyId,countType);
		return jsonMapper.toJson(result);
	}


}
