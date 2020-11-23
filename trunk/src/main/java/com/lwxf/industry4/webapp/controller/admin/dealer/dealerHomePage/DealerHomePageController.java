package com.lwxf.industry4.webapp.controller.admin.dealer.dealerHomePage;

import com.lwxf.commons.json.JsonMapper;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.facade.admin.factory.statement.HomePageStatementFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2020-03-25 16:08
 * @version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Api(value="DealerHomePageController",tags={"B端后台管理接口:首页报表接口"})
@RestController("DealerHomePageController")
@RequestMapping(value = "/api/b/",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class DealerHomePageController {
    @Resource(name = "homePageStatementFacade")
    private HomePageStatementFacade homePageStatementFacade;

    @GetMapping("homePageStatement")
    @ApiOperation(value = "首页报表接口",notes = "首页报表接口")
    private String findDealerHomePageStatement(@RequestParam(required = false)@ApiParam(value = "开始时间") String startTime,
                                         @RequestParam(required = false)@ApiParam(value = "结束时间") String endTime,
                                         @RequestParam(required = false)@ApiParam(value = "1-本月，2-本年") String type){
        JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
        RequestResult result=this.homePageStatementFacade.findDealerHomePageStatement(startTime,endTime,type);
        return jsonMapper.toJson(result);

    }

    @GetMapping("/homePageStatement/v2")
    @ApiOperation(value = "首页报表接口(新版)",notes = "首页报表接口(新版)")
    private String findDealerHomePageStatementV2(@RequestParam(required = false)@ApiParam(value = "订单开始时间") String orderStartTime,
                                               @RequestParam(required = false)@ApiParam(value = "订单结束时间") String orderEndTime,
                                               @RequestParam(required = false)@ApiParam(value = "订单 1-本周 2-本月 3-本年") String orderType,
                                               @RequestParam(required = false)@ApiParam(value = "财务开始时间") String payStartTime,
                                               @RequestParam(required = false)@ApiParam(value = "财务结束时间") String payEndTime,
                                               @RequestParam(required = false)@ApiParam(value = "财务 1-本周 2-本月 3-本年") String payType)
    {
        JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
        RequestResult result=this.homePageStatementFacade.findDealerHomePageStatementV2(orderStartTime,orderEndTime,orderType,payStartTime,payEndTime,payType);
        return jsonMapper.toJson(result);

    }
}
