package com.lwxf.industry4.webapp.controller.path;

import javax.servlet.http.HttpServletRequest;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.apache.commons.collections.map.HashedMap;

import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.shiro.ShiroUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.entity.user.User;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.common.shiro.ShiroUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;

/**
 * 功能：前端页面跳转控制（无需用户登陆的页面）
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018-05-24 14:34:30
 * @version：2018 1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Controller
public class PortalsController extends LoadBaseCfgAndSysCfgController {
	private String pagePathTerms = "terms";
	private String pagePathPrivacy = "privacy";
	@Override
	protected Map<String, Object> loadUserPreload(HttpServletRequest request) {
		Map<String, Object> userPrelaod = new HashedMap();
		User currUser = ShiroUtil.getCurrUser();
		if(null != currUser){
			userPrelaod.put("user",currUser);
		}
		userPrelaod.put(WebUtils.REQUEST_HEADER_X_C_ID,AppBeanInjector.configuration.getCompanyId());
		return userPrelaod;
	}

	@GetMapping("/404")
	public String goTo404() {
		return WebUtils.getError404PagePath();
	}

	@RequestMapping("/headlines")
	public String goHeadlines() {
		noCahce();
		return WebUtils.getResponsePagePath("headlines");
	}

	@RequestMapping("/terms")
	public String goTerms() {
		noCahce();
		return pagePathTerms;
	}

	@RequestMapping("/privacy")
	public String goPrivacy() {
		noCahce();
		return pagePathPrivacy;
	}
}
