package com.lwxf.industry4.webapp.controller.admin.factory.system;


import com.lwxf.commons.json.JsonMapper;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.entity.system.Basecode;
import com.lwxf.industry4.webapp.domain.entity.system.SysMessage;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.system.SysMessageFacade;
import com.lwxf.mybatis.utils.MapContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api(value="SysMessageController",tags={"F端后台管理接口:系统消息群发管理"})
@RequestMapping(value = "/api/f/sys/sysMessage",produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class SysMessageController {

    @Resource(name = "sysMessageFacade")
    private SysMessageFacade sysMessageFacade;

    /**
     * 系统消息查询
     * @return 消息列表
     */
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功", response = Basecode.class)
    })
    @ApiOperation(value="获取消息列表",notes="")
    @GetMapping
    public RequestResult findBaseCodes(@RequestParam(required = false) String title,
                                       @RequestParam(required = false) String type,
                                       @RequestParam(required = false) String status,
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
        MapContext mapContent = MapContext.newOne();
        mapContent.put("title",title);
        mapContent.put("type",type);
        if(status !=null && !"".equals(status)){
            mapContent.put("status",status);
        }
        return this.sysMessageFacade.findMessageByMap(mapContent,pageNum,pageSize);
    }

    /**
     * 添加群发信息
     * @RequestBody 添加群发信息
     * @return
     */
    @ApiOperation(value = "新建群发草稿", notes = "新建群发草稿")
    @PostMapping
    public String addMessage(@RequestBody SysMessage sysMessage) {
        JsonMapper jsonMapper = new JsonMapper();
        RequestResult requestResult = this.sysMessageFacade.addMessage(sysMessage);
        return jsonMapper.toJson(requestResult);
    }

    /**
     * 添加群发信息
     * @RequestBody 字典数据对象
     * @return
     */
    @ApiOperation(value = "群发", notes = "群发")
    @PostMapping(value = "/sendMessage/{messageId}")
    public String sendMessage(@PathVariable String messageId) {
        JsonMapper jsonMapper = new JsonMapper();
        RequestResult requestResult = this.sysMessageFacade.sendMessage(messageId);
        return jsonMapper.toJson(requestResult);
    }

    /**
     * 查询字典详情
     *
     * @return
     */
    @ApiOperation(value = "群发详情", notes = "")
    @GetMapping(value = "/{messageId}")
    public String findById(@PathVariable String messageId) {
        JsonMapper mapper = JsonMapper.createAllToStringMapper();
        RequestResult result = this.sysMessageFacade.findMessageById(messageId);
        return mapper.toJson(result);
    }

    /**
     * 查询字典详情
     *
     * @return
     */
    @ApiOperation(value = "我的消息列表", notes = "")
    @GetMapping(value = "/myMessageList/{userId}")
    public String myMessageList(@PathVariable String userId) {
        JsonMapper mapper = JsonMapper.createAllToStringMapper();
        RequestResult result = this.sysMessageFacade.myMessageList(userId);
        return mapper.toJson(result);
    }
    /**
     * 查询字典详情
     *
     * @return
     */
    @ApiOperation(value = "阅读消息", notes = "")
    @GetMapping(value = "/myMessage/{messageId}")
    public String myMessage(@PathVariable String messageId) {
        JsonMapper mapper = JsonMapper.createAllToStringMapper();
        RequestResult result = this.sysMessageFacade.myMessage(messageId, WebUtils.getCurrUserId());
        return mapper.toJson(result);
    }

    /**
     * @param messageId
     * @param parmas 更新的内容
     * 商家编辑活动（只能修改未发送的消息）
     * @return
     */
    @ApiOperation(value="编辑群发信息",notes="编辑群发信息")
    @PutMapping(value = "/{messageId}")
    public RequestResult updateMessage(@PathVariable String messageId,
                                        @RequestBody MapContext parmas) {
        parmas.put("id",messageId);
        return this.sysMessageFacade.updateMessage(messageId,parmas);
    }

    /**
     * 删除群发信息
     * @param messageId 信息id
     * @return
     */
    @ApiOperation(value="删除群发信息",notes="删除群发信息")
    @DeleteMapping(value = "/{messageId}")
    public RequestResult delete(@PathVariable String messageId){
        return this.sysMessageFacade.delete(messageId);
    }

}
