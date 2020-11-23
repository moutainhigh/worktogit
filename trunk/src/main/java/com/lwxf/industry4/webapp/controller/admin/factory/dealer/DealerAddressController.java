package com.lwxf.industry4.webapp.controller.admin.factory.dealer;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.dealer.WxDealerAddressDto;
import com.lwxf.industry4.webapp.domain.entity.dealer.DealerAddress;
import com.lwxf.industry4.webapp.facade.admin.factory.dealer.DealerAddressFacade;
import com.lwxf.mybatis.utils.MapContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 功能：pc端销商收货地址管理
 *
 * @author：DJL
 * @create：2019/12/2 10:21
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Api(value="DealerAddressController",tags={"F端后台管理接口:经销商收货地址管理"})
@RestController
@RequestMapping(value = "/api/f/companyAddress", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
public class DealerAddressController {
    @Resource(name = "dealerAddressFacade")
    private DealerAddressFacade dealerAddressFacade;

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
            @ApiImplicitParam(name = "pageSize", value = "页数", dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "companyId", value = "经销商ID", dataType = "string", paramType = "query")
    })
    public RequestResult findfindAllWxAddress(@RequestParam(required = false) Integer pageNum,
                                              @RequestParam(required = false) Integer pageSize,
                                              @RequestParam(required = true) String companyId) {
        String cid = companyId;
        if (null == pageNum) {
            pageNum = 1;
        }
        if (null == pageSize) {
            Integer count = this.dealerAddressFacade.countByCid(cid);
            pageSize = count;
        }

        MapContext mapContext = MapContext.newOne();
        mapContext.put("companyId", cid);

        return this.dealerAddressFacade.findAllWxAddress(pageNum, pageSize, mapContext);
    }

    /**
     * 新增经销商收货地址
     * @param dealerAddress
     * @return
     */
    @PostMapping
    @ApiOperation(value = "新增经销商收货地址", notes = "新增经销商收货地址", response = WxDealerAddressDto.class)
    public RequestResult addWxAddress(@RequestBody DealerAddress dealerAddress) {
        dealerAddress.setCreator(WebUtils.getCurrUserId()); // 创建人
        dealerAddress.setCreated(DateUtil.getSystemDate()); // 创建时间
        RequestResult result = dealerAddress.validateFields(); // 验证参数合法性
        if (result != null) {
            return result; // 验证失败返回
        }

        return this.dealerAddressFacade.addWxAddress(dealerAddress);
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
        RequestResult result = DealerAddress.validateFields(mapContext);
        if (result != null) {
            return result; // 验证失败返回
        }
        mapContext.put("id", addressId);

        return this.dealerAddressFacade.updateWxAddress(mapContext);
    }

    /**
     * 删除经销商收货地址
     * @param addressId
     * @return
     */
    @DeleteMapping("/{addressId}")
    @ApiOperation(value = "删除经销商收货地址", notes = "删除经销商收货地址")
    public RequestResult deleteWxAddress(@PathVariable(value = "addressId", required = true) String addressId) {

        return this.dealerAddressFacade.deleteWxAddress(addressId);
    }

    /**
     * 根据id查询经销商收货地址详情
     * @param addressId
     * @return
     */
    @GetMapping("/{addressId}")
    @ApiOperation(value = "查询经销商收货地址详情", notes = "查询经销商收货地址详情", response = WxDealerAddressDto.class)
    public RequestResult findWxAddressDetail(@PathVariable(value = "addressId", required = true) String addressId) {

        return this.dealerAddressFacade.findWxAddressInfo(addressId);
    }

    /**
     * 查询指定经销商的默认收货地址
     * @return
     */
    @GetMapping("/default/{companyId}")
    @ApiOperation(value = "查询经销商默认的收货地址", notes = "查询经销商默认的收货地址", response = WxDealerAddressDto.class)
    public RequestResult findDefaultWxAddress(@PathVariable(value = "companyId", required = true) String companyId) {

        String cid = companyId;

        return this.dealerAddressFacade.findDefaultWxAddress(cid);
    }

}
