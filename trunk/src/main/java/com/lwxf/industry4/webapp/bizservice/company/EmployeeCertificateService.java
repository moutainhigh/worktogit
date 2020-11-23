package com.lwxf.industry4.webapp.bizservice.company;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.dept.EmployeeCertificateDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.domain.entity.company.EmployeeCertificate;


/**
 * 功能：
 * 
 * @author：dongshibo(F_baisi@163.com)
 * @created：2019-04-04 15:32:29
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface EmployeeCertificateService extends BaseService <EmployeeCertificate, String> {

	PaginatedList<EmployeeCertificate> selectByFilter(PaginatedFilter paginatedFilter);

	List<EmployeeCertificateDto> findListByEid(String eid);
}