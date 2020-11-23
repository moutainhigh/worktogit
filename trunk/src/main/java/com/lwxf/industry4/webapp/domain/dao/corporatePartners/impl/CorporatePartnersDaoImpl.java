package com.lwxf.industry4.webapp.domain.dao.corporatePartners.impl;


import java.util.List;
import java.util.Map;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.corporatePartners.CorporatePartnersDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.corporatePartners.CorporatePartnersDao;
import com.lwxf.industry4.webapp.domain.entity.corporatePartners.CorporatePartners;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@126.com)
 * @created：2019-08-01 14:13:42
 * @version：2019 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("corporatePartnersDao")
public class CorporatePartnersDaoImpl extends BaseDaoImpl<CorporatePartners, String> implements CorporatePartnersDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<CorporatePartners> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<CorporatePartners> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public PaginatedList<CorporatePartnersDto> findListByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findListByFilter");
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<CorporatePartnersDto> partnersDtos = (PageList)this.getSqlSession().selectList(sqlId,paginatedFilter.getFilters(),pageBounds);
		return this.toPaginatedList(partnersDtos);
	}

	@Override
	public CorporatePartnersDto findCorporatePartnersInfo(String id) {
		String sqlId = this.getNamedSqlId("findCorporatePartnersInfo");
		return this.getSqlSession().selectOne(sqlId,id);
	}

	@Override
	public CorporatePartnersDto findCorporateInfoByName(String coordinationName) {
		String sqlId = this.getNamedSqlId("findCorporateInfoByName");
		return this.getSqlSession().selectOne(sqlId,coordinationName);
	}

	@Override
	public CorporatePartners findByTel(String tel) {
		String sqlId = this.getNamedSqlId("findByTel");
		return this.getSqlSession().selectOne(sqlId,tel);
	}

}