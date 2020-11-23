package com.lwxf.industry4.webapp.domain.dao.company;


import java.util.List;
import java.util.Map;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.company.companyShareMember.CompanyShareMemberDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.company.CompanyShareMember;


/**
 * 功能：
 * 
 * @author：dongshibo(F_baisi@163.com)
 * @created：2018-12-05 11:24:07
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface CompanyShareMemberDao extends BaseDao<CompanyShareMember, String> {

	PaginatedList<CompanyShareMemberDto> selectByFilter(PaginatedFilter paginatedFilter);

	PaginatedList<Map> findDesigner(PaginatedFilter paginatedFilter);

	List<CompanyShareMember> findShareMemberList(String companyId);

	CompanyShareMember findShareMemberByCUID(String companyId, String shareMemberId);

    Map<String,String>findByDesigner(MapContext mapContext);

    List<CompanyShareMember> findByOrderId(String id);

    CompanyShareMember findByCidAndNameAndType(String currCompanyId, String name,Integer type);
}