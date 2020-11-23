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
import com.lwxf.industry4.webapp.domain.dao.design.DoorStateDao;
import com.lwxf.industry4.webapp.bizservice.design.DoorStateService;
import com.lwxf.industry4.webapp.domain.entity.design.DoorState;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2019-03-15 10:41:06
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("doorStateService")
public class DoorStateServiceImpl extends BaseServiceImpl<DoorState, String, DoorStateDao> implements DoorStateService {


	@Resource

	@Override	public void setDao( DoorStateDao doorStateDao) {
		this.dao = doorStateDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<DoorState> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}


	@Override
	public DoorState findByName(MapContext mapContext) {
		return this.dao.findByName(mapContext);
	}
}