package com.lwxf.industry4.webapp.domain.dto.rolePermission;

public class RoleMenuPermissionDtoForApp {
    String menuKey;
    Integer hasPermission;

    public String getMenuKey() {
        return menuKey;
    }

    public void setMenuKey(String menuKey) {
        this.menuKey = menuKey;
    }

    public Integer getHasPermission() {
        return hasPermission;
    }

    public void setHasPermission(Integer hasPermission) {
        this.hasPermission = hasPermission;
    }
}
