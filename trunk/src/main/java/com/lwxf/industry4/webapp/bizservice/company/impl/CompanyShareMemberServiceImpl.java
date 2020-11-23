package com.lwxf.industry4.webapp.bizservice.company.impl;


import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.company.companyShareMember.CompanyShareMemberDto;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.company.CompanyShareMemberDao;
import com.lwxf.industry4.webapp.bizservice.company.CompanyShareMemberService;
import com.lwxf.industry4.webapp.domain.entity.company.CompanyShareMember;


/**
 * 功能：
 * 
 * @author：dongshibo(F_baisi@163.com)
 * @created：2018-12-05 11:24:07
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("companyShareMemberService")
public class CompanyShareMemberServiceImpl extends BaseServiceImpl<CompanyShareMember, String, CompanyShareMemberDao> implements CompanyShareMemberService {


	@Resource

	@Override	public void setDao( CompanyShareMemberDao companyShareMemberDao) {
		this.dao = companyShareMemberDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<CompanyShareMemberDto> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public List<CompanyShareMember> findShareMemberList(String companyId) {
		return this.dao.findShareMemberList(companyId);
	}



	@Override
	public CompanyShareMember findShareMemberByCUID(String companyId, String shareMemberId) {
		return this.dao.findShareMemberByCUID(companyId,shareMemberId);
	}


	@Override
	public PaginatedList<Map> findDesigner(PaginatedFilter paginatedFilter) {
		return this.dao.findDesigner(paginatedFilter);
	}

	@Override
	public Map<String, String> findByDesigner(MapContext mapContext) {
		return this.dao.findByDesigner(mapContext);
	}

	@Override
	public List<CompanyShareMember> findByOrderId(String id) {
		return this.dao.findByOrderId(id);
	}

	@Override
	public CompanyShareMember findByCidAndNameAndType(String currCompanyId, String name,Integer type) {
		return this.dao.findByCidAndNameAndType(currCompanyId,name,type);
	}
}