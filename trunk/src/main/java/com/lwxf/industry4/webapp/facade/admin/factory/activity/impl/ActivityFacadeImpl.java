package com.lwxf.industry4.webapp.facade.admin.factory.activity.impl;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.bizservice.activity.ActivityFilesService;
import com.lwxf.industry4.webapp.bizservice.activity.ActivityInfoService;
import com.lwxf.industry4.webapp.bizservice.activity.ActivityJoinService;
import com.lwxf.industry4.webapp.bizservice.activity.ActivityParamsService;
import com.lwxf.industry4.webapp.common.component.UploadInfo;
import com.lwxf.industry4.webapp.common.component.UploadType;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.UploadResourceType;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.activity.ActivityDto;
import com.lwxf.industry4.webapp.domain.entity.activity.ActivityFiles;
import com.lwxf.industry4.webapp.domain.entity.activity.ActivityInfo;
import com.lwxf.industry4.webapp.domain.entity.activity.ActivityJoin;
import com.lwxf.industry4.webapp.domain.entity.activity.ActivityParams;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.admin.factory.activity.ActivityFacade;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.io.File;
import java.util.*;

/**
 * 功能：活动管理f端Facade 实现类
 *
 * @author：zhangxiaolin(3965488@qq.com)
 * @create：2019/3/20 10:04
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */

@Component("fActivityFacade")
public class ActivityFacadeImpl extends BaseFacadeImpl implements ActivityFacade {

    @Resource(name = "activityInfoService")
    private ActivityInfoService activityInfoService;
    @Resource(name = "activityParamsService")
    private ActivityParamsService activityParamsService;
    @Resource(name = "activityFilesService")
    private ActivityFilesService activityFilesService;
    @Resource(name = "activityJoinService")
    private ActivityJoinService activityJoinService;

    @Override
    public RequestResult findListActivities(MapContext mapContext,Integer pageNum,Integer pageSize) {
        String branchId=WebUtils.getCurrBranchId();
        mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID,branchId);
        PaginatedFilter paginatedFilter = new PaginatedFilter();
        paginatedFilter.setFilters(mapContext);
        Pagination pagination = new Pagination();
        pagination.setPageSize(pageSize);
        pagination.setPageNum(pageNum);
        paginatedFilter.setPagination(pagination);
        Map<String,String> created = new HashMap<String, String>();
        created.put(WebConstant.KEY_ENTITY_CREATED,"desc");
        List sort = new ArrayList();
        sort.add(created);
        paginatedFilter.setSorts(sort);
        return ResultFactory.generateRequestResult(this.activityInfoService.selectByFilter(paginatedFilter));
    }

    /**
     * 厂家添加活动
     * @param activityDto 厂家活动信息和项目信息
     * @return
     */
    @Override
    @Transactional(value = "transactionManager")
    public RequestResult addActivity(ActivityDto activityDto) {
        activityDto.setBranchId(WebUtils.getCurrBranchId());
        this.activityInfoService.addActivityInfo(activityDto);
        MapContext map = MapContext.newOne();
        map.put("id",activityDto.getCoverId());
        map.put("activityId",activityDto.getId());
        map.put("status",1);
        this.activityFilesService.updateByMapContext(map);
        //修改活动链接
        MapContext mapContext = new MapContext();
        mapContext.put(WebConstant.KEY_ENTITY_ID,activityDto.getId());
        mapContext.put(WebConstant.KEY_COMMON_LINK,AppBeanInjector.configuration.getDomainUrl().concat(AppBeanInjector.configuration.getActivitionContentPath()).concat("?type=1&id=").concat(activityDto.getId()));
        this.activityInfoService.updateByMapContext(mapContext);
        return ResultFactory.generateSuccessResult();
    }

    /**
     * 活动详情
     * @param activityId
     * @return
     */
    @Override
    @Transactional(value = "transactionManager")
    public RequestResult findByActivityId(String activityId) {
        MapContext result = MapContext.newOne();
        ActivityInfo activityInfo = this.activityInfoService.findById(activityId);
        List<ActivityParams> activityParamsList = this.activityParamsService.findByActivityId(activityId);
        result.put("activityInfo", activityInfo);
        result.put("activityParamsList", activityParamsList);
        return ResultFactory.generateRequestResult(result);
    }

    /**
     * 修改活动的状态（0 未发布 1已发布  2已结束）
     * @param params
     * @return
     */
    @Override
    @Transactional(value = "transactionManager")
    public RequestResult putActivityStatus(MapContext params) {
        RequestResult requestResult = ActivityInfo.validateFields(params);
        if (null != requestResult) {
            return requestResult;
        }
        if(params.get("status")!=null &&params.get("status").toString().equals("3")){
            //检查是否已经有人参与
            boolean hasJoiner= activityJoinService.hasJoiner(params.get("id").toString());
            if(hasJoiner){
                return ResultFactory.generateErrorResult(ErrorCodes.BIZ_ACTIVITY_STATUS_CHANGE_ERROR_10086,"该活动已有参与人无法撤销");
            }
        }
        this.activityInfoService.updateByMapContext(params);
        return ResultFactory.generateRequestResult(params);
    }

    /**
     * 更新参与者信息（是否付款和是否免费）
     * @param params
     * @return
     */
    @Override
    @Transactional(value = "transactionManager")
    public RequestResult putActivityJoinUpdate(MapContext params) {
        RequestResult requestResult = ActivityJoin.validateFields(params);
        if (null != requestResult) {
            return requestResult;
        }
        this.activityJoinService.updateByMapContext(params);
        return ResultFactory.generateRequestResult(params);
    }

    /**
     * 添加封面/内容中的图片
     * @param multipartFile
     * @param activityId
     * @param type
     * @return
     */
    @Override
    @Transactional(value = "transactionManager")
    public RequestResult addCover(MultipartFile multipartFile,String activityId, String type) {
        //添加图片到本地
        UploadInfo uploadInfo = AppBeanInjector.baseFileUploadComponent.doUploadByModule(UploadType.FORMAL, multipartFile, UploadResourceType.FACTIVITY, activityId, type);
        //添加图片到数据库
        ActivityFiles activityFiles = new ActivityFiles();
        activityFiles.setActivityId(activityId);
        activityFiles.setCreated(DateUtil.getSystemDate());
        activityFiles.setCreator(WebUtils.getCurrUserId());
        //activityFiles.setCreator("1");
        activityFiles.setFullPath(WebUtils.getDomainUrl() + uploadInfo.getRelativePath());
        activityFiles.setMime(uploadInfo.getFileMimeType().getRealType());
        activityFiles.setName(uploadInfo.getFileName());
        activityFiles.setOriginalMime(uploadInfo.getFileMimeType().getOriginalType());
        activityFiles.setPath(uploadInfo.getRelativePath());
        activityFiles.setStatus(0);
        activityFiles.setType(0);
        this.activityFilesService.add(activityFiles);
        MapContext map = MapContext.newOne();
        map.put("relPath",activityFiles.getPath());
        map.put("fileId",activityFiles.getId());
        return ResultFactory.generateRequestResult(map);
    }


    /**
     * 修改活动信息
     *
     * @return
     */
    @Override
    @Transactional(value = "transactionManager")
    public RequestResult updateActivity( String activityId, MapContext params) {
        ActivityInfo activityInfo = this.activityInfoService.findById(activityId);
        if (null == activityInfo) {
            return ResultFactory.generateErrorResult(ErrorCodes.BIZ_NOT_ALLOW_OPERATION_10020, AppBeanInjector.i18nUtil.getMessage("BIZ_NOT_ALLOW_OPERATION_10020"));
        }
        if (activityInfo.getStatus()!=0){
            return ResultFactory.generateErrorResult(ErrorCodes.BIZ_NOT_ALLOW_OPERATION_10020,AppBeanInjector.i18nUtil.getMessage("BIZ_NOT_ALLOW_OPERATION_10020"));
        }
        RequestResult result = ActivityInfo.validateFields(params);
        if (null != result) {
            return result;
        }
        params.put(WebConstant.KEY_ENTITY_ID,activityId);
        this.activityInfoService.updateByMapContext(params);
        return ResultFactory.generateRequestResult(params);
    }

    /**
     *  修改活动的项目
     * @param activityId
     * @param map
     * @param paramsId
     * @return
     */
    @Override
    @Transactional(value = "transactionManager")
    public RequestResult updateActivityParams(String activityId,
                                              MapContext map,
                                              String paramsId) {
        ActivityInfo activityInfo = this.activityInfoService.findById(activityId);
        if (null == activityInfo) {
            return ResultFactory.generateErrorResult(ErrorCodes.BIZ_NOT_ALLOW_OPERATION_10020, AppBeanInjector.i18nUtil.getMessage("BIZ_NOT_ALLOW_OPERATION_10020"));
        }
        if (activityInfo.getStatus()!=0){
            return ResultFactory.generateErrorResult(ErrorCodes.BIZ_NOT_ALLOW_OPERATION_10020,AppBeanInjector.i18nUtil.getMessage("BIZ_NOT_ALLOW_OPERATION_10020"));
        }
        RequestResult result = ActivityParams.validateFields(map);
        if (null != result) {
            return result;
        }
        map.put(WebConstant.KEY_ENTITY_ID,paramsId);
        this.activityInfoService.updateByMapContext(map);
        return ResultFactory.generateRequestResult(map);
    }

    /**
     * 添加项目
     * @param activityParams
     * @return
     */
    @Override
    @Transactional(value = "transactionManager")
    public RequestResult addActivityParams(ActivityParams activityParams) {
        return ResultFactory.generateRequestResult(this.activityParamsService.add(activityParams));
    }

    /**
     * 删除项目
     * @param activityParamsId  项目id
     * @return
     */
    @Override
    @Transactional(value = "transactionManager")
    public RequestResult deleteActivityParams(String activityParamsId) {
        return ResultFactory.generateRequestResult(this.activityParamsService.deleteById(activityParamsId));
    }

    /**
     * 参与活动记录
     * @return
     */
    @Override
    @Transactional(value = "transactionManager")
    public RequestResult findJoinRecord(MapContext mapContext,Integer pageNum,Integer pageSize) {
        String branchId=WebUtils.getCurrBranchId();
        mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID,branchId);
        PaginatedFilter paginatedFilter = new PaginatedFilter();
        paginatedFilter.setFilters(mapContext);
        Pagination pagination = new Pagination();
        pagination.setPageSize(pageSize);
        pagination.setPageNum(pageNum);
        paginatedFilter.setPagination(pagination);
        Map<String,String> created = new HashMap<String, String>();
        created.put(WebConstant.KEY_ENTITY_CREATED,"desc");
        List sort = new ArrayList();
        sort.add(created);
        paginatedFilter.setSorts(sort);
        return ResultFactory.generateRequestResult(this.activityJoinService.findListByActivityId(paginatedFilter));
    }



    @Override
    @Transactional(value = "transactionManager")
    public RequestResult updateCover(MultipartFile multipartFile,String activityId,String type) {
        MapContext parmas = MapContext.newOne();
        parmas.put("activityId",activityId);
        UploadInfo uploadInfo = null;
        ActivityFiles activityFiles = null;
        ActivityInfo activityInfo = this.activityInfoService.findById(activityId);
        activityFiles = this.activityFilesService.findByActivityId(activityId);
        if (activityInfo == null) {
            return ResultFactory.generateErrorResult(com.lwxf.commons.exception.ErrorCodes.BIZ_RES_NOT_FOUND_10001, AppBeanInjector.i18nUtil.getMessage("BIZ_RES_NOT_FOUND_10001"));
        }
        if(null!=type&&type.equals("content")){//type是content是修改内容中的图片
            uploadInfo = AppBeanInjector.baseFileUploadComponent.doUploadByModule(UploadType.FORMAL,multipartFile,UploadResourceType.FACTIVITY,activityId,type);
        }else if (null!=type&&type.equals("cover")){//type是cover是修改封面
            uploadInfo = AppBeanInjector.baseFileUploadComponent.doUploadByModule(UploadType.FORMAL,multipartFile,UploadResourceType.FACTIVITY,activityId,type);
        }
        AppBeanInjector.baseFileUploadComponent.deleteFileByDir(AppBeanInjector.configuration.getUploadFileRootDir().concat(activityFiles.getPath()));
        MapContext update = MapContext.newOne();
        update.put(WebConstant.KEY_ENTITY_ID,activityFiles.getId());
        update.put("path",uploadInfo.getRelativePath());
        update.put("name",uploadInfo.getFileName());
        update.put("originalMime",uploadInfo.getFileMimeType().getOriginalType());
        update.put("mime",uploadInfo.getFileMimeType().getRealType());
        update.put("creator",WebUtils.getCurrUserId());
        update.put("created",DateUtil.getSystemDate());
        update.put("full_path",WebUtils.getDomainUrl() + uploadInfo.getRelativePath());
        this.activityFilesService.updateByMapContext(update);

        MapContext mapContext = new MapContext();
        if(null!=type&&type.equals("content")) {
            String oldContent = activityInfo.getContent();
            String newContent = oldContent.replace(WebUtils.getDomainUrl() + activityFiles.getPath(),WebUtils.getDomainUrl() + uploadInfo.getRelativePath());
            mapContext.put("content", newContent);
        }else {
            mapContext.put("cover", uploadInfo.getRelativePath());
        }
        mapContext.put(WebConstant.KEY_ENTITY_ID,activityId);
        this.activityInfoService.updateByMapContext(mapContext);
        Map map = new HashMap();
        map.put("relPath",uploadInfo.getRelativePath());
        return ResultFactory.generateRequestResult(map);
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult deleteActivityInfo(String id) {
        ActivityInfo activityInfo = this.activityInfoService.findById(id);
        if(activityInfo==null){
            return ResultFactory.generateSuccessResult();
        }
        this.activityInfoService.deleteById(id);
        AppBeanInjector.baseFileUploadComponent.deleteFileByDir(AppBeanInjector.configuration.getUploadAvatarRootDir().concat(UploadResourceType.CONTENT.getModule()).concat(File.separator).concat(activityInfo.getId()));
        return ResultFactory.generateSuccessResult();
    }
}
