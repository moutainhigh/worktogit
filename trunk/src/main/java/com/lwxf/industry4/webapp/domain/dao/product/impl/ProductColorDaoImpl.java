package com.lwxf.industry4.webapp.domain.dao.product.impl;


import java.util.List;
import java.util.Map;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.product.ProductColorDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.product.ProductColorDao;
import com.lwxf.industry4.webapp.domain.entity.product.ProductColor;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-01 10:21:33
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("productColorDao")
public class ProductColorDaoImpl extends BaseDaoImpl<ProductColor, String> implements ProductColorDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<ProductColor> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<ProductColor> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public List<ProductColor> selectByCategoryId(String id) {
		String sql = this.getNamedSqlId("selectByCategoryId");
		return this.getSqlSession().selectList(sql,id);
	}

	@Override
	public List<ProductColorDto> selectProductColorByFilter(MapContext mapContext) {
		String sql = this.getNamedSqlId("selectProductColorByFilter");
		return this.getSqlSession().selectList(sql,mapContext);
	}

	@Override
	public List<ProductColorDto> findAll() {
		String sql = this.getNamedSqlId("findAll");
		return this.getSqlSession().selectList(sql);
	}

	@Override
	public boolean selectByCategoryIdAndNameAndId(String productCategoryId, String name,String id) {
		String sql = this.getNamedSqlId("selectByCategoryIdAndNameAndId");
		MapContext mapContext = new MapContext();
		mapContext.put("productCategoryId",productCategoryId);
		mapContext.put("name",name);
		mapContext.put("id",id);
		return this.getSqlSession().selectOne(sql,mapContext);
	}

	@Override
	public boolean isExistCidAndId(String cid, String id) {
		MapContext mapContext = new MapContext();
		mapContext.put("cid",cid);
		mapContext.put(WebConstant.KEY_ENTITY_ID,id);
		String sql = this.getNamedSqlId("isExistCidAndId");
		return this.getSqlSession().selectOne(sql,mapContext);
	}

	@Override
	public boolean isExistNameAndNoIdAndCid(String name, String id, String cid) {
		MapContext mapContext = new MapContext();
		mapContext.put("cid",cid);
		mapContext.put(WebConstant.KEY_ENTITY_ID,id);
		mapContext.put(WebConstant.KEY_ENTITY_NAME,name);
		String sql = this.getNamedSqlId("isExistNameAndNoIdAndCid");
		return this.getSqlSession().selectOne(sql,mapContext);
	}

	@Override
	public List<ProductColor> selectByType(Integer type) {
		String sqlId = this.getNamedSqlId("selectByType");
		return this.getSqlSession().selectList(sqlId,type);
	}
}