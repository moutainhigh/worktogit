package com.lwxf.industry4.webapp.bizservice.product.impl;


import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.product.ProductSpecDto;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.product.ProductSpecDao;
import com.lwxf.industry4.webapp.bizservice.product.ProductSpecService;
import com.lwxf.industry4.webapp.domain.entity.product.ProductSpec;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-06 11:37:31
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("productSpecService")
public class ProductSpecServiceImpl extends BaseServiceImpl<ProductSpec, String, ProductSpecDao> implements ProductSpecService {


	@Resource

	@Override	public void setDao( ProductSpecDao productSpecDao) {
		this.dao = productSpecDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<ProductSpec> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public boolean isExistByProductSpec(ProductSpec productSpec) {
		return this.dao.isExistByProductSpec(productSpec);
	}

	@Override
	public List<ProductSpecDto> selectProductSpecList(String cid, String name) {
		return this.dao.selectProductSpecList(cid,name);
	}

	@Override
	public List<ProductSpec> selectByCategoryId(String id) {
		return this.dao.selectByCategoryId(id);
	}

	@Override
	public List<ProductSpec> findAll() {
		return this.dao.findAll();
	}

	@Override
	public List<ProductSpec> selectByType(Integer type) {
		return this.dao.selectByType(type);
	}
}