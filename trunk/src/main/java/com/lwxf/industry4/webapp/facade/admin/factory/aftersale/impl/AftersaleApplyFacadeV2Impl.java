package com.lwxf.industry4.webapp.facade.admin.factory.aftersale.impl;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.bizservice.aftersale.AftersaleApplyFilesService;
import com.lwxf.industry4.webapp.bizservice.aftersale.AftersaleApplyService;
import com.lwxf.industry4.webapp.bizservice.common.CityAreaService;
import com.lwxf.industry4.webapp.bizservice.company.CompanyEmployeeService;
import com.lwxf.industry4.webapp.bizservice.company.CompanyService;
import com.lwxf.industry4.webapp.bizservice.customer.CompanyCustomerService;
import com.lwxf.industry4.webapp.bizservice.customorder.*;
import com.lwxf.industry4.webapp.bizservice.dispatch.DispatchBillService;
import com.lwxf.industry4.webapp.bizservice.financing.PaymentService;
import com.lwxf.industry4.webapp.bizservice.system.BasecodeService;
import com.lwxf.industry4.webapp.common.aop.syslog.OperationMoudule;
import com.lwxf.industry4.webapp.common.aop.syslog.OperationType;
import com.lwxf.industry4.webapp.common.aop.syslog.SysOperationLog;
import com.lwxf.industry4.webapp.common.component.UploadInfo;
import com.lwxf.industry4.webapp.common.component.UploadType;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.UploadResourceType;
import com.lwxf.industry4.webapp.common.enums.aftersale.AftersaleStatus;
import com.lwxf.industry4.webapp.common.enums.aftersale.AftersaleType;
import com.lwxf.industry4.webapp.common.enums.company.CompanyStatus;
import com.lwxf.industry4.webapp.common.enums.company.CompanyType;
import com.lwxf.industry4.webapp.common.enums.company.ProduceOrderPay;
import com.lwxf.industry4.webapp.common.enums.customorder.CustomOrderCoordination;
import com.lwxf.industry4.webapp.common.enums.customorder.CustomOrderFilesStatus;
import com.lwxf.industry4.webapp.common.enums.customorder.ProduceOrderPermit;
import com.lwxf.industry4.webapp.common.enums.customorder.ProduceOrderState;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentFunds;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentStatus;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentTypeNew;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentWay;
import com.lwxf.industry4.webapp.common.enums.order.OrderStatus;
import com.lwxf.industry4.webapp.common.enums.order.ProduceOrderType;
import com.lwxf.industry4.webapp.common.enums.order.ProduceOrderWay;
import com.lwxf.industry4.webapp.common.enums.payment.PaymentResourceType;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.uniquecode.UniquneCodeGenerator;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.aftersale.AftersaleDto;
import com.lwxf.industry4.webapp.domain.dto.aftersale.AftersaleDtoForAdd;
import com.lwxf.industry4.webapp.domain.dto.customorder.CustomOrderDto;
import com.lwxf.industry4.webapp.domain.dto.customorder.OrderProductDto;
import com.lwxf.industry4.webapp.domain.dto.customorder.ProduceOrderDto;
import com.lwxf.industry4.webapp.domain.dto.financing.PaymentDto;
import com.lwxf.industry4.webapp.domain.entity.aftersale.AftersaleApply;
import com.lwxf.industry4.webapp.domain.entity.aftersale.AftersaleApplyFiles;
import com.lwxf.industry4.webapp.domain.entity.common.CityArea;
import com.lwxf.industry4.webapp.domain.entity.company.Company;
import com.lwxf.industry4.webapp.domain.entity.customer.CompanyCustomer;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrder;
import com.lwxf.industry4.webapp.domain.entity.customorder.OrderProduct;
import com.lwxf.industry4.webapp.domain.entity.customorder.OrderProductParts;
import com.lwxf.industry4.webapp.domain.entity.financing.Payment;
import com.lwxf.industry4.webapp.domain.entity.system.Basecode;
import com.lwxf.industry4.webapp.domain.entity.user.User;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.aftersale.AfterSaleApplyV2Facade;
import com.lwxf.industry4.webapp.facade.admin.factory.dealer.OrderFacade;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
@Component("fAftersaleApplyV2Facade")
public class AftersaleApplyFacadeV2Impl extends BaseFacadeImpl implements AfterSaleApplyV2Facade {

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
    @Resource(name = "companyEmployeeService")
    private CompanyEmployeeService companyEmployeeService;
    @Resource(name = "orderFacade")
    private OrderFacade orderFacade;
    @Resource(name = "customOrderFilesService")
    private CustomOrderFilesService customOrderFilesService;
    @Resource(name = "produceOrderService")
    private ProduceOrderService produceOrderService;
    @Resource(name = "basecodeService")
    private BasecodeService basecodeService;
    @Resource(name = "orderProductPartsService")
    private OrderProductPartsService orderProductPartsService;

    /**
     * 查询今日 本周 本月的售后单数量
     * @param companyId
     * @return
     */
    @Override
    public RequestResult findFactoryAftersaleApply(String companyId) {
        MapContext result = MapContext.newOne();
        Integer today = this.aftersaleApplyService.findTodayCount();
        Integer thisWeek = this.aftersaleApplyService.findThisWeekCount();
        Integer thisMonth = this.aftersaleApplyService.findThisMonthCount();
        result.put("today", today);
        result.put("thisWeek", thisWeek);
        result.put("thisMonth", thisMonth);
        return ResultFactory.generateRequestResult(result);
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
        String orderId = aftersaleApply.getCustomOrderId();
        Integer type=2;
        MapContext params=MapContext.newOne();
        params.put("type",type);
        params.put("orderId",orderId);
        Map customOrder = this.customOrderService.findFAppBaseInfoByOrderId(params);
        result.put("customOrder", customOrder);
        result.put("aftersaleApply", aftersaleApply);
        //售后订单
            //产品信息
            List<OrderProductDto> aftersaleProduct = this.orderProductService.findListByAftersaleId(aftersaleApplyId);
            //发货物流信息
           //List<Map> dispatchBillList = this.dispatchBillService.findDispatchList(resultOrderId);
        if(aftersaleApply.getPaymentId()!=null){
            result.put("payment", paymentService.findByPaymentId(aftersaleApply.getPaymentId()));
        }else{
            result.put("payment", new PaymentDto());
        }
        result.put("aftersaleProduct", aftersaleProduct);
        //result.put("dispatchBillList", dispatchBillList);
        return ResultFactory.generateRequestResult(result);
    }

    /**
     * 经销商列表
     * @return
     */
    @Override
    public RequestResult findDealersList(MapContext params) {
        List<Company> AllDealers = this.findAllDealer(params);
        if (AllDealers.size() < 0 || AllDealers == null) {
            return ResultFactory.generateSuccessResult();
        }
        List list = new ArrayList();
        for (Company company : AllDealers) {
            MapContext mapContext = MapContext.newOne();
            String name = company.getName();//公司名称
            String companyId = company.getId();
            mapContext.put("name", name);//公司名称
            mapContext.put("dealerId", companyId);//经销商公司Id
            //经销商门店区域地址
            String cityAreaId = company.getCityAreaId();
            String mergerName = "";
            if (cityAreaId != null && !cityAreaId.equals("")) {
                CityArea cityArea = this.cityAreaService.findById(cityAreaId);
                String mergerNameValue = cityArea.getMergerName();
                int a = mergerNameValue.indexOf(",");
                mergerName = mergerNameValue.substring(a + 1);
            }
            mapContext.put("mergerName", mergerName);
            //经销商详细地址
            String address = company.getAddress();
            mapContext.put("address", address);
            //经销商负责人姓名
            String leader = company.getLeader();
            User user = AppBeanInjector.userService.findByUserId(leader);
            String leaderName = "";
            if (user != null) {
                leaderName = user.getName();
            }
            mapContext.put("leaderName", leaderName);
            //负责人电话
            String leaderTel = company.getLeaderTel();
            mapContext.put("leaderTel", leaderTel);
            list.add(mapContext);
        }

        return ResultFactory.generateRequestResult(list);
    }


    /**
     * 客户列表
     *
     * @param dealerId
     * @return
     */
    @Override
    public RequestResult findCustomerList(String dealerId) {
        List<CompanyCustomer> companyCustomers = this.companyCustomerService.findCustomerListByCid(dealerId);
        if (companyCustomers.size() < 0 || companyCustomers == null) {
            return ResultFactory.generateSuccessResult();
        }
        List list = new ArrayList();
        for (CompanyCustomer companyCustomer : companyCustomers) {
            MapContext mapContext = MapContext.newOne();
            String userId = companyCustomer.getUserId();
            User user = AppBeanInjector.userService.findByUserId(userId);
            String customerName = user.getName();
            String cityAreaId = companyCustomer.getCityAreaId();
            String mergerName = "";
            if (cityAreaId != null && !cityAreaId.equals("")) {
                CityArea cityArea = this.cityAreaService.findById(cityAreaId);
                String mergerNameValue = cityArea.getMergerName();
                int a = mergerNameValue.indexOf(",");
                mergerName = mergerNameValue.substring(a + 1);
            }
            String address = companyCustomer.getAddress();
            String mobile = user.getMobile();
            List<MapContext> customOrderList = this.customOrderService.findOrderListByCidAndUid(dealerId, userId);
            mapContext.put("userId", userId);
            mapContext.put("customerName", customerName);
            mapContext.put("customOrderList", customOrderList);
            mapContext.put("mergerName", mergerName);
            mapContext.put("address", address);
            mapContext.put("mobile", mobile);
            list.add(mapContext);
        }
        return ResultFactory.generateRequestResult(list);
    }


    /**
     * 售后列表查询（按条件查询）
     *
     * @param pageNum
     * @param pageSize
     * @param mapContext
     * @return
     */
    @Override
    public RequestResult findAftersaleApplyList(Integer pageNum, Integer pageSize, MapContext mapContext) {
        PaginatedFilter paginatedFilter = new PaginatedFilter();
        Pagination pagination = new Pagination();
        pagination.setPageSize(pageSize);
        pagination.setPageNum(pageNum);
        paginatedFilter.setPagination(pagination);
        mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID,WebUtils.getCurrBranchId());
        paginatedFilter.setFilters(mapContext);
        Map dataSort = new HashMap();
        List sorts = new ArrayList();
        if (mapContext.containsKey("order")) {
            if(!mapContext.getTypedValue("order",String.class).equals("")) {
                if (mapContext.containsKey("sort")) {
                    String sort = mapContext.getTypedValue("sort", String.class);
                    if (mapContext.getTypedValue("order", String.class).equals("byCreated")) {//按创建时间排序
                        dataSort.put("created", sort);
                        paginatedFilter.setSorts(sorts);
                    } else if (mapContext.getTypedValue("order", String.class).equals("byActualDate")) {//按发货时间排序
                        dataSort.put("actualDate", sort);
                    } else if (mapContext.getTypedValue("order", String.class).equals("byProduceTime")) {//按生产时间排序
                        dataSort.put("documentaryTime", sort);
                    }
                    sorts.add(dataSort);
                }
            }
        }else{
            Map<String,String> created = new HashMap<String, String>();
            created.put(WebConstant.KEY_ENTITY_CREATED,"desc");
            sorts.add(created);
        }
        paginatedFilter.setSorts(sorts);
        PaginatedList<AftersaleDto> aftersalesList = this.aftersaleApplyService.selectDtoByFilter(paginatedFilter);
        for(AftersaleDto aftersaleDto:aftersalesList.getRows()){
            String afterId=aftersaleDto.getId();
            List<OrderProductDto> orderProductDtos=this.orderProductService.findListByAftersaleId(afterId);
            aftersaleDto.setOrderProductDtoList(orderProductDtos);
        }
        return ResultFactory.generateRequestResult(aftersalesList);
    }

    @Override
    @Transactional(value = "transactionManager")
    @SysOperationLog(detail = "创建售后单",operationType = OperationType.INSERT,operationMoudule = OperationMoudule.AFTERSALE)
    public RequestResult addFactoryAftersaleRequest(AftersaleApply aftersaleApply) {
        aftersaleApply.setCreated(DateUtil.getSystemDate());
        aftersaleApply.setCreator(WebUtils.getCurrUserId());
        aftersaleApply.setNo(aftersaleApplyService.getAftersaleOrderNo(aftersaleApply.getCustomOrderId(),aftersaleApply.getNo()));
        aftersaleApply.setStatus(0);
        aftersaleApply.setCharge(false);
        aftersaleApply.setCharge(false);
        aftersaleApply.setChargeAmount(new BigDecimal(0));
        this.aftersaleApplyService.add(aftersaleApply);
        //更新图片信息为正常
        if(aftersaleApply.getFileIds()!=null && !aftersaleApply.getFileIds().equals("")){
            String[] ids = aftersaleApply.getFileIds().split(",");
            for(String file_id: ids){
                MapContext map = MapContext.newOne();
                map.put("id",file_id);
                map.put("aftersale_apply_id",aftersaleApply.getId());
                map.put("status",1);
                this.aftersaleApplyFilesService.updateByMapContext(map);
            }
        }
        MapContext mapParam = MapContext.newOne();
        mapParam.put("id",aftersaleApply.getId());
        return ResultFactory.generateRequestResult(findAftersaleApplyList(1,1,mapParam).getData());
    }

    @Override
    @Transactional(value = "transactionManager")
    @SysOperationLog(detail = "创建售后单",operationType = OperationType.INSERT,operationMoudule = OperationMoudule.AFTERSALE)
    public RequestResult addFactoryAftersale(AftersaleDtoForAdd aftersaleApply) {
        String resultOrderId = null;
        //售后单类型
        Integer aftersaleType = aftersaleApply.getType();
        //售后订单编号
        String aftersaleOrderNo="";
//        if(aftersaleApply.getCustomOrderId()!=null){
//            aftersaleOrderNo = aftersaleApplyService.getAftersaleOrderNo(aftersaleApply.getCustomOrderId(),aftersaleApply.getNo());
//        }else{
//            aftersaleOrderNo ="H"+aftersaleApply.getNo();
//        }
        if(aftersaleApply.getCustomOrderId()!=null) {
            CustomOrder order = customOrderService.findById(aftersaleApply.getCustomOrderId());
            if (aftersaleApply.getType() == AftersaleType.BULIAO.getValue()) {
                aftersaleOrderNo = "补" + order.getNo();
            } else {
                aftersaleOrderNo = "反" + order.getNo();
            }
        }else{
            aftersaleOrderNo = aftersaleApply.getNo();
        }
        aftersaleApply.setCreated(DateUtil.getSystemDate());
        if(!aftersaleApply.getOperator().equals("")||aftersaleApply.getOperator()!=null){
            aftersaleApply.setCreator(aftersaleApply.getOperator());
        }else {
            aftersaleApply.setCreator(WebUtils.getCurrUserId());
        }
        aftersaleApply.setNo(aftersaleOrderNo);
        aftersaleApply.setStatus(AftersaleStatus.WAIT.getValue());
        aftersaleApply.setResultOrderId(resultOrderId);
        aftersaleApply.setBranchId(WebUtils.getCurrBranchId());
        this.aftersaleApplyService.add(aftersaleApply);
        if (aftersaleType == AftersaleType.BULIAO.getValue()) {
            //创建售后订单产品信息
            int prodCount = 1;
            for(OrderProductDto od : aftersaleApply.getOrderProducts()){
                OrderProduct orderProduct = new OrderProduct();
                String prodNo =String.format("%02d", prodCount);
                orderProduct.setNo(aftersaleApply.getNo()+"-"+prodNo);
                prodCount++;
                orderProduct.setAfterApplyId(aftersaleApply.getId());
                orderProduct.setDoorColor(od.getDoorColor());
                orderProduct.setBodyColor(od.getBodyColor());
                orderProduct.setType(Integer.valueOf(od.getType()));
                orderProduct.setDoor(od.getDoor());
                orderProduct.setPrice(od.getPrice());
                orderProduct.setNotes(od.getNotes());
                orderProduct.setAftersaleNum(0);
                orderProduct.setFlag(0);
                if(od.getSeries()!=null)
                    orderProduct.setSeries(Integer.valueOf(od.getSeries()));
                orderProduct.setCreated(DateUtil.getSystemDate());
                if(!aftersaleApply.getOperator().equals("")||aftersaleApply.getOperator()!=null){
                    orderProduct.setCreator(aftersaleApply.getOperator());
                }else {
                    orderProduct.setCreator(WebUtils.getCurrUserId());
                }
                this.orderProductService.add(orderProduct);
                if(od.getFileIds().size()!=0){
                    MapContext updateFile = new MapContext();
                    updateFile.put(WebConstant.KEY_ENTITY_ID,od.getFileIds());
                    updateFile.put("belongId",orderProduct.getId());
                    updateFile.put(WebConstant.KEY_ENTITY_STATUS,CustomOrderFilesStatus.FORMAL.getValue());
                    updateFile.put("customOrderId",aftersaleApply.getId());
                    this.customOrderFilesService.updateByIds(updateFile);
                }
                String productParts=od.getProductParts();
                if(productParts!=null) {
                    String[] partsIds = productParts.split(",");
                    for (String id : partsIds) {
                        OrderProductParts parts = new OrderProductParts();
                        parts.setOrderProductId(orderProduct.getId());
                        parts.setProductPartsId(id);
                        this.orderProductPartsService.add(parts);
                    }
                }
            }
            //产生支付信息
                //添加支付记录
                Payment payment = new Payment();
                String userId=WebUtils.getCurrUserId();
                String userName=AppBeanInjector.userService.findByUserId(userId).getName();
                payment.setHolder(userName);
                payment.setNo(AppBeanInjector.uniquneCodeGenerator.getNextNo(UniquneCodeGenerator.UniqueResource.PAYMENT_NO));
                payment.setAmount(aftersaleApply.getChargeAmount());
                payment.setPayAmount(aftersaleApply.getPayAmount());
                payment.setCompanyId(aftersaleApply.getCompanyId());
                payment.setStatus(PaymentStatus.PENDING_PAYMENT.getValue());
                payment.setCreated(DateUtil.getSystemDate());
                if(!aftersaleApply.getOperator().equals("")||aftersaleApply.getOperator()!=null){
                    payment.setCreator(aftersaleApply.getOperator());
                }else {
                    payment.setCreator(WebUtils.getCurrUserId());
                }
                payment.setFunds(PaymentFunds.ORDER_FEE_CHARGE.getValue());
                payment.setWay(PaymentWay.DEALER_ACCOUNT.getValue());
                payment.setType(PaymentTypeNew.INCOME.getValue());
               // payment.setPayee("4j1u3r1efshq");
                payment.setCustomOrderId(aftersaleApply.getId());
                payment.setBranchId(WebUtils.getCurrBranchId());
                payment.setResourceType(PaymentResourceType.AFTERSALEAPPLY.getValue());
                this.paymentService.add(payment);
                aftersaleApply.setPaymentId(payment.getId());
                //补充支付单id到售后单
                MapContext map=MapContext.newOne();
                map.put("id",aftersaleApply.getId());
                map.put("paymentId",payment.getId());
                this.aftersaleApplyService.updateByMapContext(map);
        }

        //更新图片信息为正常
        if(aftersaleApply.getFileIds()!=null && !aftersaleApply.getFileIds().equals("")){
            String[] ids = aftersaleApply.getFileIds().split(",");
            for(String file_id: ids){
                MapContext map = MapContext.newOne();
                map.put("id",file_id);
                map.put("aftersaleApplyId",aftersaleApply.getId());
                map.put("status",4);
                this.aftersaleApplyFilesService.updateByMapContext(map);
            }
        }
        MapContext mapParam = MapContext.newOne();
        mapParam.put("id",aftersaleApply.getId());
        return ResultFactory.generateRequestResult(findAftersaleApplyList(1,1,mapParam).getData());
    }

    @Override
    @Transactional(value = "transactionManager")
    @SysOperationLog(detail = "售后单处理",operationType = OperationType.UPDATE,operationMoudule = OperationMoudule.AFTERSALE)
    public RequestResult handleFactoryAftersale(MapContext mapContext) {
        String resultOrderId = null;
        //售后单类型
        Integer aftersaleType = mapContext.getTypedValue("aftersaleType", Integer.class);
        //售后订单编号
        String aftersaleOrderNo = mapContext.getTypedValue("aftersaleOrderNo", String.class);
        String creator =WebUtils.getCurrUserId();
       //  creator ="4gv4m5yrj18g";
        String companyId = mapContext.getTypedValue("dealerId", String.class);
        if (aftersaleType == AftersaleType.BULIAO.getValue()) {
            //原订单ID
            String address="";
            String cityAreaId="";
            if(mapContext.get("orderId")!=null){
                String orderId = mapContext.getTypedValue("orderId", String.class);
                CustomOrder oldCustomOrder = this.customOrderService.findByOrderId(orderId);
                if(oldCustomOrder!=null){
                    address = oldCustomOrder.getAddress();
                    cityAreaId = oldCustomOrder.getCityAreaId();
                }
            }
            //创建售后订单
            String customerId = mapContext.getTypedValue("customerId", String.class);
            User user = AppBeanInjector.userService.findByUserId(customerId);
            BigDecimal orderMount = mapContext.getTypedValue("chargeAmount", BigDecimal.class);
            CustomOrder customOrder = new CustomOrder();
            customOrder.setNo(aftersaleOrderNo);
            customOrder.setCompanyId(companyId);
            customOrder.setCustomerId(customerId);
            if(mapContext.containsKey("created")) {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date createdValue = dateFormat.parse(mapContext.getTypedValue("created", String.class));
                    customOrder.setCreated(createdValue);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }else {
                customOrder.setCreated(DateUtil.getSystemDate());
            }
            customOrder.setCityAreaId(cityAreaId);
            customOrder.setAddress(address);
            customOrder.setCreator(creator);
            customOrder.setStatus(OrderStatus.TO_PAID.getValue());
            customOrder.setType(1);
            if(user!=null)
                customOrder.setCustomerTel(user.getMobile());
            customOrder.setAmount(new BigDecimal(0));
            customOrder.setImprest(new BigDecimal(0));
            customOrder.setRetainage(new BigDecimal(0));
            customOrder.setEarnest(0);
            customOrder.setFactoryPrice(new BigDecimal(0));
            customOrder.setMarketPrice(new BigDecimal(0));
            customOrder.setDiscounts(new BigDecimal(0));
            customOrder.setFactoryDiscounts(new BigDecimal(0));
            customOrder.setFactoryFinalPrice(orderMount);
            customOrder.setConfirmFprice(false);
            customOrder.setConfirmCprice(false);
            customOrder.setCreator(creator);
            customOrder.setCreated(DateUtil.getSystemDate());
            if (mapContext.containsKey("consigneeName")) {
                customOrder.setConsigneeName(mapContext.getTypedValue("consigneeName", String.class));
            }
            if (mapContext.containsKey("consigneeTel")) {
                customOrder.setConsigneeTel(mapContext.getTypedValue("consigneeTel", String.class));
            }
            RequestResult result = customOrder.validateFields();
            if (null != result) {
                return result;
            }
            this.customOrderService.add(customOrder);
            resultOrderId = customOrder.getId();

            //创建售后订单产品信息
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setCustomOrderId(customOrder.getId());
            orderProduct.setDoorColor(mapContext.get("doorColor").toString());
            orderProduct.setBodyColor(mapContext.get("bodyColor").toString());
            orderProduct.setType(Integer.valueOf(mapContext.get("type").toString()));
            orderProduct.setDoor(mapContext.get("door").toString());
            orderProduct.setSeries(Integer.valueOf(mapContext.get("series").toString()));
            orderProduct.setCreated(DateUtil.getSystemDate());
            orderProduct.setCreator(creator);
            orderProduct.setAftersaleNum(0);
            orderProduct.setFlag(0);
            this.orderProductService.add(orderProduct);
        }
        //更新售后单
        mapContext.put("resultOrderId",resultOrderId);
        mapContext.put("status","3");
        return ResultFactory.generateRequestResult(this.aftersaleApplyService.updateByMapContext(mapContext));
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult addFiles(List<MultipartFile> multipartFiles) {
        String uid = WebUtils.getCurrUserId();
        List<Map<String, Object>> list = new ArrayList<>();
        AftersaleApplyFiles aftersaleApplyFiles = new AftersaleApplyFiles();
        UploadInfo uploadInfo;
        for (MultipartFile mul : multipartFiles) {
            uploadInfo = AppBeanInjector.baseFileUploadComponent.doUploadByModule(UploadType.FORMAL, mul, UploadResourceType.AFTERSALE_APPLY,"factory");
            aftersaleApplyFiles.setName(uploadInfo.getFileName());
            aftersaleApplyFiles.setCreator(uid);
            aftersaleApplyFiles.setCreated(DateUtil.getSystemDate());
            aftersaleApplyFiles.setPath(uploadInfo.getRelativePath());
            aftersaleApplyFiles.setFullPath(WebUtils.getDomainUrl() + uploadInfo.getRelativePath());
            aftersaleApplyFiles.setMime(uploadInfo.getFileMimeType().getRealType());
            aftersaleApplyFiles.setOriginalMime(uploadInfo.getFileMimeType().getOriginalType());
            aftersaleApplyFiles.setStatus(UploadType.FORMAL.getValue());
            this.aftersaleApplyFilesService.add(aftersaleApplyFiles);
            MapContext mapContext = MapContext.newOne();
            mapContext.put("imagePath", aftersaleApplyFiles.getFullPath());
            mapContext.put("fileId", aftersaleApplyFiles.getId());
            list.add(mapContext);
        }
        return ResultFactory.generateRequestResult(list);
    }

    public List<Company> findAllDealer(MapContext params) {
        List companyTypeList = new ArrayList();
        companyTypeList.add(CompanyType.MANUFACTURERS.getValue());
        companyTypeList.add(CompanyType.DIRECT_SHOP.getValue());
        companyTypeList.add(CompanyType.SHOP_IN_STORE.getValue());
        companyTypeList.add(CompanyType.EXCLUSIVE_SHOP.getValue());
        companyTypeList.add(CompanyType.FRANCHISED_STORE.getValue());
        companyTypeList.add(CompanyType.LOOSE_ORDER.getValue());
        Integer status= CompanyStatus.NORMAL.getValue();
        params.put("companyTypeList",companyTypeList);
        params.put("status",status);
        List<Company> companyList = this.companyService.findAllCompany(params);
        return companyList;
    }

    @Override
    @Transactional(value = "transactionManager")
    @SysOperationLog(detail = "修改售后单",operationType = OperationType.UPDATE,operationMoudule = OperationMoudule.AFTERSALE)
    public RequestResult updateAftersaleStatus(String aftersaleId, String status) {
        MapContext map = MapContext.newOne();
        map.put("id",aftersaleId);
        map.put("status",status);
        aftersaleApplyService.updateByMapContext(map);
        return  ResultFactory.generateRequestResult(map);
    }

    @Override
    @Transactional(value = "transactionManager")
    @SysOperationLog(detail = "删除售后单",operationType = OperationType.DELETE,operationMoudule = OperationMoudule.AFTERSALE)
    public RequestResult deleteAftersale(String aftersaleId) {
        AftersaleApply obj = aftersaleApplyService.findById(aftersaleId);
        //如果补料单则删除订单
        if(obj.getType()==AftersaleType.BULIAO.getValue()){
            orderFacade.deleteOrderById(obj.getResultOrderId());
        }
        return ResultFactory.generateRequestResult(aftersaleApplyService.deleteById(aftersaleId));
    }

    @Override
    @Transactional(value = "transactionManager")
    @SysOperationLog(detail = "修改售后单",operationType = OperationType.UPDATE,operationMoudule = OperationMoudule.AFTERSALE)
    public RequestResult editAftersaleApply(MapContext mapContext) {
        return ResultFactory.generateRequestResult(aftersaleApplyService.updateByMapContext(mapContext));
    }

    /**
     * 创建售后生产单
     * @param id
     * @param produceOrder
     * @param fileIds
     * @return
     */
	@Override
    @Transactional(value = "transactionManager")
    @SysOperationLog(detail = "创建生产单",operationType = OperationType.INSERT,operationMoudule = OperationMoudule.PRODUCE)
	public RequestResult addProduceAftersaleOrder(String id, ProduceOrderDto produceOrder, List<String> fileIds) {
    	AftersaleApply aftersaleApply=this.aftersaleApplyService.findOneById(id);
    	if(aftersaleApply==null){
			return ResultFactory.generateResNotFoundResult();
		}
		produceOrder.setCustomOrderNo(aftersaleApply.getNo());
		produceOrder.setCustomOrderId(aftersaleApply.getId());
        String orderNo = "";
        Basecode b = basecodeService.findByTypeAndCode("produceType",produceOrder.getType().toString());
        OrderProductDto prod = orderProductService.findOneById(produceOrder.getOrderProductId());
        orderNo = prod.getNo()+"-"+b.getValue();
		produceOrder.setNo(orderNo);
		produceOrder.setCreated(DateUtil.getSystemDate());
		produceOrder.setCreator(WebUtils.getCurrUserId());
		produceOrder.setBranchId(WebUtils.getCurrBranchId());
		produceOrder.setResourceType(PaymentResourceType.AFTERSALEAPPLY.getValue());
		MapContext orderMapContext = new MapContext();
		//如果是五金
		if(produceOrder.getType().equals(ProduceOrderType.HARDWARE.getValue())){
			produceOrder.setState(ProduceOrderState.COMPLETE.getValue());
			produceOrder.setWay(ProduceOrderWay.SELF_PRODUCED.getValue());
			//判断订单状态是否已付款
			if(aftersaleApply.getStatus()>OrderStatus.TO_PAID.getValue()){
				produceOrder.setPay(ProduceOrderPay.PAY.getValue());
			}else{
				produceOrder.setPay(ProduceOrderPay.NOT_PAY.getValue());
			}
			if(aftersaleApply.getStatus()==OrderStatus.PRODUCTION.getValue()||aftersaleApply.getStatus().equals(OrderStatus.TO_PRODUCED.getValue())){
				orderMapContext.put(WebConstant.KEY_ENTITY_STATUS,OrderStatus.TO_PACKAGED.getValue());
			}
		}else if(produceOrder.getType().equals(ProduceOrderType.CABINET_BODY.getValue())){//如果是柜体
			produceOrder.setState(ProduceOrderState.NOT_YET_BEGUN.getValue());
			produceOrder.setWay(ProduceOrderWay.SELF_PRODUCED.getValue());
			//判断订单状态是否已付款
			if(aftersaleApply.getStatus()>OrderStatus.TO_PAID.getValue()){
				produceOrder.setPay(ProduceOrderPay.PAY.getValue());
			}else{
				produceOrder.setPay(ProduceOrderPay.NOT_PAY.getValue());
			}
		}else{//门板
			if(produceOrder.getWay().equals(ProduceOrderWay.SELF_PRODUCED.getValue())){//如果是自产
				produceOrder.setState(ProduceOrderState.NOT_YET_BEGUN.getValue());
				//判断订单状态是否已付款
				if(aftersaleApply.getStatus()>OrderStatus.TO_PAID.getValue()){
					produceOrder.setPay(ProduceOrderPay.PAY.getValue());
				}else{
					produceOrder.setPay(ProduceOrderPay.NOT_PAY.getValue());
				}
				produceOrder.setPermit(ProduceOrderPermit.NOT_ALLOW.getValue());
			}else if(produceOrder.getWay().equals(ProduceOrderWay.SPECIAL.getValue())){//如果是特供实木
				produceOrder.setState(ProduceOrderState.COMPLETE.getValue());
				//判断订单状态是否已付款
				if(aftersaleApply.getStatus()>OrderStatus.TO_PAID.getValue()){
					produceOrder.setPay(ProduceOrderPay.PAY.getValue());
				}else{
					produceOrder.setPay(ProduceOrderPay.NOT_PAY.getValue());
				}
				if(aftersaleApply.getStatus()==OrderStatus.PRODUCTION.getValue()||aftersaleApply.getStatus().equals(OrderStatus.TO_PRODUCED.getValue())){
					orderMapContext.put(WebConstant.KEY_ENTITY_STATUS,OrderStatus.TO_PACKAGED.getValue());
				}
			}else{//如果是外协
				produceOrder.setState(ProduceOrderState.NOT_YET_BEGUN.getValue());
				produceOrder.setPay(ProduceOrderPay.NOT_PAY.getValue());
			}
		}
		this.produceOrderService.add(produceOrder);
		//判断生产拆单是否是 外协
		if(produceOrder.getWay().equals(ProduceOrderWay.COORDINATION.getValue())){
			//判断订单是否是已经是外协
			if(aftersaleApply.getCoordination()== CustomOrderCoordination.UNWANTED_COORDINATION.getValue()){
				orderMapContext.put("coordination",CustomOrderCoordination.NEED_COORDINATION.getValue());
			}
		}
		if(orderMapContext.size()>0){
			orderMapContext.put(WebConstant.KEY_ENTITY_ID,id);
			this.aftersaleApplyService.updateByMapContext(orderMapContext);
		}
		//修改图片资源
		if(fileIds.size()!=0){
			MapContext mapContext = new MapContext();
			mapContext.put(WebConstant.KEY_ENTITY_ID,fileIds);
			mapContext.put(WebConstant.KEY_ENTITY_STATUS,CustomOrderFilesStatus.FORMAL.getValue());
			this.aftersaleApplyFilesService.updateByIds(mapContext);
		}
		return ResultFactory.generateRequestResult(this.produceOrderService.findOneById(produceOrder.getId()));
	}

    @Override
    public RequestResult findAftersaleProduct(String id) {
	    AftersaleApply aftersaleApply=this.aftersaleApplyService.findOneById(id);
	    if(aftersaleApply==null){
	        return ResultFactory.generateResNotFoundResult();
        }
	    List<OrderProductDto> orderProducts=this.orderProductService.findListByAftersaleId(id);
        return ResultFactory.generateRequestResult(orderProducts);
    }

    @Override
    public RequestResult findAftersaleOrderList(MapContext mapContext, Integer pageNum, Integer pageSize) {
        PaginatedFilter paginatedFilter = new PaginatedFilter();
        Pagination pagination = new Pagination();
        pagination.setPageNum(pageNum);
        pagination.setPageSize(pageSize);
        paginatedFilter.setPagination(pagination);
        mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID, WebUtils.getCurrBranchId());
        mapContext.put("funds", PaymentFunds.ORDER_FEE_CHARGE.getValue());
        paginatedFilter.setFilters(mapContext);
        PaginatedList<CustomOrderDto> listByPaginateFilter = this.aftersaleApplyService.findListByPaginateFilter(paginatedFilter);
        if (listByPaginateFilter.getRows().size() > 0) {
            for (CustomOrderDto customOrderDto : listByPaginateFilter.getRows()) {
                String orderId = customOrderDto.getId();
                List<OrderProductDto> productDtos = this.orderProductService.findListByOrderId(orderId);
                customOrderDto.setOrderProductDtoList(productDtos);
                //物流单号
                List<String> findLogisticsNos=this.produceOrderService.findLogisticsNobyOrderId(orderId);
                customOrderDto.setLogisticsNos(findLogisticsNos);
                //外协信息
                List<String> outProduceInfo=this.produceOrderService.findOutProduceInfoByOrderId(orderId);
                customOrderDto.setOutProduceInfo(outProduceInfo);
            }
        }
        return ResultFactory.generateRequestResult(listByPaginateFilter);
    }

    @Override
    public RequestResult countAftersaleForPageIndex() {
        return ResultFactory.generateRequestResult(aftersaleApplyService.countAftersaleForPageIndex(WebUtils.getCurrBranchId()));
    }
}
