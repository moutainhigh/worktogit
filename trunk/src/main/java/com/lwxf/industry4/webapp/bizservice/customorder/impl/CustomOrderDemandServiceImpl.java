package com.lwxf.industry4.webapp.bizservice.customorder.impl;


import java.util.List;
import java.util.Map;


import com.lwxf.industry4.webapp.bizservice.customorder.CustomOrderFilesService;
import com.lwxf.industry4.webapp.common.enums.customorder.CustomOrderFilesType;
import com.lwxf.industry4.webapp.domain.dto.customorder.CustomOrderDemandDto;
import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.customorder.CustomOrderDemandDao;
import com.lwxf.industry4.webapp.bizservice.customorder.CustomOrderDemandService;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderDemand;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-04 17:48:28
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("customOrderDemandService")
public class CustomOrderDemandServiceImpl extends BaseServiceImpl<CustomOrderDemand, String, CustomOrderDemandDao> implements CustomOrderDemandService {

	@Resource(name = "customOrderFilesService")
	private CustomOrderFilesService customOrderFilesService;

	@Resource

	@Override	public void setDao( CustomOrderDemandDao customOrderDemandDao) {
		this.dao = customOrderDemandDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<CustomOrderDemand> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}


	@Override
	public List<CustomOrderDemand> findByorderId(String orderId) {
		return this.dao.findByorderId(orderId);
	}

	@Override
	public int deleteByOrderId(String orderId) {
		return this.dao.deleteByOrderId(orderId);
	}

	@Override
	public CustomOrderDemandDto selectByDemandId(String id) {
		return this.dao.selectByDemandId(id);
	}

	@Override
	public List<CustomOrderDemandDto> findListByOrderId(String id) {
		List<CustomOrderDemandDto> customOrderDemandDtos = this.dao.findListByOrderId(id);
		for(CustomOrderDemandDto customOrderDemandDto:customOrderDemandDtos){
			customOrderDemandDto.setCustomOrderFiles(this.customOrderFilesService.selectByOrderIdAndType(id,CustomOrderFilesType.DEMAND.getValue(),customOrderDemandDto.getId()));
		}
		return customOrderDemandDtos;
	}

	@Override
	public CustomOrderDemand findByProductId(String id) {
		return this.dao.findByProductId(id);
	}
}