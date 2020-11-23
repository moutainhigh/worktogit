package com.lwxf.industry4.webapp.bizservice.dealer;

import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.domain.dto.dealer.WxDealerLogisticsDto;
import com.lwxf.industry4.webapp.domain.entity.dealer.DealerLogistics;

import java.util.List;


/**
 * 功能：
 * 
 * @author：DJL(yuuyoo@163.com)
 * @created：2019-11-28 11:32:45
 * @version：2019 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface DealerLogisticsService extends BaseService <DealerLogistics, String> {

	PaginatedList<DealerLogistics> selectByFilter(PaginatedFilter paginatedFilter);

	PaginatedList<WxDealerLogisticsDto> selectDtoByFilter(PaginatedFilter paginatedFilter);

	WxDealerLogisticsDto findDtoById(String id);

	WxDealerLogisticsDto findDefaultDto(String cid);

	DealerLogistics findByCidAndLid(String cid, String logisticsId);

	Integer countByCid(String cid);

	Integer cancelCheckedByCid(String cid);

    List<WxDealerLogisticsDto> findByCid(String cid);

	Integer deleteByCid(String cid);
}