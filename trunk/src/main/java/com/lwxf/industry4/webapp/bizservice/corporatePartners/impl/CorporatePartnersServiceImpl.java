package com.lwxf.industry4.webapp.bizservice.corporatePartners.impl;


import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.corporatePartners.CorporatePartnersDto;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.corporatePartners.CorporatePartnersDao;
import com.lwxf.industry4.webapp.bizservice.corporatePartners.CorporatePartnersService;
import com.lwxf.industry4.webapp.domain.entity.corporatePartners.CorporatePartners;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@126.com)
 * @created：2019-08-01 14:13:42
 * @version：2019 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("corporatePartnersService")
public class CorporatePartnersServiceImpl extends BaseServiceImpl<CorporatePartners, String, CorporatePartnersDao> implements CorporatePartnersService {


	@Resource

	@Override	public void setDao( CorporatePartnersDao corporatePartnersDao) {
		this.dao = corporatePartnersDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<CorporatePartners> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public PaginatedList<CorporatePartnersDto> findListByFilter(PaginatedFilter paginatedFilter) {
		return this.dao.findListByFilter(paginatedFilter);
	}

	@Override
	public CorporatePartnersDto findCorporatePartnersInfo(String id) {
		return this.dao.findCorporatePartnersInfo(id);
	}

	@Override
	public CorporatePartnersDto findCorporateInfoByName(String coordinationName) {
		return this.dao.findCorporateInfoByName(coordinationName);
	}

	@Override
	public CorporatePartners findByTel(String tel) {
		return this.dao.findByTel(tel);
	}

}