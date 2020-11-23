package com.lwxf.industry4.webapp.bizservice.financing;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.domain.entity.financing.PaymentList;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-11-04 17:34:53
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface PaymentListService extends BaseService <PaymentList, String> {

	PaginatedList<PaymentList> selectByFilter(PaginatedFilter paginatedFilter);

}