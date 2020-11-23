package com.lwxf.industry4.webapp.bizservice.customorder.impl;


import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.customorder.ProduceFlowDto;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.customorder.ProduceFlowDao;
import com.lwxf.industry4.webapp.bizservice.customorder.ProduceFlowService;
import com.lwxf.industry4.webapp.domain.entity.customorder.ProduceFlow;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@126.com)
 * @created：2019-04-11 17:38:26
 * @version：2019 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("produceFlowService")
public class ProduceFlowServiceImpl extends BaseServiceImpl<ProduceFlow, String, ProduceFlowDao> implements ProduceFlowService {


	@Resource

	@Override	public void setDao( ProduceFlowDao produceFlowDao) {
		this.dao = produceFlowDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<ProduceFlow> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public List<Map> findByProduceOrderId(String produceOrderId) {
		return this.dao.findByProduceOrderId(produceOrderId);
	}

	@Override
	public ProduceFlow findOneByProduceIdAndNode(String id, Integer node) {
		return this.dao.findOneByProduceIdAndNode(id,node);
	}

	@Override
	public ProduceFlowDto findOneById(String id) {
		return this.dao.findOneById(id);
	}

	@Override
	public int deleteByOrderId(String orderId) {
		return this.dao.deleteByOrderId(orderId);
	}

	@Override
	public List<ProduceFlowDto> findListByProduceOrderId(String productId) {
		return this.dao.findListByProduceOrderId(productId);
	}

}