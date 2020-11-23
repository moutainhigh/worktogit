package com.lwxf.industry4.webapp.domain.dao.dealer;

import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.dealer.WxDealerLogisticsDto;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.dealer.DealerLogistics;
import com.lwxf.mybatis.utils.MapContext;

import java.util.List;


/**
 * 功能：经销商物流公司dao接口
 * 
 * @author：DJL(yuuyoo@163.com)
 * @created：2019-11-28 11:32:45
 * @version：2019 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface DealerLogisticsDao extends BaseDao<DealerLogistics, String> {

	PaginatedList<DealerLogistics> selectByFilter(PaginatedFilter paginatedFilter);

	PaginatedList<WxDealerLogisticsDto> selectDtoByFilter(PaginatedFilter paginatedFilter);

	WxDealerLogisticsDto selectDtoById(String id);

	WxDealerLogisticsDto selectDefaultDto(String cid);

	DealerLogistics selectByMap(MapContext mapContext);

	Integer countByCid(String cid);

	Integer cancelCheckedByCid(String cid);

    List<WxDealerLogisticsDto> findByCid(String cid);

	Integer deleteByCid(String cid);
}