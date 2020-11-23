package com.lwxf.industry4.webapp.domain.dao.product.impl;


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
import com.lwxf.industry4.webapp.domain.dao.product.ProductFilesDao;
import com.lwxf.industry4.webapp.domain.entity.product.ProductFiles;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2019-03-12 14:31:41
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("productFilesDao")
public class ProductFilesDaoImpl extends BaseDaoImpl<ProductFiles, String> implements ProductFilesDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<ProductFiles> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<ProductFiles> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public List<ProductFiles> findListByType(Integer type,String productId) {
		String sql = this.getNamedSqlId("findListByType");
		MapContext mapContext = new MapContext();
		mapContext.put("productId",productId);
		mapContext.put("type",type);
		return this.getSqlSession().selectList(sql,mapContext);
	}

	@Override
	public ProductFiles findOneByProductIdAndId(String productId, String fileId) {
		String sql = this.getNamedSqlId("findOneByProductIdAndId");
		MapContext mapContext = new MapContext();
		mapContext.put("productId",productId);
		mapContext.put("fileId",fileId);
		return this.getSqlSession().selectOne(sql,mapContext);
	}

	@Override
	public ProductFiles findOneByProductIdAndType(String id, Integer type) {
		String sql = this.getNamedSqlId("findOneByProductIdAndType");
		MapContext mapContext = new MapContext();
		mapContext.put("productId",id);
		mapContext.put("type",type);
		return this.getSqlSession().selectOne(sql,mapContext);
	}

}