package com.lwxf.industry4.webapp.domain.dao.userRole.impl;


import java.util.List;
import java.util.Map;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.userRole.UserRoleDao;
import com.lwxf.industry4.webapp.domain.entity.userRole.UserRole;


/**
 * 功能：
 * 
 * @author：lyh
 * @created：2020-01-07 13:47:50
 * @version：
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("userRoleDao")
public class UserRoleDaoImpl extends BaseDaoImpl<UserRole, String> implements UserRoleDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<UserRole> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<UserRole> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public List<UserRole> getAllUserRole(String uid) {
		String sqlId=this.getNamedSqlId("getAllUserRole");
		MapContext mapContext=MapContext.newOne();
		mapContext.put("userId",uid);
		return this.getSqlSession().selectList(sqlId,mapContext);
	}









}