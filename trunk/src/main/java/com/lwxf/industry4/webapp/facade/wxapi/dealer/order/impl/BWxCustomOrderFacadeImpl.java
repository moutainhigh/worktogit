package com.lwxf.industry4.webapp.facade.wxapi.dealer.order.impl;

import javax.annotation.Resource;

import java.util.*;

import com.lwxf.commons.uniquekey.IdGererateFactory;
import com.lwxf.industry4.webapp.bizservice.common.CityAreaService;
import com.lwxf.industry4.webapp.bizservice.system.BasecodeService;
import com.lwxf.industry4.webapp.bizservice.system.LogisticsCompanyService;
import com.lwxf.industry4.webapp.bizservice.user.UserService;
import com.lwxf.industry4.webapp.common.component.UploadInfo;
import com.lwxf.industry4.webapp.common.constant.BizConstant;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.UploadResourceType;
import com.lwxf.industry4.webapp.domain.dao.aftersale.AftersaleApplyFilesDao;
import com.lwxf.industry4.webapp.domain.dao.common.UploadFilesDao;
import com.lwxf.industry4.webapp.domain.dao.customorder.CustomOrderFilesDao;
import com.lwxf.industry4.webapp.domain.dao.financing.PaymentDao;
import com.lwxf.industry4.webapp.domain.dto.customorder.*;
import com.lwxf.industry4.webapp.domain.dto.customorder.*;
import com.lwxf.industry4.webapp.domain.entity.aftersale.AftersaleApplyFiles;
import com.lwxf.industry4.webapp.domain.entity.common.CityArea;
import com.lwxf.industry4.webapp.domain.entity.common.UploadFiles;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderFiles;
import com.lwxf.industry4.webapp.domain.entity.system.Basecode;
import com.lwxf.industry4.webapp.domain.entity.system.LogisticsCompany;
import com.lwxf.industry4.webapp.domain.entity.user.User;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.system.LogisticsCompanyFacade;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.bizservice.customorder.CustomOrderDesignService;
import com.lwxf.industry4.webapp.bizservice.customorder.CustomOrderLogService;
import com.lwxf.industry4.webapp.bizservice.customorder.CustomOrderService;
import com.lwxf.industry4.webapp.bizservice.customorder.OrderProductService;
import com.lwxf.industry4.webapp.bizservice.dispatch.DispatchBillItemService;
import com.lwxf.industry4.webapp.bizservice.dispatch.DispatchBillService;
import com.lwxf.industry4.webapp.bizservice.financing.PaymentService;
import com.lwxf.industry4.webapp.bizservice.warehouse.FinishedStockItemService;
import com.lwxf.industry4.webapp.bizservice.warehouse.FinishedStockService;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentFunds;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentWay;
import com.lwxf.industry4.webapp.common.enums.order.OrderStatus;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.domain.dto.financing.PaymentDto;
import com.lwxf.industry4.webapp.domain.dto.warehouse.FinishedStockDto;
import com.lwxf.industry4.webapp.domain.dto.warehouse.FinishedStockItemDto;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.wxapi.dealer.order.BWxCustomOrderFacade;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import static com.lwxf.industry4.webapp.facade.AppBeanInjector.baseFileUploadComponent;
import static com.lwxf.industry4.webapp.facade.AppBeanInjector.i18nUtil;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/6/19 0019 17:35
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component(value = "bWxCustomOrderFacade")
public class BWxCustomOrderFacadeImpl extends BaseFacadeImpl implements BWxCustomOrderFacade {
    @Resource(name = "customOrderService")
    private CustomOrderService customOrderService;
    @Resource(name = "orderProductService")
    private OrderProductService orderProductService;
    @Resource(name = "customOrderDesignService")
    private CustomOrderDesignService customOrderDesignService;
    @Resource(name = "paymentService")
    private PaymentService paymentService;
    @Resource(name = "dispatchBillItemService")
    private DispatchBillItemService dispatchBillItemService;
    @Resource(name = "finishedStockItemService")
    private FinishedStockItemService finishedStockItemService;
    @Resource(name = "dispatchBillService")
    private DispatchBillService dispatchBillService;
    @Resource(name = "finishedStockService")
    private FinishedStockService finishedStockService;
    @Resource(name = "customOrderLogService")
    private CustomOrderLogService customOrderLogService;
    @Resource(name = "basecodeService")
    private BasecodeService basecodeService;
    @Resource(name = "userService")
    private UserService userService;
    @Resource(name = "paymentDao")
    private PaymentDao paymentDao;
    @Resource(name = "aftersaleApplyFilesDao")
    private AftersaleApplyFilesDao aftersaleApplyFilesDao;
    @Resource
    private IdGererateFactory idGererateFactory;
    @Resource(name = "uploadFilesDao")
    private UploadFilesDao uploadFilesDao;
    @Resource(name = "cityAreaService")
    private CityAreaService cityAreaService;

    @Resource(name = "customOrderFilesDao")
    private CustomOrderFilesDao customOrderFilesDao;

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult orderUpdatefiles(String userId, MultipartFile multipartFile) {
        UploadInfo uploadInfo = baseFileUploadComponent.doUploadByModule(multipartFile, UploadResourceType.MATERIAL, userId);
        CustomOrderFiles uploadFiles = new CustomOrderFiles();
        uploadFiles.setId(idGererateFactory.nextStringId());
        uploadFiles.setCreator(userId);//用户id
        uploadFiles.setFullPath(uploadInfo.getRealPath());//附件路径
        String contentType = multipartFile.getContentType();
        uploadFiles.setMime(contentType);
        uploadFiles.setName(multipartFile.getName());
        uploadFiles.setStatus(1);
        uploadFiles.setCreated(new Date());
        uploadFiles.setPath(uploadInfo.getRelativePath());
        uploadFiles.setOriginalMime(uploadInfo.getFileMimeType().getOriginalType());
        uploadFiles.setType(0);
        uploadFiles.setCategory(0);
        customOrderFilesDao.insert(uploadFiles);
        return ResultFactory.generateRequestResult(uploadFiles);
    }


    @Override
    @Transactional(value = "transactionManager")
    public RequestResult insertCoupleBackUpdatefiles(String userId, MultipartFile multipartFile) {
        UploadInfo uploadInfo = baseFileUploadComponent.doUploadByModule(multipartFile, UploadResourceType.MATERIAL, userId);
        AftersaleApplyFiles aftersaleApplyFiles = new AftersaleApplyFiles();
        aftersaleApplyFiles.setId(idGererateFactory.nextStringId());
        aftersaleApplyFiles.setCreator(userId);//用户id
        aftersaleApplyFiles.setFullPath(uploadInfo.getRealPath());//附件路径
        String contentType = multipartFile.getContentType();
        aftersaleApplyFiles.setMime(contentType);
        aftersaleApplyFiles.setName(multipartFile.getName());
        aftersaleApplyFiles.setStatus(1);
        aftersaleApplyFiles.setCreated(new Date());
        aftersaleApplyFiles.setPath(uploadInfo.getRelativePath());
        aftersaleApplyFiles.setOriginalMime(uploadInfo.getFileMimeType().getOriginalType());
        aftersaleApplyFilesDao.insert(aftersaleApplyFiles);
        return ResultFactory.generateRequestResult(aftersaleApplyFiles);
    }


    @Override
    public RequestResult QueryOrdersTrace(String orderId) {
        WxCustomerOrderInfoDto wxCustomOrderInfoDto = this.customOrderService.findWxOrderByorderId(orderId);
        if (wxCustomOrderInfoDto == null) {//不存在订单就不查询
            return ResultFactory.generateResNotFoundResult();
        }
        Date orderCreated = wxCustomOrderInfoDto.getOrderCreated();//下单时间
        //支付时间
        List<PaymentDto> orderPaymentInfo = paymentDao.findByOrderIdfunds31(orderId);
        Date audited = null;
        Date payTime = null;
        if(orderPaymentInfo !=null && orderPaymentInfo.size()!=0){
            PaymentDto paymentDto = orderPaymentInfo.get(0);
            audited = paymentDto.getAudited();//审核时间
            payTime = paymentDto.getPayTime();
        }


        //产品开始生产时间
        List<OrderProductDto> listByOrderId = orderProductService.findListByOrderId(orderId);
        OrderProductDto orderProductInfo = null;
        if(listByOrderId !=null && listByOrderId.size() !=0){
            orderProductInfo = listByOrderId.get(0);
        }

        Date created = null;
        String creatorName = null;
        Date planFinishTime =null;
        String stockInputCreatorName = null;
        Date deliveryTime = null;
        String deliveryCreatorName =null;
        if(orderProductInfo!=null){
            created = orderProductInfo.getStartProduceTime();//开始生产时间
            creatorName = orderProductInfo.getCreatorName();//负责人
            //产品入库时间
            planFinishTime = orderProductInfo.getStockInput();//入库时间
            stockInputCreatorName = orderProductInfo.getStockInputCreatorName();//入库人
            //发货时间
            deliveryTime = orderProductInfo.getDeliveryTime();//发货时间
            deliveryCreatorName = orderProductInfo.getDeliveryCreatorName();//发货人
        }


        //预计交货时间
        Date estimatedDeliveryDate = wxCustomOrderInfoDto.getEstimatedDeliveryDate();
        //实际交货日期
        Date deliveryDate = wxCustomOrderInfoDto.getDeliveryDate();

        ArrayList<WxOrderTrace> QueryOrdersTraceList = new ArrayList<>();
        //1 头信息
        WxOrderTrace wxOrderTrace = new WxOrderTrace();
        wxOrderTrace.setOrderId(orderId);
        wxOrderTrace.setEstimatedDeliveryDate(estimatedDeliveryDate);//预计交货时间
        wxOrderTrace.setOrderPayTime(payTime);//支付时间
        if (payTime != null) {
            QueryOrdersTraceList.add(wxOrderTrace);
        }
        //2 下单信息
        WxOrderTrace wxOrderTrace2 = new WxOrderTrace();
        wxOrderTrace2.setOrderId(orderId);
        wxOrderTrace2.setTraceName("已下单");
        wxOrderTrace2.setTraceInfo("你提交了订单");
        wxOrderTrace2.setTraceTime(orderCreated);
        if(orderCreated != null){
            QueryOrdersTraceList.add(wxOrderTrace2);
        }
        //3 支付信息
        WxOrderTrace wxOrderTrace3 = new WxOrderTrace();
        wxOrderTrace3.setOrderId(orderId);
        wxOrderTrace3.setTraceName("订单支付");
        wxOrderTrace3.setTraceInfo("你支付了订单");
        wxOrderTrace3.setTraceTime(payTime);
        if(payTime != null){
            QueryOrdersTraceList.add(wxOrderTrace3);

        }
        //4 生产信息
        WxOrderTrace wxOrderTrace4 = new WxOrderTrace();
        wxOrderTrace4.setOrderId(orderId);
        wxOrderTrace4.setTraceName("订单生产");
        wxOrderTrace4.setTraceInfo("卖家开始生产产品");
        wxOrderTrace4.setTracePeopleInfo("生产负责人:");
        wxOrderTrace4.setTracePeopleName(creatorName);
        wxOrderTrace4.setTraceTime(created);
        if(created != null){
            QueryOrdersTraceList.add(wxOrderTrace4);

        }
        //5 生产信息
        WxOrderTrace wxOrderTrace5 = new WxOrderTrace();
        wxOrderTrace5.setOrderId(orderId);
        wxOrderTrace5.setTraceName("订单入库");
        wxOrderTrace5.setTraceInfo("订单进入仓库等待物流发货");
        wxOrderTrace5.setTracePeopleInfo("仓库负责人:");
        wxOrderTrace5.setTracePeopleName(stockInputCreatorName);
        wxOrderTrace5.setTraceTime(planFinishTime);
        if(planFinishTime!=null){
            QueryOrdersTraceList.add(wxOrderTrace5);

        }
        //6 发货信息
        List<OrderProductDto> listByOrderId1 = this.orderProductService.findListByOrderId(orderId);
        List<MapContext> list=new ArrayList<>();
        for(OrderProductDto orderProductDto:listByOrderId1){
            if(orderProductDto.getLogisticsCompanyName()!=null&&!orderProductDto.getLogisticsCompanyName().equals("")){
                MapContext mapContext=MapContext.newOne();
                mapContext.put("logisticsName",orderProductDto.getLogisticsCompanyName());
                mapContext.put("logisticsNo",orderProductDto.getLogisticsNo());
                list.add(mapContext);
            }
        }
        WxOrderTrace wxOrderTrace6 = new WxOrderTrace();
        wxOrderTrace6.setOrderId(orderId);
        wxOrderTrace6.setTraceName("订单发货");
        wxOrderTrace6.setTraceInfo("订单已经发送物流,正在运送");
        wxOrderTrace6.setTracePeopleInfo("物流负责人:");
        wxOrderTrace6.setLogisticsInfo(list);
        wxOrderTrace6.setTracePeopleName(deliveryCreatorName);
        wxOrderTrace6.setTraceTime(deliveryTime);
        // 订单状态为已发货
        if (wxCustomOrderInfoDto.getOrderStatus().equals(OrderStatus.SHIPPED.getValue())) {
            if(deliveryTime!=null){
                QueryOrdersTraceList.add(wxOrderTrace6);
            }
        }


        return ResultFactory.generateRequestResult(QueryOrdersTraceList);
    }


    @Override
    public RequestResult findWxOrderList(String dealerId, Integer pageNum, Integer pageSize, MapContext mapContext) {
        //今日统计
        List count = new ArrayList();
//        Object status1 = mapContext.get("status");
//        ArrayList<String> statusList= new ArrayList<>();
//        if(status1 !=null){
//            statusList = (ArrayList<String>)mapContext.get("status");
//        }

        //合计
        MapContext param1 = MapContext.newOne();
        param1.put("dealerId", dealerId);
        Integer total = this.customOrderService.findTodayOrderCount(param1);
        MapContext mapContext1 = MapContext.newOne();
        mapContext1.put("name", "总计");
        mapContext1.put("value", total);
        count.add(mapContext1);
        Integer status = OrderStatus.TO_PAID.getValue();
        //无效（未付款订单）
        MapContext param2 = MapContext.newOne();
        param2.put("dealerId", dealerId);
        param2.put("status", status);
        Integer invalid = this.customOrderService.findTodayInvalidOrder(param2);
        MapContext mapContext2 = MapContext.newOne();
        mapContext2.put("name", "无效");
        mapContext2.put("value", invalid);
        count.add(mapContext2);
        //有效（订单已支付货款）
        Integer effective = this.customOrderService.findTodayEffectiveOrder(param2);
        MapContext mapContext3 = MapContext.newOne();
        mapContext3.put("name", "有效");
        mapContext3.put("value", effective);
        count.add(mapContext3);

        //列表分页查询
        Pagination pagination = new Pagination();//设置分页信息
        pagination.setPageNum(pageNum);
        pagination.setPageSize(pageSize);
        PaginatedList<WxCustomOrderDto> customOrderDtoPaginatedList = new PaginatedList<>();

        PaginatedFilter paginatedFilter = new PaginatedFilter();//查询条件,
        paginatedFilter.setFilters(mapContext);
        paginatedFilter.setPagination(pagination);
        customOrderDtoPaginatedList = this.customOrderService.findWxOrderList(paginatedFilter);

//        if(statusList == null || statusList.size() == 0){
//
//        }else{
//            for (String st:statusList){
//                PaginatedFilter paginatedFilter = new PaginatedFilter();//查询条件,
//                mapContext.put("status",Integer.valueOf(st));
//                paginatedFilter.setFilters(mapContext);
//                paginatedFilter.setPagination(pagination);
//                customOrderDtoPaginatedList = this.customOrderService.findWxOrderList(paginatedFilter);
//
////                if(customOrderDtoPaginatedList !=null){
////                    List<WxCustomOrderDto> rows = customOrderDtoPaginatedListTmp.getRows();
////                    customOrderDtoPaginatedList.setRows(rows);
////                    customOrderDtoPaginatedList.setPagination(customOrderDtoPaginatedListTmp.getPagination());
////                }
//            }
//        }
        List<WxCustomOrderDto> rows = customOrderDtoPaginatedList.getRows();


        for (WxCustomOrderDto wxCustomOrderDto : rows) {
            String orderId = wxCustomOrderDto.getOrderId();
            //获取订单产品类型 一个订单只有一个
            List<Map> products = this.orderProductService.findByOrderId(orderId);
            if(products !=null && products.size() != 0)
            wxCustomOrderDto.setOrderProductType(products.get(0).get("type").toString());
//            if (products.size() > 1) {
//                wxCustomOrderDto.setOrderProductType("多品");
//            } else if (products.size() == 1) {
//
//            }
            //产品系列 列表 系列 0 定制实木 1 特供实木 2 美克 3 康奈 4 慧娜 5 模压
            List<OrderProductDto> orderProductDtos = this.orderProductService.findListByOrderId(orderId);
            List<String> seriesNames = new ArrayList<>();
            for (OrderProductDto orderProductDto : orderProductDtos) {
                //Basecode seriesBasecode = basecodeService.findByTypeAndCode("produceType", orderProductDto.getSeries().toString());
                //seriesNames.add(seriesBasecode.getValue());
                Integer series1 = orderProductDto.getSeries();
                String seriesName ="";
                if(series1 != null)
                switch (series1){
                    case 0:
                    seriesName="定制实木";
                    break;
                    case 1:
                    seriesName="特供实木";
                    break;
                    case 2:
                    seriesName="美克";
                    break;
                    case 3:
                    seriesName="康奈";
                    break;
                    case 4:
                    seriesName="慧娜";
                    break;
                    case 5:
                    seriesName="模压";
                    break;
                }

                 orderProductDto.getSeriesName();
                boolean isHave = false;
                for (String series:seriesNames){

                    if(series !=null && series.equals(seriesName)){
                        isHave = true;
                        break;
                    }
                }
                if (!isHave){
                    if(seriesName !=null ){
                        seriesNames.add(seriesName);
                    }

                }
            }
            wxCustomOrderDto.setSeriesNames(seriesNames);
            wxCustomOrderDto.setProductNum(orderProductDtos.size());//产品数量
            String orderProductType = wxCustomOrderDto.getOrderProductType()+"";
            String orderProductTypeName ="";
            if(orderProductType != null){
                switch (orderProductType){
                    case "0":
                        orderProductTypeName="橱柜";
                        break;
                    case "1":
                        orderProductTypeName="衣柜";
                        break;
                    case "4":
                        orderProductTypeName="五金";
                        break;
                    case "5":
                        orderProductTypeName="样块";
                        break;
                        default:orderProductTypeName = orderProductType;
                        break;
                }
            }
            //产品类型订单产品类型
//            0-橱柜
//            1-衣柜
//            4-五金
//            5-样块
            wxCustomOrderDto.setOrderProductTypeName(orderProductTypeName);
        }

        MapContext result = MapContext.newOne();
        result.put("CustomOrderlist", rows);
        result.put("count", count);
        return ResultFactory.generateRequestResult(result, customOrderDtoPaginatedList.getPagination());
    }
    @Resource(name = "logisticsCompanyService")
    private LogisticsCompanyService logisticsCompanyService;
    /**
     * 查询订单信息
     *
     * @param branchId
     * @param orderId
     * @return
     */
    @Override
    public RequestResult findWxOrderInfo(String branchId, String orderId) {
        WxCustomerOrderInfoDto wxCustomOrderInfoDto = this.customOrderService.findWxOrderByorderId(orderId);
        String cityAreaId = wxCustomOrderInfoDto.getCityAreaId();
        //经销商门店区域地址
        String mergerName = "";
        if (cityAreaId != null && !cityAreaId.equals("")) {
            CityArea cityArea = this.cityAreaService.findById(cityAreaId);
            String mergerNameValue = cityArea.getMergerName();
            int a = mergerNameValue.indexOf(",");
            mergerName = mergerNameValue.substring(a + 1);
            wxCustomOrderInfoDto.setCityAreaName(mergerName);
        }


        List<UploadFiles> UploadFiles = uploadFilesDao.findByResourceId(wxCustomOrderInfoDto.getOrderId());
        wxCustomOrderInfoDto.setUploadFiles(UploadFiles);
        if (wxCustomOrderInfoDto == null) {
            return ResultFactory.generateResNotFoundResult();
        }
        /*订单详情添加字段  : 下单人  订单状态  客户电话(没有的话就不加)  收获地址(应该address这个)
        物流信息那块  发货数量  发货人  物流名称  物流单号*/
        String placeOrder = wxCustomOrderInfoDto.getPlaceOrder();//下单人
        User byUserId = userService.findByUserId(placeOrder);
        if(byUserId !=null){
            wxCustomOrderInfoDto.setPlaceOrderName(byUserId.getName());
        }

        //订单财务信息
        Integer value = PaymentFunds.ORDER_FEE_CHARGE.getValue();
        MapContext params = MapContext.newOne();
        params.put("orderId", orderId);
        params.put("funds", value);
        PaymentDto payment = this.paymentService.findByOrderIdAndFunds(params);
        if (payment != null) {
            if (payment.getStatus() == 1) {
                if (payment.getWay() != null) {
                    if (payment.getWay() == PaymentWay.CASH.getValue()) {
                        wxCustomOrderInfoDto.setAuditorName(payment.getAuditorName());
                        wxCustomOrderInfoDto.setAudited(payment.getAudited());
                    } else {
                        wxCustomOrderInfoDto.setAudited(payment.getCreated());
                        wxCustomOrderInfoDto.setAuditorName(payment.getHolder());
                    }
                }
                wxCustomOrderInfoDto.setFinanceStatus("已到账");

            } else {
                wxCustomOrderInfoDto.setFinanceStatus("未到账");
            }
        }

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
        //产品信息
        List<OrderProductDto> orderProductDtos = this.orderProductService.findListByOrderId(orderId);


        wxCustomOrderInfoDto.setOrderProduct(orderProductDtos);
        //添加物流信息
        for (OrderProductDto orderProductDto:orderProductDtos){
            Integer series1 = orderProductDto.getSeries();
            String seriesName ="";
            if(series1 != null)
                switch (series1){
                    case 0:
                        seriesName="定制实木";
                        break;
                    case 1:
                        seriesName="特供实木";
                        break;
                    case 2:
                        seriesName="美克";
                        break;
                    case 3:
                        seriesName="康奈";
                        break;
                    case 4:
                        seriesName="慧娜";
                        break;
                    case 5:
                        seriesName="模压";
                        break;
                }
            orderProductDto.setSeriesName(seriesName);
            String logisticsCompanyId = orderProductDto.getLogisticsCompanyId();
            //生产拆单类型 0 柜体 1 门板 2 五金
            Integer type = orderProductDto.getType();
            String typeName ="";
            if(series1 != null)
                switch (series1){
                    case 0:
                        typeName="柜体";
                        break;
                    case 1:
                        typeName="门板";
                        break;
                    case 2:
                        typeName="五金";
                        break;
                }
            orderProductDto.setTypeName(typeName);
            if(logisticsCompanyId != null && !"".equals(logisticsCompanyId)){
                LogisticsCompany logisticInfoById = logisticsCompanyService.findById(logisticsCompanyId);
                if(logisticInfoById !=null){
                    String name = logisticInfoById.getName();
                    orderProductDto.setLogisticsCompanyName(name);
                }

                String deliveryCreator = orderProductDto.getDeliveryCreator();
                User deliveryCreatorUser = userService.findByUserId(deliveryCreator);
                if(deliveryCreatorUser !=null){
                    orderProductDto.setDeliveryCreatorName(deliveryCreatorUser.getName());
                }
            }


        }

        //设计信息
        List<CustomOrderDesignDto> customOrderDesignDtos = this.customOrderDesignService.findListByOrderId(orderId);
        wxCustomOrderInfoDto.setOrderDesign(customOrderDesignDtos);
        //生产信息
        List list = new ArrayList();
        Map map = new HashMap();
        map.put("documentaryTime", wxCustomOrderInfoDto.getDocumentaryTime());
        map.put("merchandiserName", wxCustomOrderInfoDto.getMerchandiserName());
        map.put("documentaryNotes", wxCustomOrderInfoDto.getDocumentaryNotes());
        list.add(map);
        wxCustomOrderInfoDto.setOrderProduce(list);
        //入库信息
        List<FinishedStockDto> finishedStockDtos = this.finishedStockService.findWxFinishedList(orderId);
        if (finishedStockDtos != null) {
            wxCustomOrderInfoDto.setOrderFinishStock(finishedStockDtos);
        }
        //发货信息
        if (finishedStockDtos.size() > 0) {
            List itemids = new ArrayList();
            for (FinishedStockDto finishedStockDto : finishedStockDtos) {//获取所有的包裹id
                List<FinishedStockItemDto> list1 = finishedStockDto.getFinishedStockItemDtos();
                for (FinishedStockItemDto finishedStockItemDto : list1) {
                    String itemid = finishedStockItemDto.getId();
                    itemids.add(itemid);
                }
            }
            List<Map> mapList = this.dispatchBillService.findDispatchListByFinishedItemId(itemids);
            for (Map map1 : mapList) {
                String dispatchBillId = map1.get("dispatchBillId").toString();
                List<FinishedStockDto> list1 = this.dispatchBillService.findFinishedItemTypeByDispatchId(dispatchBillId, itemids);
                if (list1 == null || list1.size() == 0) {
                    mapList.remove(map1);
                } else {
                    map1.put("typeNameList", list1);
                }
            }

            wxCustomOrderInfoDto.setOrderDispatchBill(mapList);
        }
        return ResultFactory.generateRequestResult(wxCustomOrderInfoDto);

    }

    @Override
    public RequestResult findMessageOrderInfo(Integer pageNum, Integer pageSize, String companyId) {
        Pagination pagination = new Pagination();//设置分页信息
        pagination.setPageNum(pageNum);
        pagination.setPageSize(pageSize);
        PaginatedFilter paginatedFilter = new PaginatedFilter();//查询条件
        MapContext mapContext = MapContext.newOne();
        mapContext.put("companyId", companyId);
        paginatedFilter.setFilters(mapContext);
        paginatedFilter.setPagination(pagination);
        PaginatedList<Map> mapPaginatedList = this.customOrderLogService.findMessageOrderInfo(paginatedFilter);
        MapContext result = MapContext.newOne();
        result.put("map", mapPaginatedList.getRows());
        return ResultFactory.generateRequestResult(result, mapPaginatedList.getPagination());
    }

	@Override
	public RequestResult findRecentOrder(String dealerId, Integer num) {
		List<WxBCustomOrderDto> list = new ArrayList<>();
		MapContext mapContext = MapContext.newOne();
		mapContext.put("dealerId", dealerId);
		mapContext.put("num", num);
		list = this.customOrderService.selectRecentOrder(mapContext);
		// 去除系列名重复的
        if (list != null && list.size() > 1) {
            for (WxBCustomOrderDto item : list) {
                if (item != null && item.getSeriesName() != null && !item.getSeriesName().equals("")) {
                    List<String> strList = Arrays.asList(item.getSeriesName().split("、"));
                    List<String> temp = new ArrayList<>(strList.size());
                    for (String str : strList) {
                        if (!temp.contains(str)) {
                            temp.add(str);
                        }
                    }
                    item.setSeriesName(StringUtils.join(temp, "、"));
                }
            }
        }
		return ResultFactory.generateRequestResult(list);
	}
}
