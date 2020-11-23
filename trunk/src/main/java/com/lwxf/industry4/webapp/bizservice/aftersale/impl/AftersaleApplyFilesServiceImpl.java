package com.lwxf.industry4.webapp.bizservice.aftersale.impl;


import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.aftersale.AftersaleApplyFilesDao;
import com.lwxf.industry4.webapp.bizservice.aftersale.AftersaleApplyFilesService;
import com.lwxf.industry4.webapp.domain.entity.aftersale.AftersaleApplyFiles;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-01-02 20:27:26
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("aftersaleApplyFilesService")
public class AftersaleApplyFilesServiceImpl extends BaseServiceImpl<AftersaleApplyFiles, String, AftersaleApplyFilesDao> implements AftersaleApplyFilesService {


	@Resource

	@Override	public void setDao( AftersaleApplyFilesDao aftersaleApplyFilesDao) {
		this.dao = aftersaleApplyFilesDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<AftersaleApplyFiles> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public List<AftersaleApplyFiles> findFilesByAfterId(String aftersaleId) {
		return this.dao.findFilesByAfterId(aftersaleId);
	}

	@Override
	public List<String> findByPath(List<String> pathList) {
		return this.dao.findByPath(pathList);
	}

	@Override
	public int updateFilesList(String id, List imgIds) {
		return this.dao.updateFilesList(id,imgIds);
	}

	@Override
	public List<AftersaleApplyFiles> findListByOrderId(String orderId) {
		return this.dao.findListByOrderId(orderId);
	}

	@Override
	public int deleteByOrderId(String orderId) {
		return this.dao.deleteByOrderId(orderId);
	}

	@Override
	public List<AftersaleApplyFiles> findListByResultOrderId(String orderId) {
		return this.dao.findListByResultOrderId(orderId);
	}

	@Override
	public int deleteByResultOrderId(String orderId) {
		return this.dao.deleteByResultOrderId(orderId);
	}

	@Override
	public int updateByIds(MapContext mapContext) {
		return this.dao.updateByIds(mapContext);
	}

}