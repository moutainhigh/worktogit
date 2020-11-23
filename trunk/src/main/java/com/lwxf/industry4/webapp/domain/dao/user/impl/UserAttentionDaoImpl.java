package com.lwxf.industry4.webapp.domain.dao.user.impl;


import java.util.List;
import java.util.Map;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.entity.company.StoreConfig;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.entity.company.StoreConfig;
import com.lwxf.industry4.webapp.domain.entity.user.UserAttention;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.user.UserAttentionDao;
import com.lwxf.industry4.webapp.domain.entity.user.UserAttention;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-09-13 18:07:19
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("userAttentionDao")
public class UserAttentionDaoImpl extends BaseDaoImpl<UserAttention, String> implements UserAttentionDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<UserAttention> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<UserAttention> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public Boolean isExistByUserIdAndResourceId(String userId, String companyId) {
		String sqlId = this.getNamedSqlId("isExistByUserIdAndResourceId");
		MapContext mapContext = MapContext.newOne();
		mapContext.put(WebConstant.KEY_ENTITY_USER_ID,userId);
		mapContext.put("resourceId",companyId);
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public List<UserAttention> findByUserId(String userId) {
		String sqlId = this.getNamedSqlId("findByUserId");
		return this.getSqlSession().selectList(sqlId,userId);
	}

	@Override
	public int deleteByUserIdAndResourceId(MapContext mapContext) {
		String sqlId = this.getNamedSqlId("deleteByUserIdAndResourceId");
		return this.getSqlSession().delete(sqlId,mapContext);
	}

	@Override
	public Map<String,Long> isExistByUserIdAndResourceIds(String userId, List<StoreConfig> storeConfigs) {
		String sqlId = this.getNamedSqlId("isExistByUserIdAndResourceIds");
		MapContext mapContext = new MapContext();
		mapContext.put(WebConstant.KEY_ENTITY_USER_ID,userId);
		mapContext.put("storeConfigs",storeConfigs);
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public Map<String, Long> isExistByUserIdAndResIds(String userId, List<?> list, String resId) {
		String sqlId = this.getNamedSqlId("isExistByUserIdAndResIds");
		MapContext mapContext = new MapContext();
		mapContext.put(WebConstant.KEY_ENTITY_USER_ID,userId);
		mapContext.put("ssss",list);
		mapContext.put("resId",resId);
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public Integer findCountByResIdAndType(String resourceId,Integer type) {
		MapContext map = MapContext.newOne();
		map.put("resourceId",resourceId);
		map.put("resourceType",type);
		String sqlId = this.getNamedSqlId("findCountByResIdAndType");
		return this.getSqlSession().selectOne(sqlId,map);
	}
}