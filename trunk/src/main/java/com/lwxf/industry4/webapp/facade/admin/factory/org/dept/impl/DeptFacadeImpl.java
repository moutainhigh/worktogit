package com.lwxf.industry4.webapp.facade.admin.factory.org.dept.impl;

import com.lwxf.commons.uniquekey.IIdGenerator;
import com.lwxf.commons.uniquekey.LwxfWorkerIdGenerator;
import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.bizservice.common.UploadFilesService;
import com.lwxf.industry4.webapp.bizservice.company.*;
import com.lwxf.industry4.webapp.bizservice.dept.DeptMemberService;
import com.lwxf.industry4.webapp.bizservice.dept.DeptService;
import com.lwxf.industry4.webapp.bizservice.system.RolePermissionService;
import com.lwxf.industry4.webapp.bizservice.system.RoleService;
import com.lwxf.industry4.webapp.bizservice.user.UserBasisService;
import com.lwxf.industry4.webapp.bizservice.user.UserService;
import com.lwxf.industry4.webapp.common.component.UploadInfo;
import com.lwxf.industry4.webapp.common.component.UploadType;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.UploadResourceType;
import com.lwxf.industry4.webapp.common.enums.company.EmployeeStatus;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dao.userRole.UserRoleDao;
import com.lwxf.industry4.webapp.domain.dto.companyEmployee.CompanyEmployeeDto;
import com.lwxf.industry4.webapp.domain.dto.dept.*;
import com.lwxf.industry4.webapp.domain.entity.common.UploadFiles;
import com.lwxf.industry4.webapp.domain.entity.company.*;
import com.lwxf.industry4.webapp.domain.entity.org.Dept;
import com.lwxf.industry4.webapp.domain.entity.org.DeptMember;
import com.lwxf.industry4.webapp.domain.entity.user.UserBasis;
import com.lwxf.industry4.webapp.domain.entity.userRole.UserRole;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.org.dept.DeptFacade;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/11/30/030 14:23
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("deptFacade")
public class DeptFacadeImpl extends BaseFacadeImpl implements DeptFacade {

	@Resource(name = "deptService")
	private DeptService deptService;
	@Resource(name = "companyService")
	private CompanyService companyService;
	@Resource(name = "companyEmployeeService")
	private CompanyEmployeeService companyEmployeeService;
	@Resource(name = "deptMemberService")
	private DeptMemberService deptMemberService;
	@Resource(name = "roleService")
	private RoleService roleService;
	@Resource(name = "rolePermissionService")
	private RolePermissionService rolePermissionService;
	@Resource(name = "userService")
	private UserService userService;
	@Resource(name = "employeeInfoService")
	private EmployeeInfoService employeeInfoService;
	@Resource(name = "employeeExperienceService")
	private EmployeeExperienceService employeeExperienceService;
	@Resource(name = "employeeCertificateService")
	private EmployeeCertificateService employeeCertificateService;
	@Resource(name = "employeeEducationExperienceService")
	private EmployeeEducationExperienceService employeeEducationExperienceService;
	@Resource(name = "employeeAssessmentService")
	private EmployeeAssessmentService employeeAssessmentService;
	@Resource(name="uploadFilesService")
	private UploadFilesService uploadFilesService;
	@Resource(name = "userBasisService")
	private UserBasisService userBasisService;
	@Resource(name = "userRoleDao")
	private UserRoleDao userRoleDao;
	@Override
	public RequestResult findAllDeptList() {
		String companyId = WebUtils.getCurrCompanyId();
		//判断该公司是否存在
		if(!this.companyService.isExist(companyId)){
			return ResultFactory.generateResNotFoundResult();
		}
//		//查询当前公司下的一级部门
//		List<Dept> depts = this.deptService.selectDeptByCompanyIdAndParentId(companyId,null);
//		List deptList = new ArrayList();
//
//		DeptDto deptDto = new DeptDto();
//		this.createDeptDtoList(depts,companyId,deptDto);



		return ResultFactory.generateRequestResult(this.deptService.findAll(companyId));
	}

	private void createDeptDtoList(List<Dept> depts,String companyId,DeptDto deptDto) {
		for (Dept dept:depts){
			DeptDto depDto = new DeptDto();
			MapContext mapContext = MapContext.newOne();
			mapContext.put(WebConstant.KEY_ENTITY_COMPANY_ID,companyId);
			mapContext.put(WebConstant.KEY_ENTITY_ID,dept.getId());
			depDto.setCompanyEmployeeList(this.deptMemberService.findListByDeptIdAndCompanyId(mapContext));
			depDto.setDept(dept);
			List<Dept> sonDeptList = this.deptService.selectDeptByCompanyIdAndParentId(companyId, dept.getId());
			if(sonDeptList!=null&&sonDeptList.size()!=0){
				deptDto.getDeptList().add(depDto);
				this.createDeptDtoList(sonDeptList,companyId,depDto);
			}else{
				deptDto.getDeptList().add(depDto);
			}
		}
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addDept(Dept dept) {
		//判断公司是否存在
		if(!this.companyService.isExist(dept.getCompanyId())){
			return ResultFactory.generateResNotFoundResult();
		}
		//部门名称不允许重复
		if(this.deptService.findDeptByNameAndParentId(dept.getName(),dept.getParentId(),WebUtils.getCurrBranchId())!=null){
			HashMap<String, String> result = new HashMap<>();
			result.put("name",AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOT_ALLOWED_REPEAT"));
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,result);
		}

		//判断key是否重复
		if(this.deptService.findDeptByKey(dept.getKey(),WebUtils.getCurrBranchId())!=null){
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


	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updateDept(MapContext mapContext, String id) {
		//判断部门是否存在
		Dept dept = this.deptService.findById(id);
		if(dept==null){
			return ResultFactory.generateResNotFoundResult();
		}
		//如果修改部门名称,判断名称是否重复
		String name = mapContext.getTypedValue("name", String.class);
		if(name!=null){
			MapContext context = MapContext.newOne();
			context.put(WebConstant.KEY_ENTITY_NAME,name);
			context.put(WebConstant.KEY_ENTITY_COMPANY_ID,WebUtils.getCurrCompanyId());
			context.put(WebConstant.KEY_ENTITY_PARENTID,dept.getParentId());
			Dept oldDept = this.deptService.findOneByKeyOrNameAndCompanyId(context);
			if(oldDept!=null&&!oldDept.getId().equals(id)){
				HashMap<String, String> result = new HashMap<>();
				result.put("name",AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOT_ALLOWED_REPEAT"));
				return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,result);
			}
		}
		//如果修改部门key 判断是否重复
		String key = mapContext.getTypedValue("key", String.class);
		if(key!=null){
			MapContext context = MapContext.newOne();
			context.put("key",key);
			context.put(WebConstant.KEY_ENTITY_COMPANY_ID,WebUtils.getCurrCompanyId());
			context.put(WebConstant.KEY_ENTITY_PARENTID,dept.getParentId());
			Dept oldDept = this.deptService.findOneByKeyOrNameAndCompanyId(context);
			if(oldDept!=null&&!oldDept.getId().equals(id)){
				HashMap<String, String> result = new HashMap<>();
				result.put("key",AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOT_ALLOWED_REPEAT"));
				return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,result);
			}
		}
		mapContext.put(WebConstant.KEY_ENTITY_ID,id);
		this.deptService.updateByMapContext(mapContext);
		return ResultFactory.generateRequestResult(this.deptService.findById(id));
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult deleteById(String id) {
		if (!this.deptService.isExist(id)){
			return ResultFactory.generateSuccessResult();
		}
		//判断部门下是否有员工
		List<DeptMember> deptMemberList = this.deptMemberService.selectByDeptId(id);
		if (deptMemberList!=null&&deptMemberList.size()!=0){
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_RES_BE_IN_USE_10006,AppBeanInjector.i18nUtil.getMessage("BIZ_RES_BE_IN_USE_10006"));
		}
		//判断部门下是否存在二级部门
		List<Dept> sonDeptList = this.deptService.findListByParentId(id);
		if (sonDeptList!=null&&sonDeptList.size()!=0){
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_RES_BE_IN_USE_10006,AppBeanInjector.i18nUtil.getMessage("BIZ_RES_BE_IN_USE_10006"));
		}
		this.deptService.deleteById(id);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	public RequestResult findMemberList(Integer pageNum,Integer pageSize,MapContext mapContext,String deptId) {
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		if(deptId!=null&&!deptId.trim().equals("")){
			//查询部门下的二级部门
			List<Dept> deptList = this.deptService.selectDeptByCompanyIdAndParentId(WebUtils.getCurrCompanyId(), deptId);
			List deptIds = new ArrayList();
			deptIds.add(deptId);
			for (int i = 0; i <deptList.size() ; i++) {
				deptIds.add(deptList.get(i).getId());
			}
			mapContext.put("deptIds",deptIds);
		}
		paginatedFilter.setFilters(mapContext);
		List<Map<String,String>> sortList= new ArrayList<Map<String,String>>();
		Map<String,String> createdSort= new HashMap<String,String>();
		createdSort.put(WebConstant.KEY_ENTITY_CREATED,"desc");
		sortList.add(createdSort);
		paginatedFilter.setSorts(sortList);
		Pagination pagination = new Pagination();
		pagination.setPageNum(pageNum);
		pagination.setPageSize(pageSize);
		paginatedFilter.setPagination(pagination);
		PaginatedList<CompanyEmployeeDto> paginatedList = this.companyEmployeeService.findListByFilter(paginatedFilter);
		List employeeDeptDtoList = new ArrayList();
		for(CompanyEmployeeDto companyEmployeeDto:paginatedList.getRows()){
			employeeDeptDtoList.add(this.deptService.findOneByUserId(companyEmployeeDto.getUserId()));
		}
		return ResultFactory.generateRequestResult(employeeDeptDtoList,paginatedList.getPagination());
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updateMemberInfo(String eid, UpdateUserRoleDeptDto updateDto) {
		CompanyEmployee companyEmployee = this.companyEmployeeService.findById(eid);
		//判断员工是否存在
		if(companyEmployee==null){
			return ResultFactory.generateResNotFoundResult();
		}
       if(updateDto.getUserBasis()!=null) {
		   String idCard = updateDto.getUserBasis().getTypedValue("identityNumber", String.class);
		   if (idCard != null) {
			   Pattern pattern = Pattern.compile("^[1-9][0-7]\\d{4}((19\\d{2}(0[13-9]|1[012])(0[1-9]|[12]\\d|30))|(19\\d{2}(0[13578]|1[02])31)|(19\\d{2}02(0[1-9]|1\\d|2[0-8]))|(19([13579][26]|[2468][048]|0[48])0229))\\d{3}(\\d|X|x)?");
			   Matcher matcher = pattern.matcher(idCard);
			   if (!matcher.matches()) {
				   return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_INVALID_ID_NUMBER_20037, AppBeanInjector.i18nUtil.getMessage("VALIDATE_INVALID_ID_NUMBER_20037"));
			   }
		   }
		   updateDto.getUserBasis().put(WebConstant.KEY_ENTITY_USER_ID,companyEmployee.getUserId());
		   this.userBasisService.updateByMapContext(updateDto.getUserBasis());
	   }

		MapContext employee = new MapContext();
		String roleId = updateDto.getRoleId();
		Integer status = updateDto.getStatus();
		//判断是否修改角色
		if(roleId!=null&&!roleId.trim().equals("")){
			if (!this.roleService.isExist(roleId)){
				return ResultFactory.generateResNotFoundResult();
			}
			//判断所要修改的角色是否是员工现在的角色
			if(!roleId.equals(companyEmployee.getRoleId())){
//				//查询新角色下的权限
////				List<RolePermission> rolePermissions = this.rolePermissionService.selectRolePermissionList(roleId);
////				if(rolePermissions!=null&&rolePermissions.size()!=0){
////					IIdGenerator idGenerator = new LwxfWorkerIdGenerator(1);
////					for(RolePermission rolePermission:rolePermissions){
////						//重新生成主键ID
////						rolePermission.setId(idGenerator.nextStringId());
////						//用公司员工主键ID替换权限ID
////						rolePermission.setRoleId(companyEmployee.getId());
////					}
////				}
				//删除之前的角色，添加新角色
				List<UserRole> allUserRole = this.userRoleDao.getAllUserRole(companyEmployee.getUserId());
				for(UserRole userRole:allUserRole){
					this.userRoleDao.deleteById(userRole.getRoleId());
				}
				UserRole userRole=new UserRole();
				IIdGenerator idGenerator = new LwxfWorkerIdGenerator(1);
				userRole.setId(idGenerator.nextStringId());
				userRole.setRoleId(roleId);
				userRole.setUserId(companyEmployee.getUserId());
				this.userRoleDao.insert(userRole);
				employee.put("roleId",roleId);

			}
		}
		//判断是否修改状态
		if(status!=null){
			employee.put("status",status);
		}
		if(updateDto.getNo()!=null){
			employee.put("no",updateDto.getNo());
		}
		if(employee.size()!=0){
			//修改公司员工表信息
			employee.put(WebConstant.KEY_ENTITY_ID,eid);
			this.companyEmployeeService.updateByMapContext(employee);
		}
		//设置员工部门
		String[] deptIds = updateDto.getDeptIds();
		//清空员工所属部门
		this.deptMemberService.deleteByDeptIdAndEmployeeId(null,eid);
		if (deptIds!=null){
			for (int i= 0;i<deptIds.length;i++){
				if(!this.deptService.isExist(deptIds[i])){
					return ResultFactory.generateResNotFoundResult();
				}
				DeptMember deptMember = new DeptMember();
				deptMember.setDeptId(deptIds[i]);
				deptMember.setEmployeeId(eid);
				this.deptMemberService.add(deptMember);
			}
		}
		updateDto.getUserInfo().put(WebConstant.KEY_ENTITY_ID,companyEmployee.getUserId());
		//修改员工基本信息
		this.userService.updateByMapContext(updateDto.getUserInfo());

		//判断资源是否存在
		EmployeeInfo employeeInfo = this.employeeInfoService.findOneByEid(eid);
		if(employeeInfo==null){
			return ResultFactory.generateResNotFoundResult();
		}
		//判断是否是修改员工信息
		if(updateDto.getEmployeeInfo().size()>0){
			updateDto.getEmployeeInfo().put(WebConstant.KEY_ENTITY_ID,employeeInfo.getId());
			this.employeeInfoService.updateByMapContext(updateDto.getEmployeeInfo());
		}
		MapContext mapContext = new MapContext();
		mapContext.put("employeeInfo",this.employeeInfoService.findOneByEid(eid));
		mapContext.put("employeeDeptDto",this.deptService.findOneByUserId(companyEmployee.getUserId()));
		return ResultFactory.generateRequestResult(mapContext);
	}

	@Override
	public RequestResult findMemberInfo(String eid) {
		//查询公司员工信息表
		EmployeeDeptDto employeeDeptDto = this.companyEmployeeService.findOneByEid(eid);
		//查询员工工作经历表
		List<EmployeeExperienceDto> employeeExperienceList = this.employeeExperienceService.findListByEid(eid);
		//查询员工信息表
		EmployeeInfo employeeInfo = this.employeeInfoService.findOneByEid(eid);
		//查询员工证书信息表
		List<EmployeeCertificateDto> employeeCertificateList = this.employeeCertificateService.findListByEid(eid);
		//查询员工教育经历表
		List<EmployeeEducationExperienceDto> employeeEducationExperienceList = this.employeeEducationExperienceService.findListByEid(eid);
		//查询员工考核信息表
		List<EmployeeAssessmentDto> employeeAssessmentList = this.employeeAssessmentService.findListByEid(eid);
		//用户基础信息表
		UserBasis userBasis = this.userBasisService.findByUserId(employeeDeptDto.getUserId());
		CompanyEmployeeInfoDto companyEmployeeInfoDto = new CompanyEmployeeInfoDto();
		companyEmployeeInfoDto.setEmployeeDeptDto(employeeDeptDto);
		companyEmployeeInfoDto.setEmployeeAssessmentList(employeeAssessmentList);
		companyEmployeeInfoDto.setEmployeeCertificateList(employeeCertificateList);
		companyEmployeeInfoDto.setEmployeeEducationExperienceList(employeeEducationExperienceList);
		companyEmployeeInfoDto.setEmployeeExperienceList(employeeExperienceList);
		companyEmployeeInfoDto.setEmployeeInfo(employeeInfo);
		companyEmployeeInfoDto.setUserBasis(userBasis);

		String userId = employeeDeptDto.getUserId();
		List<UserRole> allUserRole = userRoleDao.getAllUserRole(userId);
        companyEmployeeInfoDto.setRoleIds(allUserRole);

        return ResultFactory.generateRequestResult(companyEmployeeInfoDto);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updateEmployeeInfo(String eid, MapContext mapContext) {
		//判断资源是否存在
		EmployeeInfo employeeInfo = this.employeeInfoService.findOneByEid(eid);
		if(employeeInfo==null){
			return ResultFactory.generateResNotFoundResult();
		}
		mapContext.put(WebConstant.KEY_ENTITY_ID,employeeInfo.getId());
		this.employeeInfoService.updateByMapContext(mapContext);
		return ResultFactory.generateRequestResult(this.employeeInfoService.findOneByEid(eid));
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addEmployeeExperience(String eid, EmployeeExperience employeeExperience) {
		//判断员工是否存在
		CompanyEmployee companyEmployee = this.companyEmployeeService.findById(eid);
		if(companyEmployee==null){
			return ResultFactory.generateResNotFoundResult();
		}
		this.employeeExperienceService.add(employeeExperience);
		return ResultFactory.generateRequestResult(employeeExperience);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updataEmployeeExperience(String eid,String id, MapContext mapContext) {
		//判断该工作经历以及该员工是否存在
		EmployeeExperience employeeExperience = this.employeeExperienceService.findById(id);
		if(employeeExperience==null||!employeeExperience.getCompanyEmployeeId().equals(eid)){
			return ResultFactory.generateResNotFoundResult();
		}
		mapContext.put(WebConstant.KEY_ENTITY_ID,id);
		this.employeeExperienceService.updateByMapContext(mapContext);
		return ResultFactory.generateRequestResult(this.employeeExperienceService.findById(id));
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult deleteEmployeeExperience(String eid, String id) {
		//判断该工作经历以及该员工是否存在
		EmployeeExperience employeeExperience = this.employeeExperienceService.findById(id);
		if(employeeExperience==null||!employeeExperience.getCompanyEmployeeId().equals(eid)){
			return ResultFactory.generateSuccessResult();
		}
		this.employeeExperienceService.deleteById(id);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addEmployeeAssessment(String eid, EmployeeAssessment employeeAssessment) {
		//判断员工是否存在
		CompanyEmployee companyEmployee = this.companyEmployeeService.findById(eid);
		if(companyEmployee==null){
			return ResultFactory.generateResNotFoundResult();
		}
		this.employeeAssessmentService.add(employeeAssessment);
		return ResultFactory.generateRequestResult(employeeAssessment);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updataEmployeeAssessment(String eid, String id, MapContext mapContext) {
		//判断该员工考核信息以及该员工是否存在
		EmployeeAssessment employeeAssessment = this.employeeAssessmentService.findById(id);
		if(employeeAssessment==null||!employeeAssessment.getCompanyEmployeeId().equals(eid)){
			return ResultFactory.generateSuccessResult();
		}
		mapContext.put(WebConstant.KEY_ENTITY_ID,id);
		this.employeeAssessmentService.updateByMapContext(mapContext);
		return ResultFactory.generateRequestResult(this.employeeAssessmentService.findById(id));
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult deleteEmployeeAssessment(String eid, String id) {
		//判断该员工考核信息以及该员工是否存在
		EmployeeAssessment employeeAssessment = this.employeeAssessmentService.findById(id);
		if(employeeAssessment==null||!employeeAssessment.getCompanyEmployeeId().equals(eid)){
			return ResultFactory.generateSuccessResult();
		}
		this.employeeAssessmentService.deleteById(id);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addEmployeeCertificate(String eid, EmployeeCertificateDto employeeCertificate) {
		//判断员工是否存在
		CompanyEmployee companyEmployee = this.companyEmployeeService.findById(eid);
		if(companyEmployee==null){
			return ResultFactory.generateResNotFoundResult();
		}
		this.employeeCertificateService.add(employeeCertificate);
		return ResultFactory.generateRequestResult(employeeCertificate);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updataEmployeeCertificate(String eid, String id, MapContext mapContext) {
		//判断该员工证书信息以及该员工是否存在
		EmployeeCertificate employeeCertificate = this.employeeCertificateService.findById(id);
		if(employeeCertificate==null||!employeeCertificate.getCompanyEmployeeId().equals(eid)){
			return ResultFactory.generateSuccessResult();
		}
		mapContext.put(WebConstant.KEY_ENTITY_ID,id);
		this.employeeCertificateService.updateByMapContext(mapContext);
		return ResultFactory.generateRequestResult(this.employeeCertificateService.findById(id));
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult deleteEmployeeCertificate(String eid, String id) {
		//判断该员工证书信息以及该员工是否存在
		EmployeeCertificate employeeCertificate = this.employeeCertificateService.findById(id);
		if(employeeCertificate==null||!employeeCertificate.getCompanyEmployeeId().equals(eid)){
			return ResultFactory.generateSuccessResult();
		}
		this.employeeCertificateService.deleteById(id);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addEmployeeEducationExperience(String eid, EmployeeEducationExperience employeeEducationExperience) {
		//判断员工是否存在
		CompanyEmployee companyEmployee = this.companyEmployeeService.findById(eid);
		if(companyEmployee==null){
			return ResultFactory.generateResNotFoundResult();
		}
		this.employeeEducationExperienceService.add(employeeEducationExperience);
		return ResultFactory.generateRequestResult(employeeEducationExperience);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updataEmployeeEducationExperience(String eid, String id, MapContext mapContext) {
		//判断该员工教育经历以及该员工是否存在
		EmployeeEducationExperience employeeEducationExperience = this.employeeEducationExperienceService.findById(id);
		if(employeeEducationExperience==null||!employeeEducationExperience.getCompanyEmployeeId().equals(eid)){
			return ResultFactory.generateSuccessResult();
		}
		mapContext.put(WebConstant.KEY_ENTITY_ID,id);
		this.employeeEducationExperienceService.updateByMapContext(mapContext);
		return ResultFactory.generateRequestResult(this.employeeEducationExperienceService.findById(id));
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult deleteEmployeeEducationExperience(String eid, String id) {
		//判断该员工教育经历以及该员工是否存在
		EmployeeEducationExperience employeeEducationExperience = this.employeeEducationExperienceService.findById(id);
		if(employeeEducationExperience==null||!employeeEducationExperience.getCompanyEmployeeId().equals(eid)){
			return ResultFactory.generateSuccessResult();
		}
		this.employeeEducationExperienceService.deleteById(id);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult uploadEmployeeFiles(String eid, String resId,List<MultipartFile> multipartFiles) {
		//判断员工是否存在
		CompanyEmployee companyEmployee = this.companyEmployeeService.findById(eid);
		if(companyEmployee==null||!companyEmployee.getStatus().equals(EmployeeStatus.NORMAL.getValue())){
			return ResultFactory.generateResNotFoundResult();
		}
		List imgList = new ArrayList();
		for(MultipartFile multipartFile:multipartFiles){
			UploadFiles uploadFiles = new UploadFiles();
			uploadFiles.setCreated(DateUtil.getSystemDate());
			uploadFiles.setCompanyId(WebUtils.getCurrCompanyId());
			uploadFiles.setCreator(WebUtils.getCurrUserId());
			UploadInfo uploadInfo=AppBeanInjector.baseFileUploadComponent.doUploadByModule(UploadType.FORMAL, multipartFile, UploadResourceType.EMPLOYEE, eid);
			uploadFiles.setName(uploadInfo.getFileName());
			uploadFiles.setPath(uploadInfo.getRelativePath());
			uploadFiles.setFullPath(AppBeanInjector.configuration.getDomainUrl()+uploadInfo.getRelativePath());
			uploadFiles.setMime(uploadInfo.getFileMimeType().getRealType());
			uploadFiles.setOriginalMime(uploadInfo.getFileMimeType().getOriginalType());
			uploadFiles.setStatus(UploadType.FORMAL.getValue());
			uploadFiles.setResourceType(UploadResourceType.EMPLOYEE.getType());
			//子资源为 那条数据的ID
			uploadFiles.setBelongId(resId);
			//所属资源为 该员工ID
			uploadFiles.setResourceId(eid);
			this.uploadFilesService.add(uploadFiles);
			imgList.add(uploadFiles);
		}
		return ResultFactory.generateRequestResult(imgList);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult deleteUploadFiles(String eid, String fileId) {
		//判断该资源是否存在 以及 员工是否存在
		UploadFiles uploadFiles = this.uploadFilesService.findById(fileId);
		if(uploadFiles==null||!uploadFiles.getResourceId().equals(eid)){
			return ResultFactory.generateSuccessResult();
		}
		this.uploadFilesService.deleteById(fileId);
		AppBeanInjector.baseFileUploadComponent.deleteFileByDir(AppBeanInjector.configuration.getUploadFileRootDir().concat(uploadFiles.getPath()));
		return ResultFactory.generateSuccessResult();
	}

	@Override
	public RequestResult findDeptAndMemberList(String currBranchId) {
		List<Dept> all = this.deptService.findAll(currBranchId);
		List result=new ArrayList();
		if(all!=null&&all.size()>0){
			for(Dept dept:all) {
				MapContext mapContext=MapContext.newOne();
				String deptId=dept.getId();
				String deptName=dept.getName();
				List<MapContext> employeeNameByDeptId = this.deptMemberService.findEmployeeByDeptId(deptId);
				mapContext.put("deptId",deptId);
                mapContext.put("title",deptName);
                mapContext.put("children",employeeNameByDeptId);
                result.add(mapContext);
			}
		}

		return ResultFactory.generateRequestResult(result);
	}
}
