package com.lwxf.industry4.webapp.controller.wxapi.dealer.common;

import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：基础控制器
 *
 * @author：DJL
 * @create：2019/11/29 14:20
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class BaseController {

    /**
     * 验证当前登录用户的信息
     * @param mapInfo 通过token获取的信息集合
     * @return
     */
    protected RequestResult check(MapContext mapInfo) {
        if (null == mapInfo) {
            return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
        }
        String uid = mapInfo.get("userId") == null ? null : mapInfo.get("userId").toString();
        if (null == uid) {
            return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
        }
        String cid = mapInfo.get("companyId") == null ? null : mapInfo.get("companyId").toString();
        if (null == cid) {
            return ResultFactory.generateErrorResult(ErrorCodes.BIZ_IS_NOT_ORG_MEMBER_10004, AppBeanInjector.i18nUtil.getMessage("BIZ_IS_NOT_ORG_MEMBER_10004"));
        }
        String branchId = mapInfo.get("branchId") == null ? null : mapInfo.get("branchId").toString();
        if (null == branchId) {
            return ResultFactory.generateErrorResult(ErrorCodes.BIZ_IS_NOT_ORG_MEMBER_10004, AppBeanInjector.i18nUtil.getMessage("BIZ_IS_NOT_ORG_MEMBER_10004"));
        }

        return null;
    }
}
