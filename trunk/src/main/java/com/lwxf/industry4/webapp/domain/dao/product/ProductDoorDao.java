package com.lwxf.industry4.webapp.domain.dao.product;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.product.ProductDoor;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;

import java.util.List;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2019-02-22 13:07:48
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface ProductDoorDao extends BaseDao<ProductDoor, String> {

	PaginatedList<ProductDoor> selectByFilter(PaginatedFilter paginatedFilter);

	List<ProductDoor> selectByCategoryId(String categoryId);

	List<ProductDoor> findAll();

}