package com.lwxf.industry4.webapp.domain.dto.company;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/5/27 0027 10:51
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value = "相关评论信息",description = "相关评论信息")
public class EmployeeDailyRecordCommentDto {
	@ApiModelProperty(name = "commentUserName",value = "评论人姓名")
	private String commentUserName;
	@ApiModelProperty(name = "commentUserAvatar",value = "评论人头像")
	private String commentUserAvatar;
	@ApiModelProperty(name = "commentMessage",value = "评论内容")
	private String commentMessage;
	@ApiModelProperty(name = "employeeDailyRecordCommentId",value = "评论Id")
    private String employeeDailyRecordCommentId;
	public String getCommentUserName() {
		return commentUserName;
	}

	public void setCommentUserName(String commentUserName) {
		this.commentUserName = commentUserName;
	}

	public String getCommentUserAvatar() {
		return commentUserAvatar;
	}

	public void setCommentUserAvatar(String commentUserAvatar) {
		this.commentUserAvatar = commentUserAvatar;
	}

	public String getCommentMessage() {
		return commentMessage;
	}

	public void setCommentMessage(String commentMessage) {
		this.commentMessage = commentMessage;
	}

	public String getEmployeeDailyRecordCommentId() {
		return employeeDailyRecordCommentId;
	}

	public void setEmployeeDailyRecordCommentId(String employeeDailyRecordCommentId) {
		this.employeeDailyRecordCommentId = employeeDailyRecordCommentId;
	}
}
