package com.lwxf.industry4.webapp.common.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.servlet.AdviceFilter;
import org.apache.shiro.web.util.WebUtils;

import com.lwxf.industry4.webapp.common.constant.WebConstant;

/**
 * 功能：
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018-05-24 15:52:38
 * @version：2018 1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class NotFoundFilter  extends AdviceFilter {
	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
		WebUtils.issueRedirect(request, response, WebConstant.REDIRECT_PATH_404);
		return false;
	}
}
