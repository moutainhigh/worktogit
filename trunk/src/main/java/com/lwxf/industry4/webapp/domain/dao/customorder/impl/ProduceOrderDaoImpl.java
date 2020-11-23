package com.lwxf.industry4.webapp.domain.dao.customorder.impl;


import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lwxf.industry4.webapp.domain.dto.financing.PaymentProduceOrderDto;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.customorder.ProduceOrderState;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentFunds;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.customorder.ProduceOrderDto;
import com.lwxf.industry4.webapp.domain.dto.printTable.CoordinationPrintTableDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.customorder.ProduceOrderDao;
import com.lwxf.industry4.webapp.domain.entity.customorder.ProduceOrder;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@126.com)
 * @created：2019-04-08 15:09:45
 * @version：2019 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("produceOrderDao")
public class ProduceOrderDaoImpl extends BaseDaoImpl<ProduceOrder, String> implements ProduceOrderDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<ProduceOrder> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<ProduceOrder> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public ProduceOrderDto findOneById(String id) {
		String sqlId = this.getNamedSqlId("findOneById");
		MapContext mapContext = new MapContext();
		mapContext.put(WebConstant.KEY_ENTITY_ID,id);
		mapContext.put("funds", PaymentFunds.ORDER_FEE_CHARGE.getValue());
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public PaginatedList<ProduceOrderDto> findListByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findListByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<ProduceOrderDto> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public List<ProduceOrder> findListByIds(List<String> ids) {
		String sqlId = this.getNamedSqlId("findListByIds");
		return this.getSqlSession().selectList(sqlId,ids);
	}

	@Override
	public List<ProduceOrder> findListByProductId(String productId) {
		String sqlId = this.getNamedSqlId("findListByProductId");
		return this.getSqlSession().selectList(sqlId,productId);
	}

	@Override
	public List<ProduceOrderDto> findListByOrderId(MapContext mapContext) {
		String sqlId = this.getNamedSqlId("findListByOrderId");
		return this.getSqlSession().selectList(sqlId,mapContext);
	}

	@Override
	public List<ProduceOrderDto> findProduceOrderByProductId(String id) {
		String sqlId = this.getNamedSqlId("findProduceOrderByProductId");
		return this.getSqlSession().selectList(sqlId,id);
	}



	@Override
	public List<ProduceOrder> findIncompleteListByOrderId(String customOrderId,List<Integer> ways) {
		String sqlId = this.getNamedSqlId("findIncompleteListByOrderId");
		MapContext mapContext = new MapContext();
		mapContext.put(WebConstant.KEY_ENTITY_STATE,Arrays.asList(ProduceOrderState.NOT_YET_BEGUN.getValue(),ProduceOrderState.IN_PRODUCTION.getValue()));
		mapContext.put("orderId",customOrderId);
		mapContext.put("way",ways);
		return this.getSqlSession().selectList(sqlId,mapContext);
	}

	@Override
	public int updatePayByOrderIdAndWays(String orderId, List<Integer> ways) {
		String sqlId = this.getNamedSqlId("updatePayByOrderIdAndWays");
		MapContext mapContext = new MapContext();
		mapContext.put("orderId",orderId);
		mapContext.put("ways",ways);
		return this.getSqlSession().update(sqlId,mapContext);
	}

	@Override
	public List<ProduceOrder> findListByOrderIdAndTypesAndWays(String id, List<Integer> types, List<Integer> ways) {
		String sqlId = this.getNamedSqlId("findListByOrderIdAndTypesAndWays");
		MapContext mapContext = new MapContext();
		mapContext.put("orderId",id);
		mapContext.put("types",types);
		mapContext.put("ways",ways);
		return this.getSqlSession().selectList(sqlId,mapContext);
	}

	@Override
	public int updateMapContextByIds(MapContext mapContext) {
		String sqlId = this.getNamedSqlId("updateMapContextByIds");
		return this.getSqlSession().update(sqlId,mapContext);
	}

	@Override
	public int updatePlanTimeByIds(Date planTime, List ids) {
		String sqlId = this.getNamedSqlId("updatePlanTimeByIds");
		MapContext mapContext = new MapContext();
		mapContext.put("ids",ids);
		mapContext.put("planTime",planTime);
		mapContext.put("actualTime",planTime);
		mapContext.put(WebConstant.KEY_ENTITY_STATE,ProduceOrderState.IN_PRODUCTION.getValue());
		mapContext.put("planCreated",DateUtil.getSystemDate());
		mapContext.put("planCreator",WebUtils.getCurrUserId());
		return this.getSqlSession().update(sqlId,mapContext);
	}

	@Override
	public int deleteByOrderId(String orderId) {
		String sqlId = this.getNamedSqlId("deleteByOrderId");
		return this.getSqlSession().delete(sqlId,orderId);
	}

	@Override
	public int deleteByProductId(String pId) {
		String sqlId = this.getNamedSqlId("deleteByProductId");
		return this.getSqlSession().delete(sqlId,pId);
	}

	@Override
	public List findListOrderIdByPId(List ids) {
		String sqlId = this.getNamedSqlId("findListOrderIdByPId");
		return this.getSqlSession().selectList(sqlId,ids);
	}

	@Override
	public CoordinationPrintTableDto findCoordinationPrintInfo(String id) {
		String sqlId = this.getNamedSqlId("findCoordinationPrintInfo");
		MapContext mapContext = new MapContext();
		mapContext.put(WebConstant.KEY_ENTITY_ID,id);
		mapContext.put("funds",PaymentFunds.COORDINATION.getValue());
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public MapContext findCoordinationCount(String branchId) {
		String sqlId = this.getNamedSqlId("findCoordinationCount");
		return this.getSqlSession().selectOne(sqlId,branchId);
	}

	@Override
	public MapContext countTopProduct(MapContext params) {
		String sqlId = this.getNamedSqlId("countTopProduct");
		return this.getSqlSession().selectOne(sqlId,params);
	}

	@Override
	public List<ProduceOrderDto> findProduceListByProductId(MapContext param) {
		String sqlId = this.getNamedSqlId("findProduceListByProductId");
		return this.getSqlSession().selectList(sqlId,param);
	}

	@Override
	public Integer findByProductIdAndStockCount(String productId) {
		String sqlId = this.getNamedSqlId("findByProductIdAndStockCount");
		return this.getSqlSession().selectOne(sqlId,productId);
	}

	@Override
	public List<ProduceOrder> findListByProductIdAndType(String id, Integer type) {
		MapContext param=MapContext.newOne();
		param.put("productId",id);
		param.put("type",type);
		String sqlId=this.getNamedSqlId("findListByProductIdAndType");
		return this.getSqlSession().selectList(sqlId,param);
	}

	@Override
	public ProduceOrder findOneByTypeAndWayAndProductId(MapContext param) {
		String sqlId = this.getNamedSqlId("findOneByTypeAndWayAndProductId");
		return this.getSqlSession().selectOne(sqlId,param);
	}

	@Override
	public MapContext findListByProductIdAndState(String productId, int value) {
		MapContext param=MapContext.newOne();
		param.put("productId",productId);
		param.put("state",value);
		String sqlId = this.getNamedSqlId("findListByProductIdAndState");
		return this.getSqlSession().selectOne(sqlId,param);
	}

	@Override
	public List<Map> findByOrderId(String orderId) {
		String sqlId = this.getNamedSqlId("findByOrderId");
		return this.getSqlSession().selectList(sqlId,orderId);
	}

	@Override
	public PaginatedList<PaymentProduceOrderDto> queryOrderOutsourcePayList(PaginatedFilter paginatedFilter) {

		String sqlId = this.getNamedSqlId("queryOrderOutsourcePayList");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<PaymentProduceOrderDto> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public Integer findAllProduceNum(MapContext map) {
		String sqlId = this.getNamedSqlId("findAllProduceNum");
		return this.getSqlSession().selectOne(sqlId,map);
	}

	@Override
	public List<String> findLogisticsNobyOrderId(String orderId) {
		String sqlId = this.getNamedSqlId("findLogisticsNobyOrderId");
		return this.getSqlSession().selectList(sqlId,orderId);
	}

	@Override
	public List<String> findOutProduceInfoByOrderId(String orderId) {
		String sqlId = this.getNamedSqlId("findOutProduceInfoByOrderId");
		return this.getSqlSession().selectList(sqlId,orderId);
	}

	@Override
	public MapContext findHasDeliverCountByProductId(String productId) {
		String sqlId = this.getNamedSqlId("findHasDeliverCountByProductId");
		return this.getSqlSession().selectOne(sqlId,productId);
	}

	@Override
	public MapContext findHasInPutCount(String productId) {
		String sqlId = this.getNamedSqlId("findHasInPutCount");
		return this.getSqlSession().selectOne(sqlId,productId);
	}
}