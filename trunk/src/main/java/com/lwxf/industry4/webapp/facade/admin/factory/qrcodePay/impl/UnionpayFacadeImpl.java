package com.lwxf.industry4.webapp.facade.admin.factory.qrcodePay.impl;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.config.unionpay.sdk.AcpService;
import com.lwxf.industry4.webapp.config.unionpay.sdk.LogUtil;
import com.lwxf.industry4.webapp.config.unionpay.sdk.SDKConfig;
import com.lwxf.industry4.webapp.facade.admin.factory.qrcodePay.UnionpayFacade;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @version 1.0
 * @Auther: SunXianWei
 * @Date: 2020/7/15 16:10
 * @Description:
 */
@Component("unionpayFacade")
public class UnionpayFacadeImpl extends BaseFacadeImpl implements UnionpayFacade {
    @Value("${union.merId}")
    private String merId;
    @Override
    public String unionQrcodeUrl(String orderNo, String txnAmt, HttpServletRequest req, HttpServletResponse resp) {
        SDKConfig.getConfig().loadPropertiesFromSrc();// 从classpath加载acp_sdk.properties文件
        // 生成商户订单号  orderId
        SimpleDateFormat simple=new SimpleDateFormat("yyyyMMddHHmmss");
        Date systemDate = DateUtil.getSystemDate();
        String txnTime = null; //订单时间
        try {
            txnTime = simple.format(systemDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String  orderId = orderNo+txnTime;
        orderId=orderId.replaceAll("-","");
        Map<String, String> contentData = new HashMap<String, String>();

        /***银联全渠道系统，产品参数，除了encoding自行选择外其他不需修改***/
        contentData.put("version", SDKConfig.getConfig().getVersion());            //版本号 全渠道默认值
        contentData.put("encoding", "UTF-8");     //字符集编码 可以使用UTF-8,GBK两种方式
        contentData.put("signMethod", SDKConfig.getConfig().getSignMethod()); //签名方法  默认01
        contentData.put("txnType", "01");              		 	//交易类型 01:消费
        contentData.put("txnSubType", "07");           		 	//交易子类 07：申请消费二维码
        contentData.put("bizType", "000000");          		 	//填写000000
        contentData.put("channelType", "08");          		 	//渠道类型 08手机

        /***商户接入参数***/
        contentData.put("merId", merId);   		 	//商户号码，请改成自己申请的商户号或者open上注册得来的777商户号测试
        contentData.put("accessType", "0");            		 	//接入类型，商户接入填0 ，不需修改（0：直连商户， 1： 收单机构 2：平台商户）
        contentData.put("orderId", orderId);        	 	    //商户订单号，8-40位数字字母，不能含“-”或“_”，可以自行定制规则
        contentData.put("txnTime", txnTime);		 		    //订单发送时间，取系统时间，格式为YYYYMMDDhhmmss，必须取当前时间，否则会报txnTime无效
        contentData.put("txnAmt", Integer.valueOf(txnAmt)*100+"");	//交易金额 单位为分，不能带小数点
        contentData.put("currencyCode", "156");                 //交易币种 境内商户固定 156 人民币

        // 请求方保留域，
        // 透传字段，查询、通知、对账文件中均会原样出现，如有需要请启用并修改自己希望透传的数据。
        // 出现部分特殊字符时可能影响解析，请按下面建议的方式填写：
        // 1. 如果能确定内容不会出现&={}[]"'等符号时，可以直接填写数据，建议的方法如下。
//		contentData.put("reqReserved", "透传信息1|透传信息2|透传信息3");
        // 2. 内容可能出现&={}[]"'符号时：
        // 1) 如果需要对账文件里能显示，可将字符替换成全角＆＝｛｝【】“‘字符（自己写代码，此处不演示）；
        // 2) 如果对账文件没有显示要求，可做一下base64（如下）。
        //    注意控制数据长度，实际传输的数据长度不能超过1024位。
        //    查询、通知等接口解析时使用new String(Base64.decodeBase64(reqReserved), DemoBase.encoding);解base64后再对数据做后续解析。
//		contentData.put("reqReserved", Base64.encodeBase64String("任意格式的信息都可以".toString().getBytes(DemoBase.encoding)));

        //后台通知地址（需设置为外网能访问 http https均可），支付成功后银联会自动将异步通知报文post到商户上送的该地址，【支付失败的交易银联不会发送后台通知】
        //后台通知参数详见open.unionpay.com帮助中心 下载  产品接口规范  网关支付产品接口规范 消费交易 商户通知
        //注意:1.需设置为外网能访问，否则收不到通知    2.http https均可  3.收单后台通知后需要10秒内返回http200或302状态码
        //    4.如果银联通知服务器发送通知后10秒内未收到返回状态码或者应答码非http200或302，那么银联会间隔一段时间再次发送。总共发送5次，银联后续间隔1、2、4、5 分钟后会再次通知。
        //    5.后台通知地址如果上送了带有？的参数，例如：http://abc/web?a=b&c=d 在后台通知处理程序验证签名之前需要编写逻辑将这些字段去掉再验签，否则将会验签失败
        contentData.put("backUrl", SDKConfig.getConfig().getBackUrl());
        System.out.println(SDKConfig.getConfig().getBackUrl()+"=SDKConfig.getConfig().getBackUrl()");

        /**对请求参数进行签名并发送http post请求，接收同步应答报文**/
        Map<String, String> reqData = AcpService.sign(contentData,"UTF-8");			 //报文中certId,signature的值是在signData方法中获取并自动赋值的，只要证书配置正确即可。
        String requestAppUrl = SDKConfig.getConfig().getBackRequestUrl();								 //交易请求url从配置文件读取对应属性文件acp_sdk.properties中的 acpsdk.backTransUrl
        Map<String, String> rspData = AcpService.post(reqData,requestAppUrl,"UTF-8");  //发送请求报文并接受同步应答（默认连接超时时间30秒，读取返回结果超时时间30秒）;这里调用signData之后，调用submitUrl之前不能对submitFromData中的键值对做任何修改，如果修改会导致验签不通过
        System.out.println("rspData="+rspData);
        /**对应答码的处理，请根据您的业务逻辑来编写程序,以下应答码处理逻辑仅供参考------------->**/
        //应答码规范参考open.unionpay.com帮助中心 下载  产品接口规范  《平台接入接口规范-第5部分-附录》
        String qrCode="";
        if(!rspData.isEmpty()){
            if(AcpService.validate(rspData, "UTF-8")){
                LogUtil.writeLog("验证签名成功");
                String respCode = rspData.get("respCode");
                if(("00").equals(respCode)){
                    //成功,获取url
                     qrCode = rspData.get("qrCode");
                    System.out.println(qrCode);
                    return qrCode;
                }else{
                    LogUtil.writeErrorLog(rspData.get("respMsg"));
                }
            }else{
                LogUtil.writeErrorLog("验证签名失败");
                //  System.out.println("验证签名失败");

            }
        }else{
            //未返回正确的http状态
            LogUtil.writeErrorLog("未获取到返回报文或返回http状态码非200");
            System.out.println("未获取到返回报文或返回http状态码非200");
        }
        return qrCode;
    }
}
