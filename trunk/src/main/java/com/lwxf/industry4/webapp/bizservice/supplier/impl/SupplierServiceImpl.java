package com.lwxf.industry4.webapp.bizservice.supplier.impl;


import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.bizservice.supplier.SupplierService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.supplier.SupplierDao;
import com.lwxf.industry4.webapp.domain.dto.supplier.SupplierDto;
import com.lwxf.industry4.webapp.domain.dto.supplier.SupplierDtoFowWx;
import com.lwxf.industry4.webapp.domain.entity.supplier.Supplier;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * 功能：
 * 
 * @author：3965488@qq.com
 * @created：2019-06-12 15:33:44
 * @version：2019 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("supplierService")
public class SupplierServiceImpl extends BaseServiceImpl<Supplier, String, SupplierDao> implements SupplierService {


	@Resource
	@Override	public void setDao( SupplierDao supplierDao) {
		this.dao = supplierDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<Supplier> selectByFilter(PaginatedFilter paginatedFilter) {
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<SupplierDtoFowWx> selectDtoByFilter(PaginatedFilter paginatedFilter) {
		return this.dao.selectDtoByFilter(paginatedFilter) ;
	}

	@Override
	public SupplierDtoFowWx selectDtoById(String id) {
		return this.dao.selectDtoById(id) ;
	}

	@Override
	public MapContext countSupplierToday(String branchId) {
		return this.dao.countSupplierToday(branchId) ;
	}

	@Override
	public List<SupplierDto> findListByType(MapContext mapContext) {
		return this.dao.findListByType(mapContext);
	}

	@Override
	public PaginatedList<Map> findSupplierProducts(PaginatedFilter paginatedFilter) {
		return this.dao.findSupplierProducts(paginatedFilter);
	}
}