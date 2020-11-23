package com.lwxf.industry4.webapp.controller.admin.factory.customOrder;

import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.customorder.CustomOrderRemindStatus;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.customorder.CustomOrderRemindDto;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.order.CustomOrderRemindFacade;
import com.lwxf.mybatis.utils.MapContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 功能：订单催款
 *
 * @author：DJL
 * @create：2020/1/6 15:04
 * @version：2020 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Api(value = "customOrderRemindController",tags = {"订单催款管理接口"})
@RestController
@RequestMapping(value = "/api/f/customOrderRemind", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class CustomOrderRemindController {
    @Resource(name = "customOrderRemindFacade")
    private CustomOrderRemindFacade customOrderRemindFacade;

    @GetMapping
    @ApiOperation(value = "通过条件查询催款列表",notes = "通过条件查询催款列表",response = CustomOrderRemindDto.class)
    public RequestResult findList(@RequestParam(required = false)@ApiParam(value = "页码") Integer pageNum,
                                    @RequestParam(required = false)@ApiParam(value = "每页数据量") Integer pageSize,
                                    @RequestParam(required = false)@ApiParam(value = "订单编号") String no,
                                    @RequestParam(required = false)@ApiParam(value = "催款状态") Integer status,
                                    @RequestParam(required = false)@ApiParam(value = "经销商电话") String dealerTel,
                                    @RequestParam(required = false)@ApiParam(value = "经销商公司名称") String dealerName) {
        if (null == pageSize) {
            pageSize = AppBeanInjector.configuration.getPageSizeLimit();
        }
        if (null == pageNum) {
            pageNum = 1;
        }
        String branchId = WebUtils.getCurrBranchId();
        String userId = WebUtils.getCurrUserId();
        String companyId = WebUtils.getCurrCompanyId();
        MapContext params = createMapContext(no, status, dealerTel, dealerName);
        // 增加催款人条件
        params.put("receiver", userId);
        return customOrderRemindFacade.selectByCondition(pageNum, pageSize, params, userId, branchId, companyId);
    }

    @GetMapping("/{orderId}")
    @ApiOperation(value = "查询订单催款详情", notes = "查询订单催款详情")
    public RequestResult detail(@PathVariable @ApiParam(value = "订单单号") String orderId) {
        return customOrderRemindFacade.selectByOrderId(orderId);
    }

    @PutMapping("/{orderId}")
    @ApiOperation(value = "修改催款信息", notes = "修改催款信息")
    public RequestResult update(@PathVariable @ApiParam(value = "订单id") String orderId, @RequestBody MapContext mapContext) {
        return this.customOrderRemindFacade.updateCustomOrderRemind(orderId, mapContext);
    }

    @PutMapping("/{orderId}/updateback")
    @ApiOperation(value = "催款状态回退", notes = "催款状态回退")
    public RequestResult updateBack(@PathVariable @ApiParam(value = "订单id") String orderId) {
        return this.customOrderRemindFacade.updateBack(orderId);
    }

    @PutMapping("/receive/{id}")
    @ApiOperation(value = "开始催款", notes = "开始催款")
    public RequestResult receiveRemindOrder(@PathVariable @ApiParam(value = "催款单id") String id) {
        return this.customOrderRemindFacade.receiveRemindOrder(id);
    }

    @PutMapping("/complete/{id}")
    @ApiOperation(value = "完成催款", notes = "完成催款")
    public RequestResult completeRemindOrder(@PathVariable @ApiParam(value = "催款单id") String id) {
        return this.customOrderRemindFacade.completeRemindOrder(id);
    }

    /**
     * 保存催款人信息
     * @param orderId
     * @param mapContext
     * @return
     */
    @PostMapping("/{orderId}")
    @ApiOperation(value = "保存催款人信息", notes = "保存催款人信息")
    public RequestResult saveRemindOrderReceive(@PathVariable @ApiParam(value = "订单id") String orderId, @RequestBody MapContext mapContext) {
        return this.customOrderRemindFacade.saveRemindOrderReceive(orderId, mapContext);
    }

    // 构造查询条件
    private MapContext createMapContext(String no, Integer status, String dealerTel, String dealerName) {
        MapContext mapContext = MapContext.newOne();

        if (no != null && !no.trim().equals("")) {
            mapContext.put(WebConstant.STRING_NO, no);
        }
        if (status != null && CustomOrderRemindStatus.contains(status)) {
            mapContext.put(WebConstant.KEY_ENTITY_STATUS, status);
        }
        if (dealerTel != null && !dealerTel.trim().equals("")) {
            mapContext.put("dealerTel", dealerTel);
        }
        if (dealerName != null && !dealerName.trim().equals("")) {
            mapContext.put("dealerName", dealerName);
        }

        return mapContext;
    }
}
