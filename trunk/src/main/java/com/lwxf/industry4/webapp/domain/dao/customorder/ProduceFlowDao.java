package com.lwxf.industry4.webapp.domain.dao.customorder;


import java.util.List;
import java.util.Map;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.customorder.ProduceFlowDto;
import com.lwxf.industry4.webapp.domain.dto.financing.PaymentProduceOrderDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.customorder.ProduceFlow;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@126.com)
 * @created：2019-04-11 17:38:26
 * @version：2019 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface ProduceFlowDao extends BaseDao<ProduceFlow, String> {

	PaginatedList<ProduceFlow> selectByFilter(PaginatedFilter paginatedFilter);

    List<Map> findByProduceOrderId(String produceOrderId);

	ProduceFlow findOneByProduceIdAndNode(String id, Integer node);

	List<ProduceFlowDto> findListByProduceOrderId(String id);

	ProduceFlowDto findOneById(String id);

	int deleteByOrderId(String orderId);

}