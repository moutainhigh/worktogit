package com.lwxf.industry4.webapp.web.SchedulerConfig;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yunpian.sdk.YunpianClient;
import com.yunpian.sdk.model.Result;
import com.yunpian.sdk.model.SmsSingleSend;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.baseservice.sms.yunpian.SmsUtil;
import com.lwxf.industry4.webapp.bizservice.customorder.CustomOrderService;
import com.lwxf.industry4.webapp.common.enums.order.OrderType;
import com.lwxf.industry4.webapp.common.utils.DateUtilsExt;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：支付提醒定时任务
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/11/11 0011 16:57
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component
public class PaymentReminderScheduler {
	private static final Logger logger = LoggerFactory.getLogger(SmsUtil.class);
	@Resource(name = "customOrderService")
	private CustomOrderService customOrderService;

	private static final String TEMPLATE_USER_ACCOUNT_INFO = "【红田家居】尊敬的{0}，您的订单：{1}还未付款，应付金额为：{2}元，已付付金额为{3}元，请尽快处理！如已支付请忽略";

	//@Scheduled(cron = "0 0 10 * * ?")//每天10点触发一次
	public void paymentReminderScheduler() {
		Date date = DateUtil.getSystemDate();
		Date frontDay = DateUtilsExt.getFrontDay(date, 7);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String frontDayValue=dateFormat.format(frontDay);
		Integer type = OrderType.NORMALORDER.getValue();
		String branchId= WebUtils.getCurrUserId();
		MapContext params=MapContext.newOne();
		params.put("branchId",branchId);
		params.put("type",type);
		params.put("date",frontDayValue);
		List<MapContext> list=this.customOrderService.findByBranchIdAndTypeAndTime(params);
		for(MapContext map:list){
			String phone=map.getTypedValue("phone",String.class);
			String name=map.getTypedValue("name",String.class);
			String orderNo=map.getTypedValue("orderNo",String.class);
			String money=map.getTypedValue("money",String.class);
			sendPaymentReminderMessage(phone,name,orderNo,money);

		}
	}

	public static void sendPaymentReminderMessage(String phoneNumber, String name, String orderNo, String money) {
		Map<String, String> params = new HashMap<>();
		String smsText = LwxfStringUtils.format(TEMPLATE_USER_ACCOUNT_INFO, name, orderNo,money,"0");
		params.put(YunpianClient.MOBILE, phoneNumber);
		params.put(YunpianClient.TEXT, smsText);

		Result<SmsSingleSend> sendResult = AppBeanInjector.yunpianClient.sms().single_send(params);
		// 出现错误时输出错误信息
		if (sendResult.getCode() == 0) {

		} else {
			Throwable throwable = sendResult.getThrowable();
			if (null != throwable) {
				logger.error("********************* throwable = {}", sendResult.getThrowable());
			}
		}
	}
}
