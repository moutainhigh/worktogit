package com.lwxf.industry4.webapp.bizservice.system.impl;


import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.system.MenusDao;
import com.lwxf.industry4.webapp.bizservice.system.MenusService;
import com.lwxf.industry4.webapp.domain.entity.system.Menus;


/**
 * 功能：
 * 
 * @author：dongshibo(zjl869319827@outlook.com)
 * @created：2018-12-07 14:10:47
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("menusService")
public class MenusServiceImpl extends BaseServiceImpl<Menus, String, MenusDao> implements MenusService {


	@Resource

	@Override	public void setDao( MenusDao menusDao) {
		this.dao = menusDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<Menus> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public List<Menus> findAll() {
		return this.dao.findAll();
	}

	@Override
	public List<Menus> findListByMapContext(MapContext mapContext) {
		return this.dao.findListByMapContext(mapContext);
	}

	@Override
	public Menus findOneByKey(String key) {
		return this.dao.findOneByKey(key);
	}

	@Override
	public List<Menus> findByParentId(String id) {
		return this.dao.findByParentId(id);
	}

	@Override
	public int deletePermissionMenusByMenusId(String id) {
		Menus menus = this.dao.selectById(id);
		//删除角色菜单中的该菜单
		this.dao.deleteRolePermissionByKey(menus.getKey());
		//删除员工角色菜单中的该菜单
		this.dao.deleteEmployeePermissionByKey(menus.getKey());
		return 1;
	}

	@Override
	public int updateNameByLikeKey(String newName, String key) {
		return this.dao.updateNameByLikeKey(newName,key);
	}

	@Override
	public List<Menus> findListByLikeKey(String id) {
		return this.dao.findListByLikeKey(id);
	}

	@Override
	public List<Menus> findAllByTypeAndDisabled(int type, Boolean disabled) {
		return this.dao.findAllByTypeAndDisabled(type,disabled);
	}
}