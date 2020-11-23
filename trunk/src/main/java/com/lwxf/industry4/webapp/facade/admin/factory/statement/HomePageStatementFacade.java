package com.lwxf.industry4.webapp.facade.admin.factory.statement;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2020-03-12 15:35
 * @version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface HomePageStatementFacade extends BaseFacade {
    RequestResult findHomePageStatement(String startTime, String endTime,String type );

    RequestResult findDealerHomePageStatement(String startTime, String endTime, String type);

    RequestResult findDealerHomePageStatementV2(String orderStartTime, String orderEndTime, String orderType, String payStartTime, String payEndTime, String payType);
}
