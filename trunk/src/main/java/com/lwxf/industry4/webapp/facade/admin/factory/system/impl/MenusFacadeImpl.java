package com.lwxf.industry4.webapp.facade.admin.factory.system.impl;

import javax.annotation.Resource;

import java.util.*;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lwxf.industry4.webapp.bizservice.system.MenusService;
import com.lwxf.industry4.webapp.bizservice.system.RolePermissionService;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.shiro.LwxfShiroRealm;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.entity.company.EmployeePermission;
import com.lwxf.industry4.webapp.domain.entity.system.Menus;
import com.lwxf.industry4.webapp.domain.entity.system.RolePermission;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.admin.factory.system.MenusFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2019/1/3/003 16:11
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("menusFacade")
public class MenusFacadeImpl extends BaseFacadeImpl implements MenusFacade {
	@Resource(name = "menusService")
	private MenusService menusService;
	@Resource(name = "rolePermissionService")
	private RolePermissionService rolePermissionService;
	@Override
	public RequestResult findList(MapContext mapContext) {
        List<Menus> listByMapContext = this.menusService.findListByMapContext(mapContext);
        return ResultFactory.generateRequestResult(listByMapContext);
	}
	@Override
	public RequestResult findListPession(MapContext mapContext) {
        List<Menus> listByMapContext = this.menusService.findListByMapContext(mapContext);
        Iterator<Menus> iterator = listByMapContext.iterator();
        List<Menus> listByMapChild = new ArrayList<>();
        while (iterator.hasNext()){
            Menus menus = iterator.next();
            String parentId = menus.getParentId();
            if(parentId != null){
                listByMapChild.add(menus);
                iterator.remove();
            }

        }
        for (Menus menus:listByMapContext){
            for (Menus menuc:listByMapChild){
                String id = menus.getId();
                String parentId = menuc.getParentId();
                if(id.equals(parentId)){
                    List<Menus> submenus = menus.getSubmenus();
                    if(submenus == null){
                        submenus = new ArrayList<>();
                        menus.setSubmenus(submenus);
                    }
                    submenus.add(menuc);

                }

            }
        }
        return ResultFactory.generateRequestResult(listByMapContext);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addMenus(Menus menus) {
		//如果是二级菜单就判断以及一级菜单是否存在
		if(menus.getParentId()!=null){//创建二级菜单
			Menus parentMenus = this.menusService.findById(menus.getParentId());
			if(parentMenus==null){
				return ResultFactory.generateResNotFoundResult();
			}
			//判断是不是文件夹类菜单
			if(!parentMenus.getFolder()){
				return ResultFactory.generateErrorResult(ErrorCodes.BIZ_NOT_ALLOW_OPERATION_10020,AppBeanInjector.i18nUtil.getMessage("BIZ_NOT_ALLOW_OPERATION_10020"));
			}
			//判断key是否重复,如果存在，把已存在的挪到二级
			Menus keyMenus = this.menusService.findOneByKey(menus.getKey());
			if(keyMenus!=null){
				MapContext mapContext=MapContext.newOne();
				String menusId=keyMenus.getId();
                mapContext.put("id",menusId);
                mapContext.put("parentId",menus.getParentId());
                if(menus.getSort()!=null&&!menus.getSort().equals("")) {
					mapContext.put("sort", menus.getSort());
				}
                this.menusService.updateByMapContext(mapContext);
			}else {
				this.menusService.add(menus);
			}
		}else {//添加一级
			//判断key是否重复
			Menus keyMenus = this.menusService.findOneByKey(menus.getKey());
			if(keyMenus!=null){
				//判断是否为二级菜单
				if(keyMenus.getParentId()!=null){
					//删除角色和员工的关联信息
					this.menusService.deletePermissionMenusByMenusId(keyMenus.getId());
					//删除菜单
					this.menusService.deleteById(keyMenus.getId());
					//新增菜单
					this.menusService.add(menus);

				}else {
					return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_NOT_ALLOWED_REPEAT, AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOT_ALLOWED_REPEAT"));
				}
			}else {
				this.menusService.add(menus);
			}
		}
		return ResultFactory.generateRequestResult(menus);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updateMenus(MapContext mapContext, String id) {
		mapContext.put(WebConstant.KEY_ENTITY_ID,id);
		this.menusService.updateByMapContext(mapContext);
		Boolean disabled = mapContext.getTypedValue(WebConstant.KEY_ENTITY_DISABLED, Boolean.class);
		if(disabled!=null&&disabled){
			this.menusService.deletePermissionMenusByMenusId(id);
			//清除shiro缓存
			LwxfShiroRealm lwxfShiroRealm = new LwxfShiroRealm();
			lwxfShiroRealm.clearCachedAuthorizationInfo(WebUtils.getCurrUserId());
		}
		return ResultFactory.generateRequestResult(this.menusService.findById(id));
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult deleteMenus(String id) {
		Menus menus = this.menusService.findById(id);
		if(menus==null){
			return ResultFactory.generateSuccessResult();
		}
		//判断是否被角色权限表引用
 		List<RolePermission> rolePermission = this.rolePermissionService.findByMenusKey(menus.getKey());
		if (rolePermission!=null&&rolePermission.size()!=0){
			//return ResultFactory.generateErrorResult(ErrorCodes.BIZ_RES_BE_IN_USE_10006,AppBeanInjector.i18nUtil.getMessage("BIZ_RES_BE_IN_USE_10006"));
			//删除角色内的菜单权限
			for(RolePermission permission:rolePermission){
				this.rolePermissionService.deleteById(permission.getId());
			}

		}
		//判断是否存在下级菜单
		List<Menus> menusList = this.menusService.findByParentId(id);
		if (menusList!=null&&menusList.size()!=0){
			//return ResultFactory.generateErrorResult(ErrorCodes.BIZ_RES_BE_IN_USE_10006,AppBeanInjector.i18nUtil.getMessage("BIZ_RES_BE_IN_USE_10006"));
			//删除下级菜单
			for(Menus menu:menusList){
				this.menusService.deleteById(menu.getId());
			}
		}
		this.menusService.deleteById(id);
		return ResultFactory.generateSuccessResult();
	}
}
