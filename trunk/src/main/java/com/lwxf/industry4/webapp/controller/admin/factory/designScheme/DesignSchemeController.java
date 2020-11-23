package com.lwxf.industry4.webapp.controller.admin.factory.designScheme;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.json.JsonMapper;
import com.lwxf.commons.utils.DateUtil;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.scheme.SchemeStatus;
import com.lwxf.industry4.webapp.common.enums.scheme.SchemeType;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.uniquecode.UniquneCodeGenerator;
import com.lwxf.industry4.webapp.common.utils.FileMimeTypeUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.design.DesignSchemeDto;
import com.lwxf.industry4.webapp.domain.entity.design.DesignScheme;
import com.lwxf.industry4.webapp.domain.entity.design.SchemeContent;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.design.DesignSchemeFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2019/2/25/025 10:53
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@RestController
@RequestMapping(value = "/api/f/designs/schemes", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
@Api(value = "设计案例管理",tags = "设计案例管理")
public class DesignSchemeController {

	@Resource(name = "designSchemeFacade")
	private DesignSchemeFacade designSchemeFacade;

	/**
	 * 根据条件查询设计案例列表
	 *
	 * @param name
	 * @param status
	 * @param styles
	 * @param type
	 * @param doorState
	 * @param no
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@GetMapping
	@ApiOperation(value = "根据条件查询设计案例列表",notes = "",response = DesignSchemeDto.class)
	private String findDesignSchemeList(@RequestParam(required = false) String name,
										@RequestParam(required = false) Integer status,
										@RequestParam(required = false) String styles,
										@RequestParam(required = false) Integer type,
										@RequestParam(required = false) String doorState,
										@RequestParam(required = false) String no,
										@RequestParam(required = false) Integer pageNum,
										@RequestParam(required = false) Integer pageSize) {

		if (null == pageSize) {
			pageSize = AppBeanInjector.configuration.getPageSizeLimit();
		}
		if (null == pageNum) {
			pageNum = 1;
		}
		MapContext mapContext = this.createMapContext(name, status, styles, type, doorState, no);
		JsonMapper jsonMapper = new JsonMapper();
		return jsonMapper.toJson(this.designSchemeFacade.findDesignSchemeList(mapContext, pageNum, pageSize));
	}

	/**
	 * 新增设计案例
	 *
	 * @param designScheme
	 * @return
	 */
	@PostMapping
	@ApiOperation(value = "新增设计案例",notes = "",response =DesignSchemeDto.class )
	private String addDesignScheme(@RequestBody DesignScheme designScheme) {
		designScheme.setCreated(DateUtil.getSystemDate());
		designScheme.setCreator(WebUtils.getCurrUserId());
		designScheme.setNo(AppBeanInjector.uniquneCodeGenerator.getNextNo(UniquneCodeGenerator.UniqueResource.DESIGN_SCHEME_NO));
		designScheme.setStatus(SchemeStatus.DRAFT.getValue());
		designScheme.setCompanyId(WebUtils.getCurrCompanyId());
		designScheme.setSource(SchemeType.FACTORY.getValue());
		designScheme.setPageView(0);
		designScheme.setPraise(0);
		designScheme.setAttention(0);
		designScheme.setShare(0);
		RequestResult result = designScheme.validateFields();
		JsonMapper jsonMapper = new JsonMapper();
		if (result != null) {
			return jsonMapper.toJson(result);
		}
		return jsonMapper.toJson(this.designSchemeFacade.addDesignScheme(designScheme));
	}

	/**
	 * 查询案例详情
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	private String findDesignSchemeInfo(@PathVariable String id){
		JsonMapper jsonMapper = new JsonMapper();
		return jsonMapper.toJson(this.designSchemeFacade.findDesignSchemeInfo(id));
	}

	/**
	 * 修改设计案例
	 *
	 * @param id
	 * @param mapContext
	 * @return
	 */
	@PutMapping("/{id}")
	private String updataDesignScheme(@PathVariable String id,
									  @RequestBody MapContext mapContext) {
		RequestResult result = DesignScheme.validateFields(mapContext);
		JsonMapper jsonMapper = new JsonMapper();
		if (result != null) {
			return jsonMapper.toJson(result);
		}
		return jsonMapper.toJson(this.designSchemeFacade.updataDesignScheme(id, mapContext));
	}

	/**
	 * 上传案例封面
	 *
	 * @param id
	 * @param multipartFile
	 * @return
	 */
	@PostMapping("/{id}/files")
	private RequestResult uploadDesignSchemeFile(@PathVariable String id,
												 @RequestBody MultipartFile multipartFile) {
		Map<String, String> result = new HashMap<>();
		JsonMapper jsonMapper = new JsonMapper();
		if (multipartFile == null) {
			result.put("file", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, result);
		}
		if (multipartFile == null) {
			result.put("file", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		} else if (!FileMimeTypeUtil.isLegalImageType(multipartFile)) {
			result.put("file", AppBeanInjector.i18nUtil.getMessage("VALIDATE_ILLEGAL_ARGUMENT"));
		} else if (multipartFile.getSize() > 1024 * 1024 * AppBeanInjector.configuration.getUploadBackgroundMaxsize()) {
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_FILE_SIZE_LIMIT_10031, LwxfStringUtils.format(AppBeanInjector.i18nUtil.getMessage("BIZ_FILE_SIZE_LIMIT_10031"), AppBeanInjector.configuration.getUploadBackgroundMaxsize()));
		}
		if (result.size() > 0) {
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, result);
		}
		return this.designSchemeFacade.uploadDesignSchemeFile(id, 0, multipartFile, null);
	}

	/**
	 * 案例提交
	 * @param id
	 * @return
	 */
	@PutMapping("/{id}/submit")
	private String designSchemeSubmit(@PathVariable String id){
		JsonMapper jsonMapper = new JsonMapper();
		return jsonMapper.toJson(this.designSchemeFacade.updateDesignSchemeStatus(id,SchemeStatus.TO_AUDIT.getValue()));
	}

	/**
	 * 案例下架
	 * @param id
	 * @return
	 */
	@PutMapping("/{id}/disabled")
	private String designSchemeDisabled(@PathVariable String id){
		JsonMapper jsonMapper = new JsonMapper();
		return jsonMapper.toJson(this.designSchemeFacade.updateDesignSchemeStatus(id,SchemeStatus.SOLD_OUT.getValue()));
	}

	/**
	 * 案例启用
	 * @param id
	 * @return
	 */
	@PutMapping("/{id}/enable")
	private String designSchemeEnable(@PathVariable String id){
		JsonMapper jsonMapper = new JsonMapper();
		return jsonMapper.toJson(this.designSchemeFacade.updateDesignSchemeStatus(id,SchemeStatus.DRAFT.getValue()));
	}

	/**
	 * 案例删除
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}/delete")
	private String designSchemeDelete(@PathVariable String id){
		JsonMapper jsonMapper = new JsonMapper();
		return jsonMapper.toJson(this.designSchemeFacade.updateDesignSchemeStatus(id,SchemeStatus.DELETE.getValue()));
	}

	/**
	 * 审核案例
	 * @param id
	 * @param mapContext
	 * @return
	 */
	@PutMapping("/{id}/examine")
	private String designSchemeExamine(@PathVariable String id,@RequestBody MapContext mapContext){
		RequestResult result = DesignScheme.validateFields(mapContext);
		JsonMapper jsonMapper = new JsonMapper();
		if(result!=null){
			return jsonMapper.toJson(result);
		}
		return jsonMapper.toJson(this.designSchemeFacade.designSchemeExamine(id,mapContext));
	}

	/**
	 * 新增案例内容
	 *
	 * @param id
	 * @param schemeContent
	 * @return
	 */
	@PostMapping("/{id}/contents")
	private String addDesignContent(@PathVariable String id, @RequestBody SchemeContent schemeContent) {
		schemeContent.setSchemeId(id);
		schemeContent.setContentCreated(DateUtil.getSystemDate());
		RequestResult result = schemeContent.validateFields();
		JsonMapper jsonMapper = new JsonMapper();
		if (result != null) {
			return jsonMapper.toJson(result);
		}
		return jsonMapper.toJson(this.designSchemeFacade.addDesignContent(id, schemeContent));
	}

	/**
	 * 修改案例内容
	 * @param id
	 * @param contentId
	 * @param mapContext
	 * @return
	 */
	@PutMapping("/{id}/contents/{contentId}")
	private String updateSchemeContent(@PathVariable String id,@PathVariable String contentId,@RequestBody MapContext mapContext){
		RequestResult result = SchemeContent.validateFields(mapContext);
		JsonMapper jsonMapper = new JsonMapper();
		if(result!=null){
			return jsonMapper.toJson(result);
		}
		return jsonMapper.toJson(this.designSchemeFacade.updateSchemeContent(id,contentId,mapContext));
	}

	/**
	 * 上传案例内容图片
	 *
	 * @param id
	 * @param contentId
	 * @param multipartFile
	 * @return
	 */
	@PostMapping("/{id}/contents/{contentId}/files")
	private RequestResult uploadDesignSchemeContentFile(@PathVariable String id,
														@PathVariable String contentId,
														@RequestBody MultipartFile multipartFile) {
		Map<String, String> result = new HashMap<>();
		JsonMapper jsonMapper = new JsonMapper();
		if (multipartFile == null) {
			result.put("file", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, result);
		}
		if (multipartFile == null) {
			result.put("file", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		} else if (!FileMimeTypeUtil.isLegalImageType(multipartFile)) {
			result.put("file", AppBeanInjector.i18nUtil.getMessage("VALIDATE_ILLEGAL_ARGUMENT"));
		} else if (multipartFile.getSize() > 1024 * 1024 * AppBeanInjector.configuration.getUploadBackgroundMaxsize()) {
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_FILE_SIZE_LIMIT_10031, LwxfStringUtils.format(AppBeanInjector.i18nUtil.getMessage("BIZ_FILE_SIZE_LIMIT_10031"), AppBeanInjector.configuration.getUploadBackgroundMaxsize()));
		}
		if (result.size() > 0) {
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, result);
		}
		return this.designSchemeFacade.uploadDesignSchemeFile(id, 1, multipartFile, contentId);
	}

	/**
	 * 删除案例内容中的图片
	 * @param id
	 * @param contentId
	 * @return
	 */
	@DeleteMapping("/{id}/contents/{contentId}/files")
	private RequestResult deleteDesignSchemeContentFile(@PathVariable String id,@PathVariable String contentId){
		return this.designSchemeFacade.deleteDesignSchemeContentFile(id,contentId);
	}

	/**
	 * 删除某段内容
	 * @param id
	 * @param contentId
	 * @return
	 */
	@DeleteMapping("/{id}/contents/{contentId}")
	private RequestResult deleteDesignSchemeContent(@PathVariable String id,@PathVariable String contentId){
		return this.designSchemeFacade.deleteDesignSchemeContent(id,contentId);
	}

	private MapContext createMapContext(String name, Integer status, String styles, Integer type, String doorState, String no) {
		MapContext mapContext = new MapContext();
		if (name != null && !name.trim().equals("")) {
			mapContext.put(WebConstant.KEY_ENTITY_NAME, name);
		}
		if (status != null) {
			mapContext.put(WebConstant.KEY_ENTITY_STATUS, Arrays.asList(status));
		}else{
			mapContext.put(WebConstant.KEY_ENTITY_STATUS,Arrays.asList(SchemeStatus.DRAFT.getValue(),SchemeStatus.TO_AUDIT.getValue(),SchemeStatus.PUBLISHED.getValue(),SchemeStatus.SOLD_OUT.getValue()));
		}
		if (styles != null) {
			mapContext.put("styles", styles);
		}
		if (type != null) {
			mapContext.put("type", type);
		}
		if (doorState != null) {
			mapContext.put("doorState", doorState);
		}
		if (no != null) {
			mapContext.put(WebConstant.STRING_NO, no);
		}
		return mapContext;
	}
}
