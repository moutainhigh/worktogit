package com.lwxf.industry4.webapp.baseservice.zaoyi.facade.demo.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.lwxf.industry4.webapp.baseservice.zaoyi.domain.entity.demo.User;
import com.lwxf.industry4.webapp.baseservice.zaoyi.facade.base.impl.BaseFacadeImpl;
import com.lwxf.industry4.webapp.baseservice.zaoyi.facade.demo.UserFacade;
import com.lwxf.industry4.webapp.baseservice.zaoyi.service.demo.UserService;

/**
 * 功能：
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2019/3/1 0001 11:31
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("zyUserFacade")
public class UserFacadeImpl extends BaseFacadeImpl<User,UserService> implements UserFacade {
	@Override
	@Resource(name = "zyUserService")
	public void setService(UserService service) {
		this.service = service;
	}
}
