package com.lwxf.industry4.webapp.facade.wxapi.dealer.company.impl;

import com.lwxf.industry4.webapp.bizservice.dealer.DealerAddressService;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.domain.dto.dealer.WxDealerAddressDto;
import com.lwxf.industry4.webapp.domain.entity.dealer.DealerAddress;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.wxapi.dealer.company.BWxAddressFacade;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


/**
 * 功能：
 *
 * @author：DJL
 * @create：2019/11/28 11:49
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component(value = "bWxAddressFacade")
public class BWxAddressFacadeImpl extends BaseFacadeImpl implements BWxAddressFacade {

    @Autowired
    private DealerAddressService dealerAddressService;

    @Override
    public RequestResult findAllWxAddress(Integer pageNum, Integer pageSize, MapContext mapContext) {
        //分页查询处理
        Pagination pagination = new Pagination();
        pagination.setPageNum(pageNum);
        pagination.setPageSize(pageSize);
        PaginatedFilter paginatedFilter = new PaginatedFilter();
        paginatedFilter.setPagination(pagination);
        paginatedFilter.setFilters(mapContext);
        PaginatedList<WxDealerAddressDto> paginatedList = this.dealerAddressService.selectDtoByFilter(paginatedFilter);
        MapContext result = MapContext.newOne();
        result.put("result", paginatedList.getRows());
        return ResultFactory.generateRequestResult(result, paginatedList.getPagination());
    }

    @Override
    public RequestResult findWxAddressInfo(String addressId) {
        WxDealerAddressDto dto = this.dealerAddressService.findDtoById(addressId);
        if (null == dto) {
            return ResultFactory.generateResNotFoundResult();
        }
        return ResultFactory.generateRequestResult(dto);
    }

    @Override
    public RequestResult findDefaultWxAddress(String cid) {
        WxDealerAddressDto dto = this.dealerAddressService.findDefaultDto(cid);
        if (null == dto) {
            return ResultFactory.generateResNotFoundResult();
        }
        return ResultFactory.generateRequestResult(dto);
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult addWxAddress(DealerAddress dealerAddress) {
        String cid = dealerAddress.getCompanyId();
        Integer count = this.dealerAddressService.countByCid(cid);
        // 第一次添加
        if (null == count || count == 0) {
            dealerAddress.setChecked(1); // 设为默认地址
        }
        if (dealerAddress.getChecked()==1) {
            // 其他地址取消默认选择
            this.dealerAddressService.cancelCheckedByCid(cid);
        }
        // 新增收货地址
        this.dealerAddressService.add(dealerAddress);
        return ResultFactory.generateRequestResult(dealerAddress);
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult updateWxAddress(MapContext mapContext) {
        DealerAddress address = this.dealerAddressService.findById((String) mapContext.get("id"));
        if (null == address) {
            return ResultFactory.generateResNotFoundResult();
        }
        // 将默认地址设置为非默认，提示操作失败
        if (address.getChecked() == 1) {
            Integer oldChecked = address.getChecked();
            Integer newChecked = mapContext.getTypedValue("checked", Integer.class);
            if (!newChecked.equals(oldChecked)) {
                return ResultFactory.generateErrorResult(ErrorCodes.BIZ_NOT_ALLOW_OPERATION_10020, AppBeanInjector.i18nUtil.getMessage("BIZ_NOT_ALLOW_OPERATION_10020"));

            }
        }
        if (mapContext.getTypedValue("checked", Integer.class) == 1) {
            // 其他地址取消默认选择
            this.dealerAddressService.cancelCheckedByCid(address.getCompanyId());
        }
        this.dealerAddressService.updateByMapContext(mapContext);
        return ResultFactory.generateRequestResult(this.dealerAddressService.findDtoById(address.getId()));
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult deleteWxAddress(String addressId) {
        DealerAddress address = this.dealerAddressService.findById(addressId);
        if (null == address) {
            return ResultFactory.generateSuccessResult();
        }
        // 删除的是默认地址，提示操作失败
//        if (address.getChecked() == 1) {
//            return ResultFactory.generateErrorResult(ErrorCodes.BIZ_NOT_ALLOW_OPERATION_10020, AppBeanInjector.i18nUtil.getMessage("BIZ_NOT_ALLOW_OPERATION_10020"));
//        }
        this.dealerAddressService.deleteById(addressId);
        return ResultFactory.generateSuccessResult();
    }

    @Override
    public Integer countByCid(String cid) {
        return this.dealerAddressService.countByCid(cid);
    }
}
