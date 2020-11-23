package com.lwxf.industry4.webapp.facade.smallProgram.smallProgramDept.impl;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import org.apache.shiro.crypto.hash.Md5Hash;

import com.lwxf.commons.uniquekey.IIdGenerator;
import com.lwxf.commons.uniquekey.LwxfWorkerIdGenerator;
import com.lwxf.commons.utils.DateUtil;
import com.lwxf.commons.utils.ValidateUtils;
import com.lwxf.industry4.webapp.baseservice.cache.constant.RedisConstant;
import com.lwxf.industry4.webapp.bizservice.company.CompanyEmployeeService;
import com.lwxf.industry4.webapp.bizservice.company.CompanyService;
import com.lwxf.industry4.webapp.bizservice.company.EmployeeInfoService;
import com.lwxf.industry4.webapp.bizservice.dept.DeptMemberService;
import com.lwxf.industry4.webapp.bizservice.dept.DeptService;
import com.lwxf.industry4.webapp.bizservice.system.RolePermissionService;
import com.lwxf.industry4.webapp.bizservice.system.RoleService;
import com.lwxf.industry4.webapp.bizservice.user.UserBasisService;
import com.lwxf.industry4.webapp.bizservice.user.UserExtraService;
import com.lwxf.industry4.webapp.bizservice.user.UserService;
import com.lwxf.industry4.webapp.bizservice.user.UserThirdInfoService;
import com.lwxf.industry4.webapp.common.authcode.AuthCodeUtils;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.dto.UserInfoObj;
import com.lwxf.industry4.webapp.common.enums.company.EmployeeStatus;
import com.lwxf.industry4.webapp.common.enums.user.EmployeeInfoStatus;
import com.lwxf.industry4.webapp.common.enums.user.UserSex;
import com.lwxf.industry4.webapp.common.enums.user.UserState;
import com.lwxf.industry4.webapp.common.enums.user.UserType;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.uniquecode.UniquneCodeGenerator;
import com.lwxf.industry4.webapp.common.utils.LoginUtil;
import com.lwxf.industry4.webapp.common.utils.UserExtraUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.companyEmployee.CompanyEmployeeDto;
import com.lwxf.industry4.webapp.domain.entity.company.CompanyEmployee;
import com.lwxf.industry4.webapp.domain.entity.company.EmployeeInfo;
import com.lwxf.industry4.webapp.domain.entity.org.Dept;
import com.lwxf.industry4.webapp.domain.entity.org.DeptMember;
import com.lwxf.industry4.webapp.domain.entity.system.RolePermission;
import com.lwxf.industry4.webapp.domain.entity.user.User;
import com.lwxf.industry4.webapp.domain.entity.user.UserBasis;
import com.lwxf.industry4.webapp.domain.entity.user.UserExtra;
import com.lwxf.industry4.webapp.domain.entity.user.UserThirdInfo;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.smallProgram.smallProgramDept.SpDeptFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/10/19 0019 9:15
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("spDeptFacade")
public class SpDeptFacadeImpl extends BaseFacadeImpl implements SpDeptFacade {
	@Resource(name = "deptService")
	private DeptService deptService;
	@Resource(name = "deptMemberService")
	private DeptMemberService deptMemberService;
	@Resource(name = "companyEmployeeService")
	private CompanyEmployeeService companyEmployeeService;
	@Resource(name = "roleService")
	private RoleService roleService;
	@Resource(name = "userService")
	private UserService userService;
	@Resource(name = "companyService")
	private CompanyService companyService;
	@Resource(name = "rolePermissionService")
	private RolePermissionService rolePermissionService;
	@Resource(name = "userExtraService")
	private UserExtraService userExtraService;
	@Resource(name = "userThirdInfoService")
	private UserThirdInfoService userThirdInfoService;
	@Resource(name = "userBasisService")
	private UserBasisService userBasisService;
	@Resource(name = "employeeInfoService")
	private EmployeeInfoService employeeInfoService;

	/**
	 * 通讯录 部门员工列表
	 * @param branchId
	 * @return
	 */
	@Override
	public RequestResult findDeptMemberList(String branchId) {

		//查询部门列表
		List<Dept> deptList=this.deptService.findAllDeptByBranchId(branchId);
		if(deptList.size()==0){
			return ResultFactory.generateSuccessResult();
		}
		//查询部门下员工
		List result=new ArrayList();
		for(Dept dept:deptList){
			MapContext mapContext=MapContext.newOne();
			String deptId=dept.getId();
			Dept byId = this.deptService.findById(deptId);
			String deptName=byId.getName();
			List<CompanyEmployeeDto> employeeDtos=this.deptMemberService.findEmployeeDtoByDeptId(deptId);
			mapContext.put("deptName",deptName);
			mapContext.put("deptId",deptId);
			mapContext.put("deptMember",employeeDtos);
			result.add(mapContext);
		}
		return ResultFactory.generateRequestResult(result);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addDeptMember(MapContext mapContext, StringBuffer pwd) {
		String atoken= WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String cid =mapInfo.get("companyId")==null?null:mapInfo.get("companyId").toString();
		String branchId =mapInfo.get("branchId")==null?null:mapInfo.get("branchId").toString();
		String name = mapContext.getTypedValue("name", String.class);
		String mobile = mapContext.getTypedValue("mobile", String.class);
		String roleId = mapContext.getTypedValue("roleId", String.class);
		String deptId = mapContext.getTypedValue("deptId",String.class);

		//判断角色是否存在
		if(!this.roleService.isExist(roleId)){
			return ResultFactory.generateResNotFoundResult();
		}
		//验证电话号码是否正确
		if (!ValidateUtils.isChinaPhoneNumber(mobile)) {
			Map<String, String> errorMap = new HashMap<>();
			errorMap.put(WebConstant.KEY_ENTITY_MOBILE, AppBeanInjector.i18nUtil.getMessage("VALIDATE_INVALID_MOBILE_NO"));
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, errorMap);
		}
		//判断手机号是否已存在
		if(this.userService.findByMobile(mobile)!=null){
			Map result = new HashMap<>();
			result.put(WebConstant.KEY_ENTITY_MOBILE, AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOT_ALLOWED_REPEAT"));
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,result);
		}
		//判断公司是否存在
		if(!this.companyService.isExist(cid)){
			return ResultFactory.generateResNotFoundResult();
		}

		//用户信息
		User user = new User();
		user.setName(name);
		user.setMobile(mobile);
		user.setType(UserType.FACTORY.getValue());
		Integer sex=mapContext.getTypedValue("sex",Integer.class);
		if(sex==null||sex.equals("")){
			user.setSex(UserSex.MAN.getValue());
		}else {
			user.setSex(sex);
		}
		user.setAvatar(AppBeanInjector.configuration.getUserDefaultAvatar());
		user.setTimeZone(WebConstant.TIMEZONE_DEFAULT);
		user.setLanguage(WebConstant.LANGUAGE_DEFAULT);
		user.setCreated(DateUtil.getSystemDate());
		user.setState(UserState.ENABLED.getValue());
		user.setLoginName(AppBeanInjector.uniquneCodeGenerator.getNextNo(UniquneCodeGenerator.UniqueResource.USER_LOGNAME_NO));
		user.setFollowers(0);
		user.setChangedLoginName(false);
		user.setBranchId(branchId);
		this.userService.add(user);

		//用户扩展信息
		UserExtra userExtra = new UserExtra();
		userExtra.setUserId(user.getId());
		pwd.append(AuthCodeUtils.getRandomNumberCode(6));
		UserExtraUtil.saltingPassword(userExtra,new Md5Hash(pwd.toString()).toString());
		this.userExtraService.add(userExtra);

		// 第三方账号信息
		UserThirdInfo userThirdInfo = new UserThirdInfo();
		userThirdInfo.setWxNickname(user.getMobile());
		userThirdInfo.setWxIsBind(Boolean.FALSE);
		userThirdInfo.setWxIsSubscribe(Boolean.FALSE);
		userThirdInfo.setEmailIsBind(Boolean.FALSE);
		userThirdInfo.setMobileIsBind(Boolean.FALSE);
		userThirdInfo.setUserId(user.getId());
		userThirdInfo.setCompanyId(cid);
		userThirdInfo.setBranchId(branchId);
		userThirdInfo.setAppToken(UserExtraUtil.generateAppToken(userExtra,null));
		AppBeanInjector.redisUtils.hPut(RedisConstant.PLATFORM_TAG, user.getId(), Integer.valueOf(1));
		this.userThirdInfoService.add(userThirdInfo);

		//用户基本信息表
		UserBasis userBasis = new UserBasis();
		userBasis.setUserId(user.getId());
		this.userBasisService.add(userBasis);

		//给该用户添加公司角色
		CompanyEmployee companyEmployee = new CompanyEmployee();
		companyEmployee.setCompanyId(cid);
		companyEmployee.setUserId(user.getId());
		companyEmployee.setRoleId(roleId);
		companyEmployee.setStatus(EmployeeStatus.NORMAL.getValue());
		companyEmployee.setCreated(DateUtil.getSystemDate());
		this.companyEmployeeService.add(companyEmployee);
		//把角色相关权限添加至 员工表中
		List<RolePermission> rolePermissionList= this.rolePermissionService.selectRolePermissionList(roleId);
		if(rolePermissionList!=null&&rolePermissionList.size()!=0){
			IIdGenerator idGenerator = new LwxfWorkerIdGenerator(1);
			for(RolePermission rolePermission:rolePermissionList){
				//重新生成主键ID
				rolePermission.setId(idGenerator.nextStringId());
				//用公司员工主键ID替换权限ID
				rolePermission.setRoleId(companyEmployee.getId());
			}
		}
		//新增员工时 设置 员工信息 初始数据
		EmployeeInfo employeeInfo = new EmployeeInfo();
		employeeInfo.setCompanyEmployeeId(companyEmployee.getId());
		employeeInfo.setUserId(user.getId());
		employeeInfo.setStatus(EmployeeInfoStatus.NORMAL.getValue()+"");
		this.employeeInfoService.add(employeeInfo);
		//判断员工是否选择部门
		if(deptId!=null&&!deptId.trim().equals("")) {
			DeptMember deptMember = new DeptMember();
			deptMember.setDeptId(deptId);
			deptMember.setEmployeeId(companyEmployee.getId());
			this.deptMemberService.add(deptMember);
		}
		return ResultFactory.generateRequestResult(UserInfoObj.newOne(user,userExtra,userThirdInfo,null));
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addDept(Dept dept) {
		//判断公司是否存在
		if(!this.companyService.isExist(dept.getCompanyId())){
			return ResultFactory.generateResNotFoundResult();
		}
		//部门名称不允许重复
		if(this.deptService.findDeptByNameAndParentId(dept.getName(),dept.getParentId(),dept.getBranchId())!=null){
			HashMap<String, String> result = new HashMap<>();
			result.put("name",AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOT_ALLOWED_REPEAT"));
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,result);
		}

		//判断key是否重复
		if(this.deptService.findDeptByKey(dept.getKey(),dept.getBranchId())!=null){
			HashMap<String, String> result = new HashMap<>();
			result.put("key",AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOT_ALLOWED_REPEAT"));
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,result);
		}

		//判断部门是否为null
		if(dept.getParentId()!=null&&!this.deptService.isExist(dept.getParentId())){
			return ResultFactory.generateResNotFoundResult();
		}
		this.deptService.add(dept);
		return ResultFactory.generateRequestResult(dept);
	}

	/**
	 * 批量移动员工
	 * @param mapContext
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager")
	public RequestResult moveDeptMember(MapContext mapContext) {
		String employeeIds=mapContext.getTypedValue("employeeIds",String.class);
		String oldDeptId=mapContext.getTypedValue("oldDeptId",String.class);
		String newDeptId=mapContext.getTypedValue("newDeptId",String.class);
		String[] list=employeeIds.split(",");
		for (String employeeId:list) {
			//删除员工原有的部门
			DeptMember deptMember = this.deptMemberService.findOneByDeptIdAndEmployeeId(oldDeptId, employeeId);
			if(deptMember==null){
				return ResultFactory.generateResNotFoundResult();
			}
			this.deptMemberService.deleteById(deptMember.getId());
			//添加到新部门
			DeptMember member=new DeptMember();
			member.setDeptId(newDeptId);
			member.setEmployeeId(employeeId);
			this.deptMemberService.add(member);
		}
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult deleteDept(String ids) {
		String[] deptIds=ids.split(",");
		for(String deptId:deptIds){
			List<DeptMember> deptMemberList = this.deptMemberService.selectByDeptId(deptId);
			if(deptMemberList.size()>0){
				System.out.println(AppBeanInjector.i18nUtil.getMessage("BIZ_NOT_ALLOW_OPERATION_10020"));
				return ResultFactory.generateErrorResult(ErrorCodes.BIZ_NOT_ALLOW_OPERATION_10020,AppBeanInjector.i18nUtil.getMessage("BIZ_NOT_ALLOW_OPERATION_10020"));
			}
			this.deptService.deleteById(deptId);
		}
		return ResultFactory.generateSuccessResult();
	}
}
