package com.lwxf.industry4.webapp.facade.admin.factory.qrcodePay;

import com.lwxf.industry4.webapp.facade.base.BaseFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 *
 * @version 1.0
 * @Auther: SunXianWei
 * @Date: 2020/7/15 15:25
 * @Description:
 */
public interface AliQrcodeFacade extends BaseFacade {
    String qrcodeUrl(String orderNo, String totalAmount, HttpServletRequest requests, HttpServletResponse response);
}
