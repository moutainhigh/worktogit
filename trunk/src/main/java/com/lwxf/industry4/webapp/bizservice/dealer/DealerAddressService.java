package com.lwxf.industry4.webapp.bizservice.dealer;

import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.dealer.WxDealerAddressDto;
import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.domain.entity.dealer.DealerAddress;

import java.util.List;


/**
 * 功能：
 * 
 * @author：DJL(yuuyoo@163.com)
 * @created：2019-11-28 11:32:45
 * @version：2019 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface DealerAddressService extends BaseService <DealerAddress, String> {

	PaginatedList<DealerAddress> selectByFilter(PaginatedFilter paginatedFilter);

	PaginatedList<WxDealerAddressDto> selectDtoByFilter(PaginatedFilter paginatedFilter);

	WxDealerAddressDto findDtoById(String id);

	WxDealerAddressDto findDefaultDto(String cid);

	Integer countByCid(String cid);

	Integer cancelCheckedByCid(String cid);

	List<DealerAddress> findListByCid(String companyId);
}