package com.lwxf.industry4.webapp.facade.admin.factory.contentmng.impl;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.bizservice.contentmng.ContentsFilesService;
import com.lwxf.industry4.webapp.bizservice.contentmng.ContentsService;
import com.lwxf.industry4.webapp.common.aop.syslog.OperationMoudule;
import com.lwxf.industry4.webapp.common.aop.syslog.OperationType;
import com.lwxf.industry4.webapp.common.aop.syslog.SysOperationLog;
import com.lwxf.industry4.webapp.common.component.UploadInfo;
import com.lwxf.industry4.webapp.common.component.UploadType;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.UploadResourceType;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.UniqueKeyUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.contentmng.ContentsDto;
import com.lwxf.industry4.webapp.domain.entity.contentmng.Contents;
import com.lwxf.industry4.webapp.domain.entity.contentmng.ContentsFiles;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.admin.factory.contentmng.ContentsFacade;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("fContentsFacade")
public class ContentsFacadeImpl extends BaseFacadeImpl implements ContentsFacade {

    @Resource(name = "contentsService")
    private ContentsService contentsService;

    @Resource(name = "contentsFilesService")
    private ContentsFilesService contentsFilesService;

    @Override
    @Transactional(value = "transactionManager")
    @SysOperationLog(detail = "添加内容",operationType = OperationType.INSERT,operationMoudule = OperationMoudule.CONTENTS)
    public RequestResult addContent(ContentsDto contentsDto) {
        contentsDto.setStatus(0);
        contentsDto.setCreated(DateUtil.getSystemDate());
        contentsDto.setCreator(WebUtils.getCurrUserId());
        contentsDto.setBranchId(WebUtils.getCurrUser().getBranchId());
        //更新图片信息
        //更新图片的contentId
        this.contentsService.add(contentsDto);
        MapContext map = MapContext.newOne();
        map.put("id",contentsDto.getCoverId());
        map.put("contentsId",contentsDto.getId());
        map.put("status",1);
        this.contentsFilesService.updateByMapContext(map);
        //删除多余图片垃圾信息
        return ResultFactory.generateSuccessResult();
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult findContents(MapContext mapContext, Integer pageNum, Integer pageSize) {
        PaginatedFilter paginatedFilter = new PaginatedFilter();
        paginatedFilter.setFilters(mapContext);
        Pagination pagination = new Pagination();
        pagination.setPageSize(pageSize);
        pagination.setPageNum(pageNum);
        paginatedFilter.setPagination(pagination);
        Map<String,String> created = new HashMap<String, String>();
        created.put(WebConstant.KEY_ENTITY_CREATED,"desc");
        List sort = new ArrayList();
        sort.add(created);
        paginatedFilter.setSorts(sort);
        return ResultFactory.generateRequestResult(this.contentsService.selectByFilter(paginatedFilter));
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult uploadCover(MultipartFile multipartFile) {
        //添加图片到本地
        UploadInfo uploadInfo = AppBeanInjector.baseFileUploadComponent.doUploadByModule(UploadType.FORMAL, multipartFile, UploadResourceType.CONTENT, "cover");
        //添加图片到数据库
        ContentsFiles contentsFiles = new ContentsFiles();
        contentsFiles.setCreated(DateUtil.getSystemDate());
        contentsFiles.setCreator(WebUtils.getCurrUserId());
        contentsFiles.setFullPath(WebUtils.getDomainUrl() + uploadInfo.getRelativePath());
        contentsFiles.setMime(uploadInfo.getFileMimeType().getRealType());
        contentsFiles.setName(uploadInfo.getFileName());
        contentsFiles.setOriginalMime(uploadInfo.getFileMimeType().getOriginalType());
        contentsFiles.setPath(uploadInfo.getRelativePath());
        contentsFiles.setStatus(0);
        this.contentsFilesService.add(contentsFiles);
        MapContext map = MapContext.newOne();
        map.put("relPath",contentsFiles.getPath());
        map.put("fileId",contentsFiles.getId());
        return ResultFactory.generateRequestResult(map);
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult updateCover(MultipartFile multipartFile, String contentId) {
        MapContext parmas = MapContext.newOne();
        parmas.put("contentId",contentId);
        UploadInfo uploadInfo = null;
        ContentsFiles contentsFiles = null;
        contentsFiles= this.contentsFilesService.findByContentId(contentId);
        if (contentsFiles == null) {
            return ResultFactory.generateErrorResult(com.lwxf.commons.exception.ErrorCodes.BIZ_RES_NOT_FOUND_10001, AppBeanInjector.i18nUtil.getMessage("BIZ_RES_NOT_FOUND_10001"));
        }
        uploadInfo = AppBeanInjector.baseFileUploadComponent.doUploadByModule(UploadType.FORMAL,multipartFile,UploadResourceType.CONTENT,"cover");
        AppBeanInjector.baseFileUploadComponent.deleteFileByDir(AppBeanInjector.configuration.getUploadFileRootDir().concat(contentsFiles.getPath()));
        MapContext update = MapContext.newOne();
        update.put(WebConstant.KEY_ENTITY_ID,contentsFiles.getId());
        update.put("path",uploadInfo.getRelativePath());
        update.put("name",uploadInfo.getFileName());
        update.put("originalMime",uploadInfo.getFileMimeType().getOriginalType());
        update.put("mime",uploadInfo.getFileMimeType().getRealType());
        update.put("creator",WebUtils.getCurrUserId());
        update.put("created",DateUtil.getSystemDate());
        update.put("fullPath",WebUtils.getDomainUrl() + uploadInfo.getRelativePath());
        this.contentsFilesService.updateByMapContext(update);
        MapContext mapContext = new MapContext();
        mapContext.put("cover", uploadInfo.getRelativePath());
        mapContext.put(WebConstant.KEY_ENTITY_ID,contentId);
        this.contentsService.updateByMapContext(mapContext);
        Map map = new HashMap();
        map.put("relPath",uploadInfo.getRelativePath());
        return ResultFactory.generateRequestResult(map);
    }

    @Override
    @Transactional(value = "transactionManager")
    @SysOperationLog(detail = "删除内容",operationType = OperationType.DELETE,operationMoudule = OperationMoudule.CONTENTS)
    public RequestResult deleteContent(String id) {
        Contents content = this.contentsService.findById(id);
        if(content==null){
            return ResultFactory.generateSuccessResult();
        }
        this.contentsService.deleteById(id);
        AppBeanInjector.baseFileUploadComponent.deleteFileByDir(AppBeanInjector.configuration.getUploadAvatarRootDir().concat(UploadResourceType.CONTENT.getModule()).concat(File.separator).concat(content.getId()));
        return ResultFactory.generateSuccessResult();
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult findByContentId(String contentId) {
        return  ResultFactory.generateRequestResult(this.contentsService.findContentById(contentId));
    }

    @Override
    @Transactional(value = "transactionManager")
    @SysOperationLog(detail = "修改内容",operationType = OperationType.UPDATE,operationMoudule = OperationMoudule.CONTENTS)
    public RequestResult updateContent(MapContext mapContext) {
        return  ResultFactory.generateRequestResult(this.contentsService.updateByMapContext(mapContext));
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult publishContent(String contentId) {
        MapContext mapContext = MapContext.newOne();
        mapContext.put("id",contentId);
        mapContext.put("publisher",WebUtils.getCurrUserId());
        mapContext.put("publishTime",DateUtil.getSystemDate());
        mapContext.put("status",1);
        return  ResultFactory.generateRequestResult(this.contentsService.updateByMapContext(mapContext));
    }

    @Override
    @Transactional(value = "transactionManager")
    public MapContext uploadContentsImages(MultipartFile multipartFile, String contentsId) {
        if(contentsId==null|| contentsId.equals("")){
            contentsId = UniqueKeyUtil.getStringId();
        }
        ContentsFiles contentsFiles = new ContentsFiles();
        contentsFiles.setCreated(DateUtil.getSystemDate());
        contentsFiles.setCreator(WebUtils.getCurrUserId());
        contentsFiles.setStatus(0);
        contentsFiles.setContentsId(contentsId);
        List pathList = new ArrayList();
        //赋值给uploadinfo,数据进行处理
        UploadInfo uploadInfo = AppBeanInjector.baseFileUploadComponent.doUploadByModule(UploadType.TEMP, multipartFile, UploadResourceType.CONTENT);
        contentsFiles.setMime(uploadInfo.getFileMimeType().getRealType());
        contentsFiles.setOriginalMime(uploadInfo.getFileMimeType().getOriginalType());
        contentsFiles.setPath(uploadInfo.getRelativePath());
        contentsFiles.setFullPath(AppBeanInjector.configuration.getUploadFileRootDir().concat(uploadInfo.getRealPath()));
        contentsFiles.setName(uploadInfo.getFileName());
        this.contentsFilesService.add(contentsFiles);
        pathList.add(contentsFiles);
        MapContext map = MapContext.newOne();
        map.put("location",contentsFiles.getPath());
        return map;
    }
}
