package com.lwxf.industry4.webapp.domain.dao.quickshare;


import java.util.List;
import java.util.Set;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.dao.base.BaseNoIdDao;
import com.lwxf.industry4.webapp.domain.dto.quickshare.QuicksharePraiseDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.quickshare.QuicksharePraise;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-03-02 13:25:55
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface QuicksharePraiseDao extends BaseNoIdDao<QuicksharePraise> {

	QuicksharePraise selectByMemberIdAndQuickshareId(String memberId, String quickshareId);
	PaginatedList<QuicksharePraise> selectByFilter(PaginatedFilter paginatedFilter);

	List<QuicksharePraiseDto> selectByQuickshareId(String quickshareId);

	void deleteQuiPraByQuiId(String quickshareId);

	void deleteByQuiIdAndUid(String quickshareId, String memberId);

	List<QuicksharePraise> findByQsIds(Set<String> quickshareIds);
}