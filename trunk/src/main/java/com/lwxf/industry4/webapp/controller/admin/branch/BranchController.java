package com.lwxf.industry4.webapp.controller.admin.branch;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.dto.branch.BranchDto;
import com.lwxf.industry4.webapp.domain.entity.branch.Branch;
import com.lwxf.industry4.webapp.facade.branch.BranchFacade;
import com.lwxf.mybatis.utils.MapContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 功能：企业管理
 *
 * @author：Administrator
 * @create：2019/6/5/005 9:41
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@RestController
@RequestMapping(value = "/api/f/branch", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
@Api(value = "企业管理", tags = "企业管理")
public class BranchController {

    @Resource(name = "branchFacade")
    private BranchFacade branchFacade;

    /**
     * 查询企业列表
     *
     * @param name
     * @param status
     * @param tel
     * @param leaderName
     * @param type
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping
    @ApiOperation(value = "查询企业列表", notes = "查询企业列表", response = BranchDto.class)
    private RequestResult findBranchList(@RequestParam(required = false) @ApiParam(value = "企业名称") String name,
                                         @RequestParam(required = false) @ApiParam(value = "1 试用 2 正常 3 停用") Integer status,
                                         @RequestParam(required = false) @ApiParam(value = "负责人电话") String tel,
                                         @RequestParam(required = false) @ApiParam(value = "负责人名称") String leaderName,
                                         @RequestParam(required = false) @ApiParam(value = "类型: 1 免费用户 2 付费用户") Integer type,
                                         @RequestParam(required = false, defaultValue = "1") @ApiParam(value = "页码") Integer pageNum,
                                         @RequestParam(required = false, defaultValue = "10") @ApiParam(value = "数据量") Integer pageSize) {
        MapContext mapContext = this.createdMapContext(name, status, tel, leaderName, type);
        return this.branchFacade.findBranchList(mapContext, pageNum, pageSize);
    }

    /**
     * 新增企业
     *
     * @param branch
     * @return
     */
    @PostMapping
    @ApiOperation(value = "新增企业", notes = "新增企业")
    private RequestResult addBrandInfo(@RequestBody @ApiParam(value = "企业信息") Branch branch) {
        branch.setCreated(DateUtil.getSystemDate());
        RequestResult result = branch.validateFields();
        if (result != null) {
            return result;
        }
        return this.branchFacade.addBrandInfo(branch);
    }

    /**
     * 编辑企业信息
     *
     * @param mapContext
     * @param id         企业id
     * @return
     */
    @PutMapping("/{id}")
    @ApiOperation(value = "编辑企业", notes = "编辑企业")
    public RequestResult updateBranch(@RequestBody MapContext mapContext, @PathVariable String id) {

        return this.branchFacade.updateBranch(mapContext, id);
    }

    /**
     * 查询当前企业信息
     *
     * @return
     */
    @GetMapping("/current")
    @ApiOperation(value = "查询当前企业信息", notes = "查询当前企业信息", response = BranchDto.class)
    public RequestResult findCurrentBranch() {
        RequestResult result = this.branchFacade.findCurrentBranch();
        return result;
    }

    /**
     * 企业有效期加密
     */

    @PutMapping("/branchId")
    @ApiOperation(value = "企业有效期加密", notes = "")
    private RequestResult branchValidityEncryption(@PathVariable @ApiParam(value = "企业ID") String branchId,
                                                   @RequestBody @ApiParam(value = "企业信息") MapContext branch) {
        return this.branchFacade.branchValidityEncryption(branchId, branch);
    }

    /**
     * 企业启用/关闭催款功能
     * @return
     */
    @PutMapping("/enableRemind/{branchId}")
    @ApiOperation(value = "企业启用/关闭催款功能", notes = "企业启用/关闭催款功能")
    public RequestResult enableRemind(@PathVariable @ApiParam(value = "企业ID") String branchId) {
        return this.branchFacade.enableRemind(branchId);
    }
    /**
     * 企业启用/关闭审核发货计划功能
     * @return
     */
    @PutMapping("/toExaminePlan")
    @ApiOperation(value = "企业启用/关闭审核发货计划功能", notes = "企业启用/关闭审核发货计划功能")
    public RequestResult toExaminePlan(@RequestBody MapContext mapContext) {
        return this.branchFacade.toExaminePlan(mapContext);
    }

    @GetMapping("/expireMessage")
    @ApiOperation(value = "获取授权过期提醒消息", notes = "获取授权过期提醒消息")
    public RequestResult getExpireMessage() {
        return this.branchFacade.getExpireMessage();
    }

    /**
     * @param name
     * @param status
     * @param tel
     * @param leaderName
     * @param type
     * @return
     */

    private MapContext createdMapContext(String name, Integer status, String tel, String leaderName, Integer type) {
        MapContext mapContext = new MapContext();
        if (name != null) {
            mapContext.put(WebConstant.KEY_ENTITY_NAME, name);
        }
        if (status != null) {
            mapContext.put(WebConstant.KEY_ENTITY_STATUS, status);
        }
        if (tel != null) {
            mapContext.put("tel", tel);
        }
        if (leaderName != null) {
            mapContext.put("leaderName", leaderName);
        }
        if (type != null) {
            mapContext.put("type", type);
        }
        return mapContext;
    }

}
