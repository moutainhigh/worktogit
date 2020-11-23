package com.lwxf.industry4.webapp.facade.admin.factory.product.impl;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.bizservice.common.UploadFilesService;
import com.lwxf.industry4.webapp.bizservice.customorder.CustomOrderService;
import com.lwxf.industry4.webapp.bizservice.product.*;
import com.lwxf.industry4.webapp.common.component.UploadInfo;
import com.lwxf.industry4.webapp.common.component.UploadType;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.UploadResourceType;
import com.lwxf.industry4.webapp.common.enums.product.ProductFilesType;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.customorder.OrderProductDto;
import com.lwxf.industry4.webapp.domain.dto.printTable.OrderPrintTableDto;
import com.lwxf.industry4.webapp.domain.dto.product.ProductDto;
import com.lwxf.industry4.webapp.domain.entity.product.Product;
import com.lwxf.industry4.webapp.domain.entity.product.ProductFiles;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.admin.factory.product.ProductFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/4/004 10:49
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("productFacade")
public class ProductFacadeImpl extends BaseFacadeImpl implements ProductFacade {

	@Resource(name = "productService")
	private ProductService productService;
	@Resource(name = "productColorService")
	private ProductColorService productColorService;
	@Resource(name = "productMaterialService")
	private ProductMaterialService productMaterialService;
	@Resource(name = "productCategoryService")
	private ProductCategoryService productCategoryService;
	@Resource(name = "productSpecService")
	private ProductSpecService productSpecService;
	@Resource(name = "uploadFilesService")
	private UploadFilesService uploadFilesService;
	@Resource(name = "productFilesService")
	private ProductFilesService productFilesService;
	@Resource(name = "customOrderService")
	private CustomOrderService customOrderService;

	@Override
	public RequestResult findProductList(MapContext mapContext,Integer pageNum,Integer pageSize) {
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		Pagination pagination = new Pagination();
		pagination.setPageNum(pageNum);
		pagination.setPageSize(pageSize);
		paginatedFilter.setPagination(pagination);
		mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID,WebUtils.getCurrBranchId());
		paginatedFilter.setFilters(mapContext);
		Map<String,String> created = new HashMap<String, String>();
		created.put(WebConstant.KEY_ENTITY_CREATED,"desc");
		List sort = new ArrayList();
		sort.add(created);
		paginatedFilter.setSorts(sort);
		return ResultFactory.generateRequestResult(this.productService.selectByFilter(paginatedFilter));
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addProduct(Product product) {
		//判断所选分类是否存在
		if (!this.productCategoryService.isExist(product.getProductCategoryId())){
			return ResultFactory.generateResNotFoundResult();
		}

		//判断产品型号是否重复
		if(this.productService.selectByModel(product.getModel())!=null){
			MapContext mapContext = MapContext.newOne();
			mapContext.put("model",AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOT_ALLOWED_REPEAT"));
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,mapContext);
		}
		this.productService.add(product);
		return ResultFactory.generateRequestResult(this.productService.selectProductDtoById(product.getId()));
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updateProduct(MapContext mapContext, String id) {
		//判断型号是否重复
		String model = mapContext.getTypedValue("model",String.class);
		if(model!=null&&!model.equals("")){
			Product product = this.productService.selectByModel(model);
			if(product!=null&&!product.getId().equals(id)){
				MapContext result = MapContext.newOne();
				result.put("model",AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOT_ALLOWED_REPEAT"));
				return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,result);
			}
		}
		//判断用途是否存在
		String cid = (String) mapContext.get("productCategoryId");
		if(cid!=null){
			if(this.productCategoryService.findById(cid)==null){
				return ResultFactory.generateResNotFoundResult();
			}
		}
		mapContext.put(WebConstant.KEY_ENTITY_ID,id);
		this.productService.updateByMapContext(mapContext);
		return ResultFactory.generateRequestResult(this.productService.selectProductDtoById(id));
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult uploadFileImg(String id, Integer type, List<MultipartFile> multipartFileList) {
		ProductFiles productFiles = new ProductFiles();
		productFiles.setCreated(DateUtil.getSystemDate());
		productFiles.setCreator(WebUtils.getCurrUserId());
		productFiles.setProductId(id);
		productFiles.setStatus(UploadType.FORMAL.getValue());
		boolean isOne = false;//判断是否是上传的封面图
		switch (ProductFilesType.getByValue(type)){
			case APP_COVER_MAP://app封面图
				productFiles.setType(ProductFilesType.APP_COVER_MAP.getValue());
				isOne = true;
				break;
			case APP_MASTER_GRAPH://app主图
				productFiles.setType(ProductFilesType.APP_MASTER_GRAPH.getValue());
				break;
			case PC_COVER_MAP://pc封面
				productFiles.setType(ProductFilesType.PC_COVER_MAP.getValue());
				isOne = true;
				break;
			case PC_MASTER_GRAPH://pc主图
				productFiles.setType(ProductFilesType.PC_MASTER_GRAPH.getValue());
				break;
		}
		if(isOne){//如果上传的是封面图 就删除原来的封面图
			ProductFiles files = this.productFilesService.findOneByProductIdAndType(id,type);
			if(files!=null){
				//清除本地文件
				AppBeanInjector.baseFileUploadComponent.deleteFileByDir(AppBeanInjector.configuration.getUploadFileRootDir().concat(files.getPath()));
				//清除数据库数据
				this.productFilesService.deleteById(files.getId());
			}
		}
		List fileList = new ArrayList();
		for(MultipartFile multipartFile:multipartFileList){
			//赋值给uploadinfo,数据进行处理
			UploadInfo uploadInfo = AppBeanInjector.baseFileUploadComponent.doUploadByModule(UploadType.TEMP, multipartFile, UploadResourceType.PRODUCT, id);
			productFiles.setFullPath(WebUtils.getDomainUrl() + uploadInfo.getRelativePath());
			productFiles.setMime(uploadInfo.getFileMimeType().getRealType());
			productFiles.setOriginalMime(uploadInfo.getFileMimeType().getOriginalType());
			productFiles.setName(uploadInfo.getFileName());
			productFiles.setPath(uploadInfo.getRelativePath());
			this.productFilesService.add(productFiles);
			MapContext mapContext = new MapContext();
			mapContext.put(WebConstant.KEY_ENTITY_ID,productFiles.getId());
			mapContext.put("path",productFiles.getPath());
			mapContext.put("type",type);
			fileList.add(mapContext);
		}
		return ResultFactory.generateRequestResult(fileList);
	}

	@Override
	public RequestResult findResourcesList(MapContext mapContent) {
		return ResultFactory.generateRequestResult(this.productService.findResourcesList(mapContent));
	}

	@Override
	public RequestResult findProductInfo(String id) {
		ProductDto productDto = this.productService.selectProductDtoById(id);
		MapContext mapContext = new MapContext();
		mapContext.put("product",productDto);
		mapContext.put("appCoverMap",this.productFilesService.findListByType(ProductFilesType.APP_COVER_MAP.getValue(),id));
		mapContext.put("appMasterGraph",this.productFilesService.findListByType(ProductFilesType.APP_MASTER_GRAPH.getValue(),id));
		mapContext.put("pcCoverMap",this.productFilesService.findListByType(ProductFilesType.PC_COVER_MAP.getValue(),id));
		mapContext.put("pcMasterGraph",this.productFilesService.findListByType(ProductFilesType.PC_MASTER_GRAPH.getValue(),id));
		return ResultFactory.generateRequestResult(mapContext);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult deleteFile(String productId, String fileId) {
		ProductFiles productFiles = this.productFilesService.findOneByProductIdAndId(productId,fileId);
		if(productFiles==null){
			return ResultFactory.generateSuccessResult();
		}
		//清除本地文件
		AppBeanInjector.baseFileUploadComponent.deleteFileByDir(AppBeanInjector.configuration.getUploadFileRootDir().concat(productFiles.getPath()));
		//清除数据库数据
		this.productFilesService.deleteById(fileId);
		return ResultFactory.generateSuccessResult();
	}


}
