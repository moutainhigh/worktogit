package com.lwxf.industry4.webapp.controller.admin.factory.statement;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.common.utils.DateUtilsExt;
import com.lwxf.industry4.webapp.facade.admin.factory.statement.ProduceStatementFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.lwxf.commons.json.JsonMapper;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/7/3 0003 17:11
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Api(value="pcProduceStatementController",tags={"pc端后台接口:生产报表接口"})
@RestController("pcProduceStatementController")
@RequestMapping(value = "/api/f/",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class ProduceStatementController {
	@Resource(name = "pcProduceStatementFacade")
	private ProduceStatementFacade produceStatementFacade;

	@ApiOperation(value="本周生产报表",notes="本周生产报表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "countType",value = "要查看的类型，新增统计传“0”，排产单统计传“1”，完成-2，延期-3",dataType = "string",paramType = "path",required = true)
	})
	@GetMapping("/produceStatements/week/{countType}")
	private String weekProduceStatements(@PathVariable String countType
											 ){
		JsonMapper jsonMapper=new JsonMapper();
		String uid = WebUtils.getCurrUserId();
		String branchId =WebUtils.getCurrBranchId();
		if(uid==null){
			return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
		}

		RequestResult result=this.produceStatementFacade.weekProduceStatements(branchId,countType);
		return jsonMapper.toJson(result);
	}
	@ApiOperation(value="本月入库报表接口",notes="本月入库报表接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "countType",value = "要查看的类型，新增统计传“0”，排产单统计传“1”，完成-2",dataType = "string",paramType = "path",required = true)
	})
	@GetMapping("/produceStatements/month/{countType}")
	private String monthProduceStatements(@PathVariable String countType
												){
		JsonMapper jsonMapper=new JsonMapper();
		String uid = WebUtils.getCurrUserId();
		String branchId =WebUtils.getCurrBranchId();
		if(uid==null){
			return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
		}
		RequestResult result=this.produceStatementFacade.monthProduceStatements(branchId,countType);
		return jsonMapper.toJson(result);
	}
	@ApiOperation(value="本季入库报表接口",notes="本季入库报表接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "countType",value = "要查看的类型，新增统计传“0”，排产单统计传“1”，完成-2",dataType = "string",paramType = "path",required = true)
	})
	@GetMapping("/produceStatements/quarter/{countType}")
	private String quarterProduceStatements(@PathVariable String countType
												){
		JsonMapper jsonMapper=new JsonMapper();
		String uid = WebUtils.getCurrUserId();
		String branchId =WebUtils.getCurrBranchId();
		if(uid==null){
			return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
		}
		RequestResult result=this.produceStatementFacade.quarterProduceStatements(branchId,countType);
		return jsonMapper.toJson(result);
	}
	@ApiOperation(value="本年入库报表接口",notes="本年入库报表接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "countType",value = "要查看的类型，新增统计传“0”，排产单统计传“1”，完成-2",dataType = "string",paramType = "path",required = true)
	})
	@GetMapping("/produceStatements/year/{countType}")
	private String yearProduceStatements(@PathVariable String countType
											 ){
		JsonMapper jsonMapper=new JsonMapper();
		String uid = WebUtils.getCurrUserId();
		String branchId =WebUtils.getCurrBranchId();
		if(uid==null){
			return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
		}
		RequestResult result=this.produceStatementFacade.yearProduceStatements(branchId,countType);
		return jsonMapper.toJson(result);
	}
	@ApiOperation(value="条件搜索发货报表接口",notes="条件搜索发货报表接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "countType",value = "要查看的类型，新增统计传“0”，排产单统计传“1”，完成-2",dataType = "string",paramType = "path",required = true),
			@ApiImplicitParam(name = "dateType",value = "要查看的时间类型 0-按日 1-按月 2-按年",dataType = "String",paramType = "query"),
			@ApiImplicitParam(name = "startTime",value = "开始时间",dataType = "string",paramType = "query"),
			@ApiImplicitParam(name = "endTime",value = "结束时间",dataType = "string",paramType = "query")
	})
	@GetMapping("/produceStatements/{countType}")
	private String prodecuStatements(@PathVariable String countType,
									 @RequestParam(required = false) String dateType,
										 @RequestParam(required = false) String startTime,
										 @RequestParam(required = false) String endTime
										 ){
		JsonMapper jsonMapper=new JsonMapper();
		String uid = WebUtils.getCurrUserId();
		String branchId =WebUtils.getCurrBranchId();
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
		RequestResult result=this.produceStatementFacade.produceStatements(branchId,countType,mapContext);
		return jsonMapper.toJson(result);
	}

    /**
     * 订单生产状态统计，以产品为单位
     * @return
     */
    @ApiOperation(value="订单生产状态统计",notes="订单生产状态统计")
    @GetMapping("/produceStatements/orderProduceCount")
    public RequestResult orderProduceCount() {
        MapContext mapContext = MapContext.newOne();
        String branchId = WebUtils.getCurrBranchId();
        mapContext.put("branchId", branchId);
        return this.produceStatementFacade.orderProduceCount(mapContext);
    }

    /**
     * 生产产品类型统计
     * @return
     */
    @ApiOperation(value="生产产品类型统计",notes="生产产品类型统计")
    @GetMapping("/produceStatements/orderProduceTypeCount")
    public RequestResult orderProduceTypeCount() {
        MapContext mapContext = MapContext.newOne();
        String branchId = WebUtils.getCurrBranchId();
        mapContext.put("branchId", branchId);
        return this.produceStatementFacade.orderProduceTypeCount(mapContext);
    }

    @ApiOperation(value="逾期订单趋势",notes="逾期订单趋势")
    @GetMapping("/produceStatements/orderProduceDelayCount")
    public RequestResult orderProduceDelayCount(@RequestParam(value = "date", required = false, defaultValue = "") String date) {
        if (date == null || "".equals(date)) {
            date = DateUtil.dateTimeToString(DateUtil.getSystemDate(), "yyyy-MM");
        }
        String branchId = WebUtils.getCurrBranchId();
        return this.produceStatementFacade.orderProduceDelayCount(branchId, date);
    }
}
