package com.lwxf.industry4.webapp.controller.admin.factory.statement;

import com.lwxf.commons.json.JsonMapper;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.statement.AftersaleStatementFacade;
import com.lwxf.mybatis.utils.MapContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/6/30 0030 10:45
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Api(value="AftersaleStatementController",tags={"F端后台管理接口:售后报表接口"})
@RestController("AftersaleStatementController")
@RequestMapping(value = "/api/f/",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class AftersaleStatementController {
    @Resource(name = "AftersaleStatementFacade")
	private AftersaleStatementFacade aftersaleStatementFacade;

	@ApiOperation(value="条件搜索售后报表接口",notes="条件搜索售后报表接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "countType",value = "要查看的类型，新增统计传“0”，处理统计传“1”，交付-2",dataType = "string",paramType = "path",required = true),
			@ApiImplicitParam(name = "startTime",value = "开始时间",dataType = "string",paramType = "query"),
			@ApiImplicitParam(name = "endTime",value = "结束时间",dataType = "string",paramType = "query")
	})
	@GetMapping("/aftersaleStatements/{countType}")
	private String AftersaleStatements(@PathVariable String countType,
									   @RequestParam(required = false) Integer dateType,
									   @RequestParam(required = false) String startTime,
									   @RequestParam(required = false) String endTime){
		JsonMapper jsonMapper=new JsonMapper();
		String uid = WebUtils.getCurrUserId();
		String branchId =WebUtils.getCurrBranchId();
		if(uid==null){
			return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		MapContext mapContext=MapContext.newOne();
		mapContext.put("countType",countType);
		mapContext.put("branchId",branchId);
		RequestResult result= null;
		try {
			result = this.aftersaleStatementFacade.aftersaleStatements(sdf.parse(startTime), sdf.parse(endTime), mapContext,dateType);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return jsonMapper.toJson(result);
	}

	/**
	 * 本周售后报表
	 * @param countType
	 * @return
	 */
	@ApiOperation(value="本周售后报表",notes="本周售后报表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "countType",value = "要查看的类型，新增统计传“0”，处理统计传“1”，交付-2",dataType = "string",paramType = "path",required = true)
	})
	@GetMapping("/aftersaleStatements/week/{countType}")
    private String weekAftersaleStatements(@PathVariable String countType){
		JsonMapper jsonMapper=new JsonMapper();
		String uid =WebUtils.getCurrUserId();
		String branchId =WebUtils.getCurrBranchId();
		if(uid==null){
			return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
		}

		RequestResult result=this.aftersaleStatementFacade.weekAftersaleStatements(branchId,countType);
		return jsonMapper.toJson(result);
	}
	@ApiOperation(value="本月售后报表接口",notes="本月售后报表接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "countType",value = "要查看的类型，新增统计传“0”，处理统计传“1”，交付-2",dataType = "string",paramType = "path",required = true)
	})
	@GetMapping("/aftersaleStatements/month/{countType}")
	private String monthAftersaleStatements(@PathVariable String countType){
		JsonMapper jsonMapper=new JsonMapper();
		String uid =WebUtils.getCurrUserId();
		String branchId =WebUtils.getCurrBranchId();
		if(uid==null){
			return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
		}
		RequestResult result=this.aftersaleStatementFacade.monthAftersaleStatements(branchId,countType);
		return jsonMapper.toJson(result);
	}
	@ApiOperation(value="本季售后报表接口",notes="本季售后报表接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "countType",value = "要查看的类型，新增统计传“0”，处理统计传“1”，交付-2",dataType = "string",paramType = "path",required = true)
	})
	@GetMapping("/aftersaleStatements/quarter/{countType}")
	private String quarterAftersaleStatements(@PathVariable String countType){
		JsonMapper jsonMapper=new JsonMapper();
		String uid =WebUtils.getCurrUserId();
		String branchId =WebUtils.getCurrBranchId();
		if(uid==null){
			return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
		}
		RequestResult result=this.aftersaleStatementFacade.quarterAftersaleStatements(branchId,countType);
		return jsonMapper.toJson(result);
	}
	@ApiOperation(value="本年售后报表接口",notes="本年售后报表接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "countType",value = "要查看的类型，新增统计传“0”，处理统计传“1”，交付-2",dataType = "string",paramType = "path",required = true)
	})
	@GetMapping("/aftersaleStatements/year/{countType}")
	private String yearAftersaleStatements(@PathVariable String countType){
		JsonMapper jsonMapper=new JsonMapper();
		String uid = WebUtils.getCurrUserId();
		String branchId =WebUtils.getCurrBranchId();
		if(uid==null){
			return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
		}
		RequestResult result=this.aftersaleStatementFacade.yearAftersaleStatements(branchId,countType);
		return jsonMapper.toJson(result);
	}



}
