package com.lwxf.industry4.webapp.controller.admin.factory.contentmng;

import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.contentmng.Contents;
import com.lwxf.industry4.webapp.domain.entity.contentmng.ContentsType;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.contentmng.ContentTypeFacade;
import com.lwxf.mybatis.utils.MapContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 功能：
 *
 * @author：zhangxiaolin(3965488@qq.com)
 * @create：2019/03/27/ 13:24
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */

@Api(value="ContentTypeController",tags={"F端后台管理接口:内容分类管理"})
@RestController("ContentType")
@RequestMapping(value = "/api/f/contentTypes", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class ContentTypeController {

    @Resource(name = "fContentTypeFacade")
    private ContentTypeFacade contentTypeFacade;

    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功", response = ContentsType.class)
    })
    @ApiOperation(value="获取所有内容分类信息",notes="获取所有内容分类信息")
    @GetMapping
    private RequestResult findContents() {
        return this.contentTypeFacade.findContentTypes();
    }

    @ApiOperation(value="添加内容类型",notes="添加内容类型")
    @PostMapping
    private RequestResult addContentType(@RequestBody ContentsType contentsType) {
        return this.contentTypeFacade.addContentType(contentsType);
    }

    @ApiOperation(value="删除内容类型",notes="删除内容类型")
    @DeleteMapping(value = "/{contentTypeId}")
    private RequestResult deleteType(@PathVariable String contentTypeId) {
        return this.contentTypeFacade.deleteContentType(contentTypeId);
    }

    @ApiResponse(code = 200, message = "查询成功", response = ContentsType.class)
    @ApiOperation(value="查看内容内容类型",notes="查看内容内容类型")
    @GetMapping(value = "/{contentId}")
    private RequestResult findContentsTypeById(@PathVariable String contentId) {
        return  this.contentTypeFacade.findByContentId(contentId);
    }


    /**
     * @param contentId
     * @param parmas 更新的内容类型
     * 商家编辑活动（只能修改自己的创建的活动）
     * @return
     */
    @ApiOperation(value="更新内容类型",notes="更新内容类型")
    @PutMapping(value = "/{contentId}")
    public RequestResult updateContentType(@PathVariable String contentId,
                                       @RequestBody MapContext parmas) {
        parmas.put("id",contentId);
        return this.contentTypeFacade.updateContentType(parmas);
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
            mapContext.put("target", contentTypeId);
        }
        return mapContext;
    }

}
