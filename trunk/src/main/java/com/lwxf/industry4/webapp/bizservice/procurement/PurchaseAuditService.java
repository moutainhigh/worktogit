package com.lwxf.industry4.webapp.bizservice.procurement;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.domain.entity.procurement.PurchaseAudit;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-10-21 15:42:56
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface PurchaseAuditService extends BaseService <PurchaseAudit, String> {

	PaginatedList<PurchaseAudit> selectByFilter(PaginatedFilter paginatedFilter);

	List<PurchaseAudit> selectAuditByRequestId(String id);


	PurchaseAudit findByRequestIdAndLoginUserId(String purchaseRequestId, String userId, Integer value);

	List<Integer> findTypeByRequestIdAndUid(String id, String currUserId);
}