package com.lwxf.industry4.webapp.facade.admin.factory.activity;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.dto.activity.ActivityDto;
import com.lwxf.industry4.webapp.domain.entity.activity.ActivityParams;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.web.multipart.MultipartFile;

/**
 * 功能：活动管理f端Facade
 *
 * @author：张潇霖(3965488@qq.com)
 * @create：2019/3/20 11:51
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */

public interface ActivityFacade extends BaseFacade {

    RequestResult addActivity(ActivityDto activityDto);

    RequestResult findByActivityId(String activityId);

    RequestResult putActivityStatus(MapContext params);

    RequestResult putActivityJoinUpdate(MapContext params);

    RequestResult updateActivity(String activityId,MapContext parmas);

    RequestResult findJoinRecord(MapContext mapContext,Integer pageNum,Integer pageSize);

    RequestResult addCover(MultipartFile multipartFile,String activityId, String type);

    RequestResult updateCover(MultipartFile multipartFile,String activityId,String type);

    RequestResult updateActivityParams(String activityId, MapContext map, String paramsId);

    RequestResult addActivityParams(ActivityParams activityParams);

    RequestResult deleteActivityParams(String activityParamsId);

    RequestResult deleteActivityInfo(String id);

    RequestResult findListActivities(MapContext mapContext,Integer pageNum,Integer pageSize);
}
