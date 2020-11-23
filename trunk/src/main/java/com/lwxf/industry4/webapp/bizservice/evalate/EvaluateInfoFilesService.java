package com.lwxf.industry4.webapp.bizservice.evalate;


import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.evaluate.EvaluateInfoFiles;
import org.springframework.web.multipart.MultipartFile;


/**
 * 功能：
 * 
 * @author：lyh
 * @created：2019-11-29 16:35:04
 * @version：
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface EvaluateInfoFilesService extends BaseService <EvaluateInfoFiles, String> {

	PaginatedList<EvaluateInfoFiles> selectByFilter(PaginatedFilter paginatedFilter);

	RequestResult uploadEvaluateInfoFile(MultipartFile multipartFile, String userId);

}