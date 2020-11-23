package com.lwxf.industry4.webapp.facade.admin.factory.design.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lwxf.industry4.webapp.bizservice.design.DesignSchemeService;
import com.lwxf.industry4.webapp.bizservice.design.DoorStateService;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.design.DesignSchemeDto;
import com.lwxf.industry4.webapp.domain.entity.design.DoorState;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.admin.factory.design.DoorStateFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/3/29/029 13:16
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("doorStateFacade")
public class DoorStateFacadeImpl extends BaseFacadeImpl implements DoorStateFacade {

	@Resource(name = "doorStateService")
	private DoorStateService doorStateService;
	@Resource(name = "designSchemeService")
	private DesignSchemeService designSchemeService;

	@Override
	public RequestResult findDoorList(String name, Integer pageNum, Integer pageSize) {
		MapContext mapContext = new MapContext();
		if(name!=null){
			mapContext.put(WebConstant.KEY_ENTITY_NAME,name);
		}
		String branchId= WebUtils.getCurrBranchId();
		mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID,branchId);
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		paginatedFilter.setFilters(mapContext);
		Pagination pagination = new Pagination();
		pagination.setPageSize(pageSize);
		pagination.setPageNum(pageNum);
		paginatedFilter.setPagination(pagination);
		return ResultFactory.generateRequestResult(this.doorStateService.selectByFilter(paginatedFilter));
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addDoorState(DoorState doorState) {
		//企业id
		String branchId= WebUtils.getCurrBranchId();
		//判断名称是否重复
		MapContext mapContext=MapContext.newOne();
		mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID,branchId);
		mapContext.put("name",doorState.getName());
		DoorState byName= this.doorStateService.findByName(mapContext);
		if(byName!=null){
			MapContext error = new MapContext();
			error.put(WebConstant.KEY_ENTITY_NAME,AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOT_ALLOWED_REPEAT"));
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,error);
		}
		doorState.setBranchId(branchId);
		this.doorStateService.add(doorState);
		return ResultFactory.generateRequestResult(doorState);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updateDoorState(String id, MapContext mapContext) {
		//企业id
		String branchId= WebUtils.getCurrBranchId();
		//判断户型是否存在
		DoorState doorState = this.doorStateService.findById(id);
		if(doorState==null){
			return ResultFactory.generateResNotFoundResult();
		}
		String name = mapContext.getTypedValue(WebConstant.KEY_ENTITY_NAME, String.class);
		if(name!=null){
			//判断名称是否重复
			MapContext params=MapContext.newOne();
			params.put(WebConstant.KEY_ENTITY_BRANCH_ID,branchId);
			params.put("name",doorState.getName());
			DoorState byName= this.doorStateService.findByName(params);
			if(byName!=null&&!byName.getId().equals(id)){
				MapContext error = new MapContext();
				error.put(WebConstant.KEY_ENTITY_NAME,AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOT_ALLOWED_REPEAT"));
				return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,error);
			}
		}
		mapContext.put(WebConstant.KEY_ENTITY_ID,id);
		this.doorStateService.updateByMapContext(mapContext);
		return ResultFactory.generateRequestResult(this.doorStateService.findById(id));
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult deleteDoorState(String id) {
		//企业id
		String branchId= WebUtils.getCurrBranchId();
		MapContext mapContext = new MapContext();
		mapContext.put("doorState",id);
		mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID,branchId);
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		paginatedFilter.setFilters(mapContext);
		PaginatedList<DesignSchemeDto> schemeDtoPaginatedList = this.designSchemeService.findListByFilter(paginatedFilter);
		if(schemeDtoPaginatedList.getRows().size()!=0){
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_RES_BE_IN_USE_10006,AppBeanInjector.i18nUtil.getMessage("BIZ_RES_BE_IN_USE_10006"));
		}
		this.doorStateService.deleteById(id);
		return ResultFactory.generateSuccessResult();
	}
}
