package com.lwxf.industry4.webapp.bizservice.engineering.impl;


import java.util.List;
import java.util.Map;


import com.lwxf.industry4.webapp.domain.dto.engineeringOrder.EngineeringDto;
import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.engineering.EngineeringDao;
import com.lwxf.industry4.webapp.bizservice.engineering.EngineeringService;
import com.lwxf.industry4.webapp.domain.entity.engineering.Engineering;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2020-05-27 18:14:09
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("engineeringService")
public class EngineeringServiceImpl extends BaseServiceImpl<Engineering, String, EngineeringDao> implements EngineeringService {


	@Resource

	@Override	public void setDao( EngineeringDao engineeringDao) {
		this.dao = engineeringDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<Engineering> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public PaginatedList<EngineeringDto> findEngineeringOrderList(PaginatedFilter paginatedFilter) {
		return this.dao.findEngineeringOrderList(paginatedFilter);
	}

	@Override
	public EngineeringDto findOneById(String id) {
		return this.dao.findOneById(id);
	}

}