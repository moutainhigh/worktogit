package com.lwxf.industry4.webapp.bizservice.quickshare.impl;


import javax.annotation.Resource;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.bizservice.quickshare.QuickshareCommentService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.quickshare.QuickshareCommentDao;
import com.lwxf.industry4.webapp.domain.dto.quickshare.QuickshareCommentDto;
import com.lwxf.industry4.webapp.domain.entity.quickshare.QuickshareComment;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-03-02 13:18:25
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("quickshareCommentService")
public class QuickshareCommentServiceImpl extends BaseServiceImpl<QuickshareComment, String, QuickshareCommentDao> implements QuickshareCommentService {


	@Resource

	@Override	public void setDao( QuickshareCommentDao quickshareCommentDao) {
		this.dao = quickshareCommentDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<QuickshareComment> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public List<QuickshareCommentDto> findQuickshareCommentListByqsId(String quickshareId) {
		return this.dao.findQuickshareCommentListByqsId(quickshareId);
	}

	@Override
	public void deleteByqsId(String quickshareId) {
		this.dao.deleteByqsId(quickshareId);
	}

	@Override
	public List<QuickshareComment> findByParentId(String commentId) {
		return this.dao.findByParentId(commentId);
	}

	@Override
	public void updateByParentId(String commentId) {
		this.dao.updateByParentId(commentId);
	}

	@Override
	public List<QuickshareComment> selectQuickshareCommentByQsIds(Set<String> quickshareIds) {
		return this.dao.selectQuickshareCommentByQsIds(quickshareIds);
	}


}