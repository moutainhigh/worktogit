package com.lwxf.industry4.webapp.domain.dto.dept;

import java.util.ArrayList;
import java.util.List;

import com.lwxf.industry4.webapp.domain.dto.companyEmployee.CompanyEmployeeDto;
import com.lwxf.industry4.webapp.domain.entity.org.Dept;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/10/010 9:27
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class DeptDto {
	private List<CompanyEmployeeDto> companyEmployeeList;
	private List<DeptDto> deptList;
	private Dept dept;

	public List<CompanyEmployeeDto> getCompanyEmployeeList() {
		return companyEmployeeList;
	}

	public void setCompanyEmployeeList(List<CompanyEmployeeDto> companyEmployeeList) {
		this.companyEmployeeList = companyEmployeeList;
	}

	public List<DeptDto> getDeptList() {
		return deptList;
	}

	public void setDeptList(List<DeptDto> deptList) {
		this.deptList = deptList;
	}

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public DeptDto() {
		this.companyEmployeeList= new ArrayList<>();
		this.deptList = new ArrayList<>();
	}

	@Override
	public String toString() {
		return "DeptDto{" +
				"companyEmployeeList=" + companyEmployeeList +
				", deptList=" + deptList +
				", dept=" + dept +
				'}';
	}
}
