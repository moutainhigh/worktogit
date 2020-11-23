package com.lwxf.industry4.webapp.bizservice.financing.impl;

import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.bizservice.financing.PaymentSimpleService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.financing.PaymentSimpleDao;
import com.lwxf.industry4.webapp.domain.dto.financing.PaymentSimpleDto;
import com.lwxf.industry4.webapp.domain.dto.financing.PaymentSimpleListDtoForApp;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.domain.entity.financing.PaymentSimple;

import java.util.List;
import java.util.Map;


/**
 * 功能：
 * 
 * @author：zhangxiaolin(3965488@qq.com)
 * @created：2019-04-07 12:06:50
 * @version：2019 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("paymentSimpleService")
public class PaymentSimpleServiceImpl extends BaseServiceImpl<PaymentSimple, String, PaymentSimpleDao> implements PaymentSimpleService {

	@Resource
	@Override	public void setDao( PaymentSimpleDao paymentSimpleDao) {
		this.dao = paymentSimpleDao;
	}

	@Override
	public PaginatedList<PaymentSimple> selectByFilter(PaginatedFilter paginatedFilter) {
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public PaginatedList<PaymentSimpleDto> selectDtoByFilter(PaginatedFilter paginatedFilter) {
		return this.dao.selectDtoByFilter(paginatedFilter) ;
	}

	@Override
	public PaymentSimpleDto findDtoById(String id) {
		return this.dao.selectDtoById(id) ;
	}

	@Override
	public List<Map<String, String>> getUserForPaymentSimple(String roleId) {
		return this.dao.getUserForPaymentSimple(roleId) ;
	}

	@Override
	public Map<String,String> countPaymentSimpleForApp() {
		return this.dao.countPaymentSimpleForApp();
	}

	@Override
	public List<PaymentSimpleListDtoForApp> selectCurrentDayListByFilterForApp() {
		return this.dao.selectCurrentDayListByFilterForApp() ;
	}

	@Override
	public List<PaymentSimple> findFundsList(MapContext mapContext) {
		return this.dao.findFundsList(mapContext);
	}

	@Override
	public MapContext findCountByFundsAndDeptId(String funds, String deptId,String type) {
		return this.dao.findCountByFundsAndDeptId(funds,deptId,type);
	}

	@Override
	public MapContext countPaymentForPageIndex(String branchId) {
		return this.dao.countPaymentForPageIndex(branchId) ;
	}
}