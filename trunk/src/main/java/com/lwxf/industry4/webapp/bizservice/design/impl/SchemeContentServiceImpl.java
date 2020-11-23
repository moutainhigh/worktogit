package com.lwxf.industry4.webapp.bizservice.design.impl;


import java.util.List;
import java.util.Map;


import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.dto.design.SchemeContentDto;
import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.design.SchemeContentDao;
import com.lwxf.industry4.webapp.bizservice.design.SchemeContentService;
import com.lwxf.industry4.webapp.domain.entity.design.SchemeContent;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2019-03-15 10:08:11
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("schemeContentService")
public class SchemeContentServiceImpl extends BaseServiceImpl<SchemeContent, String, SchemeContentDao> implements SchemeContentService {


	@Resource

	@Override	public void setDao( SchemeContentDao schemeContentDao) {
		this.dao = schemeContentDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<SchemeContent> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}


	@Override
	public List<SchemeContentDto> findBySchemeId(String id) {
		return this.dao.findBySchemeId(id);
	}
}