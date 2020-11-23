package com.lwxf.industry4.webapp.facade.admin.factory.common.impl;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.bizservice.common.UploadFilesService;
import com.lwxf.industry4.webapp.bizservice.version.UpdateVersionService;
import com.lwxf.industry4.webapp.common.component.UploadInfo;
import com.lwxf.industry4.webapp.common.component.UploadType;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.UploadResourceType;
import com.lwxf.industry4.webapp.common.enums.common.UpdateVersionPlatform;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.entity.common.UploadFiles;
import com.lwxf.industry4.webapp.domain.entity.version.UpdateVersion;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.common.FUpdateVersionFacade;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/5/13/013 9:56
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component(value = "fUpdateVersionFacade")
public class FUpdateVersionFacadeImpl extends BaseFacadeImpl implements FUpdateVersionFacade{

	@Resource(name = "updateVersionService")
	private UpdateVersionService updateVersionService;
	@Resource(name = "uploadFilesService")
	private UploadFilesService uploadFilesService;

	@Override
	public RequestResult findVersionList(MapContext mapContext, Integer pageNum, Integer pageSize) {
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		Pagination pagination = new Pagination();
		pagination.setPageNum(pageNum);
		pagination.setPageSize(pageSize);
		paginatedFilter.setPagination(pagination);
		paginatedFilter.setFilters(mapContext);
		ArrayList<Map<String, String>> sort = new ArrayList<>();
		Map<String,String> created = new HashMap<String, String>();
		created.put(WebConstant.KEY_ENTITY_CREATED,"desc");
		sort.add(created);
		paginatedFilter.setSorts(sort);
		return ResultFactory.generateRequestResult(this.updateVersionService.selectByFilter(paginatedFilter));
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addUpdateVersion(UpdateVersion updateVersion) {
		//判断该类型数据是否存在,存在则更新 不存在则修改
		UpdateVersion oldVersion = this.updateVersionService.findOneByTypeAndPlatform(updateVersion.getSysType(),updateVersion.getPlatform());
		if (oldVersion==null){
			updateVersion.setCreateUser(WebUtils.getCurrUser().getName());
			updateVersion.setCreateTime(DateUtil.getSystemDate());
			this.updateVersionService.add(updateVersion);
			return ResultFactory.generateRequestResult(this.updateVersionService.findOneByTypeAndPlatform(updateVersion.getSysType(),updateVersion.getPlatform()));
		}else{
			oldVersion.setUpdateUser(WebUtils.getCurrUser().getName());
			oldVersion.setUpdateTime(DateUtil.getSystemDate());
			this.updateVersionService.updateByUpdateVersion(oldVersion);
			return ResultFactory.generateRequestResult(oldVersion);
		}
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult uploadFiles(String id, MultipartFile multipartFile) {
		//判断版本是否存在
		UpdateVersion updateVersion = this.updateVersionService.findOneByVersionId(id);
		if(updateVersion==null){
			return ResultFactory.generateResNotFoundResult();
		}
		UploadInfo uploadInfo = AppBeanInjector.baseFileUploadComponent.doUploadByModule(UploadType.FORMAL, multipartFile, UploadResourceType.UPDATE_VERSION,UpdateVersionPlatform.getByValue(updateVersion.getPlatform()).getName().concat("_").concat(updateVersion.getVersion()));
		//添加图片到数据库
		UploadFiles uploadFiles = new UploadFiles();
		uploadFiles.setCreated(DateUtil.getSystemDate());
		uploadFiles.setFullPath(WebUtils.getDomainUrl() + uploadInfo.getRelativePath());
		uploadFiles.setMime(uploadInfo.getFileMimeType().getRealType());
		uploadFiles.setName(uploadInfo.getFileName());
		uploadFiles.setOriginalMime(uploadInfo.getFileMimeType().getOriginalType());
		uploadFiles.setPath(uploadInfo.getRelativePath());
		uploadFiles.setStatus(UploadType.FORMAL.getValue());
		uploadFiles.setCompanyId(WebUtils.getCurrCompanyId());
		uploadFiles.setResourceType(UploadResourceType.UPDATE_VERSION.getType());
		//paymentSimpleFiles.setCreator(userId);
		uploadFiles.setCreator(WebUtils.getCurrUserId());
		this.uploadFilesService.add(uploadFiles);
		//修改原数据中的文件路径
		MapContext mapContext = new MapContext();
		mapContext.put("versionId",updateVersion.getVersionId());
		mapContext.put("url",uploadFiles.getFullPath());
		mapContext.put("fileSize",multipartFile.getSize());
		this.updateVersionService.updateByMapContext(mapContext);
		return ResultFactory.generateRequestResult(uploadFiles);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updateVersion(String id, MapContext mapContext) {
		//判断该条数据是否存在
		UpdateVersion oneByVersionId = this.updateVersionService.findOneByVersionId(id);
		if(oneByVersionId==null){
			return ResultFactory.generateResNotFoundResult();
		}
		mapContext.put("versionId",id);
		this.updateVersionService.updateByMapContext(mapContext);
		return ResultFactory.generateRequestResult(this.updateVersionService.findOneByVersionId(id));
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult deleteVersion(String id) {
		//判断该条数据是否存在
		UpdateVersion oneByVersionId = this.updateVersionService.findOneByVersionId(id);
		if(oneByVersionId==null){
			return ResultFactory.generateResNotFoundResult();
		}
		this.updateVersionService.deleteByVersionId(id);
		return ResultFactory.generateSuccessResult();
	}
}
