package com.lwxf.industry4.webapp.bizservice.user.impl;


import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.bizservice.company.CompanyEmployeeService;
import com.lwxf.industry4.webapp.bizservice.user.UserRoleService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.UniqueKeyUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dao.company.CompanyEmployeeDao;
import com.lwxf.industry4.webapp.domain.dao.userRole.UserRoleDao;
import com.lwxf.industry4.webapp.domain.entity.userRole.UserRole;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 功能：
 * 
 * @author：lyh
 * @created：2020-01-07 13:47:50
 * @version：
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole, String, UserRoleDao> implements UserRoleService {





	@Resource

	@Override	public void setDao( UserRoleDao userRoleDao) {
		this.dao = userRoleDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<UserRole> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public RequestResult updateInsertUserRole(String uid,List<String> roleIds) {
		int i = this.dao.deleteById(uid);//修改sql支持跟uid删除
		if(roleIds.size()<1){
			return ResultFactory.generateSuccessResult();
		}
		for (String roleId:roleIds){
			UserRole userRole = new UserRole();
			userRole.setRoleId(roleId);
			userRole.setUserId(uid);
			String id = UniqueKeyUtil.getStringId();
			userRole.setId(id);
			this.dao.insert(userRole);
		}
		return ResultFactory.generateSuccessResult();
	}

	@Override
	public RequestResult getUserRole() {
		String currUserId = WebUtils.getCurrUserId();
        PaginatedFilter paginatedFilter = new PaginatedFilter();
        MapContext mapContext = new MapContext();
        mapContext.put("userId",currUserId);
        paginatedFilter.setFilters(mapContext);
        PaginatedList<UserRole> userRolePaginatedList = this.selectByFilter(paginatedFilter);

        return ResultFactory.generateRequestResult(userRolePaginatedList);
	}

























}