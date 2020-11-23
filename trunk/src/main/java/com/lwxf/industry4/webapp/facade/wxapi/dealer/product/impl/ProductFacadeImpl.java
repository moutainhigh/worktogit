package com.lwxf.industry4.webapp.facade.wxapi.dealer.product.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.lwxf.industry4.webapp.bizservice.product.ProductFilesService;
import com.lwxf.industry4.webapp.bizservice.product.ProductService;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.company.EmployeeStatus;
import com.lwxf.industry4.webapp.common.enums.product.ProductFilesType;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.product.ProductDto;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.wxapi.dealer.product.ProductFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/6/15/015 18:14
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("wxBProductFacade")
public class ProductFacadeImpl extends BaseFacadeImpl implements ProductFacade {

	@Resource(name = "productService")
	private ProductService productService;

	@Resource(name = "productFilesService")
	private ProductFilesService productFilesService;


	@Override
	public RequestResult findProductList(Integer pageNum,Integer pageSize,String uid) {
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		MapContext mapContext = new MapContext();
		mapContext.put("lowerShelf",true);
		mapContext.put("fileType",ProductFilesType.WX_COVER_MAP.getValue());
		mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID,AppBeanInjector.companyService.findById(AppBeanInjector.companyEmployeeService.findCompanyByUidAndStatus(uid,EmployeeStatus.NORMAL.getValue()).getCompanyId()).getBranchId());
		paginatedFilter.setFilters(mapContext);
		Pagination pagination = new Pagination();
		pagination.setPageNum(pageNum);
		pagination.setPageSize(pageSize);
		paginatedFilter.setPagination(pagination);
		return ResultFactory.generateRequestResult(this.productService.selectByFilter(paginatedFilter));
	}

	@Override
	public RequestResult findProductInfo(String id) {
		//判断产品是否存在
		ProductDto productDto = this.productService.selectProductDtoById(id);
		if(productDto==null){
			return ResultFactory.generateResNotFoundResult();
		}
		productDto.setProductFiles(this.productFilesService.findListByType(ProductFilesType.WX_ATLAS.getValue(),id));
		productDto.setWxOfferFiles(this.productFilesService.findOneByProductIdAndType(id,ProductFilesType.WX_OFFER.getValue()));
		return ResultFactory.generateRequestResult(productDto);
	}
}
