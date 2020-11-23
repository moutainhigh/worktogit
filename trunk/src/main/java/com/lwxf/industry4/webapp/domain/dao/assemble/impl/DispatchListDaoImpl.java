package com.lwxf.industry4.webapp.domain.dao.assemble.impl;


import java.util.List;
import java.util.Map;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.aftersale.DateNum;
import com.lwxf.industry4.webapp.domain.dto.dispatchList.DispatchListDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.assemble.DispatchListDao;
import com.lwxf.industry4.webapp.domain.entity.assemble.DispatchList;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2018-12-19 15:05:23
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("dispatchListDao")
public class DispatchListDaoImpl extends BaseDaoImpl<DispatchList, String> implements DispatchListDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<DispatchList> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<DispatchList> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public PaginatedList<DispatchListDto> findDispatchListByCid(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findDispatchListByCid");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<DispatchListDto> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public DispatchListDto findDipatchListById(String dispatchListId) {
		String sqlId = this.getNamedSqlId("findDipatchListById");
		return this.getSqlSession().selectOne(sqlId, dispatchListId);
	}

	@Override
	public Integer findDiscountByCidAndStatus(MapContext mapContext) {
		String sqlId = this.getNamedSqlId("findDiscountByCidAndStatus");
		return this.getSqlSession().selectOne(sqlId, mapContext);
	}

	@Override
	public List<DateNum> findDatenumByCreatedAndCid(MapContext mapContext) {
		String sqlId = this.getNamedSqlId("findDatenumByCreatedAndCid");
		return this.getSqlSession().selectList(sqlId, mapContext);
	}

	@Override
	public String findTimeByOrderId(String orderId) {
		String sqlId = this.getNamedSqlId("findTimeByOrderId");
		return this.getSqlSession().selectOne(sqlId, orderId);
	}
}