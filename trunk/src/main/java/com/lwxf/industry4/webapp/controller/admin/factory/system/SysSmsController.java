package com.lwxf.industry4.webapp.controller.admin.factory.system;

import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.dto.system.SysSmsDto;
import com.lwxf.industry4.webapp.domain.entity.system.SysSms;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.system.SysSmsFacade;
import com.lwxf.mybatis.utils.MapContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 功能：消息配置
 *
 * @author：DJL
 * @create：2019/11/20 16:37
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Api(value = "SysSmsController", tags = {"F端后台管理接口：消息管理"})
@RestController
@RequestMapping(value = "/api/f/sysSms", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class SysSmsController {

    @Resource(name = "sysSmsFacade")
    private SysSmsFacade sysSmsFacade;

    /**
     * 查询所有消息配置信息
     * @param pageSize
     * @param pageNum
     * @return
     */
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功", response = SysSmsDto.class)
    })
    @ApiOperation(value = "获取消息配置信息", notes = "获取消息配置信息")
    @GetMapping
    public RequestResult findSysSms(@RequestParam(required = false) Integer nodeType,
                                    @RequestParam(required = false) Integer pageSize,
                                    @RequestParam(required = false) Integer pageNum) {

        if (null == pageNum) {
            pageNum = 1;
        }

        Pagination pagination = new Pagination();
        pagination.setPageSize(pageSize);
        pagination.setPageNum(pageNum);
        MapContext mapContent = MapContext.newOne();
        mapContent.put("nodeType", nodeType);

        return this.sysSmsFacade.selectSysSmsDtoList(pageNum, pageSize, mapContent);

    }

    /**
     * 添加消息配置
     * @param sysSms
     * @return
     */
    @ApiOperation(value = "添加消息配置信息", notes = "添加消息配置信息")
    @PostMapping()
    public RequestResult add(@RequestBody SysSms sysSms) {
        RequestResult result = sysSms.validateFields();
        if (result != null) {
            return result;
        }

        return this.sysSmsFacade.add(sysSms);
    }

    /**
     * 修改消息配置
     * @param mapContext 参数map
     * @param id 消息配置id
     * @return
     */
    @ApiOperation(value = "修改消息配置信息", notes = "修改消息配置信息")
    @PutMapping("/{id}")
    public RequestResult update(@RequestBody MapContext mapContext, @PathVariable String id) {
        RequestResult result = SysSms.validateFields(mapContext);
        if (result != null) {
            return result;
        }

        return this.sysSmsFacade.update(mapContext, id);
    }

    /**
     * 删除消息配置
     * @param id
     * @return
     */
    @ApiOperation(value = "删除消息配置信息", notes = "删除消息配置信息")
    @DeleteMapping("/{id}")
    public RequestResult delete(@PathVariable String id) {
        return this.sysSmsFacade.delete(id);
    }

}
