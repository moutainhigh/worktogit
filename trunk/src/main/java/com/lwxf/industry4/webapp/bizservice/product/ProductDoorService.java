package com.lwxf.industry4.webapp.bizservice.product;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.domain.entity.product.ProductDoor;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2019-02-22 13:07:48
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface ProductDoorService extends BaseService <ProductDoor, String> {

	PaginatedList<ProductDoor> selectByFilter(PaginatedFilter paginatedFilter);

	List<ProductDoor> selectByCategoryId(String categoryId);

	List<ProductDoor> findAll();

}