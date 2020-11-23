package com.lwxf.industry4.webapp.domain.dao.financing;

import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.financing.PaymentSimpleFundsDto;
import com.lwxf.industry4.webapp.domain.entity.financing.PaymentSimple;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.financing.PaymentSimpleFunds;
import com.lwxf.mybatis.utils.MapContext;

import java.util.List;


/**
 * 功能：
 * 
 * @author：djl(yuuyoo@163.com)
 * @created：2019-07-23 10:10:19
 * @version：2019 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface PaymentSimpleFundsDao extends BaseDao<PaymentSimpleFunds, String> {

	PaginatedList<PaymentSimpleFunds> selectByFilter(PaginatedFilter paginatedFilter);

    // 批量新增数据
    int insertBatch(List<PaymentSimpleFundsDto> fundsList);

    // 删除指定主表id的信息
    int deleteByPid(String paymentSimpleId);

	List<PaymentSimpleFunds> findFundsList(MapContext mapContext);

	MapContext findCountByFundsNameAndDeptId(String fundsName, String deptId, String type);

	List<PaymentSimpleFundsDto> findListByPaymentId(String paymentId);
}