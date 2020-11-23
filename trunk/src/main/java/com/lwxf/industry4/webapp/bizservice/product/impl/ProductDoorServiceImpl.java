package com.lwxf.industry4.webapp.bizservice.product.impl;


import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.product.ProductDoorDao;
import com.lwxf.industry4.webapp.bizservice.product.ProductDoorService;
import com.lwxf.industry4.webapp.domain.entity.product.ProductDoor;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2019-02-22 13:07:48
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("productDoorService")
public class ProductDoorServiceImpl extends BaseServiceImpl<ProductDoor, String, ProductDoorDao> implements ProductDoorService {


	@Resource

	@Override	public void setDao( ProductDoorDao productDoorDao) {
		this.dao = productDoorDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<ProductDoor> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public List<ProductDoor> selectByCategoryId(String categoryId) {
		return this.dao.selectByCategoryId(categoryId);
	}

	@Override
	public List<ProductDoor> findAll() {
		return this.dao.findAll();
	}
}