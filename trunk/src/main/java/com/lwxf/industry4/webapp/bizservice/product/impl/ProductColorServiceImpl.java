package com.lwxf.industry4.webapp.bizservice.product.impl;


import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.product.ProductColorDto;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.product.ProductColorDao;
import com.lwxf.industry4.webapp.bizservice.product.ProductColorService;
import com.lwxf.industry4.webapp.domain.entity.product.ProductColor;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-01 10:21:33
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("productColorService")
public class ProductColorServiceImpl extends BaseServiceImpl<ProductColor, String, ProductColorDao> implements ProductColorService {


	@Resource

	@Override	public void setDao( ProductColorDao productColorDao) {
		this.dao = productColorDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<ProductColor> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public List<ProductColor> selectByCategoryId(String id) {
		return this.dao.selectByCategoryId(id);
	}

	@Override
	public List<ProductColorDto> selectProductColorByFilter(MapContext mapContext) {
		return this.dao.selectProductColorByFilter(mapContext);
	}

	@Override
	public List<ProductColorDto> findAll() {
		return this.dao.findAll();
	}

	@Override
	public boolean selectByCategoryIdAndNameAndId(String productCategoryId, String name,String id) {
		return this.dao.selectByCategoryIdAndNameAndId(productCategoryId,name,id);
	}

	@Override
	public boolean isExistCidAndId(String cid, String id) {
		return this.dao.isExistCidAndId(cid,id);
	}

	@Override
	public boolean isExistNameAndNoIdAndCid(String name, String id, String cid) {
		return this.dao.isExistNameAndNoIdAndCid(name,id,cid);
	}

	@Override
	public List<ProductColor> selectByType(Integer type) {
		return this.dao.selectByType(type);
	}
}