package com.lwxf.industry4.webapp.facade.admin.factory.qrcodePay;

import com.lwxf.industry4.webapp.facade.base.BaseFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 *
 * @version 1.0
 * @Auther: SunXianWei
 * @Date: 2020/7/15 16:10
 * @Description:
 */
public interface UnionpayFacade extends BaseFacade {
    String unionQrcodeUrl(String orderNo, String txnAmt, HttpServletRequest req, HttpServletResponse resp);
}
