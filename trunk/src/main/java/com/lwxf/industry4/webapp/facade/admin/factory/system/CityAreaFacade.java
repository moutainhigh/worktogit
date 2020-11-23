package com.lwxf.industry4.webapp.facade.admin.factory.system;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.common.CityArea;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @create：2019/1/17 9:18
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */

public interface CityAreaFacade extends BaseFacade {

    RequestResult findAllCityArea();

    CityArea findCityAreaById(String id);

    RequestResult findParentCityById(String id);

    RequestResult selectCityAreaList(Integer pageNum,Integer pageSize,MapContext params);

}
