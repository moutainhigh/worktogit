package com.lwxf.industry4.webapp.bizservice.productattribute.impl;


import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.bizservice.productattribute.ProductAttributeKeyService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.productattribute.ProductAttributeKeyDao;
import com.lwxf.industry4.webapp.domain.dto.productattribute.ProductAttributeKeyDto;
import com.lwxf.industry4.webapp.domain.entity.productattribute.ProductAttributeKey;
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
@Service("productAttributeKeyService")
public class ProductAttributeKeyServiceImpl extends BaseServiceImpl<ProductAttributeKey, String, ProductAttributeKeyDao> implements ProductAttributeKeyService {


	@Resource

	@Override	public void setDao( ProductAttributeKeyDao productAttributeKeyDao) {
		this.dao = productAttributeKeyDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<ProductAttributeKey> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public List<ProductAttributeKeyDto> findListBybranchId(MapContext mapContext) {
		return this.dao.findListBybranchId(mapContext);
	}

}