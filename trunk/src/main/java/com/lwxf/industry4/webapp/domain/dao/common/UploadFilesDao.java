package com.lwxf.industry4.webapp.domain.dao.common;


import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.uploadFiles.UploadFileDto;
import com.lwxf.industry4.webapp.domain.entity.common.UploadFiles;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.common.UploadFiles;
import com.lwxf.mybatis.utils.MapContext;


/**
 * 功能：
 * 
 * @author：renzhongshan(d3shan@126.com)
 * @created：2018-06-15 11:44:26
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface UploadFilesDao extends BaseDao<UploadFiles, String> {

	PaginatedList<UploadFiles> selectByFilter(PaginatedFilter paginatedFilter);
	UploadFiles findByBelongIdAndResourceIdAndResourceType(String belongId, String ResourceId, int ResourceType);

	List<UploadFiles> findListByBelongIdAndResourceIdAndResourceType(String belongId, String ResourceId, int ResourceType);

	UploadFiles selectByName(String name);
	int deleteByResourceId(String id);

	List<UploadFiles> findByBelongId(String belongId);
	int  	updateMicImageStatusAndresourceIdAndbelongId (String[] ids, String resourceId,String belongId);

	int updateMicImageStatus (List<String> ids);

	List<UploadFiles> isNullByIds (String[] ids);

	int deleteMicImage (String[] ids);

	List<String> selectMicImageIdByBlogId (String blogId);

	List<UploadFiles> selectByCreatorAndTempAndResourceType(String creator);

	int deleteByUserIdAndStatusAndResType(String userId);

	List<UploadFiles> findByResourceId(String resourceId);

	List<UploadFiles> findByBlogIds(Set<String> blogIds);
	int deleteByBelongId(String id);
	List<UploadFiles> findByResourceIdAndStatusAndTypeId(String ResourcedId,Integer status);
	List<UploadFiles> findByResourceIdsAndStatusAndTypeId(Collection<String> resourcedIds, boolean status, int typeId);
	int updateFormalByPath(MapContext mapContext);

	UploadFiles findCoverImageByCidAndStatusAndResourceType(String dealerShopId, Integer status, Integer resourceType1);

	List<UploadFiles> findShowImageByCidAndStatusAndResType(String companyId, Integer status, Integer resourceType);

	int updateByIds(MapContext mapContext);

	List<UploadFileDto> findByCidAndStatusAndType(MapContext params);
}