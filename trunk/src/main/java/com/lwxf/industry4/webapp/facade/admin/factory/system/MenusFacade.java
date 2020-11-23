package com.lwxf.industry4.webapp.facade.admin.factory.system;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.system.Menus;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2019/1/3/003 16:11
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface MenusFacade extends BaseFacade {
	RequestResult findList(MapContext mapContext);

	RequestResult addMenus(Menus menus);

	RequestResult updateMenus(MapContext mapContext, String id);

	RequestResult deleteMenus(String id);
	public RequestResult findListPession(MapContext mapContext);
}
