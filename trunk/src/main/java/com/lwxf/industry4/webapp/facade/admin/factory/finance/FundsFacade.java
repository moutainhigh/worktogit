package com.lwxf.industry4.webapp.facade.admin.factory.finance;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.financing.Funds;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/9/25 0025 14:16
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface FundsFacade extends BaseFacade {

	RequestResult findFundsList(MapContext mapContext);

	RequestResult addFunds(Funds funds);

	RequestResult updateFunds(MapContext mapContext);

	RequestResult deleteFunds(String id);
}
