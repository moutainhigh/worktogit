package com.lwxf.industry4.webapp.domain.dao.company.impl;


import java.util.List;
import java.util.Map;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.company.companyShareMember.CompanyShareMemberDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.company.CompanyShareMemberDao;
import com.lwxf.industry4.webapp.domain.entity.company.CompanyShareMember;


/**
 * 功能：
 * 
 * @author：dongshibo(F_baisi@163.com)
 * @created：2018-12-05 11:24:07
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("companyShareMemberDao")
public class CompanyShareMemberDaoImpl extends BaseDaoImpl<CompanyShareMember, String> implements CompanyShareMemberDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<CompanyShareMemberDto> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//  过滤查询参数
		PageBounds pageBounds =
				this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<CompanyShareMemberDto> pageList =
				(PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public PaginatedList<Map> findDesigner(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findDesigner");
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<Map> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public List<CompanyShareMember> findShareMemberList(String companyId) {
		String sqlId=this.getNamedSqlId("findShareMemberList");

		return this.getSqlSession().selectList(sqlId,companyId);
	}


	@Override
	public CompanyShareMember findShareMemberByCUID(String companyId, String shareMemberId) {
		MapContext mapContext=MapContext.newOne();
		mapContext.put("companyId",companyId);
		mapContext.put("userId",shareMemberId);
		String sqlId=this.getNamedSqlId("findShareMemberByCUID");

		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public Map<String, String> findByDesigner(MapContext mapContext) {
		String sqlId = this.getNamedSqlId("findByDesigner");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public List<CompanyShareMember> findByOrderId(String id) {
		String sqlId = this.getNamedSqlId("findByOrderId");
		return this.getSqlSession().selectList(sqlId,id);
	}

	@Override
	public CompanyShareMember findByCidAndNameAndType(String currCompanyId, String name,Integer type) {
		String sqlId = this.getNamedSqlId("findByCidAndNameAndType");
		MapContext mapContext=MapContext.newOne();
		mapContext.put("companyId",currCompanyId);
		mapContext.put("name",name);
		mapContext.put("identity",type);
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}
}