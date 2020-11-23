package com.lwxf.industry4.webapp.facade.admin.factory.design.impl;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lwxf.industry4.webapp.bizservice.design.DesignSchemeService;
import com.lwxf.industry4.webapp.bizservice.design.DesignStyleService;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.design.DesignSchemeDto;
import com.lwxf.industry4.webapp.domain.entity.design.DesignStyle;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.admin.factory.design.DesignStyleFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2019/2/25/025 14:19
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("designStyleFacade")
public class DesignStyleFacadeImpl extends BaseFacadeImpl implements DesignStyleFacade{

	@Resource(name = "designStyleService")
	private DesignStyleService designStyleService;
	@Resource(name = "designSchemeService")
	private DesignSchemeService designSchemeService;


	@Override
	public RequestResult findStyleList(MapContext mapContext) {
		String branchId= WebUtils.getCurrBranchId();
		mapContext.put("branchId",branchId);
		List<DesignStyle> designStyleList = this.designStyleService.findListByFilter(mapContext);
		return ResultFactory.generateRequestResult(designStyleList);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addStyle(DesignStyle designStyle) {
		String branchId= WebUtils.getCurrBranchId();
		//判断名称是否重复
		DesignStyle oneByName = this.designStyleService.selectOneByName(designStyle.getName(),branchId);
		if(oneByName!=null){
			Map result = new HashMap();
			result.put(WebConstant.KEY_ENTITY_NAME,AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOT_ALLOWED_REPEAT"));
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,result);
		}
		designStyle.setBranchId(branchId);
		this.designStyleService.add(designStyle);
		return ResultFactory.generateRequestResult(designStyle);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updateStyle(String id, MapContext mapContext) {
		String branchId= WebUtils.getCurrBranchId();
		//判断风格是否存在
		DesignStyle designStyle = this.designStyleService.findById(id);
		if(designStyle==null){
			return ResultFactory.generateResNotFoundResult();
		}
		//如果修改名称则判断名称是否重复
		String name = mapContext.getTypedValue("name", String.class);
		if(name!=null){
			DesignStyle oneByName = this.designStyleService.selectOneByName(name,branchId);
			if(oneByName!=null&&!oneByName.getId().equals(id)){
				Map result = new HashMap();
				result.put(WebConstant.KEY_ENTITY_NAME,AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOT_ALLOWED_REPEAT"));
				return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,result);
			}
		}
		mapContext.put(WebConstant.KEY_ENTITY_ID,id);
		this.designStyleService.updateByMapContext(mapContext);
		return ResultFactory.generateRequestResult(this.designStyleService.findById(id));
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult deleteStyle(String id) {
		String branchId= WebUtils.getCurrBranchId();
		MapContext mapContext = new MapContext();
		mapContext.put("designStyleId",id);
		mapContext.put("branchId",branchId);
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		paginatedFilter.setFilters(mapContext);
		PaginatedList<DesignSchemeDto> listByFilter = this.designSchemeService.findListByFilter(paginatedFilter);
		if(listByFilter.getRows().size()!=0){
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_RES_BE_IN_USE_10006,AppBeanInjector.i18nUtil.getMessage("BIZ_RES_BE_IN_USE_10006"));
		}
		this.designStyleService.deleteById(id);
		return ResultFactory.generateSuccessResult();
	}
}
