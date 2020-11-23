package com.lwxf.industry4.webapp.facade.admin.factory.engineeringOrder.impl;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.bizservice.common.CityAreaService;
import com.lwxf.industry4.webapp.bizservice.customer.CompanyCustomerService;
import com.lwxf.industry4.webapp.bizservice.customorder.CustomOrderService;
import com.lwxf.industry4.webapp.bizservice.customorder.OrderProductAttributeService;
import com.lwxf.industry4.webapp.bizservice.customorder.OrderProductPartsService;
import com.lwxf.industry4.webapp.bizservice.customorder.OrderProductService;
import com.lwxf.industry4.webapp.bizservice.engineering.EngineeringOrderService;
import com.lwxf.industry4.webapp.bizservice.engineering.EngineeringService;
import com.lwxf.industry4.webapp.bizservice.product.ProductPartsService;
import com.lwxf.industry4.webapp.bizservice.productattribute.ProductAttributeValueService;
import com.lwxf.industry4.webapp.bizservice.system.BasecodeService;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.company.CompanyCustomerStatus;
import com.lwxf.industry4.webapp.common.enums.customorder.CustomOrderCoordination;
import com.lwxf.industry4.webapp.common.enums.order.OrderProductStatus;
import com.lwxf.industry4.webapp.common.enums.order.OrderStatus;
import com.lwxf.industry4.webapp.common.enums.order.OrderType;
import com.lwxf.industry4.webapp.common.enums.order.ProduceOrderWay;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.customorder.OrderProductDto;
import com.lwxf.industry4.webapp.domain.dto.customorder.ProduceOrderDto;
import com.lwxf.industry4.webapp.domain.dto.engineeringOrder.EngineeringDto;
import com.lwxf.industry4.webapp.domain.dto.product.ProductPartsDto;
import com.lwxf.industry4.webapp.domain.entity.branch.Branch;
import com.lwxf.industry4.webapp.domain.entity.common.CityArea;
import com.lwxf.industry4.webapp.domain.entity.customer.CompanyCustomer;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrder;
import com.lwxf.industry4.webapp.domain.entity.customorder.OrderProductAttribute;
import com.lwxf.industry4.webapp.domain.entity.customorder.OrderProductParts;
import com.lwxf.industry4.webapp.domain.entity.engineering.Engineering;
import com.lwxf.industry4.webapp.domain.entity.engineering.EngineeringOrder;
import com.lwxf.industry4.webapp.domain.entity.product.ProductParts;
import com.lwxf.industry4.webapp.domain.entity.productattribute.ProductAttributeValue;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.engineeringOrder.EngineeringOrderFacade;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2020-05-28 9:10
 * @version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("engineeringOrderFacade")
public class EngineeringOrderFacadeImpl extends BaseFacadeImpl implements EngineeringOrderFacade {
    @Resource(name = "engineeringOrderService")
    private EngineeringOrderService engineeringOrderService;
    @Resource(name = "engineeringService")
    private EngineeringService engineeringService;
    @Resource(name = "cityAreaService")
    private CityAreaService cityAreaService;
    @Resource(name = "customOrderService")
    private CustomOrderService customOrderService;
    @Resource(name = "companyCustomerService")
    private CompanyCustomerService companyCustomerService;
    @Resource(name = "orderProductService")
    private OrderProductService orderProductService;
    @Resource(name = "basecodeService")
    private BasecodeService basecodeService;
    @Resource(name = "orderProductPartsService")
    private OrderProductPartsService orderProductPartsService;
    @Resource(name = "productPartsService")
    private ProductPartsService productPartsService;
    @Resource(name = "orderProductAttributeService")
    private OrderProductAttributeService orderProductAttributeService;
    @Resource(name = "productAttributeValueService")
    private ProductAttributeValueService productAttributeValueService;

    @Override
    public RequestResult findEngineeringOrderList(Integer pageSize, Integer pageNum, MapContext mapContext) {
        PaginatedFilter paginatedFilter = new PaginatedFilter();
        mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID, WebUtils.getCurrBranchId());
        paginatedFilter.setFilters(mapContext);
        Pagination pagination = new Pagination();
        pagination.setPageSize(pageSize);
        pagination.setPageNum(pageNum);
        paginatedFilter.setPagination(pagination);
        List<Map<String, String>> sorts = new ArrayList<Map<String, String>>();
        Map<String, String> created = new HashMap<String, String>();
        created.put(WebConstant.KEY_ENTITY_CREATED, "desc");
        sorts.add(created);
        paginatedFilter.setSorts(sorts);
        PaginatedList<EngineeringDto> dtoPaginatedList=this.engineeringService.findEngineeringOrderList(paginatedFilter);
        if(dtoPaginatedList!=null&&dtoPaginatedList.getRows().size()>0){
            for(EngineeringDto engineeringDto:dtoPaginatedList.getRows()){
                String engineeringId=engineeringDto.getId();
               MapContext map= this.engineeringOrderService.findListByEngineeringId(engineeringId);
               engineeringDto.setTotalNum(map.getTypedValue("totalNum",Integer.class));
               engineeringDto.setTotalAmount(map.getTypedValue("totalAmount",BigDecimal.class));
            }
        }
        return ResultFactory.generateRequestResult(dtoPaginatedList);
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult addEngineeringOrder(EngineeringDto engineeringDto) {
        //查询工程单前缀
        String branchId=WebUtils.getCurrBranchId();
        Branch branch = AppBeanInjector.branchService.findById(branchId);
        if(branch==null){
            return ResultFactory.generateResNotFoundResult();
        }
        engineeringDto.setBranchId(branchId);
        String engineeringOrderPrefix = branch.getEngineeringOrderPrefix();
        String engineeringNo=AppBeanInjector.uniquneCodeGenerator.getNoByTime(new Date(), engineeringOrderPrefix);
        String engineeringNoValue=engineeringOrderPrefix+engineeringNo;
        engineeringDto.setNo(engineeringNoValue);
        List<OrderProductDto> orderProdutDtos = engineeringDto.getOrderProdutDtos();
        //添加工程单基础信息
        engineeringDto.setDelFlag(0);
        engineeringDto.setCreator(WebUtils.getCurrUserId());
        engineeringDto.setCreated(new Date());
        //判断客户是否存在
        MapContext filter = new MapContext();
        filter.put(WebConstant.KEY_ENTITY_NAME, engineeringDto.getCustomerName());
        filter.put(WebConstant.KEY_ENTITY_COMPANY_ID, engineeringDto.getCompanyId());
        List<CompanyCustomer> customerByMap = this.companyCustomerService.findCustomerByMap(filter);
        String customerId=null;
        if (customerByMap == null || customerByMap.size() == 0) {
            CompanyCustomer companyCustomer = new CompanyCustomer();
            companyCustomer.setName(engineeringDto.getCustomerName());
            companyCustomer.setCompanyId(engineeringDto.getCompanyId());
            companyCustomer.setCreated(DateUtil.getSystemDate());
            companyCustomer.setCreator(WebUtils.getCurrUserId());
            companyCustomer.setStatus(CompanyCustomerStatus.ORDER.getValue());
            this.companyCustomerService.add(companyCustomer);
            customerId=companyCustomer.getId();
        } else {
            customerId=customerByMap.get(0).getId();
        }
        engineeringDto.setPlaceOrderTime(new Date());
        engineeringDto.setCustomId(customerId);
        this.engineeringService.add(engineeringDto);

        if(orderProdutDtos!=null&&orderProdutDtos.size()>0){
            int size = orderProdutDtos.size();
            for(int i=0;i< orderProdutDtos.size();i++){
                OrderProductDto orderProductDto=orderProdutDtos.get(i);
                //创建订单信息
                CustomOrder customOrder=new CustomOrder();
                customOrder.setCompanyId(engineeringDto.getCompanyId());
                customOrder.setCustomerId(customerId);
                customOrder.setStatus(OrderStatus.TO_QUOTED.getValue());//待报价
                customOrder.setCreated(DateUtil.getSystemDate());
                customOrder.setCreator(WebUtils.getCurrUserId());
                customOrder.setState(1);//工厂创建的订单，state默认为1
                customOrder.setOrderSource(0);//订单来源：工厂
                int prodLength = orderProdutDtos.size();
                String orderNo = null;
                //查询订单编号前缀
                if(size<100){
                    orderNo=engineeringNoValue+"-"+ String.format("%02d",i+1);
                }else if(size<1000){
                    orderNo=engineeringNoValue+"-"+ String.format("%03d",i+1);
                }else {
                    orderNo= engineeringNoValue+"-"+String.format("%04d",i+1);
                }
                customOrder.setNo(orderNo);
                customOrder.setImprest(new BigDecimal(0));
                customOrder.setRetainage(new BigDecimal(0));
                customOrder.setMerchandiser(engineeringDto.getMerchandiser()); //跟单员需用户选择
                customOrder.setEarnest(0);
                customOrder.setMarketPrice(new BigDecimal(0));
                customOrder.setDiscounts(new BigDecimal(0));
                customOrder.setFactoryDiscounts(new BigDecimal(0));
                customOrder.setFactoryFinalPrice(new BigDecimal(0));
                customOrder.setDesignFee(new BigDecimal(0));
                customOrder.setReceiver(WebUtils.getCurrUserId());
                customOrder.setFactoryPrice(customOrder.getMarketPrice());
                customOrder.setAmount(new BigDecimal(0));
                customOrder.setConfirmFprice(false);
                customOrder.setConfirmCprice(false);
                customOrder.setEstimatedDeliveryDate(null);
                customOrder.setCoordination(CustomOrderCoordination.UNWANTED_COORDINATION.getValue());
                customOrder.setChange(0);
                customOrder.setType(OrderType.ENGINEERINGORDER.getValue());//订单类型：工程订单
                customOrder.setOrderProductType(orderProductDto.getType());
                customOrder.setBranchId(WebUtils.getCurrBranchId());
                customOrder.setPlaceOrder(engineeringDto.getPlaceOrder());//下单人
                customOrder.setReceiver(engineeringDto.getReceiver());//接单人
                customOrder.setDesigner(null);
                customOrder.setReceiptTime(DateUtil.getSystemDate());
                customOrder.setPaymentWithholding(0);
                customOrder.setHaveAppendOrder(0);
                customOrder.setFlag(0);
                customOrder.setNotes(orderProductDto.getNotes());
                //收货地址拼接
                String cityareaId = engineeringDto.getCityAreaId();
                CityArea cityarea = this.cityAreaService.findById(cityareaId);
                if (cityarea != null) {
                    String merger = cityarea.getMergerName();
                    String address = merger.substring(3, merger.length()) + engineeringDto.getAddress();
                    customOrder.setAddress(address);
                }
                customOrder.setConsigneeName(engineeringDto.getConsigneeName());
                customOrder.setConsigneeTel(engineeringDto.getConsigneeTel());
                customOrder.setCityAreaId(engineeringDto.getCityAreaId());
                this.customOrderService.add(customOrder);
                //创建订单产品信息
                orderProductDto.setCreator(WebUtils.getCurrUserId());
                orderProductDto.setCreated(new Date());
                orderProductDto.setStatus(OrderProductStatus.TO_PAYMENT.getValue());
                orderProductDto.setFlag(0);
                orderProductDto.setNo(orderNo);
                orderProductDto.setCustomOrderId(customOrder.getId());
                orderProductDto.setPartStock(0);
                orderProductDto.setChange(0);
                orderProductDto.setAftersaleNum(0);
                this.orderProductService.add(orderProductDto);
                this.saveOrderProducts(orderProductDto,orderNo,customOrder.getId());
                //创建部件信息
                List<ProduceOrderDto> produceOrderList = orderProductDto.getProduceOrderList();
                //创建工程单和订单关联信息
                EngineeringOrder engineeringOrder=new EngineeringOrder();
                engineeringOrder.setCustomOrderId(customOrder.getId());
                engineeringOrder.setEngineeringId(engineeringDto.getId());
                this.engineeringOrderService.add(engineeringOrder);
            }
        }
        return ResultFactory.generateSuccessResult();
    }


    private void saveOrderProducts( OrderProductDto orderProductDto, String noVule, String orderId) {
        //添加产品属性
        List<OrderProductAttribute> productAttributeValues = orderProductDto.getProductAttributeValues();
        if(productAttributeValues!=null&&productAttributeValues.size()>0){
            for(OrderProductAttribute attributeValue:productAttributeValues) {
                //查询属性值是否存在
                String keyId=attributeValue.getProductAttributeKeyId();
                String value=attributeValue.getValueName();
                MapContext map0=MapContext.newOne();
                map0.put("productAttributeKeyId",keyId);
                map0.put("attributeValue",value);
                map0.put("delFlag",0);
                ProductAttributeValue newone=this.productAttributeValueService.findByKeyIdAndValue(map0);
                if(newone==null){//不存在，则新建
                    ProductAttributeValue productAttributeValue=new ProductAttributeValue();
                    productAttributeValue.setProductAttributeKeyId(keyId);
                    productAttributeValue.setStatus(1);
                    productAttributeValue.setDelFlag(0);
                    productAttributeValue.setAttributeValue(attributeValue.getValueName());
                    productAttributeValue.setCreator(WebUtils.getCurrUserId());
                    productAttributeValue.setCreated(new Date());
                    productAttributeValue.setKeyName(attributeValue.getKeyName());
                    this.productAttributeValueService.add(productAttributeValue);

                    attributeValue.setProductAttributeValueId(productAttributeValue.getId());
                }else {
                    attributeValue.setProductAttributeValueId(newone.getId());
                }
                attributeValue.setOrderProductId(orderProductDto.getId());
                this.orderProductAttributeService.add(attributeValue);
            }
        }
                //添加产品部件信息
                String productParts = orderProductDto.getProductParts();
                if (productParts != null && !productParts.equals("")) {
                    String[] partsIds = productParts.split(",");
                    Boolean isCoordination = false;
                    String type = "produceOrderType";
                    String productNo = orderProductDto.getNo();
                    for (String id : partsIds) {
                        OrderProductParts parts = new OrderProductParts();
                        parts.setOrderProductId(orderProductDto.getId());
                        parts.setProductPartsId(id);
                        this.orderProductPartsService.add(parts);
                        ProductParts parts1 = this.productPartsService.findById(id);
                        if (parts1 != null) {
                            if (parts1.getProduceType() == ProduceOrderWay.COORDINATION.getValue()) {
                                isCoordination = true;
                            }
                        }
                        String code = parts1.getPartsType().toString();
                        productNo = productNo + this.basecodeService.findByTypeAndCode(type, code).getRemark();

                    }
                    if (isCoordination) {
                        MapContext updateOrder = new MapContext();
                        updateOrder.put(WebConstant.KEY_ENTITY_ID, orderId);
                        updateOrder.put("coordination", CustomOrderCoordination.NEED_COORDINATION.getValue());
                        this.customOrderService.updateByMapContext(updateOrder);
                    }
                    MapContext mapContext = MapContext.newOne();
                    mapContext.put("id", orderProductDto.getId());
                    mapContext.put("no", productNo);
                    this.orderProductService.updateByMapContext(mapContext);
                }

        }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult editEngineeringOrder(String id, MapContext mapContext) {
        Engineering byId = this.engineeringService.findById(id);
        if(byId==null){
            return ResultFactory.generateResNotFoundResult();
        }

        String customerId=null;
        if(mapContext.containsKey("customerName")){
            //判断客户是否存在
            MapContext filter = new MapContext();
            filter.put(WebConstant.KEY_ENTITY_NAME, mapContext.getTypedValue("customerName",String.class));
            filter.put(WebConstant.KEY_ENTITY_COMPANY_ID, byId.getCompanyId());
            List<CompanyCustomer> customerByMap = this.companyCustomerService.findCustomerByMap(filter);
            if (customerByMap == null || customerByMap.size() == 0) {
                CompanyCustomer companyCustomer = new CompanyCustomer();
                companyCustomer.setName(mapContext.getTypedValue("customerName",String.class));
                companyCustomer.setCompanyId(byId.getCompanyId());
                companyCustomer.setCreated(DateUtil.getSystemDate());
                companyCustomer.setCreator(WebUtils.getCurrUserId());
                companyCustomer.setStatus(CompanyCustomerStatus.ORDER.getValue());
                this.companyCustomerService.add(companyCustomer);
                customerId=companyCustomer.getId();
            } else {
                customerId=customerByMap.get(0).getId();
            }
            mapContext.put("customId",customerId);
        }
        mapContext.put("id",id);
        this.engineeringService.updateByMapContext(mapContext);
        //修改订单的公共信息部分
        List<EngineeringOrder> list=this.engineeringOrderService.findByEngineeringId(id);
        if(list!=null&&list.size()>0){
            for (EngineeringOrder engineeringOrder:list){
                if(customerId!=null&&!customerId.equals("")){
                    mapContext.put("customerId",customerId);
                }
                mapContext.put("id",engineeringOrder.getCustomOrderId());
                this.customOrderService.updateByMapContext(mapContext);
            }
        }
        return ResultFactory.generateSuccessResult();
    }

    @Override
    public RequestResult engineeringOrderInfo(String id) {
        EngineeringDto engineeringDto=this.engineeringService.findOneById(id);
        if(engineeringDto==null){
            return ResultFactory.generateResNotFoundResult();
        }
        //判断源数据保存的是省or市or区？
        String provinceId = engineeringDto.getProvinceId();
        String cityId = engineeringDto.getCityId();
        String cityAreaId = engineeringDto.getCityAreaId();
        if (cityId == null) {
            provinceId = engineeringDto.getCityAreaId();
            cityAreaId = null;
        } else if (provinceId == null) {
            provinceId = engineeringDto.getCityId();
            cityId = engineeringDto.getCityAreaId();
            cityAreaId = null;
        }
        engineeringDto.setProvinceId(provinceId);
        engineeringDto.setCityId(cityId);
        engineeringDto.setCityAreaId(cityAreaId);
        String engineeringId=engineeringDto.getId();
        MapContext map= this.engineeringOrderService.findListByEngineeringId(engineeringId);
        engineeringDto.setTotalNum(map.getTypedValue("totalNum",Integer.class));
        engineeringDto.setTotalAmount(map.getTypedValue("totalAmount",BigDecimal.class));
        List<OrderProductDto> list=new ArrayList<>();
        //查询工程单订单明细
        List<EngineeringOrder> byEngineeringId = this.engineeringOrderService.findByEngineeringId(id);
        if(byEngineeringId!=null&&byEngineeringId.size()>0){
          for(EngineeringOrder engineeringOrder:byEngineeringId){
              String customOrderId = engineeringOrder.getCustomOrderId();
              OrderProductDto orderProductDto=   this.engineeringOrderService.findProductByOrderId(customOrderId);//工程单每个子订单都是只有一个产品
              //产品属性
              List<OrderProductAttribute> listByProductId = orderProductAttributeService.findListByProductId(orderProductDto.getId());
              orderProductDto.setProductAttributeValues(listByProductId);
              //部件类型
              String productId=orderProductDto.getId();
              List<ProductPartsDto> byOrderProductId = this.productPartsService.findByOrderProductId(productId);
              orderProductDto.setProductPartsDtos(byOrderProductId);
              list.add(orderProductDto);

          }
        }
        engineeringDto.setOrderProdutDtos(list);
        return ResultFactory.generateRequestResult(engineeringDto);
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult deleteEngineeringOrder(String id) {
        EngineeringDto oneById = this.engineeringService.findOneById(id);
        if (oneById==null){
            return ResultFactory.generateResNotFoundResult();
        }
        //删除订单
        List<EngineeringOrder> byEngineeringId = this.engineeringOrderService.findByEngineeringId(id);
        if(byEngineeringId!=null&&byEngineeringId.size()>0){
            for(EngineeringOrder engineeringOrder:byEngineeringId){
                MapContext mapContext=MapContext.newOne();
                mapContext.put("id",engineeringOrder.getCustomOrderId());
                mapContext.put("flag",1);
                this.customOrderService.updateByMapContext(mapContext);
                //删除工程订单关联信息
                this.engineeringOrderService.deleteById(engineeringOrder.getId());
            }
        }
        //删除工程订单
        this.engineeringService.deleteById(id);
        return ResultFactory.generateSuccessResult();
    }
}
