package com.lwxf.industry4.webapp.controller.admin.factory.dealer;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.LogisticsCompany.LogisticsCompanyStatus;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.dealer.WxDealerLogisticsDto;
import com.lwxf.industry4.webapp.domain.entity.dealer.DealerLogistics;
import com.lwxf.industry4.webapp.domain.entity.system.LogisticsCompany;
import com.lwxf.industry4.webapp.facade.admin.factory.dealer.DealerLogisticsFacade;
import com.lwxf.mybatis.utils.MapContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 功能：B端小程序经销商物流公司管理
 *
 * @author：DJL
 * @create：2019/12/2 10:21
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Api(value="DealerLogisticsController",tags={"F端后台管理接口:经销商物流公司管理"})
@RestController
@RequestMapping(value = "/api/f/companyLogistics", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class DealerLogisticsController {
    @Resource(name = "dealerLogisticsFacade")
    private DealerLogisticsFacade dealerLogisticsFacade;

    /**
     * 分页查询经销商物流公司列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping
    @ApiOperation(value = "查询经销商物流公司列表", notes = "查询经销商物流公司列表", response = WxDealerLogisticsDto.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "页数", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "companyId", value = "经销商ID", dataType = "string", paramType = "query")
    })
    public RequestResult findfindAllWxLogistics(@RequestParam(required = false) Integer pageNum,
                                                @RequestParam(required = false) Integer pageSize,
                                                @RequestParam(required = true) String companyId) {

        String cid = companyId;
        if (null == pageNum) {
            pageNum = 1;
        }
        if (null == pageSize) {
            Integer count = this.dealerLogisticsFacade.countByCid(cid);
            pageSize = count;
        }

        MapContext mapContext = MapContext.newOne();
        mapContext.put("companyId", cid);

        return this.dealerLogisticsFacade.findAllWxLogistics(pageNum, pageSize, mapContext);
    }

    /**
     * 新增经销商物流公司
     * @param dealerLogistics
     * @return
     */
    @PostMapping
    @ApiOperation(value = "新增经销商物流公司", notes = "新增经销商物流公司", response = WxDealerLogisticsDto.class)
    public RequestResult addWxLogistics(@RequestBody DealerLogistics dealerLogistics) {
        dealerLogistics.setCreator(WebUtils.getCurrUserId()); // 创建人
        dealerLogistics.setCreated(DateUtil.getSystemDate()); // 创建时间
        RequestResult result = dealerLogistics.validateFields(); // 验证参数合法性
        if (result != null) {
            return result; // 验证失败返回
        }

        return this.dealerLogisticsFacade.addWxLogistics(dealerLogistics);
    }

    /**
     * 修改经销商物流公司
     * @param logisticsId
     * @param mapContext
     * @return
     */
    @PutMapping("/{logisticsId}")
    @ApiOperation(value = "修改经销商物流公司", notes = "修改经销商物流公司", response = WxDealerLogisticsDto.class)
    public RequestResult updateWxLogistics(@PathVariable(value = "logisticsId", required = true) String logisticsId,
                                         @RequestBody MapContext mapContext) {
        RequestResult result = DealerLogistics.validateFields(mapContext);
        if (result != null) {
            return result; // 验证失败返回
        }
        mapContext.put("id", logisticsId);

        return this.dealerLogisticsFacade.updateWxLogistics(mapContext);
    }

    /**
     * 删除经销商物流公司
     * @param logisticsId
     * @return
     */
    @DeleteMapping("/{logisticsId}")
    @ApiOperation(value = "删除经销商物流公司", notes = "删除经销商物流公司")
    public RequestResult deleteWxLogistics(@PathVariable(value = "logisticsId", required = true) String logisticsId) {

        return this.dealerLogisticsFacade.deleteWxLogistics(logisticsId);
    }

    /**
     * 根据id查询经销商的物流公司详情
     * @param logisticsId
     * @return
     */
    @GetMapping("/{logisticsId}")
    @ApiOperation(value = "查询经销商物流公司详情", notes = "查询经销商物流公司详情", response = WxDealerLogisticsDto.class)
    public RequestResult findWxLogisticsDetail(@PathVariable(value = "logisticsId", required = true) String logisticsId) {

        return this.dealerLogisticsFacade.findWxLogisticsInfo(logisticsId);
    }

    /**
     * 查询经销商的默认物流公司
     * @return
     */
    @GetMapping("/default/{companyId}")
    @ApiOperation(value = "查询经销商默认的默认物流公司", notes = "查询经销商默认的默认物流公司", response = WxDealerLogisticsDto.class)
    public RequestResult findDefaultWxLogistics(@PathVariable(value = "companyId", required = true) String companyId) {

        String cid = companyId;

        return this.dealerLogisticsFacade.findDefaultWxLogistics(cid);
    }

    /**
     * 查询工厂正常的物流公司
     * @return
     */
    @GetMapping("/factoryLogistics")
    @ApiOperation(value = "查询工厂正常的物流公司", notes = "查询工厂正常的物流公司", response = LogisticsCompany.class)
    public RequestResult findAllFactoryLogistics() {
        MapContext mapContext = MapContext.newOne();
        mapContext.put("branchId", WebUtils.getCurrBranchId()); // 企业ID
        mapContext.put("status", LogisticsCompanyStatus.NORMAL.getValue()); // 正常的物流公司
        return this.dealerLogisticsFacade.findAllFactoryLogistics(mapContext);
    }

}
