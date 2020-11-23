package com.lwxf.industry4.webapp.controller.mobile;

import com.lwxf.industry4.webapp.domain.dto.financing.PaymentSimpleDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.error.WxErrorException;

import javax.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.Blank;
import com.lwxf.industry4.webapp.common.enums.company.DealerEmployeeRole;
import com.lwxf.industry4.webapp.common.mobile.WeixinUtils;
import com.lwxf.industry4.webapp.common.mobile.weixin.XMLUtils;
import com.lwxf.industry4.webapp.common.mobile.weixin.template.ApplyClerkSuccessMsg;
import com.lwxf.industry4.webapp.common.mobile.weixin.template.UserLeaveMsg;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.user.LoginedUser;
import com.lwxf.industry4.webapp.domain.entity.user.User;
import com.lwxf.industry4.webapp.domain.entity.user.UserThirdInfo;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.json.JsonMapper;
import com.lwxf.commons.utils.LwxfStringUtils;

import static com.lwxf.industry4.webapp.facade.AppBeanInjector.i18nUtil;
import static com.lwxf.industry4.webapp.facade.AppBeanInjector.weixinFacade;
import static com.lwxf.industry4.webapp.facade.AppBeanInjector.wxMpService;

/**
 * 功能：
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018-05-24 9:53
 * @version：2018 1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@RestController
@Api(value="WeixinController",tags={"F端微信服务号接口:服务号相关接口"})
@RequestMapping(value = "/api")
public class WeixinController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	// 微信基础token验证
	@ApiOperation(value = "微信基础token验证", notes = "")
	@RequestMapping(value = "/wx",method ={RequestMethod.GET, RequestMethod.POST})
	public String checkToken(HttpServletRequest request, @RequestParam String signature, @RequestParam String timestamp, @RequestParam String nonce, @RequestParam(required = false) String echostr){
		// 验证签名
//		if(!wxMpService.checkSignature(timestamp, nonce,signature)){
//			return "";
//		}

		// 接入验证
		if(null != echostr){
			return echostr;
		}

		try {
			Map<String,String> reqXmlData = XMLUtils.parseXmlFromRequest(request);
			weixinFacade.doRequestXmlData(request.getQueryString(), reqXmlData);

			// 发送会员离开的消息
			Map<String,Object> userLeaveData = (Map)WebUtils.getDataFromRequestMap(WebConstant.WX_TEMPLATE_MSG_USER_LEAVE_DATA);
			if(null != userLeaveData){
				User user = (User)userLeaveData.get("userController");
				if(null == user){
					return WebConstant.STRING_EMPTY;
				}
				List<UserThirdInfo> tousers = (List<UserThirdInfo>)userLeaveData.get("tousers");
				UserLeaveMsg mobileMsg = new UserLeaveMsg();
				String toUser;
				for (UserThirdInfo userThirdInfo :tousers){
					toUser = userThirdInfo.getWxOpenId();
					if(LwxfStringUtils.isNotBlank(toUser)){
						mobileMsg.setTouser(toUser);
						mobileMsg.setUserInfo(user);
						AppBeanInjector.weixinTemplateMsgService.pushMsg(mobileMsg);
					}
				}
			}
			ApplyClerkSuccessMsg mobileMsg = (ApplyClerkSuccessMsg)WebUtils.getDataFromRequestMap(WebConstant.WX_TEMPLATE_MSG_APPLY_SUCCESS);
			if(null!=mobileMsg){
				AppBeanInjector.weixinTemplateMsgService.pushMsg(mobileMsg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("读取微信消息时异常:{}", e.getMessage(), e);
		}
		return WebConstant.STRING_EMPTY;
	}

	// 为用户生成关注微信二维码
	@ApiOperation(value = "为用户生成关注微信二维码", notes = "")
	@PostMapping(value = "/users/0/qrcode",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON)
	public String postQrCode(){
		JsonMapper jsonMapper = JsonMapper.nonEmptyMapper();
		RequestResult result;
		String createUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=";
		try {
			String accessToken =  wxMpService.getAccessToken();
			logger.debug("postQrCode获取accessToken:{}", accessToken);
			createUrl = createUrl + accessToken;
			logger.info("createUrl:{}",createUrl);
		} catch (WxErrorException e) {
			logger.error(e.getMessage());
		}

		HttpURLConnection urlConnection = null;
		try {
			urlConnection = WeixinUtils.createConnection(createUrl, RequestMethod.POST.toString());
			urlConnection.connect();
			logger.info("easypm4 urlConnection.connect");
			//POST请求参数
			DataOutputStream out = new DataOutputStream(urlConnection.getOutputStream());
			jsonMapper.putToMap("expire_seconds",86400);//5
			jsonMapper.putToMap("action_name","QR_STR_SCENE");//临时二维码
			Map<String,Object> sceneId = new HashMap<>();

			Map map = new HashMap();
			WebUtils.getCurrUser();
			map.put("userId", WebUtils.getCurrUserId());

			sceneId.put("scene_str", jsonMapper.toJson(map));
			jsonMapper.putToMap("action_info/scene", sceneId);
			String writeJson = jsonMapper.rootMaptoJson();
			this.logger.debug("生成二维码json参数："+writeJson);
			out.writeBytes(writeJson);
			out.flush();
			out.close();

			//读取响应结果
			BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));
			String readLine;
			StringBuilder sb = new StringBuilder();
			while ((readLine = reader.readLine()) != null) {
				readLine = new String(readLine.getBytes("UTF-8"), "UTF-8");
				sb.append(readLine);
			}
			reader.close();
			logger.debug("二维码信息："+sb.toString());
			return sb.toString();
		} catch (Exception e) {
			this.logger.error("生成用户二维码失败", e);
			result = ResultFactory.generateErrorResult(ErrorCodes.BIZ_MB_CREATE_QRCODE_FAIL_10027,i18nUtil.getMessage("BIZ_MB_CREATE_QRCODE_FAIL_10027"));
			return jsonMapper.toJson(result);
		}finally {
			if(urlConnection != null){
				urlConnection.disconnect();
			}
		}
	}

	// 解绑微信号（不取消关注）
	@ApiOperation(value = "解绑微信号（不取消关注）", notes = "")
	@DeleteMapping(value = "/users/0/wx",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON)
	public RequestResult deleteUnbind(){
		return weixinFacade.unbindWeixinByUserId(WebUtils.getCurrUserId());
	}

	// 创建微信服务号菜单
	@ApiOperation(value = "创建微信服务号菜单", notes = "", response = PaymentSimpleDto.class)
	@RequestMapping(value = "/users/0/wx/menu",method = RequestMethod.POST, produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON)
	public String createMenu(@RequestParam(required = true) String menu){
		String result = this.checkCurrUser();
		if(null != result){
			return result;
		}
		return weixinFacade.createMenu(menu);
	}
	// 删除微信服务号菜单
	@ApiOperation(value = "删除微信服务号菜单", notes = "")
	@RequestMapping(value = "/users/0/wx/menu",method = RequestMethod.GET, produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON)
	public String deleteMenu(){
		String result = this.checkCurrUser();
		if(null != result){
			return result;
		}
		return weixinFacade.deleteMenu();
	}

	// 获取微信的config信息（微信签名相关信息）
	@ApiOperation(value = "获取微信的config信息（微信签名相关信息）", notes = "")
	@RequestMapping(value = "/wx/jscfg",method = RequestMethod.GET, produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON)
	public String getJSSDKConfig(@RequestParam String url){
		return weixinFacade.getJSSDKConfig(url);
	}


	private String checkCurrUser(){
		boolean checked = true;
		if(checked){
			return null;
		}
		RequestResult result = ResultFactory.generateErrorResult(ErrorCodes.BIZ_NO_PERMISSION_10003,i18nUtil.getMessage("BIZ_NO_PERMISSION_10003"));
		return JsonMapper.nonEmptyMapper().toJson(result);
	}

	// 为用户生成绑定店主微信二维码
	@ApiOperation(value = "为用户生成绑定店主微信二维码", notes = "")
	@PostMapping(value = "/users/0/qrcodes/bingshopkeeper",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON)
	public RequestResult postBingShopKeeper(){
		JsonMapper jsonMapper = JsonMapper.nonEmptyMapper();
		RequestResult result;
		LoginedUser currUser = WebUtils.getCurrUser();
		//查询到当前登录的用户
		//如果当前用户的角色不是店主，则返回错误码
		/*CompanyEmployee employee = currUser.getRole();
		if(null == employee || employee.getRole().intValue() != DealerEmployeeRole.SHOPKEEPER.getValue()){
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_NO_PERMISSION_10003,AppBeanInjector.i18nUtil.getMessage("BIZ_NO_PERMISSION_10003"));
		}*/

		String createUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=";
		try{
			String accessToken =  wxMpService.getAccessToken();
			logger.debug("postQrCode获取accessToken:{}", accessToken);
			createUrl = createUrl + accessToken;
			logger.info("createUrl:{}",createUrl);
		}catch (WxErrorException e) {
			logger.error(e.getMessage());
		}

		HttpURLConnection urlConnection = null;
		try {
			urlConnection = WeixinUtils.createConnection(createUrl, RequestMethod.POST.toString());
			urlConnection.connect();
			logger.info("easypm4 urlConnection.connect");
			//POST请求参数
			DataOutputStream out = new DataOutputStream(urlConnection.getOutputStream());
			jsonMapper.putToMap("expire_seconds",300);//有效时间5分钟
			jsonMapper.putToMap("action_name","QR_STR_SCENE");//临时二维码
			Map<String,Object> sceneId = new HashMap<>();

			Map map = new HashMap();
			map.put("user", WebUtils.getCurrUserId());
			map.put("role", DealerEmployeeRole.SHOPKEEPER.getValue());
			map.put("flag",Blank.BINDSHOPKEEPER.getValue());

			sceneId.put("scene_str", jsonMapper.toJson(map));
			jsonMapper.putToMap("action_info/scene", sceneId);
			String writeJson = jsonMapper.rootMaptoJson();
			this.logger.debug("生成二维码json参数："+writeJson);
			out.writeBytes(writeJson);
			out.flush();
			out.close();

			//读取响应结果
			BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));
			String readLine;
			StringBuilder sb = new StringBuilder();
			while ((readLine = reader.readLine()) != null) {
				readLine = new String(readLine.getBytes("UTF-8"), "UTF-8");
				sb.append(readLine);
			}
			reader.close();
			logger.debug("二维码信息："+sb.toString());
			return ResultFactory.generateRequestResult(jsonMapper.fromJson(sb.toString(),Map.class));
		} catch (Exception e) {
			this.logger.error("生成用户二维码失败", e);
			result = ResultFactory.generateErrorResult(ErrorCodes.BIZ_MB_CREATE_QRCODE_FAIL_10027,i18nUtil.getMessage("BIZ_MB_CREATE_QRCODE_FAIL_10027"));
			return result;
		}finally {
			if(urlConnection != null){
				urlConnection.disconnect();
			}
		}
	}

	// 为用户生成添加店员微信二维码
	@ApiOperation(value = "为用户生成添加店员微信二维码", notes = "")
	@PostMapping(value = "/users/0/qrcodes/addclerk",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON)
	public RequestResult postBingclerk(){
		JsonMapper jsonMapper = JsonMapper.nonEmptyMapper();
		RequestResult result;
		String createUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=";
		try{
			String accessToken =  wxMpService.getAccessToken();
			logger.debug("postQrCode获取accessToken:{}", accessToken);
			createUrl = createUrl + accessToken;
			logger.info("createUrl:{}",createUrl);
		}catch (WxErrorException e) {
			logger.error(e.getMessage());
		}

		HttpURLConnection urlConnection = null;
		try {
			urlConnection = WeixinUtils.createConnection(createUrl, RequestMethod.POST.toString());
			urlConnection.connect();
			logger.info("easypm4 urlConnection.connect");
			//POST请求参数
			DataOutputStream out = new DataOutputStream(urlConnection.getOutputStream());
			jsonMapper.putToMap("expire_seconds",300);//有效时间5分钟
			jsonMapper.putToMap("action_name","QR_STR_SCENE");//临时二维码
			Map<String,Object> sceneId = new HashMap<>();

			Map map = new HashMap();
			map.put("role",DealerEmployeeRole.CLERK.getValue());
			map.put("flag",Blank.ADDCLERK.getValue());
			map.put("user", WebUtils.getCurrUserId());

			sceneId.put("scene_str", jsonMapper.toJson(map));
			jsonMapper.putToMap("action_info/scene", sceneId);
			String writeJson = jsonMapper.rootMaptoJson();
			this.logger.debug("生成二维码json参数："+writeJson);
			out.writeBytes(writeJson);
			out.flush();
			out.close();

			//读取响应结果
			BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));
			String readLine;
			StringBuilder sb = new StringBuilder();
			while ((readLine = reader.readLine()) != null) {
				readLine = new String(readLine.getBytes("UTF-8"), "UTF-8");
				sb.append(readLine);
			}
			reader.close();
			logger.debug("二维码信息："+sb.toString());
			return ResultFactory.generateRequestResult(jsonMapper.fromJson(sb.toString(),Map.class));
		} catch (Exception e) {
			this.logger.error("生成用户二维码失败", e);
			result = ResultFactory.generateErrorResult(ErrorCodes.BIZ_MB_CREATE_QRCODE_FAIL_10027,i18nUtil.getMessage("BIZ_MB_CREATE_QRCODE_FAIL_10027"));
			return result;
		}finally {
			if(urlConnection != null){
				urlConnection.disconnect();
			}
		}
	}

	/**
	 * 支付完成后的通知路径
	 * @param tradeNo
	 * @return
	 */
	private static String WX_ORDER_SUCCESS="SUCCESS";
	private static String WX_ORDER_PAYERROR="PAYERROR";
	@RequestMapping("/wxpay/notify/{tradeNo}")
	public String goWeixinPayNotify(@PathVariable String tradeNo) {
		/*try {
			synchronized(tradeNo){
				OrderProductStatus order = this.orderFacade.findOneByTradeNo(tradeNo);
				if(null == order || order.getPaidTime()!=null){
					return WX_ORDER_SUCCESS;
				}
				WxPayOrderQueryResult payResult = AppBeanInjector.wxPayService.queryOrder(null,tradeNo);
				if(payResult.getReturnCode().equals(WX_ORDER_SUCCESS)){
					String tradeState = payResult.getTradeState();
					if(tradeState.equals(WX_ORDER_SUCCESS)){ // 支付成功
						if(null == order){
							this.logger.error("####################### 订单不存在，tradeNo = {}",tradeNo);
							return WX_ORDER_SUCCESS;
						}
						this.weiXinPayFacade.createPaidRecord(order.getId(),order.getMemberId(),order.getCompanyId());
						//发送订单创建成功时的消息
						AppBeanInjector.sendSystemMessageService.sendCreateOrderClerkMessage(order,WebUtils.getCurrUserId(),order.getCompanyId());
					}else if(tradeState.equals(WX_ORDER_PAYERROR)){ // 支付失败
						this.weiXinPayFacade.closeOrder(order.getId(),order.getMemberId(),order.getCompanyId());
						//发送支付失败时订单关闭消息
						AppBeanInjector.sendSystemMessageService.sendCloseOrderMessage(order,WebUtils.getCurrUserId(),order.getCompanyId());
					}
				}
				this.logger.error("####################### 查询微信订单失败：{}",payResult.getReturnMsg());
			}
		} catch (WxPayException e) {
			this.logger.error("####################### 查询微信订单时出现异常",e);
		}*/
		// 写支付记录
		return WX_ORDER_SUCCESS;
	}
}
