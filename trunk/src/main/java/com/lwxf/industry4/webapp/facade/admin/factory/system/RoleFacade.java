package com.lwxf.industry4.webapp.facade.admin.factory.system;

import java.util.List;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.system.Role;
import com.lwxf.industry4.webapp.domain.entity.system.RolePermission;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/14/014 15:46
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface RoleFacade extends BaseFacade {
	RequestResult findListByType(Integer type,String key);

	RequestResult addRoles(Role role);

	RequestResult updataRoles(MapContext mapContext, String id);

	RequestResult deleteRoles(String id);

	RequestResult findRolePermission(String id);

	RequestResult updateRoleMenus(String roleId, List<RolePermission> rolePermissions);

	RequestResult findAllByType(Integer type);

	RequestResult settingDefault();

    RequestResult findListByBaseCode();

    RequestResult findRolesByRolekey();
}
