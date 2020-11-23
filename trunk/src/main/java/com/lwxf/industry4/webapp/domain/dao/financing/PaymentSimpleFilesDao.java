package com.lwxf.industry4.webapp.domain.dao.financing;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.financing.PaymentFiles;
import com.lwxf.industry4.webapp.domain.entity.financing.PaymentSimpleFiles;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;

import java.util.List;


/**
 * 功能：
 * 
 * @author：zhangxiaiolin(3965488@qq.com)
 * @created：2019-04-11 16:16:41
 * @version：2019 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface PaymentSimpleFilesDao extends BaseDao<PaymentSimpleFiles, String> {

	PaginatedList<PaymentSimpleFiles> selectByFilter(PaginatedFilter paginatedFilter);

	List<PaymentSimpleFiles> findByPaymentSimpleId(String paymentSimpleId);

}