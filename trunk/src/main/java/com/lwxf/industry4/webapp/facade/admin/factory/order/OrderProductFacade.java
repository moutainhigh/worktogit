package com.lwxf.industry4.webapp.facade.admin.factory.order;


import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.utils.excel.impl.OrderProductExcelParam;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

public interface OrderProductFacade extends BaseFacade {

    RequestResult updateOrderProduct(String id,MapContext mapContext);

    RequestResult updateDeliveryInfo(MapContext mapContext);

    RequestResult findOrderProductList(Integer pageNum, Integer pageSize, MapContext mapContext);

    RequestResult countByProductStatus(Integer Status);

    RequestResult findProductSeries();

    RequestResult countPartStock();

    RequestResult writeExcel(Integer pageNum, Integer pageSize, MapContext mapContext, OrderProductExcelParam excelParam);
}
