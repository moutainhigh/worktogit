package com.lwxf.industry4.webapp.controller.admin.factory;

import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.config.unionpay.sdk.AcpService;
import com.lwxf.industry4.webapp.config.unionpay.sdk.LogUtil;
import com.lwxf.industry4.webapp.config.unionpay.sdk.SDKConstants;
import com.lwxf.industry4.webapp.facade.admin.factory.qrcodePay.UnionpayFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @version 1.0
 * @Auther: SunXianWei
 * @Date: 2020/7/14 17:14
 * @Description:
 */
@Api(value = "UnionPayController", tags = {"银联二维码付款"})
@RestController("UnionPayController")
@RequestMapping(value = "/api/unionpay", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class UnionPayController {

    @Resource(name = "unionpayFacade")
    private UnionpayFacade unionpayFacade;
    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;

    @ApiOperation(value = "生成二维码url", notes = "生成二维码url")
    @GetMapping("/unionpayqrcode")
    protected String doPost(@RequestParam(required = true) String orderNo,
                            @RequestParam(required = true) String txnAmt,
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {
        return this.unionpayFacade.unionQrcodeUrl(orderNo,txnAmt,req,resp);
    }

    /***
     * unionpay 异步通知支付结果
     * @param req
     * @param resp
     * @throws IOException
     */
    @RequestMapping("/Notify")
    public void Notifyunionpay(HttpServletRequest req,HttpServletResponse resp) throws IOException{
        // 获取银联post过来的信息
        System.out.println("BackRcvResponse接收后台通知开始");
        String encoding = req.getParameter(SDKConstants.param_encoding);
        Map<String, String> reqParam = getAllRequestParam(req);
        LogUtil.printRequestLog(reqParam);

        Map<String, String> valideData = null;
        if (null != reqParam && !reqParam.isEmpty()) {
            Iterator<Map.Entry<String, String>> it = reqParam.entrySet().iterator();
            valideData = new HashMap<String, String>(reqParam.size());
            while (it.hasNext()) {
                Map.Entry<String, String> e = it.next();
                String key = (String) e.getKey();
                String value = (String) e.getValue();

                valideData.put(key, value);
            }
        }

        //重要！验证签名前不要修改reqParam中的键值对的内容，否则会验签不过
        if (!AcpService.validate(valideData, encoding)) {
            LogUtil.writeLog("验证签名结果[失败].");
            System.out.println("验证签名结果[失败].");
            //验签失败，需解决验签问题

        } else {
            LogUtil.writeLog("验证签名结果[成功].");
            System.out.println("验证签名结果[成功].");
            //【注：为了安全验签成功才应该写商户的成功处理逻辑】交易成功，更新商户订单状态

            String orderId =valideData.get("orderId"); //获取后台通知的数据，其他字段也可用类似方式获取

            String respCode = valideData.get("respCode");
            //判断respCode=00、A6后，对涉及资金类的交易，请再发起查询接口查询，确定交易成功后更新数据库。
        }
        LogUtil.writeLog("BackRcvResponse接收后台通知结束");
        //返回给银联服务器http 200  状态码
        resp.getWriter().print("ok");
    }
    /**
     * 获取请求参数中所有的信息
     *
     * @param request
     * @return
     */
    public static Map<String, String> getAllRequestParam(final HttpServletRequest request) {
        Map<String, String> res = new HashMap<String, String>();
        Enumeration<?> temp = request.getParameterNames();
        if (null != temp) {
            while (temp.hasMoreElements()) {
                String en = (String) temp.nextElement();
                String value = request.getParameter(en);
                res.put(en, value);
                //在报文上送时，如果字段的值为空，则不上送<下面的处理为在获取所有参数数据时，判断若值为空，则删除这个字段>
                //System.out.println("ServletUtil类247行  temp数据的键=="+en+"     值==="+value);
                if (null == res.get(en) || "".equals(res.get(en))) {
                    res.remove(en);
                }
            }
        }
        return res;
    }


}
