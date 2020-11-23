package com.lwxf.industry4.webapp.domain.dao.company.impl;


import java.util.List;
import java.util.Map;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.bizservice.base.BaseNoIdService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.base.BaseNoIdDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.company.DealerShippingLogisticsDao;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.entity.company.DealerShippingLogistics;


/**
 * 功能：
 * 
 * @author：dongshibo(F_baisi@163.com)
 * @created：2018-12-27 13:44:06
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("dealerShippingLogisticsDao")
public class DealerShippingLogisticsDaoImpl extends BaseNoIdDaoImpl<DealerShippingLogistics> implements DealerShippingLogisticsDao {

	@Override
	public DealerShippingLogistics selectByCompanyIdAndLogisticsCompanyId(String companyId, String logisticsCompanyId) {
		String sqlId = this.getNamedSqlId("selectByCompanyIdAndLogisticsCompanyId");
		//
		Map<String, Object> params = this.newParamMap();
		params.put("companyId", companyId);
		params.put("logisticsCompanyId", logisticsCompanyId);
		//
		return this.getSqlSession().selectOne(sqlId, params);
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<DealerShippingLogistics> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination());
		PageList<DealerShippingLogistics> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public DealerShippingLogistics findOneByCompanyId(String companyId) {
		String sqlId = this.getNamedSqlId("findOneByCompanyId");
		return this.getSqlSession().selectOne(sqlId,companyId);
	}

	@Override
	public int updateByCompanyId(MapContext updateDealerShipping) {
		String sqlId = this.getNamedSqlId("updateByCompanyId");
		return this.getSqlSession().update(sqlId,updateDealerShipping);
	}

}