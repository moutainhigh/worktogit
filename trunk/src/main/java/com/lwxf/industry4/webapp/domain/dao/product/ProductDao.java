package com.lwxf.industry4.webapp.domain.dao.product;


import java.util.List;
import java.util.Map;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.product.ProductDto;
import com.lwxf.industry4.webapp.domain.entity.product.ProductColor;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.product.Product;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-01 10:21:32
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface ProductDao extends BaseDao<Product, String> {

	PaginatedList<ProductDto> selectByFilter(PaginatedFilter paginatedFilter);

	List<Product> selectByCategoryId(String id);

	List<Product> selectByColorId(String id);

	List<Product> selectByMaterialId(String id);

	Product selectByModel(String no);

	ProductDto selectProductDtoById(String id);

	List<Product> selectBySpecId(String id);

	List<Product> findProductsBySupplierId(String supplierId);

	List<Product> findProductsRecommend(String companyId);

	List findResourcesList(MapContext mapContent);

	List<Map<String,Object>> findOrderChart(MapContext mapContent);

	List<Map<String,Object>> findOrderChartByseries(MapContext mapContent);

	List<Map<String,Object>> findOrderChartByDoor(MapContext mapContent);

	List<Map<String,Object>> findOrderChartByColor(MapContext mapContent);

	List<Map<String,Object>> findOrderChartTotal(MapContext mapContent);

	Integer findOrderMothyear(String Moth);

	List<Map<String, Object>> findOrderDeliveryDate(MapContext mapContent);


	List<Map<String, Object>> findStatusProductOrder(String status);

	List<Map<String, Object>> findStatusProductOrderOverdue(String deliveryDate);


	List<Map<String, Object>>  overdueOrderTrend(String queryDateMonth);

	Map<String, Object> findMaxMoneyOrder();

	Integer findStatusProductCount(String status,String startDate,String endDate);
	List<Map<String, Object>> findlogisticsNameCount(String startDate,String endDate);
}