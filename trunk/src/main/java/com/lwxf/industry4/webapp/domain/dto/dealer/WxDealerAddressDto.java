package com.lwxf.industry4.webapp.domain.dto.dealer;

import com.lwxf.industry4.webapp.domain.entity.dealer.DealerAddress;
import io.swagger.annotations.ApiModelProperty;

/**
 * 功能：微信B端收货地址Dto
 *
 * @author：DJL
 * @create：2019/11/28 11:54
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class WxDealerAddressDto extends DealerAddress {
    @ApiModelProperty(value = "经销商名称")
    private String companyName;
    @ApiModelProperty(value = "省市县区名字")
    private String mergerName;
    @ApiModelProperty(value = "创建人姓名")
    private String creatorName;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getMergerName() {
        return mergerName;
    }

    public void setMergerName(String mergerName) {
        this.mergerName = mergerName;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }
}
