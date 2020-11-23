package com.lwxf.industry4.webapp.domain.dto.engineeringOrder;

import com.lwxf.industry4.webapp.domain.dto.customorder.CustomOrderDto;
import com.lwxf.industry4.webapp.domain.dto.customorder.OrderProductDto;
import com.lwxf.industry4.webapp.domain.entity.engineering.Engineering;
import io.swagger.annotations.ApiModel;
import java.util.*;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2020-05-28 9:23
 * @version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel
public class EngineeringDto extends Engineering {
    private String placeOrderName;
    private String receiverName;
    private String dealerName;
    private String merchandiserName;
    private String customerName;
    private List<OrderProductDto> orderProdutDtos;
    private String provinceId;//省ID
    private String cityId;//市id

    public String getPlaceOrderName() {
        return placeOrderName;
    }

    public void setPlaceOrderName(String placeOrderName) {
        this.placeOrderName = placeOrderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getMerchandiserName() {
        return merchandiserName;
    }

    public void setMerchandiserName(String merchandiserName) {
        this.merchandiserName = merchandiserName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<OrderProductDto> getOrderProdutDtos() {
        return orderProdutDtos;
    }

    public void setOrderProdutDtos(List<OrderProductDto> orderProdutDtos) {
        this.orderProdutDtos = orderProdutDtos;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }
}
