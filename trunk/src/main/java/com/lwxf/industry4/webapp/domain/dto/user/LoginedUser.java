package com.lwxf.industry4.webapp.domain.dto.user;

import com.lwxf.industry4.webapp.domain.entity.company.CompanyEmployee;
import com.lwxf.industry4.webapp.domain.entity.company.CompanyShareMember;
import com.lwxf.industry4.webapp.domain.entity.user.User;

/**
 * 功能：	jsonMapper.addFilterAllExclude(User.class, "loginName", "lastLogin", "timeZone", "metadata", "language");
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018-05-24 10:49:32
 * @version：2018 1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class LoginedUser extends User {
	private CompanyEmployee companyEmployee;
	private CompanyShareMember companyShareMember;
	public LoginedUser(User user) {
		super.setId(user.getId());
		super.setAvatar(user.getAvatar());
		super.setBackground(user.getBackground());
		super.setBirthday(user.getBirthday());
		super.setCreated(user.getCreated());
		super.setEmail(user.getEmail());
		super.setLanguage(user.getLanguage());
		super.setLastLogin(user.getLastLogin());
		super.setMobile(user.getMobile());
		super.setName(user.getName());
		super.setSex(user.getSex());
		super.setState(user.getState());
		super.setTimeZone(user.getTimeZone());
		super.setLoginName(user.getLoginName());
		super.setChangedLoginName(user.getChangedLoginName());
		super.setType(user.getType());
		super.setBranchId(user.getBranchId());
		super.setEnableLoginPc(user.getEnableLoginPc());
	}

	public CompanyEmployee getCompanyEmployee() {
		return companyEmployee;
	}

	public void setCompanyEmployee(CompanyEmployee companyEmployee) {
		this.companyEmployee = companyEmployee;
	}

	public CompanyShareMember getCompanyShareMember() {
		return companyShareMember;
	}

	public void setCompanyShareMember(CompanyShareMember companyShareMember) {
		this.companyShareMember = companyShareMember;
	}
}
