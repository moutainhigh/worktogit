package com.lwxf.industry4.webapp.bizservice.customorder;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderFiles;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-04 17:48:28
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface CustomOrderFilesService extends BaseService <CustomOrderFiles, String> {

	PaginatedList<CustomOrderFiles> selectByFilter(PaginatedFilter paginatedFilter);

	List<CustomOrderFiles> selectByOrderIdAndType(String orderId,Integer type,String belongId);
	CustomOrderFiles findByBelongIdAndTypeAndOrderId(String orderId,Integer type,String belongId);
	List<CustomOrderFiles> selectByOrderId(String orderId);

	int deleteByOrderId(String orderId);

	int deleteByBelongId(String designId,String orderId);

	int updateByIds(MapContext mapContext);
}