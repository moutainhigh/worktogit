package com.lwxf.industry4.webapp.bizservice.procurement.impl;


import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.procurement.PurchaseAuditDao;
import com.lwxf.industry4.webapp.bizservice.procurement.PurchaseAuditService;
import com.lwxf.industry4.webapp.domain.entity.procurement.PurchaseAudit;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-10-21 15:42:56
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("purchaseAuditService")
public class PurchaseAuditServiceImpl extends BaseServiceImpl<PurchaseAudit, String, PurchaseAuditDao> implements PurchaseAuditService {


	@Resource

	@Override	public void setDao( PurchaseAuditDao purchaseAuditDao) {
		this.dao = purchaseAuditDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<PurchaseAudit> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public List<PurchaseAudit> selectAuditByRequestId(String id) {
		return this.dao.selectAuditByRequestId(id);
	}

	@Override
	public PurchaseAudit findByRequestIdAndLoginUserId(String id, String currUserId, Integer value) {
		return this.dao.findByRequestIdAndLoginUserId(id,currUserId,value);
	}

	@Override
	public List<Integer> findTypeByRequestIdAndUid(String id, String currUserId) {
		return this.dao.findTypeByRequestIdAndUid(id,currUserId);
	}

}