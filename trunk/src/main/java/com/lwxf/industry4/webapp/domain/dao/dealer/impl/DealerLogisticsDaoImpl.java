package com.lwxf.industry4.webapp.domain.dao.dealer.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lwxf.industry4.webapp.domain.dto.dealer.WxDealerLogisticsDto;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Repository;

import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.dealer.DealerLogisticsDao;
import com.lwxf.industry4.webapp.domain.entity.dealer.DealerLogistics;

import java.util.List;


/**
 * 功能：
 * 
 * @author：DJL(yuuyoo@163.com)
 * @created：2019-11-28 11:32:45
 * @version：2019 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("dealerLogisticsDao")
public class DealerLogisticsDaoImpl extends BaseDaoImpl<DealerLogistics, String> implements DealerLogisticsDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<DealerLogistics> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<DealerLogistics> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<WxDealerLogisticsDto> selectDtoByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectDtoByFilter");
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<WxDealerLogisticsDto> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public WxDealerLogisticsDto selectDtoById(String id) {
		String sqlId = this.getNamedSqlId("selectDtoById");
		return this.getSqlSession().selectOne(sqlId, id);
	}

	@Override
	public WxDealerLogisticsDto selectDefaultDto(String cid) {
		String sqlId = this.getNamedSqlId("selectDefaultDto");
		return this.getSqlSession().selectOne(sqlId, cid);
	}

	@Override
	public DealerLogistics selectByMap(MapContext mapContext) {
		String sqlId = this.getNamedSqlId("selectByMap");
		return this.getSqlSession().selectOne(sqlId, mapContext);
	}

	@Override
	public Integer countByCid(String cid) {
		String sqlId = this.getNamedSqlId("countByCid");
		return this.getSqlSession().selectOne(sqlId, cid);
	}

	@Override
	public Integer cancelCheckedByCid(String cid) {
		String sqlId = this.getNamedSqlId("cancelCheckedByCid");
		return this.getSqlSession().selectOne(sqlId, cid);
	}

	@Override
	public List<WxDealerLogisticsDto> findByCid(String cid) {
		String sqlId = this.getNamedSqlId("findByCid");
		return this.getSqlSession().selectList(sqlId, cid);
	}

	@Override
	public Integer deleteByCid(String cid) {
		String sqlId = this.getNamedSqlId("deleteByCid");
		return this.getSqlSession().selectOne(sqlId, cid);
	}

}