package com.lwxf.industry4.webapp.bizservice.system.impl;


import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.system.OperationsDao;
import com.lwxf.industry4.webapp.bizservice.system.OperationsService;
import com.lwxf.industry4.webapp.domain.entity.system.Operations;


/**
 * 功能：
 * 
 * @author：dongshibo(zjl869319827@outlook.com)
 * @created：2018-12-07 14:10:48
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("operationsService")
public class OperationsServiceImpl extends BaseServiceImpl<Operations, String, OperationsDao> implements OperationsService {


	@Resource

	@Override	public void setDao( OperationsDao operationsDao) {
		this.dao = operationsDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<Operations> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public List<Operations> findAll() {
		return this.dao.findAll();
	}

	@Override
	public List<Operations> findListByMapContext(MapContext mapContext) {
		return this.dao.findListByMapContext(mapContext);
	}

	@Override
	public Operations findOneByName(String name) {
		return this.dao.findOneByName(name);
	}

	@Override
	public Operations findOneByKey(String key) {
		return this.dao.findOneByKey(key);
	}

	@Override
	public List<Operations> findAllByTypes(List<Integer> types) {
		return this.dao.findAllByTypes(types);
	}
}