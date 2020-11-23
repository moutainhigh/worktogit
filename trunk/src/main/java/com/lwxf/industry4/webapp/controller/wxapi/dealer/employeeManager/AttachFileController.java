package com.lwxf.industry4.webapp.controller.wxapi.dealer.employeeManager;

import com.lwxf.industry4.webapp.bizservice.attachmentFiles.AttachmentFilesService;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.LoginUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.entity.dealerEmployee.DealerEmployee;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.mybatis.utils.MapContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 功能：b端经销商员工管理
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/21/ 14:17
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Api(value = "AttachFileController", tags = {"附件上传通用"})
@RestController
@RequestMapping(value = "/attachFileController", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class AttachFileController {



    @Resource(name = "attachmentFilesService")
    private AttachmentFilesService attachmentFilesService;


    /**
     * 通用附件上传
     *
     * @return
     */
    @PostMapping("/commitfile")
    @ApiOperation(value = "附件上传", notes = "附件上传", response = DealerEmployee.class)
    public RequestResult commitfile(@RequestBody MultipartFile multipartFile) {
        //updateFilesList
        String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
        if(uid==null){
            return ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
        }
        return attachmentFilesService.commitfile(uid, multipartFile);
    }






























}
