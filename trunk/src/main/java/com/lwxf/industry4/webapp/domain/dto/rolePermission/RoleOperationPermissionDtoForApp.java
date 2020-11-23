package com.lwxf.industry4.webapp.domain.dto.rolePermission;

public class RoleOperationPermissionDtoForApp {

    String menuKey;
    String operationName;
    Integer hasPermission;

    public String getMenuKey() {
        return menuKey;
    }

    public void setMenuKey(String menuKey) {
        this.menuKey = menuKey;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public Integer getHasPermission() {
        return hasPermission;
    }

    public void setHasPermission(Integer hasPermission) {
        this.hasPermission = hasPermission;
    }
}
