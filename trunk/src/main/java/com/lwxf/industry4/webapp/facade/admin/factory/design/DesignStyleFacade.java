package com.lwxf.industry4.webapp.facade.admin.factory.design;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.design.DesignStyle;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2019/2/25/025 14:09
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface DesignStyleFacade extends BaseFacade {
	RequestResult findStyleList(MapContext mapContext);

	RequestResult addStyle(DesignStyle designStyle);

	RequestResult updateStyle(String id, MapContext mapContext);

	RequestResult deleteStyle(String id);
}
