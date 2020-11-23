package com.lwxf.industry4.webapp.baseservice.zaoyi.domain.dao.demo.impl;

import org.springframework.stereotype.Repository;

import com.lwxf.industry4.webapp.baseservice.zaoyi.domain.dao.base.BaseNoIdDaoImpl;
import com.lwxf.industry4.webapp.baseservice.zaoyi.domain.dao.demo.UserDao;
import com.lwxf.industry4.webapp.baseservice.zaoyi.domain.entity.demo.User;

/**
 * 功能：
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2019/3/1 0001 10:56
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("zyUserDao")
public class UserDaoImpl extends BaseNoIdDaoImpl<User> implements UserDao {
}
