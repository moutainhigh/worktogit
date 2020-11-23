package com.lwxf.industry4.webapp.domain.dao.customorder;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.customorder.OfferItem;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@126.com)
 * @created：2019-07-01 14:20:03
 * @version：2019 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface OfferItemDao extends BaseDao<OfferItem, String> {

	PaginatedList<OfferItem> selectByFilter(PaginatedFilter paginatedFilter);

	int deleteByOfferId(String id);

	List<OfferItem> findByOfferId(String id);
}