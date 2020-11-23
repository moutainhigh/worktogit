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
import com.lwxf.industry4.webapp.domain.dao.product.ProductFilesDao;
import com.lwxf.industry4.webapp.bizservice.product.ProductFilesService;
import com.lwxf.industry4.webapp.domain.entity.product.ProductFiles;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2019-03-12 14:31:41
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("productFilesService")
public class ProductFilesServiceImpl extends BaseServiceImpl<ProductFiles, String, ProductFilesDao> implements ProductFilesService {


	@Resource

	@Override	public void setDao( ProductFilesDao productFilesDao) {
		this.dao = productFilesDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<ProductFiles> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public List<ProductFiles> findListByType(Integer type,String productId) {
		return this.dao.findListByType(type,productId);
	}

	@Override
	public ProductFiles findOneByProductIdAndId(String productId, String fileId) {
		return this.dao.findOneByProductIdAndId(productId,fileId);
	}

	@Override
	public ProductFiles findOneByProductIdAndType(String id, Integer type) {
		return this.dao.findOneByProductIdAndType(id,type);
	}

}