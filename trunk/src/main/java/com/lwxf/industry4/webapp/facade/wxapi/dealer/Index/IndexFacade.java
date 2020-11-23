package com.lwxf.industry4.webapp.facade.wxapi.dealer.Index;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;

public interface IndexFacade extends BaseFacade {

    RequestResult viewIndex(String companyId);
}
