package com.lwxf.industry4.webapp.controller.admin.factory.system;

import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.system.SysLog;
import com.lwxf.industry4.webapp.facade.admin.factory.system.SysLogFacade;
import com.lwxf.mybatis.utils.MapContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(value="SyslogController",tags={"F端后台管理接口:操作日志管理接口"})
@RequestMapping(value = "/api/f/syslog",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class SyslogController {
    @Resource(name = "sysLogFacade")
    private SysLogFacade sysLogFacade;

    @GetMapping
    @ApiOperation(value = "查询模块操作日志", notes = "", response = SysLog.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mouduleCode", value = "supplier:供应商\n" +
                    "supplier_product:供应商产品\n" +
                    "payment:财务审核/经销商财务\n" +
                    "company:经销商公司\n" +
                    "custom_order:订单\n" +
                    "finance:审核\n" +
                    "design:设计\n" +
                    "produce:生产单\n" +
                    "finished_stock_item:成品\n" +
                    "dispatch:发货\n" +
                    "aftersale_apply:售后单\n" +
                    "contents:内容\n" +
                    "material:原材料\n" +
                    "company_customer:终端客户\n" +
                    "corporate_partners:外协厂家\n" +
                    "corporate_order:外协订单\n" +
                    "payment_simple:日常账管理", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "操作类型：insert:插入，update：更新，delete:删除，verify:审核", dataType = "string", paramType = "query")
    })
    public RequestResult syslogList(@RequestParam(required = false) Integer pageNum,
                                         @RequestParam(required = false) Integer pageSize,
                                         @RequestParam(required = false)String mouduleCode,
                                         @RequestParam(required = false)String type){
        if(null == pageSize){
            pageSize = 10;
        }
        if(null == pageNum){
            pageNum = 1;
        }
        MapContext map = MapContext.newOne();
        if(mouduleCode!=null &&!mouduleCode.equals("")){
            map.put("mouduleCode",mouduleCode);
        }
        if(type!=null &&!type.equals("")){
            map.put("operationType",type);
        }


        return  this.sysLogFacade.selectSysLogList(pageNum,pageSize,map);
    }
}
