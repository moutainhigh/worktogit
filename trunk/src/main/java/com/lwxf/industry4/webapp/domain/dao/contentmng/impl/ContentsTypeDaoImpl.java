package com.lwxf.industry4.webapp.domain.dao.contentmng.impl;


import java.util.List;
import java.util.Map;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.contentmng.ContentsDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.contentmng.ContentsTypeDao;
import com.lwxf.industry4.webapp.domain.entity.contentmng.ContentsType;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-03-27 11:58:34
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("contentsTypeDao")
public class ContentsTypeDaoImpl extends BaseDaoImpl<ContentsType, String> implements ContentsTypeDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<ContentsType> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<ContentsType> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public List<ContentsType> findContentsTypeListByBranchId(String branchId) {
		String sqlId = this.getNamedSqlId("findContentsTypeListByBranchId");
		return this.getSqlSession().selectList(sqlId,branchId);
	}

	@Override
	public ContentsType findContentsListByCode(String code) {
		String sqlId=this.getNamedSqlId("findContentsListByCode");
		return this.getSqlSession().selectOne(sqlId,code);
	}



}