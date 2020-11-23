package com.lwxf.industry4.webapp.domain.dto.company;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.lwxf.industry4.webapp.domain.entity.company.EmployeeDailyRecordComment;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/5/27/027 17:06
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value = "员工日志的评论信息",description = "员工日志的评论信息")
public class EmployeeDailyRecordCommentDtos extends EmployeeDailyRecordComment{
	@ApiModelProperty(value = "创建人名称")
	private String createUserName;

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
}
