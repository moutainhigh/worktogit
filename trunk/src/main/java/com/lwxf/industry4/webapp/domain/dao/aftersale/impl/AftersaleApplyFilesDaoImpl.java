package com.lwxf.industry4.webapp.domain.dao.aftersale.impl;


import java.util.List;
import java.util.Map;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.aftersale.AftersaleApplyFilesDao;
import com.lwxf.industry4.webapp.domain.entity.aftersale.AftersaleApplyFiles;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-01-02 20:27:26
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("aftersaleApplyFilesDao")
public class AftersaleApplyFilesDaoImpl extends BaseDaoImpl<AftersaleApplyFiles, String> implements AftersaleApplyFilesDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<AftersaleApplyFiles> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<AftersaleApplyFiles> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public List<AftersaleApplyFiles> findFilesByAfterId(String aftersaleId) {
		String sqlId=this.getNamedSqlId("findFilesByAfterId");
		return this.getSqlSession().selectList(sqlId,aftersaleId);
	}

	@Override
	public List<String> findByPath(List<String> pathList) {
		String sqlId = this.getNamedSqlId("findByPath");
		return this.getSqlSession().selectList(sqlId,pathList);
	}

	@Override
	public int updateFilesList(String id, List imgIds) {
		String sqlId = this.getNamedSqlId("updateFilesList");
		MapContext mapContext = new MapContext();
		mapContext.put(WebConstant.KEY_ENTITY_ID,id);
		mapContext.put("imgIds",imgIds);
		return this.getSqlSession().update(sqlId,mapContext);
	}

	@Override
	public List<AftersaleApplyFiles> findListByOrderId(String orderId) {
		String sqlId = this.getNamedSqlId("findListByOrderId");
		return this.getSqlSession().selectList(sqlId,orderId);
	}

	@Override
	public int deleteByOrderId(String orderId) {
		String sqlId = this.getNamedSqlId("deleteByOrderId");
		return this.getSqlSession().delete(sqlId,orderId);
	}

	@Override
	public List<AftersaleApplyFiles> findListByResultOrderId(String orderId) {
		String sqlId = this.getNamedSqlId("findListByResultOrderId");
		return this.getSqlSession().selectList(sqlId,orderId);
	}

	@Override
	public int deleteByResultOrderId(String orderId) {
		String sqlId = this.getNamedSqlId("deleteByResultOrderId");
		return this.getSqlSession().delete(sqlId,orderId);
	}

	@Override
	public int updateByIds(MapContext mapContext) {
		String sqlId = this.getNamedSqlId("updateByIds");
		return this.getSqlSession().update(sqlId,mapContext);
	}

}