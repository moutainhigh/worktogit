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
import com.lwxf.industry4.webapp.facade.wxapi.dealer.bstatement.BOrderStatementFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/7/4 0004 10:02
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Api(value="BorderStatementController",tags={"B端微信小程序接口:订单报表接口"})
@RestController("BorderStatementController")
@RequestMapping(value = "/wxapi/b/",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class BorderStatementController {
     @Resource(name = "bOrderStatementFacade")
	private BOrderStatementFacade bOrderStatementFacade;

	@ApiOperation(value="本周订单报表接口",notes="本周订单报表接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "countType",value = "要查看的类型，下单统计传“0”，超期统计传“1”，完成-2",dataType = "string",paramType = "path",required = true)
	})
	@GetMapping("/orderStatements/week/{countType}")
	private String weekOrderStatements(@PathVariable String countType,
											HttpServletRequest request){
		JsonMapper jsonMapper=new JsonMapper();
		String atoken=request.getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
		String companyId =mapInfo.get("companyId")==null?null:mapInfo.get("companyId").toString();
		if(uid==null){
			return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
		}
		RequestResult result=this.bOrderStatementFacade.weekOrderStatements(companyId,countType);
		return jsonMapper.toJson(result);
	}


	@ApiOperation(value="本月订单报表接口",notes="本月订单报表接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "countType",value = "要查看的类型，新增统计传“0”，超期统计传“1”，完成-2",dataType = "string",paramType = "path",required = true)
	})
	@GetMapping("/orderStatements/month/{countType}")
	private String monthOrderStatements(@PathVariable String countType,
											HttpServletRequest request){
		JsonMapper jsonMapper=new JsonMapper();
		String atoken=request.getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
		String companyId =mapInfo.get("companyId")==null?null:mapInfo.get("companyId").toString();
		if(uid==null){
			return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
		}
		RequestResult result=this.bOrderStatementFacade.monthOrderStatements(companyId,countType);
		return jsonMapper.toJson(result);
	}

	@ApiOperation(value="本季订单报表接口",notes="本季订单报表接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "countType",value = "要查看的类型，新增统计传“0”，超期统计传“1”，完成-2",dataType = "string",paramType = "path",required = true)
	})
	@GetMapping("/orderStatements/quarter/{countType}")
	private String quarterOrderStatements(@PathVariable String countType,
											  HttpServletRequest request){
		JsonMapper jsonMapper=new JsonMapper();
		String atoken=request.getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
		String companyId =mapInfo.get("companyId")==null?null:mapInfo.get("companyId").toString();
		if(uid==null){
			return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
		}
		RequestResult result=this.bOrderStatementFacade.quarterOrderStatements(companyId,countType);
		return jsonMapper.toJson(result);
	}
	@ApiOperation(value="本季订单报表接口",notes="本季订单报表接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "countType",value = "要查看的类型，新增统计传“0”，超期统计传“1”，完成-2",dataType = "string",paramType = "path",required = true)
	})
	@GetMapping("/orderStatements/year/{countType}")
	private String yearOrderStatements(@PathVariable String countType,
										   HttpServletRequest request){
		JsonMapper jsonMapper=new JsonMapper();
		String atoken=request.getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
		String companyId =mapInfo.get("companyId")==null?null:mapInfo.get("companyId").toString();
		if(uid==null){
			return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
		}
		RequestResult result=this.bOrderStatementFacade.yearOrderStatements(companyId,countType);
		return jsonMapper.toJson(result);
	}
	@ApiOperation(value="条件搜索订单报表接口",notes="条件搜索订单报表接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "countType",value = "要查看的类型，新增统计传“0”，超期统计传“1”，完成-2",dataType = "string",paramType = "path",required = true),
			@ApiImplicitParam(name = "startTime",value = "开始时间",dataType = "string",paramType = "query"),
			@ApiImplicitParam(name = "endTime",value = "结束时间",dataType = "string",paramType = "query")
	})
	@GetMapping("/orderStatements/{countType}")
	private String OrderStatements(@PathVariable String countType,
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
		RequestResult result=this.bOrderStatementFacade.orderStatements(companyId,countType,mapContext);
		return jsonMapper.toJson(result);
	}

}
