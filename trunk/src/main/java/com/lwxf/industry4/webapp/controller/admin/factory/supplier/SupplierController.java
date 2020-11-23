package com.lwxf.industry4.webapp.controller.admin.factory.supplier;

import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.json.JsonMapper;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.FileMimeTypeUtil;
import com.lwxf.industry4.webapp.common.utils.LoginUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.supplier.MaterialDto;
import com.lwxf.industry4.webapp.domain.dto.supplier.SupplierDto;
import com.lwxf.industry4.webapp.domain.dto.supplier.SupplierDtoFowWx;
import com.lwxf.industry4.webapp.domain.entity.supplier.Supplier;
import com.lwxf.industry4.webapp.domain.entity.supplier.SupplierProduct;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.supplier.SupplierFacade;
import com.lwxf.mybatis.utils.MapContext;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能：
 *
 * @author：zhangxiaolin(F_baisi)
 * @create：2019/07/10 15:22
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Api(value="SupplierController",tags={"F端后台管理接口:供应商信息管理"})
@RestController
@RequestMapping(value = "/api/f/",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class SupplierController {
	@Resource(name = "supplierFacade")
	private SupplierFacade supplierFacade;

	/**
	 * 查询供应商列表
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@ApiResponse(code = 200, message = "查询成功")
	@ApiOperation(value = "供应商信息列表", notes = "",response = SupplierDtoFowWx.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "area", value = "地区名称", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "companyName", value = "供应商公司名称", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "leaderTel", value = "联系电话", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "type", value = "类型id basecode表里的id", dataType = "string", paramType = "query" ),
			@ApiImplicitParam(name = "keywords", value = "关键字：供应商名称、联系人名称、联系人电话", dataType = "string", paramType = "query" ),
			@ApiImplicitParam(name = "status", value = "状态:0：禁用，1：启用", dataType = "integer", paramType = "query" ),
			@ApiImplicitParam(name = "supplierStage", value = "类型:0：意向，1：签约", dataType = "integer", paramType = "query" ),
	})
	@GetMapping("suppliers")
	private RequestResult findAllSupplierList(@RequestParam(required = false) String area,
											  @RequestParam(required = false) String companyName,
											  @RequestParam(required = false) String leaderTel,
											  @RequestParam(required = false) String status,
											  @RequestParam(required = false) String supplierStage,
											  @RequestParam(required = false) String type,
											  @RequestParam(required = false) String keywords,
											  @RequestParam(required = false) Integer pageNum,
											  @RequestParam(required = false) Integer pageSize,
											  HttpServletRequest request){
		if(null == pageSize){
			pageSize = AppBeanInjector.configuration.getPageSizeLimit();
		}
		if(null == pageNum){
			pageNum = 1;
		}
		MapContext mapContext = this.createdMapContent(area,companyName,leaderTel,type,status,keywords,supplierStage);
		mapContext.put("branchId", WebUtils.getCurrBranchId());
		return this.supplierFacade.findAllSupplierList(pageNum,pageSize,mapContext);
	}

	/**
	 * 供应商上侧统计
	 * @return
	 */
	@ApiResponse(code = 200, message = "查询成功")
	@ApiOperation(value="供应商统计",notes="供应商统计")
	@GetMapping("countSuppliers")
	private RequestResult viewSupplierCount(){
		return this.supplierFacade.countSupplierToday();
	}


	private MapContext createdMapContent(String area,String companyName,String leaderTel,String type,String status,String keywords,String supplierStage) {
		MapContext mapContext = new MapContext();
		if(area!=null&&!area.trim().equals("")){
			mapContext.put("area",area);
		}
		if(companyName!=null&&!companyName.trim().equals("")){
			mapContext.put("supplierName",companyName);
		}
		if(leaderTel!=null&&!leaderTel.trim().equals("")){
			mapContext.put("supplierPhone",leaderTel);
		}
		if(type!=null&&!type.trim().equals("")){
			mapContext.put("categoryId",type);
		}
		if(supplierStage!=null&&!supplierStage.trim().equals("")){
			mapContext.put("supplierStage",supplierStage);
		}
		if(status!=null&&!status.trim().equals("")){
			mapContext.put("status",status);
		}
		if(status!=null&&!status.trim().equals("")){
			mapContext.put("supplierStage",status);
		}
		if(keywords!=null&&!keywords.trim().equals("")){
			mapContext.put("keywords",keywords);
		}
		return mapContext;
	}

	/**
	 * 新增供应商
	 * @return
	 */

	@ApiResponse(code = 200, message = "查询成功")
	@ApiOperation(value="添加供应商",notes="添加供应商信息",response = Supplier.class)
	@PostMapping("suppliers")
	private RequestResult addSupplier(@RequestBody SupplierDto supplier, HttpServletRequest request){
		return this.supplierFacade.addSupplier(supplier);
	}

	/**
	 * 新增供应商产品
	 * @return
	 */
//	@ApiResponse(code = 200, message = "查询成功")
//	@ApiOperation(value="添加供应商产品",notes="添加供应商产品信息",response = SupplierProduct.class)
//	@PostMapping("suppliers/{supplierId}/product")
//	private RequestResult addSupplierProduct(@PathVariable String supplierId,
//											 @RequestBody SupplierProduct supplierProduct){
//		supplierProduct.setSupplierId(supplierId);
//		return this.supplierFacade.addSupplierProduct(supplierProduct);
//	}
	@ApiResponse(code = 200, message = "查询成功")
	@ApiOperation(value="添加供应商产品",notes="添加供应商产品信息",response = MaterialDto.class)
	@PostMapping("suppliers/{supplierId}/product")
	private RequestResult addSupplierProduct(@PathVariable String supplierId,
											 @RequestBody List<SupplierProduct> supplierProducts){
		return this.supplierFacade.addSupplierProduct(supplierId,supplierProducts);
	}

	/**
	 * 根据ID查询供应商详情
	 * @return
	 */
	@ApiResponse(code = 200, message = "查询成功")
	@ApiOperation(value="查看供应商详情",notes="查看供应商详情",response = SupplierDtoFowWx.class)
	@GetMapping("/suppliers/{supplierId}")
	@ApiImplicitParam(name = "supplierId", value = "供应商id", dataType = "string", paramType = "path")
	private RequestResult findSupplierInfo(@PathVariable String supplierId,HttpServletRequest request){
		RequestResult result=this.supplierFacade.viewSupplierInfo(supplierId);
		return result;
	}

	/**
	 * 附件图片上传
	 * @param multipartFileList 多个附件
	 * @return
	 */
	@ApiOperation(value="附件图片上传",notes="返回值样例:{ \"code\": \"200\", \"data\": { \"ids\": [ \"4tuqvoqutzpc\", \"4tuqvovumolc\" ] } }")
	@PostMapping(value = "/suppliers/uploadImages")
	public RequestResult uploadImages(@RequestBody List<MultipartFile> multipartFileList, HttpServletRequest request){
		String atoken=request.getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
		if(uid==null){
			return ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
		}
		Map<String, Object> errorInfo = new HashMap<>();
		if(multipartFileList!=null && multipartFileList.size()>0) {
			for (MultipartFile multipartFile : multipartFileList) {
				if (multipartFile == null) {
					errorInfo.put("multipartFile", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
				}
				if (!FileMimeTypeUtil.isLegalImageType(multipartFile)) {
					errorInfo.put("multipartFile", AppBeanInjector.i18nUtil.getMessage("VALIDATE_ILLEGAL_ARGUMENT"));
				}
				if (multipartFile.getSize() > 1024L * 1024L * AppBeanInjector.configuration.getUploadBackgroundMaxsize()) {
					return ResultFactory.generateErrorResult(ErrorCodes.BIZ_FILE_SIZE_LIMIT_10031, LwxfStringUtils.format(AppBeanInjector.i18nUtil.getMessage("BIZ_FILE_SIZE_LIMIT_10031"), AppBeanInjector.configuration.getUploadBackgroundMaxsize()));
				}
				if (errorInfo.size() > 0) {
					return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, errorInfo);
				}
			}
		}
		return this.supplierFacade.uploadImage(uid,multipartFileList);
	}

	/**
	 * 产品图片上传
	 * @return
	 */
	@ApiOperation(value="附件图片上传",notes="返回值样例:{ \"code\": \"200\", \"data\": { \"ids\": [ \"4tuqvoqutzpc\", \"4tuqvovumolc\" ] } }")
	@PostMapping(value = "/suppliers/uploadProductImages")
	public RequestResult uploadProductImages(@RequestParam(required = false) String supplierId,@RequestBody List<MultipartFile> multipartFileList, HttpServletRequest request){
		Map<String, Object> errorInfo = new HashMap<>();
		if (multipartFileList == null) {
			errorInfo.put("multipartFile", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (!FileMimeTypeUtil.isLegalImageType(multipartFileList.get(0))) {
			errorInfo.put("multipartFile", AppBeanInjector.i18nUtil.getMessage("VALIDATE_ILLEGAL_ARGUMENT"));
		}
		if (multipartFileList.get(0).getSize() > 1024L * 1024L * AppBeanInjector.configuration.getUploadBackgroundMaxsize()) {
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_FILE_SIZE_LIMIT_10031, LwxfStringUtils.format(AppBeanInjector.i18nUtil.getMessage("BIZ_FILE_SIZE_LIMIT_10031"), AppBeanInjector.configuration.getUploadBackgroundMaxsize()));
		}
		if (errorInfo.size() > 0) {
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, errorInfo);
		}
		return this.supplierFacade.uploadProductImage(supplierId,multipartFileList.get(0));
	}

	/**
	 * 供应商更新
	 * @return
	 */
	@ApiOperation(value="更新供应商信息",notes="更新供应商信息")
	@PutMapping(value = "suppliers/{supplierId}")
	public String updateSupplier(@PathVariable String supplierId,
									  @RequestBody MapContext map, HttpServletRequest request) {
		JsonMapper jsonMapper=new JsonMapper();
		return jsonMapper.toJson(this.supplierFacade.updateSupplier(supplierId,map));
	}

	/**
	 * 删除供应商
	 * @return
	 */
	@ApiOperation(value="更新供应商信息",notes="更新供应商信息")
	@DeleteMapping(value = "suppliers/{supplierId}")
	public String deleteSupplier(@PathVariable String supplierId,HttpServletRequest request) {
		JsonMapper jsonMapper=new JsonMapper();
		return jsonMapper.toJson(this.supplierFacade.deleteSupplier(supplierId));
	}

	/**
	 * 供应商更新
	 * @return
	 */
	@ApiOperation(value="更新供应商产品信息",notes="更新供应商产品信息")
	@PutMapping(value = "suppliers/product/{prodId}")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "prodId",value = "产品id",dataType = "string",paramType = "path",required = true)
	})
	public String updateSupplierProduct(
										@PathVariable String prodId,
										@RequestBody MapContext map,
										HttpServletRequest request) {
		JsonMapper jsonMapper=new JsonMapper();
		RequestResult result=this.supplierFacade.updateSupplierProduct(prodId,map);
		return jsonMapper.toJson(result);
	}

	/**
	 * 删除供应商产品
	 * @return
	 */
	@ApiOperation(value="删除供应商产品",notes="删除供应商产品")
	@DeleteMapping(value = "suppliers/product/{prodId}")
	public String deleteSupplierProduct(@PathVariable String prodId,HttpServletRequest request) {
		JsonMapper jsonMapper=new JsonMapper();
		return jsonMapper.toJson(this.supplierFacade.deleteSupplierProduct(prodId));
	}

	@ApiOperation(value="供应商产品列表",notes="供应商产品列表")
	@GetMapping(value = "suppliers/products")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "supplierId",value = "供应商id",dataType = "string",paramType = "query"),
			@ApiImplicitParam(name = "prodId",value = "产品id",dataType = "string",paramType = "query"),
			@ApiImplicitParam(name = "prodName",value = "产品名称",dataType = "string",paramType = "query")
	})
	private String findSupplierProducts(@RequestParam(required = false) String supplierId,
										 @RequestParam(required = false) String prodId,
										 @RequestParam(required = false) String prodName,
										@RequestParam(required = false) Integer pageNum,
										@RequestParam(required = false) Integer pageSize){
		JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
		RequestResult result=this.supplierFacade.findSupplierProducts(pageNum,pageSize,supplierId,prodId,prodName);
		return jsonMapper.toJson(result);
	}

	/**
	 * 删除供应商产品
	 * @return
	 */
	@ApiOperation(value="删除供应商产品",notes="删除供应商产品")
	@DeleteMapping(value = "suppliers/images/{imgId}")
	public String deleteSupplierImg(@PathVariable String imgId,HttpServletRequest request) {
		JsonMapper jsonMapper=new JsonMapper();
		return jsonMapper.toJson(this.supplierFacade.deleteSupplierImages(imgId));
	}
}
