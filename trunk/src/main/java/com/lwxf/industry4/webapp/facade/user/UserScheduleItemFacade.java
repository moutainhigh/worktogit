package com.lwxf.industry4.webapp.facade.user;

import javax.servlet.http.HttpServletRequest;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/1/10 0010 11:37
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface UserScheduleItemFacade extends BaseFacade {


	RequestResult findUserScheduleItems(String userId, String time, HttpServletRequest request);

	RequestResult addUserScheduleItem(String userId, String scheduleTime, MapContext mapContext);


   RequestResult updateUserScheduleItem(String userScheduleItemId,MapContext mapContext);

	RequestResult deleteUserScheduleItem(String userScheduleItemId);
}
