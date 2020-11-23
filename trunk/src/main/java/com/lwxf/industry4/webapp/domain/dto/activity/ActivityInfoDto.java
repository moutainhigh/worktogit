package com.lwxf.industry4.webapp.domain.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.lwxf.industry4.webapp.domain.entity.activity.ActivityInfo;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/5/17/017 9:33
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value = "活动信息",description = "活动信息")
public class ActivityInfoDto extends ActivityInfo {
	@ApiModelProperty(value = "创建人名称")
	private String creatorName;

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
}
