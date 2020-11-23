package com.lwxf.industry4.webapp.controller.wxapi.factory.statement;

import com.lwxf.commons.json.JsonMapper;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.LoginUtil;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.wxapi.factory.statements.PaymentStatementFacade;
import com.lwxf.mybatis.utils.MapContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 功能：
 *
 * @author：zhangxiaolin(3965488@qq.com)
 * @create：2019/6/30 0030 9:28
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Api(value="wxPaymentStatementController",tags={"F端微信小程序接口:财务报表接口"})
@RestController("wxPaymentStatementController")
@RequestMapping(value = "/wxapi/f/",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class PaymentStatementController {
    @Resource(name = "wxpaymentStatementFacade")
    private PaymentStatementFacade paymentStatementFacade;

    /**
     * 业务报表
     * @param startTime
     * @param endTime
     * @param request
     * @return
     */
    @ApiOperation(value="订单报表条件搜索接口",notes="订单报表条件搜索接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime",value = "开始时间",dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "endTime",value = "结束时间",dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "countType",value = "1:实收，2：支出，3：预收，3：利润",dataType = "string",paramType = "query")
    })
    @GetMapping("/paymentStatements")
    private String paymentStatements(
            @RequestParam(required = false) String startTime,
            @RequestParam(required = false) String endTime,
            @RequestParam(required = false) String countType,
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
        if(countType!=null&&!countType.equals("")){
            mapContext.put("countType",countType);
        }
        mapContext.put("branchId",branchId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        RequestResult result=null;
        try {
             result = this.paymentStatementFacade.selectByfilter(sdf.parse(startTime), sdf.parse(endTime), mapContext);
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
    private String weekPaymentStatements(@PathVariable String countType,
                                             HttpServletRequest request){
        JsonMapper jsonMapper=new JsonMapper();
        String atoken=request.getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
        String branchId =mapInfo.get("branchId")==null?null:mapInfo.get("branchId").toString();
        if(uid==null){
            return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
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
    private String monthPaymentStatements(@PathVariable(required = false) String countType,
                                              HttpServletRequest request){
        JsonMapper jsonMapper=new JsonMapper();
        String atoken=request.getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
        String branchId =mapInfo.get("branchId")==null?null:mapInfo.get("branchId").toString();
        if(uid==null){
            return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
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
    private String quarterPaymentStatements(@PathVariable String countType,
                                                HttpServletRequest request){
        JsonMapper jsonMapper=new JsonMapper();
        String atoken=request.getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
        String branchId =mapInfo.get("branchId")==null?null:mapInfo.get("branchId").toString();
        if(uid==null){
            return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
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
    private String yearPaymentStatements(@PathVariable String countType,
                                             HttpServletRequest request){
        JsonMapper jsonMapper=new JsonMapper();
        String atoken=request.getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
        String branchId =mapInfo.get("branchId")==null?null:mapInfo.get("branchId").toString();
        if(uid==null){
            return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
        }
        MapContext mapParam = MapContext.newOne();
        mapParam.put("branchId",branchId);
        mapParam.put("countType",countType);
        RequestResult result=this.paymentStatementFacade.countPaymentByYear(mapParam);
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
}
