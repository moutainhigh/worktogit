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
import com.lwxf.industry4.webapp.domain.dao.design.DesignStyleDao;
import com.lwxf.industry4.webapp.bizservice.design.DesignStyleService;
import com.lwxf.industry4.webapp.domain.entity.design.DesignStyle;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-03 15:53:46
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("designStyleService")
public class DesignStyleServiceImpl extends BaseServiceImpl<DesignStyle, String, DesignStyleDao> implements DesignStyleService {


	@Resource

	@Override	public void setDao( DesignStyleDao designStyleDao) {
		this.dao = designStyleDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<DesignStyle> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public List<DesignStyle> findAllDesignStyle() {
		return this.dao.findAllDesignStyle();
	}

	@Override
	public List<DesignStyle> findListByFilter(MapContext mapContext) {
		return this.dao.findListByFilter(mapContext);
	}

	@Override
	public DesignStyle selectOneByName(String name,String branchId) {
		return this.dao.selectOneByName(name,branchId);
	}
}