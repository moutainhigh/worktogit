package com.lwxf.industry4.webapp.controller.wxapi.factory.statement;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lwxf.commons.json.JsonMapper;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.LoginUtil;
import com.lwxf.industry4.webapp.domain.dto.statement.CountByWeekDto;
import com.lwxf.industry4.webapp.domain.dto.statement.WxFactoryStatementDto;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.wxapi.factory.statements.StatementFacade;
import com.lwxf.mybatis.utils.MapContext;


@Api(value="StatementController",tags={"F端微信小程序接口:报表接口"})
@RestController("StatementController")
@RequestMapping(value = "/wxapi/f/",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class StatementController {

    @Resource(name = "wxStatementFacade")
    private StatementFacade statementFacade;

    /**
     * 查看报表数据
     * @return
     */
    @ApiResponse(code = 200, message = "查询成功")
    @ApiOperation(value="报表数据",notes="报表数据",response = WxFactoryStatementDto.class)
    @GetMapping("/statements")
    private String viewStatement(HttpServletRequest request){
        JsonMapper jsonMapper=new JsonMapper();
        String atoken=request.getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
        String branchId =mapInfo.get("branchId")==null?null:mapInfo.get("branchId").toString();
        if(uid==null){
            return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
        }
        RequestResult result=this.statementFacade.viewStatement(branchId);
        return jsonMapper.toJson(result);
    }



}
