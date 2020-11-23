package com.lwxf.industry4.webapp.bizservice.design.impl;


import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.design.DesignSchemeFilesDao;
import com.lwxf.industry4.webapp.bizservice.design.DesignSchemeFilesService;
import com.lwxf.industry4.webapp.domain.entity.design.DesignSchemeFiles;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-03 15:53:46
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("designSchemeFilesService")
public class DesignSchemeFilesServiceImpl extends BaseServiceImpl<DesignSchemeFiles, String, DesignSchemeFilesDao> implements DesignSchemeFilesService {


	@Resource

	@Override	public void setDao( DesignSchemeFilesDao designSchemeFilesDao) {
		this.dao = designSchemeFilesDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<DesignSchemeFiles> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public List<String> findByPath(List<String> uploadFilesList) {
		return this.dao.findByPath(uploadFilesList);
	}

	@Override
	public int updateFilesList(String id, List imgIds) {
		this.dao.updateAllTempByDesignId(id);
		return this.dao.updateFilesList(id,imgIds);
	}

	@Override
	public int deleteBySchemeId(String schemeId) {
		return this.dao.deleteBySchemeId(schemeId);
	}

	@Override
	public List<DesignSchemeFiles> findBySchemeId(String schemeId) {
		return this.dao.findBySchemeId(schemeId);
	}

	@Override
	public List<String> findByResouceId(String resourceId) {
		return this.dao.findByResouceId(resourceId);
	}

	@Override
	public List<DesignSchemeFiles> findListByResourceIdAndType(String id, Integer type) {
		return this.dao.findListByResourceIdAndType(id,type);
	}
}