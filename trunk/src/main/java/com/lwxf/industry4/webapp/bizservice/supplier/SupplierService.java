package com.lwxf.industry4.webapp.bizservice.supplier;


import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.supplier.SupplierDto;
import com.lwxf.industry4.webapp.domain.dto.supplier.SupplierDtoFowWx;
import com.lwxf.industry4.webapp.domain.entity.supplier.Supplier;
import com.lwxf.mybatis.utils.MapContext;

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
public interface SupplierService extends BaseService<Supplier, String> {

	PaginatedList<Supplier> selectByFilter(PaginatedFilter paginatedFilter);

	PaginatedList<SupplierDtoFowWx> selectDtoByFilter(PaginatedFilter paginatedFilter);

	SupplierDtoFowWx selectDtoById(String id);

	MapContext countSupplierToday(String branchId);


	List<SupplierDto> findListByType(MapContext mapContext);

    PaginatedList<Map> findSupplierProducts(PaginatedFilter paginatedFilter);
}