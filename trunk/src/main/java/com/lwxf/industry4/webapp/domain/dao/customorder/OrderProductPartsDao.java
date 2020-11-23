package com.lwxf.industry4.webapp.domain.dao.customorder;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.customorder.OrderProductParts;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-08-30 10:50:09
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface OrderProductPartsDao extends BaseDao<OrderProductParts, String> {

	PaginatedList<OrderProductParts> selectByFilter(PaginatedFilter paginatedFilter);

	List<OrderProductParts> findByProductPartsId(String partsId);

	List<OrderProductParts> findByProductId(String id);

	int deleteByProductId(String id);

	OrderProductParts findByProductIdAndPartsId(String productId, String id);
}