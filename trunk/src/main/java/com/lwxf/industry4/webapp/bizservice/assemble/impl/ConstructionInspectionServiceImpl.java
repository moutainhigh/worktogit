package com.lwxf.industry4.webapp.bizservice.assemble.impl;


import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.dispatchList.ConstructionInspectionDto;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.assemble.ConstructionInspectionDao;
import com.lwxf.industry4.webapp.bizservice.assemble.ConstructionInspectionService;
import com.lwxf.industry4.webapp.domain.entity.assemble.ConstructionInspection;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2018-12-19 15:05:23
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("constructionInspectionService")
public class ConstructionInspectionServiceImpl extends BaseServiceImpl<ConstructionInspection, String, ConstructionInspectionDao> implements ConstructionInspectionService {


	@Resource

	@Override	public void setDao( ConstructionInspectionDao constructionInspectionDao) {
		this.dao = constructionInspectionDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<ConstructionInspection> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public ConstructionInspectionDto findConstructionByDispatchId(String dispatchListId) {
		return this.dao.findConstructionByDispatchId(dispatchListId);
	}

}