package com.lwxf.industry4.webapp.bizservice.product.impl;


import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.product.ProductDto;
import com.lwxf.industry4.webapp.domain.entity.product.ProductColor;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.product.ProductDao;
import com.lwxf.industry4.webapp.bizservice.product.ProductService;
import com.lwxf.industry4.webapp.domain.entity.product.Product;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-01 10:21:32
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("productService")
public class ProductServiceImpl extends BaseServiceImpl<Product, String, ProductDao> implements ProductService {


	@Resource

	@Override	public void setDao( ProductDao productDao) {
		this.dao = productDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<ProductDto> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public List<Product> selectByCategoryId(String id) {
		return this.dao.selectByCategoryId(id);
	}

	@Override
	public List<Product> selectByColorId(String id) {
		return this.dao.selectByColorId(id);
	}

	@Override
	public List<Product> selectByMaterialId(String id) {
		return this.dao.selectByMaterialId(id);
	}

	@Override
	public Product selectByModel(String no) {
		return this.dao.selectByModel(no);
	}

	@Override
	public ProductDto selectProductDtoById(String id) {
		return this.dao.selectProductDtoById(id);
	}

	@Override
	public List<Product> selectBySpecId(String id) {
		return this.dao.selectBySpecId(id);
	}

	@Override
	public List findResourcesList(MapContext mapContent) {
		return this.dao.findResourcesList(mapContent);
	}

	@Override
	public List<Product> findProductsBySupplierId(String supplierId) {
		return this.dao.findProductsBySupplierId(supplierId);
	}

	@Override
	public List<Product> findProductsRecommend(String companyId) {
		return this.dao.findProductsRecommend(companyId);
	}
}