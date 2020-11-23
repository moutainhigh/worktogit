package com.lwxf.industry4.webapp.domain.dto.dept;

import com.lwxf.industry4.webapp.domain.entity.userRole.UserRole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Map;

import com.lwxf.industry4.webapp.domain.dto.companyEmployee.CompanyEmployeeDto;
import com.lwxf.industry4.webapp.domain.entity.company.*;
import com.lwxf.industry4.webapp.domain.entity.user.UserBasis;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/4/7/007 10:41
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value = "公司员工信息详情",description = "公司员工信息详情")
public class CompanyEmployeeInfoDto {
	@ApiModelProperty(value = "公司员工信息",name = "companyEmployee")
	private EmployeeDeptDto employeeDeptDto;//查询公司员工信息表
	@ApiModelProperty(value = "员工工作经历",name="employeeExperienceList")
	private List<EmployeeExperienceDto> employeeExperienceList;//查询员工工作经历表
	@ApiModelProperty(value = "员工信息",name = "employeeInfo")
	private EmployeeInfo employeeInfo;//查询员工信息表
	@ApiModelProperty(value = "员工证书信息",name = "employeeCertificateList")
	private List<EmployeeCertificateDto> employeeCertificateList;//查询员工证书信息表
	@ApiModelProperty(value = "员工教育经历",name = "employeeEducationExperienceList")
	private List<EmployeeEducationExperienceDto> employeeEducationExperienceList;//查询员工教育经历表
	@ApiModelProperty(value = "员工考核信息",name = "employeeAssessmentList")
	private List<EmployeeAssessmentDto> employeeAssessmentList;//查询员工考核信息表
	@ApiModelProperty(value = "用户基础信息")
	private UserBasis userBasis;
	@ApiModelProperty(value = "角色ID组")
	private List<UserRole> roleIds;

	public List<UserRole> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<UserRole> roleIds) {
		this.roleIds = roleIds;
	}

	public EmployeeDeptDto getEmployeeDeptDto() {
		return employeeDeptDto;
	}

	public void setEmployeeDeptDto(EmployeeDeptDto employeeDeptDto) {
		this.employeeDeptDto = employeeDeptDto;
	}

	public List<EmployeeExperienceDto> getEmployeeExperienceList() {
		return employeeExperienceList;
	}

	public void setEmployeeExperienceList(List<EmployeeExperienceDto> employeeExperienceList) {
		this.employeeExperienceList = employeeExperienceList;
	}

	public EmployeeInfo getEmployeeInfo() {
		return employeeInfo;
	}

	public void setEmployeeInfo(EmployeeInfo employeeInfo) {
		this.employeeInfo = employeeInfo;
	}

	public List<EmployeeCertificateDto> getEmployeeCertificateList() {
		return employeeCertificateList;
	}

	public void setEmployeeCertificateList(List<EmployeeCertificateDto> employeeCertificateList) {
		this.employeeCertificateList = employeeCertificateList;
	}

	public List<EmployeeEducationExperienceDto> getEmployeeEducationExperienceList() {
		return employeeEducationExperienceList;
	}

	public void setEmployeeEducationExperienceList(List<EmployeeEducationExperienceDto> employeeEducationExperienceList) {
		this.employeeEducationExperienceList = employeeEducationExperienceList;
	}

	public List<EmployeeAssessmentDto> getEmployeeAssessmentList() {
		return employeeAssessmentList;
	}

	public void setEmployeeAssessmentList(List<EmployeeAssessmentDto> employeeAssessmentList) {
		this.employeeAssessmentList = employeeAssessmentList;
	}

	public UserBasis getUserBasis() {
		return userBasis;
	}

	public void setUserBasis(UserBasis userBasis) {
		this.userBasis = userBasis;
	}
}
