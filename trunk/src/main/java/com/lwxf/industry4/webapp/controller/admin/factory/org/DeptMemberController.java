package com.lwxf.industry4.webapp.controller.admin.factory.org;

import io.rong.models.response.TokenResult;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import com.lwxf.commons.json.JsonMapper;
import com.lwxf.industry4.webapp.baseservice.rongcloud.RongCloudUtils;
import com.lwxf.industry4.webapp.baseservice.sms.yunpian.SmsUtil;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.dto.UserInfoObj;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.domain.entity.user.User;
import com.lwxf.industry4.webapp.domain.entity.user.UserThirdInfo;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.org.employee.DeptMemberFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/11/011 11:26
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@RestController
@RequestMapping(value = "api/f/depts/{id}/members", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class DeptMemberController {
    @Resource(name = "deptMemberFacade")
    private DeptMemberFacade deptMemberFacade;

    /**
     * 通过部门ID 与 名称 模糊查询员工列表
     *
     * @param id
     * @param name
     * @return
     */
    @GetMapping
    private String findDeptMemberList(@PathVariable String id,
                                      @RequestParam(required = false) String name,
                                      @RequestParam(required = false) String no,
                                      @RequestParam(required = false) String mobile,
                                      @RequestParam(required = false) Integer status,
                                      @RequestParam(required = false) String roleId,
                                      @RequestParam(required = false) Integer pageSize,
                                      @RequestParam(required = false) Integer pageNum) {

        if (null == pageSize) {
            pageSize = AppBeanInjector.configuration.getPageSizeLimit();
        }
        if (null == pageNum) {
            pageNum = 1;
        }
        JsonMapper jsonMapper = new JsonMapper();
        MapContext mapContext = this.createMapContent(name, no, mobile, status, roleId);
        return jsonMapper.toJson(this.deptMemberFacade.findDeptList(id, mapContext, pageNum, pageSize));
    }

    private MapContext createMapContent(String name, String no, String mobile, Integer status, String roleId) {
        MapContext mapContext = MapContext.newOne();
        if (name != null && !name.trim().equals("")) {
            mapContext.put(WebConstant.KEY_ENTITY_NAME, name);
        }
        if (no != null && !no.trim().equals("")) {
            mapContext.put(WebConstant.STRING_NO, no);
        }
        if (mobile != null && !mobile.trim().equals("")) {
            mapContext.put(WebConstant.KEY_ENTITY_MOBILE, mobile);
        }
        if (status == null || status == -1) {
            mapContext.put(WebConstant.KEY_ENTITY_STATUS, null);
        } else {
            mapContext.put(WebConstant.KEY_ENTITY_STATUS, status);
        }
        if (roleId == null || roleId.equals("all")) {
            mapContext.put("roleId", null);
        } else {
            mapContext.put("roleId", roleId);
        }
        return mapContext;
    }

    /**
     * 部门下新增员工
     *
     * @param mapContext
     * @return
     */
    @PostMapping
    private String addDeptMember(@RequestBody MapContext mapContext, @PathVariable String id) {
        JsonMapper jsonMapper = new JsonMapper();
        StringBuffer pwd = new StringBuffer();
        RequestResult requestResult = this.deptMemberFacade.addDeptMember(mapContext, id, pwd);
        //注册成功后给用户发短信
//        if (Integer.parseInt((String) requestResult.get("code")) == (200)) {
//            SmsUtil.sendDealerInfoMessage(mapContext.getTypedValue("mobile", String.class), mapContext.getTypedValue("name", String.class), pwd.toString());
//        }
        //处理融云token
        UserInfoObj data = (UserInfoObj) requestResult.getData();
        User user = null;
        if (data != null) {
            user = data.getUser();
            TokenResult tokenResult = RongCloudUtils.registerUser(user);
            if (tokenResult != null) {
                String token = tokenResult.getToken();
                AppBeanInjector.userThirdInfoFacade.updateRongToken(user.getId(), token);
                UserThirdInfo userThirdInfo = data.getUserThirdInfo();
                userThirdInfo.setRongcloudToken(token);
                return jsonMapper.toJson(ResultFactory.generateRequestResult(AppBeanInjector.deptService.findOneByUserId(user.getId())));
            }
        }
        return jsonMapper.toJson(requestResult);
    }


    @DeleteMapping("{eid}")
    private String deleteDeptMember(@PathVariable String id, @PathVariable String eid) {
        JsonMapper jsonMapper = new JsonMapper();
        return jsonMapper.toJson(this.deptMemberFacade.deleteDeptMember(id, eid));
    }
}
