package com.lwxf.industry4.webapp.facade.admin.factory.system;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.system.Operations;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2019/1/5/005 15:12
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface OperationsFacade extends BaseFacade {
	RequestResult findOperationList(MapContext mapContext);

	RequestResult addOperation(Operations operations);

	RequestResult deleteById(String id);

	RequestResult updateOperations(String id, MapContext mapContext);
}
