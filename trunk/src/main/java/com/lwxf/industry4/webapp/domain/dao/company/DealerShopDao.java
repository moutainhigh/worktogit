package com.lwxf.industry4.webapp.domain.dao.company;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.company.dealerShop.DealerShopDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.company.DealerShop;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-03-25 11:36:55
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface DealerShopDao extends BaseDao<DealerShop, String> {

	PaginatedList<DealerShop> selectByFilter(PaginatedFilter paginatedFilter);

	DealerShopDto findDealerShopById(String dealerShopId);

	List<DealerShop> findShopList(String address);
}