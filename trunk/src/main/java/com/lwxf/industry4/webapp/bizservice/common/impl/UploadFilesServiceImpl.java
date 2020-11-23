package com.lwxf.industry4.webapp.bizservice.common.impl;


import javax.annotation.Resource;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.lwxf.industry4.webapp.bizservice.common.UploadFilesService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.uploadFiles.UploadFileDto;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;

import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.bizservice.common.UploadFilesService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.common.UploadFilesDao;
import com.lwxf.industry4.webapp.domain.entity.common.UploadFiles;


/**
 * 功能：
 * 
 * @author：renzhongshan(d3shan@126.com)
 * @created：2018-06-15 11:44:26
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("uploadFilesService")
public class UploadFilesServiceImpl extends BaseServiceImpl<UploadFiles, String, UploadFilesDao> implements UploadFilesService {


	@Resource
	@Override	public void setDao( UploadFilesDao uploadFilesDao) {
		this.dao = uploadFilesDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<UploadFiles> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public UploadFiles findByBelongIdAndResourceIdAndResourceType(String belongId, String ResourceId, int ResourceType) {
		return this.dao.findByBelongIdAndResourceIdAndResourceType(belongId,ResourceId,ResourceType);
	}

	@Override
	public List<UploadFiles> findListByBelongIdAndResourceIdAndResourceType(String belongId, String ResourceId, int ResourceType) {
		return this.dao.findListByBelongIdAndResourceIdAndResourceType(belongId,ResourceId,ResourceType);
	}

	@Override
	public int deleteByResourceId(String id) {
		return this.dao.deleteByResourceId(id);
	}

	@Override
	public List<UploadFiles> findByBelongId(String belongId) {
		return this.dao.findByBelongId(belongId);
	}
	@Override
	public int updateMicImageStatusAndresourceIdAndbelongId(String[] ids, String resourceId,String belongId) {
		return this.dao.updateMicImageStatusAndresourceIdAndbelongId(ids,resourceId,belongId);
	}

	@Override
	public int updateMicImageStatus(List<String> ids) {
		return this.dao.updateMicImageStatus(ids);
	}

	@Override
	public List<UploadFiles> isNullByIds(String[] ids) {
		return this.dao.isNullByIds(ids);
	}

	@Override
	public int deleteMicImage(String[] ids) {
		return this.dao.deleteMicImage(ids);
	}

	@Override
	public List<String> selectMicImageIdByBlogId(String blogId) {
		return this.dao.selectMicImageIdByBlogId(blogId);
	}

	@Override
	public List<UploadFiles> selectByCreatorAndTempAndResourceType(String creator) {
		return this.dao.selectByCreatorAndTempAndResourceType(creator);
	}

	@Override
	public int deleteByUserIdAndStatusAndResType(String userId) {
		return this.dao.deleteByUserIdAndStatusAndResType(userId);
	}

	@Override
	public List<UploadFiles> findByResourceId(String resoureceId) {
		return this.dao.findByResourceId(resoureceId);
	}

	@Override
	public List<UploadFiles> findByResourceIdAndStatusAndTypeId(String ResourcedId,Integer status) {
		return this.dao.findByResourceIdAndStatusAndTypeId(ResourcedId,status);
	}

	@Override
	public List<UploadFiles> findByResourceIdsAndStatusAndTypeId(Collection<String> resourcedIds, boolean status, int typeId) {
		return this.dao.findByResourceIdsAndStatusAndTypeId(resourcedIds,status,typeId);
	}

	@Override
	public List<UploadFiles> findByBlogIds(Set<String> blogIds) {
		return this.dao.findByBlogIds(blogIds);
	}

	@Override
	public int deleteByBelongId(String id) {
		return this.dao.deleteByBelongId(id);
	}

	@Override
	public UploadFiles selectByName(String name) {
		return this.dao.selectByName(name);
	}

	@Override
	public int updateFormalByPath(String schemeId, List uploadFilesList) {
		MapContext mapContext = MapContext.newOne();
		mapContext.put("schemeId",schemeId);
		mapContext.put("uploadFilesList",uploadFilesList);
		return this.dao.updateFormalByPath(mapContext);
	}

	@Override
	public UploadFiles findCoverImageByCidAndStatusAndResourceType(String dealerShopId, Integer status, Integer resourceType1) {
		return this.dao.findCoverImageByCidAndStatusAndResourceType(dealerShopId,status,resourceType1);
	}

	@Override
	public List<UploadFiles> findShowImageByCidAndStatusAndResType(String companyId, Integer status, Integer resourceType) {
		return this.dao.findShowImageByCidAndStatusAndResType(companyId,status,resourceType);
	}

	@Override
	public int updateByIds(MapContext mapContext) {
		return this.dao.updateByIds(mapContext);
	}

	@Override
	public List<UploadFileDto> findByCidAndStatusAndType(MapContext params) {
		return this.dao.findByCidAndStatusAndType(params);
	}
}