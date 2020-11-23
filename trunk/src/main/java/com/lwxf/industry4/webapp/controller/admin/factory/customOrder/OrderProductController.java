package com.lwxf.industry4.webapp.controller.admin.factory.customOrder;


import com.lwxf.commons.json.JsonMapper;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.order.OrderProductStatus;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.utils.excel.impl.DispatchBillItemParam;
import com.lwxf.industry4.webapp.common.utils.excel.impl.OrderProductExcelParam;
import com.lwxf.industry4.webapp.domain.dto.warehouse.FinishedStockItemDto;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.order.OrderProductFacade;
import com.lwxf.mybatis.utils.MapContext;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/f/orderProduct",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
@Api(value = "OrderProductController",tags = {"F端后台管理接口：订单产品管理"})
public class OrderProductController {

    @Resource(name = "orderProductFacade")
    private OrderProductFacade orderProductFacade;

    /**
     * 查询成品库包裹列表
     * @return
     */
    @ApiResponses({
            @ApiResponse(code = 200,message = "查询成功",response = FinishedStockItemDto.class)
    })
    @ApiOperation(value = "查询产品列表",notes = "查询产品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "订单编号",name = "orderNo",dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "经销商名称",name = "companyName",dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "经销商地址",name = "dealerAddress",dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "客户地址",name = "customAddress",dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "状态",name = "status",dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "生产单类型：0-柜体 1-门板 2-五金",name = "produceType",dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "入库时间起",name = "beginStockInputDate",dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "入库时间止",name = "endStockInputDate",dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "下单时间起",name = "beginPayDate",dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "下单时间止",name = "endPayDate",dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "成品管理顶部统计",name = "finishedTop",dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "页数",name = "pageSize",dataType = "int", paramType = "query"),
            @ApiImplicitParam(value = "页码",name = "pageNum",dataType = "int",paramType = "query"),
            @ApiImplicitParam(value = "所属资源 成品管理传0 ，发货管理传1",name = "resources",dataType = "string",paramType = "query"),
            @ApiImplicitParam(value = "查询当天的发货计划单明细 传-1",name = "planInfo",dataType = "string",paramType = "query")
    })
    @GetMapping
    private String findProductDto(@RequestParam(required = false) String orderNo,
                                  @RequestParam(required = false) String companyName,
                                  @RequestParam(required = false) String customAddress,
                                  @RequestParam(required = false) String dealerAddress,
                                  @RequestParam(required = false) String status,
                                  @RequestParam(required = false) String produceType,
                                  @RequestParam(required = false) String logisticsNo,
                                  @RequestParam(required = false) String beginStockInputDate,
                                  @RequestParam(required = false) String endStockInputDate,
                                  @RequestParam(required = false) String beginPayDate,
                                  @RequestParam(required = false) String endPayDate,
                                  @RequestParam(required = false) String finishedTop,
                                  @RequestParam(required = false) Integer pageNum,
                                  @RequestParam(required = false) Integer pageSize,
                                  @RequestParam(required = false) String resources,
                                  @RequestParam(required = false) String planInfo
                                  ){
        if(null == pageSize){
            pageSize = AppBeanInjector.configuration.getPageSizeLimit();
        }
        if(null == pageNum){
            pageNum = 1;
        }
        MapContext mapContext = this.createMapContentDto(orderNo,companyName,customAddress,dealerAddress,beginStockInputDate,endStockInputDate,beginPayDate,endPayDate,status,logisticsNo,finishedTop,resources,produceType,planInfo);
        JsonMapper jsonMapper = JsonMapper.createAllToStringMapper();
        return jsonMapper.toJson(this.orderProductFacade.findOrderProductList(pageNum,pageSize,mapContext));
    }

    /**
     * 导出成品库包裹列表
     * @return
     */
    @ApiOperation(value = "导出成品库包裹列表",notes = "导出成品库包裹列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "订单编号",name = "orderNo",dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "经销商名称",name = "companyName",dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "经销商地址",name = "dealerAddress",dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "客户地址",name = "customAddress",dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "状态",name = "status",dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "生产单类型：0-柜体 1-门板 2-五金",name = "produceType",dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "入库时间起",name = "beginStockInputDate",dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "入库时间止",name = "endStockInputDate",dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "下单时间起",name = "beginPayDate",dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "下单时间止",name = "endPayDate",dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "成品管理顶部统计",name = "finishedTop",dataType = "string", paramType = "query"),
            @ApiImplicitParam(value = "所属资源 成品管理传0 ，发货管理传1",name = "resources",dataType = "string",paramType = "query")
    })
    @GetMapping("/export")
    public RequestResult exportProductDto(@RequestParam(required = false) String orderNo,
                                           @RequestParam(required = false) String companyName,
                                           @RequestParam(required = false) String customAddress,
                                           @RequestParam(required = false) String dealerAddress,
                                           @RequestParam(required = false) String status,
                                           @RequestParam(required = false) String produceType,
                                           @RequestParam(required = false) String logisticsNo,
                                           @RequestParam(required = false) String beginStockInputDate,
                                           @RequestParam(required = false) String endStockInputDate,
                                           @RequestParam(required = false) String beginPayDate,
                                           @RequestParam(required = false) String endPayDate,
                                           @RequestParam(required = false) String finishedTop,
                                           @RequestParam(required = false) String resources){
        Integer pageNum = 1;
        Integer pageSize = 1000; // 最多允许导出1000条数据
        MapContext mapContext = this.createMapContentDto(orderNo,companyName,customAddress,dealerAddress,beginStockInputDate,endStockInputDate,beginPayDate,endPayDate,status,logisticsNo,finishedTop,resources,produceType,null);
        return this.orderProductFacade.writeExcel(pageNum, pageSize, mapContext, new OrderProductExcelParam());
    }

    /**
     * 根据状态值统计产品总数
     */
    @ApiOperation(value = "根据状态值统计产品总数",notes = "状态： 0-待付款、1-待生产、2-生产中、3-已入库、4-待发货、5-已发货")
    @GetMapping("/countByProductStatus/{status}")
    private String countByProductStatus(@PathVariable @ApiParam(value = "状态值") Integer status){
        JsonMapper jsonMapper = new JsonMapper();
        return jsonMapper.toJson(this.orderProductFacade.countByProductStatus(status));
    }
    /**
     * 门板 ，柜体，五金未入库数
     */
    @ApiOperation(value = "门板 ，柜体，五金未入库数",notes = "门板 ，柜体，五金未入库数")
    @GetMapping("/noInstock")
    private String countPartStock(){
        JsonMapper jsonMapper = new JsonMapper();
        return jsonMapper.toJson(this.orderProductFacade.countPartStock());
    }


    /**
     * 更新产品信息
     */
    @ApiOperation(value = "更新产品信息",notes = "更新产品信息")
    @PutMapping("/{productId}")
    private String updateOrderProduct(@PathVariable @ApiParam(value = "产品id") String productId,@RequestBody MapContext map){
        JsonMapper jsonMapper = new JsonMapper();
        return jsonMapper.toJson(this.orderProductFacade.updateOrderProduct(productId,map));
    }
    /**
     * 产品发货
     */
    @ApiOperation(value = "产品发货",notes = "产品发货")
    @PutMapping("/delievery")
    private String updateDeliveryInfo(
                                      @RequestBody MapContext mapContext){
        JsonMapper jsonMapper = new JsonMapper();
        return jsonMapper.toJson(this.orderProductFacade.updateDeliveryInfo(mapContext));
    }
    /**
     * 产品系列接口
     */
    @ApiOperation(value = "产品系列接口",notes = "产品系列接口")
    @GetMapping("/products/series")
    private String findProductSeries(){
        JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
        return jsonMapper.toJson(this.orderProductFacade.findProductSeries());
    }

    /**
     *
     * @param orderNo
     * @param companyName
     * @param customAddress
     * @param dealerAddress
     * @param beginStockInputDate
     * @param endStockInputDate
     * @param beginPayDate
     * @param endPayDate
     * @param status
     * @param logisticsNo
     * @return
     */
    private MapContext createMapContentDto(String orderNo, String companyName,String customAddress,String dealerAddress,String beginStockInputDate,String endStockInputDate,String beginPayDate,String endPayDate,String status,String logisticsNo,String finishedTop,String resources,String produceType,String planInfo) {
        MapContext mapContext = new MapContext();
        if(orderNo!=null&&!orderNo.trim().equals("")){
            mapContext.put("no",orderNo);
        }
        if(customAddress!=null&&!customAddress.trim().equals("")){
            mapContext.put("customAddress",customAddress);
        }
        if(resources!=null&&!resources.trim().equals("")){
            mapContext.put("resources",resources);
        }
        if(dealerAddress!=null&&!dealerAddress.trim().equals("")){
            mapContext.put("dealerAddress",dealerAddress);
        }
        if(companyName!=null){
            mapContext.put("companyName",companyName);
        }
        if(status!=null){
            mapContext.put("status",status);
        }
        if(beginStockInputDate!=null){
            mapContext.put("beginStockInputTime",beginStockInputDate);
        }
        if(endStockInputDate!=null){
            mapContext.put("endStockInputTime",endStockInputDate);
        }
        if(beginPayDate!=null){
            mapContext.put("beginPayTime",beginPayDate);
        }
        if(endPayDate!=null){
            mapContext.put("endPayTime",endPayDate);
        }
        if(finishedTop!=null){
            mapContext.put("finishedTop",finishedTop);
        }
        if(logisticsNo!=null){
            mapContext.put("logisticsNo",logisticsNo);
        }
        if(produceType!=null){
            mapContext.put("produceType",produceType);
        }
        if(planInfo!=null){
            mapContext.put("planInfo",planInfo);
        }
        return mapContext;
    }
}
