package com.lwxf.industry4.webapp.controller.admin.factory.system;

import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.system.SysException;
import com.lwxf.industry4.webapp.facade.admin.factory.system.SysExceptionFacade;
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
@Api(value="SyslogController",tags={"F端后台管理接口:系统异常接口"})
@RequestMapping(value = "/api/f/sysExceptions",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class SysExceptionController {

    @Resource(name = "sysExceptionFacade")
    private SysExceptionFacade sysExceptionFacade;

    @GetMapping
    @ApiOperation(value = "查询系统异常日志", notes = "", response = SysException.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "method", value = "Get or Post", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "beginDate", value = "开始时间", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "结束时间", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "url", value = "接口名称", dataType = "string", paramType = "query")
    })
    public RequestResult syslogList(@RequestParam(required = false) Integer pageNum,
                                    @RequestParam(required = false) Integer pageSize,
                                    @RequestParam(required = false)String url,
                                    @RequestParam(required = false)String beginDate,
                                    @RequestParam(required = false)String endDate,
                                    @RequestParam(required = false)String postType){
        if(null == pageSize){
            pageSize = 10;
        }
        if(null == pageNum){
            pageNum = 1;
        }
        MapContext map = MapContext.newOne();
        if(url!=null &&!url.equals("")){
            map.put("url",url);
        }
        if(postType!=null &&!postType.equals("")){
            map.put("postType",postType);
        }
        if(beginDate!=null &&!beginDate.equals("")){
            map.put("beginDate",beginDate);
        }
        if(endDate!=null &&!endDate.equals("")){
            map.put("endDate",endDate);
        }


        return  this.sysExceptionFacade.selectSysExceptionList(pageNum,pageSize,map);
    }
}
