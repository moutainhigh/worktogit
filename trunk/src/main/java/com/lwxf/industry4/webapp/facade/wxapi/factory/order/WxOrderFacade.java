package com.lwxf.industry4.webapp.facade.wxapi.factory.order;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/6/15 0015 11:06
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface WxOrderFacade extends BaseFacade {
	RequestResult findWxOrderList(String branchId,Integer pageNum, Integer pageSize, MapContext mapContext);

	RequestResult findWxOrderInfo(String branchId, String orderId);

	RequestResult QueryOrdersTrace(String orderId);

	RequestResult findDeptList(String id,String companyId);

	RequestResult findDealersList(String condation,String branchId);
}
