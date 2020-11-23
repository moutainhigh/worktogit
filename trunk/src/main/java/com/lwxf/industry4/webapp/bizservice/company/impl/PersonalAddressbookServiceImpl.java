package com.lwxf.industry4.webapp.bizservice.company.impl;


import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.company.PersonalAddressbookDao;
import com.lwxf.industry4.webapp.bizservice.company.PersonalAddressbookService;
import com.lwxf.industry4.webapp.domain.entity.company.PersonalAddressbook;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2019-03-07 13:45:41
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("personalAddressbookService")
public class PersonalAddressbookServiceImpl extends BaseServiceImpl<PersonalAddressbook, String, PersonalAddressbookDao> implements PersonalAddressbookService {


	@Resource

	@Override	public void setDao( PersonalAddressbookDao personalAddressbookDao) {
		this.dao = personalAddressbookDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<PersonalAddressbook> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public PersonalAddressbook findByCidAndUidAndMobile(MapContext mapContext) {
		return this.dao.findByCidAndUidAndMobile(mapContext);
	}
}