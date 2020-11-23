package com.lwxf.industry4.webapp.facade.common.impl;

import javax.annotation.Resource;

import java.util.List;

import org.springframework.stereotype.Component;

import com.lwxf.industry4.webapp.bizservice.common.CityAreaService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.facade.common.CityAreaFacade;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.bizservice.common.CityAreaService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.domain.entity.common.CityArea;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.common.CityAreaFacade;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2018/6/21 11:05
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("cityAreaFacade")
public class CityAreaFacadeImpl extends BaseFacadeImpl implements CityAreaFacade {
	@Resource
	private CityAreaService cityAreaService;
	@Override
	public CityArea findCityAreaById(String id) {

		return this.cityAreaService.findById(id);
	}

	@Override
	public CityArea AddCityArea(CityArea cityArea) {
		return this.AddCityArea(cityArea);
	}

	@Override
	public int updateCityArea(CityArea cityArea) {
		MapContext context=new MapContext();
		return this.cityAreaService.updateByMapContext(context);
	}

	@Override
	public RequestResult selectCityAreaList(Integer pageNum, Integer pageSize, MapContext params) {

		PaginatedFilter filter = PaginatedFilter.newOne();
		filter.setFilters(params);
		Pagination pagination = Pagination.newOne();
		pagination.setPageNum(pageNum);
		pagination.setPageSize(pageSize);
		filter.setPagination(pagination);
		PaginatedList<CityArea> list = this.cityAreaService.selectByFilter(filter);
		return ResultFactory.generateRequestResult(list);
	}

	@Override
	public RequestResult selectCityAreaListByLevel(int levelType) {

		List<CityArea> list = this.cityAreaService.selectCityAreaListByLevel(levelType);
		return  ResultFactory.generateRequestResult(list);
	}

	@Override
	public RequestResult selectCityAreaListByParentId(String parentId) {
		List<CityArea> list = this.cityAreaService.selectCityAreaListByParentId(parentId);
		return  ResultFactory.generateRequestResult(list);
	}


	@Override
	public RequestResult findAllCityArea() {
		List<CityArea> allCityArea = this.cityAreaService.findAllCityArea();
		return ResultFactory.generateRequestResult(allCityArea);
	}



	@Override
	public RequestResult findALl() {
		return  ResultFactory.generateRequestResult(this.cityAreaService.findALl());
	}

}
