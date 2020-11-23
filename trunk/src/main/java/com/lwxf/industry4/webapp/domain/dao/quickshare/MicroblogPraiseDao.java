package com.lwxf.industry4.webapp.domain.dao.quickshare;


import java.util.List;
import java.util.Set;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.entity.quickshare.MicroblogPraise;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.industry4.webapp.domain.dao.base.BaseNoIdDao;
import com.lwxf.industry4.webapp.domain.entity.quickshare.MicroblogPraise;


/**
 * 功能：
 * 
 * @author：wangmingyuan(wangmingyuan@126.com)
 * @created：2018-07-02 14:59:25
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface MicroblogPraiseDao extends BaseNoIdDao<MicroblogPraise> {
	MicroblogPraise selectByMemberIdAndMicroblogId(String memberId, String microblogId);
	PaginatedList<MicroblogPraise> selectByFilter(PaginatedFilter paginatedFilter);
	int deleteByBlogId(String blogId);
	Integer findCountByMicroblogId(String microblogId);

	List<MicroblogPraise> findMemberId(MapContext mapContext);

	List<MicroblogPraise> findByBlogIds(Set<String> blogIds);

	List<MicroblogPraise> findByMemberId(String memberId);


}