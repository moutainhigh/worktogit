package com.lwxf.industry4.webapp.domain.dao.attachmentFiles;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lwxf.industry4.webapp.domain.dao.attachmentFiles.impl.AttachmentFilesDao;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.entity.attachmentFiles.AttachmentFiles;


/**
 * 功能：
 * 
 * @author：lyh
 * @created：2019-12-21 17:12:32
 * @version：
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("attachmentFilesDao")
public class AttachmentFilesDaoImpl extends BaseDaoImpl<AttachmentFiles, String> implements AttachmentFilesDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<AttachmentFiles> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<AttachmentFiles> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}


	@Override
	public int deleteByOriginalId(String originalId) {
		String sqlId = this.getNamedSqlId("deleteByOriginalId");
		return this.getSqlSession().delete(sqlId,originalId);
	}
}