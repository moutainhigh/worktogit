package com.lwxf.industry4.webapp.bizservice.product.impl;


import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.product.ProductCategoryDao;
import com.lwxf.industry4.webapp.bizservice.product.ProductCategoryService;
import com.lwxf.industry4.webapp.domain.entity.product.ProductCategory;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-01 10:21:33
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("productCategoryService")
public class ProductCategoryServiceImpl extends BaseServiceImpl<ProductCategory, String, ProductCategoryDao> implements ProductCategoryService {


	@Resource

	@Override	public void setDao( ProductCategoryDao productCategoryDao) {
		this.dao = productCategoryDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<ProductCategory> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public List<ProductCategory> findAllByBranchId(String branchId) {
		return this.dao.findAllByBranchId(branchId);
	}

	@Override
	public List<ProductCategory> selectProductCategoryByFilter(MapContext map) {
		return this.dao.selectProductCategoryByFilter(map);
	}

	@Override
	public ProductCategory selectProductCategoryByName(String name,String branchId) {
		return this.dao.selectProductCategoryByName(name,branchId);
	}

}