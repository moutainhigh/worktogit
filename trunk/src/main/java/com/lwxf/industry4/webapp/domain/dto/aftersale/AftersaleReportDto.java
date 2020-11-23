package com.lwxf.industry4.webapp.domain.dto.aftersale;

import io.swagger.annotations.ApiModelProperty;

/**
 * 功能：售后反馈简单报表Dto
 *
 * @author：DJL
 * @create：2019/11/26 9:10
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class AftersaleReportDto {
    @ApiModelProperty(value = "待处理统计数量")
    private Integer waitCount;
    @ApiModelProperty(value = "处理中统计数量")
    private Integer processingCount;
    @ApiModelProperty(value = "处理完成统计数量")
    private Integer completedCount;
    @ApiModelProperty(value = "反馈单统计总数量")
    private Integer totalCount;

    public Integer getWaitCount() {
        return waitCount;
    }

    public void setWaitCount(Integer waitCount) {
        this.waitCount = waitCount;
    }

    public Integer getProcessingCount() {
        return processingCount;
    }

    public void setProcessingCount(Integer processingCount) {
        this.processingCount = processingCount;
    }

    public Integer getCompletedCount() {
        return completedCount;
    }

    public void setCompletedCount(Integer completedCount) {
        this.completedCount = completedCount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}
