package com.lwxf.industry4.webapp.domain.dao.company;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.base.BaseNoIdDao;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.company.DealerShippingLogistics;


/**
 * 功能：
 * 
 * @author：dongshibo(F_baisi@163.com)
 * @created：2018-12-27 13:44:06
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface DealerShippingLogisticsDao extends BaseNoIdDao<DealerShippingLogistics> {

	DealerShippingLogistics selectByCompanyIdAndLogisticsCompanyId(String companyId, String logisticsCompanyId);
	PaginatedList<DealerShippingLogistics> selectByFilter(PaginatedFilter paginatedFilter);

	DealerShippingLogistics findOneByCompanyId(String companyId);

	int updateByCompanyId(MapContext updateDealerShipping);
}