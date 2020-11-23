package com.lwxf.industry4.webapp.facade.wxapi.dealer.company;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/6/20 0020 9:59
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface BWxCustomerFacade extends BaseFacade {
	RequestResult findWxBCustomers(Integer pageNum, Integer pageSize, MapContext mapContext);


	RequestResult findWxCustomerInfo(String customerId, String cid);


	RequestResult addWxCustomers(MapContext mapContext, String cid, String uid, String branchid);

	RequestResult updateWxCustomers(MapContext mapContext, String customerId);

	RequestResult deleteWxCustomer(String customerId, String cid);
}
