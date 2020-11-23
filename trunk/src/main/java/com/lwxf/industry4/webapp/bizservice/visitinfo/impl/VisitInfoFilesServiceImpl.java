package com.lwxf.industry4.webapp.bizservice.visitinfo.impl;




import com.google.common.base.Utf8;
import com.google.gson.JsonObject;
import com.lwxf.commons.uniquekey.IdGererateFactory;
import com.lwxf.industry4.webapp.bizservice.visitinfo.VisitInfoFilesService;
import com.lwxf.industry4.webapp.common.component.UploadInfo;
import com.lwxf.industry4.webapp.common.enums.UploadResourceType;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.domain.dao.visitinfo.VisitInfoFilesDao;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.entity.visitinfo.VisitInfoFiles;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

import static com.lwxf.industry4.webapp.facade.AppBeanInjector.baseFileUploadComponent;


/**
 * 功能：
 * 
 * @author：lyh
 * @created：2019-11-26 11:42:07
 * @version：
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("visitInfoFilesService")
public class VisitInfoFilesServiceImpl extends BaseServiceImpl<VisitInfoFiles, String, VisitInfoFilesDao> implements VisitInfoFilesService {


	@Resource

	@Override	public void setDao( VisitInfoFilesDao visitInfoFilesDao) {
		this.dao = visitInfoFilesDao;
	}
	@Resource
	private IdGererateFactory idGererateFactory;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<VisitInfoFiles> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	public RequestResult uploadVisitInfoFiles(MultipartFile  multipartFile, String userId) {
		UploadInfo uploadInfo = baseFileUploadComponent.doUploadByModule(multipartFile, UploadResourceType.MATERIAL, userId);
		VisitInfoFiles visitInfoFiles=new VisitInfoFiles();
		visitInfoFiles.setId(this.idGererateFactory.nextStringId());
		visitInfoFiles.setCreator(userId);//用户id
		visitInfoFiles.setFullPath(uploadInfo.getRealPath());//附件路径
		String contentType = multipartFile.getContentType();
		visitInfoFiles.setMime(contentType);
		visitInfoFiles.setName(multipartFile.getName());
		visitInfoFiles.setCreated(new Date());
		visitInfoFiles.setPath(uploadInfo.getRelativePath());
		visitInfoFiles.setOriginalMime(uploadInfo.getFileMimeType().getOriginalType());
		Byte a =1;
        visitInfoFiles.setStatus(a);
		this.dao.insert(visitInfoFiles);
		return ResultFactory.generateRequestResult(visitInfoFiles);
	}


    public String  uploadVisitInfoFiles2(MultipartFile  multipartFile, String userId) {
        UploadInfo uploadInfo = baseFileUploadComponent.doUploadByModule(multipartFile, UploadResourceType.MATERIAL, userId);
        VisitInfoFiles visitInfoFiles=new VisitInfoFiles();
        visitInfoFiles.setId(this.idGererateFactory.nextStringId());
        visitInfoFiles.setCreator(userId);//用户id
        visitInfoFiles.setFullPath(uploadInfo.getRealPath());//附件路径
        String contentType = multipartFile.getContentType();
        visitInfoFiles.setMime(contentType);
        visitInfoFiles.setName(multipartFile.getName());
        visitInfoFiles.setCreated(new Date());
        visitInfoFiles.setPath(uploadInfo.getRelativePath());
        visitInfoFiles.setOriginalMime(uploadInfo.getFileMimeType().getOriginalType());
        Byte a =1;
        visitInfoFiles.setStatus(a);
        this.dao.insert(visitInfoFiles);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("code",0);
        jsonObject.addProperty("msg","");
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.addProperty("src",visitInfoFiles.getPath());
        jsonObject.add("data",jsonObject2);


        return jsonObject.toString();
    }

























}