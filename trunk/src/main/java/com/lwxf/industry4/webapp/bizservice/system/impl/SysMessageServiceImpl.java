package com.lwxf.industry4.webapp.bizservice.system.impl;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.system.SysMessageDao;
import com.lwxf.industry4.webapp.bizservice.system.SysMessageService;
import com.lwxf.industry4.webapp.domain.entity.system.SysMessage;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@126.com)
 * @created：2019-12-09 16:51:47
 * @version：2019 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("sysMessageService")
public class SysMessageServiceImpl extends BaseServiceImpl<SysMessage, String, SysMessageDao> implements SysMessageService {
	@Resource
	@Override	public void setDao( SysMessageDao sysMessageDao) {
		this.dao = sysMessageDao;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<SysMessage> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}
}