package com.lwxf.industry4.webapp.facade.admin.factory.system;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.system.Basecode;
import com.lwxf.mybatis.utils.MapContext;

public interface BaseCodeFacade {

    RequestResult add(Basecode basecode);

    RequestResult findById(String baseCodeId);

    RequestResult findByMap(MapContext mapContext);

    RequestResult update(String activityId, MapContext parmas);

    RequestResult delete(String id);

    RequestResult findListBasecodes(MapContext mapContext,Integer pageNum,Integer pageSize);

    RequestResult addProductBaseCode(Basecode basecode);

    RequestResult findListByMap(MapContext map);
}
