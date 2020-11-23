package com.lwxf.industry4.webapp.bizservice.attachmentFiles;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.domain.entity.attachmentFiles.AttachmentFiles;
import org.springframework.web.multipart.MultipartFile;


/**
 * 功能：
 * 
 * @author：lyh
 * @created：2019-12-21 17:12:32
 * @version：
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface AttachmentFilesService extends BaseService <AttachmentFiles, String> {

	PaginatedList<AttachmentFiles> selectByFilter(PaginatedFilter paginatedFilter);


	public RequestResult commitfile(String userId, MultipartFile multipartFile);

}