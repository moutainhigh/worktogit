package com.lwxf.industry4.webapp.controller.path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.user.UserType;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.user.LoginedUser;

/**
 * 功能：web微信登录相关
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018-05-24 9:18:00
 * @version：2018 1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Controller
public class LoginController extends LoadBaseCfgController {
	private Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "/flogin", method = RequestMethod.GET)
	public String goLogin(ModelMap model) {
		LoginedUser currUser = WebUtils.getCurrUser();
		if (currUser != null) {
			model.clear();
			if(currUser.getType().intValue() == UserType.FACTORY.getValue()||currUser.getType().intValue()==UserType.ADMIN.getValue()){
				return LwxfStringUtils.format(WebConstant.REDIRECT_PATH_TEMPLATE,WebConstant.REDIRECT_PATH_FACTORY_ADMIN);
			}else{
				return LwxfStringUtils.format(WebConstant.REDIRECT_PATH_TEMPLATE,WebConstant.REDIRECT_PATH_404);
			}
		}
		return WebUtils.getPortalsPagePath(WebConstant.PAGE_FACTORY_LOGIN);
	}
}
