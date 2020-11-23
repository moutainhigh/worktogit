package com.lwxf.industry4.webapp.controller.wxapi.factory.statement;


import com.lwxf.commons.json.JsonMapper;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.LoginUtil;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.wxapi.factory.statements.CustomOrderStatementFacade;
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
 * @author：zhangxiaolin(17838625030@163.com)
 * @create：2019/6/30 0030 9:28
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Api(value="CustomOrderStatementController",tags={"F端微信小程序接口:订单报表接口"})
@RestController("wxCustomOrderStatementController")
@RequestMapping(value = "/wxapi/f/",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class CustomOrderStatementController {

    @Resource(name = "wxCustomOrderStatementFacade")
    private CustomOrderStatementFacade customOrderStatementFacade;

    /**
     * 业务报表
     * @param startTime
     * @param endTime
     * @param cityId
     * @param salesmanId
     * @param request
     * @return
     */
    @ApiOperation(value="订单报表条件搜索接口",notes="订单报表条件搜索接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startTime",value = "开始时间",dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "endTime",value = "结束时间",dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "cityId",value = "区域id",dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "salesmanId",value = "业务经理id",dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "countType",value = "要查看的类型，在网统计传“0”，签约统计传“1”，退网-2，意向-3",dataType = "string",paramType = "query")
    })
    @GetMapping("/customOrderStatements")
    private String customOrderStatements(
                                    @RequestParam(required = false) String startTime,
                                    @RequestParam(required = false) String endTime,
                                    @RequestParam(required = false) String cityId,
                                    @RequestParam(required = false) String salesmanId,
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
        if(cityId!=null&&!cityId.equals("")){
            mapContext.put("cityId",cityId);
        }
        if(salesmanId!=null&&!salesmanId.equals("")){
            mapContext.put("salesmanId",salesmanId);
        }
        if(countType!=null&&!countType.equals("")){
            mapContext.put("countType",countType);
        }
        mapContext.put("branchId",branchId);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        RequestResult result=null;
        try {
            result = this.customOrderStatementFacade.selectByfilter(sdf.parse(startTime), sdf.parse(endTime), mapContext);
        }catch(ParseException ex){
            return jsonMapper.toJson(ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT, AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT")));
        }
        return jsonMapper.toJson(result);
    }

    @ApiOperation(value="本周订单报表",notes="本周业务报表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "countType",value = "1:下单，2：有效，3延期",dataType = "string",paramType = "path")
    })
    @GetMapping("/customOrderStatements/week/{countType}")
    private String weekCustomOrderStatements(@PathVariable String countType,
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
        RequestResult result=this.customOrderStatementFacade.countOrderByWeek(mapParam);
        return jsonMapper.toJson(result);
    }
    @ApiOperation(value="本月业务报表接口",notes="本月业务报表接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "countType",value = "1:下单，2：有效，3延期",dataType = "string",paramType = "path")
    })
    @GetMapping("/customOrderStatements/month/{countType}")
    private String monthCustomOrderStatements(@PathVariable(required = false) String countType,
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
        RequestResult result=this.customOrderStatementFacade.countOrderByMonth(mapParam);
        return jsonMapper.toJson(result);
    }
    @ApiOperation(value="本季业务报表接口",notes="本季业务报表接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "countType",value = "1:下单，2：有效，3延期",dataType = "string",paramType = "path")
    })
    @GetMapping("/customOrderStatements/quarter/{countType}")
    private String quarterCustomOrderStatements(@PathVariable String countType,
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
        RequestResult result=this.customOrderStatementFacade.countOrderByQuarter(mapParam);
        return jsonMapper.toJson(result);
    }
    @ApiOperation(value="本年业务报表接口",notes="本年业务报表接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "countType",value = "1:下单，2：有效，3延期",dataType = "string",paramType = "path")
    })
    @GetMapping("/customOrderStatements/year/{countType}")
    private String yearCustomOrderStatements(@PathVariable String countType,
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
        RequestResult result=this.customOrderStatementFacade.countOrderByYear(mapParam);
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
     * 小程序订单报表列表
     */
    @ApiOperation(value="小程序订单报表列表",notes="小程序订单报表列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "year",value = "年份",dataType = "string",paramType = "query")
    })
    @GetMapping("/customOrderStatements/countlist")
    public String findOrderCountList(@RequestParam (required = false)String year,
                                             HttpServletRequest request){
        JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
        String atoken=request.getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
        String branchId =mapInfo.get("branchId")==null?null:mapInfo.get("branchId").toString();
        if(uid==null){
            return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
        }
        RequestResult result=this.customOrderStatementFacade.findOrderCountList(year,branchId);
        return jsonMapper.toJson(result);
    }
    /**
     * 小程序订单月份详情报表
     */
    @ApiOperation(value="小程序订单月份详情报表",notes="小程序订单月份详情报表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "monthTime",value = "年月 例 2019-10",dataType = "string",paramType = "query")
    })
    @GetMapping("/customOrderStatements/monthCount")
    public String findMonthOrderCountInfo(@RequestParam (required = false)String monthTime,
                                     HttpServletRequest request){
        JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
        String atoken=request.getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
        String branchId =mapInfo.get("branchId")==null?null:mapInfo.get("branchId").toString();
        if(uid==null){
            return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
        }
        RequestResult result=this.customOrderStatementFacade.findMonthOrderCountInfo(monthTime,branchId);
        return jsonMapper.toJson(result);
    }


}
