package com.lwxf.industry4.webapp.bizservice.product.impl;


import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.product.ProductPartsDto;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.product.ProductPartsDao;
import com.lwxf.industry4.webapp.bizservice.product.ProductPartsService;
import com.lwxf.industry4.webapp.domain.entity.product.ProductParts;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-08-30 10:44:55
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("productPartsService")
public class ProductPartsServiceImpl extends BaseServiceImpl<ProductParts, String, ProductPartsDao> implements ProductPartsService {


	@Resource

	@Override	public void setDao( ProductPartsDao productPartsDao) {
		this.dao = productPartsDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<ProductParts> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public List<ProductPartsDto> findByProductType(Integer productType, String branchId) {
		return this.dao.findByProductType(productType,branchId);
	}

	@Override
	public ProductParts findByType(Integer productType, Integer partsType, Integer produceType) {
		return this.dao.findByType(productType,partsType,produceType);
	}

	@Override
	public List<ProductPartsDto> findByOrderProductId(String orderProductId) {
		return this.dao.findByOrderProductId(orderProductId);
	}

}