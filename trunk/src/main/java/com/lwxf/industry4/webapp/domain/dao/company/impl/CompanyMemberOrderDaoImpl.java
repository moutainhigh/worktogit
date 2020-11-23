package com.lwxf.industry4.webapp.domain.dao.company.impl;


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
import com.lwxf.industry4.webapp.domain.dao.company.CompanyMemberOrderDao;
import com.lwxf.industry4.webapp.domain.entity.company.CompanyMemberOrder;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2020-03-24 14:53:39
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("companyMemberOrderDao")
public class CompanyMemberOrderDaoImpl extends BaseDaoImpl<CompanyMemberOrder, String> implements CompanyMemberOrderDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<CompanyMemberOrder> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<CompanyMemberOrder> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public CompanyMemberOrder findByOrderIdAndType(String id, int value) {
		String sqlId=this.getNamedSqlId("findByOrderIdAndType");
		MapContext mapContext=MapContext.newOne();
		mapContext.put("orderId",id);
		mapContext.put("identity",value);
		return this.sqlSession.selectOne(sqlId,mapContext);
	}

}