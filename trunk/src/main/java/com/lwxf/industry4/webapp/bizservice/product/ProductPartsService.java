package com.lwxf.industry4.webapp.bizservice.product;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.product.ProductPartsDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.domain.entity.product.ProductParts;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-08-30 10:44:55
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface ProductPartsService extends BaseService <ProductParts, String> {

	PaginatedList<ProductParts> selectByFilter(PaginatedFilter paginatedFilter);

	List<ProductPartsDto> findByProductType(Integer productType, String branchId);

	ProductParts findByType(Integer productType, Integer partsType, Integer produceType);

	List<ProductPartsDto> findByOrderProductId(String orderProductId);
}