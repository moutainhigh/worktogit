package com.lwxf.industry4.webapp.domain.dto.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 功能：
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @create：2019/5/21 9:29
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value="发货公司",description="发货公司")
public class LcNameAndNum {
    @ApiModelProperty(value="发货公司名称")
    private String lcName;
    @ApiModelProperty(value = "公司发货数量")
    private Integer lcNum;


    public String getLcName() {
        return lcName;
    }

    public void setLcName(String lcName) {
        this.lcName = lcName;
    }

    public Integer getLcNum() {
        return lcNum;
    }

    public void setLcNum(Integer lcNum) {
        this.lcNum = lcNum;
    }
}
