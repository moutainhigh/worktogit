package com.lwxf.industry4.webapp.facade.admin.factory.CorporatePartners;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.corporatePartners.CorporatePartners;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/8/1/001 14:26
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface CorporatePartnersFacade extends BaseFacade {
	RequestResult findCorporatePartnersList(MapContext mapContext, Integer pageSize, Integer pageNum);

	RequestResult addCorporatePartners(CorporatePartners corporatePartners);
	RequestResult addoutAccount(CorporatePartners corporatePartners);

	RequestResult updateCorporatePartners(String id, MapContext update);

	RequestResult deleteCorporatePartners(String id);

	RequestResult findCorporatePartnersInfo(String id);
}
