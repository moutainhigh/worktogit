package com.lwxf.industry4.webapp.facade.smallProgram.smallProgramSearch.impl;

import javax.annotation.Resource;

import java.util.List;

import org.springframework.stereotype.Component;

import com.lwxf.industry4.webapp.bizservice.company.CompanyEmployeeService;
import com.lwxf.industry4.webapp.bizservice.company.CompanyService;
import com.lwxf.industry4.webapp.bizservice.customorder.CustomOrderService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.domain.dto.company.CompanyDto;
import com.lwxf.industry4.webapp.domain.dto.companyEmployee.CompanyEmployeeDto;
import com.lwxf.industry4.webapp.domain.dto.customorder.CustomOrderDto;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.smallProgram.smallProgramSearch.SpSearchFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/10/19 0019 14:15
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("spSearchFacade")
public class SpSearchFacadeImpl extends BaseFacadeImpl implements SpSearchFacade {
    @Resource(name = "customOrderService")
	private CustomOrderService customOrderService;
    @Resource(name = "companyService")
    private CompanyService companyService;
    @Resource(name = "companyEmployeeService")
	private CompanyEmployeeService companyEmployeeService;

	@Override
	public RequestResult FuzzyQuery(String searchType, String branchId, String searchMessage, Integer pageSize, Integer pageNum) {
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		Pagination pagination = new Pagination();
		pagination.setPageNum(pageNum);
		pagination.setPageSize(pageSize);
		paginatedFilter.setPagination(pagination);
		MapContext map=MapContext.newOne();
		map.put("branchId",branchId);
		map.put("searchMessage",searchMessage);
		paginatedFilter.setFilters(map);
		MapContext result=MapContext.newOne();
		if(searchType.equals("1")){//查询经销商
			PaginatedList<CompanyDto> companyDtoList=this.companyService.findCompanyBySearchInfo(paginatedFilter);
			result.put("result",companyDtoList);
		}
		else if(searchType.equals("2")){//查询员工
			PaginatedList<CompanyEmployeeDto> employeeDtos=this.companyEmployeeService.findEmployeeBySearchInfo(paginatedFilter);
			result.put("result",employeeDtos);
		}
		else if(searchType.equals("3")){//查询订单
			PaginatedList<CustomOrderDto> orderDtos=this.customOrderService.findOrderBySearchInfo(paginatedFilter);
			result.put("result",orderDtos);
		}
		return ResultFactory.generateRequestResult(result);
	}
}
