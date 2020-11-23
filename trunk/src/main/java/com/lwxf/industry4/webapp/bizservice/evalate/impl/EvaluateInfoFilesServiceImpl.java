package com.lwxf.industry4.webapp.bizservice.evalate.impl;


import com.lwxf.commons.uniquekey.IdGererateFactory;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.bizservice.evalate.EvaluateInfoFilesService;
import com.lwxf.industry4.webapp.common.component.UploadInfo;
import com.lwxf.industry4.webapp.common.enums.UploadResourceType;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.domain.dao.evaluate.EvaluateInfoFilesDao;
import com.lwxf.industry4.webapp.domain.entity.evaluate.EvaluateInfoFiles;
import com.lwxf.industry4.webapp.domain.entity.visitinfo.VisitInfoFiles;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Date;

import static com.lwxf.industry4.webapp.facade.AppBeanInjector.baseFileUploadComponent;


/**
 * 功能：
 *
 * @author：lyh
 * @created：2019-11-29 16:35:04
 * @version：
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("evaluateInfoFilesService")
public class EvaluateInfoFilesServiceImpl extends BaseServiceImpl<EvaluateInfoFiles, String, EvaluateInfoFilesDao> implements EvaluateInfoFilesService {


    @Resource

    @Override
    public void setDao(EvaluateInfoFilesDao evaluateInfoFilesDao) {
        this.dao = evaluateInfoFilesDao;
    }


    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public PaginatedList<EvaluateInfoFiles> selectByFilter(PaginatedFilter paginatedFilter) {
        //
        return this.dao.selectByFilter(paginatedFilter);
    }

    @Resource
    private IdGererateFactory idGererateFactory;

    @Override
    public RequestResult uploadEvaluateInfoFile(MultipartFile multipartFile, String userId) {

        UploadInfo uploadInfo = baseFileUploadComponent.doUploadByModule(multipartFile, UploadResourceType.MATERIAL, userId);
        EvaluateInfoFiles evaluateInfoFiles = new EvaluateInfoFiles();
        evaluateInfoFiles.setId(this.idGererateFactory.nextStringId());
        evaluateInfoFiles.setCreator(userId);//用户id
        evaluateInfoFiles.setFullPath(uploadInfo.getRealPath());//附件路径
        String contentType = multipartFile.getContentType();
        evaluateInfoFiles.setMime(contentType);
        evaluateInfoFiles.setName(multipartFile.getName());
        evaluateInfoFiles.setCreated(new Date());
        evaluateInfoFiles.setPath(uploadInfo.getRelativePath());
        evaluateInfoFiles.setOriginalMime(uploadInfo.getFileMimeType().getOriginalType());
        Byte a = 1;
        evaluateInfoFiles.setStatus(a);
        this.dao.insert(evaluateInfoFiles);
        return ResultFactory.generateRequestResult(evaluateInfoFiles);
    }
}