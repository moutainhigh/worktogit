package com.lwxf.industry4.webapp.domain.dto.companyEmployee;

import com.lwxf.mybatis.annotation.Column;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.lwxf.industry4.webapp.domain.entity.company.CompanyEmployee;

import java.sql.Types;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2018/12/7 0007 11:37
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value = "公司员工Dto",discriminator = "公司员工Dto")
public class CompanyEmployeeDto extends CompanyEmployee {
	@ApiModelProperty(value = "用户名称")
	protected String userName;
	@ApiModelProperty(value = "性别:0-男，1-女")
	protected Integer sex;
	@ApiModelProperty(value = "手机号")
	protected String mobile;
	@ApiModelProperty(value = "角色名称")
	protected String roleName;
	@ApiModelProperty(value = "邮箱")
	protected String email;
	@ApiModelProperty(value = "生日")
	protected String birthday;
	@ApiModelProperty(value = "政治面貌  0 Communist_Party_members(中共党员),1 Communist Youth League member(共青团员),2 Masses(群众)")
	private Integer politicalOutlook;
	@ApiModelProperty(value = "家庭地址")
	private String address;
	@ApiModelProperty(value = "工作单位")
	private String workUnit;
	@ApiModelProperty(value = "职位")
	private String work;
	@ApiModelProperty(value = "收入 0 one(2000~4000),1 two(4000~6000),2 three(6000~8000),3 four(8000~10000)")
	private Integer income;
	@ApiModelProperty(value = "学历 0-master(硕士),1-Undergraduate(本科),2-Junior_College(大专),3-(high_school)高中,4-school(中专),5-Junior_middle_school(初中)")
	private Integer education;
	@ApiModelProperty(value = "民族")
	private String nation;
	private String key;
	@ApiModelProperty(value = "登录名")
	private String loginName;
	@ApiModelProperty(value = "公司名称")
	private String companyName;
	@ApiModelProperty(value = "企业Id")
	private String branchId;
	@ApiModelProperty(value = "用户状态")
	protected Integer userStatus;
	@ApiModelProperty(value = "状态：0-禁用，1-启用")
	protected Integer enableLoginPc;
	@ApiModelProperty(value = "状态：0-禁用，1-启用")
	protected Integer enableLoginWx;



	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Integer getPoliticalOutlook() {
		return politicalOutlook;
	}

	public void setPoliticalOutlook(Integer politicalOutlook) {
		this.politicalOutlook = politicalOutlook;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public Integer getIncome() {
		return income;
	}

	public void setIncome(Integer income) {
		this.income = income;
	}

	public Integer getEducation() {
		return education;
	}

	public void setEducation(Integer education) {
		this.education = education;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

	public Integer getEnableLoginPc() {
		return enableLoginPc;
	}

	public void setEnableLoginPc(Integer enableLoginPc) {
		this.enableLoginPc = enableLoginPc;
	}

	public Integer getEnableLoginWx() {
		return enableLoginWx;
	}

	public void setEnableLoginWx(Integer enableLoginWx) {
		this.enableLoginWx = enableLoginWx;
	}
}
