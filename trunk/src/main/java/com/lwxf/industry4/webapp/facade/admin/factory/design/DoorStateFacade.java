package com.lwxf.industry4.webapp.facade.admin.factory.design;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.design.DoorState;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/3/29/029 13:16
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface DoorStateFacade extends BaseFacade {
	RequestResult findDoorList(String name, Integer pageNum, Integer pageSize);

	RequestResult addDoorState(DoorState doorState);

	RequestResult updateDoorState(String id, MapContext mapContext);

	RequestResult deleteDoorState(String id);
}
