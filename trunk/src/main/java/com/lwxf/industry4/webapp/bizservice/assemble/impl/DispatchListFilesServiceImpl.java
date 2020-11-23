package com.lwxf.industry4.webapp.bizservice.assemble.impl;


import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.assemble.DispatchListFilesDao;
import com.lwxf.industry4.webapp.bizservice.assemble.DispatchListFilesService;
import com.lwxf.industry4.webapp.domain.entity.assemble.DispatchListFiles;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2018-12-19 15:05:23
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("dispatchListFilesService")
public class DispatchListFilesServiceImpl extends BaseServiceImpl<DispatchListFiles, String, DispatchListFilesDao> implements DispatchListFilesService {


	@Resource

	@Override	public void setDao( DispatchListFilesDao dispatchListFilesDao) {
		this.dao = dispatchListFilesDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<DispatchListFiles> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public List<DispatchListFiles> findFilesMessage(String dispatchListId) {
		return this.dao.findFilesMessage(dispatchListId);
	}


}