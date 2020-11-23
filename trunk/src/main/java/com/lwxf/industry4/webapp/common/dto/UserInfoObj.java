package com.lwxf.industry4.webapp.common.dto;

import com.lwxf.industry4.webapp.domain.entity.company.Company;
import com.lwxf.industry4.webapp.domain.entity.user.User;
import com.lwxf.industry4.webapp.domain.entity.user.UserBasis;
import com.lwxf.industry4.webapp.domain.entity.user.UserExtra;
import com.lwxf.industry4.webapp.domain.entity.user.UserThirdInfo;

/**
 * 功能：
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018-07-12 9:32
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class UserInfoObj {
	private User user;
	private UserExtra userExtra;
	private UserThirdInfo userThirdInfo;
    private UserBasis userBasis;
	private Company company;
	private String mergerName;


	public String getMergerName() {
		return mergerName;
	}

	public void setMergerName(String mergerName) {
		this.mergerName = mergerName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserExtra getUserExtra() {
		return userExtra;
	}

	public void setUserExtra(UserExtra userExtra) {
		this.userExtra = userExtra;
	}

	public UserThirdInfo getUserThirdInfo() {
		return userThirdInfo;
	}

	public void setUserThirdInfo(UserThirdInfo userThirdInfo) {
		this.userThirdInfo = userThirdInfo;
	}

	public UserBasis getUserBasis() {
		return userBasis;
	}

	public void setUserBasis(UserBasis userBasis) {
		this.userBasis = userBasis;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public static UserInfoObj newOne(User user, UserExtra userExtra, UserThirdInfo userThirdInfo,Company company){
		UserInfoObj obj = new UserInfoObj();
		obj.setUser(user);
		obj.setUserExtra(userExtra);
		obj.setUserThirdInfo(userThirdInfo);
		obj.setCompany(company);
		return obj;
	}
	public static UserInfoObj newInfo(User user, UserExtra userExtra, UserThirdInfo userThirdInfo,UserBasis userBasis,String mergerName){
		UserInfoObj obj = new UserInfoObj();
		obj.setUser(user);
		obj.setUserExtra(userExtra);
		obj.setUserThirdInfo(userThirdInfo);
		obj.setUserBasis(userBasis);
		obj.setMergerName(mergerName);
		return obj;
	}
}
