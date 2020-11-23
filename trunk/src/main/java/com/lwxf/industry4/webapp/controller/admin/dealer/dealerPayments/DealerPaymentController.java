package com.lwxf.industry4.webapp.controller.admin.dealer.dealerPayments;

import com.lwxf.commons.json.JsonMapper;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.facade.admin.factory.finance.FinanceFacade;
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
 * @create：2020-03-25 17:09
 * @version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Api(value="DealerPaymentController",tags={"B端后台管理接口:经销商财务统计接口"})
@RestController("DealerPaymentController")
@RequestMapping(value = "/api/b/",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class DealerPaymentController {
    @Resource(name = "financeFacade")
    private FinanceFacade financeFacade;

    @GetMapping("/payments")
    @ApiOperation(value = "经销商财务信息统计",notes = "经销商财务信息统计")
    private String findDealerPaymentInfo(@RequestParam(required = false)@ApiParam(value = "本月-1 本年-2") String timeType,
                                         @RequestParam(required = false)@ApiParam(value = "开始时间") String startTime,
                                         @RequestParam(required = false)@ApiParam(value = "结束时间") String endTime){
        JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
        RequestResult result=this.financeFacade.findDealerPaymentCountInfo(timeType,startTime,endTime);
        return jsonMapper.toJson(result);
    }
}
