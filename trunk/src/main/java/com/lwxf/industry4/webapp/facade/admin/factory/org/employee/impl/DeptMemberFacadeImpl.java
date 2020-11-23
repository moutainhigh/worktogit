package com.lwxf.industry4.webapp.facade.admin.factory.org.employee.impl;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lwxf.industry4.webapp.domain.dao.userRole.UserRoleDao;
import com.lwxf.industry4.webapp.domain.entity.userRole.UserRole;
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
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.uniquecode.UniquneCodeGenerator;
import com.lwxf.industry4.webapp.common.utils.UserExtraUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.entity.company.CompanyEmployee;
import com.lwxf.industry4.webapp.domain.entity.company.EmployeeInfo;
import com.lwxf.industry4.webapp.domain.entity.org.DeptMember;
import com.lwxf.industry4.webapp.domain.entity.system.RolePermission;
import com.lwxf.industry4.webapp.domain.entity.user.User;
import com.lwxf.industry4.webapp.domain.entity.user.UserBasis;
import com.lwxf.industry4.webapp.domain.entity.user.UserExtra;
import com.lwxf.industry4.webapp.domain.entity.user.UserThirdInfo;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.admin.factory.org.employee.DeptMemberFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/11/011 11:39
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("deptMemberFacade")
public class DeptMemberFacadeImpl extends BaseFacadeImpl implements DeptMemberFacade {

	@Resource(name = "deptMemberService")
	private DeptMemberService deptMemberService;
	@Resource(name = "deptService")
	private DeptService deptService;
	@Resource(name = "roleService")
	private RoleService roleService;
	@Resource(name = "companyService")
	private CompanyService companyService;
	@Resource(name = "userService")
	private UserService userService;
	@Resource(name = "companyEmployeeService")
	private CompanyEmployeeService companyEmployeeService;
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
	@Resource(name = "userRoleDao")
	private UserRoleDao userRoleDao;
	@Override
	public RequestResult findDeptList(String deptId, MapContext mapContext,Integer pageNum,Integer pageSize) {
		//判断部门是否存在
		if(!this.deptService.isExist(deptId)){
			return ResultFactory.generateResNotFoundResult();
		}
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		Pagination pagination = new Pagination();
		pagination.setPageSize(pageSize);
		pagination.setPageNum(pageNum);
		paginatedFilter.setPagination(pagination);
		mapContext.put(WebConstant.KEY_ENTITY_ID,deptId);
		mapContext.put(WebConstant.KEY_ENTITY_COMPANY_ID,WebUtils.getCurrCompanyId());
		paginatedFilter.setFilters(mapContext);
		List<Map<String,String>> sortList = new ArrayList<Map<String, String>>();
		Map<String,String> created = new HashMap<String, String>();
		created.put(WebConstant.KEY_ENTITY_CREATED,"desc");
		sortList.add(created);
		paginatedFilter.setSorts(sortList);
		return ResultFactory.generateRequestResult(this.deptMemberService.findListByDeptIdAndNameAndCompanyId(paginatedFilter));
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addDeptMember(MapContext mapContext,String deptIds,StringBuffer pwd) {

		String name = mapContext.getTypedValue("name", String.class);
		String roleId = mapContext.getTypedValue("roleId", String.class);
		String mobile = mapContext.getTypedValue("mobile", String.class);
		String no = mapContext.getTypedValue("no",String.class);

		//判断角色是否存在
		if(!this.roleService.isExist(roleId)){
			return ResultFactory.generateResNotFoundResult();
		}

		//验证电话号码是否正确
		if (!ValidateUtils.isChinaPhoneNumber(mobile)) {
			Map<String, String> errorMap = new HashMap<>();
			errorMap.put(WebConstant.KEY_ENTITY_MOBILE,AppBeanInjector.i18nUtil.getMessage("VALIDATE_INVALID_MOBILE_NO"));
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, errorMap);
		}
		//判断手机号是否已存在
		if(this.userService.findByMobile(mobile)!=null){
			Map result = new HashMap<>();
			result.put(WebConstant.KEY_ENTITY_MOBILE, AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOT_ALLOWED_REPEAT"));
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,result);
		}
		//判断公司是否存在
		if(!this.companyService.isExist(WebUtils.getCurrCompanyId())){
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
//		user.setState(UserState.ENABLED.getValue());
        user.setState(UserState.DISABLED.getValue()); // 默认公司员工禁用状态
		user.setLoginName(AppBeanInjector.uniquneCodeGenerator.getNextNo(UniquneCodeGenerator.UniqueResource.USER_LOGNAME_NO));
		user.setFollowers(0);
		user.setChangedLoginName(false);
		user.setBranchId(WebUtils.getCurrBranchId());
		user.setEnableLoginPc(WebConstant.DISABLE); // 默认禁止PC登录
        user.setEnableLoginWx(WebConstant.DISABLE); // 默认禁止微信小程序登录
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
		userThirdInfo.setCompanyId(WebUtils.getCurrCompanyId());
		userThirdInfo.setBranchId(WebUtils.getCurrBranchId());
		userThirdInfo.setAppToken(UserExtraUtil.generateAppToken(userExtra,null));
		AppBeanInjector.redisUtils.hPut(RedisConstant.PLATFORM_TAG, user.getId(), Integer.valueOf(1));
		this.userThirdInfoService.add(userThirdInfo);


		//用户基本信息表
		UserBasis userBasis = new UserBasis();
		userBasis.setUserId(user.getId());
		this.userBasisService.add(userBasis);

		//给该用户添加公司角色 店主
		CompanyEmployee companyEmployee = new CompanyEmployee();
		companyEmployee.setCompanyId(WebUtils.getCurrCompanyId());
		companyEmployee.setUserId(user.getId());
		//判断员工编号是否重复
		if(no!=null&&!no.trim().equals("")){
			if(this.companyEmployeeService.selectEmployeeByCidAndNo(WebUtils.getCurrCompanyId(),no)!=null){
				Map<String, String> errorMap = new HashMap<>();
				errorMap.put(WebConstant.STRING_NO,AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOT_ALLOWED_REPEAT"));
				return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, errorMap);
			}
			companyEmployee.setNo(no);
		}

		//店主编号未定
		companyEmployee.setRoleId(roleId);
		companyEmployee.setStatus(EmployeeStatus.NORMAL.getValue());
		companyEmployee.setCreated(DateUtil.getSystemDate());
		this.companyEmployeeService.add(companyEmployee);
//		//把店主相关权限添加至 员工表中
//		List<RolePermission> rolePermissionList= this.rolePermissionService.selectRolePermissionList(roleId);
//		if(rolePermissionList!=null&&rolePermissionList.size()!=0){
//			IIdGenerator idGenerator = new LwxfWorkerIdGenerator(1);
//			for(RolePermission rolePermission:rolePermissionList){
//				//重新生成主键ID
//				rolePermission.setId(idGenerator.nextStringId());
//				//用公司员工主键ID替换权限ID
//				rolePermission.setRoleId(companyEmployee.getId());
//			}
//		}
		//添加用户权限
		UserRole userRole=new UserRole();
		IIdGenerator idGenerator = new LwxfWorkerIdGenerator(1);
		userRole.setId(idGenerator.nextStringId());
		userRole.setRoleId(roleId);
		userRole.setUserId(companyEmployee.getUserId());
		this.userRoleDao.insert(userRole);

		//新增员工时 设置 员工信息 初始数据
		EmployeeInfo employeeInfo = new EmployeeInfo();
		employeeInfo.setCompanyEmployeeId(companyEmployee.getId());
		employeeInfo.setUserId(user.getId());
		employeeInfo.setStatus(EmployeeInfoStatus.NORMAL.getValue()+"");
		this.employeeInfoService.add(employeeInfo);

		//判断员工是否选择部门
		if(deptIds!=null&&!deptIds.trim().equals("")) {
			String[] deptId = deptIds.split(",");
			for (int i = 0; i < deptId.length; i++) {
				//判断部门是否存在
				if (!this.deptService.isExist(deptId[i])) {
					return ResultFactory.generateResNotFoundResult();
				}
				//创建部门信息
				DeptMember deptMember = new DeptMember();
				deptMember.setDeptId(deptId[i]);
				deptMember.setEmployeeId(companyEmployee.getId());
				this.deptMemberService.add(deptMember);
			}
		}
		return ResultFactory.generateRequestResult(UserInfoObj.newOne(user,userExtra,userThirdInfo,null));
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult deleteDeptMember(String id,String eid) {
		//判断部门是否存在
		if(!this.deptService.isExist(id)){
			return ResultFactory.generateResNotFoundResult();
		}
		//判断该员工是否存在于员工部门表
		DeptMember deptMember = this.deptMemberService.findOneByDeptIdAndEmployeeId(id,eid);
		if(deptMember==null||!deptMember.getDeptId().equals(id)){
			return ResultFactory.generateSuccessResult();
		}

		this.deptMemberService.deleteById(deptMember.getId());
		return ResultFactory.generateSuccessResult();
	}
}
