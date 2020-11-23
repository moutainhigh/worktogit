package com.lwxf.industry4.webapp.domain.dao.quickshare.impl;


import java.util.List;
import java.util.Map;
import java.util.Set;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.quickshare.QuickshareCommentDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.quickshare.QuickshareCommentDao;
import com.lwxf.industry4.webapp.domain.entity.quickshare.QuickshareComment;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-03-02 13:18:25
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("quickshareCommentDao")
public class QuickshareCommentDaoImpl extends BaseDaoImpl<QuickshareComment, String> implements QuickshareCommentDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<QuickshareComment> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<QuickshareComment> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public List<QuickshareCommentDto> findQuickshareCommentListByqsId(String quickshareId) {
		String sqlId=this.getNamedSqlId("findQuickshareCommentListByqsId");
		return this.getSqlSession().selectList(sqlId,quickshareId);
	}

	@Override
	public void deleteByqsId(String quickshareId) {
		String sqlId=this.getNamedSqlId("deleteByqsId");
		this.getSqlSession().delete(sqlId,quickshareId);
	}

	@Override
	public List<QuickshareComment> findByParentId(String commentId) {
		String sqlId=this.getNamedSqlId("findByParentId");
		return this.getSqlSession().selectList(sqlId,commentId);
	}

	@Override
	public void updateByParentId(String commentId) {
		String sqlId=this.getNamedSqlId("updateByParentId");
		this.getSqlSession().update(sqlId,commentId);
	}

	@Override
	public List<QuickshareComment> selectQuickshareCommentByQsIds(Set<String> quickshareIds) {
		String sqlId=this.getNamedSqlId("selectQuickshareCommentByQsIds");
		return this.getSqlSession().selectList(sqlId,quickshareIds);
	}


}