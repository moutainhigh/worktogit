package com.lwxf.industry4.webapp.facade.common.impl;

import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.utils.DateUtil;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.baseservice.sms.yunpian.SmsUtil;
import com.lwxf.industry4.webapp.baseservice.sms.yunpian.VerificationCodeType;
import com.lwxf.industry4.webapp.bizservice.company.CompanyEmployeeService;
import com.lwxf.industry4.webapp.bizservice.company.CompanyService;
import com.lwxf.industry4.webapp.bizservice.system.RolePermissionService;
import com.lwxf.industry4.webapp.bizservice.system.RoleService;
import com.lwxf.industry4.webapp.bizservice.user.UserService;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.company.CompanyStatus;
import com.lwxf.industry4.webapp.common.enums.company.EmployeeStatus;
import com.lwxf.industry4.webapp.common.enums.user.UserState;
import com.lwxf.industry4.webapp.common.enums.user.UserType;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.entity.company.Company;
import com.lwxf.industry4.webapp.domain.entity.company.CompanyEmployee;
import com.lwxf.industry4.webapp.domain.entity.company.EmployeePermission;
import com.lwxf.industry4.webapp.domain.entity.system.Role;
import com.lwxf.industry4.webapp.domain.entity.system.RolePermission;
import com.lwxf.industry4.webapp.domain.entity.user.User;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.common.UserLoginFacade;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能：
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @create：2019/3/29 9:31
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("userLoginFacade")
public class UserLoginFacadeImpl extends BaseFacadeImpl implements UserLoginFacade {

    @Resource(name = "userService")
    private UserService userService;
    @Resource(name = "companyEmployeeService")
    private CompanyEmployeeService companyEmployeeService;
    @Resource(name = "companyService")
    private CompanyService companyService;
    @Resource(name = "roleService")
    private RoleService roleService;
    @Resource(name = "rolePermissionService")
    private RolePermissionService rolePermissionService;



    @Override
    @Transactional(value = "transactionManager")
    public RequestResult userLogin(MapContext userMap) {
        Map<String, String> errorMap = new HashMap<>();
        String loginName = userMap.getTypedValue(WebConstant.KEY_ENTITY_LOGINNAME, String.class);
        //页面短信验证码
        String mobileCode = userMap.getTypedValue("smsCode", String.class);
        //从缓存中取短信验证码
        String serverMobileCode = SmsUtil.getMobileVerificationCode(loginName, VerificationCodeType.LOGIN);
        if (serverMobileCode == null || !serverMobileCode.equals(mobileCode)) {
            errorMap.put("smsCode", AppBeanInjector.i18nUtil.getMessage("VALIDATE_AUTHCODE_ERROR_20024"));
            return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, errorMap);
        }
        User user = AppBeanInjector.userService.findByMobile(loginName);
        if (user == null) {
            return ResultFactory.generateErrorResult(ErrorCodes.LOGIN_FAIL_90000, AppBeanInjector.i18nUtil.getMessage("LOGIN_FAIL_90000"));
        }
        //判断用户是否被禁用
        if (user.getState().intValue() == UserState.DISABLED.getValue()) {
            return ResultFactory.generateErrorResult(ErrorCodes.SYS_ERROR_USER_ISDISABLED_00019, AppBeanInjector.i18nUtil.getMessage("SYS_ERROR_USER_ISDISABLED_00019"));
        }

        //判断B端的员工是否被禁用
        CompanyEmployee employee = this.companyEmployeeService.findCompanyByUidAndStatus(user.getId(),EmployeeStatus.NORMAL.getValue());
        if (null==employee){
            return ResultFactory.generateErrorResult(ErrorCodes.SYS_ERROR_USER_ISDISABLED_00019, AppBeanInjector.i18nUtil.getMessage("SYS_ERROR_USER_ISDISABLED_00019"));
        }

        String companyId = employee.getCompanyId();
        Company company = this.companyService.findById(companyId);
        //判断经销商的公司是否被禁用
        if (company.getStatus().intValue()!=CompanyStatus.NORMAL.getValue()){
            return ResultFactory.generateErrorResult(ErrorCodes.SYS_ERROR_USER_ISDISABLED_00019, AppBeanInjector.i18nUtil.getMessage("SYS_ERROR_USER_ISDISABLED_00019"));
        }


        String userId = user.getId();
        //更新用户最后登录时间
        MapContext update = MapContext.newOne();
        update.put(WebConstant.KEY_ENTITY_ID, userId);
        update.put(WebConstant.KEY_ENTITY_LASTLOGIN, DateUtil.getSystemDate());
        userService.updateByMapContext(update);
        Role role = this.roleService.findRoleByCidAndUid(userId, companyId);
        String name = null;
        if(null!=role){
            name= role.getName();
        }

        List<RolePermission> listByEmployeeId = this.rolePermissionService.selectRolePermissionList(employee.getRoleId());

        List<String> permissionList = new ArrayList<String>();
        for (RolePermission permission :listByEmployeeId){
            String menuKey = permission.getMenuKey();
            String moduleKey = permission.getModuleKey();
            String operations = permission.getOperations();
            String[] split = operations.split(",");
            for (int i=0;i<split.length;i++) {
                String permissionsValid = moduleKey + "-" + menuKey + "-" + split[i];
                permissionList.add(permissionsValid);
            }
        }
        MapContext result = MapContext.newOne();
        //判断用户是B端的员工、还是工厂的员工//TODO 显示对应的F端人员的首页信息
        if (user.getType().intValue()==UserType.FACTORY.getValue()){
            result.put("userName", user.getName());
            result.put("avatar", user.getAvatar());
            result.put("mobile", user.getMobile());
            result.put("id", userId);
            result.put("type", user.getType());//类型 0F 1B 2C
            result.put("appToken", AppBeanInjector.userThirdInfoService.findByUserId(userId).getAppToken());
            result.put("companyId",companyId);
            result.put("domainUrl",WebUtils.getDomainUrl());
            result.put("role",name);
            result.put("permissionList",permissionList);
            return ResultFactory.generateRequestResult(result);

        }else {
            result.put("userName", user.getName());
            result.put("avatar", user.getAvatar());
            result.put("mobile", user.getMobile());
            result.put("id", userId);
            result.put("type", user.getType());//类型 0F 1B 2C
            result.put("appToken", AppBeanInjector.userThirdInfoService.findByUserId(userId).getAppToken());
            result.put("companyId",companyId);
            result.put("domainUrl",WebUtils.getDomainUrl());
            result.put("role",name);
            result.put("permissionList",permissionList);
            return ResultFactory.generateRequestResult(result);
        }

    }


    /**
     * 用户密码登录
     *
     * @param userMap
     * @return
     */
    @Override
    @Transactional(value = "transactionManager")
    public RequestResult userPasswordLogin(MapContext userMap) {
        String loginName = userMap.getTypedValue(WebConstant.KEY_ENTITY_LOGINNAME, String.class);
        String password = userMap.getTypedValue(WebConstant.KEY_USER_EXTRA_PASSWORD, String.class);
        if (LwxfStringUtils.isBlank(loginName) || LwxfStringUtils.isBlank(password)) {
            return ResultFactory.generateErrorResult(ErrorCodes.LOGIN_FAIL_90000, AppBeanInjector.i18nUtil.getMessage("LOGIN_FAIL_90000"));
        }

        User user = AppBeanInjector.userService.findByMobile(loginName);
        if (null == user) {
            user = AppBeanInjector.userService.findByLoginName(loginName);
        }
        if (null == user) {
            user = AppBeanInjector.userService.findByEmail(loginName);
        }
        if (user == null) {
            return ResultFactory.generateErrorResult(ErrorCodes.LOGIN_FAIL_90000, AppBeanInjector.i18nUtil.getMessage("LOGIN_FAIL_90000"));
        }

        // 密码验证不通过
        if (!AppBeanInjector.userExtraService.isPasswordCorrect(user.getId(), password)) {
            return ResultFactory.generateErrorResult(ErrorCodes.LOGIN_FAIL_90000, AppBeanInjector.i18nUtil.getMessage("LOGIN_FAIL_90000"));
        }
        //判断用户是否被禁用
        if (user.getState().intValue() == UserState.DISABLED.getValue()) {
            return ResultFactory.generateErrorResult(ErrorCodes.SYS_ERROR_USER_ISDISABLED_00019, AppBeanInjector.i18nUtil.getMessage("SYS_ERROR_USER_ISDISABLED_00019"));
        }
        //判断B端的员工是否被禁用
        CompanyEmployee employee = this.companyEmployeeService.findCompanyByUidAndStatus(user.getId(),EmployeeStatus.NORMAL.getValue());
        if (null==employee){
            return ResultFactory.generateErrorResult(ErrorCodes.SYS_ERROR_USER_ISDISABLED_00019, AppBeanInjector.i18nUtil.getMessage("SYS_ERROR_USER_ISDISABLED_00019"));
        }
        String companyId = employee.getCompanyId();
        Company company = this.companyService.findById(companyId);
        //判断经销商的公司是否被禁用
        if (company.getStatus().intValue()!=CompanyStatus.NORMAL.getValue()){
            return ResultFactory.generateErrorResult(ErrorCodes.SYS_ERROR_USER_ISDISABLED_00019, AppBeanInjector.i18nUtil.getMessage("SYS_ERROR_USER_ISDISABLED_00019"));
        }

        String userId = user.getId();
        //更新用户最后登录时间
        MapContext update = MapContext.newOne();
        update.put(WebConstant.KEY_ENTITY_ID, userId);
        update.put(WebConstant.KEY_ENTITY_LASTLOGIN, DateUtil.getSystemDate());
        userService.updateByMapContext(update);
        Role role = this.roleService.findRoleByCidAndUid(userId, companyId);
        String name = null;
        if(null!=role){
            name= role.getName();
        }
        List<RolePermission> listByEmployeeId = this.rolePermissionService.selectRolePermissionList(employee.getRoleId());

        List<String> permissionList = new ArrayList<String>();
        for (RolePermission permission :listByEmployeeId){
            String menuKey = permission.getMenuKey();//菜单key
            String moduleKey = permission.getModuleKey();//资源key
            String operations = permission.getOperations();//操作keys
            String[] split = operations.split(",");
            for (int i=0;i<split.length;i++) {
                String permissionsValid = moduleKey + "-" + menuKey + "-" + split[i];
                permissionList.add(permissionsValid);
            }
        }
        MapContext result = MapContext.newOne();
        //判断用户是B端的员工、还是工厂的员工//TODO 显示对应的F端人员的首页信息
        if (user.getType().intValue()==UserType.FACTORY.getValue()){
            result.put("userName", user.getName());
            result.put("avatar", user.getAvatar());
            result.put("mobile", user.getMobile());
            result.put("id", userId);
            result.put("type", user.getType());//类型 0F 1B 2C
            result.put("appToken", AppBeanInjector.userThirdInfoService.findByUserId(userId).getAppToken());
            result.put("companyId",companyId);
            result.put("domainUrl",WebUtils.getDomainUrl());
            result.put("role",name);
            result.put("permissionList",permissionList);
            return ResultFactory.generateRequestResult(result);

        }else {
            result.put("userName", user.getName());
            result.put("avatar", user.getAvatar());
            result.put("mobile", user.getMobile());
            result.put("id", userId);
            result.put("type", user.getType());//类型 0F 1B 2C
            result.put("appToken", AppBeanInjector.userThirdInfoService.findByUserId(userId).getAppToken());
            result.put("companyId",companyId);
            result.put("domainUrl",WebUtils.getDomainUrl());
            result.put("role",name);
            result.put("permissionList",permissionList);
            return ResultFactory.generateRequestResult(result);
        }
    }
}
