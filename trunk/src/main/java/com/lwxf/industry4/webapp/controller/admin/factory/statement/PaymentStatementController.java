package com.lwxf.industry4.webapp.controller.admin.factory.statement;

import com.lwxf.commons.json.JsonMapper;
import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.DateUtilsExt;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.statement.FinanceReportOrderDto;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.statement.CustomOrderStatementFacade;
import com.lwxf.industry4.webapp.facade.admin.factory.statement.PaymentStatementFacade;
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
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 功能：
 *
 * @author：zhangxiaolin(3965488@qq.com)
 * @create：2019/6/30 0030 9:28
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Api(value="PaymentStatementController",tags={"F端后台管理接口:财务报表接口"})
@RestController("PaymentStatementController")
@RequestMapping(value = "/api/f/",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class PaymentStatementController {
    @Resource(name = "paymentStatementFacade")
    private PaymentStatementFacade paymentStatementFacade;

    /**
     * 财务报表条件搜索接口
     * @param startTime
     * @param endTime
     * @return
     */
    @ApiOperation(value="财务报表条件搜索接口",notes="财务报表条件搜索接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime",value = "开始时间",dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "endTime",value = "结束时间",dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "countType",value = "1:实收，2：支出，3：预收，3：利润",dataType = "string",paramType = "query")
    })
    @GetMapping("/paymentStatements")
    private String paymentStatements(
            @RequestParam(required = false) String startTime,
            @RequestParam(required = false) String endTime,
            @RequestParam(required = false) Integer dateType,
            @RequestParam(required = false) String countType){
        JsonMapper jsonMapper=new JsonMapper();
        String uid = WebUtils.getCurrUserId();
        String branchId =WebUtils.getCurrBranchId();
       // String branchId ="40ord3va6adp";
        if(uid==null){
            return jsonMapper.toJson(ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
        }
        MapContext mapContext=MapContext.newOne();
        if(startTime!=null&&!startTime.equals("")){
            mapContext.put("startTime",startTime);
        }
        if(endTime!=null&&!endTime.equals("")){
            mapContext.put("endTime",endTime);
        }
        if(countType!=null&&!countType.equals("")){
            mapContext.put("countType",countType);
        }
        mapContext.put("branchId",branchId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        RequestResult result=null;
        try {
             result = this.paymentStatementFacade.selectByfilter(sdf.parse(startTime), sdf.parse(endTime), mapContext,dateType);
        }catch(ParseException ex){
            return jsonMapper.toJson(ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT, AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT")));
        }
        return jsonMapper.toJson(result);
    }

    @ApiOperation(value="本周财务报表",notes="本周财务报表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "countType",value = "1:实收，2：支出，3：预收，3：利润",dataType = "string",paramType = "path")
    })
    @GetMapping("/paymentStatements/week/{countType}")
    private String weekPaymentStatements(@PathVariable String countType){
        JsonMapper jsonMapper=new JsonMapper();
        String uid =WebUtils.getCurrUserId();
        String branchId =WebUtils.getCurrBranchId();
        if(uid==null){
            return jsonMapper.toJson(ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
        }
        MapContext mapParam = MapContext.newOne();
        mapParam.put("branchId",branchId);
        mapParam.put("countType",countType);
        RequestResult result=this.paymentStatementFacade.countPaymentByWeek(mapParam);
        return jsonMapper.toJson(result);
    }
    @ApiOperation(value="本月财务报表接口",notes="本月财务报表接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "countType",value = "1:实收，2：支出，3：预收，3：利润",dataType = "string",paramType = "path")
    })
    @GetMapping("/paymentStatements/month/{countType}")
    private String monthPaymentStatements(@PathVariable(required = false) String countType){
        JsonMapper jsonMapper=new JsonMapper();
        String uid =WebUtils.getCurrUserId();
        String branchId =WebUtils.getCurrBranchId();
        if(uid==null){
            return jsonMapper.toJson(ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
        }
        MapContext mapParam = MapContext.newOne();
        mapParam.put("branchId",branchId);
        mapParam.put("countType",countType);
        RequestResult result=this.paymentStatementFacade.countPaymentByMonth(mapParam);
        return jsonMapper.toJson(result);
    }
    @ApiOperation(value="本季财务报表接口",notes="本季财务报表接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "countType",value = "1:实收，2：支出，3：预收，3：利润",dataType = "string",paramType = "path")
    })
    @GetMapping("/paymentStatements/quarter/{countType}")
    private String quarterPaymentStatements(@PathVariable String countType){
        JsonMapper jsonMapper=new JsonMapper();
        String uid =WebUtils.getCurrUserId();
        String branchId =WebUtils.getCurrBranchId();
        if(uid==null){
            return jsonMapper.toJson(ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
        }
        MapContext mapParam = MapContext.newOne();
        mapParam.put("branchId",branchId);
        mapParam.put("countType",countType);
        mapParam.put("month1",getCurrentSeason(1));
        mapParam.put("month2",getCurrentSeason(2));
        mapParam.put("month3",getCurrentSeason(3));
        RequestResult result=this.paymentStatementFacade.countPaymentByQuarter(mapParam);
        return jsonMapper.toJson(result);
    }
    @ApiOperation(value="本年业务报表接口",notes="本年业务报表接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "countType",value = "1:实收，2：支出，3：预收，3：利润",dataType = "string",paramType = "path")
    })
    @GetMapping("/paymentStatements/year/{countType}")
    private String yearPaymentStatements(@PathVariable String countType){
        JsonMapper jsonMapper=new JsonMapper();
        String uid =WebUtils.getCurrUserId();
        String branchId =WebUtils.getCurrBranchId();
        if(uid==null){
            return jsonMapper.toJson(ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
        }
        MapContext mapParam = MapContext.newOne();
        mapParam.put("branchId",branchId);
        mapParam.put("countType",countType);
        RequestResult result=this.paymentStatementFacade.countPaymentByYear(mapParam);
        return jsonMapper.toJson(result);
    }

    /**
     * 两日数据对比
     * @return
     */
    @ApiOperation(value="两日订单量对比数据接口", notes="两日订单量对比数据接口", response = FinanceReportOrderDto.class)
    @GetMapping("/paymentStatements/day/twoDaysContrast")
    public RequestResult twoDaysContrast() {
        MapContext mapContext = MapContext.newOne();
        String branchId = WebUtils.getCurrBranchId();
        mapContext.put("branchId", branchId); // 企业ID
        // 生成日期列表 yyyy-MM-dd
//        String[] dateList = {today, yesterday};
        Integer size = 3; // 获取3天时间
        String[] dateList = new String[size];
        List<String> tempList = DateUtilsExt.getListDayDate(DateUtilsExt.getDayBegin(), -size); // 生成日期列表
        Collections.reverse(tempList); // 逆序
        tempList.toArray(dateList); // 转成数组
        mapContext.put("dateList", dateList);

        return this.paymentStatementFacade.findTwoDaysContrast(mapContext);
    }

    /**
     * 两日数据对比
     * @return
     */
    @ApiOperation(value="两月订单量对比数据接口", notes="两月订单量对比数据接口", response = FinanceReportOrderDto.class)
    @GetMapping("/paymentStatements/month/twoMonthContrast")
    public RequestResult twoMonthContrast() {
        MapContext mapContext = MapContext.newOne();
        String branchId = WebUtils.getCurrBranchId();
        mapContext.put("branchId", branchId); // 企业ID
        // 获取当月
        String date1 = DateUtil.dateTimeToString(DateUtil.getSystemDate(), "yyyy-MM-dd");
        // 获取上个月
        String date2 = DateUtil.dateTimeToString(DateUtilsExt.getBeginDayOfLastMonth(), "yyyy-MM-dd");
        // 获取上上个月
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -2);
        String date3 = DateUtil.dateTimeToString(c.getTime(), "yyyy-MM-dd");
        String[] dateList = {date1, date2, date3};
        mapContext.put("dateList", dateList);

        return this.paymentStatementFacade.findTwoMonthContrast(mapContext);
    }

    @ApiOperation(value = "新版财务订单报表（日报）", notes = "新版财务订单报表（日报）")
    @GetMapping("/paymentStatements/countByday")
    private String newOrderCount(){
        JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
        String branchId=WebUtils.getCurrBranchId();
        RequestResult result=this.paymentStatementFacade.newOrderCount(branchId);
        return jsonMapper.toJson(result);
    }

    @ApiOperation(value = "新版财务订单报表（月报）", notes = "新版财务订单报表（月报）")
    @GetMapping("/paymentStatements/countByMonth")
    private String newOrderMonthCount(){
        JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
        String branchId=WebUtils.getCurrBranchId();
        RequestResult result=this.paymentStatementFacade.newOrderMonthCount(branchId);
        return jsonMapper.toJson(result);
    }

    @ApiOperation(value = "新版财务订单报表（年报）", notes = "新版财务订单报表（年报）")
    @GetMapping("/paymentStatements/countByYear")
    private String newOrderCountByYear(){
        JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
        String branchId=WebUtils.getCurrBranchId();
        RequestResult result=this.paymentStatementFacade.newOrderCountByYear(branchId);
        return jsonMapper.toJson(result);
    }


    private int getCurrentSeason(int num){
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        if(month<=3 && month>0){
            return num;
        }else if(month>3 && month<=6){
            if(num==1){
                return 4;
            }else if(num==2){
                return 5;
            }else if(num==3){
                return 6;
            }
        }else if(month>6 && month <10){
            if(num==1){
                return 7;
            }else if(num==2){
                return 8;
            }else if(num==3){
                return 9;
            }
        }else if(month>=10){
            if(num==1){
                return 10;
            }else if(num==2){
                return 11;
            }else if(num==3){
                return 12;
            }
        }
        return 0;
    }

    /**
     * 生成查询时间条件
     * @param mode
     * @param mapContext
     */
    private void createTime(String mode, MapContext mapContext) {
        // 今年1月1日
        String firstDateOfYear = DateUtil.dateTimeToString(DateUtilsExt.getBeginDayOfYear(), DateUtil.FORMAT_DATE);
        // 现在年月日
        String nowDateOfYear = DateUtil.dateTimeToString(DateUtil.getSystemDate(), DateUtil.FORMAT_DATE);
        // 当月1日
        String firstDayOfMonth = DateUtil.dateTimeToString(DateUtilsExt.getBeginDayOfMonth(), DateUtil.FORMAT_DATE);
        switch (mode) {
            case "day":
                mapContext.put("beginTime", DateUtil.dateTimeToString(DateUtilsExt.getDayBegin(), DateUtil.FORMAT_DATE));
                mapContext.put("endTime", DateUtil.dateTimeToString(DateUtilsExt.getDayBegin(), DateUtil.FORMAT_DATE));
                break;
            case "month":
                mapContext.put("beginTime", firstDayOfMonth);
                mapContext.put("endTime", nowDateOfYear);
                break;
            case "year":
                mapContext.put("beginTime", firstDateOfYear);
                mapContext.put("endTime", nowDateOfYear);
                break;
            default: // 默认按年的时间区间插叙
                mapContext.put("beginTime", firstDateOfYear);
                mapContext.put("endTime", nowDateOfYear);
        }
    }
}
