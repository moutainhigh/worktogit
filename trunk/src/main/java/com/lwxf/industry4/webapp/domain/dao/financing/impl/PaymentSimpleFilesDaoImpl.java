package com.lwxf.industry4.webapp.domain.dao.financing.impl;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.financing.PaymentSimpleFilesDao;
import com.lwxf.industry4.webapp.domain.entity.financing.PaymentSimpleFiles;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 功能：
 * 
 * @author：zhangxiaolin(3965488@qq.com)
 * @created：2019-04-11 16:16:41
 * @version：2019 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("paymentSimpleFilesDao")
public class PaymentSimpleFilesDaoImpl extends BaseDaoImpl<PaymentSimpleFiles, String> implements PaymentSimpleFilesDao {
	@Override
	public PaginatedList<PaymentSimpleFiles> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<PaymentSimpleFiles> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public List<PaymentSimpleFiles> findByPaymentSimpleId(String paymentSimpleId) {
		String sqlId = this.getNamedSqlId("findByPaymentId");
		return this.getSqlSession().selectList(sqlId,paymentSimpleId);
	}
}