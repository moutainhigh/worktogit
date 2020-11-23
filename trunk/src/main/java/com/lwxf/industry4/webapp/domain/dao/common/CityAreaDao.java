package com.lwxf.industry4.webapp.domain.dao.common;


import java.util.List;

import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.entity.common.CityArea;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.common.CityArea;
import com.lwxf.mybatis.utils.MapContext;


/**
 * 功能：
 * 
 * @author：wangmingyuan(wangmingyuan@126.com)
 * @created：2018-06-20 20:23:04
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface CityAreaDao extends BaseDao<CityArea, String> {

	/**
	 * 分页查询
	 * @param paginatedFilter
	 * @return
	 */
	PaginatedList<CityArea> selectByFilter(PaginatedFilter paginatedFilter);
	/**
	 * 根据行政级别查询行政区域
	 * @param levelType
	 * @return
	 */
	List<CityArea> selectCityAreaListByLevel(int levelType);

	List<CityArea> findAllCityArea();

	/**
	 * 根据父ID查询行政区域
	 * @param parentId
	 * @return
	 */
	List<CityArea> selectCityAreaListByParentId(String parentId );

	CityArea selectByMergerName(String mergerName);

	CityArea selectByNameAndLevelType (MapContext mapContext);

	CityArea selectByNameAndParentId (MapContext mapContext);

	List<CityArea> findALl();
}