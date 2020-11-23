package com.lwxf.industry4.webapp.domain.dao.contentmng.impl;


import java.util.List;
import java.util.Map;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.contentmng.ContentsFilesDao;
import com.lwxf.industry4.webapp.domain.entity.contentmng.ContentsFiles;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-03-27 11:58:34
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("contentsFilesDao")
public class ContentsFilesDaoImpl extends BaseDaoImpl<ContentsFiles, String> implements ContentsFilesDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<ContentsFiles> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<ContentsFiles> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public ContentsFiles findByContentId(String contentId) {
		String sqlId = this.getNamedSqlId("selectByContentId");
		return this.getSqlSession().selectOne(sqlId,contentId);
	}

	@Override
	public List<ContentsFiles> findContentsFiles(String contentsId) {
		String sqlId=this.getNamedSqlId("findContentsFiles");
		return this.getSqlSession().selectList(sqlId,contentsId);
	}

}