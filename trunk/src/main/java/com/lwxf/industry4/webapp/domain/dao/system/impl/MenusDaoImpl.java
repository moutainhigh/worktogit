package com.lwxf.industry4.webapp.domain.dao.system.impl;


import java.util.List;
import java.util.Map;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.system.MenusDao;
import com.lwxf.industry4.webapp.domain.entity.system.Menus;


/**
 * 功能：
 * 
 * @author：dongshibo(zjl869319827@outlook.com)
 * @created：2018-12-07 14:10:47
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("menusDao")
public class MenusDaoImpl extends BaseDaoImpl<Menus, String> implements MenusDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<Menus> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<Menus> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public List<Menus> findAll() {
		String sqlId = this.getNamedSqlId("findAll");
		return this.getSqlSession().selectList(sqlId);
	}

	@Override
	public List<Menus> findListByMapContext(MapContext mapContext) {
		String sqlId = this.getNamedSqlId("findListByMapContext");
		return this.getSqlSession().selectList(sqlId,mapContext);
	}

	@Override
	public Menus findOneByKey(String key) {
		String sqlId = this.getNamedSqlId("findOneByKey");
		return this.getSqlSession().selectOne(sqlId,key);
	}

	@Override
	public List<Menus> findByParentId(String id) {
		String sqlId = this.getNamedSqlId("findByParentId");
		return this.getSqlSession().selectList(sqlId,id);
	}

	@Override
	public int deleteRolePermissionByKey(String key) {
		String sqlId = this.getNamedSqlId("deleteRolePermissionByKey");
		return this.getSqlSession().delete(sqlId,key);
	}

	@Override
	public int deleteEmployeePermissionByKey(String key) {
		String sql = this.getNamedSqlId("deleteEmployeePermissionByKey");
		return this.getSqlSession().delete(sql,key);
	}

	@Override
	public int updateNameByLikeKey(String newName, String key) {
		String sql = this.getNamedSqlId("updateNameByLikeKey");
		MapContext mapContext = new MapContext();
		mapContext.put("newName",newName);
		mapContext.put("key",key);
		return this.getSqlSession().update(sql,mapContext);
	}

	@Override
	public List<Menus> findListByLikeKey(String id) {
		String sqlId = this.getNamedSqlId("findListByLikeKey");
		return this.getSqlSession().selectList(sqlId,id);
	}

	@Override
	public List<Menus> findAllByTypeAndDisabled(int type, Boolean disabled) {
		String sqlId = this.getNamedSqlId("findAllByTypeAndDisabled");
		MapContext mapContext = new MapContext();
		mapContext.put("type",type);
		mapContext.put(WebConstant.KEY_ENTITY_DISABLED,disabled);
		return this.getSqlSession().selectList(sqlId,mapContext);
	}
}