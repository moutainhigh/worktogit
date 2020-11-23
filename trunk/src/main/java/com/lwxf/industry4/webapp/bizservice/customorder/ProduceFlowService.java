package com.lwxf.industry4.webapp.bizservice.customorder;


import java.util.List;
import java.util.Map;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.customorder.ProduceFlowDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.domain.entity.customorder.ProduceFlow;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@126.com)
 * @created：2019-04-11 17:38:26
 * @version：2019 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface ProduceFlowService extends BaseService <ProduceFlow, String> {

	PaginatedList<ProduceFlow> selectByFilter(PaginatedFilter paginatedFilter);

    List<Map> findByProduceOrderId(String produceOrderId);

	ProduceFlow findOneByProduceIdAndNode(String id, Integer node);

	ProduceFlowDto findOneById(String id);

	int deleteByOrderId(String orderId);

	List<ProduceFlowDto> findListByProduceOrderId(String productId);
}