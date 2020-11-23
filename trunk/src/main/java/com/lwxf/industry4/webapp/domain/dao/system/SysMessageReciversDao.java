package com.lwxf.industry4.webapp.domain.dao.system;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.system.SysMessageReciversDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.system.SysMessageRecivers;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@126.com)
 * @created：2019-12-09 16:51:47
 * @version：2019 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface SysMessageReciversDao extends BaseDao<SysMessageRecivers, String> {

	PaginatedList<SysMessageRecivers> selectByFilter(PaginatedFilter paginatedFilter);

	List<SysMessageReciversDto> selectMessageByUserId(String userId);

	SysMessageRecivers selectByUserIdAndMessageId(String messageId,String userId);

}