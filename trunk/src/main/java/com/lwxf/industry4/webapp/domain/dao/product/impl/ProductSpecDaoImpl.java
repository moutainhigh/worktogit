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
import com.lwxf.industry4.webapp.domain.dto.product.ProductSpecDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.product.ProductSpecDao;
import com.lwxf.industry4.webapp.domain.entity.product.ProductSpec;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-06 11:37:31
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("productSpecDao")
public class ProductSpecDaoImpl extends BaseDaoImpl<ProductSpec, String> implements ProductSpecDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<ProductSpec> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<ProductSpec> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public boolean isExistByProductSpec(ProductSpec productSpec) {
		String sql = this.getNamedSqlId("isExistByProductSpec");
		return this.getSqlSession().selectOne(sql,productSpec);
	}

	@Override
	public List<ProductSpecDto> selectProductSpecList(String cid, String name) {
		String sql = this.getNamedSqlId("selectProductSpecList");
		MapContext mapContext = MapContext.newOne();
		mapContext.put(WebConstant.REQUEST_PARAMETER_KEY_CID,cid);
		mapContext.put(WebConstant.KEY_ENTITY_NAME,name);
		return this.getSqlSession().selectList(sql,mapContext);
	}

	@Override
	public List<ProductSpec> selectByCategoryId(String id) {
		String sql = this.getNamedSqlId("selectByCategoryId");
		return this.getSqlSession().selectList(sql,id);
	}

	@Override
	public List<ProductSpec> findAll() {
		String sql = this.getNamedSqlId("findAll");
		return this.getSqlSession().selectList(sql);
	}

	@Override
	public List<ProductSpec> selectByType(Integer type) {
		String sql = this.getNamedSqlId("selectByType");
		return this.getSqlSession().selectList(sql,type);
	}
}