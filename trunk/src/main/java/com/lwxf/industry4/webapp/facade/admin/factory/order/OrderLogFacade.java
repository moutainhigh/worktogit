package com.lwxf.industry4.webapp.facade.admin.factory.order;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;

public interface OrderLogFacade extends BaseFacade {

    RequestResult findOrderLogs(String id);
}
