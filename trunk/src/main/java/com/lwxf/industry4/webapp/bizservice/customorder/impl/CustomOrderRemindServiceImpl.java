package com.lwxf.industry4.webapp.bizservice.customorder.impl;

import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.bizservice.customorder.CustomOrderRemindService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.customorder.CustomOrderRemindDao;
import com.lwxf.industry4.webapp.domain.dto.customorder.CustomOrderRemindDto;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderRemind;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 功能：
 * 
 * @author：DJL(yuuyoo@163.com)
 * @created：2020-01-06 10:14:29
 * @version：2020 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("customOrderRemindService")
public class CustomOrderRemindServiceImpl extends BaseServiceImpl<CustomOrderRemind, String, CustomOrderRemindDao> implements CustomOrderRemindService {


	@Resource
	@Override
	public void setDao( CustomOrderRemindDao customOrderRemindDao) {
		this.dao = customOrderRemindDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<CustomOrderRemind> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter);
	}

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public PaginatedList<CustomOrderRemindDto> selectDtoByFilter(PaginatedFilter paginatedFilter) {
        //
        return this.dao.selectDtoByFilter(paginatedFilter);
    }

	@Override
	public CustomOrderRemind selectByOrderId(String orderId) {
		return this.dao.selectByOrderId(orderId);
	}

	@Override
	public CustomOrderRemindDto selectDtoByOrderId(String orderId) {
		return this.dao.selectDtoByOrderId(orderId);
	}
}