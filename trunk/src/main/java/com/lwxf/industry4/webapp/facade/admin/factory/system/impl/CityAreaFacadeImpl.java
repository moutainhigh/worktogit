package com.lwxf.industry4.webapp.facade.admin.factory.system.impl;

import com.lwxf.industry4.webapp.bizservice.common.CityAreaService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.domain.entity.common.CityArea;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.admin.factory.system.CityAreaFacade;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 功能：
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @create：2019/1/17 9:18
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("fcityAreaFacade")
public class CityAreaFacadeImpl extends BaseFacadeImpl implements CityAreaFacade {

    @Resource(name = "cityAreaService")
    private CityAreaService cityAreaService;

    @Override
    public RequestResult findAllCityArea() {
        List<CityArea> cityAreas = this.cityAreaService.findAllCityArea();
        return ResultFactory.generateRequestResult(cityAreas);
    }

    @Override
    public CityArea findCityAreaById(String id) {

        return this.cityAreaService.findById(id);
    }


    @Override
    public RequestResult findParentCityById(String id) {
        MapContext map = MapContext.newOne();
        CityArea cityarea = cityAreaService.findById(id);
        String str = id.substring(2,6);
        String str2 = id.substring(4,6);
        if(id.substring(2,6).equals("0000")){
            map.put("province",cityarea);
        }else{
            if(id.substring(4,6).equals("00")){
                map.put("province",cityAreaService.findById(cityarea.getParentId()));
                map.put("city",cityarea);
            }else{
                CityArea city = cityAreaService.findById(cityarea.getParentId());
                map.put("province",cityAreaService.findById(city.getParentId()));
                map.put("city",city);
                map.put("area",cityarea);
            }
        }
        return ResultFactory.generateRequestResult(map);
    }

    @Override
    public RequestResult selectCityAreaList(Integer pageNum,Integer pageSize,MapContext params) {

        PaginatedFilter filter = PaginatedFilter.newOne();
        filter.setFilters(params);
        Pagination pagination = Pagination.newOne();
        pagination.setPageNum(pageNum);
        pagination.setPageSize(pageSize);
        filter.setPagination(pagination);
        PaginatedList<CityArea> list = this.cityAreaService.selectByFilter(filter);
        return ResultFactory.generateRequestResult(list);
    }


}

