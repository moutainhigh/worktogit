package com.lwxf.industry4.webapp.bizservice.product;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.domain.entity.product.ProductFiles;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2019-03-12 14:31:41
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface ProductFilesService extends BaseService <ProductFiles, String> {

	PaginatedList<ProductFiles> selectByFilter(PaginatedFilter paginatedFilter);

	List<ProductFiles> findListByType(Integer type,String productId);

	ProductFiles findOneByProductIdAndId(String productId, String fileId);

	ProductFiles findOneByProductIdAndType(String id, Integer type);
}