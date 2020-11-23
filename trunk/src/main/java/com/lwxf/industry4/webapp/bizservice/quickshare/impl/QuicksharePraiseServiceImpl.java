package com.lwxf.industry4.webapp.bizservice.quickshare.impl;


import javax.annotation.Resource;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.lwxf.industry4.webapp.bizservice.base.BaseNoIdServiceImpl;
import com.lwxf.industry4.webapp.bizservice.quickshare.QuicksharePraiseService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.dao.quickshare.QuicksharePraiseDao;
import com.lwxf.industry4.webapp.domain.dto.quickshare.QuicksharePraiseDto;
import com.lwxf.industry4.webapp.domain.entity.quickshare.QuicksharePraise;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-03-02 13:25:55
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("quicksharePraiseService")
public class QuicksharePraiseServiceImpl extends BaseNoIdServiceImpl<QuicksharePraise, QuicksharePraiseDao> implements QuicksharePraiseService {


	@Resource

	@Override	public void setDao( QuicksharePraiseDao quicksharePraiseDao) {
		this.dao = quicksharePraiseDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<QuicksharePraise> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public void deleteQuiPraByQuiId(String quickshareId) {
		this.dao.deleteQuiPraByQuiId(quickshareId);
	}

	@Override
	public void deleteByQuiIdAndUid(String quickshareId, String memberId) {
		 this.dao.deleteByQuiIdAndUid(quickshareId,memberId);
	}

	@Override
	public List<QuicksharePraise> findByQsIds(Set<String> quickshareIds) {
		return this.dao.findByQsIds(quickshareIds);
	}

	@Override
	public QuicksharePraise selectByMemberIdAndQuickshareId(String memberId, String quickshareId) {
		return this.dao.selectByMemberIdAndQuickshareId(memberId,quickshareId);
	}

	@Override
	public List<QuicksharePraiseDto> selectByQuickshareId(String quickshareId) {
		return this.dao.selectByQuickshareId(quickshareId);
	}

}