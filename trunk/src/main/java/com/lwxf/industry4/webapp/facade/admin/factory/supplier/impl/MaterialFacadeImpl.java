package com.lwxf.industry4.webapp.facade.admin.factory.supplier.impl;

import javax.annotation.Resource;

import java.util.*;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.utils.DataValidatorUtils;
import com.lwxf.commons.utils.DateUtil;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.bizservice.common.UploadFilesService;
import com.lwxf.industry4.webapp.bizservice.supplier.MaterialService;
import com.lwxf.industry4.webapp.common.aop.syslog.OperationMoudule;
import com.lwxf.industry4.webapp.common.aop.syslog.OperationType;
import com.lwxf.industry4.webapp.common.aop.syslog.SysOperationLog;
import com.lwxf.industry4.webapp.common.component.UploadInfo;
import com.lwxf.industry4.webapp.common.component.UploadType;
import com.lwxf.industry4.webapp.common.enums.UploadResourceType;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.customer.WxCustomerDto;
import com.lwxf.industry4.webapp.domain.dto.supplier.MaterialDto;
import com.lwxf.industry4.webapp.domain.entity.common.UploadFiles;
import com.lwxf.industry4.webapp.domain.entity.supplier.Supplier;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.supplier.MaterialFacade;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/8/1 0001 11:38
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("materialFacade")
public class MaterialFacadeImpl extends BaseFacadeImpl implements MaterialFacade {
	@Resource(name = "materialService")
	private MaterialService  materialService;

	@Resource(name = "uploadFilesService")
	private UploadFilesService uploadFilesService;
	/**
	 * 原料列表
	 * @param mapContext
	 * @return
	 */
	@Override
	public RequestResult findMaterialList(Integer pageNum,Integer pageSize,MapContext mapContext) {
		//分页查询处理
		Pagination pagination = new Pagination();
		pagination.setPageNum(pageNum);
		pagination.setPageSize(pageSize);
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		paginatedFilter.setPagination(pagination);
		paginatedFilter.setFilters(mapContext);
		Map dataSort = new HashMap();
		List sorts = new ArrayList();
		dataSort.put("created", "desc");
		sorts.add(dataSort);
		paginatedFilter.setSorts(sorts);
		PaginatedList<MaterialDto> materialList = this.materialService.findMaterialList(paginatedFilter);
		MapContext result=MapContext.newOne();
		result.put("materialList",materialList.getRows());
		return ResultFactory.generateRequestResult(result,materialList.getPagination());
	}

	/**
	 * 原材料详情
	 * @param materialId
	 * @return
	 */
	@Override
	public RequestResult findMaterialInfo(String materialId) {
		MaterialDto materialDto=this.materialService.findMaterialInfoById(materialId);
		String ResourcedId=materialId;
		Integer status=1;
		List<UploadFiles> uploadFilesList=this.uploadFilesService.findByResourceIdAndStatusAndTypeId(ResourcedId,status);
		materialDto.setFiles(uploadFilesList);
		return ResultFactory.generateRequestResult(materialDto);
	}

	/**
	 * 添加原材料
	 * @param materialDto
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager")
	@SysOperationLog(detail = "添加原材料",operationType = OperationType.INSERT,operationMoudule = OperationMoudule.MATERIAL)
	public RequestResult addMaterial(MaterialDto materialDto) {
		String name=materialDto.getName();
		String color=materialDto.getColor();
		Integer lv=materialDto.getMaterialLevel();
		String size=materialDto.getMaterialSize();
		MaterialDto material=this.materialService.findByNameAndColorAndLvAndSize(name,color,lv,size);
		if(material!=null){
			return ResultFactory.generateSuccessResult();
		}
		Date created= DateUtil.getSystemDate();
		String creator=WebUtils.getCurrUserId();
		materialDto.setCreator(creator);
		materialDto.setCreated(created);
		materialDto.setMaterialStatus(0);
		materialDto.setBranchId(WebUtils.getCurrBranchId());
		this.materialService.add(materialDto);
		//修改产品图片状态为正常
		if(materialDto.getFileIds()!=null && !materialDto.getFileIds().equals("")){
			String[] ids = materialDto.getFileIds().split(",");
			for(String file_id: ids){
				MapContext map = MapContext.newOne();
				map.put("id",file_id);
				map.put("status",1);
				map.put("resourceId",materialDto.getId());
				this.uploadFilesService.updateByMapContext(map);
			}
		}
		return ResultFactory.generateRequestResult(materialDto);
	}

	/**
	 * 修改原材料
	 * @param material
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager")
	@SysOperationLog(detail = "修改原材料",operationType = OperationType.UPDATE,operationMoudule = OperationMoudule.MATERIAL)
	public RequestResult updateMaterial(String materialId,MapContext material) {
		Map<String, String> validResult = new HashMap<>();
		if(material.containsKey("materialLevel")) {
			if (!DataValidatorUtils.isInteger1(material.getTypedValue("materialLevel",String.class))) {
				validResult.put("materialLevel", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(material.containsKey("name")) {
			if (LwxfStringUtils.getStringLength(material.getTypedValue("name",String.class)) > 40) {
				validResult.put("name", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(material.containsKey("color")) {
			if (LwxfStringUtils.getStringLength(material.getTypedValue("color",String.class)) > 20) {
				validResult.put("color", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(material.containsKey("materialSize")) {
			if (LwxfStringUtils.getStringLength(material.getTypedValue("materialSize",String.class)) > 100) {
				validResult.put("materialSize", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(material.containsKey("categoryId")) {
			if (LwxfStringUtils.getStringLength(material.getTypedValue("categoryId",String.class)) > 100) {
				validResult.put("categoryId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}
		String id=materialId;
		material.put("id",id);
		this.materialService.updateByMapContext(material);
		return ResultFactory.generateRequestResult(material);
	}

	/**
	 * 删除原材料
	 * @param materialId
	 * @return
	 */

	@Override
	@Transactional(value = "transactionManager")
	@SysOperationLog(detail = "删除原材料",operationType = OperationType.DELETE,operationMoudule = OperationMoudule.MATERIAL)
	public RequestResult deleteMaterial(String materialId) {
		this.materialService.deleteById(materialId);
		return ResultFactory.generateSuccessResult();
	}



	@Override
	public RequestResult findSuppliersByMaterialId(String materialId) {
		List<Supplier> supplierDtos=this.materialService.findSuppliersByMaterialId(materialId);
		return ResultFactory.generateRequestResult(supplierDtos);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult materialImages(List<MultipartFile> multipartFileList) {
		//添加图片到本地
		List<MapContext> listUrls = new ArrayList<>();
		if(multipartFileList!=null && multipartFileList.size()>0){
			for(MultipartFile multipartFile:multipartFileList){
				UploadInfo uploadInfo = AppBeanInjector.baseFileUploadComponent.doUploadByModule(UploadType.FORMAL, multipartFile, UploadResourceType.MATERIAL, "cover");
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


}
