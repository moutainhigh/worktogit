package com.lwxf.industry4.webapp.bizservice.customorder.impl;


import java.io.File;
import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.enums.UploadResourceType;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.customorder.CustomOrderFilesDao;
import com.lwxf.industry4.webapp.bizservice.customorder.CustomOrderFilesService;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderFiles;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-04 17:48:28
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("customOrderFilesService")
public class CustomOrderFilesServiceImpl extends BaseServiceImpl<CustomOrderFiles, String, CustomOrderFilesDao> implements CustomOrderFilesService {


	@Resource

	@Override	public void setDao( CustomOrderFilesDao customOrderFilesDao) {
		this.dao = customOrderFilesDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<CustomOrderFiles> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}


	@Override
	public List<CustomOrderFiles> selectByOrderIdAndType(String orderId,Integer type,String belongId) {
		return this.dao.selectByOrderIdAndType(orderId,type,belongId);
	}

	@Override
	public CustomOrderFiles findByBelongIdAndTypeAndOrderId(String orderId, Integer type, String belongId) {
		return this.dao.findByBelongIdAndTypeAndOrderId(orderId,type,belongId);
	}

	@Override
	public List<CustomOrderFiles> selectByOrderId(String orderId) {
		return this.dao.selectByOrderId(orderId);
	}

	@Override
	public int deleteByOrderId(String orderId) {
		return this.dao.deleteByOrderId(orderId);
	}

	@Override
	public int deleteByBelongId(String belongId,String orderId) {
		//删除本地资源
		AppBeanInjector.baseFileUploadComponent.deleteFileByDir(AppBeanInjector.configuration.getUploadFileRootDir().concat(AppBeanInjector.configuration.getUploadFileContextPath()).concat(UploadResourceType.CUSTOM_ORDER.getModule()).concat("/").concat(orderId).concat("/").concat(belongId));
		return this.dao.deleteByBelongId(belongId);
	}

	@Override
	public int updateByIds(MapContext mapContext) {
		return this.dao.updateByIds(mapContext);
	}
}