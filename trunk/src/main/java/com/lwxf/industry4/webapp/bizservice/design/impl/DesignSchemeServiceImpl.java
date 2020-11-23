package com.lwxf.industry4.webapp.bizservice.design.impl;


import java.util.List;
import java.util.Map;


import com.lwxf.industry4.webapp.domain.dto.design.DesignSchemeDto;
import com.thoughtworks.xstream.core.util.ThreadSafeSimpleDateFormat;
import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.design.DesignSchemeDao;
import com.lwxf.industry4.webapp.bizservice.design.DesignSchemeService;
import com.lwxf.industry4.webapp.domain.entity.design.DesignScheme;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-03 15:53:46
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("designSchemeService")
public class DesignSchemeServiceImpl extends BaseServiceImpl<DesignScheme, String, DesignSchemeDao> implements DesignSchemeService {


	@Resource

	@Override	public void setDao( DesignSchemeDao designSchemeDao) {
		this.dao = designSchemeDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<DesignScheme> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public PaginatedList<DesignSchemeDto> selectByCondition(PaginatedFilter paginatedFilter) {
		return this.dao.selectByCondition(paginatedFilter);
	}

	@Override
	public Integer findCountByDesigner(String designer) {
		return this.dao.findCountByDesigner(designer);
	}

	@Override
	public PaginatedList<DesignSchemeDto> findListByFilter(PaginatedFilter paginatedFilter) {
		return this.dao.findListByFilter(paginatedFilter);
	}

	@Override
	public DesignSchemeDto findOneById(String id) {
		return this.dao.findOneById(id);
	}

	@Override
	public List<Map<String, String>> findStyleAndCountByDesigner(String designer) {
		return this.dao.findStyleAndCountByDesigner(designer);
	}

	@Override
	public List<DesignSchemeDto> findByDesignerAndStatus(MapContext map) {
		return this.dao.findByDesignerAndStatus(map);
	}

	@Override
	public Map selectBySchemeId(String id) {
		return this.dao.selectBySchemeId(id);
	}

	@Override
	public PaginatedList<Map> findListBydesigner(PaginatedFilter filter) {
		return this.dao.findListBydesigner(filter);
	}

	@Override
	public PaginatedList<Map> findList(PaginatedFilter filter) {
		return this.dao.findList(filter);
	}
}