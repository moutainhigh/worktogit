package com.lwxf.industry4.webapp.domain.dao.dealerEmployee.impl;


import java.util.List;
import java.util.Map;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.dealerEmployee.DealerEmployeeDao;
import com.lwxf.industry4.webapp.domain.entity.dealerEmployee.DealerEmployee;


/**
 * 功能：
 * 
 * @author：lyh
 * @created：2019-12-20 16:35:37
 * @version：
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("dealerEmployeeDao")
public class DealerEmployeeDaoImpl extends BaseDaoImpl<DealerEmployee, String> implements DealerEmployeeDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<DealerEmployee> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<DealerEmployee> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

}