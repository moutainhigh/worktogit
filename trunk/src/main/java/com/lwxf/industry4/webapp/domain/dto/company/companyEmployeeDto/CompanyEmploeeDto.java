package com.lwxf.industry4.webapp.domain.dto.company.companyEmployeeDto;

import java.util.Date;
import java.util.List;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/3/28 0028 16:20
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class CompanyEmploeeDto {
	private String roleName;
	private String employeeName;
	private List<String> deptNames;
	private String userId;
	private String employeeId;
	private String corporatePartnersId;
	public String getCorporatePartnersId() {
		return corporatePartnersId;
	}
	public void setCorporatePartnersId(String corporatePartnersId) {
		this.corporatePartnersId = corporatePartnersId;
	}
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public List<String> getDeptNames() {
		return deptNames;
	}

	public void setDeptNames(List<String> deptNames) {
		this.deptNames = deptNames;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
}
