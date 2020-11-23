package com.lwxf.industry4.webapp.controller.wxapi.factory.aftersale;

import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.json.JsonMapper;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.LoginUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.wxapi.factory.aftersale.AftersaleFacade;
import com.lwxf.mybatis.utils.MapContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Api(value="AftersaleController",tags={"F端微信小程序接口:售后管理接口"})
@RestController
@RequestMapping(value = "/wxapi/f/aftersales", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class AftersaleController {

    @Resource(name = "wxfAftersaleApplyFacade")
    private AftersaleFacade aftersaleFacade;

    /**
     * 售后单首页
     * @return
     */
    @ApiOperation(value = "售后单首页", notes = "")
    @GetMapping("/index")
    public String viewIndex(HttpServletRequest request){
        JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
        String atoken=request.getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String branchId =mapInfo.get("branchId")==null?null:mapInfo.get("branchId").toString();
        RequestResult result=this.aftersaleFacade.viewIndex("branchId");//test
        return jsonMapper.toJson(result);
    }

    /**
     * 售后申请单列表
     */
    @ApiOperation(value = "售后单查询列表", notes = "")
    @GetMapping("/aftersaleApplies")
    public  String findAftersaleApplyList(@RequestParam(required = false) String condation,
                                          @RequestParam(required = false) String startTime,
                                          @RequestParam(required = false) String endTime,
                                          @RequestParam(required = false) String cityId,
                                          @RequestParam(required = false) String dealerId,
                                          @RequestParam(required = false) String no,
                                          @RequestParam(required = false) Integer pageNum,
                                          @RequestParam(required = false) Integer pageSize){
        JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
        String atoken= WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
        if(uid==null){
            return jsonMapper.toJson(ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
        }
        String branchid =mapInfo.get("branchId")==null?null:mapInfo.get("branchId").toString();
        if (null == pageSize) {
            pageSize = AppBeanInjector.configuration.getPageSizeLimit();
        }
        if (null == pageNum) {
            pageNum = 1;
        }
        MapContext mapContext=MapContext.newOne();
        if(LwxfStringUtils.isNotBlank(condation)){
            mapContext.put("condation",condation);
        }
        if(LwxfStringUtils.isNotBlank(startTime)){
            mapContext.put("startTime",startTime);
        }
        if(LwxfStringUtils.isNotBlank(endTime)){
            mapContext.put("endTime",endTime);
        }
        if(LwxfStringUtils.isNotBlank(cityId)){
            mapContext.put("cityId",cityId);
        }
        if(LwxfStringUtils.isNotBlank(dealerId)){
            mapContext.put("dealerId",dealerId);
        }
        if(LwxfStringUtils.isNotBlank(no)){
            mapContext.put("no",no);
        }
        RequestResult result=this.aftersaleFacade.findAftersaleApplyList(branchid,pageNum,pageSize,mapContext);
        return jsonMapper.toJson(result);
    }


    /**
     * 售后单详情
     * @param aftersaleApplyId
     * @return
     */
    @ApiOperation(value = "售后单详情", notes = "")
    @GetMapping("/{aftersaleApplyId}")
    public String factoryAftersaleApplyInfo(@PathVariable String aftersaleApplyId){
        JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
        RequestResult result=this.aftersaleFacade.factoryAftersaleApplyInfo(aftersaleApplyId);
        return jsonMapper.toJson(result);
    }

//    /**
//     * 参数组成
//     * @param beginTime  开始时间
//     * @return
//     */
//    private MapContext createMapContent(String beginTime,String endTime,String leaderName,String custName,String cityName,String prodType,String series,Integer status,Integer isHandle,String orderNo) {
//        MapContext mapContext = MapContext.newOne();
//        if (beginTime!=null && !beginTime.equals("")) {
//            mapContext.put("startTime", beginTime);
//        }
//        if (endTime!=null && !endTime.equals("")) {
//            mapContext.put("endTime", endTime);
//        }
//        if (leaderName!=null && !leaderName.equals("")) {
//            mapContext.put("leaderName", leaderName);
//        }
//        if (custName!=null && !custName.equals("")) {
//            mapContext.put("status", custName);
//        }
//        if (cityName!=null && !cityName.equals("")) {
//            mapContext.put("cityName", cityName);
//        }
//        if (prodType!=null && !prodType.equals("")) {
//            mapContext.put("productType", prodType);
//        }
//        if (series!=null && !series.equals("")) {
//            mapContext.put("productSeries", series);
//        }
//        if (orderNo!=null && !orderNo.equals("")) {
//            mapContext.put("orderNo", orderNo);
//        }
//        if (status!=null) {
//            mapContext.put("status", status);
//        }else{
//            //处理页面查询
//            if(isHandle!=null && isHandle==1){
//                mapContext.put("statusHandles", "1");
//            }
//        }
//        return mapContext;
//    }
}
