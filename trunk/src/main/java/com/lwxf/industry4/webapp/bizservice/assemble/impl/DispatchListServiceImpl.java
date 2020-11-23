package com.lwxf.industry4.webapp.bizservice.assemble.impl;


import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.aftersale.DateNum;
import com.lwxf.industry4.webapp.domain.dto.dispatchList.DispatchListDto;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.assemble.DispatchListDao;
import com.lwxf.industry4.webapp.bizservice.assemble.DispatchListService;
import com.lwxf.industry4.webapp.domain.entity.assemble.DispatchList;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2018-12-19 15:05:23
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("dispatchListService")
public class DispatchListServiceImpl extends BaseServiceImpl<DispatchList, String, DispatchListDao> implements DispatchListService {


	@Resource

	@Override	public void setDao( DispatchListDao dispatchListDao) {
		this.dao = dispatchListDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<DispatchList> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public PaginatedList<DispatchListDto> findDispatchListByCid(PaginatedFilter paginatedFilter) {
		return this.dao.findDispatchListByCid(paginatedFilter);
	}

	@Override
	public DispatchListDto findDipatchListById(String dispatchListId) {
		return this.dao.findDipatchListById(dispatchListId);
	}

	@Override
	public Integer findDiscountByCidAndStatus(MapContext mapContext) {
		return this.dao.findDiscountByCidAndStatus(mapContext);
	}

	@Override
	public List<DateNum> findDatenumByCreatedAndCid(MapContext mapContext) {
		return this.dao.findDatenumByCreatedAndCid(mapContext);
	}

	@Override
	public String findTimeByOrderId(String orderId) {
		return this.dao.findTimeByOrderId(orderId);
	}
}