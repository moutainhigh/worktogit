package com.lwxf.industry4.webapp.facade.wxapi.dealer.company.impl;

import com.lwxf.industry4.webapp.bizservice.dealer.DealerLogisticsService;
import com.lwxf.industry4.webapp.bizservice.system.LogisticsCompanyService;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.domain.dto.dealer.WxDealerLogisticsDto;
import com.lwxf.industry4.webapp.domain.entity.dealer.DealerLogistics;
import com.lwxf.industry4.webapp.domain.entity.system.LogisticsCompany;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.wxapi.dealer.company.BWxLogisticsFacade;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 功能：
 *
 * @author：DJL
 * @create：2019/11/29 10:29
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component(value = "bWxLogisticsFacade")
public class BWxLogisticsFacadeImpl extends BaseFacadeImpl implements BWxLogisticsFacade {

    @Autowired
    private DealerLogisticsService dealerLogisticsService;
    @Autowired
    private LogisticsCompanyService logisticsCompanyService;

    @Override
    public RequestResult findAllWxLogistics(Integer pageNum, Integer pageSize, MapContext mapContext) {
        //分页查询处理
        Pagination pagination = new Pagination();
        pagination.setPageNum(pageNum);
        pagination.setPageSize(pageSize);
        PaginatedFilter paginatedFilter = new PaginatedFilter();
        paginatedFilter.setPagination(pagination);
        paginatedFilter.setFilters(mapContext);
        PaginatedList<WxDealerLogisticsDto> paginatedList = this.dealerLogisticsService.selectDtoByFilter(paginatedFilter);
        MapContext result = MapContext.newOne();
        result.put("result", paginatedList.getRows());
        return ResultFactory.generateRequestResult(result, paginatedList.getPagination());
    }

    @Override
    public RequestResult findWxLogisticsInfo(String logisticsId) {
        WxDealerLogisticsDto dto = this.dealerLogisticsService.findDtoById(logisticsId);
        if (null == dto) {
            return ResultFactory.generateResNotFoundResult();
        }
        return ResultFactory.generateRequestResult(dto);
    }

    @Override
    public RequestResult findDefaultWxLogistics(String cid) {
        WxDealerLogisticsDto dto = this.dealerLogisticsService.findDefaultDto(cid);
        if (null == dto) {
            return ResultFactory.generateResNotFoundResult();
        }
        return ResultFactory.generateRequestResult(dto);
    }


    @Override
    @Transactional(value = "transactionManager")
    public RequestResult addWxLogistics(DealerLogistics dealerLogistics) {
        String cid = dealerLogistics.getCompanyId();
        String logisticsId = dealerLogistics.getLogisticsCompanyId();
        // 判断重复
        DealerLogistics dl = this.dealerLogisticsService.findByCidAndLid(cid, logisticsId);
        if (dl != null) {
            return ResultFactory.generateErrorResult(ErrorCodes.BIZ_NO_REPETITION_ALLOWED_10094, AppBeanInjector.i18nUtil.getMessage("BIZ_NO_REPETITION_ALLOWED_10094"));
        }
        Integer count = this.dealerLogisticsService.countByCid(cid);
        // 第一次添加
        if (null == count || count == 0) {
            dealerLogistics.setChecked(new Byte("1")); // 设为默认地址
        }
        if (dealerLogistics.getChecked().equals(new Byte("1"))) {
            // 其他地址取消默认选择
            this.dealerLogisticsService.cancelCheckedByCid(cid);
        }
        // 新增收货地址
        this.dealerLogisticsService.add(dealerLogistics);
        return ResultFactory.generateRequestResult(dealerLogistics);
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult updateWxLogistics(MapContext mapContext) {
        DealerLogistics dealerLogistics = this.dealerLogisticsService.findById((String) mapContext.get("id"));
        if (null == dealerLogistics) {
            return ResultFactory.generateResNotFoundResult();
        }
        // 将默认地址设置为非默认，提示操作失败
        if (dealerLogistics.getChecked() == 1) {
            Byte oldChecked = dealerLogistics.getChecked();
            Byte newChecked = mapContext.getTypedValue("checked", Byte.class);
            if (!newChecked.equals(oldChecked)) {
                return ResultFactory.generateErrorResult(ErrorCodes.BIZ_NOT_ALLOW_OPERATION_10020, AppBeanInjector.i18nUtil.getMessage("BIZ_NOT_ALLOW_OPERATION_10020"));

            }
        }
        if (mapContext.getTypedValue("checked", Byte.class) == 1) {
            // 其他地址取消默认选择
            this.dealerLogisticsService.cancelCheckedByCid(dealerLogistics.getCompanyId());
        }
        this.dealerLogisticsService.updateByMapContext(mapContext);
        return ResultFactory.generateRequestResult(this.dealerLogisticsService.findDtoById(dealerLogistics.getId()));
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult deleteWxLogistics(String logisticsId) {
        DealerLogistics dealerLogistics = this.dealerLogisticsService.findById(logisticsId);
        if (null == dealerLogistics) {
            return ResultFactory.generateSuccessResult();
        }
        // 删除的是默认地址，提示操作失败
//        if (dealerLogistics.getChecked() == 1) {
//            return ResultFactory.generateErrorResult(ErrorCodes.BIZ_NOT_ALLOW_OPERATION_10020, AppBeanInjector.i18nUtil.getMessage("BIZ_NOT_ALLOW_OPERATION_10020"));
//        }
        this.dealerLogisticsService.deleteById(logisticsId);
        return ResultFactory.generateSuccessResult();
    }

    @Override
    public Integer countByCid(String cid) {
        return this.dealerLogisticsService.countByCid(cid);
    }


    /**
     * 查询工厂提供的所有物流公司列表
     * @param mapContext
     * @return
     */
    @Override
    public RequestResult findAllFactoryLogistics(MapContext mapContext) {
        List<LogisticsCompany> list = this.logisticsCompanyService.findAllNormalByBranchId(mapContext);
        if (null == list) {
            return ResultFactory.generateResNotFoundResult();
        }
        return ResultFactory.generateRequestResult(list);
    }
}
