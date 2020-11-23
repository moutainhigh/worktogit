package com.lwxf.industry4.webapp.bizservice.supplier.impl;


import java.util.List;
import com.lwxf.industry4.webapp.domain.dto.supplier.SupplierDtoFowWx;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.supplier.SupplierProductDto;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.supplier.SupplierProductDao;
import com.lwxf.industry4.webapp.bizservice.supplier.SupplierProductService;
import com.lwxf.industry4.webapp.domain.entity.supplier.SupplierProduct;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-28 09:25:21
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("supplierProductService")
public class SupplierProductServiceImpl extends BaseServiceImpl<SupplierProduct, String, SupplierProductDao> implements SupplierProductService {


	@Resource
	@Override	public void setDao( SupplierProductDao supplierProductDao) {
		this.dao = supplierProductDao;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<SupplierProduct> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public SupplierProduct findOneBySupplierAndProductId(String id, String productId) {
		return this.dao.findOneBySupplierAndProductId(id,productId);
	}

	@Override
	public List<SupplierProductDto> findListBySupplierAndProductIds(String id, List productIds) {
		return this.dao.findListBySupplierAndProductIds(id,productIds);
	}

	@Override
	public List<SupplierProduct> selectAllSupplierProduct(MapContext map) {
		return this.dao.selectAllSupplierProduct(map) ;
	}

	@Override
	public int deleteBySupplierId(String supplierId) {
		return this.dao.deleteBySupplierId(supplierId) ;
	}

	@Override
	public List<SupplierProduct> findListBySupplierIdAndMaterialType(String supplierId, String code) {
		return this.dao.findListBySupplierIdAndMaterialType(supplierId,code);
	}
}