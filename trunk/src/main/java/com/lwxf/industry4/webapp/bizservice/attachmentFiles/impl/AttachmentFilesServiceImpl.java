package com.lwxf.industry4.webapp.bizservice.attachmentFiles.impl;


import com.lwxf.commons.uniquekey.IdGererateFactory;
import com.lwxf.industry4.webapp.common.component.UploadInfo;
import com.lwxf.industry4.webapp.common.enums.UploadResourceType;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.domain.entity.aftersale.AftersaleApplyFiles;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.attachmentFiles.impl.AttachmentFilesDao;
import com.lwxf.industry4.webapp.bizservice.attachmentFiles.AttachmentFilesService;
import com.lwxf.industry4.webapp.domain.entity.attachmentFiles.AttachmentFiles;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

import static com.lwxf.industry4.webapp.facade.AppBeanInjector.baseFileUploadComponent;


/**
 * 功能：
 * 
 * @author：lyh
 * @created：2019-12-21 17:12:32
 * @version：
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("attachmentFilesService")
public class AttachmentFilesServiceImpl extends BaseServiceImpl<AttachmentFiles, String, AttachmentFilesDao> implements AttachmentFilesService {

	@Resource
	private IdGererateFactory idGererateFactory;

	@Resource

	@Override	public void setDao( AttachmentFilesDao attachmentFilesDao) {
		this.dao = attachmentFilesDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<AttachmentFiles> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}



	@Override
	@Transactional(value = "transactionManager")
	public RequestResult commitfile(String userId, MultipartFile multipartFile) {
		UploadInfo uploadInfo = baseFileUploadComponent.doUploadByModule(multipartFile, UploadResourceType.MATERIAL, userId);
		AttachmentFiles attachmentFiles = new AttachmentFiles();
		attachmentFiles.setId(idGererateFactory.nextStringId());
		attachmentFiles.setCreator(userId);//用户id
		attachmentFiles.setFullPath(uploadInfo.getRealPath());//附件路径
		String contentType = multipartFile.getContentType();
		attachmentFiles.setMime(contentType);
		attachmentFiles.setName(multipartFile.getName());
		attachmentFiles.setCreated(new Date());
        Byte a =1;
        attachmentFiles.setStatus(a);
		attachmentFiles.setPath(uploadInfo.getRelativePath());
		attachmentFiles.setOriginalMime(uploadInfo.getFileMimeType().getOriginalType());
        a = 0;
		attachmentFiles.setCategory(a);
		this.dao.insert(attachmentFiles);
		return ResultFactory.generateRequestResult(attachmentFiles);
	}
}