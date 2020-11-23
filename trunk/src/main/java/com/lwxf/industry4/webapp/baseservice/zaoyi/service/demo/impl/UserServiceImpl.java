package com.lwxf.industry4.webapp.baseservice.zaoyi.service.demo.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lwxf.industry4.webapp.baseservice.zaoyi.domain.dao.demo.UserDao;
import com.lwxf.industry4.webapp.baseservice.zaoyi.domain.entity.demo.User;
import com.lwxf.industry4.webapp.baseservice.zaoyi.service.base.BaseNoIdServiceImpl;
import com.lwxf.industry4.webapp.baseservice.zaoyi.service.demo.UserService;

/**
 * 功能：
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2019/3/1 0001 11:07
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("zyUserService")
public class UserServiceImpl extends BaseNoIdServiceImpl<User,UserDao> implements UserService {
	@Override
	@Resource(name = "zyUserDao")
	public void setDao(UserDao dao) {
		this.dao = dao;
	}
}
