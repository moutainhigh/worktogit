package com.lwxf.industry4.webapp.domain.dao.dispatch.impl;


import java.util.List;
import java.util.Map;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.dispatch.DispatchBillItemDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.dispatch.DispatchBillItemDao;
import com.lwxf.industry4.webapp.domain.entity.dispatch.DispatchBillItem;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-20 15:10:29
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("dispatchBillItemDao")
public class DispatchBillItemDaoImpl extends BaseDaoImpl<DispatchBillItem, String> implements DispatchBillItemDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<DispatchBillItem> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<DispatchBillItem> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public List<DispatchBillItem> findListByOrderId(String orderId) {
		String sql = this.getNamedSqlId("findListByOrderId");
		return this.getSqlSession().selectList(sql,orderId);
	}

	@Override
	public Integer findDispatchItemCountByOrderId(String dispatchBillId) {
		String sql = this.getNamedSqlId("findDispatchItemCountByOrderId");
		return this.getSqlSession().selectOne(sql,dispatchBillId);
	}

	@Override
	public Integer findDispatchItemCountByDispatchIdAndType(String dispatchBillId, Integer type) {
		String sql = this.getNamedSqlId("findDispatchItemCountByDispatchIdAndType");
		MapContext mapContext=MapContext.newOne();
		mapContext.put("dispatchBillId",dispatchBillId);
		mapContext.put("type",type);
		return this.getSqlSession().selectOne(sql,mapContext);
	}

	@Override
	public MapContext findLogisticsByDispatchId(String dispatchBillId) {
		String sql = this.getNamedSqlId("findLogisticsByDispatchId");
		return this.getSqlSession().selectOne(sql,dispatchBillId);
	}

	@Override
	public List<MapContext> findByDispatchBillId(String dispatchBillId) {
		String sqlId=this.getNamedSqlId("findByDispatchBillId");
		return this.getSqlSession().selectList(sqlId,dispatchBillId);
	}

	@Override
	public int deleteByOrderId(String orderId) {
		String sqlId = this.getNamedSqlId("deleteByOrderId");
		return this.getSqlSession().delete(sqlId,orderId);
	}

	@Override
	public List<DispatchBillItemDto> findListByDIdAndTypes(String id, List<Integer> types) {
		String sqlId = this.getNamedSqlId("findListByDIdAndTypes");
		MapContext mapContext = new MapContext();
		mapContext.put(WebConstant.KEY_ENTITY_ID,id);
		mapContext.put("types",types);
		return this.getSqlSession().selectList(sqlId,mapContext);
	}

	@Override
	public List<DispatchBillItem> findListByDispatchBillId(String dispatchBillId) {
		String sqlId=this.getNamedSqlId("findListByDispatchBillId");
		return this.getSqlSession().selectList(sqlId,dispatchBillId);
	}

}