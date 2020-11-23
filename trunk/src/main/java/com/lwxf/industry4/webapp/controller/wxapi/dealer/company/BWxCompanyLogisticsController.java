package com.lwxf.industry4.webapp.controller.wxapi.dealer.company;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.LogisticsCompany.LogisticsCompanyStatus;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.utils.LoginUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.controller.wxapi.dealer.common.BaseController;
import com.lwxf.industry4.webapp.domain.dto.dealer.WxDealerLogisticsDto;
import com.lwxf.industry4.webapp.domain.entity.dealer.DealerLogistics;
import com.lwxf.industry4.webapp.domain.entity.system.LogisticsCompany;
import com.lwxf.industry4.webapp.facade.wxapi.dealer.company.BWxLogisticsFacade;
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
 * @create：2019/11/29 15:51
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Api(value="BWxCompanyLogisticsController",tags={"B端微信小程序接口:物流公司管理"})
@RestController
@RequestMapping(value = "/wxapi/b/companyLogistics", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class BWxCompanyLogisticsController extends BaseController {
    @Resource(name = "bWxLogisticsFacade")
    private BWxLogisticsFacade bWxLogisticsFacade;

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
            @ApiImplicitParam(name = "pageSize", value = "页数", dataType = "integer", paramType = "query")
    })
    public RequestResult findfindAllWxLogistics(@RequestParam(required = false) Integer pageNum,
                                              @RequestParam(required = false) Integer pageSize) {
        String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        RequestResult checkResult = this.check(mapInfo);
        if (checkResult != null) {
            return checkResult; // 验证失败返回
        }
        String cid = mapInfo.get("companyId").toString();
        if (null == pageNum) {
            pageNum = 1;
        }
        if (null == pageSize) {
            Integer count = this.bWxLogisticsFacade.countByCid(cid);
            pageSize = count;
        }

        MapContext mapContext = MapContext.newOne();
        mapContext.put("companyId", cid);

        return this.bWxLogisticsFacade.findAllWxLogistics(pageNum, pageSize, mapContext);
    }

    /**
     * 新增经销商物流公司
     * @param dealerLogistics
     * @return
     */
    @PostMapping
    @ApiOperation(value = "新增经销商物流公司", notes = "新增经销商物流公司", response = WxDealerLogisticsDto.class)
    public RequestResult addWxLogistics(@RequestBody DealerLogistics dealerLogistics) {
        String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        RequestResult checkResult = this.check(mapInfo);
        if (checkResult != null) {
            return checkResult; // 验证失败返回
        }
        dealerLogistics.setCreator(mapInfo.get("userId").toString()); // 创建人
        dealerLogistics.setCompanyId(mapInfo.get("companyId").toString()); // 经销商id
        dealerLogistics.setCreated(DateUtil.getSystemDate()); // 创建时间
        RequestResult result = dealerLogistics.validateFields(); // 验证参数合法性
        if (result != null) {
            return result; // 验证失败返回
        }

        return this.bWxLogisticsFacade.addWxLogistics(dealerLogistics);
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
        String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        RequestResult checkResult = this.check(mapInfo);
        if (checkResult != null) {
            return checkResult; // 验证失败返回
        }
        RequestResult result = DealerLogistics.validateFields(mapContext);
        if (result != null) {
            return result; // 验证失败返回
        }
        mapContext.put("id", logisticsId);

        return this.bWxLogisticsFacade.updateWxLogistics(mapContext);
    }

    /**
     * 删除经销商物流公司
     * @param logisticsId
     * @return
     */
    @DeleteMapping("/{logisticsId}")
    @ApiOperation(value = "删除经销商物流公司", notes = "删除经销商物流公司")
    public RequestResult deleteWxLogistics(@PathVariable(value = "logisticsId", required = true) String logisticsId) {
        String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        RequestResult checkResult = this.check(mapInfo);
        if (checkResult != null) {
            return checkResult; // 验证失败返回
        }

        return this.bWxLogisticsFacade.deleteWxLogistics(logisticsId);
    }

    /**
     * 根据id查询经销商的物流公司详情
     * @param logisticsId
     * @return
     */
    @GetMapping("/{logisticsId}")
    @ApiOperation(value = "查询经销商物流公司详情", notes = "查询经销商物流公司详情", response = WxDealerLogisticsDto.class)
    public RequestResult findWxLogisticsDetail(@PathVariable(value = "logisticsId", required = true) String logisticsId) {
        String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        RequestResult checkResult = this.check(mapInfo);
        if (checkResult != null) {
            return checkResult; // 验证失败返回
        }

        return this.bWxLogisticsFacade.findWxLogisticsInfo(logisticsId);
    }

    /**
     * 查询经销商的默认物流公司
     * @return
     */
    @GetMapping("/default")
    @ApiOperation(value = "查询经销商默认的默认物流公司", notes = "查询经销商默认的默认物流公司", response = WxDealerLogisticsDto.class)
    public RequestResult findDefaultWxLogistics() {
        String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        RequestResult checkResult = this.check(mapInfo);
        if (checkResult != null) {
            return checkResult; // 验证失败返回
        }

        String cid = mapInfo.get("companyId").toString();

        return this.bWxLogisticsFacade.findDefaultWxLogistics(cid);
    }

    /**
     * 查询工厂正常的物流公司
     * @return
     */
    @GetMapping("/factoryLogistics")
    @ApiOperation(value = "查询工厂正常的物流公司", notes = "查询工厂正常的物流公司", response = LogisticsCompany.class)
    public RequestResult findAllFactoryLogistics() {
        String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        RequestResult checkResult = this.check(mapInfo);
        if (checkResult != null) {
            return checkResult; // 验证失败返回
        }
        MapContext mapContext = MapContext.newOne();
        mapContext.put("branchId", mapInfo.get("branchId").toString()); // 企业ID
        mapContext.put("status", LogisticsCompanyStatus.NORMAL.getValue()); // 正常的物流公司
        return this.bWxLogisticsFacade.findAllFactoryLogistics(mapContext);
    }

}
