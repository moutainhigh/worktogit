package com.lwxf.industry4.webapp.facade.admin.factory.dealer;

import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.utils.excel.ExcelParam;
import com.lwxf.industry4.webapp.domain.dto.aftersale.AftersaleDto;
import com.lwxf.industry4.webapp.domain.dto.customorder.CustomOrderDto;
import com.lwxf.industry4.webapp.domain.dto.customorder.CustomOrderInfoDto;
import com.lwxf.industry4.webapp.domain.dto.customorder.OrderProductDto;
import com.lwxf.industry4.webapp.domain.dto.customorder.ProduceOrderDto;
import com.lwxf.industry4.webapp.domain.dto.warehouse.FinishedStockItemDto;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrder;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderDemand;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderDesign;
import com.lwxf.industry4.webapp.domain.entity.customorder.ProduceFlow;
import com.lwxf.industry4.webapp.domain.entity.financing.Payment;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 功能：
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @create：2018/12/6 16:34
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface OrderFacade extends BaseFacade {

    RequestResult selectByCondition(Integer pageNum, Integer pageSize, MapContext params, String uid, String cid);
    RequestResult selectByOrderId(String id);
    RequestResult addOrder(CustomOrder customOrder, String uid, String cid);
    RequestResult updateOrder(MapContext updateMap, HttpServletRequest request);
    RequestResult deleteByOrderId(String orderId, HttpServletRequest request);
    RequestResult commitOrderDemand(MapContext params);

	RequestResult queryOrderOutsourcePayList(PaginatedFilter paginatedFilter);

    RequestResult findFinishedOrderList(List<Integer> integers, Integer pageNum, Integer pageSize, boolean finishedOrder);

    RequestResult findOrderList(MapContext mapContext, Integer pageNum, Integer pageSize);

	RequestResult updateOrderDesigner(String userId, String id);

	RequestResult findOrderInfo(String id);

	RequestResult findOrderInfoNew(String id);

	RequestResult findOrderDemand(String id, String demandId);

	RequestResult findOrderDesignId(String id, String designId);

	RequestResult addOrderDesign(String id, CustomOrderDesign customOrderDesign);

	RequestResult updateDesign(String id, String designId, MapContext mapContext);

	RequestResult deleteDesign(String id, String designId);

	RequestResult uploadFile(String id, String designId, List<MultipartFile> multipartFileList, Integer category);

	RequestResult deleteFile(String id, String designId, String fileId);

	RequestResult factoryAddOrder(CustomOrderInfoDto customOrderInfoDto, String aftersaleId, Payment payment,String noValue);

	RequestResult factoryUpdateOrder(String id, MapContext mapContext);

	RequestResult updateProduceOrderOutsource(String pId, MapContext mapContext);

	/**
	 * 新建生产单（非外协）
	 * @param id
	 * @param produceOrderDto
	 * @param fileIds
	 * @return
	 */
	RequestResult addProduceOrder(String id, ProduceOrderDto produceOrderDto, List<String> fileIds);
	/**
	 * 新建生产单（外协）
	 * @param id
	 * @param produceOrderDto
	 * @param fileIds
	 * @return
	 */
	RequestResult addCorporateProduceOrder(String id, ProduceOrderDto produceOrderDto, List<String> fileIds);


	RequestResult addOrderProduct(String id, OrderProductDto orderProduct);

	RequestResult updateOrderProduct(String id, List<MapContext> mapContexts);

	RequestResult deleteOrderProduct(String id, String pId);

	RequestResult updateProduceOrder(String id, String pId, MapContext mapContext);

	RequestResult deleteProduceOrder(String id, String pId);

	RequestResult findProducesList(MapContext mapContext, Integer pageNum, Integer pageSize, List<Map<String, String>> sorts);

	RequestResult addProduceFlow(String id, ProduceFlow produceFlow);

	RequestResult addOrderPack(String id, List<FinishedStockItemDto> finishedStockItemList,int type);

	RequestResult orderPacked(String id);

	RequestResult updateProducesOrderPlanTime(List ids, Date planTime);

	RequestResult endCoordination(String id);

	RequestResult findAllDesign(MapContext mapContext, Integer pageNum, Integer pageSize);

	RequestResult findOrderPackagesNo(String id, String type, Integer count);

	RequestResult uploadOrderFiles(String id, Integer type, String resId, List<MultipartFile> multipartFileList);

	RequestResult deleteCustomOrderFile(String id, String fileId);

	RequestResult deleteOrderById(String orderId);

	RequestResult producePermit(String orderId, String produceId);

	RequestResult produceComplete(String orderId, String produceId);

	RequestResult submitDesignfee(String orderId, MapContext mapContext);

	RequestResult addCustomOrderDemand(String orderId, CustomOrderDemand customOrderDemand);

	RequestResult updateCustomOrderDemand(String orderId, String demandId, MapContext mapContext);

	RequestResult deleteCustomOrderDemand(String orderId, String demandId);

	RequestResult findProduceOrderById(String id, String productId);

	RequestResult findProductInfo(String id, String pId);

	RequestResult findOrderPrintTable(String orderId);

	RequestResult findProductPrintTable(String orderProductId);


	RequestResult findDemandPrintTable(String demandId);

	RequestResult findDesignPrintTable(String designId);

	RequestResult findProducePrintTable(String produceId);

	RequestResult findDesignOverview();

	RequestResult findCustomOrderOverview();

	RequestResult findOrderProductPrintTable(String id);

	RequestResult addFactoryAftersaleOrder(CustomOrderDto customOrderDto);

	RequestResult updateProducts(String id, List<MapContext> mapContexts);

	RequestResult findExtendIntoNextMonth(Integer pageNum, Integer pageSize,MapContext mapContext);

	RequestResult editExtendIntoNextMonth(String orderId);

	RequestResult countLoginUserOrders(MapContext mapContext);

	RequestResult findAftersaleOrderOverview();

	RequestResult updateCustomOrderFlag(String orderId, String flag);

	RequestResult leaderupdateorder(String id, MapContext mapContext);

	RequestResult addOrderFiles(List<MultipartFile> multipartFileList);

	RequestResult writeOrderExcel(MapContext mapContext, Integer pageNum, Integer pageSize, ExcelParam excelParam);

	RequestResult addFactoryAftersaleOrderTwo(CustomOrderDto customOrderDto);


	RequestResult findDeliverGoodsList(String orderNo, String dealerName, String deliverStatus,String logisticsNo, Integer pageSize, Integer pageNum);


	RequestResult findDeliveMessage(String orderIds);

	RequestResult findDealerOrderList(String dealerId, Integer pageNum, Integer pageSize, MapContext mapContext);

	RequestResult dealerAddOrder(CustomOrderInfoDto customOrderInfoDto);

	RequestResult dealerUpdateOrder(String id, MapContext mapContext);

    RequestResult dealerFindOrderInfo(String id);

	RequestResult addAfterOrder(AftersaleDto aftersaleDto);

	RequestResult deleteDealerOrderInfo(String id);

    RequestResult factoryUpdateOrderBack(String id);

    boolean noDuplicateCheck(String no);

    RequestResult inputexcel(MultipartFile file);

    void inputpayment();

	RequestResult addOrderFilesByType(List<MultipartFile> multipartFileList, String fileType);

	void inputmaterial(MultipartFile file);

	void inputsupply(MultipartFile file);
}
