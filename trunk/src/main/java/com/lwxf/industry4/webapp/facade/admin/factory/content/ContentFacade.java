package com.lwxf.industry4.webapp.facade.admin.factory.content;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/5/15/015 16:42
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface ContentFacade extends BaseFacade {
	RequestResult findContentByTypeAndId(Integer type, String id);
}
