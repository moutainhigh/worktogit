package com.lwxf.industry4.webapp.facade.quickshare;

import javax.servlet.http.HttpServletRequest;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/3/6 0006 15:07
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface QuicksharePraiseFacade extends BaseFacade {


	RequestResult updateQuicksharePraise(String quickshareId, HttpServletRequest request);
}
