package com.lwxf.industry4.webapp.bizservice.system.impl;


import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.system.SysExceptionDao;
import com.lwxf.industry4.webapp.bizservice.system.SysExceptionService;
import com.lwxf.industry4.webapp.domain.entity.system.SysException;


/**
 * 功能：
 * 
 * @author：zhangxiaolin(3965488@qq.com)
 * @created：2019-11-16 14:43:13
 * @version：2019 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("sysExceptionService")
public class SysExceptionServiceImpl extends BaseServiceImpl<SysException, String, SysExceptionDao> implements SysExceptionService {


	@Resource

	@Override	public void setDao( SysExceptionDao sysExceptionDao) {
		this.dao = sysExceptionDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<SysException> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

}