package com.lwxf.industry4.webapp.domain.dto.customorder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderDemand;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderFiles;

/**
 * 功能：
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @create：2018/12/21 11:18
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value = "订单需求",discriminator = "订单需求")
public class CustomOrderDemandDto extends CustomOrderDemand {

    @ApiModelProperty(value = "案例名称")
    private String designSchemeName;
    @ApiModelProperty(value = "产品系列名称")
    private String productSeriesName;
    @ApiModelProperty(value = "创建人名")
    private String creatorName;
    @ApiModelProperty(value = "资源文件集合")
    private List<CustomOrderFiles> customOrderFiles;

    public String getDesignSchemeName() {
        return designSchemeName;
    }

    public void setDesignSchemeName(String designSchemeName) {
        this.designSchemeName = designSchemeName;
    }

    public String getProductSeriesName() {
        return productSeriesName;
    }

    public void setProductSeriesName(String productSeriesName) {
        this.productSeriesName = productSeriesName;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public List<CustomOrderFiles> getCustomOrderFiles() {
        return customOrderFiles;
    }

    public void setCustomOrderFiles(List<CustomOrderFiles> customOrderFiles) {
        this.customOrderFiles = customOrderFiles;
    }
}

