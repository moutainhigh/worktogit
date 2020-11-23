package com.lwxf.industry4.webapp.bizservice.assemble;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.aftersale.DateNum;
import com.lwxf.industry4.webapp.domain.dto.dispatchList.DispatchListDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.domain.entity.assemble.DispatchList;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2018-12-19 15:05:23
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface DispatchListService extends BaseService <DispatchList, String> {

	PaginatedList<DispatchList> selectByFilter(PaginatedFilter paginatedFilter);


	PaginatedList<DispatchListDto> findDispatchListByCid(PaginatedFilter paginatedFilter);


	DispatchListDto findDipatchListById(String dispatchListId);

	Integer findDiscountByCidAndStatus(MapContext mapContext);

	List<DateNum> findDatenumByCreatedAndCid(MapContext mapContext);

    String findTimeByOrderId(String orderId);
}