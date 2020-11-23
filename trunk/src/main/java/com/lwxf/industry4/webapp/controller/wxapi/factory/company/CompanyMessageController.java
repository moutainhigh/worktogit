package com.lwxf.industry4.webapp.controller.wxapi.factory.company;

import com.lwxf.commons.json.JsonMapper;
import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.LoginUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.company.CompanyMessageDto;
import com.lwxf.industry4.webapp.domain.entity.company.Company;
import com.lwxf.industry4.webapp.domain.entity.company.CompanyMessage;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.wxapi.factory.company.CompanyMessageFacade;
import com.lwxf.mybatis.utils.MapContext;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@Api(value="CompanyMessageFacade",tags={"F端微信小程序接口:经销商消息管理"})
@RestController("companyMessageFacade")
@RequestMapping(value = "/wxapi/f/companyMessages", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class CompanyMessageController {

    @Resource(name = "wxCompanyMessageFacade")
    private CompanyMessageFacade companyMessageFacade;

    /**
     * 查询聊天信息
     * @return
     */
    @ApiResponse(code = 200, message = "查询成功")
    @ApiOperation(value = "查询聊天信息列表", notes = "", response = CompanyMessageDto.class)
    @GetMapping("messageList")
    private RequestResult findCompanyMessageList(
                                          @RequestParam(required = false) Integer pageSize,
                                          @RequestParam(required = false) Integer pageNum,
                                          HttpServletRequest request){
        if (null == pageNum) {
            pageNum = 1;
        }
        if (null == pageSize) {
            pageSize = AppBeanInjector.configuration.getPageSizeLimit();
        }
        JsonMapper jsonMapper=new JsonMapper();
        String atoken=request.getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
        if(uid==null){
            return ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
        }
        MapContext map = MapContext.newOne();
        map.put("branchId",mapInfo.get("branchId"));
        RequestResult result=this.companyMessageFacade.findCompanyMessageList(map,pageNum,pageSize);
        return ResultFactory.generateRequestResult(result);
    }

    /**
     * 查询聊天信息
     * @return
     */
    @ApiResponse(code = 200, message = "查询成功")
    @ApiOperation(value = "查询聊天信息详情", notes = "", response = CompanyMessageDto.class)
    @GetMapping("messages/{companyId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyId", value = "经销商ID", dataType = "string", paramType = "path")
    })
    private String findMessages(@PathVariable String companyId,
                                @RequestParam(required = false) Integer pageSize,
                                @RequestParam(required = false) Integer pageNum,
                                HttpServletRequest request){
        JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
        if (null == pageNum) {
            pageNum = 1;
        }
        if (null == pageSize) {
            pageSize = AppBeanInjector.configuration.getPageSizeLimit();
        }
        String atoken=request.getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
        if(uid==null){
            return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
            }
        MapContext map = MapContext.newOne();
        map.put("branchId",mapInfo.get("branchId"));
        map.put("companyId",companyId);
        RequestResult result=this.companyMessageFacade.findMessagesByFromuser(pageNum,pageSize,map);
        return jsonMapper.toJson(result);
    }


    /**
     * 信息发送
     * @param companyMessage 信息实体
     * @return
     */
    @ApiOperation(value="发送信息",notes="发送信息")
    @PostMapping(value = "sendMessage")
    public String sendCompanyMessage(@RequestBody CompanyMessage companyMessage,
                                     HttpServletRequest request){
        JsonMapper jsonMapper=new JsonMapper();
        String atoken=request.getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
        if(uid==null){
            return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
        }
        companyMessage.setCreated(DateUtil.getSystemDate());
        companyMessage.setBranchId(mapInfo.get("branchId").toString());
        return jsonMapper.toJson(this.companyMessageFacade.sendCompanyMessage(companyMessage));
    }

    /**
     * 经销商列表
     * @return
     */
    @ApiOperation(value="经销商列表",notes="经销商列表",response = Company.class)
    @GetMapping(value = "companyList")
    public String getCompanyList(HttpServletRequest request){
        JsonMapper jsonMapper=new JsonMapper();
        String atoken=request.getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
        if(uid==null){
            return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
        }
        return jsonMapper.toJson(this.companyMessageFacade.getCompanyList(mapInfo));
    }
}
