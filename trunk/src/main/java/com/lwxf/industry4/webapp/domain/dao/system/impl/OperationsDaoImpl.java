package com.lwxf.industry4.webapp.domain.dao.system.impl;


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
import com.lwxf.industry4.webapp.domain.dao.system.OperationsDao;
import com.lwxf.industry4.webapp.domain.entity.system.Operations;


/**
 * 功能：
 * 
 * @author：dongshibo(zjl869319827@outlook.com)
 * @created：2018-12-07 14:10:48
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("operationsDao")
public class OperationsDaoImpl extends BaseDaoImpl<Operations, String> implements OperationsDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<Operations> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<Operations> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public List<Operations> findAll() {
		String sqlId = this.getNamedSqlId("findAll");
		return this.getSqlSession().selectList(sqlId);
	}

	@Override
	public List<Operations> findListByMapContext(MapContext mapContext) {
		String sqlId = this.getNamedSqlId("findListByMapContext");
		return this.getSqlSession().selectList(sqlId,mapContext);
	}

	@Override
	public Operations findOneByName(String name) {
		String sqlId = this.getNamedSqlId("findOneByName");
		return this.getSqlSession().selectOne(sqlId,name);
	}

	@Override
	public Operations findOneByKey(String key) {
		String sqlId = this.getNamedSqlId("findOneByKey");
		return this.getSqlSession().selectOne(sqlId,key);
	}

	@Override
	public List<Operations> findAllByTypes(List<Integer> types) {
		String sqlId = this.getNamedSqlId("findAllByTypes");
		return this.getSqlSession().selectList(sqlId,types);
	}
}