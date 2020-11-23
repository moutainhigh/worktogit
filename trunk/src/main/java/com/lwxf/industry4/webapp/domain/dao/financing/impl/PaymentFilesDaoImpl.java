package com.lwxf.industry4.webapp.domain.dao.financing.impl;


import java.util.List;
import java.util.Map;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.financing.PaymentFilesDao;
import com.lwxf.industry4.webapp.domain.entity.financing.PaymentFiles;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-19 16:16:41
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("paymentFilesDao")
public class PaymentFilesDaoImpl extends BaseDaoImpl<PaymentFiles, String> implements PaymentFilesDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<PaymentFiles> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<PaymentFiles> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public List<PaymentFiles> findByPaymentId(String paymentId) {
		String sqlId = this.getNamedSqlId("findByPaymentId");
		return this.getSqlSession().selectList(sqlId,paymentId);
	}

	@Override
	public int deleteByPaymentId(String paymentId) {
		String sqlId = this.getNamedSqlId("deleteByPaymentId");
		return this.getSqlSession().delete(sqlId,paymentId);
	}
}