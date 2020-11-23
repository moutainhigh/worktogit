package com.lwxf.industry4.webapp.bizservice.customorder.impl;


import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.customorder.OfferItemDao;
import com.lwxf.industry4.webapp.bizservice.customorder.OfferItemService;
import com.lwxf.industry4.webapp.domain.entity.customorder.OfferItem;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@126.com)
 * @created：2019-07-01 14:20:03
 * @version：2019 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("offerItemService")
public class OfferItemServiceImpl extends BaseServiceImpl<OfferItem, String, OfferItemDao> implements OfferItemService {


	@Resource

	@Override	public void setDao( OfferItemDao offerItemDao) {
		this.dao = offerItemDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<OfferItem> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public int deleteByOfferId(String id) {
		return this.dao.deleteByOfferId(id);
	}

	@Override
	public List<OfferItem> findByOfferId(String id) {
		return this.dao.findByOfferId(id);
	}

}