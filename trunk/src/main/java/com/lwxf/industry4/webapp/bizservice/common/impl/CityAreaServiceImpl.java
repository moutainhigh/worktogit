package com.lwxf.industry4.webapp.bizservice.common.impl;


import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.bizservice.common.CityAreaService;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.common.CityAreaDao;
import com.lwxf.industry4.webapp.domain.entity.common.CityArea;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 功能：
 * 
 * @author：wangmingyuan(wangmingyuan@126.com)
 * @created：2018-06-20 20:23:04
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("cityAreaService")
public class CityAreaServiceImpl extends BaseServiceImpl<CityArea, String, CityAreaDao> implements CityAreaService {


	@Resource

	@Override	public void setDao( CityAreaDao cityAreaDao) {
		this.dao = cityAreaDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<CityArea> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}
	@Override
	public List<CityArea> selectCityAreaListByLevel(int levelType) {

		return this.dao.selectCityAreaListByLevel(levelType);
	}

	@Override
	public List<CityArea> selectCityAreaListByParentId(String parentId) {
		return this.dao.selectCityAreaListByParentId(parentId);
	}


	@Override
	public CityArea selectByMergerName(String mergerName) {
		return this.dao.selectByMergerName(mergerName);
	}

	@Override
	public CityArea selectByNameAndLevelType(MapContext mapContext) {
		return this.dao.selectByNameAndLevelType(mapContext);
	}

	@Override
	public CityArea selectByNameAndParentId(MapContext mapContext) {
		return this.dao.selectByNameAndParentId(mapContext);
	}

	@Override
	public CityArea selectDistrictByName(String provinceName, String cityName, String districtName) {
		//查询省的地址id
		MapContext provinceMap = MapContext.newOne();
		provinceMap.put(WebConstant.KEY_ENTITY_NAME,provinceName);
		provinceMap.put(WebConstant.KEY_ENTITY_LEVELTYPE,1);
		CityArea provinceArea =  this.dao.selectByNameAndLevelType(provinceMap);
		if (null==provinceArea){
			return null;
		}
		//查询市的地址id
		MapContext cityMap = MapContext.newOne();
		cityMap.put(WebConstant.KEY_ENTITY_NAME,cityName);
		cityMap.put(WebConstant.KEY_ENTITY_PARENTID,provinceArea.getId());
		CityArea cityArea = this.dao.selectByNameAndParentId(cityMap);
		if (null==cityArea){
			return null;
		}
		//将市地址id查询出公司的id列表
		MapContext districtMap = MapContext.newOne();
		districtMap.put(WebConstant.KEY_ENTITY_NAME,districtName);
		districtMap.put(WebConstant.KEY_ENTITY_PARENTID,cityArea.getId());
		CityArea districtArea = this.dao.selectByNameAndParentId(districtMap);
		if (null==districtArea){
			return null;
		}
		return districtArea;
	}


	@Override
	public List<CityArea> findAllCityArea() {
		return this.dao.findAllCityArea();
	}



	@Override
	public List<CityArea> findALl() {
		return this.dao.findALl();
	}
}