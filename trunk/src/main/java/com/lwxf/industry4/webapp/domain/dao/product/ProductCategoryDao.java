package com.lwxf.industry4.webapp.domain.dao.product;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.product.ProductCategory;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-01 10:21:33
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface ProductCategoryDao extends BaseDao<ProductCategory, String> {

	PaginatedList<ProductCategory> selectByFilter(PaginatedFilter paginatedFilter);

	List<ProductCategory> findAllByBranchId(String branchId);

	List<ProductCategory> selectProductCategoryByFilter(MapContext map);

	ProductCategory selectProductCategoryByName(String name,String branchId);
}