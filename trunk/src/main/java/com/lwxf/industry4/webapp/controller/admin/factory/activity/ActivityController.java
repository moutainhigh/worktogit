package com.lwxf.industry4.webapp.controller.admin.factory.activity;

import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.json.JsonMapper;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.FileMimeTypeUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.activity.ActivityDto;
import com.lwxf.industry4.webapp.domain.dto.activity.ActivityJoinDto;
import com.lwxf.industry4.webapp.domain.entity.activity.ActivityInfo;
import com.lwxf.industry4.webapp.domain.entity.activity.ActivityParams;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.activity.ActivityFacade;
import com.lwxf.mybatis.utils.MapContext;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能：活动管理工厂端Api
 * @author：zhangxiaolin(3965488@qq.com)
 * @create：2019/3/20 10:27
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Api(value="ActivityController",tags={"工厂F端活动管理接口"})
@RestController(value = "fActivityController")
@RequestMapping(value = "/api/f/activity", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class ActivityController {

    @Resource(name = "fActivityFacade")
    private ActivityFacade activityFacade;

    /**
     * 查询所有活动
     * @return 活动信息列表
     */
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功", response = ActivityInfo.class)
            })
    @ApiOperation(value="获取活动信息",notes="查询出的为活动基本信息，不包括活动项目")
    @GetMapping
    public RequestResult findAllActivity(@RequestParam(required = false) String name,
                                         @RequestParam(required = false) Integer type,
                                         @RequestParam(required = false) Integer classify,
                                         @RequestParam(required = false) Integer target,
                                         @RequestParam(required = false) Integer status,
                                         @RequestParam(required = false) Integer pageSize,
                                         @RequestParam(required = false) Integer pageNum) {
        if (null == pageNum) {
            pageNum = 1;
        }
        if (null == pageSize) {
            pageSize = AppBeanInjector.configuration.getPageSizeLimit();
        }
        Pagination pagination = new Pagination();
        pagination.setPageSize(pageSize);
        pagination.setPageNum(pageNum);
        MapContext mapContent = this.createMapContent(name, type, classify,target,status);
        mapContent.put(WebConstant.KEY_ENTITY_BRANCH_ID, WebUtils.getCurrBranchId());
        return this.activityFacade.findListActivities(mapContent,pageNum,pageSize);
    }

    /**
     * 厂家添加活动
     * @RequestBody 活动信息对象
     * @return
     */
    @ApiOperation(value = "活动添加", notes = "test")
    @PostMapping(value = "/activities")
    public String addActivity(@RequestBody  ActivityDto activityDto) {
        JsonMapper jsonMapper = new JsonMapper();
        RequestResult requestResult = this.activityFacade.addActivity(activityDto);
        return jsonMapper.toJson(requestResult);
    }

    /**
     * @param activityId
     * @param parmas 更新的内容
     * 商家编辑活动（只能修改自己的创建的活动）
     * @return
     */
    @ApiOperation(value="商家编辑活动",notes="商家活动编辑")
    @PutMapping(value = "/{activityId}")
    public RequestResult updateActivity(@PathVariable String activityId,
                                        @RequestBody MapContext parmas) {
        return this.activityFacade.updateActivity(activityId,parmas);
    }

    /**
     * 修改活动参数
     * @param activityId
     * @param map
     * @param paramsId
     * @return
     */
    @ApiOperation(value="活动项目编辑",notes="活动项目编辑")
    @PutMapping(value = "/{activityId}/activityParams/{paramsId}")
    public RequestResult updateActivityParams(@PathVariable String activityId,
                                              @RequestBody MapContext map,
                                              @PathVariable String paramsId){
        return this.activityFacade.updateActivityParams(activityId,map,paramsId);
    }

    /**
     * 添加活动项目
     * @param activityParams 活动参数实体
     * @return
     */
    @ApiOperation(value="活动项目添加",notes="活动项目添加")
    @PostMapping(value = "/{activityId}/activityParams")
    public RequestResult addActivityParams(@PathVariable String activityId,
                                           @RequestBody ActivityParams activityParams){
        activityParams.setActivityId(activityId);
        return this.activityFacade.addActivityParams(activityParams);
    }

    /**
     * 删除活动项目
     * @param paramsId 活动参数实体Id
     * @return
     */
    @ApiOperation(value="活动项目删除",notes="活动项目删除")
    @DeleteMapping(value = "/activityParams/{paramsId}")
    public RequestResult deleteActivityParams(@PathVariable String paramsId){
        return this.activityFacade.deleteActivityParams(paramsId);
    }

    /**
     * 添加封面/内容中的图片
     * @param multipartFile
     * @return
     */
    @PostMapping(value = "/activityFiles/{type}")
    public RequestResult addCover(@RequestBody MultipartFile multipartFile,
                                  @RequestParam(required = false) String activityId,
                                  @PathVariable String type){
        Map<String, Object> errorInfo = new HashMap<>();
        if (multipartFile==null){
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
        return this.activityFacade.addCover(multipartFile,activityId,type);
    }

    /**
     * 修改活动封面 或 内容中的图片
     * @param activityId 活动id
     * @param multipartFile  文件集合
     * @param type  是文章图片还是封面图片
     * @return
     */
    @ApiOperation(value="更新活动封面",notes="更新活动封面")
    @PostMapping(value = "/{activityId}/{type}")
    public RequestResult updateCover(@PathVariable(required = false) String activityId,
                                     @RequestBody MultipartFile multipartFile,
                                     @PathVariable(required = false) String type){
        return this.activityFacade.updateCover(multipartFile,activityId,type);
    }

    /**
     * 设置商家活动状态
     * @param activityId  活动id
     * @param status  待更新的状态
     * @return
     */
    @ApiOperation(value="更新活动状态",notes="更新活动状态")
    @PutMapping(value = "/{activityId}/status/{status}")
    public RequestResult putActivityStatus(@PathVariable String activityId,
                                           @PathVariable Integer status) {
        MapContext params = MapContext.newOne();
        params.put("id",activityId);
        params.put("status",status);
        return this.activityFacade.putActivityStatus(params);
    }

    /**
     * 设置参与者状态
     * @param activityJoinId  参与者id
     * @param status  待更新的状态
     * @return
     */
    @ApiOperation(value="更新参与者状态",notes="更新参与者状态")
    @PutMapping(value = "/activityJoins/{activityJoinId}/status/{status}")
    public RequestResult putActivityJoinStatus(@PathVariable String activityJoinId,
                                           @PathVariable Integer status) {
        MapContext params = MapContext.newOne();
        params.put("id",activityJoinId);
        params.put("status",status);
        return this.activityFacade.putActivityJoinUpdate(params);
    }

    /**
     * 更新付款状态
     * @param activityJoinId  活动参与者id
     * @param isPaid  待更新的付款状态
     * @return
     */
    @PutMapping(value = "/{activityJoinId}/isPaid/{isPaid}")
    public RequestResult putActivityIsPaid(@PathVariable String activityJoinId,
                                           @PathVariable Integer isPaid) {
        MapContext params = MapContext.newOne();
        params.put("id",activityJoinId);
        params.put("paid",isPaid);
        return this.activityFacade.putActivityJoinUpdate(params);
    }

    /**
     * 更新是否免费
     * @param activityJoinId  活动参与者id
     * @param isFree  待更新的状态
     * @return
     */
    @PutMapping(value = "/{activityJoinId}/isFree/{isFree}")
    public RequestResult putActivityIsFree(@PathVariable String activityJoinId,
                                           @PathVariable Integer isFree) {
        MapContext params = MapContext.newOne();
        params.put("id",activityJoinId);
        params.put("free",isFree);
        return this.activityFacade.putActivityJoinUpdate(params);
    }

    /**
     * 查询商家活动的详情（工厂的活动详情也可以用）
     *
     * @return
     */
    @GetMapping(value = "/{activityId}")
    public String findByActivityId(@PathVariable String activityId) {
        JsonMapper mapper = JsonMapper.createAllToStringMapper();
        RequestResult result = this.activityFacade.findByActivityId(activityId);
        return mapper.toJson(result);
    }


    /**
     * 参与活动记录
     * @return
     */
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功", response = ActivityJoinDto.class)
    })
    @ApiOperation(value = "活动参与者信息", notes = "活动参与者信息")
    @GetMapping(value = "/{activityId}/activityJoins")
    public RequestResult findJoinRecord(@PathVariable  String activityId,
                                        @RequestParam(required = false) String name,
                                        @RequestParam(required = false) String mobile,
                                        @RequestParam(required = false) String companyName,
                                        @RequestParam(required = false) Integer type,
                                        @RequestParam(required = false) Integer paid,
                                        @RequestParam(required = false) Integer free,
                                        @RequestParam(required = false) Integer pageSize,
                                        @RequestParam(required = false) Integer pageNum){
        MapContext params = createActivityJoinMapContent(name,mobile,companyName,type,paid,activityId,free);
        return this.activityFacade.findJoinRecord(params,pageNum,pageSize);
    }

    /**
     * 删除活动
     * @param activityId  活动id
     * @return
     */
    @DeleteMapping(value = "/{activityId}")
    public RequestResult deleteByActivityId(@PathVariable String activityId) {
        return this.activityFacade.deleteActivityInfo(activityId);
    }

    /**
     * 参数组成
     * @param name  标题
     * @param type  创建人类型（0厂家、1经销商）
     * @param classify  分类（0 海报  1 互动的页面）
     * @param target  面向的顾客（0 经销商 1终端）
     * @param status  状态 （0 未发布，1已发布，2活动结束）
     * @return
     */
    private MapContext createMapContent(String name, Integer type, Integer classify, Integer target,Integer status) {
        MapContext mapContext = MapContext.newOne();
        if (name != null&&!name.equals("")) {
            mapContext.put(WebConstant.KEY_ENTITY_NAME, name);
        }
        if (type!=null) {
            mapContext.put("type", type);
        }
        if (classify!=null) {
            mapContext.put("classify", classify);
        }
        if (status!=null) {
            mapContext.put("status", status);
        }
        if (target!=null) {
            mapContext.put("target", target);
        }
        return mapContext;
    }

    private MapContext createActivityJoinMapContent(String name, String mobile, String companyName, Integer type,Integer isPaid,String activityId,Integer isFree) {
        MapContext mapContext = MapContext.newOne();
        if (name != null && !name.equals("")) {
            mapContext.put(WebConstant.KEY_ENTITY_NAME, name);
        }
        if (mobile!=null && !mobile.equals("")) {
            mapContext.put("mobile", mobile);
        }if (activityId!=null && !activityId.equals("")) {
            mapContext.put("activity_id", activityId);
        }
        if (companyName!=null && !companyName.equals("")) {
            mapContext.put("companyName", companyName);
        }
        if (type!=null) {
            mapContext.put("type", type);
        }
        if (isPaid!=null) {
            mapContext.put("paid", isPaid);
        }
        if (isFree!=null) {
            mapContext.put("free", isFree);
        }
        return mapContext;
    }
}
