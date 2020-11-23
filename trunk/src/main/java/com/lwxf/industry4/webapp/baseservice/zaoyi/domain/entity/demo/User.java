package com.lwxf.industry4.webapp.baseservice.zaoyi.domain.entity.demo;

import java.sql.Types;

import com.lwxf.industry4.webapp.baseservice.zaoyi.domain.entity.base.AbstractNoIdEntity;
import com.lwxf.mybatis.annotation.Column;
import com.lwxf.mybatis.annotation.Table;

/**
 * 功能：
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2019/3/1 0001 10:43
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Table(name = "user",displayName = "user")
public class User extends AbstractNoIdEntity {
	@Column(type = Types.INTEGER,nullable = false,name = "user_id",displayName = "用户id，主键")
	Integer userId;
	@Column(type = Types.CHAR,length = 255,nullable = false,name = "user_name",displayName = "用户名称")
	String userName;
	@Column(type = Types.CHAR,length = 255,nullable = false,name = "email",displayName = "邮箱")
	String email;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
