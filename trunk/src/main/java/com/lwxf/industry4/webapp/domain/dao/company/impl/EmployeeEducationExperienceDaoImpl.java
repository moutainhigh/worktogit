package com.lwxf.industry4.webapp.domain.dao.company.impl;


import java.util.List;
import java.util.Map;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.dept.EmployeeEducationExperienceDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.company.EmployeeEducationExperienceDao;
import com.lwxf.industry4.webapp.domain.entity.company.EmployeeEducationExperience;


/**
 * 功能：
 * 
 * @author：dongshibo(F_baisi@163.com)
 * @created：2019-04-04 15:32:29
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("employeeEducationExperienceDao")
public class EmployeeEducationExperienceDaoImpl extends BaseDaoImpl<EmployeeEducationExperience, String> implements EmployeeEducationExperienceDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<EmployeeEducationExperience> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<EmployeeEducationExperience> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public List<EmployeeEducationExperienceDto> findListByEid(String eid) {
		String sqlId = this.getNamedSqlId("findListByEid");
		return this.getSqlSession().selectList(sqlId,eid);
	}

}