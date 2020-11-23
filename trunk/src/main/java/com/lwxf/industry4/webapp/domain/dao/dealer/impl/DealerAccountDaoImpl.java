package com.lwxf.industry4.webapp.domain.dao.dealer.impl;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.dealer.DealerAccountDao;
import com.lwxf.industry4.webapp.domain.entity.dealer.DealerAccount;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-20 18:46:16
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("dealerAccountDao")
public class DealerAccountDaoImpl extends BaseDaoImpl<DealerAccount, String> implements DealerAccountDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<DealerAccount> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<DealerAccount> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public List<DealerAccount> findByCompanIdAndNature(String companyId, Integer nature) {
		String sqlId = this.getNamedSqlId("findByCompanIdAndNature");
		MapContext mapContext = MapContext.newOne();
		mapContext.put("companyId",companyId);
		mapContext.put("nature",nature);
		return this.getSqlSession().selectList(sqlId,mapContext);
	}

	@Override
	public DealerAccount findByCompanIdAndNatureAndType(String companyId, Integer nature, Integer type) {
		String sqlId = this.getNamedSqlId("findByCompanIdAndNatureAndType");
		MapContext mapContext = MapContext.newOne();
		mapContext.put("companyId",companyId);
		mapContext.put("nature",nature);
		mapContext.put("type",type);
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}
}