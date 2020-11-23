package com.lwxf.industry4.webapp.facade.admin.factory.qrcodePay.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.lwxf.industry4.webapp.facade.admin.factory.qrcodePay.AliQrcodeFacade;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 *
 * @version 1.0
 * @Auther: SunXianWei
 * @Date: 2020/7/15 15:26
 * @Description:
 */
@Component("aliQrcodeFacade")
public class AliQrcodeFacadeImpl extends BaseFacadeImpl implements AliQrcodeFacade {

    @Resource
    private AlipayClient alipayClient;
    @Override
    public String qrcodeUrl(String orderNo, String totalAmount, HttpServletRequest requests, HttpServletResponse response){
        //商户预创建支付订单，生成二维码
        AlipayTradePrecreateModel model=new AlipayTradePrecreateModel();
        model.setOutTradeNo(orderNo+System.currentTimeMillis()); //商户订单号
        model.setSubject(orderNo);     //订单标题
        model.setTotalAmount(totalAmount);//订单总金额
        AlipayTradePrecreateRequest request=new AlipayTradePrecreateRequest();
        request.setBizModel(model);
        String content="";
        AlipayTradePrecreateResponse alipayTradePrecreateResponse= null;
        try {
            alipayTradePrecreateResponse = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        content=alipayTradePrecreateResponse.getQrCode();
        return content;
    }

}
