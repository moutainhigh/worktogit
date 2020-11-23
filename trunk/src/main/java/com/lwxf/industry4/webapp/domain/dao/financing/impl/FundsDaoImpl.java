package com.lwxf.industry4.webapp.domain.dao.financing.impl;


import java.util.List;
import java.util.Map;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.financing.FundsDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.financing.FundsDao;
import com.lwxf.industry4.webapp.domain.entity.financing.Funds;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-09-25 09:25:44
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("fundsDao")
public class FundsDaoImpl extends BaseDaoImpl<Funds, String> implements FundsDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<Funds> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<Funds> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public List<FundsDto> findFundsList(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findFundsList");
		return this.getSqlSession().selectList(sqlId,mapContext);
	}

	@Override
	public List<FundsDto> findByParentId(String id) {
		MapContext mapContext=MapContext.newOne();
		mapContext.put("parentId",id);
		String sqlId=this.getNamedSqlId("findByParentId");
		return this.getSqlSession().selectList(sqlId,mapContext);
	}

}