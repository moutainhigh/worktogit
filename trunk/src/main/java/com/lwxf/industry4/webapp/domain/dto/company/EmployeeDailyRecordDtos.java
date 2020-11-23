package com.lwxf.industry4.webapp.domain.dto.company;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import com.lwxf.industry4.webapp.domain.entity.company.EmployeeDailyRecord;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/5/25/025 17:44
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value = "员工日志信息",description = "员工日志信息")
public class EmployeeDailyRecordDtos extends EmployeeDailyRecord {
	@ApiModelProperty(value = "创建人名称")
	private String createUserName;
	@ApiModelProperty(value = "修改人名称")
	private String updateUserName;
	@ApiModelProperty(value = "日志评论")
	private List<EmployeeDailyRecordCommentDtos> commentDtoList;

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

	public List<EmployeeDailyRecordCommentDtos> getCommentDtoList() {
		return commentDtoList;
	}

	public void setCommentDtoList(List<EmployeeDailyRecordCommentDtos> commentDtoList) {
		this.commentDtoList = commentDtoList;
	}
}
