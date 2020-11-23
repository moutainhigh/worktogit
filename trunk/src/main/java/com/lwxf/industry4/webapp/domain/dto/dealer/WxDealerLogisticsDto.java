package com.lwxf.industry4.webapp.domain.dto.dealer;

import com.lwxf.industry4.webapp.domain.entity.dealer.DealerLogistics;
import io.swagger.annotations.ApiModelProperty;

/**
 * 功能：B端小程序经销商物流公司信息Dto
 *
 * @author：DJL
 * @create：2019/11/29 11:22
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class WxDealerLogisticsDto extends DealerLogistics {
    @ApiModelProperty(value = "经销商名称")
    private String companyName;
    @ApiModelProperty(value = "物流公司名称")
    private String logisticsName;
    @ApiModelProperty(value = "创建人姓名")
    private String creatorName;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLogisticsName() {
        return logisticsName;
    }

    public void setLogisticsName(String logisticsName) {
        this.logisticsName = logisticsName;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }
}
