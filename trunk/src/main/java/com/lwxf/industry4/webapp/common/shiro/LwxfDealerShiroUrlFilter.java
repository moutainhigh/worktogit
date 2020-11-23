package com.lwxf.industry4.webapp.common.shiro;

import javax.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;

import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.shiro.matcher.IApiPathPermissionMatcher;
import com.lwxf.industry4.webapp.common.shiro.matcher.impl.app.DealerApiPathMatcher;

/**
 * 功能：
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018/12/7 0007 16:16
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class LwxfDealerShiroUrlFilter extends BaseShiroUrlFilter {
	private List<IApiPathPermissionMatcher> matchers;
	public LwxfDealerShiroUrlFilter(){
		this.matchers = new ArrayList<>();
		this.matchers.add(new DealerApiPathMatcher());
		super.registerMatcher(this.matchers);
		this.setLoginUrl(WebConstant.REDIRECT_PATH_DEALER_LOGIN);
	}
}
