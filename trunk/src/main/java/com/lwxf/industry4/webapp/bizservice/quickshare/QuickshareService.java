package com.lwxf.industry4.webapp.bizservice.quickshare;


import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.entity.quickshare.Quickshare;


/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-03-02 13:18:25
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface QuickshareService extends BaseService <Quickshare, String> {

	PaginatedList<Quickshare> selectByFilter(PaginatedFilter paginatedFilter);



}