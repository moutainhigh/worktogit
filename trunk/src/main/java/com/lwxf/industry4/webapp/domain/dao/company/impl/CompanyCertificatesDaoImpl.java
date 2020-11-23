package com.lwxf.industry4.webapp.domain.dao.company.impl;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.company.CompanyCertificatesDao;
import com.lwxf.industry4.webapp.domain.dto.company.CompanyCertificatesDto;
import com.lwxf.industry4.webapp.domain.entity.company.CompanyCertificates;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2020-08-20 10:15:09
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("companyCertificatesDao")
public class CompanyCertificatesDaoImpl extends BaseDaoImpl<CompanyCertificates, String> implements CompanyCertificatesDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<CompanyCertificates> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<CompanyCertificates> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public List<CompanyCertificatesDto> findByCid(String cid) {
		String sqlId=this.getNamedSqlId("findByCid");
		return this.getSqlSession().selectList(sqlId,cid);
	}

}