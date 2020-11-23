package com.lwxf.industry4.webapp.controller.wxapi.dealer.company;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.utils.LoginUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.controller.wxapi.dealer.common.BaseController;
import com.lwxf.industry4.webapp.domain.dto.dealer.WxDealerAddressDto;
import com.lwxf.industry4.webapp.domain.entity.dealer.DealerAddress;
import com.lwxf.industry4.webapp.facade.wxapi.dealer.company.BWxAddressFacade;
import com.lwxf.mybatis.utils.MapContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 功能：B端小程序经销商收货地址管理
 *
 * @author：DJL
 * @create：2019/11/28 15:53
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Api(value="BWxCompanyAddressController",tags={"B端微信小程序接口:收货地址管理"})
@RestController
@RequestMapping(value = "/wxapi/b/companyAddress", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class BWxCompanyAddressController extends BaseController {
    @Resource(name = "bWxAddressFacade")
    private BWxAddressFacade bWxAddressFacade;

    /**
     * 分页查询经销商收货地址列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping
    @ApiOperation(value = "查询经销商收货地址列表", notes = "查询经销商收货地址列表", response = WxDealerAddressDto.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "页数", dataType = "integer", paramType = "query")
    })
    public RequestResult findfindAllWxAddress(@RequestParam(required = false) Integer pageNum,
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
            Integer count = this.bWxAddressFacade.countByCid(cid);
            pageSize = count;
        }

        MapContext mapContext = MapContext.newOne();
        mapContext.put("companyId", cid);

        return this.bWxAddressFacade.findAllWxAddress(pageNum, pageSize, mapContext);
    }

    /**
     * 新增经销商收货地址
     * @param dealerAddress
     * @return
     */
    @PostMapping
    @ApiOperation(value = "新增经销商收货地址", notes = "新增经销商收货地址", response = WxDealerAddressDto.class)
    public RequestResult addWxAddress(@RequestBody DealerAddress dealerAddress) {
        String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        RequestResult checkResult = this.check(mapInfo);
        if (checkResult != null) {
            return checkResult; // 验证失败返回
        }
        dealerAddress.setCreator(mapInfo.get("userId").toString()); // 创建人
        dealerAddress.setCompanyId(mapInfo.get("companyId").toString()); // 经销商id
        dealerAddress.setCreated(DateUtil.getSystemDate()); // 创建时间
        RequestResult result = dealerAddress.validateFields(); // 验证参数合法性
        if (result != null) {
            return result; // 验证失败返回
        }

        return this.bWxAddressFacade.addWxAddress(dealerAddress);
    }

    /**
     * 修改经销商收货地址
     * @param addressId
     * @param mapContext
     * @return
     */
    @PutMapping("/{addressId}")
    @ApiOperation(value = "修改经销商收货地址", notes = "修改经销商收货地址", response = WxDealerAddressDto.class)
    public RequestResult updateWxAddress(@PathVariable(value = "addressId", required = true) String addressId,
                                         @RequestBody MapContext mapContext) {
        String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        RequestResult checkResult = this.check(mapInfo);
        if (checkResult != null) {
            return checkResult; // 验证失败返回
        }
        RequestResult result = DealerAddress.validateFields(mapContext);
        if (result != null) {
            return result; // 验证失败返回
        }
        mapContext.put("id", addressId);

        return this.bWxAddressFacade.updateWxAddress(mapContext);
    }

    /**
     * 删除经销商收货地址
     * @param addressId
     * @return
     */
    @DeleteMapping("/{addressId}")
    @ApiOperation(value = "删除经销商收货地址", notes = "删除经销商收货地址")
    public RequestResult deleteWxAddress(@PathVariable(value = "addressId", required = true) String addressId) {
        String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        RequestResult checkResult = this.check(mapInfo);
        if (checkResult != null) {
            return checkResult; // 验证失败返回
        }

        return this.bWxAddressFacade.deleteWxAddress(addressId);
    }

    /**
     * 根据id查询经销商收货地址详情
     * @param addressId
     * @return
     */
    @GetMapping("/{addressId}")
    @ApiOperation(value = "查询经销商收货地址详情", notes = "查询经销商收货地址详情", response = WxDealerAddressDto.class)
    public RequestResult findWxAddressDetail(@PathVariable(value = "addressId", required = true) String addressId) {
        String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        RequestResult checkResult = this.check(mapInfo);
        if (checkResult != null) {
            return checkResult; // 验证失败返回
        }

        return this.bWxAddressFacade.findWxAddressInfo(addressId);
    }

    /**
     * 查询指定经销商的默认收货地址
     * @return
     */
    @GetMapping("/default")
    @ApiOperation(value = "查询经销商默认的收货地址", notes = "查询经销商默认的收货地址", response = WxDealerAddressDto.class)
    public RequestResult findDefaultWxAddress() {
        String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
        MapContext mapInfo = LoginUtil.checkLogin(atoken);
        RequestResult checkResult = this.check(mapInfo);
        if (checkResult != null) {
            return checkResult; // 验证失败返回
        }

        String cid = mapInfo.get("companyId").toString();

        return this.bWxAddressFacade.findDefaultWxAddress(cid);
    }

}
