package com.lwxf.industry4.webapp.bizservice.contentmng.impl;


import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.contentmng.ContentsFilesDao;
import com.lwxf.industry4.webapp.bizservice.contentmng.ContentsFilesService;
import com.lwxf.industry4.webapp.domain.entity.contentmng.ContentsFiles;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-03-27 11:58:34
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("contentsFilesService")
public class ContentsFilesServiceImpl extends BaseServiceImpl<ContentsFiles, String, ContentsFilesDao> implements ContentsFilesService {


	@Resource

	@Override	public void setDao( ContentsFilesDao contentsFilesDao) {
		this.dao = contentsFilesDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<ContentsFiles> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public ContentsFiles findByContentId(String contentId) {
		return this.dao.findByContentId(contentId) ;
	}

	@Override
	public List<ContentsFiles> findContentsFiles(String contentsId) {
		return this.dao.findContentsFiles(contentsId);
	}

}