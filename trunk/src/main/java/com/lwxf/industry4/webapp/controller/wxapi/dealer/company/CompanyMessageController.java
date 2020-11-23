package com.lwxf.industry4.webapp.controller.wxapi.dealer.company;

import com.lwxf.commons.json.JsonMapper;
import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.LoginUtil;
import com.lwxf.industry4.webapp.domain.entity.company.CompanyMessage;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.wxapi.dealer.company.CompanyMessagefacade;
import com.lwxf.mybatis.utils.MapContext;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@Api(value="CompanyMessageFacade",tags={"B端微信小程序接口:经销商消息管理"})
@RestController
@RequestMapping(value = "/wxapi/b/companyMessages", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class CompanyMessageController {
    @Resource(name = "wxDealerCompanyMessageFacade")
    private CompanyMessagefacade companyMessageFacade;

    /**
     * 查询聊天信息
     * @return
     */
    @ApiResponse(code = 200, message = "查询成功")
    @ApiOperation(value = "查询聊天信息", notes = "", response = CompanyMessage.class)
    @GetMapping("messageList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyId", value = "经销商ID", dataType = "string", paramType = "query")
    })
    private String findCompanyMessageList(@RequestParam(required = false) String companyId,
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
            return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
        }
        MapContext map = MapContext.newOne();
        map.put("companyId",companyId);
        map.put("branchId",mapInfo.get("branchId"));
        RequestResult result=this.companyMessageFacade.findCompanyMessageList(map,pageNum,pageSize);
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

}
