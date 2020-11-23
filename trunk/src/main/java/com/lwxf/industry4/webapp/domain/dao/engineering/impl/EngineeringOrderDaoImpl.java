package com.lwxf.industry4.webapp.domain.dao.engineering.impl;


import java.util.List;
import java.util.Map;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lwxf.industry4.webapp.domain.dto.customorder.OrderProductDto;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.engineering.EngineeringOrderDao;
import com.lwxf.industry4.webapp.domain.entity.engineering.EngineeringOrder;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2020-05-27 18:14:09
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("engineeringOrderDao")
public class EngineeringOrderDaoImpl extends BaseDaoImpl<EngineeringOrder, String> implements EngineeringOrderDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<EngineeringOrder> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<EngineeringOrder> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public MapContext findListByEngineeringId(String engineeringId) {
		String sqlId=this.getNamedSqlId("findListByEngineeringId");
		return this.getSqlSession().selectOne(sqlId,engineeringId);
	}

	@Override
	public List<EngineeringOrder> findByEngineeringId(String engineeringId) {
		String sqlId=this.getNamedSqlId("findByEngineeringId");
		return this.getSqlSession().selectList(sqlId,engineeringId);
	}

	@Override
	public OrderProductDto findProductByOrderId(String customOrderId) {
		String sqlId=this.getNamedSqlId("findProductByOrderId");
		return this.getSqlSession().selectOne(sqlId,customOrderId);
	}

}