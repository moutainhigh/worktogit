package com.lwxf.industry4.webapp.domain.dao.financing;



import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.financing.BankAccount;
import com.lwxf.mybatis.utils.MapContext;
import java.util.List;


/**
 * 功能：
 * 
 * @author：zhangxiaolin(3965488@qq.com)
 * @created：2019-05-22 16:20:22
 * @version：2019 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface BankAccountDao extends BaseDao<BankAccount, String> {

	PaginatedList<BankAccount> selectByFilter(PaginatedFilter paginatedFilter);

	List<BankAccount> selectByFilter(MapContext map);

	BankAccount findByCode(String code);
}