package com.lwxf.industry4.webapp.controller.admin.factory.statement;


import com.lwxf.commons.json.JsonMapper;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.DateUtilsExt;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.statement.CustomOrderStatementFacade;
import com.lwxf.mybatis.utils.DateUtil;
import com.lwxf.mybatis.utils.MapContext;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 * 功能：
 *
 * @author：zhangxiaolin(17838625030@163.com)
 * @create：2019/6/30 0030 9:28
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Api(value = "CustomOrderStatementController", tags = {"F端后台管理接口:订单报表接口"})
@RestController("CustomOrderStatementController")
@RequestMapping(value = "/api/f/", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class CustomOrderStatementController {

	@Resource(name = "customOrderStatementFacade")
	private CustomOrderStatementFacade customOrderStatementFacade;

	//    /**
//     * 业务报表
//     * @param startTime
//     * @param endTime
//     * @param cityId
//     * @param salesmanId
//     * @return
//     */
//    @ApiOperation(value="订单报表条件搜索接口",notes="订单报表条件搜索接口")//无用
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "startTime",value = "开始时间",dataType = "string",paramType = "query"),
//            @ApiImplicitParam(name = "endTime",value = "结束时间",dataType = "string",paramType = "query"),
//            @ApiImplicitParam(name = "cityId",value = "区域id",dataType = "string",paramType = "query"),
//            @ApiImplicitParam(name = "salesmanId",value = "业务经理id",dataType = "string",paramType = "query"),
//            @ApiImplicitParam(name = "countType",value = "要查看的类型",dataType = "string",paramType = "query")
//    })
//    @GetMapping("/customOrderStatements")
//    private String customOrderStatements(
//                                    @RequestParam(required = false) String startTime,
//                                    @RequestParam(required = false) String endTime,
//                                    @RequestParam(required = false) String cityId,
//                                    @RequestParam(required = false) Integer dateType,
//                                    @RequestParam(required = false) String salesmanId,
//                                    @RequestParam(required = false) String countType){
//        JsonMapper jsonMapper=new JsonMapper();
//        String uid = WebUtils.getCurrUserId();
//        String branchId =WebUtils.getCurrBranchId();
//        if(uid==null){
//            return jsonMapper.toJson(ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
//        }
//        MapContext mapContext=MapContext.newOne();
//        if(startTime!=null&&!startTime.equals("")){
//            mapContext.put("startTime",startTime);
//        }
//        if(endTime!=null&&!endTime.equals("")){
//            mapContext.put("endTime",endTime);
//        }
//        if(cityId!=null&&!cityId.equals("")){
//            mapContext.put("cityId",cityId);
//        }
//        if(salesmanId!=null&&!salesmanId.equals("")){
//            mapContext.put("salesmanId",salesmanId);
//        }
//        if(countType!=null&&!countType.equals("")){
//            mapContext.put("countType",countType);
//        }else {
//            mapContext.put("countType",1);
//        }
//        mapContext.put("branchId",branchId);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        RequestResult result= null;
//        try {
//            result = this.customOrderStatementFacade.selectByfilter(sdf.parse(startTime), sdf.parse(endTime), mapContext,dateType);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return jsonMapper.toJson(result);
//    }
//
//    @ApiOperation(value="本周订单报表",notes="本周业务报表")//无用
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "countType",value = "1:下单，2：有效，3延期",dataType = "string",paramType = "path")
//    })
//    @GetMapping("/customOrderStatements/week/{countType}")
//    private String weekCustomOrderStatements(@PathVariable String countType){
//        JsonMapper jsonMapper=new JsonMapper();
//        String uid = WebUtils.getCurrUserId();
//        String branchId =WebUtils.getCurrBranchId();
//        if(uid==null){
//            return jsonMapper.toJson(ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
//        }
//        MapContext mapParam = MapContext.newOne();
//        mapParam.put("branchId",branchId);
//        mapParam.put("countType",countType);
//        RequestResult result=this.customOrderStatementFacade.countOrderByWeek(mapParam);
//        return jsonMapper.toJson(result);
//    }
//    @ApiOperation(value="本月业务报表接口",notes="本月业务报表接口")//无用
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "countType",value = "1:下单，2：有效，3延期",dataType = "string",paramType = "path")
//    })
//    @GetMapping("/customOrderStatements/month/{countType}")
//    private String monthCustomOrderStatements(@PathVariable(required = false) String countType){
//        JsonMapper jsonMapper=new JsonMapper();
//        String uid = WebUtils.getCurrUserId();
//        String branchId =WebUtils.getCurrBranchId();
//        if(uid==null){
//            return jsonMapper.toJson(ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
//        }
//        MapContext mapParam = MapContext.newOne();
//        mapParam.put("branchId",branchId);
//        mapParam.put("countType",countType);
//        RequestResult result=this.customOrderStatementFacade.countOrderByMonth(mapParam);
//        return jsonMapper.toJson(result);
//    }
//    @ApiOperation(value="本季业务报表接口",notes="本季业务报表接口")//无用
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "countType",value = "1:下单，2：有效，3延期",dataType = "string",paramType = "path")
//    })
//    @GetMapping("/customOrderStatements/quarter/{countType}")
//    private String quarterCustomOrderStatements(@PathVariable String countType){
//        JsonMapper jsonMapper=new JsonMapper();
//        String uid = WebUtils.getCurrUserId();
//        String branchId =WebUtils.getCurrBranchId();
//        if(uid==null){
//            return jsonMapper.toJson(ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
//        }
//        MapContext mapParam = MapContext.newOne();
//        mapParam.put("branchId",branchId);
//        mapParam.put("countType",countType);
//        mapParam.put("month1",getCurrentSeason(1));
//        mapParam.put("month2",getCurrentSeason(2));
//        mapParam.put("month3",getCurrentSeason(3));
//        RequestResult result=this.customOrderStatementFacade.countOrderByQuarter(mapParam);
//        return jsonMapper.toJson(result);
//    }
//    @ApiOperation(value="本年业务报表接口",notes="本年业务报表接口")//无用
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "countType",value = "1:下单，2：有效，3延期",dataType = "string",paramType = "path")
//    })
//    @GetMapping("/customOrderStatements/year/{countType}")
//    private String yearCustomOrderStatements(@PathVariable String countType){
//        JsonMapper jsonMapper=new JsonMapper();
//        String uid = WebUtils.getCurrUserId();
//        String branchId =WebUtils.getCurrBranchId();
//        if(uid==null){
//            return jsonMapper.toJson(ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
//        }
//        MapContext mapParam = MapContext.newOne();
//        mapParam.put("branchId",branchId);
//        mapParam.put("countType",countType);
//        RequestResult result=this.customOrderStatementFacade.countOrderByYear(mapParam);
//        return jsonMapper.toJson(result);
//    }
	@ApiOperation(value = "订单汇总统计接口", notes = "订单汇总统计接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "yearTime", value = "年份", dataType = "string", paramType = "query")
	})
	@GetMapping("/customOrderStatements")
	private String selectOrderCount(@RequestParam(required = false) String yearTime) {
		JsonMapper jsonMapper = JsonMapper.createAllToStringMapper();
		String branchId = WebUtils.getCurrBranchId();
		RequestResult result = this.customOrderStatementFacade.selectOrderCount(branchId,yearTime);
        return jsonMapper.toJson(result);

	}

	@ApiOperation(value = "订单月份明细统计接口", notes = "订单月份明细统计接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "monthTime", value = "年月 例：2019-10", dataType = "string", paramType = "query")
	})
	@GetMapping("/customOrderStatements/month")
	private String selectOrderMonthCount(@RequestParam(required = false) String monthTime) {
		JsonMapper jsonMapper = JsonMapper.createAllToStringMapper();
		String branchId = WebUtils.getCurrBranchId();
		RequestResult result = this.customOrderStatementFacade.selectOrderMonthCount(branchId,monthTime);
		return jsonMapper.toJson(result);

	}
	@ApiOperation(value = "外协单月份明细统计接口", notes = "外协单月份明细统计接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "monthTime", value = "年月 例：2019-10", dataType = "string", paramType = "query")
	})
	@GetMapping("/coordinationStatements/month")
	private String selectcoordinationMonthCount(@RequestParam(required = false) String monthTime) {
		JsonMapper jsonMapper = JsonMapper.createAllToStringMapper();
		String branchId = WebUtils.getCurrBranchId();
		RequestResult result = this.customOrderStatementFacade.selectcoordinationMonthCount(branchId,monthTime);
		return jsonMapper.toJson(result);

	}
	@ApiOperation(value = "外协厂家排名统计接口", notes = "外协厂家排名统计接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "startTimeTime", value = "开始时间", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "endTimeTime", value = "开始时间", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "coordinationName", value = "外协厂家名称", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "coordinationAddress", value = "外协厂家地址", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "order", value = "数量倒叙-0 数量正序-1 金额倒叙-2 金额正序-3", dataType = "string", paramType = "query")
	})
	@GetMapping("/coordinationStatements/ranking")
	private String coordinationOrderRanking(@RequestParam(required = false) String startTimeTime,
											@RequestParam(required = false) String endTimeTime,
											@RequestParam(required = false) String coordinationName,
											@RequestParam(required = false) String coordinationAddress,
											@RequestParam(required = false) String order) {
		JsonMapper jsonMapper = JsonMapper.createAllToStringMapper();
		String branchId = WebUtils.getCurrBranchId();
		MapContext mapContext=MapContext.newOne();
		mapContext.put("branchId",branchId);
		if(coordinationName!=null&&!coordinationName.equals("")){
			mapContext.put("coordinationName",coordinationName);
		}
		if(coordinationAddress!=null&&!coordinationAddress.equals("")){
			mapContext.put("coordinationAddress",coordinationAddress);
		}
		if(order!=null&&!order.equals("")){
			mapContext.put("order",order);
		}else {
			mapContext.put("order",0);
		}
		RequestResult result = this.customOrderStatementFacade.coordinationOrderRanking(startTimeTime,endTimeTime,mapContext);
		return jsonMapper.toJson(result);

	}

	@ApiOperation(value = "外协厂家详情数据统计", notes = "外协厂家详情数据统计")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "coordinationName", value = "外协厂家名称", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "yearTime", value = "年月 例：2019-10", dataType = "string", paramType = "query")
	})
	@GetMapping("/coordinationStatements/info")
	private String selectcoordinationInfoCount(@RequestParam(required = false) String coordinationName,
											   @RequestParam(required = false) String yearTime) {
		JsonMapper jsonMapper = JsonMapper.createAllToStringMapper();
		RequestResult result = this.customOrderStatementFacade.selectcoordinationInfoCount(coordinationName,yearTime);
		return jsonMapper.toJson(result);

	}


//    private int getCurrentSeason(int num){
//        Calendar cal = Calendar.getInstance();
//        int month = cal.get(Calendar.MONTH) + 1;
//        if(month<=3 && month>0){
//            return num;
//        }else if(month>3 && month<=6){
//            if(num==1){
//                return 4;
//            }else if(num==2){
//                return 5;
//            }else if(num==3){
//                return 6;
//            }
//        }else if(month>6 && month <10){
//            if(num==1){
//                return 7;
//            }else if(num==2){
//                return 8;
//            }else if(num==3){
//                return 9;
//            }
//        }else if(month>=10){
//            if(num==1){
//                return 10;
//            }else if(num==2){
//                return 11;
//            }else if(num==3){
//                return 12;
//            }
//        }
//        return 0;
//    }

	/**
	 * 订单系列统计报表
	 */
	@ApiOperation(value = "订单系列统计报表", notes = "订单系列统计报表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "startDateTime", value = "开始日期", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "endDateTime", value = "结束日期", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "dealerId", value = "经销商id", dataType = "string", paramType = "query")
	})
	@GetMapping("/customOrderStatements/series")
	private String countOrderSeries(@RequestParam(required = false) String startDateTime,
									@RequestParam(required = false) String endDateTime,
									@RequestParam(required = false) String dealerId) {
		JsonMapper jsonMapper = JsonMapper.createAllToStringMapper();
		MapContext mapContext = MapContext.newOne();
		mapContext.put("branchId", WebUtils.getCurrBranchId());
		mapContext.put("companyId", dealerId);
		RequestResult result = this.customOrderStatementFacade.countOrderSeries(startDateTime, endDateTime, mapContext);
		return jsonMapper.toJson(result);
	}

	/**
	 * 外协厂家业务报表
	 */
	@ApiOperation(value = "外协厂家业务报表", notes = "外协厂家业务报表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "startDateTime", value = "开始日期", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "endDateTime", value = "结束日期", dataType = "string", paramType = "query")
	})
	@GetMapping("/corporatePartners")
	private String countCorporatePartners(@RequestParam(required = false) String startDateTime,
										  @RequestParam(required = false) String endDateTime) {
		JsonMapper jsonMapper = JsonMapper.createAllToStringMapper();
		MapContext mapContext = MapContext.newOne();
		mapContext.put("branchId", WebUtils.getCurrBranchId());
		RequestResult result = this.customOrderStatementFacade.countCorporatePartners(startDateTime, endDateTime, mapContext);
		return jsonMapper.toJson(result);
	}

	/**
	 * 市场部员工绩效报表
	 */
	@ApiOperation(value = "市场部员工绩效报表", notes = "市场部员工绩效报表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "startDateTime", value = "开始日期", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "endDateTime", value = "结束日期", dataType = "string", paramType = "query")
	})
	@GetMapping("/salemanAchievements")
	private String countSalemanAchievements(@RequestParam(required = false) String startDateTime,
											@RequestParam(required = false) String endDateTime) {
		JsonMapper jsonMapper = JsonMapper.createAllToStringMapper();
		MapContext mapContext = MapContext.newOne();
		mapContext.put("branchId", WebUtils.getCurrBranchId());
		RequestResult result = this.customOrderStatementFacade.countSalemanAchievements(startDateTime, endDateTime, mapContext);
		return jsonMapper.toJson(result);
	}
	/**
	 * 订单部员工绩效报表
	 */
	@ApiOperation(value = "订单部员工绩效报表", notes = "订单部员工绩效报表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "startDateTime", value = "开始日期", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "endDateTime", value = "结束日期", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "monthTime", value = "月份 ", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "role", value = "角色 全部-1 接单员-2 下单员-3", dataType = "string", paramType = "query")
	})
	@GetMapping("/orderDeptAchievements")
	private String countOrderDeptAchievements(@RequestParam(required = false) String startDateTime,
											@RequestParam(required = false) String endDateTime,
											  @RequestParam(required = false) String monthTime,
											  @RequestParam(required = false) String role) {
		JsonMapper jsonMapper = JsonMapper.createAllToStringMapper();
		MapContext mapContext = MapContext.newOne();
		mapContext.put("branchId", WebUtils.getCurrBranchId());
		if(monthTime!=null&&!monthTime.equals("")){
			mapContext.put("monthTime",monthTime);
		}
		if(role!=null&&!role.equals("")){
			mapContext.put("role",role);
		}
		if(startDateTime!=null&&!startDateTime.equals("")){
			mapContext.put("startTime",startDateTime);
		}
		if(endDateTime!=null&&!endDateTime.equals("")){
			mapContext.put("endTime",endDateTime);
		}
		if(startDateTime==null||startDateTime.equals("")){
			if(monthTime!=null&&!monthTime.equals("")){
				SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM");
				try {
					mapContext.put("startTime",DateUtilsExt.getFirstDayOfMonth(dateFormat.parse(monthTime)));
					mapContext.put("endTime",DateUtilsExt.getLastDayOfMonth(dateFormat.parse(monthTime)));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}else {
				mapContext.put("startTime",DateUtilsExt.getFirstDayOfMonth(DateUtil.now()));
				mapContext.put("endTime",DateUtilsExt.getLastDayOfMonth(DateUtil.now()));
			}
		}
		RequestResult result = this.customOrderStatementFacade.countOrderDeptAchievements(mapContext);
		return jsonMapper.toJson(result);
	}
	/**
	 * 订单部员工绩效报表
	 */
	@ApiOperation(value = "订单部员工个人绩效报表", notes = "订单部员工个人绩效报表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "startDateTime", value = "开始日期", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "endDateTime", value = "结束日期", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "monthTime", value = "月份 ", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "userId", value = "用户id", dataType = "string", paramType = "query")
	})
	@GetMapping("/orderDeptAchievements/user")
	private String countUserAchievements(@RequestParam(required = false) String startDateTime,
											  @RequestParam(required = false) String endDateTime,
											  @RequestParam(required = false) String monthTime,
											  @RequestParam(required = false) String userId,
										 @RequestParam(required = false)Integer pageNum,
										 @RequestParam(required = false)Integer pageSize) {
		JsonMapper jsonMapper = JsonMapper.createAllToStringMapper();
		MapContext mapContext = MapContext.newOne();
		mapContext.put("branchId", WebUtils.getCurrBranchId());
		if(monthTime!=null&&!monthTime.equals("")){
			mapContext.put("monthTime",monthTime);
		}
		if(userId!=null&&!userId.equals("")){
			mapContext.put("userId",userId);
		}else {
			mapContext.put("userId",WebUtils.getCurrUserId());
		}
		if(startDateTime!=null&&!startDateTime.equals("")){
			mapContext.put("startTime",startDateTime);
		}
		if(endDateTime!=null&&!endDateTime.equals("")){
			mapContext.put("endTime",endDateTime);
		}
		if(startDateTime==null||startDateTime.equals("")){
			if(monthTime!=null&&!monthTime.equals("")){
				SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM");
				try {
					mapContext.put("startTime",DateUtilsExt.getFirstDayOfMonth(dateFormat.parse(monthTime)));
					mapContext.put("endTime",DateUtilsExt.getLastDayOfMonth(dateFormat.parse(monthTime)));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}else {
				mapContext.put("startTime",DateUtilsExt.getFirstDayOfMonth(DateUtil.now()));
				mapContext.put("endTime",DateUtilsExt.getLastDayOfMonth(DateUtil.now()));
			}
		}
		RequestResult result = this.customOrderStatementFacade.countUserAchievements(mapContext,pageNum,pageSize);
		return jsonMapper.toJson(result);
	}
	@ApiOperation(value = "新版订单报表（日报）", notes = "新版订单报表（日报）")
	@GetMapping("/countByday")
	private String newOrderCount(){
		JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
		String branchId=WebUtils.getCurrBranchId();
		RequestResult result=this.customOrderStatementFacade.newOrderCount(branchId);
		return jsonMapper.toJson(result);
	}
	@ApiOperation(value = "新版订单报表（月报）", notes = "新版订单报表（月报）")
	@GetMapping("/countByMonth")
	private String newOrderCountByMonth(){
		JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
		String branchId=WebUtils.getCurrBranchId();
		RequestResult result=this.customOrderStatementFacade.newOrderCountByMonth(branchId);
		return jsonMapper.toJson(result);
	}
	@ApiOperation(value = "新版订单报表（年报）", notes = "新版订单报表（年报）")
	@GetMapping("/countByYear")
	private String newOrderCountByYear(){
		JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
		String branchId=WebUtils.getCurrBranchId();
		RequestResult result=this.customOrderStatementFacade.newOrderCountByYear(branchId);
		return jsonMapper.toJson(result);
	}

	@ApiOperation(value = "PC首页订单数量统计", notes = "PC首页订单数量统计")
	@GetMapping("/homeOrderCount")
	public RequestResult homeOrderCount() {
		String branchId=WebUtils.getCurrBranchId();
		return this.customOrderStatementFacade.homeOrderCount(branchId);
	}

    @ApiOperation(value = "我的订单数量统计", notes = "我的订单数量统计")
    @GetMapping("/myOrderCount")
	public RequestResult myOrderCount() {
        String branchId = WebUtils.getCurrBranchId();
        String userId = WebUtils.getCurrUserId();
        String companyId = WebUtils.getCurrCompanyId();
        return this.customOrderStatementFacade.myOrderCount(branchId, companyId, userId);
    }
}
