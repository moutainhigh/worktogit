package com.lwxf.industry4.webapp.bizservice.design;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.domain.entity.design.DoorState;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2019-03-15 10:41:06
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface DoorStateService extends BaseService <DoorState, String> {

	PaginatedList<DoorState> selectByFilter(PaginatedFilter paginatedFilter);


	DoorState findByName(MapContext mapContext);
}