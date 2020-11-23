package com.lwxf.industry4.webapp.bizservice.aftersale;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.domain.entity.aftersale.AftersaleApplyFiles;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-01-02 20:27:26
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface AftersaleApplyFilesService extends BaseService <AftersaleApplyFiles, String> {

	PaginatedList<AftersaleApplyFiles> selectByFilter(PaginatedFilter paginatedFilter);

	List<AftersaleApplyFiles> findFilesByAfterId(String aftersaleId);

	List<String> findByPath(List<String> pathList);

	int updateFilesList(String id, List imgIds);

	List<AftersaleApplyFiles> findListByOrderId(String orderId);

	int deleteByOrderId(String orderId);

	List<AftersaleApplyFiles> findListByResultOrderId(String orderId);

	int deleteByResultOrderId(String orderId);

	int updateByIds(MapContext mapContext);
}