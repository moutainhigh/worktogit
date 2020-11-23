package com.lwxf.industry4.webapp.bizservice.system.impl;



import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.bizservice.system.BasecodeService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.system.BasecodeDao;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.domain.entity.system.Basecode;
import java.util.List;

/**
 * 功能：
 * 
 * @author：zhangxiaolin(3965488@qq.com)
 * @created：2019-05-04 10:55:31
 * @version：2019 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("basecodeService")
public class BasecodeServiceImpl extends BaseServiceImpl<Basecode, String, BasecodeDao> implements BasecodeService {


	@Resource
	@Override	public void setDao( BasecodeDao basecodeDao) {
		this.dao = basecodeDao;
	}

	@Override
	public PaginatedList<Basecode> selectByFilter(PaginatedFilter paginatedFilter) {
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public List<Basecode> selectByFilter(MapContext mapContext) {
		return this.dao.selectByFilter(mapContext);
	}

	@Override
	public List<Basecode> findAll() {
		return this.dao.findAll();
	}

	@Override
	public Basecode findByTypeAndValue(String type, String value) {
		return this.dao.findByTypeAndValue(type,value);
	}

	@Override
	public List<Basecode> findByType(String type) {
		return this.dao.findByType(type);
	}

	@Override
	public Basecode findByTypeAndCode(String typeString, String code) {
		return this.dao.findByTypeAndCode(typeString,code);
	}

	@Override
	public List<Basecode> findByTypeAndDelFlag(String type, Integer delFlag) {
		return this.dao.findByTypeAndDelFlag(type,delFlag);
	}

	@Override
	public Basecode findByTypeAndValueAndDelFlag(MapContext mapContext) {
		return this.dao.findByTypeAndValueAndDelFlag(mapContext);
	}
}