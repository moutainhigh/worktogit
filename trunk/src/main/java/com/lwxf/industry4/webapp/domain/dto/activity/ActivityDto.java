package com.lwxf.industry4.webapp.domain.dto.activity;

import com.lwxf.industry4.webapp.domain.entity.activity.ActivityInfo;
import com.lwxf.industry4.webapp.domain.entity.activity.ActivityParams;
import java.util.List;


public class ActivityDto extends ActivityInfo {

    private List<ActivityParams> paramlist ;
    private String coverId;
    private String[] activityImgsId;

    public String getCoverId() {
        return coverId;
    }

    public void setCoverId(String coverId) {
        this.coverId = coverId;
    }

    public String[] getActivityImgsId() {
        return activityImgsId;
    }

    public void setActivityImgsId(String[] activityImgsId) {
        this.activityImgsId = activityImgsId;
    }

    //查询项目列表
    public List<ActivityParams> getParamlist() {return this.paramlist;}

    public void setParamlist(List<ActivityParams> paramlist) {
        this.paramlist = paramlist;
    }
}
