package com.lwxf.industry4.webapp.facade.wxapi.factory.aftersale.impl;

import javax.annotation.Resource;

import java.util.*;

import org.springframework.stereotype.Component;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.bizservice.aftersale.AftersaleApplyFilesService;
import com.lwxf.industry4.webapp.bizservice.aftersale.AftersaleApplyService;
import com.lwxf.industry4.webapp.bizservice.common.CityAreaService;
import com.lwxf.industry4.webapp.bizservice.company.CompanyService;
import com.lwxf.industry4.webapp.bizservice.customer.CompanyCustomerService;
import com.lwxf.industry4.webapp.bizservice.customorder.CustomOrderService;
import com.lwxf.industry4.webapp.bizservice.customorder.OrderProductService;
import com.lwxf.industry4.webapp.bizservice.dispatch.DispatchBillService;
import com.lwxf.industry4.webapp.bizservice.financing.PaymentService;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentFunds;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentWay;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.customorder.WxCustomerOrderInfoDto;
import com.lwxf.industry4.webapp.domain.dto.financing.PaymentDto;
import com.lwxf.industry4.webapp.domain.entity.aftersale.AftersaleApply;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrder;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.wxapi.factory.aftersale.AftersaleFacade;
import com.lwxf.mybatis.utils.MapContext;

@Component("wxfAftersaleApplyFacade")
public class AftersaleFacadeImple  extends BaseFacadeImpl implements AftersaleFacade {

    @Resource(name = "aftersaleApplyService")
    private AftersaleApplyService aftersaleApplyService;
    @Resource(name = "companyService")
    private CompanyService companyService;
    @Resource(name = "customOrderService")
    private CustomOrderService customOrderService;
    @Resource(name = "aftersaleApplyFilesService")
    private AftersaleApplyFilesService aftersaleApplyFilesService;
    @Resource(name = "cityAreaService")
    private CityAreaService cityAreaService;
    @Resource(name = "companyCustomerService")
    private CompanyCustomerService companyCustomerService;
    @Resource(name = "dispatchBillService")
    private DispatchBillService dispatchBillService;
    @Resource(name = "orderProductService")
    private OrderProductService orderProductService;
    @Resource(name = "paymentService")
    private PaymentService paymentService;

    /**
     * 售后列表查询（按条件查询）
     *
     * @param pageNum
     * @param pageSize
     * @param mapContext
     * @return
     */
    @Override
    public RequestResult findAftersaleApplyList(String branchId,Integer pageNum, Integer pageSize, MapContext mapContext) {
        PaginatedFilter paginatedFilter = new PaginatedFilter();
        Pagination pagination = new Pagination();
        pagination.setPageSize(pageSize);
        pagination.setPageNum(pageNum);
        paginatedFilter.setPagination(pagination);
        mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID, branchId);
        paginatedFilter.setFilters(mapContext);
        PaginatedList<MapContext> aftersalesList = this.aftersaleApplyService.findWxAftersaleApplyList(paginatedFilter);
        MapContext result=MapContext.newOne();
        result.put("dataList",aftersalesList.getRows());
        //上测统计
        Map<String,Long> map =  this.aftersaleApplyService.countAftersale(branchId);
        result.put("fankuidan",map.get("fankuidan")==null?0:map.get("fankuidan"));
        result.put("buliaodan",map.get("buliaodan")==null?0:map.get("buliaodan"));
        result.put("totalAftersale",map.get("totalAftersale")==null?0:map.get("totalAftersale"));
        return ResultFactory.generateRequestResult(result,aftersalesList.getPagination());
    }

    /**
     * 售后详情
     *
     * @param aftersaleApplyId
     * @return
     */
    @Override
    public RequestResult factoryAftersaleApplyInfo(String aftersaleApplyId) {
        MapContext result = MapContext.newOne();
        //售后信息
        AftersaleApply aftersaleApply = this.aftersaleApplyService.findOneById(aftersaleApplyId);
        if (aftersaleApply == null) {
            return ResultFactory.generateResNotFoundResult();
        }
        //源订单信息
        String orderId = aftersaleApply.getCustomOrderId();
        WxCustomerOrderInfoDto wxCustomOrderInfoDto = this.customOrderService.findWxOrderByorderId(orderId);
        if (wxCustomOrderInfoDto != null) {
        //剩余时间计算
        Date estimatedDeliveryDate = wxCustomOrderInfoDto.getEstimatedDeliveryDate();//预计交货时间
        Date deliveryDate = wxCustomOrderInfoDto.getDeliveryDate();//实际交货时间
        Date systemDate = DateUtil.getSystemDate();//系统当前时间
        if (deliveryDate != null) {//实际交货时间不为空，则订单生产已结束
            wxCustomOrderInfoDto.setTimeRemaining("00:00:00");
            wxCustomOrderInfoDto.setTimeRemainingStatus(0);
        } else {
            if (estimatedDeliveryDate != null) {
                long nd = 1000 * 24 * 60 * 60;//一天的毫秒数
                long nh = 1000 * 60 * 60;//一小时的毫秒数
                long nm = 1000 * 60;//一分钟的毫秒数
                long ns = 1000;//一秒钟的毫秒数
                //预计交货时间毫秒
                long estimatedDeliveryDateValue = estimatedDeliveryDate.getTime();
                //系统时间
                long systemDateTime = systemDate.getTime();
                //预计交货时间毫秒-下车间的时间 = 剩余的时间
                long diff = (estimatedDeliveryDateValue - systemDateTime);
                if (diff > 0) {//如果剩余时间大于0，时间交货时间为空
                    long day = diff / nd;//计算剩余多少天
                    long hour = diff % nd / nh;//计算剩余多少小时
                    long min = diff % nd % nh / nm;//计算剩余多少分钟
                    String residueTime = day + "天" + hour + "小时" + min + "分钟";
                    wxCustomOrderInfoDto.setTimeRemaining(residueTime);
                    if (day > 1) {
                        wxCustomOrderInfoDto.setTimeRemainingStatus(1);
                    } else if (day < 1) {
                        wxCustomOrderInfoDto.setTimeRemainingStatus(2);
                    }
                } else {
                    wxCustomOrderInfoDto.setTimeRemainingStatus(3);
                    long diffValue = systemDateTime - estimatedDeliveryDateValue;
                    long day = diffValue / nd;//计算超期多少天
                    long hour = diffValue % nd / nh;//计算超期多少小时
                    long min = diffValue % nd % nh / nm;//计算超期多少分钟
                    String residueTime = "-" + day + "天" + hour + "小时" + min + "分钟";
                    wxCustomOrderInfoDto.setTimeRemaining(residueTime);
                }
            }
        }
        }
        result.put("customOrder", wxCustomOrderInfoDto);
        result.put("aftersaleApply", aftersaleApply);
        //处理结果订单
        List<MapContext> aftersaleProduct = new ArrayList<>();
        List<Map> dispatchBillList = new ArrayList<>();
        String resultOrderId = aftersaleApply.getResultOrderId();
        MapContext aftersalePayInfo=MapContext.newOne();
        if(resultOrderId!=null&&!resultOrderId.equals("")) {
            CustomOrder customOrder = this.customOrderService.findByOrderId(resultOrderId);
            if (customOrder != null) {
                aftersalePayInfo.put("factoryPrice", customOrder.getFactoryPrice());
                aftersalePayInfo.put("factoryFinalPrice", customOrder.getFactoryFinalPrice());
                aftersalePayInfo.put("discountsNote", customOrder.getDiscountsNote() == null ? "" : customOrder.getDiscountsNote());
                //售后订单财务信息
                Integer value = PaymentFunds.ORDER_FEE_CHARGE.getValue();
                MapContext params = MapContext.newOne();
                params.put("orderId", resultOrderId);
                params.put("funds", value);
                PaymentDto payment = this.paymentService.findByOrderIdAndFunds(params);
                if (payment != null) {
                    if (payment.getStatus() == 1) {
                        if (payment.getWay() != null) {
                            if (payment.getWay() == PaymentWay.CASH.getValue()) {
                                aftersalePayInfo.put("auditorName", payment.getAuditorName());
                                aftersalePayInfo.put("audited", payment.getAudited());
                            } else {
                                aftersalePayInfo.put("auditorName", payment.getCreated());
                                aftersalePayInfo.put("audited", payment.getHolder());
                            }
                        }
                        aftersalePayInfo.put("financeStatus", "已到账");
                    } else {
                        aftersalePayInfo.put("financeStatus", "未到账");
                    }
                }
                //产品信息
                aftersaleProduct = this.aftersaleApplyService.findAftersaleProductByorderId(resultOrderId);
                //发货物流信息
                dispatchBillList = this.dispatchBillService.findDispatchList(resultOrderId);

            }
        }
        result.put("aftersalePayInfo", aftersalePayInfo);
        result.put("aftersaleProduct", aftersaleProduct);
        result.put("dispatchBillList", dispatchBillList);
        return ResultFactory.generateRequestResult(result);

    }

    @Override
    public RequestResult viewIndex(String branchId) {
        MapContext mapReturn = MapContext.newOne();
        //上测统计
        Map<String,Long> map =  this.aftersaleApplyService.countAftersale(branchId);
        mapReturn.put("fankuidan",map.get("fankuidan")==null?0:map.get("fankuidan"));
        mapReturn.put("buliaodan",map.get("buliaodan")==null?0:map.get("buliaodan"));
        mapReturn.put("totalAftersale",map.get("totalAftersale")==null?0:map.get("totalAftersale"));
        //下测列表
        PaginatedFilter paginatedFilter = new PaginatedFilter();
        Pagination pagination = new Pagination();
        pagination.setPageSize(15);
        pagination.setPageNum(1);
        paginatedFilter.setPagination(pagination);
        MapContext param = MapContext.newOne();
        param.put(WebConstant.KEY_ENTITY_BRANCH_ID, branchId);
        paginatedFilter.setFilters(param);
        List sorts = new ArrayList();
            Map<String,String> created = new HashMap<String, String>();
        created.put(WebConstant.KEY_ENTITY_CREATED,"desc");
        sorts.add(created);
        paginatedFilter.setSorts(sorts);
        PaginatedList<MapContext> aftersalesList = this.aftersaleApplyService.findAftersaleApplyList(paginatedFilter);

        mapReturn.put("dataList",aftersalesList);
        return  ResultFactory.generateRequestResult(mapReturn);
    }
}
