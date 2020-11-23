package com.lwxf.industry4.webapp.domain.dao.financing.impl;

import java.util.List;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Repository;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.financing.BankAccountDao;
import com.lwxf.industry4.webapp.domain.entity.financing.BankAccount;


/**
 * 功能：
 * 
 * @author：zhangxiaolin(3965488@qq.com)
 * @created：2019-05-22 16:20:22
 * @version：2019 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("bankAccountDao")
public class BankAccountDaoImpl extends BaseDaoImpl<BankAccount, String> implements BankAccountDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<BankAccount> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<BankAccount> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public List<BankAccount> selectByFilter(MapContext map) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		return this.getSqlSession().selectList(sqlId,map);
	}

	@Override
	public BankAccount findByCode(String code) {
		String sqlId = this.getNamedSqlId("findByCode");
		return this.getSqlSession().selectOne(sqlId,code);
	}
}