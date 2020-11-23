package com.lwxf.industry4.webapp.domain.dao.company;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.dto.company.CompanyCertificatesDto;
import com.lwxf.industry4.webapp.domain.entity.company.CompanyCertificates;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;

import java.util.List;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2020-08-20 10:15:09
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface CompanyCertificatesDao extends BaseDao<CompanyCertificates, String> {

	PaginatedList<CompanyCertificates> selectByFilter(PaginatedFilter paginatedFilter);

    List<CompanyCertificatesDto> findByCid(String cid);
}