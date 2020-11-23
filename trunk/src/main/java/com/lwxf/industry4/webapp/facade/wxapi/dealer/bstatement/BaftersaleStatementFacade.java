package com.lwxf.industry4.webapp.facade.wxapi.dealer.bstatement;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/7/8 0008 10:10
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface BaftersaleStatementFacade extends BaseFacade {

	RequestResult aftersaleStatements(String companyId, String countType, MapContext mapContext);

	RequestResult weekAftersaleStatements(String companyId, String countType);

	RequestResult monthAftersaleStatements(String companyId, String countType);

	RequestResult quarterAftersaleStatements(String companyId, String countType);

	RequestResult yearAftersaleStatements(String companyId, String countType);
}
