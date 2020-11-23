package com.lwxf.industry4.webapp.controller.path;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import org.apache.commons.collections.map.HashedMap;

import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.shiro.ShiroUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.entity.user.User;
import com.lwxf.industry4.webapp.facade.reservation.ReservationFacade;

/**
 * 功能：需要用户登录后才能访问的路径
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018-07-13 9:53
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Controller
public class UserLoginedController extends LoadBaseCfgAndSysCfgController {
	private Logger logger = LoggerFactory.getLogger(PortalsController.class);
	private static final String PAGE_QUICKSHARE = "quickshare";
	@Resource(name = "reservationFacade")
	private ReservationFacade reservationFacade;

	@Override
	protected Map<String, Object> loadUserPreload(HttpServletRequest request) {
		Map<String, Object> userPrelaod = new HashedMap();
		User currUser = ShiroUtil.getCurrUser();
		if(null != currUser){
			userPrelaod.put("user",currUser);
		}
		String reqPath = request.getServletPath();
		if(reqPath.indexOf("/reservation")>-1){
			userPrelaod.put("reservations",this.reservationFacade.findAllAmount());
		}
		return userPrelaod;
	}

	/**
	 * 跟路径
	 * @return
	 */
	@GetMapping("/")
	public String goBasePath() {
		noCahce();
		return LwxfStringUtils.format(WebConstant.REDIRECT_PATH_TEMPLATE,WebConstant.REDIRECT_PATH_FACTORY_ADMIN);
	}

	/**
	 * 快享
	 * @return
	 */
	@GetMapping("/quickshare")
	public String goQuickShareHome() {
		noCahce();
		return WebUtils.getPortalsPagePath(PAGE_QUICKSHARE);
	}

	/**
	 * 公众号支付
	 * @param model
	 * @return
	 */
	@GetMapping("/payment/mppay")
	public String goPaymentHome(ModelMap model) {
		noCahce();
		/*RequestResult requestResult = weiXinPayFacade.mpPay("43lby6vuwydc");
		model.addAttribute("payData",requestResult.getData());
		logger.error(this.json.toJson(requestResult.getData()));*/
		return "payment/payment";
	}
}
