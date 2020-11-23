package com.lwxf.industry4.webapp.domain.dao.financing.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.financing.PaymentSimpleDao;
import com.lwxf.industry4.webapp.domain.dto.financing.PaymentSimpleDto;
import com.lwxf.industry4.webapp.domain.dto.financing.PaymentSimpleListDtoForApp;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Repository;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
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
@Repository("paymentSimpleDao")
public class PaymentSimpleDaoImpl extends BaseDaoImpl<PaymentSimple, String> implements PaymentSimpleDao {

	@Override
	public PaginatedList<PaymentSimple> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<PaymentSimple> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	public PaginatedList<PaymentSimpleDto> selectDtoByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectDtoByFilter");
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<PaymentSimpleDto> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public PaymentSimpleDto selectDtoById(String id) {
		String sqlId = this.getNamedSqlId("selectDtoById");
		return this.sqlSession.selectOne(sqlId,id);
	}

	@Override
	public List<Map<String,String>> getUserForPaymentSimple(String roleId) {
		String sqlId = this.getNamedSqlId("getUserForPaymentSimple");
		return this.sqlSession.selectList(sqlId,roleId);
	}

	@Override
	public List<PaymentSimpleListDtoForApp> selectCurrentDayListByFilterForApp() {
		String sqlId = this.getNamedSqlId("selectCurrentDayListByFilterForApp");
		return this.sqlSession.selectList(sqlId);
	}

	@Override
	public List<PaymentSimple> findFundsList(MapContext mapContext) {
		String sqlId = this.getNamedSqlId("findFundsList");
		return this.sqlSession.selectList(sqlId,mapContext);
	}

	@Override
	public MapContext findCountByFundsAndDeptId(String funds, String deptId,String type) {
		String sqlId = this.getNamedSqlId("findCountByFundsAndDeptId");
		MapContext mapContext=MapContext.newOne();
		mapContext.put("funds",funds);
		mapContext.put("deptId",deptId);
		mapContext.put("type",type);
		return this.sqlSession.selectOne(sqlId,mapContext);
	}

	@Override
	public Map<String,String> countPaymentSimpleForApp() {
		String sqlId = this.getNamedSqlId("countPaymentSimpleForApp");
		return this.sqlSession.selectOne(sqlId);
	}

	@Override
	public MapContext countPaymentForPageIndex(String branchId) {
		String sqlId = this.getNamedSqlId("countPaymentForPageIndex");
		return this.sqlSession.selectOne(sqlId,branchId);
	}
}