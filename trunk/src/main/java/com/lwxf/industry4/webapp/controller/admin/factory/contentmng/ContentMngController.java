package com.lwxf.industry4.webapp.controller.admin.factory.contentmng;

import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.json.JsonMapper;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.FileMimeTypeUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.contentmng.ContentsDto;
import com.lwxf.industry4.webapp.domain.entity.contentmng.Contents;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.contentmng.ContentsFacade;
import com.lwxf.mybatis.utils.MapContext;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能：
 *
 * @author：zhangxiaolin(3965488@qq.com)
 * @create：2019/03/27/ 13:24
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */

@Api(value="ContentMngController",tags={"F端后台管理接口:内容管理"})
@RestController("contents")
@RequestMapping(value = "/api/f/contents", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class ContentMngController {

    @Resource(name = "fContentsFacade")
    private ContentsFacade contentsFacade;

    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功", response = Contents.class)
    })
    @ApiOperation(value="查询内容列表",notes="查询内容列表")
    @GetMapping
    private RequestResult findContents(@RequestParam(required = false) String name,
                                       @RequestParam(required = false) String code,
                                       @RequestParam(required = false) String publisher,
                                       @RequestParam(required = false) String contentTypeId,
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
        MapContext mapContent = this.createMapContent(name, code, publisher,contentTypeId,status);
        mapContent.put(WebConstant.KEY_ENTITY_BRANCH_ID, WebUtils.getCurrBranchId());
        return this.contentsFacade.findContents(mapContent,pageNum,pageSize);
    }

    @ApiOperation(value="添加内容",notes="添加内容")
    @PostMapping(value = "/contents")
    private RequestResult addContents(@RequestBody   ContentsDto contentsDto) {
        return this.contentsFacade.addContent(contentsDto);
    }

    @ApiOperation(value="删除内容",notes="删除内容")
    @DeleteMapping(value = "/{contentId}")
    private RequestResult deleteContents(@PathVariable String contentId) {
        return this.contentsFacade.deleteContent(contentId);
    }

    @ApiResponse(code = 200, message = "查询成功", response = ContentsDto.class)
    @ApiOperation(value="查看内容详情",notes="查看内容详情")
    @GetMapping(value = "/{contentId}")
    private RequestResult findContentsById(@PathVariable String contentId) {
        return  this.contentsFacade.findByContentId(contentId);
    }

    /**
     * @param contentId
     * @param parmas 更新的内容
     * 编辑内容（只能修改自己的创建的活动）
     * @return
     */
    @ApiOperation(value="更新内容",notes="更新内容")
    @PutMapping(value = "/{contentId}")
    public RequestResult updateContent(@PathVariable String contentId,
                                        @RequestBody MapContext parmas) {
        parmas.put("id",contentId);
        return this.contentsFacade.updateContent(parmas);
    }
    /**
     * @param contentId
     * @param status 状态
     * 更新内容状态
     * @return
     */
    @ApiOperation(value="更新内容状态",notes="更新内容状态")
    @PutMapping(value = "/{contentId}/status/{status}")
    public RequestResult updateStatus(@PathVariable String contentId,
                                      @PathVariable Integer status) {
        MapContext map = MapContext.newOne();
        map.put("id",contentId);
        map.put("status",status);
        return this.contentsFacade.updateContent(map);
    }
    /**
     * @param contentId
     * 发布内容
     */
    @ApiOperation(value="内容发布",notes="内容发布")
    @PutMapping(value = "/{contentId}/publish")
    public RequestResult publishContent(@PathVariable String contentId) {
        return this.contentsFacade.publishContent(contentId);
    }


    /**
     * 添加封面/内容中的图片
     * @param multipartFile
     * @return
     */
    @ApiOperation(value="封面上传",notes="封面上传")
    @PostMapping(value = "/uploadCover")
    public RequestResult uploadCover(@RequestBody MultipartFile multipartFile){
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
        return this.contentsFacade.uploadCover(multipartFile);
    }
    /**
     * 添加封面/内容中的图片
     * @param file
     * @return
     */
    @ApiOperation(value="文本编辑器图片上传",notes="文本编辑器图片上传")
    @PostMapping(value = "/uploadContentImages")
    public String uploadContentsImages(@RequestBody MultipartFile file,
                                     @RequestParam(required = false) String contentId){
        JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
        Map<String, Object> errorInfo = new HashMap<>();
        if (file==null){
            errorInfo.put("multipartFile", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
        }
        if (!FileMimeTypeUtil.isLegalImageType(file)) {
            errorInfo.put("multipartFile", AppBeanInjector.i18nUtil.getMessage("VALIDATE_ILLEGAL_ARGUMENT"));
        }
        if (file.getSize() > 1024L * 1024L * AppBeanInjector.configuration.getUploadBackgroundMaxsize()) {
            return jsonMapper.toJson(LwxfStringUtils.format(AppBeanInjector.i18nUtil.getMessage("BIZ_FILE_SIZE_LIMIT_10031"), AppBeanInjector.configuration.getUploadBackgroundMaxsize()));
        }
        if (errorInfo.size() > 0) {
            return jsonMapper.toJson(errorInfo);
        }
        return jsonMapper.toJson(this.contentsFacade.uploadContentsImages(file,contentId));
    }

    /**
     * 修改活动封面 或 内容中的图片
     * @param contentId 内容id
     * @param multipartFile  文件集合
     * @return
     */
    @ApiOperation(value="更新活动封面",notes="更新活动封面")
    @PostMapping(value = "/{contentId}/updateCover")
    public RequestResult updateCover(@PathVariable(required = false) String contentId,
                                     @RequestBody MultipartFile multipartFile){
        return this.contentsFacade.updateCover(multipartFile,contentId);
    }


    /**
     * 参数组成
     * @param name  标题
     * @param code  文件类型(0:学习 1帮助 2新闻)
     * @param publisher  发布人
     * @param contentTypeId  文章类型ID
     * @param status  文件状态：0草稿 1发布 2取消发布（默认为0）
     * @return
     */
    private MapContext createMapContent(String name, String code, String publisher, String contentTypeId,Integer status) {
        MapContext mapContext = MapContext.newOne();
        if (name != null&&!name.equals("")) {
            mapContext.put(WebConstant.KEY_ENTITY_NAME, name);
        }
        if (code!=null) {
            mapContext.put("code", code);
        }
        if (publisher!=null) {
            mapContext.put("publisher", publisher);
        }
        if (status!=null) {
            mapContext.put("status", status);
        }
        if (contentTypeId!=null) {
            mapContext.put("contentsTypeId", contentTypeId);
        }
        return mapContext;
    }

}
