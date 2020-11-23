package com.lwxf.industry4.webapp.facade.wxapi.factory.customer;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.customer.CompanyCustomer;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/6/6 0006 15:43
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface CustomerFacade extends BaseFacade {
	RequestResult findWxCustomers(Integer pageNum, Integer pageSize, MapContext mapContext);

	Object findWxCustomerInfo(String branchId, String customerId);

	RequestResult addWxCustomers(MapContext mapContext,String branchId,String uid);

	RequestResult findWxCompanyEmployees(String branchId, String dealerId);

	RequestResult findWxDealersAddCustomer(String branchId,String cityId);


	RequestResult findCustomerOrders(Integer pageSize, Integer pageNum, String customerId);
}
