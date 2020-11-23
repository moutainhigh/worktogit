package com.lwxf.industry4.webapp.domain.dto.company;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/5/27 0027 10:42
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value = "员工日志",description = "员工日志信息")
public class EmployeeDailyRecordDto {
	@ApiModelProperty(name = "employeeDailyRecordId",value = "日志id")
	private String employeeDailyRecordId;
	@ApiModelProperty(name = "emplyeeAvatar",value = "员工头像")
	private  String emplyeeAvatar;
	@ApiModelProperty(name = "employeeDeptName",value = "员工部门名称")
	private List<String> employeeDeptNames;
	@ApiModelProperty(name = "emplyeeName",value = "员工名称")
	private String emplyeeName;
	@ApiModelProperty(name = "content",value = "日志内容")
	private String content;
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	@ApiModelProperty(name = "dateTime",value = "日期")
	private Date dateTime;
	@ApiModelProperty(name = "dailyRecordCommentDtos",value = "评论信息列表")
	private List<EmployeeDailyRecordCommentDto> dailyRecordCommentDtos;
	@ApiModelProperty(name = "isUpdate",value = "是否能编辑 0:能编辑 1:不能编辑")
	private Integer isUpdate;
	@ApiModelProperty(name = "employeeId",value = "员工用户id")
	private String employeeId;
	@ApiModelProperty(name = "visibleState",value = "可见范围 0：本部门，1：全部可见")
	private Integer visibleState;


	public String getEmployeeDailyRecordId() {
		return employeeDailyRecordId;
	}

	public void setEmployeeDailyRecordId(String employeeDailyRecordId) {
		this.employeeDailyRecordId = employeeDailyRecordId;
	}

	public String getEmplyeeAvatar() {
		return emplyeeAvatar;
	}

	public void setEmplyeeAvatar(String emplyeeAvatar) {
		this.emplyeeAvatar = emplyeeAvatar;
	}

	public List<String> getEmployeeDeptNames() {
		return employeeDeptNames;
	}

	public void setEmployeeDeptNames(List<String> employeeDeptNames) {
		this.employeeDeptNames = employeeDeptNames;
	}

	public String getEmplyeeName() {
		return emplyeeName;
	}

	public void setEmplyeeName(String emplyeeName) {
		this.emplyeeName = emplyeeName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public Integer getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Integer isUpdate) {
		this.isUpdate = isUpdate;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public List<EmployeeDailyRecordCommentDto> getDailyRecordCommentDtos() {
		return dailyRecordCommentDtos;
	}

	public void setDailyRecordCommentDtos(List<EmployeeDailyRecordCommentDto> dailyRecordCommentDtos) {
		this.dailyRecordCommentDtos = dailyRecordCommentDtos;
	}

	public Integer getVisibleState() {
		return visibleState;
	}

	public void setVisibleState(Integer visibleState) {
		this.visibleState = visibleState;
	}
}
