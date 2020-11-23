package com.lwxf.industry4.webapp.domain.dto.rolePermission;

import com.lwxf.industry4.webapp.domain.entity.system.RolePermission;

public class RolePermissionDto extends RolePermission {
	String menuName;

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
}
