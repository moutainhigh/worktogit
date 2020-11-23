package com.lwxf.industry4.webapp.domain.dao.engineering;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.customorder.OrderProductDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.engineering.EngineeringOrder;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2020-05-27 18:14:09
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface EngineeringOrderDao extends BaseDao<EngineeringOrder, String> {

	PaginatedList<EngineeringOrder> selectByFilter(PaginatedFilter paginatedFilter);

    MapContext findListByEngineeringId(String engineeringId);

    List<EngineeringOrder> findByEngineeringId(String engineeringId);

    OrderProductDto findProductByOrderId(String customOrderId);
}