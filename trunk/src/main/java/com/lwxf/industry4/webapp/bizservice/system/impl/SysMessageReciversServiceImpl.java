package com.lwxf.industry4.webapp.bizservice.system.impl;

import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.system.SysMessageReciversDto;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.system.SysMessageReciversDao;
import com.lwxf.industry4.webapp.bizservice.system.SysMessageReciversService;
import com.lwxf.industry4.webapp.domain.entity.system.SysMessageRecivers;

import java.util.List;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@126.com)
 * @created：2019-12-09 16:51:47
 * @version：2019 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("sysMessageReciversService")
public class SysMessageReciversServiceImpl extends BaseServiceImpl<SysMessageRecivers, String, SysMessageReciversDao> implements SysMessageReciversService {


	@Resource

	@Override	public void setDao( SysMessageReciversDao sysMessageReciversDao) {
		this.dao = sysMessageReciversDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<SysMessageRecivers> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public List<SysMessageReciversDto> selectMessageByUserId(String userId) {
		return this.dao.selectMessageByUserId(userId) ;
	}

	@Override
	public SysMessageRecivers selectByUserIdAndMessageId(String messageId, String userId) {
		return this.dao.selectByUserIdAndMessageId(messageId,userId) ;
	}
}