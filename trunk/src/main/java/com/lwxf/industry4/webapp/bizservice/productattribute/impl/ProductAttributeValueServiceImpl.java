package com.lwxf.industry4.webapp.bizservice.productattribute.impl;


import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.bizservice.productattribute.ProductAttributeValueService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.productattribute.ProductAttributeValueDao;
import com.lwxf.industry4.webapp.domain.entity.productattribute.ProductAttributeValue;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2020-07-29 10:28:04
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("productAttributeValueService")
public class ProductAttributeValueServiceImpl extends BaseServiceImpl<ProductAttributeValue, String, ProductAttributeValueDao> implements ProductAttributeValueService {


	@Resource

	@Override	public void setDao( ProductAttributeValueDao productAttributeValueDao) {
		this.dao = productAttributeValueDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<ProductAttributeValue> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public List<ProductAttributeValue> findByKeyId(MapContext map) {
		return this.dao.findByKeyId(map);
	}

	@Override
	public ProductAttributeValue findByKeyIdAndValue(MapContext map0) {
		return this.dao.findByKeyIdAndValue(map0);
	}

}