package com.lwxf.industry4.webapp.domain.dao.system.impl;


import java.util.List;
import java.util.Map;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lwxf.industry4.webapp.domain.dto.system.SysSmsDto;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.system.SysSmsDao;
import com.lwxf.industry4.webapp.domain.entity.system.SysSms;


/**
 * 功能：
 * 
 * @author：DJL(yuuyoo@163.com)
 * @created：2019-11-20 17:16:51
 * @version：2019 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("sysSmsDao")
public class SysSmsDaoImpl extends BaseDaoImpl<SysSms, String> implements SysSmsDao {

	@Override
	public Integer count() {
		String sqlId = this.getNamedSqlId("count");
		Integer count = this.getSqlSession().selectOne(sqlId);
		return count;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<SysSmsDto> selectDtoByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectDtoByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<SysSmsDto> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

}