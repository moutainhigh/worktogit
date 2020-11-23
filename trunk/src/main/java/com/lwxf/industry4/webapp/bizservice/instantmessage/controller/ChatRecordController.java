package com.lwxf.industry4.webapp.bizservice.instantmessage.controller;

import com.lwxf.commons.uniquekey.IdGererateFactory;
import com.lwxf.industry4.webapp.bizservice.chatrecord.ChatRecordService;
import com.lwxf.industry4.webapp.bizservice.company.CompanyEmployeeService;
import com.lwxf.industry4.webapp.bizservice.dept.DeptMemberService;
import com.lwxf.industry4.webapp.bizservice.dept.DeptService;
import com.lwxf.industry4.webapp.bizservice.system.RolePermissionService;
import com.lwxf.industry4.webapp.common.constant.CommonConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.LoginUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dao.chatrecord.ChatRecordDao;
import com.lwxf.industry4.webapp.domain.dao.company.CompanyDao;
import com.lwxf.industry4.webapp.domain.dao.org.DeptMemberDao;
import com.lwxf.industry4.webapp.domain.dao.user.UserDao;
import com.lwxf.industry4.webapp.domain.dto.companyEmployee.CompanyEmployeeDto;
import com.lwxf.industry4.webapp.domain.dto.user.UserAreaDto;
import com.lwxf.industry4.webapp.domain.entity.chatrecord.ChatRecord;
import com.lwxf.industry4.webapp.domain.entity.company.Company;
import com.lwxf.industry4.webapp.domain.entity.company.CompanyEmployee;
import com.lwxf.industry4.webapp.domain.entity.org.Dept;
import com.lwxf.industry4.webapp.domain.entity.system.RolePermission;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.mybatis.utils.MapContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Description: Im聊天记录
 * @Author: jeecg-boot
 * @Date: 2019-09-18
 * @Version: V1.0
 */
@Api(tags = "IM联系人")
@RestController
@RequestMapping("/chat/chatRecord")
public class ChatRecordController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Resource(name = "companyEmployeeService")
    private CompanyEmployeeService companyEmployeeService;

    @Resource(name = "rolePermissionService")
    private RolePermissionService rolePermissionService;

    @Resource(name = "deptMemberService")
    private DeptMemberService deptMemberService;

    @Resource(name = "deptService")
    private DeptService deptService;

    @Resource(name = "userDao")
    private UserDao userDao;

    @Resource(name = "deptMemberDao")
    private DeptMemberDao deptMemberDao;


    @Resource(name = "companyDao")
    private CompanyDao companyDao;

    /**
     * 获取经销商的联系人列表
     *
     * @return
     */
    @ApiOperation(value = "获取经销商的联系人列表", notes = "获取经销商的联系人列表", response = String.class)
    @GetMapping("/getDealerContactInfo")
    public RequestResult getDealerContactRoleId() {
        String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        String companyId = "";
        String currUserId = "";
        if (atoken == null || atoken.equals("")) {
            currUserId = WebUtils.getCurrUserId();
        } else {
            MapContext mapInfo = LoginUtil.checkLogin(atoken);
            currUserId = mapInfo.get("userId") == null ? null : mapInfo.get("userId").toString();
            companyId = mapInfo.get("companyId") == null ? null : mapInfo.get("companyId").toString();
        }
        //List<Map<String, Object>> lsmap = new ArrayList<>();
        HashMap data = new HashMap();
        UserAreaDto my = userDao.findByUid(currUserId);
        //个人信息
        Map<String, Object> mine = new HashMap<>();
        mine.put("username", my.getName());//用户名
        mine.put("id", my.getId());//用户id
        mine.put("avatar", my.getAvatar());//用户头像 TODO 默认
        mine.put("status", "online");//用户签名 TODO 默认
        mine.put("sign", my.getDeptName());//本人签名 TODO 默认 部门名称
        data.put("mine", mine);




        List<Map<String, Object>> roleinfo = new ArrayList<>();
        //可与经销商联系的分组列表
        //role_permission中的id 对应 role_id
        List<RolePermission> dealermng = rolePermissionService.findByMenusKey("chatRole");//menu_key
        List<UserAreaDto> listUserAreaDto = new ArrayList<>();
        for (RolePermission permission : dealermng) {
            List<CompanyEmployee> listByRoleId = companyEmployeeService.findListByRoleId(permission.getRoleId());

            HashMap<String, Object> hashMap = new HashMap();
            for (CompanyEmployee companyEmployee : listByRoleId) {
                String userId = companyEmployee.getUserId();
                UserAreaDto byUid = userDao.findByUid(userId);
                List<String> deptNameByEmployeeId = deptMemberDao.findDeptNameByEmployeeId(companyEmployee.getId());
                if (deptNameByEmployeeId != null && deptNameByEmployeeId.size() != 0) {
                    String deptName = deptNameByEmployeeId.get(0);
                    byUid.setDeptName(deptName);
                    listUserAreaDto.add(byUid);
                }
            }
            hashMap.put("listByRoleId", listByRoleId);
            roleinfo.add(hashMap);
        }
        return ResultFactory.generateRequestResult(listUserAreaDto);
    }

    /**
     * 获取工厂的联系人列表
     *
     * @return
     */
    @ApiOperation(value = "获取工厂的联系人列表", notes = "获取工厂的联系人列表", response = String.class)
    @GetMapping("/getFactoryContactInfo")
    public RequestResult getFactoryContactInfo() {
        String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        String companyId = "";
        String currUserId = "";
        if (atoken == null || atoken.equals("")) {
            currUserId = WebUtils.getCurrUserId();
        } else {
            MapContext mapInfo = LoginUtil.checkLogin(atoken);
            currUserId = mapInfo.get("userId") == null ? null : mapInfo.get("userId").toString();
            companyId = mapInfo.get("companyId") == null ? null : mapInfo.get("companyId").toString();
        }
        if (currUserId == null || currUserId.equals("")) {
            return ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
        }
        HashMap data = new HashMap();
        UserAreaDto byUid = userDao.findByUid(currUserId);
        //个人信息
        Map<String, Object> mine = new HashMap<>();
        mine.put("username", byUid.getName());//用户名
        mine.put("id", byUid.getId());//用户id
        mine.put("avatar", byUid.getAvatar());//用户头像 TODO 默认
        mine.put("status", "online");//用户签名 TODO 默认
        mine.put("sign", byUid.getDeptName());//本人签名 TODO 默认 部门名称
        data.put("mine", mine);
        //公司联系人信息
        List<Dept> listByCompanyId = deptService.findListByCompanyId(companyId);
        ArrayList<HashMap<String, Object>> hashMaps = new ArrayList<>();
        for (Dept dept : listByCompanyId) {
            //群组信息
            HashMap<String, Object> objectObjectHashMap = new HashMap<>();
            objectObjectHashMap.put("groupname",dept.getName());
            objectObjectHashMap.put("id",dept.getId());
            //群组成员信息
            ArrayList<Map<String, Object>> friends = new ArrayList<>();
            List<CompanyEmployeeDto> employeeDtoByDeptId = deptMemberDao.findEmployeeDtoByDeptId(dept.getId());
            for (CompanyEmployeeDto companyEmployeeDto:employeeDtoByDeptId ){
                HashMap<String, Object> objectObjectHashMap1 = new HashMap<>();
                objectObjectHashMap1.put("username",companyEmployeeDto.getUserName());
                String userId = companyEmployeeDto.getUserId();
                UserAreaDto byUid1 = userDao.findByUid(userId);
                if(byUid1==null){
                    continue;
                }
                objectObjectHashMap1.put("id",companyEmployeeDto.getUserId());
                objectObjectHashMap1.put("avatar",byUid1.getAvatar());
                objectObjectHashMap1.put("sign",byUid1.getDeptName());
                objectObjectHashMap1.put("status","online");
                friends.add(objectObjectHashMap1);
            }
            objectObjectHashMap.put("list",friends);
            hashMaps.add(objectObjectHashMap);
        }

        //查询全部经销商放一个群组
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("groupname","经销商");
        objectObjectHashMap.put("id","123");
        List<Company> dealers = companyDao.findCompanyByTypes();
        ArrayList<Map<String, Object>> dealList = new ArrayList<>();
        for (Company company:dealers){
            HashMap<String, Object> stringObjectHashMap = new HashMap<>();
            stringObjectHashMap.put("id",company.getLeader());
            String leaderUid = company.getLeader();
            UserAreaDto byUid1 = userDao.findByUid(leaderUid);
            if(byUid1==null){
                continue;
            }
            stringObjectHashMap.put("avatar",byUid1.getAvatar());
            stringObjectHashMap.put("sign",byUid1.getDeptName());
            stringObjectHashMap.put("status","online");
            stringObjectHashMap.put("username",company.getName());
            dealList.add(stringObjectHashMap);
        }
        objectObjectHashMap.put("list",dealList);
        hashMaps.add(objectObjectHashMap);
        data.put("friend",hashMaps);
        return ResultFactory.generateRequestResult(data);
    }


    @Resource(name = "chatRecordService")
    private ChatRecordService chatRecordService;

    @Resource
    private IdGererateFactory idGererateFactory;

    /**
     * 获取工厂的联系人列表
     *
     * @return
     */
    @ApiOperation(value = "获取当前用户id", notes = "获取当前用户id", response = String.class)
    @GetMapping("/getMyInfo")
    public RequestResult getMyInfo() {
        String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        String currUserId = "";
        if (atoken == null || atoken.equals("")) {
            currUserId = WebUtils.getCurrUserId();
        } else {
            MapContext mapInfo = LoginUtil.checkLogin(atoken);
            currUserId = mapInfo.get("userId") == null ? null : mapInfo.get("userId").toString();
        }
        if (currUserId == null || currUserId.equals("")) {
            return ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
        }
        ChatRecord chatRecord = new ChatRecord();
        chatRecord.setChatType(CommonConstant.CHAT_STR);//聊天记录类型：1.字符串2.文件路径
        chatRecord.setChatTime(new Date());//聊天插入记录时间
        chatRecord.setChatUsername("");//保存汉字姓名
        chatRecord.setChatDepatId("");//保存部门名称
        chatRecord.setChatContent("");//聊天内容：文字为字符串，图片文件为地址
        chatRecordService.add(chatRecord);

        return ResultFactory.generateRequestResult(currUserId);
    }



















}
