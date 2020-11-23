package com.lwxf.industry4.webapp.bizservice.company.impl;


import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.bizservice.base.BaseNoIdServiceImpl;
import com.lwxf.industry4.webapp.bizservice.company.DealerShippingLogisticsService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.company.DealerShippingLogisticsDao;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.domain.entity.company.DealerShippingLogistics;
import com.lwxf.mybatis.utils.MapContext;


/**
 * 功能：
 * 
 * @author：dongshibo(F_baisi@163.com)
 * @created：2018-12-27 13:44:06
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("dealerShippingLogisticsService")
public class DealerShippingLogisticsServiceImpl extends BaseNoIdServiceImpl<DealerShippingLogistics, DealerShippingLogisticsDao> implements DealerShippingLogisticsService {


	@Resource

	@Override	public void setDao( DealerShippingLogisticsDao dealerShippingLogisticsDao) {
		this.dao = dealerShippingLogisticsDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<DealerShippingLogistics> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public DealerShippingLogistics findOneByCompanyId(String companyId) {
		return this.dao.findOneByCompanyId(companyId);
	}

	@Override
	public int updateByCompanyId(MapContext updateDealerShipping) {
		return this.dao.updateByCompanyId(updateDealerShipping);
	}

}