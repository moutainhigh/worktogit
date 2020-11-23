package com.lwxf.industry4.webapp.domain.dto.dept;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import com.lwxf.industry4.webapp.domain.dto.companyEmployee.CompanyEmployeeDto;
import com.lwxf.industry4.webapp.domain.entity.company.CompanyEmployee;
import com.lwxf.industry4.webapp.domain.entity.org.Dept;
import com.lwxf.industry4.webapp.domain.entity.user.User;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/12/012 11:51
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value = "员工部门Dto",discriminator = "员工部门Dto")
public class EmployeeDeptDto extends CompanyEmployeeDto {
	@ApiModelProperty(value = "部门集合")
	private List<Dept> deptList;

	public List<Dept> getDeptList() {
		return deptList;
	}

	public void setDeptList(List<Dept> deptList) {
		this.deptList = deptList;
	}

	public EmployeeDeptDto clone(CompanyEmployeeDto companyEmployeeDto){
		this.id=companyEmployeeDto.getId();
		this.userName = companyEmployeeDto.getUserName();
		this.sex = companyEmployeeDto.getSex();
		this.mobile = companyEmployeeDto.getMobile();
		this.roleName = companyEmployeeDto.getRoleName();
		this.companyId = companyEmployeeDto.getCompanyId();
		this.userId = companyEmployeeDto.getUserId();
		this.roleId = companyEmployeeDto.getRoleId();
		this.created = companyEmployeeDto.getCreated();
		this.status = companyEmployeeDto.getStatus();
		this.no = companyEmployeeDto.getNo();
		this.email=companyEmployeeDto.getEmail();
		this.birthday=companyEmployeeDto.getBirthday();
		this.userStatus=companyEmployeeDto.getUserStatus();
		this.enableLoginPc=companyEmployeeDto.getEnableLoginPc();
		this.enableLoginWx=companyEmployeeDto.getEnableLoginWx();
		return this;
	}
}
