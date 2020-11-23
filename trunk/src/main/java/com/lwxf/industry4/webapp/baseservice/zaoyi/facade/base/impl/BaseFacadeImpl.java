package com.lwxf.industry4.webapp.baseservice.zaoyi.facade.base.impl;


import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.lwxf.industry4.webapp.baseservice.zaoyi.domain.entity.base.AbstractNoIdEntity;
import com.lwxf.industry4.webapp.baseservice.zaoyi.facade.base.BaseFacade;
import com.lwxf.industry4.webapp.baseservice.zaoyi.service.base.BaseNoIdService;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;

/**
 * 功能：
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2019/3/1 0001 11:25
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Transactional(value="zyTransactionManager",readOnly = true)
public abstract class BaseFacadeImpl<T extends AbstractNoIdEntity,S extends BaseNoIdService<T>> implements BaseFacade<T> {
	protected S service;
	public abstract void setService(S service);

	@Override
	public RequestResult findAll() {
		List<T> users =  this.service.findAll();
		return ResultFactory.generateRequestResult(users);
	}

	@Override
	public RequestResult findListByParams(Map<String,Object> params) {
		List<T> users =  this.service.findListByParams(params);
		return ResultFactory.generateRequestResult(users);
	}
}
