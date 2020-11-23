package com.lwxf.industry4.webapp.facade.admin.factory.supplier.impl;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.bizservice.common.UploadFilesService;
import com.lwxf.industry4.webapp.bizservice.supplier.MaterialService;
import com.lwxf.industry4.webapp.bizservice.supplier.SupplierProductService;
import com.lwxf.industry4.webapp.bizservice.supplier.SupplierService;
import com.lwxf.industry4.webapp.common.aop.syslog.OperationMoudule;
import com.lwxf.industry4.webapp.common.aop.syslog.OperationType;
import com.lwxf.industry4.webapp.common.aop.syslog.SysOperationLog;
import com.lwxf.industry4.webapp.common.component.UploadInfo;
import com.lwxf.industry4.webapp.common.component.UploadType;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.UploadResourceType;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.supplier.SupplierDto;
import com.lwxf.industry4.webapp.domain.dto.supplier.SupplierDtoFowWx;
import com.lwxf.industry4.webapp.domain.entity.common.UploadFiles;
import com.lwxf.industry4.webapp.domain.entity.supplier.Material;
import com.lwxf.industry4.webapp.domain.entity.supplier.SupplierProduct;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.supplier.SupplierFacade;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.*;

/**
 * 功能：
 *
 * @author：zhangxiaolin(F_baisi)
 * @create：2019/7/29/10 15:25
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("supplierFacade")
public class SupplierFacadeImpl extends BaseFacadeImpl implements SupplierFacade {
	@Resource(name = "supplierService")
	private SupplierService supplierService;
	@Resource(name = "supplierProductService")
	private SupplierProductService supplierProductService;
	@Resource(name = "uploadFilesService")
	private UploadFilesService uploadFilesService;
	@Resource(name = "materialService")
	private MaterialService materialService;

	@Override
	public RequestResult findAllSupplierList(Integer pageNum, Integer pageSize, MapContext mapContext) {
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		Pagination pagination = new Pagination();
		pagination.setPageNum(pageNum);
		pagination.setPageSize(pageSize);
		paginatedFilter.setPagination(pagination);
		paginatedFilter.setFilters(mapContext);
		Map<String,String> created = new HashMap<String, String>();
		created.put(WebConstant.KEY_ENTITY_CREATED,"desc");
		List sort = new ArrayList();
		sort.add(created);
		paginatedFilter.setSorts(sort);
		PaginatedList<SupplierDtoFowWx> list = this.supplierService.selectDtoByFilter(paginatedFilter);
		MapContext mapparam = MapContext.newOne();
		for(SupplierDtoFowWx obj : list.getRows()){
			//给供应商绑定附件
			List<UploadFiles> supplierFiles = uploadFilesService.findByResourceId(obj.getId());
			obj.setFiles(supplierFiles);
			//给供应商绑定产品
			mapparam.put("supplierId",obj.getId());
			mapparam.put("branchId",mapContext.get("branchId"));
			List<SupplierProduct> products = supplierProductService.selectAllSupplierProduct(mapparam);
			//List<MaterialDto> materialDtos=this.materialService.findAllMaterials(mapparam);
			//给供应商产品绑定附件
			for(SupplierProduct ma : products){
				List<UploadFiles> prodFiles = uploadFilesService.findByResourceId(ma.getId());
				ma.setFiles(prodFiles);
			}
			obj.setListProduct(products);
		}
		return ResultFactory.generateRequestResult(this.supplierService.selectDtoByFilter(paginatedFilter));
	}
	@Override
	public RequestResult countSupplierToday() {
		return ResultFactory.generateRequestResult(this.supplierService.countSupplierToday(WebUtils.getCurrBranchId()));
	}




	@Override
	@Transactional(value = "transactionManager")
	@SysOperationLog(detail = "创建供应商",operationType = OperationType.INSERT,operationMoudule = OperationMoudule.SUPPLIER)
	public RequestResult addSupplier(SupplierDto supplier) {
		supplier.setCreated(new Date());
		supplier.setCreator(WebUtils.getCurrUserId());
		supplier.setBranchId(WebUtils.getCurrBranchId());
		supplier.setStatus(1); //0是未启用，1为启用
		if(supplier.getSignTime()==null||supplier.getSignTime().equals("")){
			supplier.setSignTime(null);
		}
		if(supplier.getEndSignTime()==null||supplier.getEndSignTime().equals("")){
			supplier.setEndSignTime(null);
		}
		this.supplierService.add(supplier);
		//插入产品信息
		if(supplier.getProducts()!=null){
			for(SupplierProduct sp : supplier.getProducts()){
				sp.setCreated(new Date());
				sp.setCreator(WebUtils.getCurrUserId());
				sp.setSupplierId(supplier.getId());
				supplierProductService.add(sp);
			}
		}
//		if(supplier.getMaterialIds()!=null){
//			for(String id  : supplier.getMaterialIds()){
//				SupplierProduct sp=new SupplierProduct();
//				sp.setCreated(new Date());
//				sp.setCreator(WebUtils.getCurrUserId());
//				sp.setSupplierId(supplier.getId());
//				sp.setMaterialId(id);
//				supplierProductService.add(sp);
//			}
//		}
		//更新图片信息为正常
		if(supplier.getFileIds()!=null && !supplier.getFileIds().equals("")){
			String[] ids = supplier.getFileIds().split(",");
			for(String file_id: ids){
				MapContext map = MapContext.newOne();
				map.put("id",file_id);
				map.put("status",1);
				map.put("resourceId",supplier.getId());
				this.uploadFilesService.updateByMapContext(map);
			}
		}
		return ResultFactory.generateRequestResult(supplier);
	}

	@Override
	@Transactional(value = "transactionManager")
	@SysOperationLog(detail = "创建供应商产品",operationType = OperationType.INSERT,operationMoudule = OperationMoudule.SUPPLIER_PRODUCT)
	public RequestResult addSupplierProduct(String supplierId,List<SupplierProduct> supplierProducts) {
		MapContext mapContext=MapContext.newOne();
		if(supplierProducts!=null&&supplierProducts.size()>0){
			for (SupplierProduct supplierProduct:supplierProducts) {
				String materialId=supplierProduct.getMaterialId();
				if(materialId==null||materialId.trim().equals("")){
					Material material=new Material();
					material.setBranchId(WebUtils.getCurrBranchId());
					material.setColor(supplierProduct.getColor());
					material.setName(supplierProduct.getName());
					material.setCreated(new Date());
					material.setCreator(WebUtils.getCurrUserId());
					material.setMaterialStatus(0);
					material.setMaterialLevel(supplierProduct.getMaterialLevel());
					this.materialService.add(material);
				}
					else {
					SupplierProduct supplierProduct1=this.supplierProductService.findOneBySupplierAndProductId(supplierId,materialId);
					if(supplierProduct1==null){
						supplierProduct.setCreated(new Date());
						supplierProduct.setCreator(WebUtils.getCurrUserId());
						supplierProduct.setSupplierId(supplierId);
						this.supplierProductService.add(supplierProduct);
				}else {
						String productId=supplierProduct1.getId();
						mapContext.put("id",productId);
						mapContext.put("price",supplierProduct.getPrice());
						mapContext.put("notes",supplierProduct.getNotes());
						mapContext.put("supplyTime",supplierProduct.getSupplyTime());
						mapContext.put("minCount",supplierProduct.getMinCount());
						mapContext.put("minMoney",supplierProduct.getMinMoney());
						this.supplierProductService.updateByMapContext(mapContext);
					}

				}
			}
		}
		return  ResultFactory.generateSuccessResult();
	}

	@Override
	public RequestResult viewSupplierInfo(String id) {
		SupplierDtoFowWx dto = supplierService.selectDtoById(id);
		MapContext map = MapContext.newOne();
		map.put("supplierId",id);
		dto.setFiles(uploadFilesService.findByResourceId(id));
		List<SupplierProduct> spList =supplierProductService.selectAllSupplierProduct(map);
		//List<MaterialDto> materialDtos=this.materialService.findAllMaterials(map);
		dto.setFiles(uploadFilesService.findByResourceId(id));
		dto.setListProduct(spList);
		return ResultFactory.generateRequestResult(dto);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult uploadImage(String userId, List<MultipartFile> multipartFileList) {
		//添加图片到本地
		List<MapContext> listUrls = new ArrayList<>();
		if(multipartFileList!=null && multipartFileList.size()>0){
			for(MultipartFile multipartFile:multipartFileList){
				UploadInfo uploadInfo = AppBeanInjector.baseFileUploadComponent.doUploadByModule(UploadType.FORMAL, multipartFile, UploadResourceType.SUPPLIER_PRODUCT_FILE, "cover");
				//添加图片到数据库
				UploadFiles uploadFiles = new UploadFiles();
				uploadFiles.setCreated(DateUtil.getSystemDate());
				uploadFiles.setFullPath(WebUtils.getDomainUrl() + uploadInfo.getRelativePath());
				uploadFiles.setMime(uploadInfo.getFileMimeType().getRealType());
				uploadFiles.setName(uploadInfo.getFileName());
				uploadFiles.setOriginalMime(uploadInfo.getFileMimeType().getOriginalType());
				uploadFiles.setPath(uploadInfo.getRelativePath());
				uploadFiles.setStatus(0);
				uploadFiles.setCreator(userId);
				uploadFiles.setResourceType(UploadResourceType.SUPPLIER_PRODUCT_FILE.getType());
				this.uploadFilesService.add(uploadFiles);
				MapContext map = MapContext.newOne();
				map.put("imgFullPath",uploadFiles.getFullPath());
				map.put("imgRelPath",uploadInfo.getRelativePath());
				map.put("fileId",uploadFiles.getId());
				listUrls.add(map);
			}
		}
		return ResultFactory.generateRequestResult(listUrls);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult uploadProductImage(String supplierId, MultipartFile multipartFile) {
		//添加图片到本地
		List<MapContext> listUrls = new ArrayList<>();
		UploadInfo uploadInfo = AppBeanInjector.baseFileUploadComponent.doUploadByModule(UploadType.FORMAL, multipartFile, UploadResourceType.SUPPLIER_PRODUCT_FILE, "cover");
		//添加图片到数据库
		UploadFiles uploadFiles = new UploadFiles();
		uploadFiles.setCreated(DateUtil.getSystemDate());
		uploadFiles.setFullPath(WebUtils.getDomainUrl() + uploadInfo.getRelativePath());
		uploadFiles.setMime(uploadInfo.getFileMimeType().getRealType());
		uploadFiles.setName(uploadInfo.getFileName());
		uploadFiles.setOriginalMime(uploadInfo.getFileMimeType().getOriginalType());
		uploadFiles.setPath(uploadInfo.getRelativePath());
		uploadFiles.setStatus(0);
		uploadFiles.setCreator(WebUtils.getCurrUserId());
		uploadFiles.setResourceType(UploadResourceType.SUPPLIER_PRODUCT_FILE.getType());
		uploadFiles.setResourceId(supplierId);
		this.uploadFilesService.add(uploadFiles);
		MapContext map = MapContext.newOne();
		map.put("imgFullPath",uploadFiles.getFullPath());
		map.put("imgRelPath",uploadInfo.getRelativePath());
		map.put("fileId",uploadFiles.getId());
		return ResultFactory.generateRequestResult(listUrls);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updateSupplier(String supplierId, MapContext map) {
		map.put("id",supplierId);
		if(map.getTypedValue("signTime",String.class)==null||map.getTypedValue("signTime",String.class).equals("")){
			map.put("signTime",null);
		}
		if(map.getTypedValue("endSignTime",String.class)==null||map.getTypedValue("endSignTime",String.class).equals("")){
			map.put("endSignTime",null);
		}
		return ResultFactory.generateRequestResult(this.supplierService.updateByMapContext(map));
	}

	@Override
	@Transactional(value = "transactionManager")
	@SysOperationLog(detail = "删除供应商信息",operationType = OperationType.DELETE,operationMoudule = OperationMoudule.SUPPLIER)
	public RequestResult deleteSupplier(String supplierId) {
		supplierProductService.deleteBySupplierId(supplierId);
		return ResultFactory.generateRequestResult(this.supplierService.deleteById(supplierId));
	}

	@Override
	@Transactional(value = "transactionManager")
	@SysOperationLog(detail = "更新供应商产品信息",operationType = OperationType.UPDATE,operationMoudule = OperationMoudule.SUPPLIER_PRODUCT)
	public RequestResult updateSupplierProduct(String prodId, MapContext map) {
//		SupplierProduct supplierProduct=this.supplierProductService.findOneBySupplierAndProductId(supplierId,prodId);
//		if(supplierProduct==null){
//			return ResultFactory.generateResNotFoundResult();
//		}
		map.put("id",prodId);
		return ResultFactory.generateRequestResult(supplierProductService.updateByMapContext(map));
	}

	@Override
	public RequestResult findSupplierProducts(Integer pageNum, Integer pageSize, String supplierId, String prodId, String prodName) {
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		Pagination pagination = new Pagination();
		pagination.setPageNum(pageNum);
		pagination.setPageSize(pageSize);
		paginatedFilter.setPagination(pagination);
		MapContext mapContext=MapContext.newOne();
		if(supplierId!=null&&!supplierId.equals("")){
			mapContext.put("supplierId",supplierId);
		}
		if(prodId!=null&&!prodId.equals("")){
			mapContext.put("prodId",prodId);
		}
		if(prodName!=null&&!prodName.equals("")){
			mapContext.put("prodName",prodName);
		}
		paginatedFilter.setFilters(mapContext);
		Map<String,String> created = new HashMap<String, String>();
		created.put(WebConstant.KEY_ENTITY_CREATED,"desc");
		List sort = new ArrayList();
		sort.add(created);
		paginatedFilter.setSorts(sort);
		PaginatedList<Map> list = this.supplierService.findSupplierProducts(paginatedFilter);
		return ResultFactory.generateRequestResult(list);
	}


	@Override
	@Transactional(value = "transactionManager")
	@SysOperationLog(detail = "删除供应商产品信息",operationType = OperationType.DELETE,operationMoudule = OperationMoudule.SUPPLIER_PRODUCT)
	public RequestResult deleteSupplierProduct(String prodId) {
//		SupplierProduct supplierProduct=this.supplierProductService.findOneBySupplierAndProductId(supplierId,prodId);
//		if(supplierProduct==null){
//			return ResultFactory.generateResNotFoundResult();
//		}
		String id=prodId;
		return ResultFactory.generateRequestResult(supplierProductService.deleteById(id));
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult deleteSupplierImages(String imgId) {
		return ResultFactory.generateRequestResult(uploadFilesService.deleteById(imgId));
	}
}
