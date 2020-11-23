package com.lwxf.industry4.webapp.domain.dao.attachmentFiles.impl;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.attachmentFiles.AttachmentFiles;


/**
 * 功能：
 * 
 * @author：lyh
 * @created：2019-12-21 17:12:32
 * @version：
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface AttachmentFilesDao extends BaseDao<AttachmentFiles, String> {

	PaginatedList<AttachmentFiles> selectByFilter(PaginatedFilter paginatedFilter);

	int deleteByOriginalId(String originalId);

}