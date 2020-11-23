package com.lwxf.industry4.webapp.bizservice.product.impl;


import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.product.ProductMaterialDto;
import com.lwxf.industry4.webapp.domain.entity.product.ProductColor;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.product.ProductMaterialDao;
import com.lwxf.industry4.webapp.bizservice.product.ProductMaterialService;
import com.lwxf.industry4.webapp.domain.entity.product.ProductMaterial;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-01 10:21:33
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("productMaterialService")
public class ProductMaterialServiceImpl extends BaseServiceImpl<ProductMaterial, String, ProductMaterialDao> implements ProductMaterialService {


	@Resource

	@Override	public void setDao( ProductMaterialDao productMaterialDao) {
		this.dao = productMaterialDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<ProductMaterial> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public List<ProductMaterial> selectByCategoryId(String id) {
		return this.dao.selectByCategoryId(id);
	}

	@Override
	public List<ProductMaterialDto> selectProductMaterialList(String cid, String name) {
		return this.dao.selectProductMaterialList(cid,name);
	}

	@Override
	public boolean isExistByProductMaterial(ProductMaterial productMaterial) {
		return this.dao.isExistByProductMaterial(productMaterial);
	}

	@Override
	public List<ProductMaterial> findAll() {
		return this.dao.findAll();
	}


	@Override
	public List<ProductMaterial> selectByType(Integer type) {
		return this.dao.selectByType(type);
	}
}