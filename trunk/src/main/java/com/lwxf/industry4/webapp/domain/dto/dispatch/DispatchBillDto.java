package com.lwxf.industry4.webapp.domain.dto.dispatch;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Map;

import com.lwxf.industry4.webapp.common.enums.dispatch.DispatchBillStatus;
import com.lwxf.industry4.webapp.domain.entity.common.UploadFiles;
import com.lwxf.industry4.webapp.domain.entity.dispatch.DispatchBill;

/**
 * 功能：
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @create：2018/12/20 15:42
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value = "配送单信息",discriminator = "配送单信息")
public class DispatchBillDto extends DispatchBill {
    @ApiModelProperty(value = "物流名称")
    private String logisticsName;//物流名称
    @ApiModelProperty(value = "发货人名字")
    private String consignorName;//发货人名字
    @ApiModelProperty(value = "发货包裹集合")
    private List<DispatchBillItemDto> dispatchBillItemDtoList;
    @ApiModelProperty(value = "资源文件ID集合")
    private List<String> fileIds;
    @ApiModelProperty(value = "资源文件集合")
    private List<UploadFiles> uploadFiles;
    @ApiModelProperty(value = "城市名称")
    private String cityName;
    @ApiModelProperty(value = "发货人名称")
    private String delivererName;
    @ApiModelProperty(value = "产品相关信息")
    private  List<Map> products;//产品相关信息
    @ApiModelProperty(value = "公司名称")
    private String companyName;
    @ApiModelProperty(value = "状态名称")
    private String typeName;
    @ApiModelProperty(value = "生产单ids,逗号分隔")
    private String produceIds;


    public String getDelivererName() {
        return delivererName;
    }

    public void setDelivererName(String delivererName) {
        this.delivererName = delivererName;
    }

    public String getLogisticsName() {
        return logisticsName;
    }

    public void setLogisticsName(String logisticsName) {
        this.logisticsName = logisticsName;
    }

    public String getConsignorName() {
        return consignorName;
    }

    public void setConsignorName(String consignorName) {
        this.consignorName = consignorName;
    }

    public List<DispatchBillItemDto> getDispatchBillItemDtoList() {
        return dispatchBillItemDtoList;
    }

    public void setDispatchBillItemDtoList(List<DispatchBillItemDto> dispatchBillItemDtoList) {
        this.dispatchBillItemDtoList = dispatchBillItemDtoList;
    }
    public List<String> getFileIds() {
        return fileIds;
    }

    public void setFileIds(List<String> fileIds) {
        this.fileIds = fileIds;
    }

    public List<UploadFiles> getUploadFiles() {
        return uploadFiles;
    }

    public void setUploadFiles(List<UploadFiles> uploadFiles) {
        this.uploadFiles = uploadFiles;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<Map> getProducts() {
        return products;
    }

    public void setProducts(List<Map> products) {
        this.products = products;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTypeName() {
        return DispatchBillStatus.getByValue(this.getStatus()).getName();
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getProduceIds() {
        return produceIds;
    }

    public void setProduceIds(String produceIds) {
        this.produceIds = produceIds;
    }
}

