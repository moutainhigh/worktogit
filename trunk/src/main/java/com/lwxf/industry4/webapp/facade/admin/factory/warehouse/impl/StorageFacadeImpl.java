package com.lwxf.industry4.webapp.facade.admin.factory.warehouse.impl;

import com.lwxf.industry4.webapp.bizservice.procurement.PurchaseService;
import com.lwxf.industry4.webapp.bizservice.product.ProductCategoryService;
import com.lwxf.industry4.webapp.bizservice.system.MenusService;
import com.lwxf.industry4.webapp.bizservice.system.RolePermissionService;
import com.lwxf.industry4.webapp.bizservice.user.UserService;
import com.lwxf.industry4.webapp.bizservice.warehouse.FinishedStockService;
import com.lwxf.industry4.webapp.bizservice.warehouse.StockService;
import com.lwxf.industry4.webapp.bizservice.warehouse.StorageService;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.procurement.PurchaseDto;
import com.lwxf.industry4.webapp.domain.dto.warehouse.StockDto;
import com.lwxf.industry4.webapp.domain.entity.system.Menus;
import com.lwxf.industry4.webapp.domain.entity.warehouse.FinishedStock;
import com.lwxf.industry4.webapp.domain.entity.warehouse.Storage;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.warehouse.StorageFacade;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/13/013 15:34
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("storageFacade")
public class StorageFacadeImpl extends BaseFacadeImpl implements StorageFacade {
	@Resource(name = "storageService")
	private StorageService storageService;
	@Resource(name = "productCategoryService")
	private ProductCategoryService productCategoryService;
	@Resource(name = "userService")
	private UserService userService;
	@Resource(name = "stockService")
	private StockService stockService;
	@Resource(name = "menusService")
	private MenusService menusService;
	@Resource(name = "finishedStockService")
	private FinishedStockService finishedStockService;
	@Resource(name = "rolePermissionService")
	private RolePermissionService rolePermissionService;
	@Resource(name = "purchaseService")
	private PurchaseService purchaseService;

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addStorage(Storage storage) {
        String branchId= WebUtils.getCurrBranchId();
		//判断名称是否重复
		if(this.storageService.findOneByName(storage.getName(),branchId)!=null){
			Map result = new HashMap<String,String>();
			result.put(WebConstant.KEY_ENTITY_NAME,AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOT_ALLOWED_REPEAT"));
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,result);
		}
		//判断管理员是否存在
		if(!this.userService.isExist(storage.getStorekeeper())){
			return ResultFactory.generateResNotFoundResult();
		}
		storage.setBranchId(branchId);
		this.storageService.add(storage);
		return ResultFactory.generateRequestResult(this.storageService.findOneById(storage.getId()));
	}

	@Override
	public RequestResult findStorageList(MapContext mapContext, Integer pageNum, Integer pageSize) {
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID,WebUtils.getCurrBranchId());
		Pagination pagination = new Pagination();
		pagination.setPageNum(pageNum);
		pagination.setPageSize(pageSize);
		paginatedFilter.setPagination(pagination);
		paginatedFilter.setFilters(mapContext);
		List<Map<java.lang.String,java.lang.String>> sortList= new ArrayList<Map<java.lang.String,java.lang.String>>();
		Map<java.lang.String, java.lang.String> createdSort= new HashMap<java.lang.String, java.lang.String>();
		createdSort.put(WebConstant.KEY_ENTITY_CREATED,"desc");
		sortList.add(createdSort);
		paginatedFilter.setSorts(sortList);
		return ResultFactory.generateRequestResult(this.storageService.selectByFilter(paginatedFilter));
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updateStorage(String id, MapContext mapContext) {
		String branchId=WebUtils.getCurrBranchId();
		//判断资源是否存在
		Storage storage = this.storageService.findById(id);
		if(storage==null){
			return ResultFactory.generateResNotFoundResult();
		}
		String name = mapContext.getTypedValue("name", String.class);
		if (name!=null&&!name.trim().equals("")){
			Storage byName = this.storageService.findOneByName(name,branchId);
			if(byName!=null&&!byName.getId().equals(id)){
				Map result = new HashMap();
				result.put(WebConstant.KEY_ENTITY_NAME,AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOT_ALLOWED_REPEAT"));
				return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,result);
			}
		}
		String storekeeper = mapContext.getTypedValue("storekeeper", String.class);
		if(storekeeper!=null){
			if(!this.userService.isExist(storekeeper)){
				return ResultFactory.generateResNotFoundResult();
			}
		}
		mapContext.put(WebConstant.KEY_ENTITY_ID,id);
		this.storageService.updateByMapContext(mapContext);
		return ResultFactory.generateRequestResult(this.storageService.findOneById(id));
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult deleteById(String id) {
		//判断该资源是否存在
		Storage storage = this.storageService.findById(id);
		if(storage==null){
			return ResultFactory.generateSuccessResult();
		}
		//判断是否被库存表使用
		MapContext mapContext = MapContext.newOne();
		mapContext.put(WebConstant.KEY_ENTITY_ID,id);
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		paginatedFilter.setFilters(mapContext);
		PaginatedList<StockDto> filter = this.stockService.findListByFilter(paginatedFilter);
		if(filter.getRows()!=null&&filter.getRows().size()!=0){
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_RES_BE_IN_USE_10006,AppBeanInjector.i18nUtil.getMessage("BIZ_RES_BE_IN_USE_10006"));
		}

		//判断是否存在采购单
		MapContext productMapContext = new MapContext();
		productMapContext.put("storageId",id);
		paginatedFilter.setFilters(productMapContext);
		PaginatedList<PurchaseDto> purchaseDtoPaginatedList = this.purchaseService.selectByFilter(paginatedFilter);
		if(purchaseDtoPaginatedList.getRows()!=null&&purchaseDtoPaginatedList.getRows().size()!=0){
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_RES_BE_IN_USE_10006,AppBeanInjector.i18nUtil.getMessage("BIZ_RES_BE_IN_USE_10006"));
		}

		//判断是否被成品库使用
		List<FinishedStock> finishedStockList = this.finishedStockService.findListByStorageId(id);
		if(finishedStockList!=null&&finishedStockList.size()!=0){
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_RES_BE_IN_USE_10006,AppBeanInjector.i18nUtil.getMessage("BIZ_RES_BE_IN_USE_10006"));
		}
		this.storageService.deleteById(id);

		//删除仓库时 删除所有使用该仓库菜单的
		List<Menus> menusList = this.menusService.findListByLikeKey(storage.getId());
		for(Menus menus:menusList){
			//删除使用该菜单的 角色权限
			this.rolePermissionService.deleteByKey(menus.getKey());
			//删除菜单
			this.menusService.deleteById(menus.getId());
		}
		return ResultFactory.generateSuccessResult();
	}

	@Override
	public RequestResult findAllProduct() {
		List<StockDto> stockDtos= this.storageService.findAllProduct();
		return ResultFactory.generateRequestResult(stockDtos);
	}
}
