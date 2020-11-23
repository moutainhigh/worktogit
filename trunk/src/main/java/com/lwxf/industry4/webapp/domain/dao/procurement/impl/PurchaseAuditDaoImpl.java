package com.lwxf.industry4.webapp.domain.dao.procurement.impl;


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
import com.lwxf.industry4.webapp.domain.dao.procurement.PurchaseAuditDao;
import com.lwxf.industry4.webapp.domain.entity.procurement.PurchaseAudit;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-10-21 15:42:56
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("purchaseAuditDao")
public class PurchaseAuditDaoImpl extends BaseDaoImpl<PurchaseAudit, String> implements PurchaseAuditDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<PurchaseAudit> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<PurchaseAudit> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public List<PurchaseAudit> selectAuditByRequestId(String id) {
		String sqlId=this.getNamedSqlId("selectAuditByRequestId");
		return this.getSqlSession().selectList(sqlId,id);
	}

	@Override
	public PurchaseAudit findByRequestIdAndLoginUserId(String id, String currUserId, Integer value) {
		MapContext mapContext=MapContext.newOne();
		mapContext.put("requestId",id);
		mapContext.put("userId",currUserId);
		mapContext.put("type",value);
		String sqlId=this.getNamedSqlId("findByRequestIdAndLoginUserId");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public List<Integer> findTypeByRequestIdAndUid(String id, String currUserId) {
		MapContext mapContext=MapContext.newOne();
		mapContext.put("requestId",id);
		mapContext.put("userId",currUserId);
		String sqlId=this.getNamedSqlId("findTypeByRequestIdAndUid");
		return this.getSqlSession().selectList(sqlId,mapContext);
	}

}