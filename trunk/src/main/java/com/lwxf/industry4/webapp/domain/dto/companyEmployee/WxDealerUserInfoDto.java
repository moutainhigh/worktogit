package com.lwxf.industry4.webapp.domain.dto.companyEmployee;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

import com.lwxf.industry4.webapp.domain.entity.company.CompanyEmployee;
import com.lwxf.industry4.webapp.domain.entity.org.Dept;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/6/14/014 14:56
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value="微信经销商用户详情",description="微信经销商用户详情")
public class WxDealerUserInfoDto extends CompanyEmployee {
	@ApiModelProperty(value = "头像")
	private String avatar;
	@ApiModelProperty(value = "性别名称")
	private String sexValue;
	@ApiModelProperty(value = "性别")
	private Integer sex;
	@ApiModelProperty(value = "生日")
	private Date birthday;
	@ApiModelProperty(value = "手机号")
	private String mobile;
	@ApiModelProperty(value = "学历名称")
	private String educationValue;
	@ApiModelProperty(value = "学历")
	private Integer education;
	@ApiModelProperty(value = "身份证号")
	private String identityNumber;
	@ApiModelProperty(value = "部门集合")
	private List<Dept> depts;
	@ApiModelProperty(value = "岗位(角色)")
	private String roleName;
	@ApiModelProperty(value = "入职时间")
	private Date entryTime;
	@ApiModelProperty(value = "转义后状态")
	private String statusName;
	@ApiModelProperty(value = "用户名字")
	private String userName;
	@ApiModelProperty(value = "公司名称")
	private String companyName;

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getSexValue() {
		return sexValue;
	}

	public void setSexValue(String sexValue) {
		this.sexValue = sexValue;
	}

	public String getEducationValue() {
		return educationValue;
	}

	public void setEducationValue(String educationValue) {
		this.educationValue = educationValue;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getEducation() {
		return education;
	}

	public void setEducation(Integer education) {
		this.education = education;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getIdentityNumber() {
		return identityNumber;
	}

	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}

	public List<Dept> getDepts() {
		return depts;
	}

	public void setDepts(List<Dept> depts) {
		this.depts = depts;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Date getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}
