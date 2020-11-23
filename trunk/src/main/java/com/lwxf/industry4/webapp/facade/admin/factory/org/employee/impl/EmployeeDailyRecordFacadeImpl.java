package com.lwxf.industry4.webapp.facade.admin.factory.org.employee.impl;

import javax.annotation.Resource;

import java.util.*;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lwxf.industry4.webapp.bizservice.company.CompanyEmployeeService;
import com.lwxf.industry4.webapp.bizservice.company.EmployeeDailyRecordCommentService;
import com.lwxf.industry4.webapp.bizservice.company.EmployeeDailyRecordService;
import com.lwxf.industry4.webapp.bizservice.dept.DeptMemberService;
import com.lwxf.industry4.webapp.bizservice.dept.DeptService;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.company.EmployeeDailyRecordRecordState;
import com.lwxf.industry4.webapp.common.enums.company.EmployeeDailyRecordVisibleState;
import com.lwxf.industry4.webapp.common.enums.user.UserType;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.companyEmployee.CompanyEmployeeDto;
import com.lwxf.industry4.webapp.domain.entity.company.CompanyEmployee;
import com.lwxf.industry4.webapp.domain.entity.company.EmployeeDailyRecord;
import com.lwxf.industry4.webapp.domain.entity.company.EmployeeDailyRecordComment;
import com.lwxf.industry4.webapp.domain.entity.org.Dept;
import com.lwxf.industry4.webapp.domain.entity.org.DeptMember;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.org.employee.EmployeeDailyRecordFacade;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.mybatis.utils.DateUtil;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/5/25/025 14:31
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("fEmployeeDailyRecordFacade")
public class EmployeeDailyRecordFacadeImpl extends BaseFacadeImpl implements EmployeeDailyRecordFacade {

	@Resource(name = "employeeDailyRecordService")
	private EmployeeDailyRecordService employeeDailyRecordService;
	@Resource(name = "employeeDailyRecordCommentService")
	private EmployeeDailyRecordCommentService employeeDailyRecordCommentService;
	@Resource(name = "deptMemberService")
	private DeptMemberService deptMemberService;
	@Resource(name = "companyEmployeeService")
	private CompanyEmployeeService companyEmployeeService;
	@Resource(name = "deptService")
	private DeptService deptService;

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addDailyrecord(EmployeeDailyRecord employeeDailyRecord) {
		this.employeeDailyRecordService.add(employeeDailyRecord);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	public RequestResult findDailyrecord(MapContext mapContext, Integer pageSize, Integer pageNum) {
		PaginatedFilter paginatedFilter = new PaginatedFilter();

		//判断当前操作人是否是系统账户
		Integer type = WebUtils.getCurrUser().getType();
		if(!type.equals(UserType.ADMIN.getValue())&&!type.equals(UserType.SUPER_ADMIN.getValue())){
			//判断当前登录人 是否是总经办 的人
			CompanyEmployee companyEmployee = this.companyEmployeeService.findOneByCompanyIdAndUserId(WebUtils.getCurrCompanyId(), WebUtils.getCurrUserId());
			String zjbKey = "zjb";
			Dept deptByKey = this.deptService.findDeptByKey(zjbKey,WebUtils.getCurrBranchId());
			DeptMember deptMember = this.deptMemberService.findOneByDeptIdAndEmployeeId(deptByKey.getId(),companyEmployee.getId());
			if(deptMember==null){
				//判断是否是部门负责人
				if(companyEmployee.getSupervisor().equals(1)){
					List<DeptMember> deptMembers = this.deptMemberService.findDeptMemberListByEmployeeId(companyEmployee.getId());
					mapContext.put("deptMembers",deptMembers);
				}else{
					mapContext.put("createUser",WebUtils.getCurrUserId());
				}
			}else{
				mapContext.put("all",1);
			}
		}else{
			mapContext.put("all",1);
		}
		paginatedFilter.setFilters(mapContext);
		Pagination pagination = new Pagination();
		pagination.setPageSize(pageSize);
		pagination.setPageNum(pageNum);
		paginatedFilter.setPagination(pagination);
		List<Map<String, String>> sorts = new ArrayList<Map<String, String>>();
		Map<String,String> created = new HashMap<String, String>();
		created.put("create_time","desc");
		sorts.add(created);
		paginatedFilter.setSorts(sorts);
		return ResultFactory.generateRequestResult(this.employeeDailyRecordService.findListByFilter(paginatedFilter));
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updateDailyrecord(String id, MapContext mapContext) {
		//判断日志数据是否存在
		EmployeeDailyRecord dailyRecord = this.employeeDailyRecordService.findById(id);
		if(dailyRecord==null||dailyRecord.getRecordState().equals(EmployeeDailyRecordRecordState.DELETE.getValue())){
			return ResultFactory.generateResNotFoundResult();
		}
		//只允许本人修改
		if(!dailyRecord.getCreateUser().equals(WebUtils.getCurrUserId())){
			return ResultFactory.generateResNotFoundResult();
		}
		//超过24小时的不允许修改
		Date created = dailyRecord.getCreateTime();
		long anHour = 24 * 60 * 60 * 1000;//24小时的毫秒数
		long createdTime = created.getTime();
		long updateTime = DateUtil.now().getTime();
		long hours = (updateTime - createdTime) / anHour;
		if (hours > 1) {
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_NOT_ALLOW_OPERATION_10020, AppBeanInjector.i18nUtil.getMessage("BIZ_NOT_ALLOW_OPERATION_10020"));
		}
		mapContext.put(WebConstant.KEY_ENTITY_ID,id);
		this.employeeDailyRecordService.updateByMapContext(mapContext);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult deleteDailyrecord(String id) {
		//判断日志数据是否存在
		EmployeeDailyRecord dailyRecord = this.employeeDailyRecordService.findById(id);
		if(dailyRecord==null||dailyRecord.getRecordState().equals(EmployeeDailyRecordRecordState.DELETE.getValue())){
			return ResultFactory.generateSuccessResult();
		}
		//只允许本人删除
		if(!dailyRecord.getCreateUser().equals(WebUtils.getCurrUserId())){
			return ResultFactory.generateResNotFoundResult();
		}
		MapContext mapContext = new MapContext();
		mapContext.put("recordState",EmployeeDailyRecordRecordState.DELETE.getValue());
		mapContext.put("id",id);
		this.employeeDailyRecordService.updateByMapContext(mapContext);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addDailyrecordComment(String id,EmployeeDailyRecordComment employeeDailyRecordComment) {
		//判断日志数据是否存在
		EmployeeDailyRecord dailyRecord = this.employeeDailyRecordService.findById(id);
		if(dailyRecord==null||dailyRecord.getRecordState().equals(EmployeeDailyRecordRecordState.DELETE.getValue())){
			return ResultFactory.generateSuccessResult();
		}
		CompanyEmployee employee = this.companyEmployeeService.findOneByCompanyIdAndUserId(WebUtils.getCurrCompanyId(), WebUtils.getCurrUserId());
		//不是管理员的话  没有评论权限
		if(employee.getSupervisor().equals(0)){
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_NO_PERMISSION_10003,AppBeanInjector.i18nUtil.getMessage("BIZ_NO_PERMISSION_10003"));
		}
		this.employeeDailyRecordCommentService.add(employeeDailyRecordComment);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult deleteComment(String id, String commentId) {
		//判断评论是否存在
		EmployeeDailyRecordComment byId = this.employeeDailyRecordCommentService.findById(id);
		if(byId==null||!byId.getDailyRecordId().equals(id)){
			return ResultFactory.generateSuccessResult();
		}
		//只允许本人删除
		if(!byId.getCommentUser().equals(WebUtils.getCurrUserId())){
			return ResultFactory.generateResNotFoundResult();
		}
		this.employeeDailyRecordCommentService.deleteById(commentId);
		return ResultFactory.generateSuccessResult();
	}


}
