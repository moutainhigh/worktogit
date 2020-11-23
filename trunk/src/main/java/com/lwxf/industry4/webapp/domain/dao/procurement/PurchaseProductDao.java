package com.lwxf.industry4.webapp.domain.dao.procurement;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.procurement.PurchaseProductDto;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.procurement.PurchaseProduct;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-17 11:00:40
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface PurchaseProductDao extends BaseDao<PurchaseProduct, String> {

	PaginatedList<PurchaseProduct> selectByFilter(PaginatedFilter paginatedFilter);

	List<PurchaseProductDto> selectProductsByRequestId(String id);
}