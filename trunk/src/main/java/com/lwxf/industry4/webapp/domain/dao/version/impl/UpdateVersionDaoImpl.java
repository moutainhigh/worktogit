package com.lwxf.industry4.webapp.domain.dao.version.impl;


import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.base.BaseNoIdDaoImpl;
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
@Repository("updateVersionDao")
public class UpdateVersionDaoImpl extends BaseNoIdDaoImpl<UpdateVersion> implements UpdateVersionDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<UpdateVersion> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination());
		PageList<UpdateVersion> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public UpdateVersion findVersionNo(MapContext params) {
		String sqlId=this.getNamedSqlId("findVersionNo");
		return this.getSqlSession().selectOne(sqlId,params);
	}

	@Override
	public UpdateVersion findOneByTypeAndPlatform(Integer sysType, Integer platform) {
		String sqlId = this.getNamedSqlId("findOneByTypeAndPlatform");
		MapContext mapContext = new MapContext();
		mapContext.put("sysType",sysType);
		mapContext.put("platform",platform);
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public int updateByUpdateVersion(UpdateVersion updateVersion) {
		String sqlId = this.getNamedSqlId("updateByMapContext");
		return this.getSqlSession().update(sqlId,updateVersion);
	}

	@Override
	public UpdateVersion findOneByVersionId(String id) {
		String sqlId = this.getNamedSqlId("findOneByVersionId");
		return this.getSqlSession().selectOne(sqlId,id);
	}

	@Override
	public int deleteByVersionId(String id) {
		String sqlId = this.getNamedSqlId("deleteByVersionId");
		return this.getSqlSession().delete(sqlId,id);
	}

}