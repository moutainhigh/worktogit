package com.lwxf.industry4.webapp.controller.admin.factory.statement;

import com.lwxf.commons.json.JsonMapper;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.facade.admin.factory.statement.HomePageStatementFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2020-03-12 15:34
 * @version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Api(value="HomePageStatementController",tags={"F端后台管理接口:首页报表接口"})
@RestController("HomePageStatementController")
@RequestMapping(value = "/api/f/",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class HomePageStatementController {
    @Resource(name = "homePageStatementFacade")
    private HomePageStatementFacade homePageStatementFacade;

    @GetMapping("homePageStatement")
    @ApiOperation(value = "首页报表接口",notes = "首页报表接口")
    private String findHomePageStatement(@RequestParam(required = false)@ApiParam(value = "开始时间") String startTime,
                                         @RequestParam(required = false)@ApiParam(value = "结束时间") String endTime,
                                         @RequestParam(required = false)@ApiParam(value = "1-本月，2-本年") String type){
        JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
        RequestResult result=this.homePageStatementFacade.findHomePageStatement(startTime,endTime,type);
        return jsonMapper.toJson(result);

    }
}
