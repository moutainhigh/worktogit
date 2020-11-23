package com.lwxf.industry4.webapp.controller.admin.factory.supplier;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.json.JsonMapper;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.FileMimeTypeUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.supplier.MaterialDto;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.supplier.MaterialFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/8/1 0001 11:19
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Api(value="MaterialController",tags={"F端后台管理接口:原材料信息管理"})
@RestController
@RequestMapping(value = "/api/f/materials",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class MaterialController {

	@Resource(name = "materialFacade")
	private MaterialFacade materialFacade;

	/**
	 * 原材料列表
	 * @return
	 */
	@GetMapping
	@ApiOperation(value = "条件查询原材料列表",notes = "原材料列表查询",response = MaterialDto.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "supplierId", value = "供应商ID", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "name", value = "产品名称", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "color", value = "产品颜色", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "materialLevel", value = "产品等级", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "materialStatus", value = "产品上下线", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "type", value = "原材料类型： 1-板材类 2-五金类 3-耗材类 4-其他类", dataType = "string", paramType = "query")

	})
	private String findMaterialList(@RequestParam(required = false) String supplierId,
									@RequestParam(required = false) String name,
									@RequestParam(required = false) String color,
									@RequestParam(required = false) String materialLevel,
									@RequestParam(required = false) String materialStatus,
									@RequestParam(required = false) String type,
									@RequestParam(required = false) Integer pageNum,
									@RequestParam(required = false) Integer pageSize
									){

		JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
		MapContext mapContext=MapContext.newOne();
		if(LwxfStringUtils.isNotBlank(supplierId)){
			mapContext.put("supplierId",supplierId);
		}
		if(LwxfStringUtils.isNotBlank(name)){
			mapContext.put("name",name);
		}
		if(LwxfStringUtils.isNotBlank(color)){
			mapContext.put("color",color);
		}
		if(LwxfStringUtils.isNotBlank(materialLevel)){
			mapContext.put("materialLevel",materialLevel);
		}
		if(LwxfStringUtils.isNotBlank(materialStatus)){
			mapContext.put("materialStatus",materialStatus);
		}
		if(LwxfStringUtils.isNotBlank(type)){
			mapContext.put("type",type);
		}
		String branchId= WebUtils.getCurrBranchId();
		mapContext.put("branchId",branchId);
		RequestResult result=this.materialFacade.findMaterialList(pageNum,pageSize,mapContext);
		return jsonMapper.toJson(result);
	}

	/**
	 * 原材料详情
	 * @param materialId
	 * @return
	 */
	@GetMapping("/{materialId}")
	@ApiOperation(value = "原材料详情",notes = "原材料详情",response = MaterialDto.class )
	private String findMaterialInfo(@PathVariable String materialId){
		JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
		RequestResult result=this.materialFacade.findMaterialInfo(materialId);
		return jsonMapper.toJson(result);
	}

	/**
	 * 添加原材料
	 * @param materialDto
	 * @return
	 */
	@ApiOperation(value = "添加原材料",notes = "添加原材料",response = MaterialDto.class)
	@PostMapping
	private RequestResult addMaterial(@RequestBody MaterialDto materialDto){
		String branchId=WebUtils.getCurrBranchId();
		materialDto.setBranchId(branchId);
		RequestResult result=this.materialFacade.addMaterial(materialDto);
		return result;
	}

	/**
	 * 修改原材料
	 * @param material
	 * @return
	 */
	@ApiOperation(value = "修改原材料",notes = "修改原材料",response = MaterialDto.class)
	@PutMapping("/{materialId}")
	private RequestResult updateMaterial(@PathVariable String materialId,
			                             @RequestBody MapContext material){
		RequestResult result=this.materialFacade.updateMaterial(materialId,material);
		return result;
	}

	/**
	 * 原材料删除
	 * @param materialId
	 * @return
	 */
	@ApiOperation(value = "原材料删除",notes = "原材料删除")
	@DeleteMapping("/{materialId}/delete")
	private RequestResult deleteMaterial(@PathVariable String materialId){
		return this.materialFacade.deleteMaterial(materialId);
	}
	/**
	 * 根据原材料查询供应商列表
	 */
	@ApiOperation(value = "根据原材料查询供应商列表",notes = "")
	@GetMapping("/{materialId}/suppliers")
	private String findSuppliersByMaterialId(@PathVariable String materialId){
		JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
		RequestResult result=this.materialFacade.findSuppliersByMaterialId(materialId);
		return jsonMapper.toJson(result);
	}
	/**
	 * 产品图片上传
	 * @return
	 */
	@ApiOperation(value="附件图片上传",notes="返回值样例:{ \"code\": \"200\", \"data\": { \"ids\": [ \"4tuqvoqutzpc\", \"4tuqvovumolc\" ] } }")
	@PostMapping(value = "/materialImages")
	public RequestResult materialImages(@RequestBody List<MultipartFile> multipartFileList){
		Map<String, Object> errorInfo = new HashMap<>();
		if (multipartFileList == null) {
			errorInfo.put("multipartFile", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
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
		return this.materialFacade.materialImages(multipartFileList);
	}
}
