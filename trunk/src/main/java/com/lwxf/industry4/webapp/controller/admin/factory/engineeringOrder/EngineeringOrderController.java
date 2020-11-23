package com.lwxf.industry4.webapp.controller.admin.factory.engineeringOrder;

import com.lwxf.commons.json.JsonMapper;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.engineeringOrder.EngineeringDto;
import com.lwxf.industry4.webapp.facade.admin.factory.engineeringOrder.EngineeringOrderFacade;
import com.lwxf.mybatis.utils.MapContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2020-05-28 8:41
 * @version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@RestController
@RequestMapping(value = "/api/f/engineeringorder", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
@Api(value = "EngineeringOrderController", tags = "F端后台管理接口：工程单管理")
public class EngineeringOrderController {

    @Resource(name = "engineeringOrderFacade")
    private EngineeringOrderFacade engineeringOrderFacade;

    @GetMapping
    @ApiOperation(value = "查询工程单列表", notes = "查询工程单列表",response = EngineeringDto.class)
    public String findEngineeringOrderList(@RequestParam(required = false) @ApiParam(value = "工程单编号") String no
            , @RequestParam(required = false) @ApiParam(value = "经销商名称") String dealerName
            , @RequestParam(required = false) @ApiParam(value = "终端客户") String customerName
            , @RequestParam(required = false) @ApiParam(value = "收货人名称") String consigneeName
            , @RequestParam(required = false) @ApiParam(value = "收货人电话") String consigneeTel
            , @RequestParam(required = false) @ApiParam(value = "下单开始时间") String startTime
            , @RequestParam(required = false) @ApiParam(value = "下单结束时间") String endTime
            , @RequestParam(required = false) @ApiParam(value = "下单人") String placeOrder
            , @RequestParam(required = false) @ApiParam(value = "接单人") String receiver
            , @RequestParam(required = false) @ApiParam(value = "页面条数") Integer pageSize
            , @RequestParam(required = false) @ApiParam(value = "页码") Integer pageNum
                                                                                      ) {

        JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
        MapContext mapContext=MapContext.newOne();
        String branchId= WebUtils.getCurrBranchId();
        mapContext.put("branchId",branchId);
        if(LwxfStringUtils.isNotBlank(no)){
            mapContext.put("no",no);
        }
        if(LwxfStringUtils.isNotBlank(dealerName)){
            mapContext.put("dealerName",dealerName);
        }
        if(LwxfStringUtils.isNotBlank(customerName)){
            mapContext.put("customerName",customerName);
        }
        if(LwxfStringUtils.isNotBlank(consigneeName)){
            mapContext.put("consigneeName",consigneeName);
        }
        if(LwxfStringUtils.isNotBlank(consigneeTel)){
            mapContext.put("consigneeTel",consigneeTel);
        }
        if(LwxfStringUtils.isNotBlank(startTime)){
            mapContext.put("startTime",startTime);
        }
        if(LwxfStringUtils.isNotBlank(endTime)){
            mapContext.put("endTime",endTime);
        }
        if(LwxfStringUtils.isNotBlank(placeOrder)){
            mapContext.put("placeOrder",placeOrder);
        }
        if(LwxfStringUtils.isNotBlank(receiver)){
            mapContext.put("receiver",receiver);
        }
        RequestResult result=this.engineeringOrderFacade.findEngineeringOrderList(pageSize,pageNum,mapContext);
        return jsonMapper.toJson(result);
    }


    /**
     * 添加工程单
     */
    @PostMapping
    @ApiOperation(value = "添加工程订单",notes = "添加工程订单",response = EngineeringDto.class)
      public RequestResult addEngineeringOrder(@RequestBody EngineeringDto engineeringDto){
      return this.engineeringOrderFacade.addEngineeringOrder(engineeringDto);
      }


    /**
     * 编辑工程单
     */
    @PutMapping("/{id}")
    @ApiOperation(value = "修改工程订单",notes = "修改工程订单")
    public RequestResult editEngineeringOrder(@PathVariable String id,
                                             @RequestBody MapContext mapContext){
        return this.engineeringOrderFacade.editEngineeringOrder(id,mapContext);
    }

    /**
     * 工程单详情
     */

    @GetMapping("/{id}")
    @ApiOperation(value = "工程订单详情",notes = "工程订单详情")
    public RequestResult engineeringOrderInfo(@PathVariable String id
                                              ){
        return this.engineeringOrderFacade.engineeringOrderInfo(id);
    }

    /**
     * 工程单删除
     */

    @DeleteMapping("/{id}")
    @ApiOperation(value = "工程单删除",notes = "工程单删除")
    public RequestResult deleteEngineeringOrder(@PathVariable String id
    ){
        return this.engineeringOrderFacade.deleteEngineeringOrder(id);
    }

}
