package com.lwxf.industry4.webapp.facade.wxapi.factory.company;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.company.CompanyMessage;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

public interface CompanyMessageFacade   extends BaseFacade {

    RequestResult findCompanyMessageList(MapContext mapContext, Integer pageNum, Integer pageSize);

    RequestResult sendCompanyMessage(CompanyMessage cmpanyMessage);

    RequestResult getCompanyList(MapContext userInfo);

	RequestResult findMessagesByFromuser(Integer pageNum, Integer pageSize,MapContext map);
}
