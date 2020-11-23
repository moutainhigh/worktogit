package com.lwxf.industry4.webapp.domain.dto.statement;

import io.swagger.annotations.ApiModelProperty;

public class CountByMonthDto {
    @ApiModelProperty(value = "节点1：1-7号")
    public Double point1;
    @ApiModelProperty(value = "节点2：8-15号")
    public Double point2;
    @ApiModelProperty(value = "节点3：16-23号")
    public Double point3;
    @ApiModelProperty(value = "节点4：24-月底")
    public Double point4;

    public Double getPoint1() {
        return point1;
    }

    public void setPoint1(Double point1) {
        this.point1 = point1;
    }

    public Double getPoint2() {
        return point2;
    }

    public void setPoint2(Double point2) {
        this.point2 = point2;
    }

    public Double getPoint3() {
        return point3;
    }

    public void setPoint3(Double point3) {
        this.point3 = point3;
    }

    public Double getPoint4() {
        return point4;
    }

    public void setPoint4(Double point4) {
        this.point4 = point4;
    }
}
