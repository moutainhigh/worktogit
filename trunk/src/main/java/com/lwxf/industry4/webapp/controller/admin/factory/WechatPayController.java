package com.lwxf.industry4.webapp.controller.admin.factory;

import com.github.binarywang.utils.qrcode.MatrixToImageWriter;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.facade.admin.factory.qrcodePay.WechatQrcodeFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *微信二维码付款
 * @Auther: SunXianWei
 * @Date: 2020/07/10/16:05
 * @Description:
 */
@Api(value = "WechatPayController",tags = {"微信二维码付款"})
@RestController
@RequestMapping(value = "/api/wechat", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class WechatPayController {

    @Resource(name = "wechatQrcodeFacade")
    private WechatQrcodeFacade wechatQrcodeFacade;
    @GetMapping("/pay")
    @ApiOperation(value = "",notes = "")
    public String wechatPay(@ApiParam(value="支付订单号",required = true)@RequestParam(required = true) String orderNo,
                          @ApiParam(value="支付订单金额",required = true)@RequestParam(required = true) String totalAmount,
                          HttpServletRequest requests,
                          HttpServletResponse response) {
        return this.wechatQrcodeFacade.wechatQrcodeUrl(orderNo,totalAmount,requests,response);
    }
    public void makeQRCode(String content, OutputStream outputStream) throws Exception{  //生成二维码
        Map<EncodeHintType,Object> map=new HashMap<>();
        map.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        map.put(EncodeHintType.MARGIN,2);
        QRCodeWriter qrCodeWriter=new QRCodeWriter();
        BitMatrix bitMatrix=qrCodeWriter.encode(content, BarcodeFormat.QR_CODE,200,200,map);
        MatrixToImageWriter.writeToStream(bitMatrix,"jpeg",outputStream);
    }

}
