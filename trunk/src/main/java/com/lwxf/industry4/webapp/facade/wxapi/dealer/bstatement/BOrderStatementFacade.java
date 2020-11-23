package com.lwxf.industry4.webapp.facade.wxapi.dealer.bstatement;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/7/4 0004 10:19
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface BOrderStatementFacade extends BaseFacade {
	RequestResult monthOrderStatements(String companyId, String countType);

	RequestResult weekOrderStatements(String companyId, String countType);

	RequestResult quarterOrderStatements(String companyId, String countType);

	RequestResult yearOrderStatements(String companyId, String countType);

	RequestResult orderStatements(String companyId, String countType, MapContext mapContext);
}
