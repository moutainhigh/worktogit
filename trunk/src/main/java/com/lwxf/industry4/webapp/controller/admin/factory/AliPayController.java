package com.lwxf.industry4.webapp.controller.admin.factory;

import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeCancelModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeCancelRequest;
import com.alipay.api.response.AlipayTradeCancelResponse;
import com.github.binarywang.utils.qrcode.MatrixToImageWriter;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.config.alipay.AlipayProperties;
import com.lwxf.industry4.webapp.facade.admin.factory.qrcodePay.AliQrcodeFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: SunXianWei
 * @Date: 2020/07/06/17:04
 * @Description:
 */
@Api(value = "AliPayController",tags = {"支付宝二维码付款"})
@RestController
@RequestMapping(value = "/api/ali", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class AliPayController {
    @Resource
    private AlipayClient alipayClient;

    @Resource
    private AlipayProperties alipayProperties;

    @Resource(name = "aliQrcodeFacade")
    private AliQrcodeFacade aliQrcodeFacade;

    @ApiOperation(value = "商户预创建支付订单，生成二维码", notes = "商户预创建支付订单，生成二维码")
    @RequestMapping("/create")
    public String precreate(@ApiParam(value="支付订单号",required = true)@RequestParam(required = true) String orderNo,
                          @ApiParam(value="支付订单金额",required = true)@RequestParam(required = true) String totalAmount,
                          HttpServletRequest requests,
                          HttpServletResponse response) throws Exception{
        return this.aliQrcodeFacade.qrcodeUrl(orderNo,totalAmount,requests,response);
        //makeQRCode(content,response.getOutputStream());
    }

    public void makeQRCode(String content, OutputStream outputStream) throws Exception{  //生成二维码
        Map<EncodeHintType,Object> map=new HashMap<>();
        map.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        map.put(EncodeHintType.MARGIN,2);

        QRCodeWriter qrCodeWriter=new QRCodeWriter();
        BitMatrix bitMatrix=qrCodeWriter.encode(content, BarcodeFormat.QR_CODE,200,200,map);
        MatrixToImageWriter.writeToStream(bitMatrix,"jpeg",outputStream);
    }

    @RequestMapping("/cancel")
    public String cancel(@ApiParam(value="支付订单号",required = true)@RequestParam(required = true) String orderNo) throws Exception{  //取消订单，支付超时、支付结果未知是可撤销，超过24小时不可撤销
        AlipayTradeCancelModel model=new AlipayTradeCancelModel();
        model.setOutTradeNo(orderNo);

        AlipayTradeCancelRequest request=new AlipayTradeCancelRequest();
        request.setBizModel(model);

        AlipayTradeCancelResponse response=alipayClient.execute(request);
        return response.getBody();
    }

    @RequestMapping("/notify")
    public void notify(HttpServletRequest request) throws Exception{//trade_success状态下异步通知接口
        if (check(request.getParameterMap())){
            System.out.println(request.getParameter("trade_status"));
            System.out.println("异步通知 "+ Instant.now());
        }else {
            System.out.println("验签失败");
        }
    }

    @RequestMapping("/return")
    public String returnUrl(HttpServletRequest request) throws Exception{  //订单支付成功后同步返回地址
        if (check(request.getParameterMap())){
            return "success";
        }else {
            return "false";
        }
    }

    private boolean check(Map<String,String[]> requestParams) throws Exception{  //对return、notify参数进行验签
        Map<String,String> params = new HashMap<>();

        for (String name : requestParams.keySet()) {
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }

            params.put(name, valueStr);
            System.out.println(name+" ==> "+valueStr);
        }

        return AlipaySignature.rsaCheckV1(params, alipayProperties.getAlipayPublicKey(),
                alipayProperties.getCharset(), alipayProperties.getSignType()); //调用SDK验证签名
    }


}
