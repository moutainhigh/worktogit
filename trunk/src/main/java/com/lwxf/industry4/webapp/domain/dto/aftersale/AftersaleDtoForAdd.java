package com.lwxf.industry4.webapp.domain.dto.aftersale;

import java.util.List;

import com.lwxf.industry4.webapp.domain.dto.customorder.OrderProductDto;
import com.lwxf.industry4.webapp.domain.entity.aftersale.AftersaleApply;
import com.lwxf.industry4.webapp.domain.entity.aftersale.AftersaleApplyFiles;
import com.lwxf.industry4.webapp.domain.entity.customorder.OrderProduct;
import io.swagger.annotations.ApiModelProperty;

/**
 * 功能：
 *
 * @author：Szhangxiaolin(3965488@qq.com)
 * @create：2019/5/17 0003 12:50
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class AftersaleDtoForAdd extends AftersaleApply {
    @ApiModelProperty(value="客户ID",name="customerId",required=true,example = "")
    private String customerId;//客户ID
    @ApiModelProperty(value="售后订单编号",name="aftersaleOrderNo",required=true,example = "")
    private String aftersaleOrderNo;//售后订单编号
    @ApiModelProperty(value="附件上传列表",name="aftersaleApplyFilesList",example = "")
    private List<AftersaleApplyFiles> aftersaleApplyFilesList;
    @ApiModelProperty(value="收货人名称",name="consigneeName",example = "")
    private String consigneeName; //收货人名称
    @ApiModelProperty(value="收货人电话",name="consigneeTel",example = "")
    private String consigneeTel; //收货人电话
    @ApiModelProperty(value="产品列表",name="orderProducts",example = "")
    List<OrderProductDto> orderProducts;

    @ApiModelProperty(value="业务员（经销商）",name="salesman",required=true,example = "")
    private String salesman;//客户ID
    @ApiModelProperty(value="跟单员（厂方）",name="merchandiser",required=true,example = "")
    private String merchandiser;//客户ID
    @ApiModelProperty(value="操作员",name="operator",required=true,example = "")
    private String operator;

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getSalesman() {
        return salesman;
    }

    public void setSalesman(String salesman) {
        this.salesman = salesman;
    }

    public String getMerchandiser() {
        return merchandiser;
    }

    public void setMerchandiser(String merchandiser) {
        this.merchandiser = merchandiser;
    }

    public List<OrderProductDto> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProductDto> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAftersaleOrderNo() {
        return aftersaleOrderNo;
    }

    public void setAftersaleOrderNo(String aftersaleOrderNo) {
        this.aftersaleOrderNo = aftersaleOrderNo;
    }

    public List<AftersaleApplyFiles> getAftersaleApplyFilesList() {
        return aftersaleApplyFilesList;
    }

    public void setAftersaleApplyFilesList(List<AftersaleApplyFiles> aftersaleApplyFilesList) {
        this.aftersaleApplyFilesList = aftersaleApplyFilesList;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getConsigneeTel() {
        return consigneeTel;
    }

    public void setConsigneeTel(String consigneeTel) {
        this.consigneeTel = consigneeTel;
    }
}
