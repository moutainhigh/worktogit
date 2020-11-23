package com.lwxf.industry4.webapp.bizservice.version.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lwxf.industry4.webapp.bizservice.base.BaseNoIdServiceImpl;
import com.lwxf.industry4.webapp.bizservice.version.UpdateVersionService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.version.UpdateVersionDao;
import com.lwxf.industry4.webapp.domain.dto.version.VersionDto;
import com.lwxf.industry4.webapp.domain.entity.version.UpdateVersion;
import com.lwxf.mybatis.utils.MapContext;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-05-06 10:02:57
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("updateVersionService")
public class UpdateVersionServiceImpl extends BaseNoIdServiceImpl<UpdateVersion,UpdateVersionDao> implements UpdateVersionService {


	@Resource

	@Override	public void setDao( UpdateVersionDao updateVersionDao) {
		this.dao = updateVersionDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<UpdateVersion> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public UpdateVersion findVersionNo(MapContext params) {
		return this.dao.findVersionNo(params);
	}

	@Override
	public UpdateVersion findOneByTypeAndPlatform(Integer sysType, Integer platform) {
		return this.dao.findOneByTypeAndPlatform(sysType,platform);
	}

	@Override
	public int updateByUpdateVersion(UpdateVersion updateVersion) {
		return this.dao.updateByUpdateVersion(updateVersion);
	}

	@Override
	public UpdateVersion findOneByVersionId(String id) {
		return this.dao.findOneByVersionId(id);
	}

	@Override
	public int deleteByVersionId(String id) {
		return this.dao.deleteByVersionId(id);
	}

}