package com.lwxf.industry4.webapp.controller.admin.factory.common;

import com.lwxf.commons.json.JsonMapper;
import com.lwxf.industry4.webapp.bizservice.customorder.CustomOrderTimeService;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderTime;
import com.lwxf.industry4.webapp.domain.entity.system.Basecode;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.order.CustomOrderTimeFacade;
import com.lwxf.mybatis.utils.MapContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/api/f/producetime",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
@Api(value = "生产周期配置",tags = "生产周期配置")
public class OrderProduceTimeController {
    @Resource(name="customOrderTimeFacade")
    private CustomOrderTimeFacade customOrderTimeFacade;

    @GetMapping
    @ApiOperation(value = "生产周期配置列表",notes = "生产周期配置列表",response = CustomOrderTime.class)
    private String selectCustoOrderTime(@RequestParam(required = false)@ApiParam(value = "产品名称") String productName,
                                        @RequestParam(required = false)@ApiParam(value = "部件名称") String produceName,
                                        @RequestParam(required = false)@ApiParam(value = "系列名称") String seriesName){
        JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
        MapContext mapContext=MapContext.newOne();
        mapContext.put("branchId",WebUtils.getCurrBranchId());
        if(productName!=null&&!productName.trim().equals("")){
            mapContext.put("productName",productName);
        }
        if(produceName!=null&&!produceName.trim().equals("")){
            mapContext.put("produceName",produceName);
        }
        if(seriesName!=null&&!seriesName.trim().equals("")){
            mapContext.put("seriesName",seriesName);
        }
        return jsonMapper.toJson(this.customOrderTimeFacade.selectCustoOrderTime(mapContext));
    }
    @PostMapping
    @ApiOperation(value = "添加生产周期配置",notes = "添加生产周期配置",response = CustomOrderTime.class)
    private String addCustoOrderTime(@RequestBody MapContext mapContext){
        JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
        return jsonMapper.toJson(this.customOrderTimeFacade.addCustoOrderTime(mapContext));
    }
    @PutMapping
    @ApiOperation(value = "修改生产周期配置",notes = "修改生产周期配置",response = CustomOrderTime.class)
    private String editCustoOrderTime(@RequestBody MapContext mapContext){
        JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
        return jsonMapper.toJson(this.customOrderTimeFacade.editCustoOrderTime(mapContext));
    }
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除生产周期配置",notes = "删除生产周期配置",response = CustomOrderTime.class)
    private RequestResult deleteCustoOrderTime(@PathVariable String id){
        JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
        return this.customOrderTimeFacade.deleteCustoOrderTime(id);
    }

    @GetMapping("/ordertime")
    @ApiOperation(value = "订单工期详情列表",notes = "订单工期详情列表")
    private String findOrderTimelist(@RequestParam(required = false)String orderNo,
                                     @RequestParam(required = false)Integer pageSize,
                                     @RequestParam(required = false)Integer pageNum
                                     ){
        JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
        MapContext mapContext=MapContext.newOne();
        if(pageSize==null||pageSize.equals("")){
            pageSize = AppBeanInjector.configuration.getPageSizeLimit();
        }
        if(pageNum==null||pageSize.equals("")){
            pageNum=1;
        }
        if(orderNo!=null&&!orderNo.equals("")){
            mapContext.put("no",orderNo);
        }
        mapContext.put("branchId",WebUtils.getCurrBranchId());

        RequestResult result=this.customOrderTimeFacade.findOrderTimelist(mapContext,pageNum,pageSize);
        return jsonMapper.toJson(result);
    }
}
