package com.lwxf.industry4.webapp.domain.dto.dept;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.lwxf.mybatis.utils.MapContext;

import java.util.List;

/**
 * 功能：修改员工信息时 使用
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/12/012 17:28
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value = "修改员工的用户、角色、部门、信息实体类",discriminator = "修改员工的用户、角色、部门、信息实体类")
public class UpdateUserRoleDeptDto {
	@ApiModelProperty(value = "所需修改的用户信息")
	private MapContext userInfo;
	@ApiModelProperty(value = "员工部门集合")
	private String[] deptIds;
	@ApiModelProperty(value = "角色ID")
	private String roleId;
	@ApiModelProperty(value = "角色ID组")
	private List<String> roleIds;

	@ApiModelProperty(value = "员工状态0 - 正常状态；1 - 禁用（删除）；2 - 离职")
	private Integer status;
	@ApiModelProperty(value = "所需修改的员工信息")
	private MapContext employeeInfo;
	@ApiModelProperty(value = "所需修改的用户基本信息")
	private MapContext userBasis;
	@ApiModelProperty(value = "员工编号")
	private String no;

	public List<String> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<String> roleIds) {
		this.roleIds = roleIds;
	}

	public MapContext getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(MapContext userInfo) {
		this.userInfo = userInfo;
	}

	public String[] getDeptIds() {
		return deptIds;
	}

	public void setDeptIds(String[] deptIds) {
		this.deptIds = deptIds;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public MapContext getEmployeeInfo() {
		return employeeInfo;
	}

	public void setEmployeeInfo(MapContext employeeInfo) {
		this.employeeInfo = employeeInfo;
	}

	public MapContext getUserBasis() {
		return userBasis;
	}

	public void setUserBasis(MapContext userBasis) {
		this.userBasis = userBasis;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}
}
