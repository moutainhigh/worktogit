package com.lwxf.industry4.webapp.bizservice.evalate;


import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.evaluate.EvaluateInfo;
import org.springframework.web.multipart.MultipartFile;


/**
 * 功能：
 * 
 * @author：lyh
 * @created：2019-11-29 14:39:15
 * @version：
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface EvaluateInfoService extends BaseService <EvaluateInfo, String> {

	PaginatedList<EvaluateInfo> selectByFilter(PaginatedFilter paginatedFilter);



}