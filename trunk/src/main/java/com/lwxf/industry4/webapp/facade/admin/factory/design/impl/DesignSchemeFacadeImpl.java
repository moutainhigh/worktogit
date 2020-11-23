package com.lwxf.industry4.webapp.facade.admin.factory.design.impl;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.bizservice.company.CompanyEmployeeService;
import com.lwxf.industry4.webapp.bizservice.design.*;
import com.lwxf.industry4.webapp.bizservice.system.RoleService;
import com.lwxf.industry4.webapp.common.component.UploadInfo;
import com.lwxf.industry4.webapp.common.component.UploadType;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.UploadResourceType;
import com.lwxf.industry4.webapp.common.enums.company.FactoryEmployeeRole;
import com.lwxf.industry4.webapp.common.enums.scheme.DesignSchemeFileFileType;
import com.lwxf.industry4.webapp.common.enums.scheme.DesignSchemeFileType;
import com.lwxf.industry4.webapp.common.enums.scheme.SchemeStatus;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.LoginUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.user.LoginedUser;
import com.lwxf.industry4.webapp.domain.entity.company.CompanyEmployee;
import com.lwxf.industry4.webapp.domain.entity.design.*;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.admin.factory.design.DesignSchemeFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2019/2/26/026 9:59
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("designSchemeFacade")
public class DesignSchemeFacadeImpl extends BaseFacadeImpl implements DesignSchemeFacade {

	@Resource(name = "designSchemeService")
	private DesignSchemeService designSchemeService;
	@Resource(name = "designStyleService")
	private DesignStyleService designStyleService;
	@Resource(name = "companyEmployeeService")
	private CompanyEmployeeService companyEmployeeService;
	@Resource(name = "roleService")
	private RoleService roleService;
	@Resource(name = "designSchemeFilesService")
	private DesignSchemeFilesService designSchemeFilesService;
	@Resource(name = "doorStateService")
	private DoorStateService doorStateService;
	@Resource(name = "schemeContentService")
	private SchemeContentService schemeContentService;

	@Override
	public RequestResult findDesignSchemeList(MapContext mapContext, Integer pageNum, Integer pageSize) {
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		String branchId= WebUtils.getCurrBranchId();
		mapContext.put("branchId",branchId);
		paginatedFilter.setFilters(mapContext);
		Pagination pagination = new Pagination();
		pagination.setPageNum(pageNum);
		pagination.setPageSize(pageSize);
		paginatedFilter.setPagination(pagination);
		List<Map<String, String>> sorts = new ArrayList<>();
		Map<String, String> created = new HashMap<>();
		created.put(WebConstant.KEY_ENTITY_CREATED, "desc");
		sorts.add(created);
		paginatedFilter.setSorts(sorts);
		return ResultFactory.generateRequestResult(this.designSchemeService.findListByFilter(paginatedFilter));
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addDesignScheme(DesignScheme designScheme) {
		//判断风格是否存在
		if(designScheme.getStyles()!=null){
			if(designScheme.getStyles().equals("")){
				designScheme.setStyles(null);
			}else {
				DesignStyle designStyle = this.designStyleService.findById(designScheme.getStyles());
				if (designStyle == null) {
					return ResultFactory.generateResNotFoundResult();
				}
			}
		}
		//判断设计师是否存在
		if(designScheme.getDesigner()!=null){
			if(designScheme.getDesigner().equals("")){
				designScheme.setDesigner(null);
			}else{
				CompanyEmployee companyEmployee = this.companyEmployeeService.findOneByCompanyIdAndUserId(WebUtils.getCurrCompanyId(), designScheme.getDesigner());
				if (companyEmployee == null || !companyEmployee.getRoleId().equals(this.roleService.selectByKey(FactoryEmployeeRole.DESIGNER.getValue(),WebUtils.getCurrBranchId()).getId())) {
					return ResultFactory.generateResNotFoundResult();
				}
			}
		}
		//判断户型是否存在
		if(designScheme.getDoorState()!=null){
			if(designScheme.getDoorState().equals("")){
				designScheme.setDoorState(null);
			}else{
				DoorState doorState = this.doorStateService.findById(designScheme.getDoorState());
				if (doorState == null) {
					return ResultFactory.generateResNotFoundResult();
				}
			}
		}
		//新增设计案例
		this.designSchemeService.add(designScheme);
		//修改案例链接
		MapContext mapContext = new MapContext();
		mapContext.put(WebConstant.KEY_ENTITY_ID,designScheme.getId());
		mapContext.put(WebConstant.KEY_COMMON_LINK,AppBeanInjector.configuration.getDomainUrl().concat(AppBeanInjector.configuration.getSchemeContentPath()).concat("?type=0&id=").concat(designScheme.getId()));
		this.designSchemeService.updateByMapContext(mapContext);
		return ResultFactory.generateRequestResult(this.designSchemeService.findOneById(designScheme.getId()));
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult uploadDesignSchemeFile(String id, Integer type, MultipartFile multipartFile, String contentId) {
		//判断设计案例是否存在
		DesignScheme designScheme = this.designSchemeService.findById(id);
		if (designScheme == null) {
			return ResultFactory.generateResNotFoundResult();
		}
		DesignSchemeFiles designSchemeFiles = new DesignSchemeFiles();
		designSchemeFiles.setCreated(DateUtil.getSystemDate());
		designSchemeFiles.setCreator(WebUtils.getCurrUserId());
		designSchemeFiles.setType(type);
		designSchemeFiles.setFileType(DesignSchemeFileFileType.IMG.getValue());
		//获取原始的图片
		List<DesignSchemeFiles> uploadFiles = this.designSchemeFilesService.findListByResourceIdAndType(id, type);
		//赋值给uploadinfo,数据进行处理
		UploadInfo uploadInfo = AppBeanInjector.baseFileUploadComponent.doUploadByModule(UploadType.TEMP, multipartFile, UploadResourceType.DESIGN_SCHEMES, id);
		designSchemeFiles.setMime(uploadInfo.getFileMimeType().getRealType());
		designSchemeFiles.setOriginalMime(uploadInfo.getFileMimeType().getOriginalType());
		designSchemeFiles.setPath(uploadInfo.getRelativePath());
		designSchemeFiles.setFullPath(WebUtils.getDomainUrl() + uploadInfo.getRelativePath());
		designSchemeFiles.setName(uploadInfo.getFileName());
		if (type == 0) {
			designSchemeFiles.setStatus(1);
			designSchemeFiles.setResourceId(id);
			//删除数据库中数据以及本地文件
			if (uploadFiles != null && uploadFiles.size() != 0) {
				AppBeanInjector.baseFileUploadComponent.deleteFileByDir(AppBeanInjector.configuration.getUploadFileRootDir().concat(uploadFiles.get(0).getPath()));
				this.designSchemeFilesService.deleteById(uploadFiles.get(0).getId());
			}
			this.designSchemeFilesService.add(designSchemeFiles);
			MapContext mapContext = new MapContext();
			mapContext.put(WebConstant.KEY_ENTITY_ID, id);
			mapContext.put("cover", designSchemeFiles.getPath());
			this.designSchemeService.updateByMapContext(mapContext);
		} else {
			designSchemeFiles.setStatus(0);
			designSchemeFiles.setResourceId(contentId);
			//删除数据库中数据以及本地文件
			if (uploadFiles != null && uploadFiles.size() != 0) {
				AppBeanInjector.baseFileUploadComponent.deleteFileByDir(AppBeanInjector.configuration.getUploadFileRootDir().concat(uploadFiles.get(0).getPath()));
				this.designSchemeFilesService.deleteById(uploadFiles.get(0).getId());
			}
			this.designSchemeFilesService.add(designSchemeFiles);
			MapContext updateContent = new MapContext();
			updateContent.put(WebConstant.KEY_ENTITY_ID, contentId);
			updateContent.put("contentImage", designSchemeFiles.getPath());
			this.schemeContentService.updateByMapContext(updateContent);
		}
		return ResultFactory.generateRequestResult(designSchemeFiles);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updataDesignScheme(String id, MapContext mapContext) {
		//判断设计案例是否真实存在
		DesignScheme designScheme = this.designSchemeService.findById(id);
		if (designScheme == null) {
			return ResultFactory.generateResNotFoundResult();
		}
		//如果修改户型 判断户型是否存在
		String doorStateId = mapContext.getTypedValue("doorState", String.class);
		if(doorStateId!=null){
			if(doorStateId.equals("")){
				mapContext.remove("doorState");
			}else{
				DoorState doorState = this.doorStateService.findById(doorStateId);
				if(doorState==null){
					return ResultFactory.generateResNotFoundResult();
				}
			}
		}
		//如果修改风格 判断风格是否存在
		String styleId = mapContext.getTypedValue("styles", String.class);
		if(styleId!=null){
			if(styleId.equals("")){
				mapContext.remove("styles");
			}else{
				DesignStyle designStyle = this.designStyleService.findById(styleId);
				if(designStyle==null){
					return ResultFactory.generateResNotFoundResult();
				}
			}
		}
		//判断设计师是否存在
		String designer = mapContext.getTypedValue("designer", String.class);
		if(designer!=null){
			if(designer.equals("")){
				mapContext.remove("designer");
			}else{
				CompanyEmployee companyEmployee = this.companyEmployeeService.findOneByCompanyIdAndUserId(WebUtils.getCurrCompanyId(), designer);
				if (companyEmployee == null || !companyEmployee.getRoleId().equals(this.roleService.selectByKey(FactoryEmployeeRole.DESIGNER.getValue(),WebUtils.getCurrBranchId()).getId())) {
					return ResultFactory.generateResNotFoundResult();
				}
			}
		}

		mapContext.put(WebConstant.KEY_ENTITY_ID, id);
		this.designSchemeService.updateByMapContext(mapContext);
		return ResultFactory.generateRequestResult(this.designSchemeService.findOneById(id));
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addDesignContent(String id, SchemeContent schemeContent) {
		//判断案例是否存在
		DesignScheme designScheme = this.designSchemeService.findById(id);
		if (designScheme == null) {
			return ResultFactory.generateResNotFoundResult();
		}
		//todo: 排序功能暂时不使用
//		List<SchemeContentDto> schemeContentDtoList = this.schemeContentService.findBySchemeId(id);
//		schemeContent.setContentIndex(schemeContentDtoList.size() + 1);
		this.schemeContentService.add(schemeContent);
		return ResultFactory.generateRequestResult(this.schemeContentService.findById(schemeContent.getId()));
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updateSchemeContent(String id, String contentId, MapContext mapContext) {
		//判断案例与内容是否存在
		SchemeContent content = this.schemeContentService.findById(contentId);
		if (content==null||!content.getSchemeId().equals(id)){
			return ResultFactory.generateResNotFoundResult();
		}
		mapContext.put(WebConstant.KEY_ENTITY_ID,contentId);
		this.schemeContentService.updateByMapContext(mapContext);
		return ResultFactory.generateRequestResult(this.schemeContentService.findById(contentId));
	}

	@Override
	public RequestResult findDesignSchemeInfo(String id) {
		MapContext mapContext = new MapContext();
		mapContext.put("designSheme",this.designSchemeService.findOneById(id));
		mapContext.put("designContentList",this.schemeContentService.findBySchemeId(id));
		return ResultFactory.generateRequestResult(mapContext);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult deleteDesignSchemeContentFile(String id, String contentId) {
		//判断案例与内容是否存在
		SchemeContent content = this.schemeContentService.findById(contentId);
		if (content==null||!content.getSchemeId().equals(id)){
			return ResultFactory.generateResNotFoundResult();
		}
		List<DesignSchemeFiles> designSchemeFiles = this.designSchemeFilesService.findListByResourceIdAndType(contentId, DesignSchemeFileType.CONTENT.getValue());
		if(designSchemeFiles.size()!=0){
			AppBeanInjector.baseFileUploadComponent.deleteFileByDir(AppBeanInjector.configuration.getUploadFileRootDir().concat(designSchemeFiles.get(0).getPath()));
			this.designSchemeFilesService.deleteById(designSchemeFiles.get(0).getId());
		}
		MapContext mapContext = new MapContext();
		mapContext.put(WebConstant.KEY_ENTITY_ID,contentId);
		mapContext.put("contentImage","");
		this.schemeContentService.updateByMapContext(mapContext);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult deleteDesignSchemeContent(String id, String contentId) {
		//判断案例与内容是否存在
		SchemeContent content = this.schemeContentService.findById(contentId);
		if (content==null||!content.getSchemeId().equals(id)){
			return ResultFactory.generateResNotFoundResult();
		}
		//删除图片
		List<DesignSchemeFiles> designSchemeFiles = this.designSchemeFilesService.findListByResourceIdAndType(contentId, DesignSchemeFileType.CONTENT.getValue());
		if(designSchemeFiles.size()!=0){
			AppBeanInjector.baseFileUploadComponent.deleteFileByDir(AppBeanInjector.configuration.getUploadFileRootDir().concat(designSchemeFiles.get(0).getPath()));
			this.designSchemeFilesService.deleteById(designSchemeFiles.get(0).getId());
		}
		//删除内容
		this.schemeContentService.deleteById(contentId);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult designSchemeExamine(String id, MapContext mapContext) {
		//判断案例是否存在
		DesignScheme designScheme = this.designSchemeService.findById(id);
		if(designScheme==null||!designScheme.getStatus().equals(SchemeStatus.TO_AUDIT.getValue())){
			return ResultFactory.generateResNotFoundResult();
		}
		mapContext.put(WebConstant.KEY_ENTITY_ID,id);
		this.designSchemeService.updateByMapContext(mapContext);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updateDesignSchemeStatus(String id,Integer status) {
		//判断案例是否存在
		DesignScheme designScheme = this.designSchemeService.findById(id);
		if(designScheme==null){
			return ResultFactory.generateResNotFoundResult();
		}
		Integer schemeStatus = designScheme.getStatus();//当前案例的状态
		switch (SchemeStatus.getByValue(status)){
			case DRAFT:
				if(!schemeStatus.equals(SchemeStatus.SOLD_OUT.getValue())){
					return ResultFactory.generateErrorResult(ErrorCodes.BIZ_SCHEME_STATUS_ERROR_10081,AppBeanInjector.i18nUtil.getMessage("BIZ_SCHEME_STATUS_ERROR_10081"));
				}
				break;
			case TO_AUDIT:
				if(!schemeStatus.equals(SchemeStatus.DRAFT.getValue())){
					return ResultFactory.generateErrorResult(ErrorCodes.BIZ_SCHEME_STATUS_ERROR_10081,AppBeanInjector.i18nUtil.getMessage("BIZ_SCHEME_STATUS_ERROR_10081"));
				}
				break;
			case PUBLISHED:
				if(!schemeStatus.equals(SchemeStatus.TO_AUDIT.getValue())){
					return ResultFactory.generateErrorResult(ErrorCodes.BIZ_SCHEME_STATUS_ERROR_10081,AppBeanInjector.i18nUtil.getMessage("BIZ_SCHEME_STATUS_ERROR_10081"));
				}
				break;
			case SOLD_OUT:
				if(!schemeStatus.equals(SchemeStatus.PUBLISHED.getValue())){
					return ResultFactory.generateErrorResult(ErrorCodes.BIZ_SCHEME_STATUS_ERROR_10081,AppBeanInjector.i18nUtil.getMessage("BIZ_SCHEME_STATUS_ERROR_10081"));
				}
				break;
		}
		MapContext mapContext = new MapContext();
		mapContext.put(WebConstant.KEY_ENTITY_ID,id);
		mapContext.put(WebConstant.KEY_ENTITY_STATUS,status);
		this.designSchemeService.updateByMapContext(mapContext);
		return ResultFactory.generateRequestResult(this.designSchemeService.findOneById(id));
	}
}
