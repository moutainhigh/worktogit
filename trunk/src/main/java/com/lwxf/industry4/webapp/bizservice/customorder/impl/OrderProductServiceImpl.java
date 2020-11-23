package com.lwxf.industry4.webapp.bizservice.customorder.impl;


import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.bizservice.customorder.OrderProductService;
import com.lwxf.industry4.webapp.common.enums.customorder.CustomOrderFilesType;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.customorder.CustomOrderFilesDao;
import com.lwxf.industry4.webapp.domain.dao.customorder.OrderProductAttributeDao;
import com.lwxf.industry4.webapp.domain.dao.customorder.OrderProductDao;
import com.lwxf.industry4.webapp.domain.dao.customorder.ProduceOrderDao;
import com.lwxf.industry4.webapp.domain.dao.product.ProductPartsDao;
import com.lwxf.industry4.webapp.domain.dao.warehouse.FinishedStockItemDao;
import com.lwxf.industry4.webapp.domain.dto.customorder.OrderProductDto;
import com.lwxf.industry4.webapp.domain.dto.customorder.ProduceOrderDto;
import com.lwxf.industry4.webapp.domain.dto.product.ProductPartsDto;
import com.lwxf.industry4.webapp.domain.dto.warehouse.FinishedStockItemDto;
import com.lwxf.industry4.webapp.domain.entity.customorder.OrderProduct;
import com.lwxf.industry4.webapp.domain.entity.customorder.OrderProductAttribute;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@126.com)
 * @created：2019-04-11 17:38:26
 * @version：2019 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("orderProductService")
public class OrderProductServiceImpl extends BaseServiceImpl<OrderProduct, String, OrderProductDao> implements OrderProductService {


	@Resource

	@Override	public void setDao( OrderProductDao orderProductDao) {
		this.dao = orderProductDao;
	}

	@Resource(name = "customOrderFilesDao")
	private CustomOrderFilesDao customOrderFilesDao;
	@Resource(name = "finishedStockItemDao")
	private FinishedStockItemDao finishedStockItemDao;
	@Resource(name = "productPartsDao")
	private ProductPartsDao productPartsDao;
	@Resource(name = "produceOrderDao")
	private ProduceOrderDao produceOrderDao;
	@Resource(name = "orderProductAttributeDao")
	private OrderProductAttributeDao orderProductAttributeDao;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<OrderProduct> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public PaginatedList<OrderProductDto> selectDtoByFilter(PaginatedFilter paginatedFilter) {
		return this.dao.selectDtoByFilter(paginatedFilter) ;
	}

	@Override
	public List<Map> findByOrderId(String orderId) {
		return this.dao.findByOrderId(orderId);
	}

	@Override
	public Integer countProductsByStatus(MapContext map) {
		return this.dao.countProductsByStatus(map);
	}

	@Override
	public OrderProductDto findOneById(String id) {
		OrderProductDto oneById = this.dao.findOneById(id);
		if(oneById!=null){
			oneById.setUploadFiles(this.customOrderFilesDao.selectByOrderIdAndType(oneById.getCustomOrderId(),CustomOrderFilesType.ORDER_PRODUCT.getValue(),id));
		}
		return oneById;
	}

	@Override
	public List<OrderProductDto> findListByOrderId(String id) {
		List<OrderProductDto> listByOrderId = this.dao.findListByOrderId(id);
		for(OrderProductDto orderProductDto:listByOrderId){
			List<ProduceOrderDto> produceOrderDtos=this.produceOrderDao.findProduceOrderByProductId(orderProductDto.getId());
			for(ProduceOrderDto produceOrderDto:produceOrderDtos){
				Integer count = produceOrderDto.getCount();
				Integer hasDeliverCount = produceOrderDto.getHasDeliverCount();
				if(count!=null&&!count.equals("")){
					if(hasDeliverCount==null||hasDeliverCount.equals("")){
						hasDeliverCount=0;
					}
					Integer toBeDeliver=count-hasDeliverCount;
					produceOrderDto.setToBeDeliver(toBeDeliver.toString());
				}else {
					produceOrderDto.setToBeDeliver("");
				}
			}
			List<ProductPartsDto> productPartsDtos=this.productPartsDao.findByOrderProductId(orderProductDto.getId());
			orderProductDto.setProduceOrderList(produceOrderDtos);
			orderProductDto.setProductPartsDtos(productPartsDtos);
			orderProductDto.setUploadFiles(this.customOrderFilesDao.selectByOrderIdAndType(id,CustomOrderFilesType.ORDER_PRODUCT.getValue(),orderProductDto.getId()));
			//查询产品属性
			List<OrderProductAttribute> attributes=this.orderProductAttributeDao.findListByProductId(orderProductDto.getId());
			orderProductDto.setProductAttributeValues(attributes);
		}
		return listByOrderId;
	}

	@Override
	public BigDecimal findCountPriceByOrderId(String id) {
		return this.dao.findCountPriceByOrderId(id);
	}

	@Override
	public BigDecimal findCountPriceByCreatedAndStatus(String beginTime, String endTime, String created,
													   Integer status,Integer type,Integer series) {
		MapContext params = MapContext.newOne();
		params.put("beginTime", beginTime);
		params.put("endTime", endTime);
		params.put("status", status);
		params.put("created", created);
		params.put("type", type);
		params.put("series", series);
		return this.dao.findCountPriceByCreatedAndStatus(params);
	}
	@Override
	public Integer findCountNumByCreatedAndStatus(String beginTime, String endTime, String created,
													   Integer status,Integer type,Integer series) {
		MapContext params = MapContext.newOne();
		params.put("beginTime", beginTime);
		params.put("endTime", endTime);
		params.put("status", status);
		params.put("created", created);
		params.put("type", type);
		params.put("series", series);
		return this.dao.findCountNumByCreatedAndStatus(params);
	}

	@Override
	public int deleteByOrderId(String orderId) {
		return this.dao.deleteByOrderId(orderId);
	}

	@Override
	public List<OrderProductDto> findProductsByOrderId(String id) {
		List<OrderProductDto> orderProductDtos=this.dao.findProductsByOrderId(id);
		for(OrderProductDto orderProductDto:orderProductDtos){
			String productId=orderProductDto.getId();
			List<FinishedStockItemDto> finishedStockItemDtos=finishedStockItemDao.findListByProductId(productId);
			orderProductDto.setFinishedStockItemDtos(finishedStockItemDtos);
		}
		return orderProductDtos;
	}

	@Override
	public List<OrderProductDto> findListByAftersaleId(String id) {
		return this.dao.findListByAftersaleId(id);
	}

	@Override
	public PaginatedList<OrderProductDto> findListByPaginateFilter(PaginatedFilter paginatedFilter) {
		return this.dao.findListByPaginateFilter(paginatedFilter);
	}

	@Override
	public int cancelOrderProduct(MapContext mapContext) {
		return this.dao.cancelOrderProduct(mapContext);
	}

	@Override
	public List<MapContext> countPartStock(String branchId) {
		return this.dao.countPartStock(branchId);
	}

	@Override
	public Integer findCountNumByCreatedAndType(String beginTime, String endTime, Integer type, String created,Integer series) {
		MapContext params = MapContext.newOne();
		params.put("beginTime", beginTime);
		params.put("endTime", endTime);
		params.put("type", type);
		params.put("series", series);
		params.put("created", created);
		return this.dao.findCountNumByCreatedAndType(params);
	}

	@Override
	public MapContext countInputPart(String branchId) {
		return this.dao.countInputPart(branchId);
	}

	@Override
	public List<OrderProductDto> findListByOrderIdAndStatus(MapContext map) {
		return this.dao.findListByOrderIdAndStatus(map);
	}

	@Override
	public List<MapContext> findProductNumGroupByType(MapContext map) {
		return this.dao.findProductNumGroupByType(map);
	}

	@Override
	public double findProductMoneyByType(String dealerId, String productType) {
		return this.dao.findProductMoneyByType(dealerId,productType);
	}

	@Override
	public double findProductPriceTrendByTime(MapContext params) {
		return this.dao.findProductPriceTrendByTime(params);
	}

	@Override
	public Integer updateByMap(Map product) {
		return this.dao.updateByMap(product);
	}
}