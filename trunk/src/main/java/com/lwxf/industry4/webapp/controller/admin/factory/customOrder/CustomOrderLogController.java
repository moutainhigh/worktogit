package com.lwxf.industry4.webapp.controller.admin.factory.customOrder;

import com.lwxf.commons.json.JsonMapper;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderLog;
import com.lwxf.industry4.webapp.facade.admin.factory.order.OrderLogFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
@RestController
@Api(value = "CustomOrderLogController",tags = {"F端后台管理:订单日志接口"})
@RequestMapping(value = "/api/f/customOrderLog",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class CustomOrderLogController {

    @Resource(name = "orderLogFacade")
    private OrderLogFacade orderLogFacade;

    /**
     * 查询订单查询日志
     * @return
     */
    @GetMapping
    @ApiOperation(value = "查询订单操作日志",notes = "查询订单操作日志",response = CustomOrderLog.class)
    private String findOrderList(@RequestParam(required = false)@ApiParam(value = "订单编号") String id) {
        JsonMapper jsonMapper = new JsonMapper();
        return jsonMapper.toJson(this.orderLogFacade.findOrderLogs(id));
    }
}
