package com.lwxf.industry4.webapp.domain.dao.customorder.impl;


import java.util.List;
import java.util.Map;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lwxf.industry4.webapp.domain.dto.financing.PaymentProduceOrderDto;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.customorder.ProduceFlowDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.customorder.ProduceFlowDao;
import com.lwxf.industry4.webapp.domain.entity.customorder.ProduceFlow;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@126.com)
 * @created：2019-04-11 17:38:26
 * @version：2019 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("produceFlowDao")
public class ProduceFlowDaoImpl extends BaseDaoImpl<ProduceFlow, String> implements ProduceFlowDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<ProduceFlow> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<ProduceFlow> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public List<Map> findByProduceOrderId(String produceOrderId) {
		String sqlId = this.getNamedSqlId("findByProduceOrderId");
		return this.getSqlSession().selectList(sqlId,produceOrderId);
	}

	@Override
	public ProduceFlow findOneByProduceIdAndNode(String id, Integer node) {
		String sqlId = this.getNamedSqlId("findOneByProduceIdAndNode");
		MapContext mapContext = new MapContext();
		mapContext.put(WebConstant.KEY_ENTITY_ID,id);
		mapContext.put("node",node);
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public List<ProduceFlowDto> findListByProduceOrderId(String id) {
		String sqlId = this.getNamedSqlId("findListByProduceOrderId");
		return this.getSqlSession().selectList(sqlId,id);
	}

	@Override
	public ProduceFlowDto findOneById(String id) {
		String sqlId = this.getNamedSqlId("findOneById");
		return this.getSqlSession().selectOne(sqlId,id);
	}

	@Override
	public int deleteByOrderId(String orderId) {
		String sqlId = this.getNamedSqlId("deleteByOrderId");
		return this.getSqlSession().delete(sqlId,orderId);
	}


}