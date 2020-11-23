package com.lwxf.industry4.webapp.facade.smallProgram.smallProgramOrder;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/10/19 0019 9:57
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface SpOrderFacade extends BaseFacade {
	RequestResult findSmallProgramOrderList(Integer pageNum, Integer pageSize, MapContext mapContext);

	RequestResult findSmallProgramOrderInfo(String branchid, String orderId);

	RequestResult findOrderIndexInfo(MapContext mapContext);
}
