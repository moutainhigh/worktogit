package com.lwxf.industry4.webapp.bizservice.system.impl;


import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.system.RoleDao;
import com.lwxf.industry4.webapp.bizservice.system.RoleService;
import com.lwxf.industry4.webapp.domain.entity.system.Role;


/**
 * 功能：
 * 
 * @author：dongshibo(zjl869319827@outlook.com)
 * @created：2018-12-07 14:10:48
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role, String, RoleDao> implements RoleService {


	@Resource

	@Override	public void setDao( RoleDao roleDao) {
		this.dao = roleDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<Role> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public Role selectByKey(String key,String branchId) {
		return this.dao.selectByKey(key,branchId);
	}

	@Override
	public List<Role> findListByType(Integer type,String key,String branchId) {
		return this.dao.findListByType(type,key,branchId);
	}


	@Override
	public Role findRoleByCidAndUid(String userId, String companyId) {
		return this.dao.findRoleByCidAndUid(userId,companyId);
	}

}