package com.lwxf.industry4.webapp.bizservice.financing.impl;

import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.bizservice.financing.BankAccountService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.financing.BankAccountDao;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.domain.entity.financing.BankAccount;
import java.util.List;

/**
 * 功能：
 * 
 * @author：zhangxiaolin(3965488@qq.com)
 * @created：2019-05-22 16:20:22
 * @version：2019 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("bankAccountService")
public class BankAccountServiceImpl extends BaseServiceImpl<BankAccount, String, BankAccountDao> implements BankAccountService {

	@Resource
	@Override
	public void setDao( BankAccountDao bankAccountDao) {
		this.dao = bankAccountDao;
	}

	@Override
	public PaginatedList<BankAccount> selectByFilter(PaginatedFilter paginatedFilter) {
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public List<BankAccount> selectByFilter(MapContext map) {
		return this.dao.selectByFilter(map) ;
	}

	@Override
	public BankAccount findByCode(String code) {
		return this.dao.findByCode(code);
	}
}