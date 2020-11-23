package com.lwxf.industry4.webapp.domain.dao.quickshare;


import java.util.List;
import java.util.Set;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.quickshare.QuickshareCommentDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.quickshare.QuickshareComment;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-03-02 13:18:25
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface QuickshareCommentDao extends BaseDao<QuickshareComment, String> {

	PaginatedList<QuickshareComment> selectByFilter(PaginatedFilter paginatedFilter);

	List<QuickshareCommentDto> findQuickshareCommentListByqsId(String quickshareId);

	void deleteByqsId(String quickshareId);

	List<QuickshareComment> findByParentId(String commentId);

	void updateByParentId(String commentId);

	List<QuickshareComment> selectQuickshareCommentByQsIds(Set<String> quickshareIds);
}