package com.lwxf.industry4.webapp.bizservice.visitinfo;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.domain.entity.visitinfo.VisitInfoFiles;
import org.springframework.web.multipart.MultipartFile;


/**
 * 功能：
 * 
 * @author：lyh
 * @created：2019-11-26 11:42:07
 * @version：
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface VisitInfoFilesService extends BaseService <VisitInfoFiles, String> {

	PaginatedList<VisitInfoFiles> selectByFilter(PaginatedFilter paginatedFilter);

	public RequestResult uploadVisitInfoFiles(MultipartFile multipartFile, String userId);
	public String  uploadVisitInfoFiles2(MultipartFile multipartFile, String userId);

}