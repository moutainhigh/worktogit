package com.lwxf.industry4.webapp.controller.admin.factory.common;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import sun.misc.Version;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.sun.org.apache.regexp.internal.RE;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.FileMimeTypeUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.version.VersionDto;
import com.lwxf.industry4.webapp.domain.entity.common.UploadFiles;
import com.lwxf.industry4.webapp.domain.entity.version.UpdateVersion;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.common.FUpdateVersionFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/5/13/013 9:53
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@RestController
@RequestMapping(value = "/api/f/versions",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
@Api(value = "版本管理",tags = "版本管理")
public class FUpdateVersionController {
	@Resource(name = "fUpdateVersionFacade")
	private FUpdateVersionFacade fUpdateVersionFacade;

	/**
	 * 查询版本列表
	 * @param versionNo
	 * @param version
	 * @param name
	 * @param sysType
	 * @param platform
	 * @param updateState
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@GetMapping
	@ApiOperation(value = "查询版本列表",notes = "查询版本列表",response = UpdateVersion.class)
	private RequestResult findVersionList(@RequestParam(required = false)@ApiParam(value = "内部版本号") String versionNo, @RequestParam(required = false)@ApiParam(value = "展示版本号") String version, @RequestParam(required = false)@ApiParam(value = "系统名称") String name,
										  @RequestParam(required = false)@ApiParam(value = "系统类型，0：工业4.0整合版，1：工业4.0F端，2：工业4.0B端，3：工业4.0C端") Integer sysType, @RequestParam(required = false)@ApiParam(value = "平台,0:ios,1:android，2：PC端") Integer platform, @RequestParam(required = false)@ApiParam(value = "是否有效，0：无效，1：有效，默认：0") Integer updateState,
										  @RequestParam(required = false,defaultValue = "1")Integer pageNum,@RequestParam(required = false,defaultValue = "10")Integer pageSize){
		MapContext mapContext = this.createMapContext(versionNo,version,name,sysType,platform,updateState);
		return this.fUpdateVersionFacade.findVersionList(mapContext,pageNum,pageSize);
	}

	/**
	 * 新增版本更新信息
	 * @param updateVersion
	 * @return
	 */
	@PostMapping
	@ApiOperation(value = "新增版本更新信息",notes = "新增版本更新信息",response = UpdateVersion.class)
	private RequestResult addUpdateVersion(@RequestBody@ApiParam(value = "版本信息") UpdateVersion updateVersion){
		return this.fUpdateVersionFacade.addUpdateVersion(updateVersion);
	}

	/**
	 * 上传APK附件
	 * @param id
	 * @param multipartFile
	 * @return
	 */
	@PostMapping("/{id}/files")
	@ApiOperation(value = "上传APK附件",notes = "上传APK附件",response = UploadFiles.class)
	private RequestResult uploadFiles(@PathVariable@ApiParam(value = "版本信息ID") String id, @RequestBody@ApiParam(value = "资源文件") MultipartFile multipartFile){
		Map<String, Object> errorInfo = new HashMap<>();
		//判断资源文件是否为空
		if(multipartFile==null){
			errorInfo.put("multipartFile", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
	//		if(!FileMimeTypeUtil.isLegalApkType(multipartFile)){
	//			errorInfo.put("multipartFile", AppBeanInjector.i18nUtil.getMessage("VALIDATE_ILLEGAL_ARGUMENT"));
	//		}
			if (multipartFile.getSize() > 1024L * 1024L * AppBeanInjector.configuration.getUploadApkMaxSize()) {
				return ResultFactory.generateErrorResult(com.lwxf.commons.exception.ErrorCodes.BIZ_FILE_SIZE_LIMIT_10031, LwxfStringUtils.format(AppBeanInjector.i18nUtil.getMessage("BIZ_FILE_SIZE_LIMIT_10031"), AppBeanInjector.configuration.getUploadApkMaxSize()));
			}
		}
		if (errorInfo.size() > 0) {
			return ResultFactory.generateErrorResult(com.lwxf.commons.exception.ErrorCodes.VALIDATE_ERROR, errorInfo);
		}
		return this.fUpdateVersionFacade.uploadFiles(id,multipartFile);
	}

	/**
	 * 修改版本信息
	 * @param id
	 * @param mapContext
	 * @return
	 */
	@PutMapping("/{id}")
	@ApiOperation(value = "修改版本信息",notes = "修改版本信息",response = UpdateVersion.class)
	private RequestResult updateVersion(@PathVariable@ApiParam(value = "版本信息ID") String id,@RequestBody@ApiParam(value = "所需修改内容") MapContext mapContext){
		RequestResult result = UpdateVersion.validateFields(mapContext);
		if (result!=null){
			return result;
		}
		mapContext.put("updateUser",WebUtils.getCurrUser().getName());
		mapContext.put("updateTime",DateUtil.getSystemDate());
		return this.fUpdateVersionFacade.updateVersion(id,mapContext);
	}

	/**
	 * 删除版本信息
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除版本信息",notes = "删除版本信息")
	private RequestResult deleteVersion(@PathVariable@ApiParam(value = "版本信息ID") String id){
		return this.fUpdateVersionFacade.deleteVersion(id);
	}

	private MapContext createMapContext(String versionNo, String version, String name, Integer sysType, Integer platform, Integer updateState) {
		MapContext mapContext = new MapContext();
		if(versionNo!=null){
			mapContext.put("versionNo",versionNo);
		}
		if(version!=null){
			mapContext.put("version",version);
		}
		if(name!=null){
			mapContext.put("name",name);
		}
		if(sysType!=null){
			mapContext.put("sysType",sysType);
		}
		if(platform!=null){
			mapContext.put("platForm",platform);
		}
		if(updateState!=null){
			mapContext.put("updateState",updateState);
		}
		return mapContext;
	}
}
