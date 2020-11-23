package com.lwxf.industry4.webapp.bizservice.company.impl;


import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.company.OutsourcingFactoryDao;
import com.lwxf.industry4.webapp.bizservice.company.OutsourcingFactoryService;
import com.lwxf.industry4.webapp.domain.entity.company.OutsourcingFactory;


/**
 * 功能：
 * 
 * @author：dongshibo(F_baisi@163.com)
 * @created：2019-06-01 17:02:04
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("outsourcingFactoryService")
public class OutsourcingFactoryServiceImpl extends BaseServiceImpl<OutsourcingFactory, String, OutsourcingFactoryDao> implements OutsourcingFactoryService {


	@Resource

	@Override	public void setDao( OutsourcingFactoryDao outsourcingFactoryDao) {
		this.dao = outsourcingFactoryDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<OutsourcingFactory> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

}