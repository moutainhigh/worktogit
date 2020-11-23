package com.lwxf.industry4.webapp.controller;

import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.facade.admin.factory.qrcodePay.AliQrcodeFacade;
import com.lwxf.industry4.webapp.facade.admin.factory.qrcodePay.UnionpayFacade;
import com.lwxf.industry4.webapp.facade.admin.factory.qrcodePay.WechatQrcodeFacade;
import com.lwxf.mybatis.utils.MapContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @version 1.0
 * @Auther: SunXianWei
 * @Date: 2020/7/15 14:16
 * @Description:
 */
@Api(value = "QrCodePayController",tags = {"支付宝/微信/银联二维码付款"})
@RestController
@RequestMapping(value = "/api/ercodepay", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class QrCodePayController {

    @Resource(name = "aliQrcodeFacade")
    private AliQrcodeFacade aliQrcodeFacade;
    @Resource(name = "wechatQrcodeFacade")
    private WechatQrcodeFacade wechatQrcodeFacade;
    @Resource(name = "unionpayFacade")
    private UnionpayFacade unionpayFacade;

    @ApiOperation(value = "支付宝/微信/银联二维码付款",notes = "支付宝/微信/银联二维码付款")
    @PostMapping
    public RequestResult QrcodePay(@ApiParam(value="支付订单号",required = true)@RequestParam(required = true) String orderNo,
                                   @ApiParam(value="支付订单金额",required = true)@RequestParam(required = true) String amount,
                                   HttpServletRequest requests,
                                   HttpServletResponse response){

        List result=new ArrayList<>();
        if(Integer.valueOf(amount)!=0) {
            MapContext map = MapContext.newOne();
            //阿里支付url
            String aliurl = aliQrcodeFacade.qrcodeUrl(orderNo, amount, requests, response);
            map.put("name", "支付宝");
            map.put("url", aliurl);
            result.add(map);
            //微信支付url
            MapContext map1 = MapContext.newOne();
            String wechatUrl = wechatQrcodeFacade.wechatQrcodeUrl(orderNo, amount, requests, response);
            map1.put("name", "微信");
            map1.put("url", wechatUrl);
            result.add(map1);
            //银联支付url
            MapContext map2 = MapContext.newOne();
            String unionpayUrl = unionpayFacade.unionQrcodeUrl(orderNo, amount, requests, response);
            map2.put("name", "银联");
            map2.put("url", unionpayUrl);
            result.add(map2);
        }
        return ResultFactory.generateRequestResult(result);
    }
}
