package com.lwxf.industry4.webapp.bizservice.dispatch.impl;


import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.dispatch.DispatchBillItemDto;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.dispatch.DispatchBillItemDao;
import com.lwxf.industry4.webapp.bizservice.dispatch.DispatchBillItemService;
import com.lwxf.industry4.webapp.domain.entity.dispatch.DispatchBillItem;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-20 15:10:29
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("dispatchBillItemService")
public class DispatchBillItemServiceImpl extends BaseServiceImpl<DispatchBillItem, String, DispatchBillItemDao> implements DispatchBillItemService {


	@Resource

	@Override	public void setDao( DispatchBillItemDao dispatchBillItemDao) {
		this.dao = dispatchBillItemDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<DispatchBillItem> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public List<DispatchBillItem> findListByOrderId(String orderId) {
		return this.dao.findListByOrderId(orderId);
	}

	@Override
	public Integer findDispatchItemCountByOrderId(String dispatchBillId) {
		return this.dao.findDispatchItemCountByOrderId(dispatchBillId);
	}

	@Override
	public Integer findDispatchItemCountByDispatchIdAndType(String dispatchBillId, Integer type) {
		return this.dao.findDispatchItemCountByDispatchIdAndType(dispatchBillId,type);
	}

	@Override
	public MapContext findLogisticsByDispatchId(String dispatchBillId) {
		return this.dao.findLogisticsByDispatchId(dispatchBillId);
	}

	@Override
	public List<MapContext> findByDispatchBillId(String dispatchBillId) {
		return this.dao.findByDispatchBillId(dispatchBillId);
	}

	@Override
	public int deleteByOrderId(String orderId) {
		return this.dao.deleteByOrderId(orderId);
	}

	@Override
	public List<DispatchBillItemDto> findListByDIdAndTypes(String id, List<Integer> types) {
		return this.dao.findListByDIdAndTypes(id,types);
	}

	@Override
	public List<DispatchBillItem> findListByDispatchBillId(String dispatchBillId) {
		return this.dao.findListByDispatchBillId(dispatchBillId);
	}

}